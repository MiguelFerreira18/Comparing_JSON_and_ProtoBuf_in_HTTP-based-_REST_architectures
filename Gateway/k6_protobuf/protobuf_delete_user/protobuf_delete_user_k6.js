import http from 'k6/http';
import { check } from 'k6';
import { Trend } from 'k6/metrics';
import { htmlReport } from 'https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js';
import { textSummary } from 'https://jslib.k6.io/k6-summary/0.0.1/index.js';


const AUTH_TOKEN = __ENV.AUTH_TOKEN || 'default-token-for-dev-only';
const ITERATIONS = __ENV.ITERATIONS || 1000;
const ITERATION_MULTIPLIER = __ENV.ITERATION_MULTIPLIER || 1;

const responseTime = new Trend('response_time');
const responseSizeMetric = new Trend('reponse_size');


export const options = {
    summaryTrendStats: ['avg', 'min', 'med', 'max', 'p(50)', 'p(90)', 'p(95)', 'p(99)', 'p(99.99)', 'count'],
};

let id = (ITERATIONS == 1000 ? 401 : 1401) * ITERATION_MULTIPLIER;

export default function () {
    const url = `http://localhost:8082/users/${id++}`;



    const params = {
        headers: {
            'Content-Type': 'application/json',
            'Connection': 'keep-alive',
            'Accept': '*/*',
            'Cache-Control': 'no-cache',
            'Authorization': `Bearer ${AUTH_TOKEN}`,
        },
    };

    const res = http.del(url, null, params);
    responseTime.add(res.timings.duration);

    const responseSize = res.body ? res.body.length : 0;
    responseSizeMetric.add(responseSize);

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
