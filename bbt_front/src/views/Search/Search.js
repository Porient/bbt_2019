import Vue from "vue";
import { Layout, Avatar, Input, Select, Card } from "ant-design-vue";
import ProductPreview from "@/components/ProductPreview/ProductPreview.vue";
import { mapGetters } from "vuex";

Vue.use(Layout);
Vue.use(Avatar);
Vue.use(Input);
Vue.use(Select);
Vue.use(Card);

export default {
  name: "Search",
  data() {
    return {
      initial: true,
    };
  },
  components: {
    ProductPreview,
  },
  methods: {
    // 点击搜索按钮
    search() {
      this.initial = false;
    },
    // 随机一个属性的标签
    randomColor() {
      const tagsColor = this.$store.state.tagsColor;
      return tagsColor[Math.floor(Math.random() * 6)];
    },
  },
  computed: {
    ...mapGetters({
      recommendItems: "search/getRecommendItems",
      relatedTags: "search/getRelatedTags",
    }),
  },
};
