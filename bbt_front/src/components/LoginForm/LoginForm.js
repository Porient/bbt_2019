import Vue from "vue";
import { Form, Input, Button, Icon, Radio, Col, Row } from "ant-design-vue";

Vue.use(Form);
Vue.use(Input);
Vue.use(Button);
Vue.use(Radio);
Vue.use(Icon);
Vue.use(Col);
Vue.use(Row);

export default {
  name: "LoginForm",
  data() {
    return {
      form: this.$form.createForm(this, { name: "loginForm" }),
    };
  },
  methods: {
    login() {},
    resetPassword() {
      this.$router.push({ path: "/forget" });
      
    },
  },
};
