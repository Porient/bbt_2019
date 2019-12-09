// 通用的工具函数

// 将登录者的信息存进localStorage
export function setUserInfo(email, password, type) {
  localStorage.setItem(
    "userInfo",
    JSON.stringify({
      email,
      password,
      type,
    }),
  );
}

// 获取localStorage中的登录者的信息
export function getUserInfo() {
  return JSON.parse(localStorage.getItem("userInfo"));
}
