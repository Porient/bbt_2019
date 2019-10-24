import Vue from "vue";
import { Tabs } from "ant-design-vue";
import LoginForm from "@/components/LoginForm/LoginForm.vue";
import RegisterForm from "@/components/RegisterForm/RegisterForm.vue";

Vue.use(Tabs);

export default {
  name: "Login",
  data() {
    return {};
  },
  components: {
    LoginForm,
    RegisterForm,
  },
};
