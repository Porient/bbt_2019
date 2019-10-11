import Vue from "vue";
import { Form,Input,Icon,Col,Radio } from "ant-design-vue";
Vue.use(Form);
Vue.use(Input);
Vue.use(Icon);
Vue.use(Col);
Vue.use(Radio);
import axios from "axios";

const options = [
    { label: '用户登录', value: 'user' },
    { label: '管理员登录', value: 'administrator' },
  ]

export default {
    name:"Login",
    beforeCreate () {
      this.form = this.$form.createForm(this);
    },
    data(){
        return{
            options,
            value:'用户登录'
        }
    },
    methods: {
      handleSubmit (e) {
        e.preventDefault();
        this.form.validateFields((err, values) => {
          if (!err) {
            axios({
                method:"post",
                url:"/testForm",
                transformRequest:[
                    function(data){
                        let newData ="";
                        for (let k in data){
                            newData += encodeURIComponent(k)+"="+encodeURIComponent(data[k])+"&";
                        }
                        return newData;
                    }
                ],
                data:{
                    mail:values.mail,
                    password:values.password
                }
            }).then(res=>{
                console.log(res);
            });  
            console.log('Received values of form: ', values);
          }
        });
      },
    },
  };