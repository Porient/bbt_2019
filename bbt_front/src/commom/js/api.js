// 存放所有的接口

import request from "./request.js";

//登录注册API
export function userLogin(params) {
  //用户登录
  return request({
    url: "/user/login",
    method: "POST",
    data: params,
  });
}

export function adminLogin(params) {
  //管理员登录
  return request({
    url: "/admin/login",
    method: "POST",
    data: params,
  });
}

export function userLogout(params) {
  //用户注销
  return request({
    url: "/user/logout",
    method: "POST",
    data: params,
  });
}

export function adminLogout(params) {
  //管理员注销
  return request({
    url: "/admin/logout",
    method: "POST",
    data: params,
  });
}

export function userRegister(params) {
  //用户注册
  return request({
    url: "/user/register",
    method: "POST",
    data: params,
  });
}

export function adminRegister(params) {
  //管理员注册
  return request({
    url: "/admin/register",
    method: "POST",
    data: params,
  });
}

export function registerCode(params) {
  //注册验证码
  return request({
    url: "/user/code",
    method: "POST",
    data: params,
  });
}

// 用户个人中心API
export function userCollectPic(params) {
  //用户收藏画像
  return request({
    url: "/user/getCollectPic",
    method: "POST",
    data: params,
  });
}

export function userUpdate(params) {
  //更新个人信息
  return request({
    url: "/user/update",
    method: "POST",
    data: params,
  });
}

export function userRecordPic(params) {
  //个人收藏画像
  return request({
    url: "/user/getRecordPic",
    method: "POST",
    data: params,
  });
}

export function userCommentPic(params) {
  //个人评论画像
  return request({
    url: "/user/getCommentPic",
    method: "POST",
    data: params,
  });
}

//评论API
export function commentLike(params) {
  //点赞评论
  return request({
    url: "/comment/likeComment",
    method: "POST",
    data: params,
  });
}

export function commentDelete(params) {
  //删除评论
  return request({
    url: "/comment/delete",
    method: "POST",
    data: params,
  });
}

export function commentUpdata(params) {
  //更新评论
  return request({
    url: "/comment/update",
    method: "POST",
    data: params,
  });
}

export function commentAdd(params) {
  //添加评论
  return request({
    url: "/comment/add",
    method: "POST",
    data: params,
  });
}

export function commentList(params) {
  //获取评论列表
  return request({
    url: "/comment/list",
    method: "POST",
    data: params,
  });
}

//浏览记录
export function recordAdd(params) {
  //增加浏览记录
  return request({
    url: "/record/add",
    method: "POST",
    data: params,
  });
}

export function recordDelete(params) {
  //删除浏览记录
  return request({
    url: "/record/delete",
    method: "POST",
    data: params,
  });
}

export function recordList(params) {
  //获取浏览记录列表
  return request({
    url: "/record/list",
    method: "POST",
    data: params,
  });
}

//收藏API
export function collectDelete(params) {
  //删除收藏
  return request({
    url: "/collect/delete",
    method: "POST",
    data: params,
  });
} 

export function collectAdd(params) {
  //添加收藏
  return request({
    url: "/collect/add",
    method: "POST",
    data: params,
  });
}

export function collectList(params) {
  //收藏列表
  return request({
    url: "/collect/list",
    method: "POST",
    data: params,
  });
}

//产品API
export function productRecommend(params) {
  //获取推荐产品
  return request({
    url: "/product/recommendProduct",
    method: "POST",
    data: params,
  });
}

export function productList(params) {
  //获得产品列表
  return request({
    url: "/product/list",
    method: "POST",
    data: params,
  });
}

export function productDelete(params) {
  //删除产品
  return request({
    url: "/product/delete",
    method: "POST",
    data: params,
  });
}

export function productDetail(params) {
  //获取产品详情
  return request({
    url: "/product/detail",
    method: "POST",
    data: params,
  });
}

export function productLike(params) {
  //点赞产品
  return request({
    url: "/product/likeProduct",
    method: "POST",
    data: params,
  });
}

export function productSearch(params) {
  //搜索产品
  return request({
    url: "/product/searchProduct",
    method: "POST",
    data: params,
  });
}

export function productChange(params) {
  //产品上下架
  return request({
    url: "/product/changeState",
    method: "POST",
    data: params,
  });
}

export function productHot(params) {
  //获取热门产品
  return request({
    url: "/product/hotProduct",
    method: "POST",
    data: params,
  });
}

