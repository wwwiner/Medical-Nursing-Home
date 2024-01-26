<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>住院管理</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/index/inWaed' }"
        >住院信息</el-breadcrumb-item
      >
      <el-breadcrumb-item :to="{ path: '/index/toInWaed' }"
        >住院办理</el-breadcrumb-item
      >
    </el-breadcrumb>
    <el-card class="box-card-toInWard">
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="住院编号">
          <el-input v-model="formInline.id" readonly></el-input>
        </el-form-item>
        <el-form-item label="病历编号" style="margin-left: 50px">
          <el-input v-model="formInline.registerId" readonly></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="科室分类">
          <el-input v-model="formInline.deptCategoryName" readonly></el-input>
        </el-form-item>
        <el-form-item label="科室名称" style="margin-left: 50px">
          <el-input v-model="formInline.deptType" readonly></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="老人账号">
          <el-input v-model="formInline.userAccount" readonly></el-input>
        </el-form-item>
        <el-form-item label="老人姓名" style="margin-left: 50px">
          <el-input v-model="formInline.userName" readonly></el-input>
        </el-form-item>
        <el-form-item label="诊断结果" style="margin-left: 50px">
          <el-input v-model="formInline.disease"></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="医生账号">
          <el-input v-model="formInline.docAccount" readonly></el-input>
        </el-form-item>
        <el-form-item label="医生姓名" style="margin-left: 50px">
          <el-input v-model="formInline.docName" readonly></el-input>
        </el-form-item>
        <el-form-item label="医生类型" style="margin-left: 50px">
          <el-input v-model="formInline.docType" readonly></el-input>
        </el-form-item>
      </el-form>
      <el-form
        :inline="true"
        :model="formInline"
        class="demo-form-inline"
        :rules="rules"
      >
        <el-form-item label="选择病房" prop="wardType">
          <el-select v-model="formInline.wardType" placeholder="病房类型">
            <el-option
              v-for="ward in wardType"
              :key="ward.id"
              :label="ward.wardType"
              :value="ward.wardType"
              @click.native="ward_1(ward)"
            >
            </el-option>
          </el-select>
          <el-select v-model="formInline.wardName" placeholder="病房名称">
            <el-option
              v-for="ward in wardName"
              :key="ward.id"
              :label="ward.wardName"
              :value="ward.wardName"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <baidu-map
          class="map"
          :center="{ lng: 113.3963, lat: 23.05351 }"
          :zoom="17"
        >
          <bm-marker
            :position="{ lng: 113.3963, lat: 23.05351 }"
            :dragging="true"
            animation="BMAP_ANIMATION_BOUNCE"
          >
          </bm-marker>
        </baidu-map>
      </el-form>
      <el-form
        :inline="true"
        :model="formInline"
        class="demo-form-inline"
        :rules="rules"
      >
        <el-form-item label="住院时长(月)" prop="continuedTime">
          <div class="block">
            <el-slider
              v-model="formInline.continuedTime"
              style="width: 450px"
              :max="30"
              show-stops
            ></el-slider>
          </div>
        </el-form-item>
      </el-form>
      <p style="position: absolute; margin-top: 13px">普通护理房</p>
      <el-progress
        :text-inside="true"
        :stroke-width="26"
        :percentage="wardRemainingProportion.pt"
        style="width: 300px; margin-left: 100px"
      ></el-progress>
      <p style="position: absolute; margin-top: 23px">高级护理房</p>
      <el-progress
        :text-inside="true"
        :stroke-width="24"
        :percentage="wardRemainingProportion.gj"
        status="success"
        style="width: 300px; margin-top: 10px; margin-left: 100px"
      ></el-progress>
      <p style="position: absolute; margin-top: 22px">临终护理房</p>
      <el-progress
        :text-inside="true"
        :stroke-width="22"
        :percentage="wardRemainingProportion.lz"
        status="warning"
        style="width: 300px; margin-top: 10px; margin-left: 100px"
      ></el-progress>
      <p style="position: absolute; margin-top: 22px">重症监护室</p>
      <el-progress
        :text-inside="true"
        :stroke-width="20"
        :percentage="wardRemainingProportion.icu"
        status="exception"
        style="width: 300px; margin-top: 10px; margin-left: 100px"
      ></el-progress>
      <el-form> </el-form>
      <el-form :inline="true" class="demo-form-inline" style="margin-top: 80px">
        <el-row>
          <el-button
            type="danger"
            round
            style="margin-left: 350px"
            @click="goBack"
            >返回</el-button
          >
          <el-button
            type="success"
            round
            style="margin-left: 30px"
            @click="InWard"
            >办理住院</el-button
          >
        </el-row>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      formInline: {
        deptCategoryName: "",
        deptType: "",
        id: "",
        registerId: "",
        userAccount: "",
        userName: "",
        docAccount: "",
        docName: "",
        docType: "",
        drug: "",
        status: "",
        disease: "",
        continuedTime: "",
        wardName: "",
        wardType: "",
      },
      drugPriceList: [],
      alertResult: "",
      user: {},
      ward: {
        wardType: "",
        wardName: "",
      },
      wardType: [],
      wardName: [],
      wardRemainingProportion: {
        pt: "",
        gj: "",
        lz: "",
        icu: "",
      },
      rules: {
        continuedTime: [{ required: true }],
        wardType: [{ required: true }],
        wardName: [{ required: true }],
      },
    };
  },
  created() {
    this.user = this.$getSessionStorage("user");
    //初始化表单赋值
    this.selectPatientRegisterById();
    //初始化查询病房分类（下拉选）
    this.selectWardType();
    //初始化查询病房占比（数据可视化）
    this.selectWardRemainingProportion();
    //console.log(this.wardRemainingProportion.PT)
  },
  updated() {},
  methods: {
    selectPatientRegisterById() {
      console.log(this.$route.query.row);
      //axios查询病历请求
      this.$axios
        .post("/hospitalization/selectHospitalizationById", {
          id: this.$route.query.row.id,
        })
        .then((Response) => {
          this.formInline = Response.data;
          this.formInline.docType = this.$route.query.row.deptType + "医生";
          this.formInline.docAccount = "***";
        })
        .catch((error) => {
          console.error(error);
        });
    },
    selectWardType() {
      //axios查询病房类型请求
      this.$axios
        .post("/ward/selectWardType")
        .then((Response) => {
          this.wardType = Response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    },
    selectWardName(wardType) {
      //axios查询病房名称请求
      this.$axios
        .post("/ward/selectWardNameByWardType", {
          wardType: wardType,
        })
        .then((Response) => {
          this.wardName = Response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    },
    ward_1(ward) {
      console.log(ward.wardType);
      this.selectWardName(ward.wardType);
    },
    goBack() {
      this.$confirm("确认返回到 [ 住院信息 ] 界面？")
        .then((_) => {
          this.$router.push({
            path: "/index/inWard",
          });
        })
        .catch((_) => {});
    },
    InWard() {
      console.log(this.formInline.wardName);
      var wardPrice;
      if (this.formInline.wardName.indexOf("普通") >= 0) {
        wardPrice = 100;
      } else if (this.formInline.wardName.indexOf("高级") >= 0) {
        wardPrice = 300;
      } else if (this.formInline.wardName.indexOf("临终") >= 0) {
        wardPrice = 500;
      } else if (this.formInline.wardName.indexOf("重症") >= 0) {
        wardPrice = 1000;
      }
      var resultPrice = wardPrice * this.formInline.continuedTime;
      this.user = this.$getSessionStorage("user");
      this.$confirm(
        "确认入住" +
          " [ " +
          this.formInline.wardName +
          " ] " +
          " [ " +
          this.formInline.continuedTime +
          " ] " +
          "月？\n " +
          "共需缴费" +
          resultPrice +
          "元"
      )
        .then((_) => {
          //axios住院请求
          this.$axios
            .post("/ward/inWard", {
              userAccount: this.user.userName,
              userName: this.user.realName,
              disease: this.formInline.disease,
              continuedTime: this.formInline.continuedTime,
              docName: this.formInline.docName,
              wardName: this.formInline.wardName,
              wardPrice: wardPrice,
              hosId: this.formInline.id,
            })
            .then((Response) => {
              if (Response.data == "SUCCESS") {
                alert(
                  "办理入住成功,缴费" +
                    " [ " +
                    resultPrice +
                    " ]" +"元，"+
                    "已返回住院信息界面"
                );
                this.$router.push({
                  path: "/index/inWard",
                });
                location.reload();
              } else if (Response.data.indexOf("余额不足") >= 0) {
                alert(Response.data);
              } else {
                alert("办理入住失败！");
              }
            })
            .catch((error) => {
              console.error(error);
            });
          this.$router.push({
            path: "/index/inward",
          });
          //location.reload();
        })
        .catch((_) => {});
    },
    formatTooltip(val) {
      return val / 100;
    },
    selectWardRemainingProportion() {
      //axios查询病房剩余占比（数据可视化）
      this.$axios
        .post("/ward/selectWardRemainingProportion")
        .then((Response) => {
          this.wardRemainingProportion = Response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>

<style>
.text {
  font-size: 14px;
}

.item {
  padding: 18px 0;
}

.box-card-toInWard {
  width: 1080px;
  height: 1020px;
  margin-top: 30px;
}

.map {
  position: absolute;
  width: 300px;
  height: 300px;
  margin-left: 630px;
}
</style>