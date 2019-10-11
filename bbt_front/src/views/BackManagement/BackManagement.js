import Vue from "vue";
import { Layout, Menu, Icon, Popconfirm } from "ant-design-vue";

import Library from "@/components/Library/Library.vue";
import ScriptSetting from "@/components/ScriptSetting/ScriptSetting.vue";

Vue.use(Layout);
Vue.use(Icon);
Vue.use(Menu);
Vue.use(Popconfirm)

export default {
  name: "BackManagement",
  data() {
    return {
      collapsed: false,
      whichShow: "Library",
      userName: 13242524133
    };
  },
  method: {
  },
  components: {
    Library,
    ScriptSetting,
  },
};
