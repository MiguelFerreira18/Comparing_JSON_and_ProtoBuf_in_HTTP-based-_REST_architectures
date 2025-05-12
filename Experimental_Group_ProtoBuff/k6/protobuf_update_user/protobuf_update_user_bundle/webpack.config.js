const path = require('path');
const { CleanWebpackPlugin } = require("clean-webpack-plugin");
module.exports = {
    mode: 'production',
    entry: {
        k6: './src/protobuf_update_user_k6.js',
    },
    output: {
        path: path.resolve(__dirname, 'dist'), // eslint-disable-line
        libraryTarget: 'commonjs',
        filename: '[name].bundle.js',
    },
    module: {
        rules: [{ test: /\.js$/, use: 'babel-loader' }],
    },
    plugins: [new CleanWebpackPlugin()],
    stats: {
        colors: true,
    },
    externals: /^(k6|https?\:\/\/)(\/.*)?/,
};