<template>
  <a-form :form="form">
    <a-form-item class="row">
      <a-input
        placeholder="邮箱"
        v-decorator="['email',{ rules:[{required:true, message:'请输入你的邮箱地址' } ] } ]"
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
    </a-form-item >
    <a-row>
      <a-col :span="16">
        <a-form-item>
          <a-input
            placeholder="验证码"
            v-decorator="['verifyCode',{rules:[{required: true, message: '请输入你的验证码'}]}]"
          >
            <a-icon type="safety" slot="prefix" />
          </a-input>
        </a-form-item>
      </a-col>
      <a-col :span="8">
        <a-form-item>
          <a-button type="primary">发送验证码</a-button>
        </a-form-item>
      </a-col>
    </a-row>
    <a-form-item>
      <a-button type="primary" block @click="register">注册</a-button>
    </a-form-item>
  </a-form>
</template>

<script src="./RegisterForm.js">
</script>

<style lang='less' scoped>
@import "./RegisterForm.less";
</style>