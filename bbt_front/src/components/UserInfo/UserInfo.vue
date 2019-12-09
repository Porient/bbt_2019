<template>
  <div class="user-info-card">
    <a-card title="基本信息" :bordered="false">
      <a-form layout="horizontal" :form="form" :hideRequiredMark="true">
        <a-row>
          <a-col :span="contentSpan.left">
            <a-form-item
              label="修改头像"
              :label-col="formItemLayout.labelCol"
              :wrapper-col="formItemLayout.wrapperCol"
            >
              <a-upload name="avatar">
                <a-avatar icon="user" :size="80" />
              </a-upload>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="contentSpan.left">
            <a-form-item
              label="昵称"
              :label-col="formItemLayout.labelCol"
              :wrapper-col="formItemLayout.wrapperCol"
            >
              <a-input
                v-decorator="['username',{ rules:[{required:true, message:'请输入你的昵称' } ] } ]"
                placeholder="请输入昵称"
              />
            </a-form-item>
          </a-col>
          <a-col
            :span=" 24 - contentSpan.left - contentSpan.offset - 2 "
            :offset="contentSpan.offset"
            class="form-content-right"
          >
            <span class="text">4到20个字符，不包含特殊字符</span>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="contentSpan.left">
            <a-form-item
              label="邮箱"
              :label-col="formItemLayout.labelCol"
              :wrapper-col="formItemLayout.wrapperCol"
            >
              <a-input
                placeholder="请输入邮箱"
                v-decorator="['email',{ rules:[{required:true, message:'请输入你的邮箱地址' } ] } ]"
              />
            </a-form-item>
          </a-col>
          <a-col :span="2" :offset="contentSpan.offset" class="form-content-right">
            <a-button>验证</a-button>
          </a-col>
          <a-col :span="8" :offset="contentSpan.offset" class="form-content-right">
            <span class="text">更换邮箱后需要验证才能保存修改</span>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="contentSpan.left">
            <a-form-item
              label="密码"
              :label-col="formItemLayout.labelCol"
              :wrapper-col="formItemLayout.wrapperCol"
            >
              <a-input-password
                placeholder="请输入密码"
                @change="valuatePassword"
                v-model="password"
                v-decorator="['password',{rules:[{required: true, message: '请输入你的密码'}]}]"
              />
            </a-form-item>
          </a-col>
          <a-col :span="3" :offset="contentSpan.offset" class="form-content-right">
            <a-progress
              :strokeWidth="8"
              :percent="progressPercent"
              :status="progressStatus"
              :format="progressFormat"
            />
          </a-col>
          <a-col :span="8" :offset="contentSpan.offset" class="form-content-right">
            <span class="text">6到20个字符，必须包含数字和字母</span>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="contentSpan.left">
            <a-form-item
              label="二次密码"
              :label-col="formItemLayout.labelCol"
              :wrapper-col="formItemLayout.wrapperCol"
            >
              <a-input-password
                placeholder="请再次输入密码"
                v-decorator="['passwordAgain',{rules:[{required: true, message: '请输入你的密码'}]}]"
              />
            </a-form-item>
          </a-col>
          <a-col :span="8" :offset="contentSpan.offset" class="form-content-right">
            <span class="text">与上方密码相同</span>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="contentSpan.left">
            <a-form-item
              label=" "
              :colon="false"
              :label-col="formItemLayout.labelCol"
              :wrapper-col="formItemLayout.wrapperCol"
            >
              <a-button type="primary">确认修改</a-button>
            </a-form-item>
          </a-col>
        </a-row>
      </a-form>
    </a-card>
  </div>
</template>

<script>
import Vue from "vue";
import {
  Form,
  Col,
  Row,
  Avatar,
  Input,
  Button,
  Progress,
  Upload
} from "ant-design-vue";

Vue.use(Form);
Vue.use(Col);
Vue.use(Row);
Vue.use(Avatar);
Vue.use(Input);
Vue.use(Button);
Vue.use(Progress);
Vue.use(Upload);

export default {
  name: "UserInfo",
  data() {
    return {
      form: this.$form.createForm(this, { name: "userInfoForm" }),
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
</script>

<style lang='less' scoped>
.user-info-card {
  text-align: left;
}

.form-content-right {
  line-height: 40px;
  .text {
    color: black;
    opacity: 0.45;
  }
}

</style>
