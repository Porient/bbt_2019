<template>
  <div class="container">
    <div class="content">
      <div class="mainInfo">
        <img
          class="image"
          src="https://pro-fd.zol-img.com.cn/t_s300x300c5/g4/M01/07/0F/ChMlzF2Dpy6IejY8AAEVvvSQ3WIAAXuRgENve0AARXW047.jpg"
          alt="商品图片"
        />
        <div class="info">
          <div class="cell">
            <div class="text">品牌：华为</div>
            <div class="extra">知名品牌</div>
          </div>
          <div class="cell">
            <div class="text">名称：Mate30 Pro</div>
            <div class="extra">高端机型</div>
          </div>
          <div class="cell">
            <div class="text">CPU：麒麟990</div>
            <div class="extra">高性能芯片</div>
          </div>
          <div class="cell">
            <div class="text">内存：8G</div>
            <div class="extra">大内存</div>
          </div>
          <div class="cell">
            <div class="text">电池：4500毫安时</div>
            <div class="extra">大电池</div>
          </div>
          <div class="cell">
            <div class="text">屏幕：6.53英寸</div>
            <div class="extra">大屏幕</div>
          </div>
          <div class="cell">
            <div class="text">分辨率：2400x1176像素</div>
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
            <div class="value">2019年</div>
          </div>
          <div class="cardItem">
            <div class="key">价格</div>
            <div class="value">4999</div>
          </div>
          <div class="cardItem">
            <div class="key">品牌</div>
            <div class="value">华为</div>
          </div>
          <div class="cardItem">
            <div class="key">型号</div>
            <div class="value">Mate30</div>
          </div>
          <div class="cardItem">
            <div class="key">机身颜色</div>
            <div class="value">红色</div>
          </div>
          <div class="cardItem">
            <div class="key">操作系统类型</div>
            <div class="value">安卓</div>
          </div>
          <div class="cardItem">
            <div class="key">操作系统版本</div>
            <div class="value">Android 10</div>
          </div>
        </div>
        <div class="card">
          <div class="subTitle">硬件</div>
          <div class="cardItem">
            <div class="key">CPU型号</div>
            <div class="value">麒麟 990</div>
          </div>
          <div class="cardItem">
            <div class="key">运行内存</div>
            <div class="value">8G</div>
          </div>
          <div class="cardItem">
            <div class="key">机身存储</div>
            <div class="value">256G</div>
          </div>
        </div>
        <div class="card">
          <div class="subTitle">屏幕</div>
          <div class="cardItem">
            <div class="key">屏幕尺寸</div>
            <div class="value">6寸</div>
          </div>
          <div class="cardItem">
            <div class="key">屏幕比例</div>
            <div class="value">18.5 : 9</div>
          </div>
          <div class="cardItem">
            <div class="key">分辨率</div>
            <div class="value">1080x1920</div>
          </div>
          <div class="cardItem">
            <div class="key">屏幕材质类型</div>
            <div class="value">LCD</div>
          </div>
        </div>
        <div class="card">
          <div class="subTitle">摄像头</div>
          <div class="cardItem">
            <div class="key">前置摄像头</div>
            <div class="value">xxxx</div>
          </div>
          <div class="cardItem">
            <div class="key">后置摄像头</div>
            <div class="value">xxxx</div>
          </div>
        </div>
      </div>
      <div class="chart">
        <a-card
          size="small"
          title="热度"
          :headStyle="{fontWeight: '600', backgroundColor: '#fafafa'}"
          :style="{marginTop: '20px'}"
        >
          <div class="chartItem" id="chart1"></div>
        </a-card>
        <a-card
          size="small"
          title="热度增长"
          :headStyle="{fontWeight: '600', backgroundColor: '#fafafa'}"
          :style="{marginTop: '20px'}"
        >
          <div class="chartItem" id="chart2"></div>
        </a-card>
        <a-card
          size="small"
          title="词云图"
          :headStyle="{fontWeight: '600', backgroundColor: '#fafafa'}"
          :style="{marginTop: '20px'}"
        >
          <div class="chartItem">词云图</div>
        </a-card>
        <a-card
          size="small"
          title="排名"
          :headStyle="{fontWeight: '600', backgroundColor: '#fafafa'}"
          :style="{marginTop: '20px'}"
        >
          <div class="chartItem" id="chart3"></div>
        </a-card>
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
    };
  },
  methods: {
    drawChart1() {
      const data = [
        { item: "收藏次数", count: 40, percent: 0.4 },
        { item: "点赞次数", count: 21, percent: 0.21 },
        { item: "浏览次数", count: 17, percent: 0.17 },
        { item: "评论次数", count: 13, percent: 0.13 },
      ];
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
      const data = [
        {
          name: "评论次数",
          "Jan.": 18.9,
          "Feb.": 28.8,
          "Mar.": 39.3,
          "Apr.": 81.4,
          May: 47,
          "Jun.": 20.3,
          "Jul.": 24,
          "Aug.": 35.6,
        },
        {
          name: "点赞次数",
          "Jan.": 12.4,
          "Feb.": 23.2,
          "Mar.": 34.5,
          "Apr.": 99.7,
          May: 52.6,
          "Jun.": 35.5,
          "Jul.": 37.4,
          "Aug.": 42.4,
        },
        {
          name: "浏览次数",
          "Jan.": 12.4,
          "Feb.": 23.2,
          "Mar.": 34.5,
          "Apr.": 99.7,
          May: 52.6,
          "Jun.": 35.5,
          "Jul.": 37.4,
          "Aug.": 42.4,
        },
        {
          name: "收藏次数",
          "Jan.": 12.4,
          "Feb.": 23.2,
          "Mar.": 34.5,
          "Apr.": 99.7,
          May: 52.6,
          "Jun.": 35.5,
          "Jul.": 37.4,
          "Aug.": 42.4,
        },
      ];
      const ds = new DataSet();
      const dv = ds.createView().source(data);
      dv.transform({
        type: "fold",
        fields: ["Jan.", "Feb.", "Mar.", "Apr.", "May", "Jun.", "Jul.", "Aug."], // 展开字段集
        key: "月份", // key字段
        value: "次数", // value字段
      });
      console.log(dv);

      const chart = new G2.Chart({
        container: "chart2",
        forceFit: true,
        height: this.chartHeight,
        padding: "auto",
      });
      chart.source(dv);
      chart
        .intervalStack()
        .position("月份*次数")
        .color("name");
      chart.render();
    },
    drawChart3() {
      // 注意由于分类轴的顺序是从下往上的，所以数组的数值顺序要从小到大
      const data = [
        { key: "运行内存排行", value: 18203 },
        { key: "重量排行", value: 23489 },
        { key: "机身存储排行", value: 29034 },
        { key: "长度排行", value: 104970 },
        { key: "价格排行", value: 131744 },
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
  },
  created() {
    // 获取商品的ID，用于查询
    console.log(this.$route.query);
  },
  mounted() {
    this.drawChart1();
    this.drawChart2();
    this.drawChart3();
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
      .chartItem {
        width: @containerWidth / 2 - 50px;
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