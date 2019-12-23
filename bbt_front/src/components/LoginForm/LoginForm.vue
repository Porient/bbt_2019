<template>
  <a-form :form="form">
    <a-form-item>
      <a-input
        placeholder="邮箱"
        v-decorator="[
          'email',
          { rules: [{ required: true, message: '请输入你的邮箱地址' }] },
        ]"
      >
        <a-icon type="mail" slot="prefix" />
      </a-input>
    </a-form-item>
    <a-form-item>
      <a-input-password
        placeholder="密码"
        v-decorator="[
          'password',
          { rules: [{ required: true, message: '请输入你的密码' }] },
        ]"
      >
        <a-icon type="lock" slot="prefix" />
      </a-input-password>
    </a-form-item>
    <a-row type="flex" justify="space-between">
      <a-col offset="1">
        <a-form-item>
          <div>
            <a-radio-group
              v-decorator="[
                'loginType',
                {
                  initialValue: 'user',
                  rules: [{ required: true, message: '请选择你登录类型' }],
                },
              ]"
            >
              <a-radio value="user">用户</a-radio>
              <a-radio value="admin">管理员</a-radio>
            </a-radio-group>
          </div>
        </a-form-item>
      </a-col>
      <a-col>
        <a-form-item>
          <a-button type="link" @click="resetPassword">忘记密码?</a-button>
        </a-form-item>
      </a-col>
    </a-row>
    <a-form-item>
      <a-button type="primary" block @click="login">登录</a-button>
    </a-form-item>
  </a-form>
</template>

<script>
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
    login() {
      // 使用组件的校验函数获取输入值
      this.form.validateFields((error, values) => {
        if (!error) {
          if (values.loginType === "user") {
            // 用户登陆
            this.$api
              .userLogin({
                userEmail: values.email, // 请求需要的参数
                password: values.password,
              })
              .then(response => {
                // response 就是接口返回的东西
                // 在这处理下一步
                if (response.code === 200) {
                  // 成功登陆以后

                  // 获取用户的详细信息
                  this.$api
                    .getUserDetail({
                      userId: response.data.userId,
                    })
                    .then(res => {
                      this.$store.commit("login", {
                        id: res.data.userId,
                        type: values.loginType,
                        email: values.email,
                        password: values.password,
                        name: res.data.nickname,
                      });
                    });
                  // 记录用户的信息
                  this.$store.commit("login", {
                    email: values.email,
                    password: values.password,
                    type: values.loginType,
                    id: response.data.userId,
                  });
                  //跳转到搜索页面
                  this.$router.push("/index");
                }
              });
          } else if (values.loginType === "admin") {
            // 管理员登陆
            this.$api
              .adminLogin({
                adminEmail: values.email,
                password: values.password,
              })
              .then(response => {
                if (response.code === 200) {
                  // 成功登陆以后
                  this.$store.commit("login", {
                    email: values.email,
                    password: values.password,
                    type: values.loginType,
                    id: response.data.adminId,
                  });
                  //跳转到搜索页面
                  this.$router.push("/index");
                }
              });
          }
        }
      });
    },
    resetPassword() {
      this.$router.push({ path: "/forget" });
    },
  },
};
</script>

<style lang="less" scoped></style>
