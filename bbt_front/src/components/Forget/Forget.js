import Vue from "vue";
import { Form } from "ant-design-vue";
Vue.use(Form);
import axios from "axios";

export default {
    beforeCreate () {
      this.form = this.$form.createForm(this);
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
                    code:values.code,
                    newpassword:values.newpsd,
                    renewpassword:values.renewpsd
                }
              }).then(res=>{
                  console.log(res);
              });  
            console.log('Received values of form: ', values);
          }
        });
      },
      sendCode:function(){

      }
    },
  };