export default {
  route: {
    path: '/',
    routes: [
      {
        path: '/welcome',
        name: '概况',
      },
      {
        path: '/baseSetting',
        name: '基本设置',
      },
      {
        path: '/member',
        name: '用户管理',
      },
      {
        path: '/accessToken',
        name: 'AccessToken日志',
      },
      {
        path: '/data',
        name: '数据分析',
      },
      {
        path: '/income',
        name: '收益',
      },
    ],
  },
  location: {
    pathname: '/',
  },
};
