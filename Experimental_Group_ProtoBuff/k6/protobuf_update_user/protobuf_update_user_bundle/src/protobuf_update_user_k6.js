import http from "k6/http";
import { htmlReport } from 'https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js';
import { textSummary } from 'https://jslib.k6.io/k6-summary/0.0.1/index.js';
import { randomString, randomIntBetween } from 'https://jslib.k6.io/k6-utils/1.4.0/index.js';
import { check, sleep } from 'k6';
import { Trend } from 'k6/metrics';
const editUserDtoPb = require('./edit_user_dto_pb.js');



const AUTH_TOKEN = __ENV.AUTH_TOKEN || 'default-token-for-dev-only';
const ITERATIONS = __ENV.ITERATIONS || 1000;

const responseTime = new Trend('response_time');
const responseSizeMetric = new Trend('reponse_size');


export const options = {
    summaryTrendStats: ['avg', 'min', 'med', 'max', 'p(50)', 'p(90)', 'p(95)', 'p(99)', 'p(99.99)', 'count'],
};

let id = ITERATIONS == 1000 ? 401 : 1401;

export default function () {
    const url = `http://localhost:8081/users/${id++}`;

    const userDTO = new editUserDtoPb.EditUserDTO();
    userDTO.setUsername(randomString(40, 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'));
    userDTO.setEmail(randomString(10, 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ') + '.' + randomString(10, 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ') + '@mail.com');

    const binaryData = userDTO.serializeBinary();
    const params = {
        headers: {
            'Content-Type': 'application/x-protobuf',
            'Connection': 'keep-alive',
            'Accept': '*/*',
            'Cache-Control': 'no-cache',
            'Authorization': `Bearer ${AUTH_TOKEN}`,
        },
    }

    const res = http.put(url, binaryData, params);
    responseTime.add(res.timings.duration);

    const responseSize = res.body ? res.body.length : 0;
    responseSizeMetric.add(responseSize);

    check(res, {
        'status is 200': (r) => r.status === 200,
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

