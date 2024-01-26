module.exports = {
  //打包之后整个工程放在什么文件夹下
  outputDir: 'dist',
  //打包基本目录
  publicPath: './',
  //静态资源目录
  assetsDir: 'assets',

  devServer: {
    //修改启动端口
    port: 8082,
    proxy: {
      '/his': {
        //设置需要跨域访问的url
        target: 'http://localhost:8888',
        //使用webSocket实现服务代理
        ws: true,
        //允许跨域
        changeOrigin: true,
        //重写url
        // pathRewrite: {
          '^/his': ''
        }
      }
    }
  }