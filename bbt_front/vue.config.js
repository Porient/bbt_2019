module.exports = {
    //...
    devServer: {
        proxy: {
            '/api': {
                target: 'http://localhost:9090',
                pathRewrite: {'^/api' : ''},
                "secure": true
            }
        }
    }
};
//跨域处理