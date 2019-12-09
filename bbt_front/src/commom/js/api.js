// 存放所有的接口

import request from "./request.js";

export function userLogin(params) {
  return request({
    url: "/user/login",
    method: "POST",
    data: params,
  });
}

export function adminLogin(params) {
  return request({
    url: "/admin/login",
    method: "POST",
    data: params,
  });
}
