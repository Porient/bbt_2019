import Vue from "vue";
import { Table, Tag, Button, Popconfirm, Alert, message } from "ant-design-vue";

import BackManageForm from "@/components/BackManageForm/BackManageForm.vue"

Vue.use(Table);
Vue.use(Tag);
Vue.use(Button);
Vue.use(Popconfirm);
Vue.use(Alert);
Vue.use(message);

const columns = [
  {
    title:"品牌",
    dataIndex:"brand",
  },
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
    width: '10%',
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
    title: "价格",
    dataIndex: "price",
    key: "price",
    width: '13%',
    scopedSlots: { customRender: "price" },
    sorter: (a, b) => a.price - b.price,
  },
  {
    title: "状态",
    dataIndex: "status",
    key: "status",
    width: '13%',
    scopedSlots: { customRender: "status" },
  },
  {
    title: "修改时间",
    dataIndex: "fixtime",
    key: "fixtime",
    scopedSlots: { customRender: "fixtime" },
    // filters: [
    //   {
    //     text: "未审核",
    //     value: 0,
    //   },
    //   {
    //     text: "审核通过",
    //     value: 1,
    //   },
    //   {
    //     text: "审核未通过",
    //     value: -1,
    //   },
    // ],
  },
  {
    title: "操作",
    dataIndex: "action",
    key: "x",
    scopedSlots: { customRender: "action" },
  },
];


export default {
  name: "Product",
  data() {
    return {
      datalist:[
        {
          key: "0",
          brand:"华为",
          name: "Mate 30 pro",
          type: "手机",
          price: 50000.00,
          status: 0,
          fixtime:"2019-01-01",
          id:12345,
        },
        {
          key: "1",
          brand:"华为",
          name: "Mate 30 pro",
          type: "手机",
          price: 50000.00,
          status: 1,
          fixtime:"2019-01-01",
          id:23456,
        },
        {
          key: "2",
          brand:"华为",
          name: "Mate 30 pro",
          type: "手机",
          price: 50000.00,
          status: 0,
          fixtime:"2019-01-01",
          id:34567,
        },
        {
          key: "3",
          brand:"华为",
          name: "MateBook 13",
          type: "电脑",
          price: 53990.00,
          status: 0,
          fixtime:"2019-01-01",
          id:45678,
        }
      ],
      columns,
      searchText: "",
      searchInput: null,
      selectedRowKeys: [],
      loading: false,
      ModalText: 'Content of the modal',
      visible: false,
      confirmLoading: false,
      selectrecord:0,
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
    confirm(e) {
      console.log(e);
      // this.$message.success('Click on Yes');
    },
    cancel(e) {
      console.log(e);
      // this.$message.error('Click on No');
    },

    //弹出编辑表单
    editModal(e) {
      console.log("edit "+e);
      this.visible = true;
      this.selectrecord=e;
    },
    handleOk() {
      this.ModalText = 'The modal will be closed after two seconds';
      this.confirmLoading = true;
      setTimeout(() => {
        this.visible = false;
        this.confirmLoading = false;
      }, 2000);
    },
    handleCancel() {
      console.log('Clicked cancel button');
      this.visible = false;
    },
  },
  components:{
    BackManageForm
  }
};
