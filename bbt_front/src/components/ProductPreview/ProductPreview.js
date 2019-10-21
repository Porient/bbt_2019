import Vue from "vue";
import { Card, Tag, Progress } from "ant-design-vue";

Vue.use(Card);
Vue.use(Tag);
Vue.use(Progress);

export default {
  name: "ProductPreview",
  props: {
    imgSrc: String,
    title: String,
    tags: Array,
    src: String,
  },
  methods: {
    showDetail: function() {
      window.open(this.src, "_blank");
    },
  },
};
