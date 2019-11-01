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

<script src="./UserInfo.js">
</script>

<style lang='less' scoped>
@import "./UserInfo.less";
</style>
