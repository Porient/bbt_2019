<template>
  <span class="card" @click="showDetail">
    <img :src="imgSrc" class="card-img" />
    <span class="card-description">
      <div class="card-title">{{title}}</div>
      <div class="card-tags">
        <a-tag :color="tag.color" v-for="tag in tags" :key="tag.name">{{tag.name}}</a-tag>
      </div>
      <a-progress :percent="80" size="small" class="card-progress" :showInfo="false" />
    </span>
  </span>
</template>

<script src="./ProductPreview.js" >
</script>

<style scoped lang="less">
@import "./ProductPreview.less";
</style>