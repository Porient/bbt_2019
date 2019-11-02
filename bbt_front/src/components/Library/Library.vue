<template>
  <div>
    <!--表格操作：重载、删除-->
    <div style="margin-bottom: 16px; text-align:left">
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
        <template v-if="hasSelected">
          <a-alert :message="`已选择 ${selectedRowKeys.length} 项`" type="success" showIcon />
        </template>
      </span>
    </div>
    <a-table
      :columns="columns"
      :dataSource="data"
      :pagination="{pageSize:50}"
      :rowSelection="{selectedKeys: selectedRowKeys, onChange: onSelectChange}"
    >
      <!--实现搜索-->
      <!-- <div
        slot="filterDropdown"
        slot-scope="{ setSelectedKeys, selectedKeys, confirm, clearFilters, column }"
        class="custom-filter-dropdown"
      >
        <a-input
          v-ant-ref="c => searchInput = c"
          :placeholder="`Search ${column.dataIndex}`"
          :value="selectedKeys[0]"
          @change="e => setSelectedKeys(e.target.value ? [e.target.value] : [])"
          @pressEnter="() => handleSearch(selectedKeys, confirm)"
          style="width: 188px; margin-bottom: 8px; display: block;"
        />
        <a-button
          type="primary"
          @click="() => handleSearch(selectedKeys, confirm)"
          icon="search"
          size="small"
          style="width: 90px; margin-right: 8px"
        >Search</a-button>
        <a-button @click="() => handleReset(clearFilters)" size="small" style="width: 90px">Reset</a-button>
      </div>-->
      <!-- <a-icon
        slot="filterIcon"
        slot-scope="filtered"
        type="search"
        :style="{ color: filtered ? '#108ee9' : undefined }"
      />-->
      <!-- <template slot="customRender" slot-scope="text"> -->
      <!-- <span v-if="searchText">
          <template
            v-for="(fragment, i) in text.toString().split(new RegExp(`(?<=${searchText})|(?=${searchText})`, 'i'))"
          >
            <mark
              v-if="fragment.toLowerCase() === searchText.toLowerCase()"
              :key="i"
              class="highlight"
            >{{fragment}}</mark>
            <template v-else>{{fragment}}</template>
          </template>
      </span>-->
      <!-- <template >{{text}}</template>
      </template>-->
      <!--搜索-->

      <a slot="name" slot-scope="text">{{text}}</a>
      <!-- <span slot="tags" slot-scope="tags">
        <a-tag v-for="tag in tags" color="#2db7f5" :key="tag">{{tag}}</a-tag>
      </span>-->
      <span slot="action" slot-scope="text,record" style>
        <a-button
          class="button"
          v-if="record.status == 0"
          href="javascript:;"
          style="color:rgb(29,165,122);border-color:rgb(29,165,122)"
          size="small"
        >
          <!-- <a-icon type="loading" /> -->
          上架
        </a-button>
        <a-button
          v-if="record.status == 1"
          href="javascript:;"
          class="button"
          style="background-color:#1ABC9C; color:white"
        >查看报告</a-button>
        <a-button
          class="button"
          href="javascript:;"
          style="color:rgb(29,165,122);border-color:rgb(29,165,122)"
          size="small"
        >编辑</a-button>
        <a-popconfirm
          title="你确定要删除吗?"
          @confirm="confirm"
          @cancel="cancel"
          okText="Yes"
          cancelText="No"
        >
          <a-button class="button" type="danger" size="small" ghost>删除</a-button>
        </a-popconfirm>
      </span>
      <span slot="status" slot-scope="text,record">
        <span v-if="record.status== 1">已通过</span>
        <span v-else-if="record.status== 0">未审核</span>
        <span v-else-if="record.status== -1">未通过</span>
        <span v-else>状态获取错误</span>
      </span>

      <!--展开项-->
      <!-- <p slot="expandedRowRender" slot-scope="record" style="margin: 0">{{record.description}}</p> -->
    </a-table>
  </div>
</template>

<script src='./Library.js'></script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="less">
@import "./Library.less";
</style>
