import Vue from "vue";
import { Table, Card, Alert, Button, Divider } from "ant-design-vue";

Vue.use(Table);
Vue.use(Card);
Vue.use(Alert);
Vue.use(Button);
Vue.use(Divider);

export default {
  name: "UserComments",
  data() {
    return {
      columns: [
        {
          title: "评论内容",
          dataIndex: "comment",
        },
        {
          title: "商品名称",
          dataIndex: "productName",
        },
        {
          title: "评论时间",
          dataIndex: "date",
        },
        {
          title: "点赞数",
          dataIndex: "likeCount",
        },
        {
          title: "操作",
          key: "x",
          scopedSlots: { customRender: "action" },
        },
      ],
      dataSource: [
        {
          commentId: "1",
          comment: "asdasdasd",
          productName: "Mate30",
          date: "2019年1月1日",
          likeCount: "15",
        },
        {
          commentId: "2",
          comment: "asdasdasd",
          productName: "Mate30",
          date: "2019年1月1日",
          likeCount: "15",
        },
        {
          commentId: "3",
          comment: "asdasdasd",
          productName: "Mate30",
          date: "2019年1月1日",
          likeCount: "15",
        },
        {
          commentId: "4",
          comment: "asdasdasd",
          productName: "Mate30",
          date: "2019年1月1日",
          likeCount: "15",
        },
        {
          commentId: "5",
          comment: "asdasdasd",
          productName: "Mate30",
          date: "2019年1月1日",
          likeCount: "15",
        },
      ],
      selectedRowKeys: [],
    };
  },
  methods: {
    onSelectedChange(selectedRowKeys) {
      this.selectedRowKeys = selectedRowKeys;
    },
  },
};
