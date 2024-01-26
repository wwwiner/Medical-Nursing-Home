<template>
  <el-container style="height: 800px; display: flex; flex-direction: column">
    <el-header style="flex: 0 0 60px">
      <nobr>
        <i class="el-icon-house" style="display: inline;"></i>
        <h1 style="display: inline; font-size: 25px">老人之家--医养结合养老院</h1>
<!--        <img src="../assets/logo.jpg" alt="" height="50px"/>-->
<!--        <h1 style="display: inline; font-size: 25px;">老人之家</h1>-->
        <p
          class="account"
          style="display: inline; margin-left: 760px; font-size: 18px"
          @click="dialogVisible = true"
        >
          欢迎您:{{ user.realName }}
        </p>
        <el-row class="avatar">
          <!-- <el-col :span="12" @click="go">
            <div class="demo-basic--circle" @click="go">
              <div class="block" @click="go">
                <el-avatar
                  shape="square"
                  :size="50"
                  :src="squareUrl"
                  :preview-src-list="srcList"
                ></el-avatar>
              </div>
            </div>
          </el-col> -->
          <el-image
            style="width: 50px; height: 50px"
            :src="squareUrl"
            :preview-src-list="srcList"
          >
          </el-image>
<!--          <el-image-->
<!--              style="width: 50px; height: 50px"-->
<!--              :src="getImgUrl(row.image)"-->
<!--              :preview-src-list="srcList"-->
<!--          >-->
<!--          </el-image>-->
        </el-row>
      </nobr>
      <div>
      </div>
    </el-header>
    <el-container style="flex: 1">
      <el-aside width="200px">
        <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          background-color="#8FBC9F"
          text-color="##303133"
          active-text-color="#ffd04b"
          router
        >
          <router-link to="/index">
          <el-menu-item index="0">
            <template slot="title">
              <i class="el-icon-s-home"></i>
              <span>首页导航</span>
            </template>

          </el-menu-item>
          </router-link>


          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-first-aid-kit"></i>
              <span>公告展示</span>
            </template>
            <el-menu-item index="/index/WeatherForecast">天气查看</el-menu-item>
            <el-menu-item index="/index/BulletinBoard">留言互动</el-menu-item>
          </el-submenu>


          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-first-aid-kit"></i>
              <span>现场预约</span>
            </template>
            <el-menu-item index="/index/PatientRegister">挂号收费</el-menu-item>
            <el-menu-item index="/index/medicalRecord">预约状态</el-menu-item>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span>门诊医生</span>
            </template>
            <el-menu-item @click="toDocter">医生诊断</el-menu-item>
            <el-menu-item @click="toDocterView">诊断数据</el-menu-item>
          </el-submenu>
          <el-submenu index="4">
            <template slot="title">
              <i class="el-icon-document"></i>
              <span>药房管理</span>
            </template>
            <el-menu-item index="/index/drugAdministration"
              >药品管理</el-menu-item
            >
          </el-submenu>

          <el-submenu index="5">
            <template slot="title">
              <i class="el-icon-office-building"></i>
              <span>入住管理</span>
            </template>
            <el-menu-item index="/index/inWard">入住信息</el-menu-item>
            <el-menu-item index="/index/ward">房间查看</el-menu-item>
          </el-submenu>

          <el-submenu index="6">
            <template slot="title">
              <i class="el-icon-setting"></i>
              <span>系统管理</span>
            </template>
            <el-menu-item index="/index/deptartmentList">科室管理</el-menu-item>
            <el-menu-item @click="toUserList">用户管理</el-menu-item>
            <el-menu-item index="/index/rechargeAccount">充值中心</el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>

        <router-view />

      </el-main>
    </el-container>
    <el-footer style="flex: 0 0 60px; color: #333"
      >&copy; 版权所有mwy</el-footer
    >
    <el-dialog title="提示" :visible.sync="dialogVisible" width="40%">
      <span>
        <h1>欢迎您：{{ user.realName }}，您的账号信息为：</h1>
        <p>用户编号：{{ user.id }}</p>
        <p>用户账号：{{ user.userName }}</p>
        <p>用户密码：{{ user.password }}</p>
        <p>用户姓名：{{ user.realName }}</p>
        <p>用户类型：{{ user.userType }}</p>
        <span>账户余额：{{ user.money }} </span><span>元</span>
      </span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="updateAccount"> 修改账号信息</el-button>
        <el-button @click="drawer = true" type="success"> 上传头像</el-button>
        <el-button @click="logout" type="danger"> 退出登录</el-button>
        <el-button type="primary" @click="dialogVisible = false"
          >确 定</el-button
        >
      </span>
    </el-dialog>
    <el-drawer title="上传头像" :visible.sync="drawer" :with-header="false">
      <h1 style="font-size: 30px; margin-top: 20px; margin-left: 40px">
        上传头像
      </h1>
      <el-upload
        class="avatar-uploader"
        action="./assets"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload"

      >
        <img v-if="imageUrl" :src="imageUrl" class="avatarUpdate" />
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
    </el-drawer>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      user: {},
      dialogVisible: false,
      squareUrl: "",
      srcList: [],
      drawer: false,
      imageUrl: "",
      // name:'',
      // data(){
      //   return {}
      // }
    };
  },
  created() {
    this.user = this.$getSessionStorage("user");
    this.selectAvatarlink();
  },
  methods: {
    //退出登录
    logout() {
      this.$confirm("确认退出登录？")
          .then((_) => {
            this.$setSessionStorage("user", "");
            console.log("已清空session，正在跳转到登录页面");
            this.$router.push({
              path: "/login",
            });
          })
          .catch((_) => {
          });
    },
    //修改账号信息
    updateAccount() {
      this.$confirm("确认跳转到账号信息修改页面？")
          .then((_) => {
            this.$setSessionStorage("user", "");
            console.log("正在跳转到账号信息修改页面");
            this.$router.push({
              path: "/updateAccount",
            });
          })
          .catch((_) => {
          });
    },
    //充值中心
    rechargeAccount(){
      this.$confirm("确认跳转到账号充值页面？")
          .then((_) => {this.$setSessionStorage("user", "");
            console.log("正在跳转到账号充值页面");
            this.$router.push({
              path: "/rechargeAccount",
            });
          })
          .catch((_) => {
          });
    },
    toUserList() {
      if (this.user.userType === "管理员") {
        this.$router.push({
          path: "/index/userList",
        });
      } else {
        alert("很抱歉，您没有权限查看用户管理界面");
      }
    },
    toDocter() {
      console.log(this.user.userType);
      //身份验证
      if (
          this.user.userType.indexOf("医生") >= 0 ||
          this.user.userType.indexOf("主任") >= 0 ||
          this.user.userType.indexOf("管理员") >= 0
      ) {
        this.$router.push({
          path: "/index/doctorDiagnosis",
        });
      } else {
        alert(
            "您的身份为" +
            this.user.userType +

            ",无权限访问医生诊断页面"
        );
      }
    },
    toDocterView() {
      //身份验证
      if (
          this.user.userType.indexOf("医生") >= 0 ||
          this.user.userType.indexOf("主任") >= 0 ||
          this.user.userType.indexOf("管理员") >= 0
      ) {
        this.$router.push({
          path: "/index/docView",
        });
      } else {
        alert(
            "您的身份为" +

            this.user.userType +

            ",无权限访问诊断数据页面"
        );
      }
    },
    toWard() {
      if (this.user.userType.indexOf("管理员") >= 0) {
        this.$router.push({
          path: "/index/ward",
        });
      }
    },
    //查询登陆人头像链接
    selectAvatarlink() {
      this.$axios
          .post("/user/selectAvatarlinkByUserAccount", {
            userName: this.user.userName,
          })
          .then((Response) => {
            // debugger

            const avatarlink = Response.data;
            const url = require("@/assets/" + avatarlink);
            this.squareUrl = url;
            this.srcList.push(url);
            console.log(this.srcList);
          })
          .catch((error) => {
            console.error(error);
          });
    },
    handleAvatarSuccess(res, file) {
      // this.imageUrl = `http://localhost:8082/user/download?name=${res}`
      this.imageUrl = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }

      return  isLt2M;
    },
    // getImgUrl(img) {
    //   //这里的路径注意自己的后端Controller路径
    //   return `http://localhost:8082/user/download?name=${img}`
    // }

    //   init({BMap, map}){
    //     this.map = map
    //     var point = new BMap.Point(108.910752, 34.237372)  //设置城市经纬度坐标
    //     map.centerAndZoom(point, 15)    //
    //     map.setCurrentCity('西安')   // 设置地图显示的城市 此项是必须设置的
    //     map.enableScrollWheelZoom(true)    //鼠标滚动缩放
    //
    //     // #for(var i in mapdata) {
    //     // #    var pt = new BMap.Point(mapdata[i].longitude, mapdata[i].latitude )   //经纬度
    //     // #    var myicon = new BMap.Icon('图片路径', new BMap.Size(32, 32))   //设置对应图片和图片大小
    //     // #    var marker2 = new BMap.Marker(pt, { icon: myicon })  //创建标注
    //     // #    map.addOverLay(marker2)
    //     // #}
    //   }
    // },
    // mounted(){
    //   this.init()
    // }

  }
};
</script>

<style scoped>
.el-icon-house {
  text-align: center;
  line-height: 60px;
  font-size: 25px;
}
/*.el-container{*/
/*  width: 1080px;*/
/*}*/
/*.el-header,*/
/*.el-footer {*/
/*  background-color: #F0E68C;*/
/*  color: #333;*/
/*  text-align: center;*/
/*  line-height: 60px;*/

/*  display: flex;*/
/*  flex-direction: column;*/
/*  flex: 1;*/
/*}*/

.el-aside {
  background-color: #8FBC9F;
  color: #333;
  text-align: center;
  line-height: 180px;
  width: 210px;
  height: 730px;

}

.el-main {
  background-color: #FFF;
  color: #333;
  /* text-align: center; */
  line-height: 0px;
}

.account:hover {
  color: #FFF;
  text-decoration: none;
  cursor: pointer;
}

.account:active {
  color: rgb(119, 255, 255);
  text-decoration: rgb(119, 255, 255);
  cursor: pointer;
}


.el-header[data-v-23543608]{
  background-color: #EEE8AA;
  color: #333;
  text-align: center;
  line-height: 60px;
  display: flex;
  flex-direction: column;
  flex: 1;
  /*align-items: center;*/

}
.el-footer[data-v-23543608]{
  background-color: #EEE8AA;
  color: #333;
  text-align: center;
  line-height: 60px;
  display: flex;
  flex-direction: column;
  flex: 1;
  margin-top: 0px;
  font-size: 18px;
}
.avatar {
  position: absolute;
  margin-left: 1350px;
  margin-top: -58px;
}
.v-step-0 {
  position: absolute;
  left: 1650px;
  margin-top: -58px;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatarUpdate {
  width: 178px;
  height: 178px;
  display: block;
}
/*.el-dialog{*/
/*  position: absolute;*/
/*  left: 1000px;*/
/*  margin-top: -58px;*/

/*}*/


/*.dv { width: 500px; height: 500px; }*/
</style>


