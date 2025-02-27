import http from 'k6/http';
import { check } from 'k6';
import { randomString, randomIntBetween } from 'https://jslib.k6.io/k6-utils/1.4.0/index.js';
import { Trend } from 'k6/metrics';
import { htmlReport } from 'https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js';
import { textSummary } from 'https://jslib.k6.io/k6-summary/0.0.1/index.js';

const responseTime = new Trend('response_time');
const responseSizeMetric = new Trend('reponse_size');

const requestSizePayloadMetric = new Trend('request_size_payload');

export const options = {
    summaryTrendStats: ['avg', 'min', 'med', 'max', 'p(50)', 'p(90)', 'p(95)', 'p(99)', 'p(99.99)', 'count'],
    vus: 1,
    iterations: 5000,
    duration: '20m',
    summaryTimeUnit: 'ms',
};

export default function () {
    const url = 'http://localhost:8080/products/c1d4e7r8d5f2/reviews';

    const payload = JSON.stringify({
        reviewText: randomString(30, 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'),
        userID: 22,
        rating: randomIntBetween(1, 4),
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
            'Connection': 'keep-alive',
            'Accept': '*/*',
            'Cache-Control': 'no-cache',
            'Authorization': 'eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJleGFtcGxlLmlvIiwic3ViIjoiMzAsYWRtaW4xQG1haWwuY29tIiwiZXhwIjoxNzQwNzE1MzMyLCJpYXQiOjE3NDA2NzkzMzIsInJvbGVzIjoiQWRtaW4ifQ.f5zncTtDmJA9q76Vqk0PyvdDwfKPwSh4fXmSIvTp2RyqM76RaXMM76DhMTHpoOmz0MOh67BhpW9U09wmVBX6epucUYXPjqE53u5Thf1MLfZLM7J92JZ5sAE16yMcuZYhAdyep5jalRRkyw34FZ8hF9yE8UjgR5q2CL5p0jaVDE7fPShbj8D4XqvUd2U995pD8ptMPZc4MEpqnOmLCED-i_Cy1DpYG3MYjiHeE5xToCrqLp7tVgZjjBGjrpGDkAofmCWRfJ7X3VHrRKRt-WXwqlWP_3WHmcV0lmu0JSpfIGTTEwVyU_KOv1OTI4msYFDMzsE6zG9TyZzciuyVvdH_L9Kih0AvtvP0NSdmtA5ryPeTBdjyZAECEmymHAnViZABdyrGZOHUtWrF6HTLF9MF6NhK0xWmMNvIqQ8UrIt0e2s94gSvfdOk52eQf4JE4Rzi3ddYRCiIf_jYhCBnFJdWwcwBuSVPpK5NK02y5tDQkSS1yESIWtc_D5z5gemzRAOj7wmqkMNgm3QTiyLGzLxjwq9UgMc5syH2OLxQFgOKKmbXDtA9joPDFBcuoktXTqHJh5yqg0JLYrDgqUd0ZjRW1IYdiGCaRDUx3I9sgiZdAz92M_5Ka5EmOR-CzpnlKMsvgQR4D4BJQy97A6XeKVQE5Pgm5eJjG1Knn8IAtnll_RE'
        },
    };

    const res = http.post(url, payload, params);
    responseTime.add(res.timings.duration);

    const responseSize = res.body ? res.body.length : 0;
    responseSizeMetric.add(responseSize);

    const requestSizePayload = new TextEncoder().encode(payload).length;
    requestSizePayloadMetric.add(requestSizePayload);


    check(res, {
        'is status 200': (r) => r.status === 201,
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
