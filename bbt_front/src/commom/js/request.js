import axios from "axios";

const service = axios.create({
  baseURL: process.env.NODE_ENV === "development" ? "http://localhost:9090" : "", // api 的 base_url
  withCredentials: true, // 跨域请求时发送 cookies
  timeout: 30000, // request timeout
  headers: {'content-type': 'application/x-www-form-urlencoded'},
});

// request interceptor
service.interceptors.request.use(
  config => {
    // 发送请求前的处理

    return config;
  },
  error => {
    // 请求错误时的处理
    console.log(error);
    return Promise.reject(error);
  },
);

service.interceptors.response.use(
  response =>{
    // 响应回来的处理
    return response;
  },
  error => {
    // 响应错误的处理
    console.log("response" + error);
    return Promise.reject(error);
  }
)

export default service;
