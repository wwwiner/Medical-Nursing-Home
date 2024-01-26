import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUi from 'element-ui'
import BaiduMap from 'vue-baidu-map'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import qs from 'qs'
import 'lib-flexible/flexible'
import {setSessionStorage,getSessionStorage} from './common.js'

import Vant from 'vant';
import 'vant/lib/index.css';
Vue.use(Vant);


Vue.config.productionTip = false
//全局应用ElementUi
Vue.use(ElementUi);
//axios连接服务器端
axios.defaults.baseURL = 'http://localhost:8082/his/';
//将axios挂载到vue
Vue.prototype.$axios = axios;
Vue.prototype.$qs = qs;
Vue.prototype.$setSessionStorage = setSessionStorage;
Vue.prototype.$getSessionStorage = getSessionStorage;
//将百度地图挂载到vue
Vue.use(BaiduMap, {
  // ak: 'vYDEkBdfiRt3t5HEFUkXs9khSxrDnP5o'
  ak: 'B9jDoDTF5QMYEWGvH37MpmMn9zbtTuYL'
})

router.beforeEach(function (to, from, next) {
  let user = sessionStorage.getItem("user");
  if (to.path == '/index' || to.path == '/' || to.path == '/deptartmentList' || to.path == '/userList') {
    console.log(user);
    if (user == null) {
      console.log("检测到未登录，正在跳转到登录页面");
      router.push({
        path: "/login",
      });   
      location.reload();
    }
  }
  next();
});



new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
