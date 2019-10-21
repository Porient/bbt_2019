<template>
  <div>
    <a-layout class="layout">
      <a-layout-header>
        <div class="nav-right">
          <a-avatar size="small" icon="user" class="nav-avatar" />
          <span class="nav-name">卢越兴</span>
        </div>
      </a-layout-header>
      <a-layout-content class="layout-content">
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
      </a-layout-content>
    </a-layout>
  </div>
</template>

<script src="./Search.js">
</script>

<style scoped lang="less">
@import "./Search.less";
</style>