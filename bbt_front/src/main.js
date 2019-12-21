import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import * as api from "./commom/js/api.js";
import {
  message
} from 'ant-design-vue';

Vue.config.productionTip = false;
Vue.prototype.$api = api;
Vue.prototype.$message = message

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount("#app");
