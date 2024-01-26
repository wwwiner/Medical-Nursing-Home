<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>门诊医生</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/index/doctorDiagnosis' }"
        >医生诊断</el-breadcrumb-item
      >
    </el-breadcrumb>

    <el-card class="box-cardd">
      <el-table
        border
        text-align:
        center
        :cell-style="{ padding: '2px' }"
        :data="tableData"
        style="width: 100%"
        @selection-change="handleSelectionChange"
      >
        <el-table-column prop="id" label="病历编号"> </el-table-column>
        <el-table-column prop="userAccount" label="病人账号"> </el-table-column>
        <el-table-column prop="userName" label="病人姓名"> </el-table-column>
        <el-table-column prop="docName" label="预约医生"> </el-table-column>
        <el-table-column prop="deptCategoryName" label="预约科室类型">
        </el-table-column>
        <el-table-column prop="deptName" label="预约科室名称">
        </el-table-column>
        <el-table-column prop="status" label="状态"> </el-table-column>
        <el-table-column fixed="right" label="诊断">
          <template slot-scope="scope">
            <el-button
              el-button
              type="primary"
              icon="el-icon-edit"
              circle
              @click="diagnosis(scope.row)"
              size="small"
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData: [],
      user: {},
    };
  },
  created() {
    this.user = this.$getSessionStorage("user");
    this.patientRegister();
  },
  methods: {
    diagnosis(row) {
      console.log(row);
      this.$confirm("确认诊断" + " [ " + row.userName + " ] " + "病人？")
        .then((_) => {
          //axios医生信息入挂号表请求
          this.$axios
            .post("/register/addDoctorInformationById", {
              id: row.id,
              docAccount: this.user.userName,
              docType: this.user.userType,
            })
            .then((Response) => {
              if (Response.data == "SUCCESS") {
                console("医生信息已录入挂号表");
              }
            })
            .catch((error) => {
              console.error(error);
            });
          this.$router.push({
            path: "/index/toDoctorDiagnosis",
            query: { row: row },
          });
        })
        .catch((_) => {});
    },
    patientRegister() {
      //axios查询当前登陆人被预约请求
      this.$axios
        .post("/register/selectDoctorDiagnosisByDocAccount", {
          docName: this.user.realName,
        })
        .then((Response) => {
          this.tableData = Response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
      console.log(val);
    },
    deleteRegister(row) {
      console.log(row.id);
      //axios删除此条病历请求
      this.$axios
        .post("/register/deletePatientRegisterById", {
          id: row.id,
        })
        .then((Response) => {
          if (Response.data == "SUCCESS") {
            alert("已取消预约" + " [ " + row.docName + " ] " + "医生");
          }
        })
        .catch((error) => {
          console.error(error);
          alert("取消预约失败");
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

.box-cardd {
  margin-top: 30px;
  width: 1280px;
}
</style>