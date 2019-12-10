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
              <a-icon type="logout" @click="logout"/>退出登陆
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

<script>
import Vue from "vue";
import { Layout, Avatar, Dropdown, Menu, Icon } from "ant-design-vue";
import { mapGetters } from "vuex";
import { userInfo } from 'os';
import { getUserInfo } from "@/commom/js/util.js"

Vue.use(Layout);
Vue.use(Avatar);
Vue.use(Dropdown);
Vue.use(Menu);
Vue.use(Icon);

export default {
  name: "index",
  data() {
    return {};
  },
  created() {
    // 检查一遍登录的状态
    this.$store.commit("checkLoginState");
  },
  components: {},
  methods: {
    toLogin() {
      // 如果没有登录的话，就跳转到登录页面
      if (!this.isLogin) {
        this.$router.push("/login");
      }
    },
    toUserSpace() {
      this.$router.push("userspace");
    },
    logout() {
      //发送注销请求
      var info = getUserInfo()
      if (info.type === "user"){
        //用户注销
        this.$api.userLogout({
          userId: info.id,
        }).then(response => {
          if(response.code === 200){
            //修改登录状态
            this.$store.commit("logout")
            //跳转到登录页面
            this.$router.push("/login");
          }
        });
      }else if (info.type === "admin"){
        this.$api.adminLogout({
          admin: info.id,
        }).then(response => {
          if(response.code === 200){
            //修改登录状态
            this.$store.commit("logout")
            //跳转到登录页面
            this.$router.push("/login");
          }
        });
      }
    }
  },
  computed: {
    ...mapGetters({
      isLogin: "getLoginState",
    }),
  },
};
</script>

<style scoped lang="less">
.layout {
  background-color: inherit;
  .ant-layout-header {
    background-color: @primary-color;
  }
  .nav-right {
    float: right;
    padding: 0 12px;
    .nav-avatar {
      margin-right: 8px;
    }

    .nav-name {
      color: white;
    }
    cursor: pointer;
  }

  .nav-right:hover {
    background-color: @link-color;
  }

  .layout-content {
    background-color: inherit;
  }
}
</style>
