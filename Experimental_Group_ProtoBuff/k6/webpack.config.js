const path = require('path');

module.exports = {
    mode: 'production',
    entry: './protobuf_create_product/protobuf_create_product_k6.js',
    output: {
        path: path.resolve(__dirname, 'dist'),
        filename: 'test.js',
        libraryTarget: 'commonjs'
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /node_modules/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['@babel/preset-env']
                    }
                }
            }
        ]
    },
    target: 'web',
    externals: /k6(\/.*)?/
};