import http from 'k6/http';
import { check } from 'k6';
import { randomString, randomIntBetween } from 'https://jslib.k6.io/k6-utils/1.4.0/index.js';
import { Trend } from 'k6/metrics';
import { htmlReport } from 'https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js';
import { textSummary } from 'https://jslib.k6.io/k6-summary/0.0.1/index.js';
import { encodeMessage } from './proto-loader.js';

const responseTime = new Trend('response_time');
const responseSizeMetric = new Trend('reponse_size');

export const options = {
    summaryTrendStats: ['avg', 'min', 'med', 'max', 'p(50)', 'p(90)', 'p(95)', 'p(99)', 'p(99.99)', 'count'],
    vus: 1,
    iterations: 1,
    duration: '20m',
    summaryTimeUnit: 'ms',
};

export default function () {
    const url = 'http://localhost:8080/products';

    const payload = {
        designation: randomString(40, 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'),
        description: randomString(20, 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'),
    };

    const protoData = encodeMessage(payload);

    const params = {
        headers: {
            'Content-Type': 'application/x-protobuf',
            'Accept': 'application/x-protobuf',
            'Connection': 'keep-alive',
            'Accept': '*/*',
            'Cache-Control': 'no-cache',
            'Authorization': 'eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJleGFtcGxlLmlvIiwic3ViIjoiMzAsYWRtaW4xQG1haWwuY29tIiwiZXhwIjoxNzQwNzc3MzcxLCJpYXQiOjE3NDA3NDEzNzEsInJvbGVzIjoiQWRtaW4ifQ.dI4hI5_3ZiKJ7WUrjKSKSkx3CM2PCbZu_oAYvs2zZpVe4noM77NKey9jw37MAvVcCGgOhQdWJ-ZrvxpaDYfXy82GBhpjm3a4vk2ysg3oA5x96WPrvKmB6eJx0SJ-ChBhYNTOR2cE4xd_tiDkpPwM-aqzP8dC2VnMT_7btAsSe1jBm6QaF3A_USxKErIH__qojCHPCTFtnCM9luIFvn13KAzwd7xnoXWlWp9ls_GhqLT_n0igpim7qj8EUAYpBFbVRphbhVXxFVDxXuEV_JoZhDJzUAYaCjQkXcJZ5dl8cLpg5QYGKqK8isilLY1wdcy6PME_udhv0YljV11HhY8RbWKBkuR_7j-aSjv15h71CkMbl8ywCq3YnBxZ4zb6W6wJCKjZ3XUmdjcw3i-xU0Ge6EoqOxSmHecuSut1o3iGl9HTgiYYqIxgNL2kpihvc1b0UZgzkbx93ODp4puLSzdznbTNWnomXhqC5dmy3TxtLs2O8uwLhFtwILBG-nC6kMwpd-4rpNtDIlRstQY8XZJdbg2I9ytZd_z_O9qhu1OTQLXp65fsYv-SyK1gwzUbpsK-rZrokW4dzx2TXikdGOtD9qb7GQXYCE6q8WkA60S-MQOgGNuLtnWmvyCCtrY5LwPIlbakCf3z2Zt7HA2ISR6Si41dIwnY84M9-1WxHT3_MW'
        },
    };

    const res = http.post(url, protoData, params);
    responseTime.add(res.timings.duration);

    const responseSize = res.body ? res.body.length : 0;
    responseSizeMetric.add(responseSize);


    check(res, {
        'is status 201': (r) => r.status === 201,
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
