import Vue from "vue";
import { Button } from "ant-design-vue";

Vue.use(Button);

export default {
  name: "HelloWorld",
  props: {
    msg: String,
  },
};
