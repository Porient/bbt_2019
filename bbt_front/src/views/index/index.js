import Vue from "vue";
import { Layout, Avatar, Dropdown, Menu } from "ant-design-vue";
import Search from "@/views/Search/Search.vue";
import { mapGetters } from "vuex";

Vue.use(Layout);
Vue.use(Avatar);
Vue.use(Dropdown);
Vue.use(Menu);

export default {
  name: "index",
  data() {
    return {};
  },
  created() {
    // 检查一遍登录的状态
    this.$store.commit("checkLoginState");
  },
  components: {
    Search,
  },
  methods: {
    toLogin () {
      // 如果没有登录的话，就跳转到登录页面
      if(!this.isLogin){
        this.$router.push("login")
      }
    }
  },
  computed: {
    ...mapGetters({
      isLogin: "getLoginState",
    }),
  },
};
