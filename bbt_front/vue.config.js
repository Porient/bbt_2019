

module.exports = {
  css: {
    loaderOptions: {
      less: {
        modifyVars: {
          "primary-color": "#1DA57A",   // antd的主题色
          "link-color": "#41b883",      // 链接按钮的主题色
          "border-radius-base": "2px",
        },
        javascriptEnabled: true,
      },
    },
  },
  devServer: {
    open: true, // 自动打开浏览器
    // BASE_API: "", // api 的基础路径
  },
};
