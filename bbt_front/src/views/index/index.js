import Vue from "vue";
import { Layout, Avatar, Dropdown, Menu, Icon } from "ant-design-vue";
import { mapGetters } from "vuex";

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
        this.$router.push("login");
      }
    },
    toUserSpace() {
      this.$router.push("userspace");
    },
  },
  computed: {
    ...mapGetters({
      isLogin: "getLoginState",
    }),
  },
};
