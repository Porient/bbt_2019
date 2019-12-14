<template>
  <div class="box">
    <div class="block">
      <div>登录注册API</div>
      <a-button type="primary" @click="register">用户注册</a-button>
      <a-button type="primary" @click="getCode">注册验证码</a-button>
      <a-button type="primary" @click="userLogin">用户登录</a-button>
      <a-button type="primary" @click="userLogout">用户注销</a-button>
      <a-button type="primary" @click="adminLogin">管理员登录</a-button>
      <a-button type="primary" @click="adminLogout">管理员注销</a-button>
    </div>
    <div class="block">
      <div>评论API</div>
      <a-button type="primary" @click="commentLike">点赞评论</a-button>
      <a-button type="primary" @click="commentDelete">删除评论</a-button>
      <a-button type="primary" @click="commentUpdate">更新评论</a-button>
      <a-button type="primary" @click="commentAdd">添加评论</a-button>
      <a-button type="primary" @click="commentList">评论列表</a-button>
    </div>
    <div class="block">
      <div>用户个人中心API</div>
      <a-button type="primary" @click="userCollectPic"> 获取用户收藏画像</a-button>
      <a-button type="primary" @click="userUpdate">更新个人信息</a-button>
      <a-button type="primary" @click="userRecordPic">获取个人浏览记录画像</a-button>
      <a-button type="primary" @click="userCommentPic">获取个人评论画像</a-button>
    </div>
    <div class="block">
      <div>浏览记录API</div>
      <a-button type="primary" @click="recordAdd">增加浏览记录</a-button>
      <a-button type="primary" @click="recordDelete">删除浏览记录</a-button>
      <a-button type="primary" @click="recordList">获取浏览记录列表</a-button>
    </div>
    <div class="block">
      <div>收藏API</div>
      <a-button type="primary" @click="collectDelete">删除收藏</a-button>
      <a-button type="primary" @click="collectAdd" >添加收藏</a-button>
      <a-button type="primary" @click="collectList">收藏列表</a-button>
    </div>
    <div class="block">
      <div>产品API</div>
      <a-button @click="getMiningInfo">获取MiningInfo</a-button>
      <a-button @click="getCommentInfo">获取CommentInfo</a-button>
      <a-button @click="getCompareInfo">获取CompareInfo</a-button>
      <a-button @click="getStatisticInfo">获取StatisticsInfo</a-button>
      <a-button @click="getBasicInfo">获取基本信息</a-button>
      <a-button @click="productRecommend">获取推荐产品</a-button>
      <a-button type="primary" @click="productList">获取产品列表</a-button>
      <a-button type="primary" @click="productDelete">删除产品</a-button>
      <a-button type="primary" @click="productLike">点赞产品</a-button>
      <a-button type="primary" @click="productSearch">搜索产品</a-button>
      <a-button type="primary" @click="productChange"> 产品上下架</a-button>
    </div>
  </div>
</template>

<script>
import Vue from "vue";
import { Button } from "ant-design-vue";

Vue.use(Button)

export default {
  name: "testCommunicate",
  data() {
    return {};
  },
  components: {
  },
  methods:{
    //登录注册API
    register(){
      //注册
      //请求参数
      var user = {
        userEmail:"1145054472@qq.com",
        nickname:"刘斌",
        password:"123456",
      };
      var verifyCode = "450601";
      //返回参数
      var code = "";
      var msg = "";

      this.$api.register({
        user:user,
        verifyCode:verifyCode,
      }).then(response => {
        if(response.code === 200) {
          console.log(response);
          code = response.code;
          msg = response.msg;
        }
      });
    },
    getCode(){
      //获取验证码
      //请求参数
      var email = "1145054472@qq.com";
      //返回参数
      var code = "";
      var msg = ""
      var verifyCode = "";

      this.$api.registerCode({
        userEmail: email,
      }).then(response => {
        if(response.code === 200) {
          console.log(response);
          code = response.code;
          verifyCode = response.data.verifyCode;
        }
      });

    },
    userLogin(){
      //用户登录
      //请求参数
      var email = "chenjing@yahoo.com";
      var password = "123456";
      //返回参数
      var userId = "";
      var msg = "";
      var code = "";

      this.$api.userLogin({
        userEmail: email,
        password: password,
      }).then(response => {
        if(response.code === 200) {
          console.log(response);
          userId = response.data.userId;
          msg = response.msg;
          code = response.code;
        }
      });
    },
    userLogout(){
      //用户注销
      //请求参数
      var userId = "66";
      //返回参数
      var msg = "";
      var code ="";

      this.$api.userLogout({
        userId: userId
      }).then(response => {
        if(response.code === 200) {
          console.log(response);
          msg = response.msg;
          code = response.code;
        }
      });
    },
    adminLogin(){
      //管理员登录
      //请求参数
      var email = "496612585@qq.com";
      var password = "123456";
      //返回参数
      var code = 200;
      var msg = "登录成功";
      var adminId = 1;

      this.$api.adminLogin({
        adminEmail: email,
        password: password,
      }).then(response => {
        if(response.code === 200) {
          console.log(response);
          adminId = response.dataadminId;
          code = response.code;
          msg = response.msg;
        }
      });
    },
    adminLogout(){
      //管理员注销
      //请求参数
      var adminId = "1";
      //返回参数
      var code = "";
      var msg = "";
      this.$api.adminLogout({
        adminId: adminId
      }).then(response => {
        if(response.code === 200) {
          console.log(response);
          code = response.code;
          msg = response.code;
        }
      });
    },

    //评论API
    commentLike(){
      //点赞评论
      //请求参数
      var userId = "101"; 
      var commentId = "1";  //like_num = 6+1
      //返回参数
      var msg = "";
      var code = "";

      this.$api.commentLike({
        userId: userId,
        commentId: commentId,
      }).then(response => {
        if(response.code === 200) {
          console.log(response);
          msg = response.msg;
          code = response.code
        }
      });
    },
    commentDelete(){
      //删除评论
      //请求参数
      var commentId = "996"
      //返回参数
      var msg = "";
      var code = "";
      this.$api.commentDelete({
        commentId:commentId,
      }).then(response => {
        if(response.code === "200") {
          console.log(response)
          msg = response.msg;
          code = response.code;
        }
      });
    },
    commentUpdate(){
      //更新评论
      //请求参数
      var comment = {
        commentId:"995",
        content:"修改评论内容"
        };
      //返回参数
      var msg = "";
      var code = "";

      this.$api.commentUpdate({
        comment:comment,
      }).then(response => {
        if(response.code === 200) {
          console.log(response)
          msg = response.msg;
          code = response.code;
        }
      });
    },
    commentAdd(){
      //添加评论
      //请求参数
      var comment = {
        userId:"101",
        //
        productId:"1",
        productType:"0",
        productName:"T91",
        content:"1",
      };
      //返回参数
      var msg = "";
      var code ="";

      this.$api.commentAdd({
        comment:comment
      }).then(response => {
        if(response.code === 200) {
          console.log(response)
          msg = response.msg;
          code = response.code;
        }
      });
    },
    commentList(){
      //获取评论列表
      //请求参数
      var userId ="1";
      var pageNo = "1";
      var pageSize = "20";
      //返回参数
      var pageInfoResult ={
        obj: {},
        total: "",
        pageNum: "",
        pageSize: "",
      };
      var code = "";
      var msg = "";

      this.$api.commentList({
        userId: userId,
        pageNo: pageNo,
        pageSize: pageSize,
      }).then(response => {
        if(response.code === 200) {
          console.log(response)
          pageInfoResult = response.data.pageInfoResult;
          code = response.code;
          msg = response.msg;
        }
      });
    },

    //用户个人中心API
    userCollectPic(){
      //用户收藏画像
      //请求参数
      var userId = "2";
      //返回参数
      var collectPic = {
        collectNum:"",
        rank:"",
        brandMap:{},
        TagMap:{},
      }
      var code = "";
      var msg = "";

      this.$api.userCollectPic({
        userId: userId,
      }).then(response => {
        if(response.code === 200) {
          console.log(response)
          collectPic = response.data.collectPic;
          code = response.code;
          msg = response.msg;
        }
      })
    },
    userUpdate(){
      //更新个人信息
      //请求参数
      var user = {
        userId: "101",
        nickname:"10356",
        phone:"",
        age:"",
        profession:"",
        tag1:"",
        tag2:"",
        tag3:"",
        used:"",
        isBan:"",
      };
      //返回参数
      var msg = "";
      var code = "";

      this.$api.userUpdate({
        user:user
      }).then(response => {
        if(response.code === 200) {
          console.log(response)
          msg = response.msg;
          code = response.code;
        }
      });
    },
    userRecordPic(){
      //个人收藏画像
      //请求参数
      var userId = "1";
      //返回参数
      var recordPic = {
        recordNum:"",
        computerNum:"",
        phoneNum:"",
        recordMap:{},
        timeMap:{},
      }
      var code = "";
      var msg = "";

      this.$api.userRecordPic({
        userId: userId,
      }).then(response => {
        if(response.code === 200) {
          console.log(response)
          recordPic = response.data.recordPic;
          code = response.code;
          msg = response.msg;
        }
      })
    },
    userCommentPic(){
      //个人评论画像
      //请求参数
      var userId = "1";
      //返回参数
      var commentPic={
        commentNum:"",
        rank:"",
        likeNum1:"",
        likeNum2:"",
        likeNum3:"",
        likeNum4:"",
      };
      var code = "";
      var msg = "";

      this.$api.userCommentPic({
        userId: userId,
      }).then(response => {
        if(response.code === 200) {
          console.log(response)
          commentPic = response.data.commentPic;
          code = response.code;
          msg = response.msg;
        }
      })
    },

    //浏览记录API
    recordAdd(){
      //获取记录详情
      //请求参数
      var recordId = "1";
      //返回参数
      var record ={
        userId:"",
        productId:"",
        productType:"",
        productName:"",
        productPicture:"",
        browseTime:"",
      };
      var code = "";
      var msg = "";

      this.$api.recordAdd({
        recordId : recordId
      }).then(response => {
        if(response.code === 200) {
          console.log(response)
          code = response.code;
          msg = response.msg;
          record = response.data.record;
        }
      });
    },
    recordDelete(){
      //删除浏览记录
      //请求参数
      var recordId = "1000";
      //返回参数
      var msg = "";
      var code = "";

      this.$api.recordDelete({
        recordId:recordId,
      }).then(response => {
        if(response.code === 200) {
          console.log(response)
          code = response.code;
          msg = response.msg;
        }
      });
    },
    recordList(){
      //获取浏览记录列表
      //请求参数
      var userId = "1";
      var pageNo = "1";
      var pageSize = "1";
      //返回参数
      var recordPageInfo = {
        obj:{},
        total:"",
        pageNum:"",
        pageSize:"",
      }
      var code = "";
      var msg = "";

      this.$api.recordList({
        userId: userId,
        pageNo: pageNo,
        pageSize: pageSize,
      }).then(response => {
        if(response.code === 200) {
          console.log(response)
          recordPageInfo = response.data.recordPageInfo;
          code = response.code;
          msg = response.msg;
        }
      });
    },

    //收藏API
    collectDelete(){
      //删除收藏
      //请求参数
      var collectId = "398";
      //返回参数
      var msg = "";
      var code = "";

      this.$api.collectDelete({
        collectId:collectId
      }).then(response => {
        if(response.code === 200) {
          console.log(response)
          code = response.code;
          msg = response.msg;
        }
      });
    },
    collectAdd(){
      //添加收藏
      //请求参数
      var collect = {
         userId : "1",
         productId : "1",
         productType:"0",
      };
    
      //返回参数
      var msg = "";
      var code = "";

      this.$api.collectAdd({
        collect:collect,
      }).then(response => {
        if(response.code === 200) {
          console.log(response)
          code = response.code;
          msg = response.msg;
        }
      });
    },
    collectList(){
      //收藏列表
      //请求参数
      var userId = "1";
      var pageNo = "1";
      var pageSize = "1";
      //返回参数
      var pageInfoResult = {
        obj:{},
        total:"",
        pageNum:"",
        pageSize:"",
      };
      var code = "";
      var msg = "";

      this.$api.collectList({
        userId: userId,
        pageNo: pageNo,
        pageSize: pageSize,
      }).then(response => {
        if(response.code === 200) {
          console.log(response)
          pageInfoResult = response.data.pageInfoResult;
          code = response.code;
          msg = response.msg
        }
      });
    },

    //产品API
    getMiningInfo(){
      //获取挖掘信息
      //请求参数
      var productId = "1";
      //返回参数
      var miningInfo = {
        comment_confidence_list:[{
          user_id:"",
          liked_num:"",
          review_num:"",
          like_num:"",
          collect_num:""
        }],
        profession_counts:[{
          name:"",
          num:"",
        }],
        profession_cloud_path:"",
      };
      var code = "";
      var msg = "";

      this.$api.getMiningInfo({
        productId:productId,
      }).then(response => {
        if(response.code === 200) {
          console.log(response)
          code = response.code;
          msg = response.msg;
          miningInfo = response.data.miningInfo;
        }
      });
    },
    getCommentInfo(){
      //获取评论信息
      //请求参数
      var productId = "";
      //返回参数
      var commentInfo = {
        comment_id:"",
        content:"",
        like_num:"",
        user:{
          id:"",
          nickname:"",
          profession:"",
        },
      };
      var code = "";
      var msg = "";

      this.$api.getCommentInfo({
        productId:productId,
      }).then(response => {
        if(response.code === 200) {
          console.log(response)
          code = response.code;
          msg = response.msg;
          commentInfo = response.data.commentInfo;
        }
      });
    },
    getCompareInfo(){
      //获取比较信息
      //请求参数
      var productId = "";
      //返回参数
      var compareInfo = {
        collect_rank:"",
        browse_rank:"",
        running_memory_rank:"",
        length_rank:"",
        weight_rank:"",
        thick_rank:"",
        memory_rank:"",
        price_rank:"",
      };
      var code = "";
      var msg = "";

      this.$api.getCompareInfo({
        productId:productId,
      }).then(response => {
        if(response.code === 200) {
          console.log(response)
          code = response.code;
          msg = response.msg;
          compareInfo = response.data.compareInfo;
        }
      });
    },
    getStatisticInfo(){
      //获取统计信息
      //请求参数
      var productId = "";
      //返回参数
      var statisticInfo = {
        collect_num : "",
        like_num : "",
        browse_num : "",
        review_num : "",
        daily : [{
          date:"",
          browse_num:"",
          like_num:"",
          review_num:"",
        }],
        comment_wordcloud_path:"",
      };
      var code = "";
      var msg = "";

      this.$api.getStatisticInfo({
        productId:productId,
      }).then(response => {
        if(response.code === 200) {
          console.log(response)
          code = response.code;
          msg = response.msg;
          statisticInfo = response.data.statisticInfo;
        }
      });
    },
    getBasicInfo(){
      //获取基本信息
      //请求参数
      var productId = "";
      //返回参数
      var basicInfo = {
        id:"",
        brand:"",
        name:"",
        performance:{
          cpu_type:"",
          running_memory:"",
          os_type:"",
          os_version:"",
        },
        interfaces:{
          data_interface:"",
          earphone_interface:"",
          nfc_support:"",
        },
        font_camera:{
          circle:"",
          pixel:"",
        },
        rear_camera:{
          circle:"",
          pixel:"",
        },
        phone_feature:"",
        body:{
          color:"",
          length:"",
          weight:"",
          handcraft:"",
          width:"",
          material:"",
          thick:"",
          memory:"",
        },
        communication:{
          memory_card:"",
          card_num:"",
          cpu_type:"",
        },
        endurance:{
          power:"",
          power_capacity:"",
          
        },
        screen:{
          dpi:"",
          screen_type:"",
          propotion:"",
          screen_size:"",
        },
        date:"",
        price:"",
        url:"",
        tag1:"",
        tag2:"",
        tag3:"",
        appearance1:"",
        appearance2:"",
        appearance3:"",
        type:"",
        library:"",
        analysis:"",
      };
      var code = "";
      var msg = "";

      this.$api.getBasicInfo({
        productId:productId,
      }).then(response => {
        if(response.code === 200) {
          console.log(response)
          code = response.code;
          msg = response.msg;
          basicInfo = response.data.basicInfo;
        }
      });
    },
    productRecommend(){
      //获取推荐产品
      //请求参数
      var productId = "";
      //返回参数
      products = {
        top_n_phone_list:{
          like_list:[],
          collection_list:[],
          browse_list:[],
        },
        top_n_computer_list:{
          like_list:[],
          collection_list:[],
          browse_list:[],
        },
      }
      var code = "";
      var msg = "";

      this.$api.productRecommend({
        productId:productId,
      }).then(response => {
        if(response.code === 200) {
          console.log(response)
          code = response.code;
          msg = response.msg;
          products = response.data.products;
        }
      });
    },
    productList(){
      //获取产品列表
      //请求参数
      var productType="1";
      var library="1";
      var pageNum="1";
      var pageSize="1";
      //返回参数
      var pageInfoResult = {
        obj:{},
        total:"",
        pageNum:"",
        pageSize:"",
      }
      
      var code = "";
      var msg = "";

      this.$api.productList({
        productType:productType,
        library:library,
        pageNum:pageNum,
        pageSize:pageSize,
      }).then(response => {
        if(response.code === 200) {
          console.log(response)
          code = response.code;
          msg = response.msg;
          pageInfoResult = response.data.pageInfoResult;
        }
      });
    },
    productDelete(){
      //删除产品
      //请求参数
      var deleteObject= {
        "likeObject": [
            {
                "type": "0",
                "productId": "219"
            },
            {
                "type": "1",
                "productId": "220"
            }
        ]
      }
      //返回参数
      var msg = "";
      var code = "";

      this.$api.productDelete(deleteObject).then(response => {
        if(response.code === 200) {
          console.log(response)
          code = response.code;
          msg = response.msg;
        }
      });
    },
    productLike(){
      //点赞产品
      //请求参数
      var likeObject= {
        type:"0",
        productId:"1",
        productId:"1"
      };
      var userId = "";
      //返回参数
      var msg ="";
      var code = "";

      this.$api.productLike({
        userId:userId,
        likeObject:likeObject,
      }).then(response => {
        if(response.code === 200) {
          console.log(response)
          code = response.code;
          msg = response.msg;
        }
      });
    },
    productSearch(){
      //搜索产品
      //请求参数
      var searchObject= {
        type:"0",
        searchStr:"华为"
      };
      var pageNum = "1";
      var pageSize = "1";

      //返回参数
      var SearchResult = {
        words:["",""],
        products:{
          product:"",
          suitability:"",
        },
        total:"",
        pageNum:"",
        pageSize:"",
      };

      this.$api.productSearch({
        searchObject:searchObject,
        pageNum:pageNum,
        pageSize:pageSize,
      }).then(response => {
        if(response.code === 200) {
          console.log(response)
          code = response.code;
          msg = response.msg;
          searchTokens = response.data.searchTokens;
          products = response.data.products;
        }
      });
    },
    productChange(){
      //产品上下架
      //请求参数
      var productObject = {
        productType:"0",
        productState:"0",
        productId:"1"
      };
      
      //返回参数
      var msg = "";
      var code = "";

      this.$api.productChange({
        productObject:productObject,
      }).then(response => {
        if(response.code === 200) {
          console.log(response)
          code = response.code;
          msg = response.msg;
        }
      });
    },
  }
};
</script>

<style>
.block{
  margin-top: 20px;
  padding: 10px;
}
</style>
