import Vue from "vue";
import { Tabs, Input, Button, Icon, Radio } from "ant-design-vue";

Vue.use(Tabs);
Vue.use(Input);
Vue.use(Button);
Vue.use(Radio);
Vue.use(Icon);

export default {
  name: "Login",
  data() {
    return {
      loginType: "user", // 登录类型：用户或管理员
      loginEmail: "",  // 登录的邮箱
      loginPassworld: "", // 登录的密码
      registerEmail: "",  // 注册的邮箱
      registerPassworld: "", // 注册的密码
    };
  },
  methods: {
    login() {
      console.log(this.email);
    },
  },
};
