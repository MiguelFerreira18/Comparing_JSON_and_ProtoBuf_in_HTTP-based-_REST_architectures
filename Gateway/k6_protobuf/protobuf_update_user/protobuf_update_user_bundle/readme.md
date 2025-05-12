# How to run the bundled k6 test

### To generate the pb.js file

```bash
npm install -g protoc-gen-js
```

Then run the following command to generate the pb.js file

```bash
protoc --js_out=import_style=commonjs,binary:. ./src/edit_user_dto.proto
```

(Make sure the proto compiler is installed and in your PATH)

### To run the test

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
k6 run --out csv=protobuf_update_user_report_xx.csv dist/k6.bundle.js # Replace xx with the number of the test
```
