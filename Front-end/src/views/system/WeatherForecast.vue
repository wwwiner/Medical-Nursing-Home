<template>

  <div class="container">
    <div class="nav">
      <div class="time">{{ localTime }}</div>
      <div  @click="changeCity">切换城市</div>
    </div>

    <div class="city-info">
      <p class="city">{{ mapData.city }}</p>
      <p class="weather">{{ mapData.weather }}</p>
      <h2 class="temp">
        <em>{{ mapData.temperature }}</em
        >℃
      </h2>
      <div class="detail">
        <span>风力: {{ mapData.windPower }}</span> |
        <span>风向: {{ mapData.windDirection }}</span> |
        <span>空气湿度: {{ mapData.humidity }} %</span>
      </div>
    </div>

    <div class="future" v-if="futureMapData.length > 0">
      <div class="group">
        明天:
        <span class="tm"
        >白天: {{ futureMapData[1].dayTemp }}℃
          {{ futureMapData[1].dayWeather }}
          {{ futureMapData[1].dayWindDir }}
          {{ futureMapData[1].dayWindPower }}
        </span>
        <span class="tm"
        >夜间: {{ futureMapData[1].nightTemp }}℃
          {{ futureMapData[1].nightWeather }}
          {{ futureMapData[1].nightWindDir }}
          {{ futureMapData[1].nightWindPower }}
        </span>
      </div>
      <div class="group">
        后天:
        <span class="tm"
        >白天: {{ futureMapData[2].dayTemp }}℃
          {{ futureMapData[2].dayWeather }}
          {{ futureMapData[2].dayWindDir }}
          {{ futureMapData[2].dayWindPower }}
        </span>
        <span class="tm"
        >夜间: {{ futureMapData[2].nightTemp }}℃
          {{ futureMapData[2].nightWeather }}
          {{ futureMapData[2].nightWindDir }}
          {{ futureMapData[2].nightWindPower }}
        </span>
      </div>
    </div>

    <div class="echart-container" ref="echartContainer"></div>

    <van-popup v-model="show" position="bottom" :style="{ height: '30%' }" >
      <van-area
          title="标题"
          :area-list="areaList"

          @cancel="show = false"
          columns-num="2"
          @confirm="selectCity"
      />
    </van-popup>

  </div>
</template>

<script>
import * as echarts from "echarts";
import { areaList } from '@vant/area-data';
import area from "@/utils/area.js"
export default {
  data() {
    return {
      localTime: "",
      mapData: {},
      futureMapData: [],
      seriesData: [],
      seriesNightData: [],
      areaList: areaList,
      show: false
    };
  },
  created() {

    setInterval(() => {
      this.localTime = this.getLocalTime();
    }, 1000);

    this.initMap();

    this.areaList = area.AddressInfo;
  },
  methods: {
    getLocalTime() {
      return new Date().toLocaleTimeString();
    },
    initMap() {
      let that = this;
      AMap.plugin("AMap.CitySearch", function () {
        var citySearch = new AMap.CitySearch();
        citySearch.getLocalCity(function (status, result) {
          if (status === "complete" && result.info === "OK") {
            // 查询成功，result即为当前所在城市信息
            // console.log(result.city);
            that.getWeatherData(result.city);
          }
        });
      });
    },
    getWeatherData(cityName) {
      let that = this;
      AMap.plugin("AMap.Weather", function () {
        //创建天气查询实例
        var weather = new AMap.Weather();

        //执行实时天气信息查询
        weather.getLive(cityName, function (err, data) {
          console.log(err, data);
          that.mapData = data;
        });

        //执行实时天气信息查询
        weather.getForecast(cityName, function (err, data) {
          that.futureMapData = data.forecasts;
          console.log(that.futureMapData);

          // 每天的温度
          that.seriesData = []
          that.seriesNightData = []
          data.forecasts.forEach(item => {
            that.seriesData.push(item.dayTemp)
            that.seriesNightData.push(item.nightTemp)
          })

          that.$nextTick(() => {
            that.initEchart()
          })
        });
      });
    },
    initEchart() {
      // 基于准备好的dom，初始化echarts实例
      let myChart = echarts.init(this.$refs.echartContainer);

      // 绘制图表
      let option = {
        title: {
          text: "未来三天天气情况",
        },
        tooltip: {},
        xAxis: {
          data: ["今天", "明天", "后天", "三天后"],
          axisTick: {
            show: false
          },
          axisLine: {
            lineStyle: {
              color: "#fff"
            }
          }
        },
        yAxis: {
          min: '-10',
          max: '50',
          interval: 10,
          axisLine: {
            show: true,
            lineStyle: {
              color: '#fff'
            }
          },
          splitLine: {
            show: true,
            lineStyle: {
              type: "dashed",
              color: ['red', 'green', 'yellow']
            }
          }
        },
        series: [
          {
            name: "白天温度",
            type: "line",
            data: this.seriesData,
          },
          {
            name: "夜间温度",
            type: "line",
            data: this.seriesNightData,
            lineStyle: {
              color: 'red'
            }
          },
        ],
      }
      myChart.setOption(option)
    },
    changeCity() {
      this.show = true
    },
    selectCity(e) {
      console.log(e[1].name);
      this.getWeatherData(e[1].name)
      this.show = false
    },
  },
};
</script>
<style lang="less" scoped>
.container {
  padding-left: 20px;
  padding-right: 20px;
  padding-top: 20px;
  margin-top: -20px;
  margin-left: -22px;
  margin-right:-20px;
  margin-bottom: -20px;

  height: 710px;
  background: #000;
  opacity: 0.7;
  color: #fff;
  font-size: 16px;
  .nav {
    display: flex;
    justify-content: space-between;
    padding: 10px;
  }
  .city {
    font-size: 30px;
    text-align: center;
    padding: 10px;
    margin-top: 10px;

  }
  .time{
    margin-top: 10px;
    text-align: center;
    font-size: large;
  }
  .weather{
    margin-top: 50px;
    margin-left: 530px;
    font-size: 25px;
  }
  .temp{
    text-align: left;
    margin-left: 620px;

    .em{
      margin-left: 100px;}
  }
  .detail{

    margin-left: 470px;
    margin-top: 40px;
  }
  .future {
    text-align: center;
    margin-top: 30px;
    padding: 0 10px;
    .group {
      height: 44px;
      line-height: 44px;
      border-radius: 4px;
      background: rgba(255, 255, 255, 0.6);
      color: rgba(16, 16, 16, 1);
      font-size: 20px;
      margin-bottom: 10px;
      padding: 0 10px;
      .tm {
        color: #fff;
      }
      .tm:last-child {
        margin-left: 8px;
      }
    }
  }
  .echart-container {
    margin-top: 50px;
    margin-left: 130px;
    margin-right: 150px;

    width: 100%;
    height: 50vh;
  }
}
</style>