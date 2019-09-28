import Vue from 'vue'
import App from './App.vue'

import VueRouter from 'vue-router'
import routes from './router'
import axios from 'axios'
//import store from './store'

Vue.config.productionTip = false

Vue.use(VueRouter)
axios.defaults.baseURL="/api" //跨域处理

const router=new VueRouter({
  routes
})

//路由守卫
router.beforeEach(function(to,from,next){
  //通常在此调用store中的变量以判断是否处于登录状态
  //console.log(store);
  next();
})

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')

