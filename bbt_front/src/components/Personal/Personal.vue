<template>
  <div class="card">
    <div class="title">个人画像</div>
    <a-row :gutter="20">
      <a-col :span="8">
        <div class="background graphics-3in1">
          <a-statistic title="评论商品总数" value="123" :valueStyle="statisticValueStyle"></a-statistic>
          <div class="statistic-extra statistic-extra_padding">
            排名：12
            <a-icon type="caret-up" :style="{marginLeft: '5px', color: 'green'}" />
          </div>
        </div>
      </a-col>
      <a-col :span="8">
        <div class="background graphics-3in1">
          <a-statistic title="查看过的商品总数" value="233" :valueStyle="statisticValueStyle"></a-statistic>
          <div class="statistic-extra" id="readTotalContainer"></div>
        </div>
      </a-col>
      <a-col :span="8">
        <div class="background graphics-3in1">
          <a-statistic title="收藏商品总数" value="123" :valueStyle="statisticValueStyle"></a-statistic>
          <div class="statistic-extra statistic-extra_padding">
            排名：12
            <a-icon type="caret-up" :style="{marginLeft: '5px', color: 'green'}" />
          </div>
        </div>
      </a-col>
    </a-row>
    <a-row :gutter="20">
      <a-col :span="12">
        <div class="background graphics-2in1">
          <a-card title="评论被点赞的总数" :bordered="false" :bodyStyle="cardBodyStyle">
            <div id="commentCountContainer"></div>
          </a-card>
        </div>
      </a-col>
      <a-col :span="12">
        <div class="background graphics-2in1">
          <a-card title="收藏的商品的标签" :bordered="false" :bodyStyle="cardBodyStyle">
            <div id="productLabelContainer"></div>
          </a-card>
        </div>
      </a-col>
    </a-row>
    <a-row :gutter="20">
      <a-col :span="12">
        <div class="background graphics-2in1">
          <a-card title="浏览次数前五的品牌" :bordered="false" :bodyStyle="cardBodyStyle">
            <div id="fiveMostVisitContainer"></div>
          </a-card>
        </div>
      </a-col>
      <a-col :span="12">
        <div class="background graphics-2in1">
          <a-card title="收藏商品的品牌分布" :bordered="false" :bodyStyle="cardBodyStyle">
            <div id="productBrandContainer"></div>
          </a-card>
        </div>
      </a-col>
    </a-row>
    <a-row :gutter="20">
      <a-col :span="24">
        <div class="background graphics-1 bottom">
          <a-card title="过去十五天浏览商品的次数" :bordered="false" :bodyStyle="cardBodyStyle">
            <div id="productVisitContainer"></div>
          </a-card>
        </div>
      </a-col>
    </a-row>
  </div>
</template>

<script>
import Vue from "vue";
import G2 from "@antv/g2";
import { Card, Col, Row, Statistic } from "ant-design-vue";

Vue.use(Card);
Vue.use(Col);
Vue.use(Row);
Vue.use(Statistic);

export default {
  name: "Personal",
  data() {
    return {
      statisticValueStyle: {
        margin: "5px 0",
        fontSize: "26px",
        color: "black",
      },
      cardBodyStyle: {
        padding: "16px",
      },
    };
  },
  mounted() {
    this.drawReadTotal();
    this.drawCommentCount();
    this.drawProductLabel();
    this.drawFiveMostVisit();
    this.drawProductBrand();
    this.drawProductVisit();
  },
  methods: {
    drawReadTotal() {
      const data = [
        { label: "阅读量", type: "笔记本", value: 13 },
        { label: "阅读量", type: "手机", value: 20 },
      ];
      const chart = new G2.Chart({
        container: "readTotalContainer",
        forceFit: true,
        height: 50,
        padding: 0,
      });
      chart.source(data);
      // chart.axis("value", {
      //   position: "right",
      // });
      // chart.axis("label", {
      //   label: {
      //     offset: 12,
      //   },
      // });
      chart.legend(false);
      chart.axis(false);
      chart
        .coord()
        .transpose()
        .scale(1, -1);
      chart
        .interval()
        .position("label*value")
        .color("type")
        .adjust([
          {
            type: "dodge",
            marginRatio: 1 / 32,
          },
        ]);
      chart.render();
    },
    drawCommentCount() {
      const data = [
        { label: "0~50", value: 38 },
        { label: "51~100", value: 52 },
        { label: "101~500", value: 61 },
        { label: "500以上", value: 145 },
      ];
      const chart = new G2.Chart({
        container: "commentCountContainer",
        forceFit: true,
        height: 200,
        padding: "auto",
      });
      chart.source(data);
      chart.scale("value", {
        tickInterval: 20,
      });
      chart.interval().position("label*value");
      chart.render();
    },
    drawProductBrand() {
      const data = [
        { item: "华为", count: 40, percent: 0.4 },
        { item: "小米", count: 21, percent: 0.21 },
        { item: "联想", count: 17, percent: 0.17 },
        { item: "魅族", count: 13, percent: 0.13 },
        { item: "神舟", count: 9, percent: 0.09 },
      ];
      const chart = new G2.Chart({
        container: "productBrandContainer",
        forceFit: true,
        height: 200,
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
      chart.coord("theta");
      chart.tooltip({
        showTitle: false,
      });
      chart
        .intervalStack()
        .position("percent")
        .color("item")
        .label("percent", {
          offset: -40,
          // autoRotate: false,
          textStyle: {
            textAlign: "center",
            shadowBlur: 2,
            shadowColor: "rgba(0, 0, 0, .45)",
            fill: "#fff",
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
    drawFiveMostVisit() {
      const data = [
        { label: "华为", value: 12 },
        { label: "小米", value: 24 },
        { label: "联想", value: 56 },
        { label: "神州", value: 2 },
        { label: "魅族", value: 77 },
      ];
      const chart = new G2.Chart({
        container: "fiveMostVisitContainer",
        forceFit: true,
        height: 200,
        padding: "auto",
      });
      chart.source(data);
      chart.axis("label", {
        label: {
          offset: 12,
        },
      });
      chart.coord().transpose();
      chart.interval().position("label*value");
      chart.render();
    },
    drawProductLabel() {
      const data = [
        { type: "华为", value: 40 },
        { type: "大屏幕", value: 21 },
        { type: "大电池", value: 17 },
        { type: "紫色", value: 13 },
        { type: "重量轻", value: 9 },
      ];

      // 可以通过调整这个数值控制分割空白处的间距，0-1 之间的数值
      const sliceNumber = 0.01;

      // 自定义 other 的图形，增加两条线
      G2.Shape.registerShape("interval", "sliceShape", {
        draw(cfg, container) {
          const points = cfg.points;
          let path = [];
          path.push(["M", points[0].x, points[0].y]);
          path.push(["L", points[1].x, points[1].y - sliceNumber]);
          path.push(["L", points[2].x, points[2].y - sliceNumber]);
          path.push(["L", points[3].x, points[3].y]);
          path.push("Z");
          path = this.parsePath(path);
          return container.addShape("path", {
            attrs: {
              fill: cfg.color,
              path,
            },
          });
        },
      });

      const chart = new G2.Chart({
        container: "productLabelContainer",
        forceFit: true,
        height: 200,
        padding: "auto",
      });

      chart.source(data);
      chart.coord("theta", {
        innerRadius: 0.75,
      });
      chart.tooltip({
        showTitle: false,
      });
      chart
        .intervalStack()
        .position("value")
        .color("type")
        .shape("sliceShape");

      chart.render();
    },
    drawProductVisit() {
      const data = [
        { date: "11/1", value: 3 },
        { date: "11/2", value: 4 },
        { date: "11/3", value: 3.5 },
        { date: "11/4", value: 5 },
        { date: "11/5", value: 4.9 },
        { date: "11/6", value: 6 },
        { date: "11/7", value: 7 },
        { date: "11/8", value: 9 },
        { date: "11/9", value: 13 },
        { date: "11/10", value: 3 },
        { date: "11/11", value: 4 },
        { date: "11/12", value: 3.5 },
        { date: "11/13", value: 5 },
        { date: "11/14", value: 4.9 },
        { date: "11/15", value: 6 },
      ];
      const chart = new G2.Chart({
        container: "productVisitContainer",
        forceFit: true,
        height: 250,
        padding: "auto",
      });
      chart.source(data);
      chart.scale("value", {
        min: 0,
        alias: "浏览量",
      });
      chart.scale("date", {
        range: [0, 1],
      });
      chart.tooltip({
        crosshairs: {
          type: "line",
        },
      });
      chart.line().position("date*value");
      chart
        .point()
        .position("date*value")
        .size(4)
        .shape("circle")
        .style({
          stroke: "#fff",
          lineWidth: 1,
        });
      chart.render();
    },
  },
};
</script>

<style lang="less" scoped>
.card {
  text-align: left;
  .title {
    min-height: 48px;
    padding: 16px 24px;
    background-color: white;
    font-size: 16px;
    color: rgba(0, 0, 0, 0.85);
    margin-bottom: 10px;
  }

  .background {
    background-color: white;
    margin-bottom: 15px;
  }

  .graphics-3in1 {
    height: 150px;
    padding: 16px;
  }

  .graphics-2in1 {
    height: 300px;
  }

  .graphics-1 {
    height: 350px;
  }
  .bottom {
    margin-bottom: 50px;
  }

  .statistic-extra {
    border-top: 1px solid #e3e3e3;
  }

  .statistic-extra_padding {
    padding: 20px 0;
  }
}
</style>
