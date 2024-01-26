<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>门诊医生</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/index/doctorDiagnosis' }"
        >医生诊断</el-breadcrumb-item
      >
      <el-breadcrumb-item :to="{ path: '/index/toDoctorDiagnosis' }"
        >详细诊断</el-breadcrumb-item
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
        <el-form-item label="病历编号">
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
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="病情自述">
          <el-input
            type="textarea"
            v-model="formInline.registerDepartment"
            style="width: 600px"
            readonly
          ></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="诊断结果" >
          <el-input v-model="formInline.disease"></el-input>
        </el-form-item>
        <el-form-item label="允许入住">
          <el-switch v-model="formInline.isHosAdvice"></el-switch>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="选择药物">
          <el-select v-model="formInline.drugType" placeholder="药品类型">
            <el-option
              v-for="drug in drugType"
              :key="drug.id"
              :label="drug.drugType"
              :value="drug.drugType"
              @click.native="drug_1(drug)"
            >
            </el-option>
          </el-select>
          <el-select
            v-model="formInline.drugName"
            multiple
            placeholder="药品名称"
          >
            <el-option
              v-for="drug in drugName"
              :key="drug.id"
              :label="drug.drugName"
              :value="drug.drugName"
            >
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="诊断说明">
          <el-input
            type="textarea"
            v-model="formInline.docDiagnostic"
            style="width: 600px"
          ></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="drug" class="demo-form-inline">
        <el-row>
          <el-button
            type="danger"
            round
            style="margin-left: 450px"
            @click="goBack"
            >取消</el-button
          >
          <el-button
            type="success"
            round
            style="margin-left: 30px"
            @click="diagnosisIsOk"
            >诊断完成</el-button
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
        disease: "",
        isHosAdvice: "",
      },
      drug: {
        drugType: "",
        drugName: "",
      },
      drugType: [],
      drugName: [],
    };
  },
  created() {
    this.selectDrugType();
    this.selectPatientRegisterById();
    console.log(this.$route.query.row);
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
    selectDrugName(drugType) {
      //axios根据药品分类查询药品名称请求
      this.$axios
        .post("/drug/selectDrugNameByDrugType", {
          drugType: drugType,
        })
        .then((Response) => {
          this.drugName = Response.data;
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
      this.$confirm("确认返回到 [ 医生诊断 ] 界面？")
        .then((_) => {
          this.$router.push({
            path: "/index/doctorDiagnosis",
          });
        })
        .catch((_) => {});
    },
    diagnosisIsOk() {
      this.$confirm("确认提交诊断？")
        .then((_) => {
          var drugList = this.formInline.drugName;
          var drugResult = "";
          for (var j = 0; j < drugList.length; j++) {
            drugResult = drugResult + "," + drugList[j];
          }
          console.log(drugResult);
          //axios提交诊断请求
          this.$axios
            .post("/register/diagnosisIsOk", {
              id: this.$route.query.row.id,
              userAccount: this.$route.query.row.userAccount,
              userName: this.$route.query.row.userName,
              docAccount: this.$route.query.row.docAccount,
              docName: this.$route.query.row.docName,
              docType: this.$route.query.row.docType,
              docDiagnostic: this.formInline.docDiagnostic,
              disease: this.formInline.disease,
              isHosAdvice: this.formInline.isHosAdvice,
              deptName: this.$route.query.row.deptName,
              deptCategoryName: this.$route.query.row.deptCategoryName,
              drug: drugResult,
            })
            .then((Response) => {
              if ((Response.data = "SUCCESS")) {
                alert("提交诊断成功");
                this.$router.push({
                  path: "/index/doctorDiagnosis",
                });
              } else {
                alert("提交诊断失败");
              }
            })
            .catch((error) => {
              console.error(error);
            });
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
  margin-top: 30px;
}
</style>