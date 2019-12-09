<template>
  <div class="box">
    <a-tabs defaultActiveKey="login" size="large">
      <a-tab-pane key="login">
        <span slot="tab" class="tab-text">登录</span>
        <LoginForm />
      </a-tab-pane>
      <a-tab-pane key="register">
        <span slot="tab" class="tab-text">注册</span>
        <RegisterForm />
      </a-tab-pane>
    </a-tabs>
  </div>
</template>

<script>
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
</script>

<style lang="less" scoped>
.box {
  width: 450px;
  height: 450px;
  padding: 25px 50px;
  background-color: white;
  display: inline-block;
  margin-top: 200px;
  .tab-text {
    font-size: 25px;
    font-weight: 600;
    margin: 0 8px;
  }
}
</style>
