<template>
  <div class="userspace">
    <a-tabs
      defaultActiveKey="info"
      tabPosition="left"
      :tabBarStyle="{backgroundColor: 'white', padding: '5px 0', borderLeft: 'none'}"
    >
      <a-tab-pane key="info">
        <span slot="tab">
          <a-icon type="user" />个人信息
        </span>
        <UserInfo />
      </a-tab-pane>
      <a-tab-pane key="personal">
        <span slot="tab">
          <a-icon type="solution" />个人画像
        </span>
        <Personal />
      </a-tab-pane>
      <a-tab-pane key="collection">
        <span slot="tab">
          <a-icon type="star" />我的收藏
        </span>
        <MyCollection />
      </a-tab-pane>
      <a-tab-pane key="footprint">
        <span slot="tab">
          <a-icon type="environment" />我的足迹
        </span>
        <MyFootStep />
      </a-tab-pane>
      <a-tab-pane key="comment">
        <span slot="tab">
          <a-icon type="message" />我的评论
        </span>
        <UserComments />
      </a-tab-pane>
    </a-tabs>
  </div>
</template>

<script>
import Vue from "vue";
import { Tabs, Card } from "ant-design-vue";
import UserInfo from "@/components/UserInfo/UserInfo.vue";
import UserComments from "@/components/UserComments/UserComments.vue";
import Personal from "@/components/Personal/Personal.vue";
import MyCollection from "@/components/MyCollection/MyCollection.vue";
import MyFootStep from "@/components/MyFootStep/MyFootStep.vue";

Vue.use(Tabs);
Vue.use(Card);

export default {
  name: "UserSpace",
  components: {
    UserInfo,
    UserComments,
    Personal,
    MyCollection,
    MyFootStep,
  },
};
</script>

<style lang='less' scoped>
.userspace {
  width: 1000px;
  display: inline-block;
  margin-top: 50px;
}
.tab-content {
  background-color: white;
  height: 600px;
}
</style>
