<template>
    <a-layout id="components-layout-demo-custom-trigger">
      <a-layout-sider
        :trigger="null"
        collapsible
        v-model="collapsed"
      >
      <div class="logo" />
      <a-menu theme="dark" mode="inline" :defaultSelectedKeys="['1']">
        <a-menu-item key='1'>
          <a-icon type="products" />
          <span>产品库管理</span>
        </a-menu-item>
        <a-menu-item key='2'>
          <a-icon type="setting" />
          <span>爬取管理</span>
        </a-menu-item>
      </a-menu>
    </a-layout-sider>
    <a-layout>
      <a-layout-header style="background: #fff; padding: 0">
        <a-icon
          class="trigger"
          :type="collapsed ? 'menu-unfold' : 'menu-fold'"
          @click="()=> collapsed = !collapsed"
        />
      </a-layout-header>
      <a-layout-content :style="{ margin: '24px 16px', padding: '24px', background: '#fff', minHeight: '280px' }">
        <!--根据绑定的组件名称改变-->
        <component v-bind:is="whichShow"></component>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<script src='./BackManagement.js'></script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="less">
@import "./BackManagement.less";
</style>
