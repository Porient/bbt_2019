import Vue from "vue";
import Router from "vue-router";
import Home from "./views/Home.vue";
import LoginRegister from "./components/LoginRegister/LoginRegister.vue";
import Login from "./components/Login/Login.vue";
import Register from "./components/Register/Register.vue";
import Forget from "./components/Forget/Forget.vue";

Vue.use(Router);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "home",
      component: Home,
    },
    {
      path: "/about",
      name: "about",
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () =>
        import(/* webpackChunkName: "about" */ "./views/About.vue"),
    },
    {
      path:"/logreg",
      name:"loginregister",
      component:LoginRegister,
      redirect:"/logreg/login",
      children:[
        {path:"login",name:"login",component:Login},
        {path:"register",name:"register",component:Register}
      ]
    },
    {
      path:"/forget",
      name:"forget",
      component:Forget 
    }
  ],
});
