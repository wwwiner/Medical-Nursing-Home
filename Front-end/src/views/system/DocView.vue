<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>门诊医生</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/index/doctorView' }"
        >诊断数据</el-breadcrumb-item
      >
    </el-breadcrumb>
    <el-form
      :inline="true"
      :model="formInline"
      class="demo-form-inline"
      style="margin-top: 50px; line-height: 10px"
    >
      <el-form-item label="医生姓名">
        <el-input
          v-model="formInline.docName"
          placeholder="医生姓名"
        ></el-input>
      </el-form-item>
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
    <el-card class="box-card-docView">
      <el-table
        border
        text-align:
        center
        :cell-style="{ padding: '2px'}"
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
        <el-table-column fixed="right" label="查看详情">
          <template slot-scope="scope">
            <el-button
              el-button
              type="primary"
              icon="el-icon-search"
              circle
              @click="diagnosis(scope.row)"
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
        docName: "",
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
        .post("/register/selectDoctorDiagnosisAll", {
          pageNum: pageNum,
          docName: this.formInline.docName,
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
    diagnosis(row) {
      this.$router.push({
        path: "/index/toDocView",
        query: { row: row },
      });
    },
    patientRegister() {
      //axios查询诊断数据请求
      this.$axios
        .post("/register/selectDoctorDiagnosisAll", {})
        .then((Response) => {
          this.tableData = Response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    },
    handleSelectionChange(val) {
      this.select(pageNumber);
    },
    resetForm() {
      location.reload();
    },
    paging(pageNum) {
      //axios查询科室列表请求(分页无参)
      this.$axios
        .post("/register/selectDoctorDiagnosisAll", {
          pageNum: pageNum,
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
    handleCurrentChange(pageNumber) {
      this.select(pageNumber);
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

.box-card-docView {
  margin-top: 0px;
  width: 1280px;
}
</style>