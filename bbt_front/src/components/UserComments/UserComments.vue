<template>
  <div class="user-comment-card">
    <a-card title="用户评论" :bordered="false">
      <div class="content-row">
        <a-button type="primary" class="button">一键清除所有评论</a-button>
        <a-button type="danger">删除评论</a-button>
      </div>
      <a-alert message="Informational Notes" type="info" showIcon class="content-row" />
      <a-table
        :columns="columns"
        :dataSource="dataSource"
        rowKey="commentId"
        :rowSelection="{selectedRowKeys,onChange: onSelectedChange}"
      >
        <span slot="action">
          <a>详情</a>
          <a-divider type="vertical" />
          <a>编辑</a>
          <a-divider type="vertical" />
          <a>删除</a>
        </span>
      </a-table>
    </a-card>
  </div>
</template>

<script src="./UserComments.js">
</script>

<style lang="less" scoped>
@import "./UserComments.less";
</style>