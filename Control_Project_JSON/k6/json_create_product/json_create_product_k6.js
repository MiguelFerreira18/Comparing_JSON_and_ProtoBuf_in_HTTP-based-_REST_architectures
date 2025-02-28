import http from 'k6/http';
import { check } from 'k6';
import { randomString, randomIntBetween } from 'https://jslib.k6.io/k6-utils/1.4.0/index.js';
import { Trend } from 'k6/metrics';
import { htmlReport } from 'https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js';
import { textSummary } from 'https://jslib.k6.io/k6-summary/0.0.1/index.js';

const responseTime = new Trend('response_time');
const responseSizeMetric = new Trend('reponse_size');


export const options = {
    summaryTrendStats: ['avg', 'min', 'med', 'max', 'p(50)', 'p(90)', 'p(95)', 'p(99)', 'p(99.99)', 'count'],
    vus: 1,
    iterations: 5000,
    duration: '20m',
    summaryTimeUnit: 'ms',
};

export default function () {
    const url = 'http://localhost:8080/products';

    const payload = JSON.stringify({
        designation: randomString(40, 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'),
        description: randomString(20, 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'),
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
            'Connection': 'keep-alive',
            'Accept': '*/*',
            'Cache-Control': 'no-cache',
            'Authorization': 'eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJleGFtcGxlLmlvIiwic3ViIjoiMzAsYWRtaW4xQG1haWwuY29tIiwiZXhwIjoxNzQwNzcyNDk1LCJpYXQiOjE3NDA3MzY0OTUsInJvbGVzIjoiQWRtaW4ifQ.G3H9dVF1nAZwbY0b1s797MisW_mpFtYTFf-FYSujvARK_oiCEDuR8VMwNKnxVBxCpAk5zZRIapVovk6VRkVm_wHgPOAjJpwDBb6ZTmoiTVdhd4BY8OMu_i1OFydmRAWIh9P-ULcz_CWCpR7EPlG7HvMKh-LHfnjsnXegk5w7LrMtZJShQEU3rSjZwJtebHjW3vRl5PwxGc-1cZ8-lzDJ48mzEYjUtkz65f2hEdeIUC3h1wZbngZ8WXnJPrBNTveSXjsrLZ8SiM4K_M9IQ8M6Xr16rb5h-iBdUUsiPNLODkGu6hVhV_sz3an7TXdOY2MatH2pBf1Ipy-janAUXPi4uE-m0f2TKwNohsGiHP1yuBTS2mZObPG2NFaxAVdBaAlovGiY4u0iMNavuQzllMYmz1nXX78BhQTXfKqA5FtdGVw7P8Y2kBUpCxi9hJAvcgX9wt9ktl9yOYpBk5P-McbyOmARhXIFNVWFj-5T0fjYANF1ukzx5wstJ-yhx6NPRVl0t7f_xAJARPHADo1SQ-XkYcLqsaH04nY8Gp-CwD5Nc2_z-M-evrmiJ8CgZnAtDK2NZDVFuxBqZ-0VjBVNOrLhmpnxmyQ_cZHt3FDPLRlHuAARXbOom_fYqTXm91ZuoV_g9muezKI8i_yUzRvuNTLpjU5h7Ufw6pbw_-zLFbAe04I'
        },
    };

    const res = http.post(url, payload, params);
    responseTime.add(res.timings.duration);

    const responseSize = res.body ? res.body.length : 0;
    responseSizeMetric.add(responseSize);

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
