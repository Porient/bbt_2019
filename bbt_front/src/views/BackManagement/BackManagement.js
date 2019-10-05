import Vue from "vue";
import { Layout, Menu, Icon } from "ant-design-vue";

import Library from "@/components/Library/Library.vue";
// import ScriptSetting from "@/components/ScriptSetting/ScriptSetting.vue";

Vue.use(Layout);
Vue.use(Icon);
Vue.use(Menu);

export default {
  name: "BackManagement",
  data() {
    return {
      collapsed: false,
      whichShow: "Library",
    };
  },
  method: {},
  components: {
    Library,
    // ScriptSetting,
  },
};
