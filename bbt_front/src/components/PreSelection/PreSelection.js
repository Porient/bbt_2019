import Vue from "vue";
import { Table, Tag, Button, Popconfirm,Alert, Modal, Form, Input, Select, Tabs } from "ant-design-vue";

import PhoneForm from "@/components/PhoneForm/PhoneForm.vue"
import ComputerForm from "@/components/ComputerForm/ComputerForm.vue"

Vue.use(Table);
Vue.use(Tag);
Vue.use(Button);
Vue.use(Popconfirm);
Vue.use(Alert);
Vue.use(Form);
Vue.use(Modal);
Vue.use(Input);
Vue.use(Select);
Vue.use(Tabs);

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
    title: "价格",
    dataIndex: "price",
    key: "price",
    width: '13%',
    scopedSlots: { customRender: "price" },
    sorter: (a, b) => a.price - b.price,
  },
  {
    title: "创建时间",
    dataIndex: "settime",
    key: "settime",
    scopedSlots: { customRender: "settime" },
  },
  {
    title: "修改时间",
    dataIndex: "fixtime",
    key: "fixtime",
    scopedSlots: { customRender: "fixtime" },
  },
  {
    title: "操作",
    dataIndex: "action",
    key: "x",
    scopedSlots: { customRender: "action" },
  },
];

export default {
  name: "PreSelection",
  data() {
    return {
      datalist1:[
        {
          key: "0",
          brand:"华为",
          name: "Mate 30 pro",
          type: "手机",
          price: 50000.00,
          settime: "2019-01-01",
          fixtime:"2019-01-01",
          status: 0,
          verified:0,//审核
          id:12345,
        },
        {
          key: "1",
          brand:"华为",
          name: "Mate 30 pro",
          type: "手机",
          price: 50000.00,
          settime: "2019-01-01",
          fixtime:"2019-01-01",
          status: 0,
          verified:0,//审核
          id:23456,
        },
        {
          key: "2",
          brand:"华为",
          name: "Mate 30 pro",
          type: "手机",
          price: 50000.00,
          settime: "2019-01-01",
          fixtime:"2019-01-01",
          status: 0,
          verified:0,//审核
          id:34567,
        }
      ],
      datalist2:[
        {
          key: "0",
          brand:"华为",
          name: "MateBook 13",
          type: "电脑",
          price: 53990.00,
          settime: "2019-01-01",
          fixtime:"2019-01-01",
          status: 0,
          verified:0,//审核
          id:45678,
        }
      ],
      datalist: [],
      columns,
      searchText: "",
      searchInput: null,
      selectedRowKeys: [],
      selectedRowKeys_phone:[],
      selectedRowKeys_computer:[],
      produce_loading: false,
      delete_loading:false,
      visible: false,
      confirmLoading: false,
      selectrecord:0,
      type:"1"
    };
  },
  computed: {
    hasSelected(){
      if(this.type==="1")
      {
        return this.selectedRowKeys_phone.length > 0;
      }
      if(this.type==="2")
      {
        return this.selectedRowKeys_computer.length > 0;
      }
    }
  },
  created(){
    this.datalist=this.datalist1,
    this.type="1"
  },
  methods: {
    callback(key) {
      if(key==="1")
      {
        this.datalist=this.datalist1,
        this.type="1"
      }
      if(key==="2")
      {
        this.datalist=this.datalist2,
        this.type="2"
      }
    },
    timeToMs(str) {
      //将时间字符串转化为毫秒
      return str - "0";
    },
    sortByTime(a, b) {
      //a,b为两个时间字符串
      return this.timeToMs(a) - this.timeToMs(b);
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
      if(this.type==="1")
      {
        this.selectedRowKeys_phone = selectedRowKeys;
      }
      if(this.type==="2")
      {
        this.selectedRowKeys_computer = selectedRowKeys;
      }
    },
    batchDelete() {
      //批量删除
      // const datalist=[...this.datalist];
      this.delete_loading=true;
      setTimeout(() => {
        this.delete_loading = false;
        if(this.type==="1")
        {
          for(var i=0;i<this.selectedRowKeys_phone.length;++i)
          {
            this.datalist=this.datalist.filter(item=>item.key!==this.selectedRowKeys_phone[i]);
            this.datalist1=this.datalist;
            console.log(this.datalist)
          }
          this.selectedRowKeys_phone=[]
        }
        if(this.type==="2")
        {
          for(var j=0;j<this.selectedRowKeys_computer.length;++j)
          {
            this.datalist=this.datalist.filter(item=>item.key!==this.selectedRowKeys_computer[j]);
            this.datalist2=this.datalist;
          }
          this.selectedRowKeys_computer=[]
        }
      }, 1000);
    },
    onDelete(key) {
      //删除选中项
      const datalist=[...this.datalist];
      this.datalist=datalist.filter(item=>item.key !== key);
      if(this.type==="1")
      {
        this.datalist1=this.datalist
      }
      if(this.type==="2")
      {
        this.datalist2=this.datalist
      }
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
      for(var i=0;i<this.datalist.length;++i)
      {
        if(this.datalist[i].key===e)
        {
          this.selectrecord=i;
        }
      }
      // this.selectrecord=e;
    },
    handleOk() {
      this.confirmLoading = true;
      setTimeout(() => {
        this.visible = false;
        this.confirmLoading = false;
      }, 2000);
    },
    handleCancel() {
      this.visible = false;
    },
    receive(){
      this.visible=false;
    }
  },
  components:{
    PhoneForm,
    ComputerForm,
  }
};
