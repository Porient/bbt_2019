import Vue from "vue";
import {Form, Select, Modal, Row, Col } from "ant-design-vue";

Vue.use(Form);
Vue.use(Select);
Vue.use(Modal);
Vue.use(Row);
Vue.use(Col);

export default{
    name:"BackManageForm",
    data(){
        return{
            formLayout: 'horizontal',
            form: this.$form.createForm(this, { name: 'coordinated' }),
        };
    },
    props:['info'],
    mounted(){
      this.initialValue(this.record);
    },
    computed: {
        
    },
    methods: {
        //表单内操作
        handleSubmit(e) {
            e.preventDefault();
            this.form.validateFields((err, values) => {
              if (!err) {
                console.log('Received values of form: ', values);
              }
            });
          },
          handleSelectChange(value) {
            console.log(value);
            this.form.setFieldsValue({
              note: `Hi, ${value === 'male' ? 'man' : 'lady'}!`,
            });
          },
          initialValue(){
            console.log(this.info),
            this.form.setFieldsValue({
              id: this.info.id,
              brand:this.info.brand,
              product_name:this.info.name,
              price:this.info.price,
            })
          },
    },
}