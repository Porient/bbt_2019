import Vue from "vue";
import { Tabs, Card } from "ant-design-vue";
import UserInfo from "@/components/UserInfo/UserInfo.vue";
import UserComments from "@/components/UserComments/UserComments.vue";

Vue.use(Tabs);
Vue.use(Card);

export default {
  name: "UserSpace",
  components: {
    UserInfo,
    UserComments,
  },
};