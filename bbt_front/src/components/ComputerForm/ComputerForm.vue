<template>
  <div>
    <a-form :form="form" @submit="handleSubmit" class="form">
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="产品id" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['product_id', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="品牌型号" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['brand', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
      </a-row>

      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="常规性能" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['con_performance', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="游戏性能" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['game_performance', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
      </a-row>

      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="接口" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['interfaces', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="存储" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['storage', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="音效" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['sound', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="摄像头" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['camera', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="规格" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['specification', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="外设" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['peripheral', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="续航" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['endurance', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="网络" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['internet', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="屏幕" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['screen', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="续航" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['endurance', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="其他" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['other', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="价格" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['price', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="京东链接" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['url', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="标签1" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['tag1', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="标签2" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['tag2', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="标签3" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['tag3', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="外观1" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['appearance1', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="外观2" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['appearance2', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="外观3" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['appearance3', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="所属类型" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['product_type', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="所属库" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['library', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="产品分析" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['product_analysis', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
      </a-row>

      <a-form-item :wrapper-col="{ span: 12, offset: 10 }">
        <a-button type="primary" html-type="submit">确认修改</a-button>
      </a-form-item>
    </a-form>
  </div>
</template>


<script src='./ComputerForm.js'></script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="less">
@import "./ComputerForm.less";
</style>