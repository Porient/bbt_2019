<template>
  <a-form :form="form">
    <a-form-item>
      <a-input
        placeholder="邮箱"
        v-decorator="['email',{ rules:[{required:true, message:'请输入你的邮箱地址' } ] } ]"
      >
        <a-icon type="mail" slot="prefix" />
      </a-input>
    </a-form-item>
    <a-form-item>
      <a-input-password
        placeholder="密码"
        v-decorator="['password',{rules:[{required: true, message: '请输入你的密码'}]}]"
      >
        <a-icon type="lock" slot="prefix" />
      </a-input-password>
    </a-form-item>
    <a-row type="flex" justify="space-between">
      <a-col offset="1">
        <a-form-item>
          <div>
            <a-radio-group
              v-decorator="['loginType', {initialValue:'user', rules: [{required: true, message: '请选择你登录类型'}]}]"
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

<script src="./LoginForm.js">
</script>

<style lang="less" scoped>
@import "./LoginForm.less";
</style>