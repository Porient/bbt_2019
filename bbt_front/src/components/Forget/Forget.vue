<template>
    <div class="main">
        <div class="container">
            <div id="title">
                重置密码
            </div>

            <div id="forget_main">
                <div id="forget_container">
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
                                { rules: [{ required: true, message: 'Please input your e-mail!' }] }
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
                        <a-col :span="15">
                            <a-input
                                v-decorator="[
                                'code',
                                { rules: [{ required: true, message: 'Please input your identifying code!' }] }
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
                            <a-input
                                v-decorator="[
                                'newpsd',
                                { rules: [{ required: true, message: 'Please input your new Password!' }] }
                                ]"
                                type="password"
                                placeholder="请输入新密码"
                            >
                                <a-icon
                                slot="prefix"
                                type="lock"
                                style="color: rgba(0,0,0,.25)"
                                />
                            </a-input>
                        </a-form-item>
                        <a-form-item>
                            <a-input
                                v-decorator="[
                                'renewpsd',
                                { rules: [{ required: true, message: 'Please reinput your new Password!' }] }
                                ]"
                                type="password"
                                placeholder="再次输入新密码"
                            >
                                <a-icon
                                slot="prefix"
                                type="lock"
                                style="color: rgba(0,0,0,.25)"
                                />
                            </a-input>
                        </a-form-item>
                        <a-form-item>
                            <a-button
                                type="primary"
                                html-type="submit"
                                class="login-form-button"
                            >
                                重置密码
                            </a-button>
                        </a-form-item>
                    </a-form>

                   
                    <router-link to="/logreg/login">
                            <a-icon type="arrow-left" />返回登录注册界面
                    </router-link>
                    
                </div>
            </div>
        </div>
    </div>
</template>

<script src="./Forget.js"></script>

<style scoped lang="less">
@import "./Forget.less";
</style>