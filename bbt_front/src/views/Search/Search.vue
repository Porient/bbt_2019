<template>
  <div>
    <a-input-search class="input-search" size="large" @search="search">
      <a-select slot="addonBefore" defaultValue="cellphone">
        <a-select-option value="cellphone">手机</a-select-option>
        <a-select-option value="laptop">笔记本</a-select-option>
      </a-select>
    </a-input-search>
    <a-card
      v-if="initial"
      title="推荐商品"
      class="result"
      :bodyStyle="{ padding: '12px 16px'}"
      :headStyle="{padding: '0', fontWeight: 600}"
    >
      <!-- 循环生成推荐列表 -->
      <ProductPreview
        v-for="item in recommendItems"
        :key="item.key"
        :src="item.src"
        :title="item.title"
        :imgSrc="item.imgSrc"
        :tags="item.tags"
        class="result-item"
      />
    </a-card>
    <template v-else>
      <a-card
        title="搜索建议"
        class="result"
        :bodyStyle="{ padding: '12px 16px'}"
        :headStyle="{padding: '0', fontWeight: 600}"
      >
        <!-- 循环生成搜索建议 -->
        <a-tag
          v-for="tag in relatedTags"
          :key="tag"
          class="result-item"
          :color="randomColor()"
        >{{tag}}</a-tag>
      </a-card>
      <a-card
        title="搜索结果"
        class="result"
        :bodyStyle="{ padding: '12px 16px'}"
        :headStyle="{padding: '0', fontWeight: 600}"
      >
        <!-- 循环生成搜索结果 -->
        <ProductPreview
          v-for="item in recommendItems"
          :key="item.key"
          :src="item.src"
          :title="item.title"
          :imgSrc="item.imgSrc"
          :tags="item.tags"
          class="result-item"
        />
      </a-card>
    </template>
  </div>
</template>

<script>
import Vue from "vue";
import { Input, Select, Card } from "ant-design-vue";
import ProductPreview from "@/components/ProductPreview/ProductPreview.vue";
import { mapGetters } from "vuex";

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
</script>

<style scoped lang="less">
.input-search {
  margin-top: 100px;
  width: 600px;
}

.result {
  margin: 32px auto;
  max-width: 1050px;
  min-width: 350px;
  .result-item {
    float: left;
  }
}
</style>
