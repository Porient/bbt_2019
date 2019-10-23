<template>
  <div class="box">
    <a-tabs defaultActiveKey="login" size="large">
      <a-tab-pane key="login">
        <span slot="tab" class="tab-text">登录</span>
        <a-input placeholder="邮箱" class="input" v-model="loginEmail">
          <a-icon type="mail" slot="prefix" />
        </a-input>
        <a-input-password placeholder="密码" class="input" v-model="loginPassworld">
          <a-icon type="lock" slot="prefix" />
        </a-input-password>
        <div class="input-type">
          <a-radio-group v-model="loginType">
            <a-radio value="user">用户</a-radio>
            <a-radio value="admin">管理员</a-radio>
          </a-radio-group>
          <a-button type="link" class="forget">忘记密码?</a-button>
        </div>
        <a-button type="primary" block class="input" @click="login">登录</a-button>
      </a-tab-pane>
      <a-tab-pane key="register">
        <span slot="tab" class="tab-text">注册</span>
        <a-input placeholder="邮箱" class="input" v-model="registerEmail">
          <a-icon type="mail" slot="prefix" />
        </a-input>
        <a-input-password placeholder="密码" class="input" v-model="registerPassworld">
          <a-icon type="lock" slot="prefix" />
        </a-input-password>
      </a-tab-pane>
    </a-tabs>
  </div>
</template>

<script src="./Login.js">
</script>

<style lang="less" scoped>
@import "./Login.less";
</style>