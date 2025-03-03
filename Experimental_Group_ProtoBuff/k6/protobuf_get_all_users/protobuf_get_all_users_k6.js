import http from 'k6/http';
import { check } from 'k6';
import { Trend } from 'k6/metrics';
import { htmlReport } from 'https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js';
import { textSummary } from 'https://jslib.k6.io/k6-summary/0.0.1/index.js';

const responseTime = new Trend('response_time');
const responseSizeBodyMetric = new Trend('reponse_size_body');
const responseSizeMetric = new Trend('response_size');


export const options = {
    summaryTrendStats: ['avg', 'min', 'med', 'max', 'p(50)', 'p(90)', 'p(95)', 'p(99)', 'p(99.99)', 'count'],
    vus: 1,
    iterations: 30000,
    duration: '40m',
    summaryTimeUnit: 'ms',
};

export default function () {
    const url = 'http://localhost:8080/users?orderby=id&order=asc';

    const params = {
        headers: {
            'Content-Type': 'application/x-protobuf',
            'Connection': 'keep-alive',
            'Accept': '*/*',
            'Cache-Control': 'no-cache',
            'Authorization': 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLXByb3ZpZGVyIiwic3ViIjoiZGllZ29AZW1haWwuY29tIiwiZXhwIjoxNzQxMDQzNDY1fQ.svLPmL4ZaVNMzd6zGKG1GoYnRbxE--af61f78X8t5OE'
        },
    };

    const res = http.get(url, params);
    responseTime.add(res.timings.duration);

    const responseBodySize = res.body ? res.body.length : 0;
    responseSizeBodyMetric.add(responseBodySize);

    const responseHeaderSize = res.headers ? JSON.stringify(res.headers).length : 0;
    responseSizeMetric.add(responseBodySize + responseHeaderSize);



    check(res, {
        'is status 200': (r) => r.status === 200,
    });
}

export function handleSummary(data) {
    const testStartTime = Date.now() - data.state.testRunDurationMs;
    const testEndTime = Date.now();

    console.log(`Test started at: ${new Date(testStartTime).toISOString()}`);
    console.log(`Test ended at: ${new Date(testEndTime).toISOString()}`);

    return {
        'summary.json': JSON.stringify(data, null, 2),
        'summary.html': htmlReport(data),
        stdout: textSummary(data, { indent: ' ', enableColors: true }),
    };
}
