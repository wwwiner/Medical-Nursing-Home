<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>住院管理</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/index/inWard' }"
        >住院办理</el-breadcrumb-item
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
      <el-form-item label="诊断科室名称">
        <el-select
          v-model="formInline.deptCategoryName"
          placeholder="诊断科室名称"
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
          <el-option lable="待用户选择" value="待用户选择"></el-option>
          <el-option lable="住院中" value="住院中"></el-option>
          <el-option lable="已出院" value="已出院"></el-option>
          <el-option lable="拒绝住院" value="拒绝住院"></el-option>
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
        :cell-style="{ padding: '2px' }"
        :data="tableData"
        style="width: 100%"
        @selection-change="handleSelectionChange"
      >
        <el-table-column prop="id" label="住院编号"> </el-table-column>
        <el-table-column prop="registerId" label="病历编号"> </el-table-column>
        <el-table-column prop="userAccount" label="老人账号"> </el-table-column>
        <el-table-column prop="userName" label="老人姓名"> </el-table-column>
        <el-table-column prop="disease" label="诊断结果"> </el-table-column>
        <el-table-column prop="docName" label="负责医生"> </el-table-column>
        <el-table-column prop="deptType" label="诊断科室类型">
        </el-table-column>
        <el-table-column prop="status" label="状态"> </el-table-column>
        <el-table-column fixed="right" label="办理住院/拒绝住院/出院" width="170">
          <template slot-scope="scope">
            <el-button
              el-button
              type="primary"
              icon="el-icon-search"
              circle
              @click="InWard(scope.row)"
              size="small"
              :disabled="scope.row.status != '待用户选择'"
            ></el-button>
            <el-button
              el-button
              type="danger"
              icon="el-icon-delete"
              circle
              @click="notInWard(scope.row)"
              size="small"
              :disabled="scope.row.status != '待用户选择'"
            ></el-button>
            <el-button
              el-button
              type="success"
              icon="el-icon-check"
              circle
              @click="outWard(scope.row)"
              size="small"
              :disabled="scope.row.status != '住院中'"
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
    this.selectDepartmentClassification();
    this.paging(1);
  },
  methods: {
    select(pageNum) {
      //axios查询待住院列表请求(分页有参)
      if (isNaN(pageNum) == true) {
        pageNum = 1;
      }
      this.$axios
        .post("/hospitalization/selectHospitalizationListByUserAccount", {
          pageNum: pageNum,
          docName: this.formInline.docName,
          deptCategoryName: this.formInline.deptCategoryName,
          status: this.formInline.status,
          userAccount: this.user.userName,
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
    handleSelectionChange(val) {
      this.select(pageNumber);
    },
    resetForm() {
      location.reload();
    },
    paging(pageNum) {
      //axios查询待住院列表请求(分页无参)
      this.$axios
        .post("/hospitalization/selectHospitalizationListByUserAccount", {
          pageNum: pageNum,
          userAccount: this.user.userName,
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
    notInWard(row) {
      this.$confirm("确认拒绝住院？")
        .then((_) => {
          //axios拒绝住院请求
          this.$axios
            .post("/hospitalization/notInWardById", {
              id: row.id,
            })
            .then((Response) => {
              if (Response.data == "SUCCESS") {
                alert(
                  "成功拒绝" +
                    " [ " +
                    row.docName +
                    " ] " +
                    "医生的住院请求"
                );
                location.reload();
              } else {
                alert("拒绝住院请求失败！");
              }
            })
            .catch((error) => {
              console.error(error);
            });
        })
        .catch((_) => {});
    },
    InWard(row) {
        this.$confirm("确认前往住院办理？")
        .then((_) => {
            this.$router.push({
            path: "/index/toInWard",
            query: { row: row },
          });
        })
        .catch((_) => {});
        
    },
    outWard(row){
      this.$confirm("确认出院？")
        .then((_) => {
          console.log(row.id)
          //axios出院请求
          this.$axios
            .post("/ward/outWardById", {
              id: row.id,
              userAccount: row.userAccount,
            })
            .then((Response) => {
              if (Response.data == "SUCCESS") {
                alert("出院成功，欢迎下次光临！");
                location.reload();
              } else {
                alert("出院请求失败！");
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

.box-card-docView {
  margin-top: 0px;
  width: 1280px;
}
</style>