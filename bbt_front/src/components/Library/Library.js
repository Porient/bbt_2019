import Vue from "vue";
import { Table, Tag, Button, Popconfirm } from "ant-design-vue";

Vue.use(Table);
Vue.use(Tag);
Vue.use(Button);
Vue.use(Popconfirm);

const columns = [
  {
    title: "产品名称",
    dataIndex: "name",
    key: "name",
    scopedSlots: {
      filterDropdown: "filterDropdown",
      filterIcon: "filterIcon",
      customRender: "customRender",
    },
    onFilter: (value, record) =>
      record.name.toLoweCase().includes(value.toLowerCase()),
    onFilterDropdownVisibleChange: visible => {
      if (visible) {
        setTimeout(() => {
          this.searchInput.focus();
        }, 0);
      }
    },
  },
  {
    title: "类型",
    dataIndex: "type",
    key: "type",
    scopedSlots: { customRender: "type" },
    filters: [
      {
        text: "手机",
        value: "手机",
      },
      {
        text: "电脑",
        value: "电脑",
      },
    ],
    onFilter: (value, record) => record.type.indexOf(value) === 0,
  },
  {
    title: "标签示例",
    dataIndex: "tags",
    key: "tags",
    scopedSlots: { customRender: "tags" },
  },
  {
    title: "创建时间",
    dataIndex: "time",
    key: "time",
    scopedSlots: { customRender: "time" },
    sorter: "sortByTime",
  },
  {
    title: "状态",
    dataIndex: "status",
    key: "status",
    scopedSlots: { customRender: "status" },
    filters: [
      {
        text: "未审核",
        value: 0,
      },
      {
        text: "审核通过",
        value: 1,
      },
      {
        text: "审核未通过",
        value: -1,
      },
    ],
  },
  {
    title: "操作",
    dataIndex: "action",
    key: "x",
    scopedSlots: { customRender: "action" },
  },
];

const data = [
  {
    key: "1",
    name: "Hua Wei Mate 30 pro",
    type: "手机",
    tags: ["曲面屏", "四摄", "双快充"],
    time: "2019-10-5",
    status: 1,
  },
  {
    key: "2",
    name: "Hua Wei Mate 30 pro",
    type: "手机",
    tags: ["曲面屏", "四摄", "双快充"],
    time: "2019-10-6",
    status: 0,
  },
  {
    key: "3",
    name: "Hua Wei Mate 30 pro",
    type: "手机",
    tags: ["曲面屏", "四摄", "双快充"],
    time: "2019-10-7",
    status: -1,
  },
];

export default {
  name: "Library",
  data() {
    return {
      data,
      columns,
      searchText: "",
      searchInput: null,
      selectedRowKeys: [],
      loading: false,
    };
  },
  computed: {
    hasSelected() {
      return this.selectedRowKeys.length > 0;
    },
  },
  methods: {
    timeToMs(str) {
      //将时间字符串转化为毫秒
      return str - "0";
    },
    sortByTime(a, b) {
      //a,b为两个时间字符串
      return this.timeToMs(a) - this.timeToMs(b);
    },
    handleSearch(selectedKeys, confirm) {
      //搜索
      confirm();
      this.searchText = selectedKeys[0];
    },
    handleReset(clearFilters) {
      //重置筛选状态
      clearFilters();
      this.searchText = "";
    },
    handleReload() {
      //重载
      this.loading = true;
      //axios request
      setTimeout(() => {
        this.loading = false;
        this.selectedRowKeys = [];
      }, 1000);
    },
    onSelectChange(selectedRowKeys) {
      console.log("selectedRowKeys change: ", selectedRowKeys);
      this.selectedRowKeys = selectedRowKeys;
    },
    handleDelete() {
      //删除选中项
    },
  },
};
