import Vue from "vue";
import { Form, Input, Button, Icon, Col, Row } from "ant-design-vue";

Vue.use(Form);
Vue.use(Input);
Vue.use(Button);
Vue.use(Icon);
Vue.use(Col);
Vue.use(Row);

export default {
  name: "Forget",
  data() {
    return {
      form: this.$form.createForm(this, { name: "forgetForm" }),
    };
  },
  methods: {
    reset() {},
  },
};
