<template>
  <span class="card" @click="showDetail">
    <img :src="imgSrc" class="card-img" />
    <span class="card-description">
      <div class="card-title">{{title}}</div>
      <div class="card-tags">
        <a-tag :color="tag.color" v-for="tag in tags" :key="tag.name">{{tag.name}}</a-tag>
      </div>
      <a-progress :percent="80" size="small" :showInfo="false" />
    </span>
  </span>
</template>

<script>
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
    id: Number,
  },
  methods: {
    showDetail: function() {
      this.$router.push({
        path: "detail",
        query: {
          id: this.id
        }
      })
    },
  },
};
</script>

<style scoped lang="less">
.card {
  display: inline-block;
  border: 1px solid #e8e8e8;
  margin: 6px 6px;
  height: 150px;
  cursor: pointer;
  .card-img {
    vertical-align: middle;
    width: 150px;
    height: 100%;
  }
  .card-description {
    display: inline-block;
    width: 150px;
    margin: 0 12px;
    text-align: left;
    vertical-align: middle;
    .card-title {
      font-weight: 600;
    }
    .card-tags {
      margin: 12px 0;
      .ant-tag {
        margin: 2px 8px 2px 0;
        cursor: inherit;
      }
    }
  }
}
</style>
