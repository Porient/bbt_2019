import Vue from "vue";
import {
  Form,
  Col,
  Row,
  Avatar,
  Input,
  Button,
  Progress,
} from "ant-design-vue";

Vue.use(Form);
Vue.use(Col);
Vue.use(Row);
Vue.use(Avatar);
Vue.use(Input);
Vue.use(Button);
Vue.use(Progress);

export default {
  name: "UserInfo",
  data() {
    return {
      // 表单的标题和内容分别占的比例
      formItemLayout: {
        labelCol: { span: 8 },
        wrapperCol: { span: 16 },
      },
      // 表单的右侧提示内容的布局
      contentSpan: {
        left: 10,
        offset: 1,
      },
      // 密码复杂程度的颜色 success exception active normal
      progressStatus: "exception",
      // 密码复杂程度的百分比
      progressPercent: 0,
      // 密码，用来校验复杂度
      password: "",
      // 密码的最小长度
      minPasswordLen: 6,
    };
  },
  methods: {
    // 处理密码负责程度条的文字和颜色
    progressFormat(percent) {
      if (percent === 0) {
        this.progressStatus = "exception";
        return "无效";
      } else if (percent < 34) {
        this.progressStatus = "exception";
        return "低";
      } else if (percent < 67) {
        this.progressStatus = "normal";
        return "中";
      } else {
        this.progressStatus = "success";
        return "高";
      }
    },
    valuatePassword() {
      let p = 0;
      const uppercase = this.password.match(/([A-Z])+/);
      const lower = this.password.match(/([a-z])+/);
      const number = this.password.match(/([0-9])+/);
      // 必须满足：大于{{minPasswordLen}}个字符，匹配到数字，匹配到字母（部分大小写）
      if (
        this.password.length >= this.minPasswordLen &&
        number &&
        (uppercase || lower)
      ) {

        // 匹配到大写字母
        if (uppercase) {
          p++;
        }

        // 匹配到小写字母
        if (lower) {
          p++;
        }

        // 匹配到特殊字符
        if (this.password.match(/[^A-Za-z0-9_]+/)) {
          p++;
        }
      }

      this.progressPercent = (p / 3) * 100;
    },
  },
};
