<template>
  <div class="container">
    <div class="content">
      <div class="mainInfo">
        <img
          class="image"
          :src="'http://' + basicInfo.appearance1"
          alt="商品图片"
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
            <div class="text">
              分辨率：{{ basicInfo.screen ? basicInfo.screen.dpi : "" }}
            </div>
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
            <div class="value">
              {{ basicInfo.body ? basicInfo.body.color : "" }}
            </div>
          </div>
          <div class="cardItem">
            <div class="key">操作系统类型</div>
            <div class="value">
              {{ basicInfo.performance ? basicInfo.performance.os_type : "" }}
            </div>
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
            <div class="value">
              {{ basicInfo.performance ? basicInfo.performance.cpu_type : "" }}
            </div>
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
            <div class="value">
              {{ basicInfo.body ? basicInfo.body.memory + "G" : "" }}
            </div>
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
            <div class="value">
              {{ basicInfo.screen ? basicInfo.screen.propotion : "" }}
            </div>
          </div>
          <div class="cardItem">
            <div class="key">分辨率</div>
            <div class="value">
              {{ basicInfo.screen ? basicInfo.screen.dpi : "" }}
            </div>
          </div>
          <div class="cardItem">
            <div class="key">屏幕材质类型</div>
            <div class="value">
              {{ basicInfo.screen ? basicInfo.screen.screen_type : "" }}
            </div>
          </div>
        </div>
        <div class="card">
          <div class="subTitle">摄像头</div>
          <div class="cardItem">
            <div class="key">前置摄像头光圈</div>
            <div class="value">
              {{ basicInfo.front_camera ? basicInfo.front_camera.circle : "" }}
            </div>
          </div>
          <div class="cardItem">
            <div class="key">前置摄像头像素</div>
            <div class="value">
              {{ basicInfo.front_camera ? basicInfo.front_camera.pixel : "" }}
            </div>
          </div>
          <div class="cardItem">
            <div class="key">后置摄像头光圈</div>
            <div class="value">
              {{ basicInfo.rear_camera ? basicInfo.rear_camera.circle : "" }}
            </div>
          </div>
          <div class="cardItem">
            <div class="key">后置摄像头像素</div>
            <div class="value">
              {{ basicInfo.rear_camera ? basicInfo.rear_camera.pixel : "" }}
            </div>
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
          <div class="chartItem2In1" id="chart1"></div> </a-card
        ><a-card
          size="small"
          title="排名"
          :headStyle="{ fontWeight: '600', backgroundColor: '#fafafa' }"
          :style="{ marginTop: '20px' }"
        >
          <div class="chartItem2In1" id="chart3"></div>
        </a-card>
        <a-card
          size="small"
          title="热度增长"
          :headStyle="{ fontWeight: '600', backgroundColor: '#fafafa' }"
          :style="{ marginTop: '20px' }"
        >
          <div class="chartItem1In1" id="chart2"></div>
        </a-card>
        <!-- <a-card
          size="small"
          title="词云图"
          :headStyle="{ fontWeight: '600', backgroundColor: '#fafafa' }"
          :style="{ marginTop: '20px' }"
        >
          <div class="chartItem">词云图</div>
        </a-card> -->
      </div>
      <div class="commemtBox">
        <div class="title">评论区</div>
        <div class="commentList">
          <a-list itemLayout="horizontal" :dataSource="commentList">
            <a-list-item slot="renderItem" slot-scope="item">
              <a-list-item-meta :description="item.comment" :title="item.name">
                <a-avatar size="large" icon="user" slot="avatar" />
              </a-list-item-meta>
            </a-list-item>
          </a-list>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Vue from "vue";
import G2 from "@antv/g2";
import { DataSet } from "@antv/data-set";
import { Card, List } from "ant-design-vue";

Vue.use(Card).use(List);

export default {
  name: "ProductDetail",
  data() {
    return {
      commentList: [
        { name: "max", comment: "这台手机很好！！！！！" },
        { name: "max", comment: "这台手机很好！！！！！" },
        { name: "max", comment: "这台手机很好！！！！！" },
        { name: "max", comment: "这台手机很好！！！！！" },
        { name: "max", comment: "这台手机很好！！！！！" },
      ],
      chartHeight: 280,
      basicInfo: {}, // 基本信息
      statisticInfo: {}, // 统计信息
      compareInfo: {}, // 比较信息
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
      const fields = this.statisticInfo.daily ? Object.keys(this.statisticInfo.daily): [];
      let likeObj = {name: "点赞次数"}
      let browseObj = {name: "浏览次数"}
      let reviewObj = {name: "评论次数"}

      fields.forEach(item => {
        likeObj = {...likeObj,[item]:this.statisticInfo.daily[item].like_num}
        browseObj = {...browseObj,[item]:this.statisticInfo.daily[item].browse_num}
        reviewObj = {...reviewObj,[item]:this.statisticInfo.daily[item].review_num}
      })

      const data = [{...likeObj},{...browseObj},{...reviewObj}]
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
      console.log(dv)
      chart
        .intervalStack()
        .position("日期*次数")
        .color("name");
      chart.render();
    },
    drawChart3() {
      // 注意由于分类轴的顺序是从下往上的，所以数组的数值顺序要从小到大
      const data = [
        { key: "运行内存排行", value: this.compareInfo.running_memory_rank },
        { key: "重量排行", value: this.compareInfo.weight_rank },
        { key: "机身存储排行", value: this.compareInfo.memory_rank },
        { key: "长度排行", value: this.compareInfo.length_rank },
        { key: "价格排行", value: this.compareInfo.price_rank },
      ];
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
    // 获取基本信息
    getBasicInfo(id) {
      this.$api
        .getBasicInfo({
          productId: id,
        })
        .then(res => {
          if (res.code === 200) {
            this.basicInfo = { ...res.data };
          }
        });
    },
    // 获取统计信息
    getStatisticInfo(id) {
      this.$api
        .getStatisticInfo({
          productId: id,
        })
        .then(res => {
          if (res.code === 200) {
            this.statisticInfo = { ...res.data };
          }
          // 画图
          this.drawChart1();
          this.drawChart2();
        });
    },
    // 获取对比信息
    getCompareInfo(id) {
      this.$api
        .getCompareInfo({
          productId: id,
        })
        .then(res => {
          if (res.code === 200) {
            this.compareInfo = { ...res.data };
          }
          // 画图
          this.drawChart3();
        });
    },
    // 获取评论信息
    getCommentInfo(id) {
      this.$api.getCommentInfo({
        productId:id,
      }).then(res => {
        if(res.code === 200) {
          console.log(res)
        }
      })
    }
  },
  created() {
    // 获取商品的ID，用于查询
    this.getBasicInfo(this.$route.query.id);
    this.getCommentInfo(this.$route.query.id);
  },
  mounted() {
    this.getStatisticInfo(this.$route.query.id);
    this.getCompareInfo(this.$route.query.id);
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
        min-width: @containerWidth / 2 - 50px;
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
      }
    }
  }
}
</style>
