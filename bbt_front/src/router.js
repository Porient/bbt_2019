import Vue from "vue";
import Router from "vue-router";
import { getUserInfo } from "./commom/js/util.js";

Vue.use(Router);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/testCommunicate",
      name: "testCommunicate",
      component: () => import("@/views/testCommunicate.vue"),
    },
    {
      path: "/",
      redirect: "/index/search",
    },
    {
      path: "/index",
      redirect: "/index/search",
    },
    {
      path: "/index",
      name: "index",
      component: () => import("@/views/index/index.vue"),
      children: [
        {
          path: "search",
          component: () => import("@/views/Search/Search.vue"),
        },
        {
          path: "userspace",
          component: () => import("@/views/UserSpace/UserSpace.vue"),
          beforeEnter: (to, from, next) => {
            const info = getUserInfo();
            if (info && info.type === "user") {
              next();
            } else {
              next("/index");
            }
          },
        },
        {
          path: "detail",
          component: () => import("@/views/ProductDetail/ProductDetail.vue"),
        },
      ],
    },
    {
      path: "/admin",
      name: "admin",
      component: () => import("@/views/BackManagement/BackManagement.vue"),
      beforeEnter: (to, from, next) => {
        const info = getUserInfo();
        if (info && info.type === "admin") {
          next();
        } else {
          next("/index");
        }
      },
    },
    {
      path: "/login",
      name: "login",
      component: () => import("@/views/Login/Login.vue"),
    },
    {
      path: "/forget",
      name: "forget",
      component: () => import("@/views/Forget/Forget.vue"),
    },
  ],
});
