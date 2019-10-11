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
                    <a-radio-group :options="options" @change="onChange2" v-model="value" />
                    <router-link
                        class="login-form-forgot"
                        to="/forget"
                    >
                        忘记密码？
                    </router-link>
                    <a-button
                        type="primary"
                        html-type="submit"
                        class="login-form-button"
                    >
                        登录
                    </a-button>
                </a-form-item>
            </a-form>
        </div>
    </div>
  
</template>

<script src="./Login.js"></script>

<style scoped lang="less">
@import "./Login.less";
</style>