import Vue from "vue";
import { Form } from "ant-design-vue";

Vue.use(Form);

export default {
    name:"LoginRegister",
    data () {
    return {
    }
  },
  methods: {
    callback (key) {
      console.log(key)
    },
  }
}