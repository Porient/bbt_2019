import Vue from "vue";
import Vuex from "vuex";
import { getUserInfo, setUserInfo, removeUserInfo } from "./commom/js/util.js";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    isLogin: false, // 判断是否登录，只作为判断localStorage的中介
    tagsColor: ["pink", "red", "orange", "green", "cyan", "blue", "purple"],
    userInfo: {}, // 备份用户信息
  },
  getters: {
    // 返回当前登录的状态
    getLoginState: state => {
      return state.isLogin;
    },
    getUserInfoState: state => {
      return state.userInfo;
    },
  },
  mutations: {
    // 从localStorage检查当前登录的状态
    checkLoginState: state => {
      const info = getUserInfo();
      if (info) {
        state.isLogin = true;
        state.userInfo = { ...info };
      } else {
        state.isLogin = false;
        state.userInfo = {};
      }
    },
    logout: state => {
      //修改登录状态
      if (state.isLogin) {
        state.isLogin = false;
        //删除userInfo
        state.userInfo = {};
        removeUserInfo();
      }
    },
    login: (state, payload) => {
      state.userInfo = { ...payload };
      setUserInfo(payload);
    },
  },
  actions: {},
  modules: {
    // 搜索页面的State
    search: {
      namespaced: true,
      state: {
        // 后台返回的列表数据
        recommendItems: [
          {
            key: 1,
            imgSrc:
              "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1571241611961&di=20e3b2d85a4d70758cb3fb29e59cfd13&imgtype=0&src=http%3A%2F%2Fpics0.baidu.com%2Ffeed%2Fcb8065380cd7912333ed3adbdccfe386b3b78014.jpeg%3Ftoken%3De889267109bbb7074316565e51d6e7a7%26s%3DA0B3CF340BC76F5B1E4764D5030080BA",
            title: "华为P30 pro",
            src: "https://www.baidu.com",
            tags: [
              {
                name: "大电池",
                color: "pink",
              },
              {
                name: "大屏幕",
                color: "red",
              },
              {
                name: "快充",
                color: "green",
              },
              {
                name: "摄像",
                color: "blue",
              },
            ],
          },
          {
            key: 2,
            imgSrc:
              "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1571322781061&di=b52dc190da011ebd00e052b704d51c8b&imgtype=0&src=http%3A%2F%2Finews.gtimg.com%2Fnewsapp_bt%2F0%2F10192848025%2F1000.jpg",
            title: "华为Mate30 pro",
            tags: [
              {
                name: "大电池",
                color: "pink",
              },
              {
                name: "大屏幕",
                color: "red",
              },
              {
                name: "快充",
                color: "green",
              },
              {
                name: "摄像",
                color: "blue",
              },
            ],
          },
          {
            key: 3,
            imgSrc:
              "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1571241611961&di=20e3b2d85a4d70758cb3fb29e59cfd13&imgtype=0&src=http%3A%2F%2Fpics0.baidu.com%2Ffeed%2Fcb8065380cd7912333ed3adbdccfe386b3b78014.jpeg%3Ftoken%3De889267109bbb7074316565e51d6e7a7%26s%3DA0B3CF340BC76F5B1E4764D5030080BA",
            title: "华为P30 pro",
            src: "https://www.baidu.com",
            tags: [
              {
                name: "大电池",
                color: "pink",
              },
              {
                name: "大屏幕",
                color: "red",
              },
              {
                name: "快充",
                color: "green",
              },
              {
                name: "摄像",
                color: "blue",
              },
            ],
          },
          {
            key: 4,
            imgSrc:
              "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1571241611961&di=20e3b2d85a4d70758cb3fb29e59cfd13&imgtype=0&src=http%3A%2F%2Fpics0.baidu.com%2Ffeed%2Fcb8065380cd7912333ed3adbdccfe386b3b78014.jpeg%3Ftoken%3De889267109bbb7074316565e51d6e7a7%26s%3DA0B3CF340BC76F5B1E4764D5030080BA",
            title: "华为P30 pro",
            src: "https://www.baidu.com",
            tags: [
              {
                name: "大电池",
                color: "pink",
              },
              {
                name: "大屏幕",
                color: "red",
              },
              {
                name: "快充",
                color: "green",
              },
              {
                name: "摄像",
                color: "blue",
              },
            ],
          },
        ],
        relatedTags: [
          "大电池",
          "大屏幕",
          "高性能",
          "信号好",
          "分辨率高",
          "有耳机孔",
          "5G",
          "全面屏",
          "刘海屏",
          "快充",
        ],
      },
      getters: {
        getRecommendItems: state => {
          return state.recommendItems;
        },
        getRelatedTags: state => {
          return state.relatedTags;
        },
      },
      mutations: {},
    },
  },
});
