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
        <div class="tab-content">我的收藏</div>
      </a-tab-pane>
      <a-tab-pane key="footprint">
        <span slot="tab">
          <a-icon type="environment" />我的足迹
        </span>
        <div class="tab-content">我的足迹</div>
      </a-tab-pane>
      <a-tab-pane key="comment">
        <span slot="tab">
          <a-icon type="message" />我的评论
        </span>
        <!-- <div class="tab-content">我的评论</div> -->
        <UserComments />
      </a-tab-pane>
    </a-tabs>
  </div>
</template>

<script src="./UserSpace.js">
</script>

<style lang='less' scoped>
@import "./UserSpace.less";
</style>
