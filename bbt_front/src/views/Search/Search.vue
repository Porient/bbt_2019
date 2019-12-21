<template>
  <div>
    <a-input-search
      class="input-search"
      size="large"
      @search="search"
      v-model="searchStr"
    >
      <a-select slot="addonBefore" defaultValue="0" v-model="searchType">
        <a-select-option value="0">手机</a-select-option>
        <!-- <a-select-option value="1">笔记本</a-select-option> -->
      </a-select>
    </a-input-search>
    <a-card
      v-if="initial"
      title="推荐商品"
      class="result"
      :bodyStyle="{ padding: '12px 16px' }"
      :headStyle="{ padding: '0', fontWeight: 600, backgroundColor: '#fafafa' }"
    >
      <a-spin size="large" v-if="recommendLoading" />
      <a-empty description="暂无数据" v-else-if="recommendList.length === 0" />
      <!-- 循环生成推荐列表 -->
      <ProductPreview
        v-else
        v-for="(item, index) in recommendList"
        :key="index"
        :id="item.id"
        :title="item.name"
        :imgSrc="'http://' + item.appearance1"
        :tags="item.tags"
      />
    </a-card>
    <template v-else>
      <a-card
        title="搜索建议"
        class="result"
        :bodyStyle="{ padding: '12px 16px' }"
        :headStyle="{
          padding: '0',
          fontWeight: 600,
          backgroundColor: '#fafafa',
        }"
      >
        <!-- 循环生成搜索建议 -->
        <a-tag
          v-for="tag in relatedTags"
          :key="tag"
          class="result-item"
          :color="randomColor()"
          @click="searchMore(tag)"
          >{{ tag }}</a-tag
        >
      </a-card>
      <a-card
        title="搜索结果"
        class="result"
        :bodyStyle="{ padding: '12px 16px' }"
        :headStyle="{
          padding: '0',
          fontWeight: 600,
          backgroundColor: '#fafafa',
        }"
      >
        <a-spin size="large" v-if="searchLoading" />
        <a-empty description="暂无数据" v-else-if="resultList.length === 0" />
        <!-- 循环生成搜索结果 -->
        <ProductPreview
          v-else
          v-for="(item, index) in resultList"
          :key="index"
          :id="item.product.productId"
          :title="item.product.productName"
          :imgSrc="'http://' + item.product.appearance1"
          :tags="item.product.tags"
          class="result-item"
        />
      </a-card>
    </template>
  </div>
</template>

<script>
import Vue from "vue";
import { Input, Select, Card, Spin, Empty } from "ant-design-vue";
import ProductPreview from "@/components/ProductPreview/ProductPreview.vue";
import { mapGetters } from "vuex";

Vue.use(Input)
  .use(Select)
  .use(Card)
  .use(Spin)
  .use(Empty);

export default {
  name: "Search",
  data() {
    return {
      initial: true,
      pageInfo: {
        pageNum: 1,
        pageSize: 10,
        total: 0,
      },
      searchStr: "",
      searchType: "0",
      resultList: [], // 搜索结果
      searchLoading: false,
      recommendList: [], // 推荐列表
      recommendLoading: false,
    };
  },
  methods: {
    // 点击搜索按钮
    search() {
      this.initial = false;
      console.log(this.searchStr, this.searchType);
      if (this.searchStr.trim() !== "") {
        this.searchLoading = true;
        this.$api
          .productSearch({
            searchObject: {
              type: this.searchType,
              searchStr: this.searchStr,
            },
            pageNum: this.pageInfo.pageNum,
            pageSize: this.pageInfo.pageSize,
          })
          .then(res => {
            this.pageInfo = {
              total: res.data.total,
              pageSize: res.data.pageSize,
              pageNum: res.data.pageNum,
            };
            this.resultList = res.data.products.map(item => {
              return {
                product: {
                  ...item.product,
                  tags: [
                    {
                      name: item.product.tag1,
                      color: this.randomColor(),
                    },
                    {
                      name: item.product.tag2,
                      color: this.randomColor(),
                    },
                    {
                      name: item.product.tag3,
                      color: this.randomColor(),
                    },
                  ],
                },
                suitability: item.suitability,
              };
            });
            this.searchLoading = false;
          });
      }
    },
    // 随机一个属性的标签
    randomColor() {
      const tagsColor = this.$store.state.tagsColor;
      return tagsColor[Math.floor(Math.random() * 6)];
    },
    searchMore(tag) {
      this.searchStr += tag;
      this.search();
    },
    getRecommendList() {
      this.recommendLoading = true;
      this.$api
        .productRecommend({
          userId: "1",
        })
        .then(res => {
          const {
            like_list,
            collection_list,
            browse_list,
          } = res.data.top_n_phone_list;
          const tempList = [...like_list, ...collection_list, ...browse_list];
          console.log(tempList);
          if (res.code === 200) {
            this.recommendList = tempList.map(item => {
              return {
                ...item,
                tags: [
                  {
                    name: item.tag1,
                    color: this.randomColor(),
                  },
                  {
                    name: item.tag2,
                    color: this.randomColor(),
                  },
                  {
                    name: item.tag3,
                    color: this.randomColor(),
                  },
                ],
              };
            });
          }
        })
        .finally(() => {
          this.recommendLoading = false;
        });
    },
  },
  created() {
    this.getRecommendList();
  },
  computed: {
    ...mapGetters({
      // recommendItems: "search/getRecommendItems",
      relatedTags: "search/getRelatedTags",
    }),
  },
  components: {
    ProductPreview,
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
