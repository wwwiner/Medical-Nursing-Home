<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统信息</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/index/medicalRecord' }"
        >病历管理</el-breadcrumb-item
      >
    </el-breadcrumb>
    <el-form
      :inline="true"
      :model="formInline"
      class="demo-form-inline"
      style="margin-top: 50px; line-height: 10px"
    >
      <el-form-item label="预约科室名称">
        <el-select
          v-model="formInline.deptCategoryName"
          placeholder="预约科室名称"
        >
          <el-option
            v-for="departmentClassification in departmentClassification"
            :key="departmentClassification.deptCategoryName"
            :lable="departmentClassification.deptCategoryName"
            :value="departmentClassification.deptCategoryName"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="状态">
        <el-select v-model="formInline.status" placeholder="状态">
          <el-option lable="正在处理" value="正在处理"></el-option>
          <el-option
            lable="诊断完成，待缴费"
            value="诊断完成，待缴费"
          ></el-option>
          <el-option lable="已缴费" value="已缴费"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" plain @click="select">查询</el-button>

        <el-button type="danger" plain @click="resetForm"
          >重置</el-button
        ></el-form-item
      >
    </el-form>
    <el-card class="box-card-record">
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
        <el-table-column prop="status" label="状态"> 
          <template scope="scope">
          <span v-if="scope.row.status === '正在处理'" style="color: orange">正在处理</span>
          <span v-if="scope.row.status === '诊断完成，待缴费'" style="color: #19b46d">{{scope.row.status}}</span>
          <span v-if="scope.row.status.indexOf('已缴费')>=0" style="color: #409eff">{{scope.row.status}}</span>
          </template>
        </el-table-column>
        <el-table-column fixed="right" label="查看诊断结果/退号">
          <template slot-scope="scope">
            <el-button
              el-button
              type="success"
              icon="el-icon-tickets"
              circle
              @click="medicalResult(scope.row)"
              size="small"
            ></el-button>
            <el-button
              el-button
              type="danger"
              icon="el-icon-delete"
              circle
              @click="deleteRegister(scope.row)"
              size="small"
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-pagination
      background
      layout="prev, pager, next,total"
      :total="departmentPageDto.totalRow"
      :page-size="10"
      style="position: absolute; margin-left: 380px; margin-top: 30px"
      @current-change="handleCurrentChange"
    >
    </el-pagination>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData: [],
      user: {},
      formInline: {
        deptCategoryName: "",
        status: "",
      },
      departmentClassification: [],
      pageTotal: "",
      departmentPageDto: {},
    };
  },
  created() {
    this.user = this.$getSessionStorage("user");
    //this.patientRegister();
    this.selectDepartmentClassification();
    this.paging(1);
  },
  methods: {
    select(pageNum) {
      var statusResult = this.formInline.status;
      if (statusResult.indexOf("已缴费") >= 0) {
        statusResult = "已缴费";
      }
      //axios查询科室列表请求((分页有参))
      var a = isNaN(pageNum);
      console.log(a);
      if (isNaN(pageNum) == true) {
        pageNum = 1;
      }
      this.$axios
        .post("/register/selectPatientRegisterByUserName", {
          pageNum: pageNum,
          userAccount: this.user.userName,
          deptCategoryName: this.formInline.deptCategoryName,
          status: statusResult,
        })
        .then((Response) => {
          this.tableData = Response.data.list;
          this.departmentPageDto = Response.data;
          console.log(this.departmentPageDto);
        })
        .catch((error) => {
          console.error(error);
        });
    },
    medicalResult(row) {
      console.log(row.status);
      if (row.status == "正在处理") {
        alert("您的病历还未处理完成，请耐心等待");
      } else {
        this.$router.push({
          path: "/index/toMedicalRecord",
          query: { row: row },
        });
      }
    },
    patientRegister() {
      //axios查询当前登陆人病历请求
      this.$axios
        .post("/register/selectPatientRegisterByUserName", {
          userName: this.user.userName,
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
      if (row.status == "正在处理") {
        this.$confirm("确认退号？")
          .then((_) => {
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
          })
          .catch((_) => {});
      } else {
        alert("您的挂号已被处理，无法退号");
      }
    },
    selectDepartmentClassification() {
      //axios查询科室分类请求
      this.$axios
        .post("/department/selectDepartmentClassification")
        .then((Response) => {
          this.departmentClassification = Response.data;
          console.log(this.departmentClassification);
        })
        .catch((error) => {
          console.error(error);
        });
    },
    resetForm() {
      location.reload();
    },
    // handleSelectionChange(val) {
    //   this.select(pageNumber);
    // },
    paging(pageNum) {
      //axios查询科室列表请求(分页无参)
      this.$axios
        .post("/register/selectPatientRegisterByUserName", {
          pageNum: pageNum,
          userAccount: this.user.userName,
        })
        .then((Response) => {
          this.tableData = Response.data.list;
          this.departmentPageDto = Response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    },
    handleCurrentChange(pageNumber) {
      this.select(pageNumber);
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

.box-card-record {
  margin-top: 0px;
  width: 1280px;
}
</style>