<template>
  <div >
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>挂号收费</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/index/medicalRecord' }"
        >病历管理</el-breadcrumb-item
      >
      <el-breadcrumb-item :to="{ path: '/index/toMedicalRecord' }"
        >诊断结果</el-breadcrumb-item
      >
    </el-breadcrumb>
    <el-card class="box-card">
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="科室分类">
          <el-input v-model="formInline.deptCategoryName" readonly></el-input>
        </el-form-item>
        <el-form-item label="科室名称" style="margin-left: 50px">
          <el-input v-model="formInline.deptName" readonly></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="病历编号" >
          <el-input v-model="formInline.id" readonly></el-input>
        </el-form-item>
        <el-form-item label="病人账号" style="margin-left: 50px">
          <el-input v-model="formInline.userAccount" readonly></el-input>
        </el-form-item>
        <el-form-item label="病人姓名" style="margin-left: 50px">
          <el-input v-model="formInline.userName" readonly></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="医生账号" >
          <el-input v-model="formInline.docAccount" readonly></el-input>
        </el-form-item>
        <el-form-item label="医生姓名" style="margin-left: 50px">
          <el-input v-model="formInline.docName" readonly></el-input>
        </el-form-item>
        <el-form-item label="医生类型" style="margin-left: 50px">
          <el-input v-model="formInline.docType" readonly></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="病情自述" >
          <el-input
            type="textarea"
            v-model="formInline.registerDepartment"
            style="width: 600px"
            readonly
          ></el-input>
        </el-form-item>
      </el-form>
       <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="诊断结果" style="width: 500px">
          <el-input v-model="formInline.disease" readonly></el-input>
        </el-form-item>
        <el-form-item label="是否建议住院" >
          <el-input v-model="formInline.isHosAdvice" readonly ></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="医生开药">
          <el-input
            type="textarea"
            v-model="formInline.drug"
            style="width: 600px"
            readonly
          ></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="诊断说明" >
          <el-input
            type="textarea"
            v-model="formInline.docDiagnostic"
            style="width: 600px"
            readonly
          ></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="缴费状态" >
          <el-input v-model="formInline.status" readonly></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" class="demo-form-inline">
        <el-row>
          <el-button
            type="danger"
            round
            style="margin-left: 350px"
            @click="goBack"
            >返回</el-button
          >
          <el-button
            type="primary"
            round
            style="margin-left: 30px"
            @click="selectDrugPriceList"
            >查看费用</el-button
          >
          <el-button
            type="success"
            round
            style="margin-left: 30px"
            @click="payMoney"
            :disabled="this.formInline.status.indexOf('已缴费') >= 0"
            >缴费</el-button
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
        deptName: "",
        id: "",
        userAccount: "",
        userName: "",
        docAccount: "",
        docName: "",
        docType: "",
        registerDepartment: "",
        drugType: "",
        drugName: "",
        docDiagnostic: "",
        drug: "",
        status: "",
        disease: "",
        isHosAdvice: "",
      },
      drugPriceList: [],
      alertResult: "",
      user: {},
    };
  },
  created() {
    this.selectPatientRegisterById();
  },
  updated() {
    this.diagnosisIsOk();
  },
  methods: {
    selectPatientRegisterById() {
      console.log(this.$route.query.row.id);
      //axios查询病历请求
      this.$axios
        .post("/register/selectPatientRegisterById", {
          id: this.$route.query.row.id,
        })
        .then((Response) => {
          this.formInline = Response.data[0];
        })
        .catch((error) => {
          console.error(error);
        });
    },
    selectDrugType() {
      //axios查询药品类型请求
      this.$axios
        .post("/drug/selectDrugType")
        .then((Response) => {
          this.drugType = Response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    },
    drug_1(drug) {
      console.log(drug.drugType);
      this.selectDrugName(drug.drugType);
    },
    goBack() {
      this.$confirm("确认返回到 [ 病历管理 ] 界面？")
        .then((_) => {
          this.$router.push({
            path: "/index/medicalRecord",
          });
        })
        .catch((_) => {});
    },
    diagnosisIsOk() {
      var drugList = this.formInline.drug;
      //axios查询药品价格请求
      this.$axios
        .post("/register/payDrugPrice", {
          drug: drugList,
        })
        .then((Response) => {
          var resultList = Response.data;
          var alertResult = "药品价格：";
          for (var j = 0; j < resultList.length; j++) {
            if (resultList[j] != null) {
              alertResult = alertResult + "\n" + resultList[j];
            }
          }
          this.alertResult = alertResult;
        })
        .catch((error) => {
          console.error(error);
        });
      // var tip = this.alertResult;
      // this.$confirm(tip)
      //   .then((_) => {})
      //   .catch((_) => {});
    },
    selectDrugPriceList() {
      console.log(this.alertResult);
      var tip = this.alertResult;
      this.$confirm(tip)
        .then((_) => {})
        .catch((_) => {});
    },
    payMoney() {
      this.user = this.$getSessionStorage("user");
      this.$confirm("确认缴费？")
        .then((_) => {
          //axios缴费请求
          var drugList = this.formInline.drug;
          this.$axios
            .post("/register/pay", {
              id: this.$route.query.row.id,
              drug: drugList,
              userAccount: this.user.userName,
              userName: this.user.realName,
            })
            .then((Response) => {
              if (Response.data.indexOf("缴费成功") >= 0) {
                alert(Response.data);
              } else {
                alert(Response.data);
              }
            })
            .catch((error) => {
              console.error(error);
            });
          this.$router.push({
            path: "/index/medicalRecord",
          });
          //location.reload();
        })
        .catch((_) => {});
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

.box-card {
  width: 1080px;

}
</style>