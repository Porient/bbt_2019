import Vue from "vue";
import { Upload } from "ant-design-vue";
import { Table } from "ant-design-vue";
import { Button } from "ant-design-vue";
import { Popconfirm } from "ant-design-vue";
import { Select } from "ant-design-vue";
import { EditableCell } from "ant-design-vue";
/*
 * EditableCell Code https://github.com/vueComponent/ant-design-vue/blob/master/components/table/demo/EditableCell.vue
 */

Vue.use(Upload);
Vue.use(Table);
Vue.use(Button);
Vue.use(Popconfirm);
Vue.use(Select);

const columns = [
  {
    title: "备注",
    dataIndex: "name",
    key: "name",
    scopedSlots: { customRender: "name" },
  },
  {
    title: "起始地址",
    dataIndex: "start",
    key: "start",
    scopedSlots: { customRender: "start" },
  },
  {
    title: "抓取间隔",
    dataIndex: "timeMargin",
    key: "timeMargin",
    scopedSlots: { customRender: "timeMargin" },
  },
  {
    title: "脚本",
    dataIndex: "script",
    key: "script",
    scopedSlots: { customRender: "timeMargin" },
  },
  {
    title: "状态",
    dataIndex: "status",
    key: "status",
    scopedSlots: { customRender: "status" },
  },
];

const data = [
  {
    name: "京东",
    start: "www.jd.com",
    timeMargin: 1,
    script: "jd.py",
    status: 1,
  },
  {
    name: "公众号",
    start: "www.wechat.com",
    timeMargin: 2,
    script: "wechat.py",
    status: 0,
  },
];

const filelist =[
  {
    name:"jd",
    address:"www.jd.com"
  }
]

export default {
  name: "ScriptSetting",
  data() {
    return {
      data,
      columns,
      selectedRowKeys: [],
      filelist,
    };
  },
  components: {
    EditableCell,
  },
  computed: {
    hasSelected() {
      return this.selectedRowKeys.length > 0;
    },
  },
  methods: {
    handleFileChange(info) {
      const status = info.file.status;
      if (status !== "uploading") {
        console.log(info.file, info.fileList);
      }
      if (status === "done") {
        this.$message.success(`${info.file.name} file uploaded success fully.`);
      } else if (status === "error") {
        this.$message.error(`${info.file.name} file upload failed.`);
      }
    },
    handleMarginChange(value) {
      //时间间隔选择器改变
      console.log(`selected ${value}`);
    },
    handleScriptChange(name) {
      //脚本选择器改变
      console.log(`selected ${name}`);
    },
    onCellChange(key, dataIndex, value) {
      //可编辑部分改变
      const dataSource = [...this.dataSource];
      const target = dataSource.find(item => item.key === key);
      if (target) {
        target[dataIndex] = value;
        this.dataSource = dataSource;
      }
    },
    handleDelete(key) {
      //删除
      const dataSource = [...this.dataSource];
      this.dataSource = dataSource.filter(item => item.key !== key);
    },
    handleAdd() {
      //新增
      const { count, dataSource } = this;
      const newData = {
        key: count,
        name: `Edward King ${count}`,
        age: 32,
        address: `London, Park Lane no. ${count}`,
      };
      this.dataSource = [...dataSource, newData];
      this.count = count + 1;
    },
    handleSubmit() {
      //提交
    },
    handleReload() {
      //重载
      this.loading = true;
      // axios request
      setTimeout(() => {
        this.loading = false;
        this.selectedRowKeys = [];
      }, 1000);
    },
    onSelectChange(selectedRowKeys) {
      console.log("selectedRowKeys changed: ", selectedRowKeys);
      this.selectedRowKeys = selectedRowKeys;
    },
  },
};
