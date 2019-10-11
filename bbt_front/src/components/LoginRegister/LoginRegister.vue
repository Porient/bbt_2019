<template>
    <div class="main">
        <div class="container">
            <ul>
                <router-link to="/logreg/login"><li>登录</li></router-link>
                <router-link to="/logreg/register"><li>注册</li></router-link>
            </ul>
            <router-view></router-view>
        </div>
    </div>
</template>

<script src="./LoginRegister.js">
</script>

<style lang="less" scoped>
@import "./LoginRegister.less";
</style>