import Vue from "vue";
import { Form, Input, Button, Icon, Col, Row } from "ant-design-vue";
import { ENGINE_METHOD_NONE } from "constants";
//import { threadId } from "worker_threads";

Vue.use(Form);
Vue.use(Input);
Vue.use(Button);
Vue.use(Icon);
Vue.use(Col);
Vue.use(Row);

export default {
  name: "RegisterForm",
  data() {
    return {
      form: this.$form.createForm(this, { name: "registerForm" }),
      verifyCode: 0
    };
  },
  methods: {
    register() {
      //使用组件的校验函数获取输入值
      this.form.validateFields((error, values) => {
        if (!error) {
          //用户注册,需要先判断VerifyCode是否相同
          if (values.code === this.verifyCode){
            this.$api.userRegister({
              userEmail: values.email,
              username: values.username,
              password: values.password,
            }).then(response => {
              if(response === 200) {
                //成功注册，跳转到login
                this.$router.push({ path: "/login"});
              }
            });
          }else{
            console.log("验证码错误")
          }
        }
      });
    },

    getCode() {
      //发送验证码
      this.form.validateFields((error, values) => {
        if (!error) {
          this.$api.userCode({
            userEmail: values.email,
          }).then(response => {
            if (response.code === 200) {
              this.verifyCode = response.verifyCode
            }
          })
        }
      })
    },
  },
};
