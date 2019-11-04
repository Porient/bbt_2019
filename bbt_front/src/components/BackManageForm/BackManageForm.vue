<template>
  <div>
    <a-form :form="form" @submit="handleSubmit" class="form">
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="产品id" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['id', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="品牌" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['brand', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
      </a-row>

      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="产品名称" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['product_name', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="性能" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['performance', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
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
          <a-form-item label="前置摄像头" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['front-camera', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="后置摄像头" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['rear-camera', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="拍照特点" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['photo_features', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="机身" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['body', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="通信" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['communication', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
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
          <a-form-item label="其他" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['other', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="价格" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['price', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="京东链接" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['url', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="标签1" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['tag1', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="标签2" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['tag2', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="标签3" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['tag3', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="外观1" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['appearance1', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="外观2" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['appearance2', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="外观3" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['appearance3', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="所属类型" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['product_type', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="所属库" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['library', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="产品分析" :label-col="{ span: 8 }" :wrapper-col="{ span: 15 }">
            <a-input
              v-decorator="['product_analysis', { rules: [{ required: false, message: 'Please input your product id!' }] }]"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12"></a-col>
      </a-row>

      <a-form-item :wrapper-col="{ span: 12, offset: 10 }">
        <a-button type="primary" html-type="submit">确认修改</a-button>
      </a-form-item>
    </a-form>
  </div>
</template>


<script src='./BackManageForm.js'></script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="less">
@import "./BackManageForm.less";
</style>