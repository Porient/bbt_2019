import Vue from "vue";
import { Layout, Menu, Icon, Popconfirm } from "ant-design-vue";

import Product from "@/components/Product/Product.vue";
import PreSelection from "@/components/PreSelection/PreSelection.vue";

Vue.use(Layout);
Vue.use(Icon);
Vue.use(Menu);
Vue.use(Popconfirm)

export default {
  name: "BackManagement",
  data() {
    return {
      collapsed: false,
      whichShow: "Product",
      userName: 13242524133
    };
  },
  method: {
  },
  components: {
    Product,
    PreSelection,
  },
};
