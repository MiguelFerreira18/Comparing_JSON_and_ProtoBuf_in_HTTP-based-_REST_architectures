# How to run the bundled k6 test

Install the dependencies

```bash
npm install
```

Build the bundle with webpack

```bash
npm run build
```

Run the created bundle inside the dist folder

```bash
k6 run --out csv=protobuf_create_user_report_xx.csv dist/k6.bundle.js # Replace xx with the number of the test
```
