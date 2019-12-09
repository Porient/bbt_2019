<template>
  <div class="container">
    <a-card title="我的收藏" :bordered="false">
      <ProductPreview
        v-for="item in collectionList"
        :key="item.key"
        :src="item.src"
        :title="item.title"
        :imgSrc="item.imgSrc"
        :tags="item.tags"
        class="list-item"
      />
      <div class="pagination">
        <a-pagination :total="50" showSizeChanger showQuickJumper />
      </div>
    </a-card>
  </div>
</template>

<script>
import Vue from "vue";
import { Card, Pagination } from "ant-design-vue";
import ProductPreview from "@/components/ProductPreview/ProductPreview.vue";

Vue.use(Card);
Vue.use(Pagination);

export default {
  name: "MyCollection",
  data() {
    return {
      collectionList: [
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
    };
  },
  components: {
    ProductPreview,
  },
};
</script>

<style lang="less" scoped>
.container {
  text-align: left;
  .list-item {
    margin-left: 50px;
  }
  .pagination {
    margin-top: 24px;
    float: right;
  }
}
</style>
