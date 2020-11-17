
module.exports = [
  {
    context: '/booking/api',
    target: 'http://localhost:8080',
    secure: true,
    changeOrigin: true
  },
  {
    context: '/booking/auth',
    target: 'http://localhost:8080/auth',
    secure: true,
    changeOrigin: true,
    onProxyRes: proxyResponse => {
      if (proxyResponse.headers['set-cookie']) {
        proxyResponse.headers['set-cookie'] = proxyResponse.headers['set-cookie'].map(cookie => {
          return cookie
            .replace(/; secure/gi, '')
            .replace(/Domain=localhost/gi, 'Domain=localhost');
        });
      }
    },

  }
];
