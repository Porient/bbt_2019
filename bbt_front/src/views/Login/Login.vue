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

<script src="./Login.js">
</script>

<style lang="less" scoped>
@import "./Login.less";
</style>