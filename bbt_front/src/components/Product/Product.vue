<template>
  <div>
    <!--表格操作：重载、删除-->
    <div class="batchbutton">
      <a-button type="primary" @click="handleReload" :loading="loading" class="button">批量上架</a-button>
      <a-button
        @click="handleReload"
        :loading="loading"
        class="button"
        style="background-color:grey;color:white;border-color:grey"
      >批量下架</a-button>
      <a-popconfirm placement="top" okText="确认" cancelText="取消" @confirm="handleDelete">
        <template slot="title">
          <p>确认要删除所选项吗</p>
        </template>
        <a-button type="danger" :disabled="!hasSelected" class="button" ghost>批量删除</a-button>
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
      <a slot="name" slot-scope="text">{{text}}</a>
      <span slot="action" slot-scope="text,record" style>
        <a-button
          class="button"
          v-if="record.status == 0"
          href="javascript:;"
          style="color:rgb(29,165,122);border-color:rgb(29,165,122)"
          size="small"
        >
          <span style="font-size:12px">上架</span>
        </a-button>
        <a-button
          class="button"
          v-if="record.status == 1"
          href="javascript:;"
          style="color:rgb(29,165,122);border-color:rgb(29,165,122)"
          size="small"
        >
          <span style="font-size:12px">下架</span>
        </a-button>

        <a-button
          class="button"
          style="color:rgb(29,165,122);border-color:rgb(29,165,122)"
          size="small"
          @click="editModal(record.key)"
        >
          <span style="font-size:12px">编辑</span>
        </a-button>
        <a-modal
          title="手机信息"
          :visible="visible"
          @ok="handleOk"
          :confirmLoading="confirmLoading"
          @cancel="handleCancel"
          :footer="null"
          :centered=true
          width="700px"
        >
          <!-- 解决不重载问题 -->
          <div v-if="visible">
            <BackManageForm v-show="visible" :info="datalist[selectrecord]"></BackManageForm>
          </div>
        </a-modal>

        <a-popconfirm
          title="你确定要删除吗?"
          @confirm="confirm"
          @cancel="cancel"
          okText="Yes"
          cancelText="No"
        >
          <a-button class="button" type="danger" size="small" ghost>
            <span style="font-size:12px">删除</span>
          </a-button>
        </a-popconfirm>
      </span>
      <span slot="status" slot-scope="text,record">
        <span v-if="record.status== 1">
          <svg
            t="1572832880159"
            class="icon"
            viewBox="250 200 650 650"
            version="1.1"
            xmlns="http://www.w3.org/2000/svg"
            p-id="3646"
            width="10"
            height="10"
          >
            <path
              d="M480 480m-160 0a2.5 2.5 0 1 0 320 0 2.5 2.5 0 1 0-320 0Z"
              p-id="3647"
              fill="#1DA57A"
            />
          </svg>
          已上架
        </span>
        <span v-else-if="record.status== 0">
          <svg
            t="1572832880159"
            class="icon"
            viewBox="250 200 650 650"
            version="1.1"
            xmlns="http://www.w3.org/2000/svg"
            p-id="3646"
            width="10"
            height="10"
          >
            <path
              d="M480 480m-160 0a2.5 2.5 0 1 0 320 0 2.5 2.5 0 1 0-320 0Z"
              p-id="3647"
              fill="#bfbfbf"
            />
          </svg>
          未上架
        </span>
        <span v-else-if="record.status== -1">未通过</span>
        <span v-else>状态获取错误</span>
      </span>
    </a-table>
  </div>
</template>

<script src='./Product.js'></script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="less">
@import "./Product.less";
</style>
