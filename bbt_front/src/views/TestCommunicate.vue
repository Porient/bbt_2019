<template>
    <div>
        <button type="primary" htmlType="submit" @click="handleForm">表单提交</button>
        <button type="primary" htmlType="submit" @click="handleJson">Json提交</button>
    </div>
</template>

<script>
import axios from "axios";
export default {
    name:"TestConmunicate",
    data(){
        return{
        };
    },
    methods:{
        /*表单提交，用在登录，方便使用 Spring Security*/
        handleForm:function(){
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
                    tips:"这是表单提交"
                }
            }).then(res=>{
                console.log(res);
            });
        },
        handleJson:function(){
            axios({
                method:"post",
                url:"/testJson",
                data:{
                    tips:"这是Json提交"
                }
            }).then(res=>{
                console.log(res);
            });
        }
    }
};
</script>
