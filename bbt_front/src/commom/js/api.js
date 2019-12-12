// 存放所有的接口

import request from "./request.js";

//登录注册API
export function userLogin(params) {
  //用户登录
  return request({
    url: "/user/login.do",
    method: "POST",
    params,
  });
}

export function adminLogin(params) {
  //管理员登录
  return request({
    url: "/admin/login.do",
    method: "POST",
    params,
  });
}

export function userLogout(params) {
  //用户注销
  return request({
    url: "/user/logout.do",
    method: "POST",
    params,
  });
}

export function adminLogout(params) {
  //管理员注销
  return request({
    url: "/admin/logout.do",
    method: "POST",
    params,
  });
}

export function register(params) {
  //用户注册
  return request({
    url: "/user/register.do",
    method: "POST",
    params,
  });
}


export function registerCode(params) {
  //注册验证码
  return request({
    url: "/user/code.do",
    method: "POST",
    params,
  });
}

// 用户个人中心API
export function userCollectPic(params) {
  //用户收藏画像
  return request({
    url: "/user/getCollectPic.do",
    method: "POST",
    params,
  });
}

export function userUpdate(params) {
  //更新个人信息
  return request({
    url: "/user/update.do",
    method: "POST",
    params,
  });
}

export function userRecordPic(params) {
  //个人收藏画像
  return request({
    url: "/user/getRecordPic.do",
    method: "POST",
    params,
  });
}

export function userCommentPic(params) {
  //个人评论画像
  return request({
    url: "/user/getCommentPic.do",
    method: "POST",
    params,
  });
}

//评论API
export function commentLike(params) {
  //点赞评论
  return request({
    url: "/comment/likeComment.do",
    method: "POST",
    params,
  });
}

export function commentDelete(params) {
  //删除评论
  return request({
    url: "/comment/delete.do",
    method: "POST",
    params,
  });
}

export function commentUpdate(params) {
  //更新评论
  return request({
    url: "/comment/update.do",
    method: "POST",
    params,
  });
}

export function commentAdd(params) {
  //添加评论
  return request({
    url: "/comment/add.do",
    method: "POST",
    params,
  });
}

export function commentList(params) {
  //获取评论列表
  return request({
    url: "/comment/list.do",
    method: "POST",
    params,
  });
}

//浏览记录
export function recordAdd(params) {
  //增加浏览记录
  return request({
    url: "/record/add.do",
    method: "POST",
    params,
  });
}

export function recordDelete(params) {
  //删除浏览记录
  return request({
    url: "/record/delete.do",
    method: "POST",
    params,
  });
}

export function recordList(params) {
  //获取浏览记录列表
  return request({
    url: "/record/list.do",
    method: "POST",
    params,
  });
}

//收藏API
export function collectDelete(params) {
  //删除收藏
  return request({
    url: "/collect/delete.do",
    method: "POST",
    params,
  });
} 

export function collectAdd(params) {
  //添加收藏
  return request({
    url: "/collect/add.do",
    method: "POST",
    params,
  });
}

export function collectList(params) {
  //收藏列表
  return request({
    url: "/collect/list.do",
    method: "POST",
    params,
  });
}

//产品API
export function getBasicInfo(params) {
  //获取产品基本信息
  return request({
    url: "/product/getBasicInfo.do",
    method: "POST",
    params,
  });
}

export function getCommentInfo(params) {
  //获取产品评论信息
  return request({
    url: "/product/getCommentInfo.do",
    method: "POST",
    params,
  });
}

export function getStatisticInfo(params) {
  //获取产品统计信息
  return request({
    url: "/product/getStatisticInfo.do",
    method: "POST",
    params,
  });
}

export function getCompareInfo(params) {
  //获取产品比较信息
  return request({
    url: "/product/getCompareInfo.do",
    method: "POST",
    params,
  });
}

export function getMiningInfo(params) {
  //获取产品挖掘信息
  return request({
    url: "/product/getMiningInfo.do",
    method: "POST",
    params,
  });
}

export function productRecommend(params) {
  //获取推荐产品
  return request({
    url: "/product/recommendProduct.do",
    method: "POST",
    params,
  });
}

export function productList(params) {
  //获得产品列表
  return request({
    url: "/product/list.do",
    method: "POST",
    params,
  });
}

export function productDelete(params) {
  //删除产品
  return request({
    url: "/product/delete.do",
    method: "POST",
    params,
  });
}

// export function productDetail(params) {
//   //获取产品详情,数据库中未处理信息
//   return request({
//     url: "/product/detail",
//     method: "POST",
//     params,
//   });
// }

export function productLike(params) {
  //点赞产品
  return request({
    url: "/product/likeProduct.do",
    method: "POST",
    params,
  });
}

export function productSearch(params) {
  //搜索产品
  return request({
    url: "/product/searchProduct.do",
    method: "POST",
    params,
  });
}

export function productChange(params) {
  //产品上下架
  return request({
    url: "/product/changeState.do",
    method: "POST",
    params,
  });
}

// export function productHot(params) {
//   //获取热门产品
//   return request({
//     url: "/product/hotProduct",
//     method: "POST",
//     params,
//   });
// }

