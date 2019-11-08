import Vue from "vue";
import {Form, Select, Modal, Row, Col } from "ant-design-vue";

Vue.use(Form);
Vue.use(Select);
Vue.use(Modal);
Vue.use(Row);
Vue.use(Col);

export default{
    name:"ComputerForm",
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
            this.$emit("update");
          },
          initialValue(){
            // console.log(this.info),
            this.form.setFieldsValue({
                product_id: this.info.id,
                brand:this.info.brand,
                price:this.info.price,
            })  
          },
    },
}