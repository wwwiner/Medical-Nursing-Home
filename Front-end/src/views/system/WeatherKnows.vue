<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>公告展示</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/index/weatherKnows' }"
      >天气查询</el-breadcrumb-item
      >
    </el-breadcrumb>

      <div class="marquee-box" ref="marquee" @mouseover="menter" @mouseleave="mleave">
        <p  ref="cmdlist" id="pWidth">
          尊敬的用户您好，本疗养院提醒您：注意身体健康，请根据天气增减衣物！
        </p>
      </div>

      <div class="search_form">
<!--      <div class="logo"><img src="src/assets/logo.png" alt="logo" /></div>-->
      <div class="form_group">
        <input
            type="text" v-model="city" @keyup.enter="searchWeather"

            class="input_txt"
            placeholder="请输入查询的天气"
        />
        <button class="input_sub" @click="searchWeather">
          搜 索
        </button>
      </div>
      <div class="hotkey">
        <a href="javascript:;" @click="changeCity('北京')">北京</a>
        <a href="javascript:;" @click="changeCity('上海')">上海</a>
        <a href="javascript:;" @click="changeCity('广州')">广州</a>
        <a href="javascript:;" @click="changeCity('深圳')">深圳</a>
      </div>
     </div>
     <ul class="weather_list">
       <li v-for="item in weatherList">
        <div class="info_type"><span class="iconfont">{{ item.cond.txt_d }}</span></div>
        <div class="info_temp">
          <b>{{ "最低 "+item.tmp.min+"度" }}</b>
          ~
          <b>{{ "最高 "+item.tmp.max+"度" }}</b>
        </div>
        <div class="info_date"><span>{{ item.date }}</span></div>
       </li>
      </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      value: 0,
      timer: '',//计时器
      pwidth: 0,//公告文本的宽度
      windowWidth: document.documentElement.clientWidth,// 设备屏幕的宽度

      city: '',
      weatherList: []
    }
  },
  mounted() {
    let element = this.$refs.cmdlist;
    this.pwidth = document.defaultView.getComputedStyle(element, '').width.split('px');
    this.timer = setInterval(this.clickCommend, 20);
  },

  watch: {
    value(newValue, oldValue)
    {
      let allWidth = parseInt(this.windowWidth) + parseInt(this.pwidth[0]);
      if (newValue <= -allWidth) {
        this.$refs.cmdlist.style.marginLeft = this.windowWidth + "px";
        this.value = 0;
      }
    }

  },
  methods: {
    clickCommend(e) {
      let _this = this;
      this.$nextTick(() => {
        this.value -= 1;
        this.$refs.cmdlist.style.marginLeft = _this.windowWidth + this.value + "px";
      });
    },
    menter() {
      clearInterval(this.timer);
    },
    mleave() {
      this.timer = setInterval(this.clickCommend, 20);
    },

  beforeDestroy() {
    clearInterval(this.timer);
  },

  searchWeather: function () {
    //    console.log('天气查询');
    //    console.log(this.city);

    const that = this;

    this.$axios.get('https://bird.ioliu.cn/weather?city=' + this.city)
        .then(function (response) {
          console.log(response)
          that.weatherList = response.data.daily_forecast

        }).catch(function (err) {

    })


  },
  changeCity: function (city) {
    this.city = city;

    // this关键字调用methods内部的方法
    this.searchWeather();

  }
}



};



</script>

<style>
#box {
  width: 100%;
  height: 50px;
  margin-top: 50px;
  position: relative;
}
.marquee-box  {
  margin-top: 30px;
  position: relative;
  width: 100%;
  height: 100%;
  overflow:auto;
  background-color: #f8f8f8;
}
#pWidth{
  width: 100%;
  height: 50px;
  padding: 0;
  margin: 0;
  line-height: 50px;
  display: block;
  word-break: keep-all;
  white-space: nowrap;
  overflow:hidden;
  font-family: 微软雅黑; fontSize:14px;
  background-color: #f8f8f8
}
::-webkit-scrollbar {
  width: 0 !important;
}
::-webkit-scrollbar {
  width: 0 !important;height: 0;
}

body{
  font-family:'Microsoft YaHei';
}
.wrap{
  position: fixed;
  left:0;
  top:0;
  width:100%;
  height:100%;
  /* background: radial-gradient(#f3fbfe, #e4f5fd, #8fd5f4); */
  /* background:#8fd5f4; */
  /* background: linear-gradient(#6bc6ee, #fff); */
  background:#fff;

}
.search_form{
  width:640px;
  /*margin-top: 20px;*/
  margin:50px auto 0;
}
.logo img{
  display:block;
  margin:0 auto;
}
.form_group{
  width:640px;
  height:40px;
  margin-top:45px;
}
.input_txt{
  width:538px;
  height:38px;
  padding:0px;
  float:left;
  border:1px solid #41a1cb;
  outline:none;
  text-indent:10px;
}

.input_sub{
  width:100px;
  height:40px;
  border:0px;
  float: left;
  background-color: #41a1cb;
  color:#fff;
  font-size:10px;
  outline:none;
  cursor: pointer;
  position: relative;
}
.input_sub.loading::before{
  content:'';
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  /*background: url('src/assets/line.png');*/
}

.hotkey{
  margin:30px 0 0 20px;
}

.hotkey a{
  font-size:14px;
  color:#666;
  padding-right:15px;
}
.weather_list{
  height:200px;
  text-align:center;
  margin-top:50px;
  font-size:0px;
}
.weather_list li{
  display:inline-block;
  width:140px;
  height:200px;
  padding:0 10px;
  overflow: hidden;
  position: relative;
  /*background:url('src/assets/loading.gif') right center no-repeat;*/
  background-size: 1px 130px;
}

.weather_list li:last-child{
  background:none;
}

/* .weather_list .col02{
    background-color: rgba(65, 165, 158, 0.8);
}
.weather_list .col03{
    background-color: rgba(94, 194, 237, 0.8);
}
.weather_list .col04{
    background-color: rgba(69, 137, 176, 0.8);
}
.weather_list .col05{
    background-color: rgba(118, 113, 223, 0.8);
} */


.info_date{
  width:100%;
  height:40px;
  line-height:40px;
  color:#999;
  font-size:14px;
  left:0px;
  bottom:0px;
  margin-top: 15px;
}
.info_date b{
  float: left;
  margin-left:15px;
}

.info_type span{
  color:#fda252;
  font-size:30px;
  line-height:80px;
}
.info_temp{
  font-size:14px;
  color:#fda252;
}
.info_temp b{
  font-size:13px;
}
.tem .iconfont {
  font-size: 50px;
}
body,ul,h1,h2,h3,h4,h5,h6{
  margin: 0;
  padding: 0;
}
h1,h2,h3,h4,h5,h6{
  font-size:100%;
  font-weight:normal;
}
a{
  text-decoration:none;
}
ul{
  list-style:none;
}
img{
  border:0px;
}

/* 清除浮动，解决margin-top塌陷 */
.clearfix:before,.clearfix:after{
  content:'';
  display:table;
}
.clearfix:after{
  clear:both;
}
.clearfix{
  zoom:1;
}

.fl{
  float:left;
}
.fr{
  float:right;
}


</style>