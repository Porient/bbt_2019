<template>
  <div class="container">
    <div class="content">
      <div class="mainInfo">
        <img
          class="image"
          alt="商品图片"
          :src="'http://' + basicInfo.appearance1 || basicInfo.appearance2 || basicInfo.appearance3"
        />
        <div class="info">
          <div class="cell">
            <div class="text">品牌：{{ basicInfo.brand }}</div>
            <div class="extra">知名品牌</div>
          </div>
          <div class="cell">
            <div class="text">名称：{{ basicInfo.name }}</div>
            <div class="extra">高端机型</div>
          </div>
          <div class="cell">
            <div class="text">
              CPU：{{
              basicInfo.performance ? basicInfo.performance.cpu_type : ""
              }}
            </div>
            <div class="extra">高性能芯片</div>
          </div>
          <div class="cell">
            <div class="text">
              内存：{{
              basicInfo.performance
              ? basicInfo.performance.running_memory + "G"
              : ""
              }}
            </div>
            <div class="extra">大内存</div>
          </div>
          <div class="cell">
            <div class="text">
              电池：{{
              basicInfo.endurance
              ? basicInfo.endurance.power_capacity + "毫安时"
              : ""
              }}
            </div>
            <div class="extra">大电池</div>
          </div>
          <div class="cell">
            <div class="text">
              屏幕：{{
              basicInfo.screen ? basicInfo.screen.screen_size + "英寸" : ""
              }}
            </div>
            <div class="extra">大屏幕</div>
          </div>
          <div class="cell">
            <div class="text">分辨率：{{ basicInfo.screen ? basicInfo.screen.dpi : "" }}</div>
            <div class="extra">高清屏幕</div>
          </div>
        </div>
      </div>
      <div class="detail">
        <div class="title">详细参数</div>
        <div class="card">
          <div class="subTitle">基本参数</div>
          <div class="cardItem">
            <div class="key">上市时间</div>
            <div class="value">{{ basicInfo.date }}</div>
          </div>
          <div class="cardItem">
            <div class="key">价格</div>
            <div class="value">{{ basicInfo.price }}</div>
          </div>
          <div class="cardItem">
            <div class="key">品牌</div>
            <div class="value">{{ basicInfo.brand }}</div>
          </div>
          <div class="cardItem">
            <div class="key">型号</div>
            <div class="value">{{ basicInfo.name }}</div>
          </div>
          <div class="cardItem">
            <div class="key">机身颜色</div>
            <div class="value">{{ basicInfo.body ? basicInfo.body.color : "" }}</div>
          </div>
          <div class="cardItem">
            <div class="key">操作系统类型</div>
            <div class="value">{{ basicInfo.performance ? basicInfo.performance.os_type : "" }}</div>
          </div>
          <div class="cardItem">
            <div class="key">操作系统版本</div>
            <div class="value">
              {{
              basicInfo.performance ? basicInfo.performance.os_version : ""
              }}
            </div>
          </div>
        </div>
        <div class="card">
          <div class="subTitle">硬件</div>
          <div class="cardItem">
            <div class="key">CPU型号</div>
            <div class="value">{{ basicInfo.performance ? basicInfo.performance.cpu_type : "" }}</div>
          </div>
          <div class="cardItem">
            <div class="key">运行内存</div>
            <div class="value">
              {{
              basicInfo.performance
              ? basicInfo.performance.running_memory + "G"
              : ""
              }}
            </div>
          </div>
          <div class="cardItem">
            <div class="key">机身存储</div>
            <div class="value">{{ basicInfo.body ? basicInfo.body.memory + "G" : "" }}</div>
          </div>
        </div>
        <div class="card">
          <div class="subTitle">屏幕</div>
          <div class="cardItem">
            <div class="key">屏幕尺寸</div>
            <div class="value">
              {{
              basicInfo.screen ? basicInfo.screen.screen_size + "英寸" : ""
              }}
            </div>
          </div>
          <div class="cardItem">
            <div class="key">屏幕比例</div>
            <div class="value">{{ basicInfo.screen ? basicInfo.screen.propotion : "" }}</div>
          </div>
          <div class="cardItem">
            <div class="key">分辨率</div>
            <div class="value">{{ basicInfo.screen ? basicInfo.screen.dpi : "" }}</div>
          </div>
          <div class="cardItem">
            <div class="key">屏幕材质类型</div>
            <div class="value">{{ basicInfo.screen ? basicInfo.screen.screen_type : "" }}</div>
          </div>
        </div>
        <div class="card">
          <div class="subTitle">摄像头</div>
          <div class="cardItem">
            <div class="key">前置摄像头光圈</div>
            <div class="value">{{ basicInfo.front_camera ? basicInfo.front_camera.circle : "" }}</div>
          </div>
          <div class="cardItem">
            <div class="key">前置摄像头像素</div>
            <div class="value">{{ basicInfo.front_camera ? basicInfo.front_camera.pixel : "" }}</div>
          </div>
          <div class="cardItem">
            <div class="key">后置摄像头光圈</div>
            <div class="value">{{ basicInfo.rear_camera ? basicInfo.rear_camera.circle : "" }}</div>
          </div>
          <div class="cardItem">
            <div class="key">后置摄像头像素</div>
            <div class="value">{{ basicInfo.rear_camera ? basicInfo.rear_camera.pixel : "" }}</div>
          </div>
        </div>
      </div>
      <div class="chart">
        <a-card
          size="small"
          title="热度"
          :headStyle="{ fontWeight: '600', backgroundColor: '#fafafa' }"
          :style="{ marginTop: '20px' }"
        >
          <a-spin size="large" v-if="statisticLoading" />
          <div class="chartItem2In1" id="chart1"></div>
        </a-card>
        <!-- <a-card
          size="small"
          title="热度排行"
          :headStyle="{ fontWeight: '600', backgroundColor: '#fafafa' }"
          :style="{ marginTop: '20px' }"
        >
          <a-spin size="large" v-if="compareLoading" />
          <div class="chartItem2In1" id="chart4"></div>
        </a-card>-->
        <a-card
          size="small"
          title="标签"
          :headStyle="{ fontWeight: '600', backgroundColor: '#fafafa' }"
          :style="{ marginTop: '20px' }"
        >
          <a-spin size="large" v-if="compareLoading" />
          <div class="chartItem2In1" id="chart4">
            <div class="tags">
              <a-tag
                v-for="tag in tagList"
                :key="tag"
                class="tag"
                :color="randomColor()"
              >{{ tag }}</a-tag>
            </div>
          </div>
        </a-card>
        <a-card
          size="small"
          title="参数排名"
          :headStyle="{ fontWeight: '600', backgroundColor: '#fafafa' }"
          :style="{ marginTop: '20px' }"
        >
          <a-spin size="large" v-if="compareLoading" />
          <div class="chartItem2In1" id="chart3"></div>
        </a-card>
        <a-card
          size="small"
          title="词云图"
          :headStyle="{ fontWeight: '600', backgroundColor: '#fafafa' }"
          :style="{ marginTop: '20px' }"
        >
          <div class="chartItem2In1">
            <img
              :src="wordcloudUrlList[Math.floor(Math.random()*44)]"
              alt="词云图"
              style="width: 100%; height: 100%"
            />
          </div>
        </a-card>
        <a-card
          size="small"
          title="热度增长"
          :headStyle="{ fontWeight: '600', backgroundColor: '#fafafa' }"
          :style="{ marginTop: '20px' }"
        >
          <a-spin size="large" v-if="statisticLoading" />
          <div class="chartItem1In1" id="chart2"></div>
        </a-card>
      </div>
      <div class="commemtBox">
        <div class="title">评论区</div>
        <div class="commentList">
          <a-spin size="large" v-if="commentLoading" />
          <div v-else>
            <a-list itemLayout="horizontal" :dataSource="commentList">
              <a-list-item slot="renderItem" slot-scope="item">
                <a-comment :author="item.user.nick_name" :content="item.content">
                  <a-avatar size="large" icon="user" slot="avatar" />
                  <template slot="actions">
                    <span>
                      <a-icon
                        type="like"
                        :theme="item.liked ? 'twoTone' : 'outlined'"
                        twoToneColor="#1da57a"
                        @click="
                          likeComment(item.comment_id, item.user.id, item.liked)
                        "
                      />
                      <span style="padding-left: '8px';cursor: 'auto'">{{ item.like_num }}</span>
                    </span>
                  </template>
                </a-comment>
              </a-list-item>
            </a-list>
            <div class="newComment">
              <a-comment>
                <a-avatar size="large" icon="user" slot="avatar" />
                <div slot="content">
                  <a-textarea placeholder="请输入你的评论" :row="4" v-model="newComment" />
                  <a-button type="primary" @click="submitComment" class="button">添加评论</a-button>
                </div>
              </a-comment>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Vue from "vue";
import G2 from "@antv/g2";
import { DataSet } from "@antv/data-set";
import { Card, List, Spin, Comment, Input, Button, Tag } from "ant-design-vue";
import { mapGetters } from "vuex";

Vue.use(Card)
  .use(List)
  .use(Spin)
  .use(Comment)
  .use(Input)
  .use(Button)
  .use(Tag);

export default {
  name: "ProductDetail",
  data() {
    return {
      // 评论信息
      commentList: [],
      chartHeight: 280,
      basicInfo: {}, // 基本信息
      statisticInfo: {}, // 统计信息
      compareInfo: {}, // 比较信息
      commentLoading: false,
      statisticLoading: false,
      compareLoading: false,
      newComment: "", // 准备新增的内容
      productId: null,
      wordcloudUrlList: [],
      tagList: [],
    };
  },
  methods: {
    // 计算百分比
    computePercent(list) {
      let total = 0;
      list.forEach(item => {
        total += item.value;
      });
      return list.map(item => {
        return {
          item: item.name,
          count: item.value,
          percent: item.value / total,
        };
      });
    },
    drawChart1() {
      const data = this.computePercent([
        {
          name: "收藏次数",
          value: this.statisticInfo.collect_num,
        },
        {
          name: "点赞次数",
          value: this.statisticInfo.like_num,
        },
        { name: "浏览次数", value: this.statisticInfo.browse_num },
        { name: "评论次数", value: this.statisticInfo.review_num },
      ]);
      const chart = new G2.Chart({
        container: "chart1",
        forceFit: true,
        height: this.chartHeight,
        padding: "auto",
      });
      chart.source(data, {
        percent: {
          formatter: val => {
            val = val * 100 + "%";
            return val;
          },
        },
      });
      chart.coord("theta", {
        radius: 0.75,
      });
      chart.tooltip({
        showTitle: false,
        itemTpl:
          '<li><span style="background-color:{color};" class="g2-tooltip-marker"></span>{name}: {value}</li>',
      });
      chart
        .intervalStack()
        .position("percent")
        .color("item")
        .label("count", {
          formatter: (val, item) => {
            return item.point.item + ": " + val + "次";
          },
        })
        .tooltip("item*percent", (item, percent) => {
          percent = percent * 100 + "%";
          return {
            name: item,
            value: percent,
          };
        })
        .style({
          lineWidth: 1,
          stroke: "#fff",
        });
      chart.render();
    },
    drawChart2() {
      // 此处数据使用了按行组织的模式，所以需要使用 DataSet 的 fold 方法对数据进行加工
      const fields = this.statisticInfo.daily
        ? Object.keys(this.statisticInfo.daily)
        : [];
      let likeObj = { name: "点赞次数" };
      let browseObj = { name: "浏览次数" };
      let reviewObj = { name: "评论次数" };

      fields.forEach(item => {
        likeObj = {
          ...likeObj,
          [item]: this.statisticInfo.daily[item].like_num,
        };
        browseObj = {
          ...browseObj,
          [item]: this.statisticInfo.daily[item].browse_num,
        };
        reviewObj = {
          ...reviewObj,
          [item]: this.statisticInfo.daily[item].review_num,
        };
      });

      const data = [{ ...likeObj }, { ...browseObj }, { ...reviewObj }];
      const ds = new DataSet();
      const dv = ds.createView().source(data);
      dv.transform({
        type: "fold",
        fields, // 展开字段集
        key: "日期", // key字段
        value: "次数", // value字段
      });

      const chart = new G2.Chart({
        container: "chart2",
        forceFit: true,
        height: this.chartHeight,
        padding: "auto",
      });
      chart.source(dv);
      chart
        .intervalStack()
        .position("日期*次数")
        .color("name");
      chart.render();
    },
    drawChart3() {
      // 注意由于分类轴的顺序是从下往上的，所以数组的数值顺序要从小到大
      let data = [
        { key: "运行内存排行", value: this.compareInfo.running_memory_rank },
        { key: "重量排行", value: this.compareInfo.weight_rank },
        { key: "机身存储排行", value: this.compareInfo.memory_rank },
        { key: "长度排行", value: this.compareInfo.length_rank },
        { key: "价格排行", value: this.compareInfo.price_rank },
      ];
      data = data.sort((a, b) => {
        return a.value - b.value;
      });
      const chart = new G2.Chart({
        container: "chart3",
        forceFit: true,
        height: this.chartHeight,
        padding: ["auto", "auto", "50", "auto"],
      });
      chart.source(data);
      chart.axis("key", {
        label: {
          offset: 12,
        },
      });
      chart.coord().transpose();
      chart.interval().position("key*value");
      chart.render();
    },
    // drawChart4() {
    //   // 注意由于分类轴的顺序是从下往上的，所以数组的数值顺序要从小到大
    //   let data = [
    //     { key: "收藏排行", value: this.compareInfo.collect_rank },
    //     { key: "点赞排行", value: this.compareInfo.like_rank },
    //     { key: "浏览排行", value: this.compareInfo.browse_rank },
    //   ];
    //   data = data.sort((a, b) => {
    //     return a.value - b.value;
    //   });
    //   const chart = new G2.Chart({
    //     container: "chart4",
    //     forceFit: true,
    //     height: this.chartHeight,
    //     padding: ["auto", "auto", "50", "auto"],
    //   });
    //   chart.source(data);
    //   chart.axis("key", {
    //     label: {
    //       offset: 12,
    //     },
    //   });
    //   chart.coord().transpose();
    //   chart.interval().position("key*value");
    //   chart.render();
    // },
    // 获取基本信息
    getBasicInfo(id) {
      this.$api
        .getBasicInfo({
          productId: id,
        })
        .then(res => {
          if (res.code === 200) {
            this.basicInfo = { ...res.data };
            this.tagList.push(res.data.tag1, res.data.tag2, res.data.tag3);
          }
        });
    },
    // 获取统计信息
    getStatisticInfo(id) {
      this.statisticLoading = true;
      this.$api
        .getStatisticInfo({
          productId: id,
        })
        .then(res => {
          this.statisticLoading = false;
          if (res.code === 200) {
            this.statisticInfo = { ...res.data };
          }
          // 画图
          this.drawChart1();
          this.drawChart2();
        })
        .finally(() => {
          this.statisticLoading = false;
        });
    },
    // 获取对比信息
    getCompareInfo(id) {
      this.compareLoading = true;
      this.$api
        .getCompareInfo({
          productId: id,
        })
        .then(res => {
          this.compareLoading = false;
          if (res.code === 200) {
            this.compareInfo = { ...res.data };
          }
          // 画图
          this.drawChart3();
          // this.drawChart4();
        })
        .finally(() => {
          this.compareLoading = false;
        });
    },
    // 获取评论信息
    getCommentInfo(id) {
      this.commentLoading = true;
      this.$api
        .getCommentInfo({
          productId: id,
        })
        .then(res => {
          if (res.code === 200) {
            this.commentList = res.data.map(item => {
              return {
                ...item,
                liked: false,
              };
            });
          }
        })
        .finally(() => {
          this.commentLoading = false;
        });
    },
    // 获取当前登录用
    getLikedComment() {
      const { id } = this.$store.getters.getUserInfoState;
      if (id) {
        this.$api.getLikedComment(id).then(res => {
          console.log(res);
        });
      }
    },
    // 点赞评论
    likeComment(commentId, userId, liked) {
      if (this.$store.getters.getLoginState) {
        if (!liked) {
          this.commentList = this.commentList.map(item => {
            if (item.comment_id === commentId) {
              return {
                ...item,
                like_num: item.like_num + 1,
                liked: true,
              };
            } else {
              return { ...item };
            }
          });
          // 发送点赞请求
          this.$api.commentLike({
            userId: userId,
            commentId: commentId,
          });
        }
      } else {
        this.$message.error("请先登录");
      }
    },
    // 添加评论
    submitComment() {
      if (this.$store.getters.getLoginState) {
        const content = this.newComment.trim();
        if (content) {
          this.$api
            .commentAdd({
              comment: {
                userId: this.$store.getters.getUserInfoState.id,
                productId: this.productId,
                productType: this.basicInfo.library,
                content,
              },
            })
            .then(res => {
              console.log(res);
            });
        }
      } else {
        this.$message.error("请先登录");
      }
    },
    // 随机一个属性的标签
    randomColor() {
      const tagsColor = this.$store.state.tagsColor;
      return tagsColor[Math.floor(Math.random() * 6)];
    },
  },
  computed: {
    ...mapGetters({
      // recommendItems: "search/getRecommendItems",
      relatedTags: "search/getRelatedTags",
    }),
  },
  created() {
    this.productId = this.$route.query.id;
    // 获取商品的ID，用于查询
    this.getBasicInfo(this.productId);
    this.getCommentInfo(this.productId);
    this.getLikedComment();

    // 获取图片
    for (let i = 1; i <= 44; i++) {
      this.wordcloudUrlList.push(require(`@/assets/wordcloud/${i}.png`));
    }

    let list = [...this.relatedTags];
    for (let i = 0; i < 5; i++) {
      const index = Math.floor(Math.random() * list.length);
      this.tagList.push(...list.splice(index, 1));
    }
  },
  mounted() {
    this.getStatisticInfo(this.productId);
    this.getCompareInfo(this.productId);
  },
};
</script>

<style lang="less" scoped>
@borderColor: #e3e3e3;
@extraTextColor: #888;
@containerWidth: 800px;
.container {
  display: flex;
  justify-content: center;
  background-color: white;
  .content {
    width: @containerWidth;
    // background-color: antiquewhite;
    margin: 50px 0;
    font-family: "Microsoft YaHei", "\5FAE\8F6F\96C5\9ED1";
    .mainInfo {
      height: 300px;
      display: flex;
      // justify-content: space-between;
      .image {
        height: 100%;
        border: 1px solid @borderColor;
      }
      .info {
        display: flex;
        flex-direction: column;
        justify-content: space-around;
        align-items: flex-start;
        margin-left: 70px;
        .cell {
          display: flex;
          .text {
            padding-right: 20px;
          }
          .extra {
            padding-left: 20px;
            border-left: 1px solid @borderColor;
            color: @extraTextColor;
          }
        }
      }
    }
    .detail {
      margin: 20px 0 10px;
      .title {
        text-align: left;
        height: 48px;
        line-height: 48px;
        font-size: 18px;
        font-weight: 700;
      }
      .card {
        border: 1px solid @borderColor;
        border-bottom: none;
        .subTitle {
          text-align: left;
          font-size: 16px;
          line-height: 34px;
          font-weight: bold;
          color: #333;
          background: #fafafa;
          padding: 4px 20px;
          border-bottom: 1px solid @borderColor;
        }
        .cardItem {
          display: flex;
          .key {
            width: 20%;
            text-align: left;
            font-weight: normal;
            color: #333;
            border-right: 1px solid @borderColor;
            border-bottom: 1px solid @borderColor;
            padding: 8px 20px;
          }
          .value {
            width: 80%;
            text-align: left;
            font-weight: normal;
            color: #333;
            padding: 8px 20px;
            border-bottom: 1px solid @borderColor;
          }
        }
      }
    }
    .chart {
      margin-top: 30px;
      display: flex;
      flex-wrap: wrap;
      justify-content: space-between;
      .chartItem2In1 {
        width: @containerWidth / 2 - 50px;
        height: 300px;
        display: flex;
        justify-content: center;
        align-items: center;
      }
      .chartItem1In1 {
        min-width: @containerWidth - 24px;
        height: 300px;
        display: flex;
        justify-content: center;
        align-items: center;
      }
    }
    .commemtBox {
      margin: 20px 0 10px;
      .title {
        text-align: left;
        height: 48px;
        line-height: 48px;
        font-size: 18px;
        font-weight: 700;
      }
      .commentList {
        border: 1px solid @borderColor;
        text-align: left;
        padding: 0 20px;
        & /deep/ .ant-list-item {
          padding: 0;
        }
      }
      .newComment {
        border-top: 1px solid @borderColor;
        .button {
          margin-top: 16px;
        }
      }
    }
    .tags {
      display: flex;
      flex-wrap: wrap;
      .tag {
        margin: 24px 12px 24px 0;
      }
    }
  }
}
</style>
