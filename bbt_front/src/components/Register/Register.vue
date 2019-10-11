<template>
    <div id="main">
        <div id="container">
            <a-form
                id="components-form-demo-normal-login"
                :form="form"
                class="login-form"
                @submit="handleSubmit"
            >
                <a-form-item>
                    <a-input
                        v-decorator="[
                        'mail',
                        { rules: [{ required: true, message: 'Please input your username!' }] }
                        ]"
                        placeholder="邮箱"
                    >
                        <a-icon
                        slot="prefix"
                        type="mail"
                        style="color: rgba(0,0,0,.25)"
                        />
                    </a-input>
                </a-form-item>
                <a-form-item>
                    <a-input
                        v-decorator="[
                        'username',
                        { rules: [{ required: true, message: 'Please input your username!' }] }
                        ]"
                        placeholder="昵称"
                    >
                        <a-icon
                        slot="prefix"
                        type="user"
                        style="color: rgba(0,0,0,.25)"
                        />
                    </a-input>
                </a-form-item>
                <a-form-item>
                    <a-input
                        v-decorator="[
                        'password',
                        { rules: [{ required: true, message: 'Please input your Password!' }] }
                        ]"
                        type="password"
                        placeholder="密码"
                    >
                        <a-icon
                        slot="prefix"
                        type="lock"
                        style="color: rgba(0,0,0,.25)"
                        />
                    </a-input>
                </a-form-item>
                <a-form-item>
                  <a-col :span="15">
                    <a-input
                        v-decorator="[
                        'code',
                        { rules: [{ required: true, message: 'Please input your Password!' }] }
                        ]"
                        placeholder="请输入验证码"
                    >
                        <a-icon
                        slot="prefix"
                        type="safety"
                        style="color: rgba(0,0,0,.25)"
                        />
                    </a-input>
                  </a-col>
                  <a-col :span="8">
                    <a-button
                          type="primary"
                          html-type="submit"
                          class="login-form-button"
                          @click="sendCode"
                      >
                          发送验证码
                    </a-button> 
                  </a-col> 
                </a-form-item>
                <a-form-item>
                    <a-button
                        type="primary"
                        html-type="submit"
                        class="login-form-button"
                    >
                        注册
                    </a-button>
                </a-form-item>
            </a-form>
        </div>
    </div>
  
</template>

<script src="./Register.js"></script>

<style scoped lang="less">
@import "./Register.less";
</style>