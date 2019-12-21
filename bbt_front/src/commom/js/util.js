// 通用的工具函数

// 将登录者的信息存进localStorage
export function setUserInfo(payload) {
  localStorage.setItem(
    "userInfo",
    JSON.stringify(payload),
  );
}

// 获取localStorage中的登录者的信息
export function getUserInfo() {
  return JSON.parse(localStorage.getItem("userInfo"));
}

export function removeUserInfo() {
  localStorage.removeItem('userInfo')
}
