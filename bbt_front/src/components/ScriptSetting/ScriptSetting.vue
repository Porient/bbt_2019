<template>
  <div>
    <div>
      <!--文件拖拽式上传-->
      <a-upload-dragger
        name="file"
        :multiple="true"
        action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
        @change="handleFileChange"
      >
        <p class="ant-upload-drag-icon">
          <a-icon type="inbox" />
        </p>
        <p class="ant-upload-text">Click or drag file to this area to upload</p>
        <p
          class="ant-upload-hint"
        >Support for a single or bulk upload. Strictly prohibit from uploading company data or other band files</p>
      </a-upload-dragger>
    </div>

    <div>
      <!--表格操作：重载、新增、删除、提交-->
      <div style="margin-bottom: 16px">
        <a-button
          type="primary"
          @click="handleReload"
          :disabled="!hasSelected"
          :loading="loading"
        >重新加载</a-button>

        <a-button class="editable-add-btn" @click="handleAdd">新增</a-button>

        <a-popconfirm placement="top" okText="确认" cancelText="取消" @confirm="handleDelete">
          <template slot="title">
            <p>确认要删除所选项吗</p>
          </template>
          <a-button type="primary" :disable="hasSelected">删除</a-button>
        </a-popconfirm>

        <a-popconfirm placement="top" okText="确认" cancelText="取消" @confirm="handleSubmit">
          <template slot="title">
            <p>确认要提交吗</p>
          </template>
          <a-button type="primary" @click="handleSubmit">提交</a-button>
        </a-popconfirm>

        <span style="margin-left:8px">
          <template v-if="hasSelected">{{`Selected ${selectedRowKeys.length} items`}}</template>
        </span>
      </div>

      <!--表格-->
      <a-table
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        bordered
        :dataSource="data"
        :columns="columns"
      >
        <template slot="name" slot-scope="text, record">
          <editable-cell :text="text" @change="onCellChange(record.key, 'name', $event)" />
        </template>

        <template slot="start" slot-scope="text, record">
          <editable-cell :text="text" @change="onCellChange(record.key,'start',$event)" />
        </template>

        <span slot="timeMargin" slot-scope="text">
          <a-select defaultValue="lucy" style="width: 120px" @change="handleMarginChange">
            <a-select-option value="1">一天</a-select-option>
            <a-select-option value="2">两天</a-select-option>
            <a-select-option value="3">三天</a-select-option>+
          </a-select>
        </span>

        <span slot="script" slot-scope="text, filelist">
          <a-select defaultValue="lucy" style="width: 120px" @change="handleScriptChange">
            <a-select-option v-for:>一天</a-select-option>
          </a-select>
        </span>

        <span slot="status" slot-scope="text,record">
          <span v-if="record.status== 1">已启用</span>
          <span v-else-if="record.status== 0">未启用</span>
          <span v-else>状态获取错误</span>
        </span>
      </a-table>
    </div>
  </div>
</template>

<script src='./ScriptSetting.js'></script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="less">
@import "./ScriptSetting.less";
</style>
