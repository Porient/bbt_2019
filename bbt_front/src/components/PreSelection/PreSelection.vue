<template>
  <div>
    <!--表格操作：重载、删除-->
    <div class="batchButton">
      <a-button type="primary" @click="handleReload" :loading="produce_loading" class="button">批量生成</a-button>
      <a-popconfirm placement="top" okText="确认" cancelText="取消" @confirm="batchDelete(selectedRowKeys)">
        <template slot="title">
          <p>确认要删除所选项吗</p>
        </template>
        <a-button type="danger" :disabled="!hasSelected" :loading="delete_loading" class="button" ghost>批量删除</a-button>
      </a-popconfirm>

      <span class="choose">
        <template>
          <a-alert :message="`已选择 ${selectedRowKeys.length} 项`" type="success" showIcon />
        </template>
      </span>
    </div>
    <a-table
      :columns="columns"
      :dataSource="datalist"
      :pagination="{pageSize:50}"
      :rowSelection="{selectedKeys: selectedRowKeys, onChange: onSelectChange}"
    >
      <span slot="status" slot-scope="text,record">
        <span v-if="record.status== 1">已通过</span>
        <span v-else-if="record.status== 0">未审核</span>
        <span v-else-if="record.status== -1">未通过</span>
        <span v-else>状态获取错误</span>
      </span>
      <span slot="action" slot-scope="text,record" style>
        <a-button
          class="button"
          v-if="record.status == 0"
          href="javascript:;"
          style="color:rgb(29,165,122);border-color:rgb(29,165,122)"
          size="small"
        >
          <span style="font-size:12px">生成报告</span>
        </a-button>
        <a-button
          v-if="record.status == 1"
          href="javascript:;"
          class="button"
          style="background-color:#1ABC9C; color:white"
        >查看报告</a-button>
        <a-button
          class="button"
          style="color:rgb(29,165,122);border-color:rgb(29,165,122)"
          size="small"
          @click="editModal(record.key)"
        >
          <span style="font-size:12px">编辑</span>
        </a-button>
        <a-modal
          :title="`${datalist[selectrecord].type}信息`"
          :visible="visible"
          @ok="handleOk"
          :confirmLoading="confirmLoading"
          @cancel="handleCancel"
          :centered="true"
          :footer="null"
          width="700px"
        >
          <!-- 解决不重载问题 -->
          <div v-if="visible&&datalist[selectrecord].type=='手机'">
            <PhoneForm
              v-show="visible&&datalist[selectrecord].type=='手机'"
              :info="datalist[selectrecord]"
              v-on:update="receive"
            ></PhoneForm>
          </div>

          <div v-if="visible&&datalist[selectrecord].type=='电脑'">
            <ComputerForm
              v-show="visible&&datalist[selectrecord].type=='电脑'"
              :info="datalist[selectrecord]"
              v-on:update="receive"
            ></ComputerForm>
          </div>
        </a-modal>

        <a-popconfirm
          title="你确定要删除吗?"
          @confirm="()=>onDelete(record.key)"
          @cancel="cancel"
          okText="Yes"
          cancelText="No"
        >
          <a-button class="button" href="javascript:;" type="danger" size="small" ghost>
            <span style="font-size:12px">删除</span>
          </a-button>
        </a-popconfirm>
      </span>
    </a-table>
  </div>
</template>

<script src='./PreSelection.js'></script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="less">
@import "./PreSelection.less";
</style>
