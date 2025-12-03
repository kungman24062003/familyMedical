// vue.config.js
module.exports = {
  devServer: {
    port: 5173, // your frontend port
    proxy: {
      '/api': {
        target: 'http://localhost:8080', // your Spring Boot backend
        changeOrigin: true,
        secure: false,
        pathRewrite: { '^/api': '/api' } // keeps /api prefix
      },
      '/oauth2': {
        target: 'http://localhost:8080',
        changeOrigin: true,
        secure: false,
        pathRewrite: { '^/oauth2': '/oauth2' } // keeps /oauth2 prefix
      }
    }
  }
}
