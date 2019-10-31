<template>
  <div>
    <a-layout class="layout">
      <a-layout-header>
        <a-dropdown :trigger="['click']" placement="bottomRight">
          <div class="nav-right" @click="toLogin">
            <a-avatar size="small" icon="user" class="nav-avatar" />
            <span class="nav-name">未登录</span>
          </div>
          <a-menu slot="overlay" v-if="isLogin">
            <a-menu-item key="0" @click="toUserSpace">
              <a-icon type="user" />个人中心
            </a-menu-item>
            <a-menu-item key="1">
              <a-icon type="logout" />推出登陆
            </a-menu-item>
          </a-menu>
        </a-dropdown>
      </a-layout-header>
      <a-layout-content class="layout-content">
        <router-view></router-view>
      </a-layout-content>
    </a-layout>
  </div>
</template>

<script src="./index.js">
</script>

<style scoped lang="less">
@import "./index.less";
</style>
