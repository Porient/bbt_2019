<template>
  <a-form :form="form">
    <a-form-item class="row">
      <a-input
        placeholder="邮箱"
        v-decorator="['email',{ rules:[{required:true, message:'请输入你的邮箱地址' }, {pattern: /^\w+@[a-z0-9]+\.[a-z]{2,4}$/, message: '请输入正确的邮箱地址'}] } ]"
      >
        <a-icon type="mail" slot="prefix" />
      </a-input>
    </a-form-item>
    <a-form-item class="row">
      <a-input
        placeholder="昵称"
        v-decorator="['username',{ rules:[{required:true, message:'请输入你的昵称' } ] } ]"
      >
        <a-icon type="user" slot="prefix" />
      </a-input>
    </a-form-item>
    <a-form-item class="row">
      <a-input-password
        placeholder="密码"
        v-decorator="['password',{rules:[{required: true, message: '请输入你的密码'}]}]"
      >
        <a-icon type="lock" slot="prefix" />
      </a-input-password>
    </a-form-item>
    <a-row>
      <a-col :span="12">
        <a-form-item>
          <a-input
            placeholder="验证码"
            v-decorator="['verifyCode',{rules:[{required: true, message: '请输入你的验证码'}]}]"
          >
            <a-icon type="safety" slot="prefix" />
          </a-input>
        </a-form-item>
      </a-col>
      <a-col :span="8" :offset="2">
        <a-form-item>
          <a-button type="primary" @click="getCode" :loading="getCodeLoading">发送验证码</a-button>
        </a-form-item>
      </a-col>
    </a-row>
    <a-form-item>
      <a-button type="primary" block @click="register">注册</a-button>
    </a-form-item>
  </a-form>
</template>

<script>
import Vue from "vue";
import { Form, Input, Button, Icon, Col, Row } from "ant-design-vue";
// import { ENGINE_METHOD_NONE } from "constants";
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
      getCodeLoading: false, // 获取验证码的时间间隔状态
      verifyCode: "", // 验证码
    };
  },
  methods: {
    register() {
      //使用组件的校验函数获取输入值
      this.form.validateFieldsAndScroll((error, values) => {
        if (!error) {
          this.$api
            .register({
              user: {
                userEmail: values.email,
                nickname: values.username,
                password: values.password,
              },
              verifyCode: this.verifyCode
            })
            .then(response => {
              if (response.code === 200) {
                //成功注册，自动登录
                this.$message.success("注册成功");
              } else {
                this.$message.error(response.msg);
              }
            });
        }
      });
    },

    //发送验证码
    getCode() {
      this.form.validateFieldsAndScroll(["email"], (error, values) => {
        if (!error) {
          // 不允许频繁请求验证码，60秒发一次
          this.getCodeLoading = true;
          setTimeout(() => {
            this.getCodeLoading = false;
          }, 60000);
          this.$api
            .registerCode({
              userEmail: values.email,
            })
            .then(response => {
              console.log(response);
              if (response.code === 200) {
                this.verifyCode = response.data.verifyCode;
                this.$message.success(response.msg);
              } else {
                this.$message.error(response.msg);
              }
            });
        }
      });
    },

    // 校验验证码
    validateCode(rule, value, callback) {
      console.log(rule, value);
      callback();
    },
  },
};
</script>

<style lang='less' scoped>
.row {
  margin-bottom: 16px;
}
</style>