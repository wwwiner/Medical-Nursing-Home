<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/index/userList' }"
        >用户管理</el-breadcrumb-item
      >
      <el-breadcrumb-item :to="{ path: '/index/salary' }"
        >转账管理</el-breadcrumb-item
      >
    </el-breadcrumb>
    <el-form
      :inline="true"
      :model="formInline"
      class="demo-form-inline"
      style="margin-top: 50px; line-height: 10px"
    >
      <el-form-item label="转账金额">
        <el-input v-model="formInline.money" placeholder="转账金额"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" plain @click="pay">转账</el-button>

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
        :cell-style="{ padding: '7px' }"
        :data="tableData"
        style="width: 100%"
        @selection-change="handleSelectionChange"
      >
        <el-table-column prop="id" label="转账编号"> </el-table-column>
        <el-table-column prop="userAccount" label="收款人账号">
        </el-table-column>
        <el-table-column prop="userName" label="收款人姓名"> </el-table-column>
        <el-table-column prop="transferAccount" label="转账人账号">
        </el-table-column>
        <el-table-column prop="transferName" label="转账人姓名">
        </el-table-column>
        <el-table-column prop="transferTime" label="转账日期" width="239">
        </el-table-column>
        <el-table-column prop="transferMoney" label="转账金额">
        </el-table-column>
        <el-table-column
          prop="allTransferMoney"
          label="转账总金额"
        ></el-table-column>
        <el-table-column prop="transferType" label="转账类型">
          <template scope="scope">
            <span v-if="scope.row.transferType === '工资发放'" style="color: rgb(255, 208, 75)">{{scope.row.transferType}}</span>
            <span v-if="scope.row.transferType === '住院缴费'" style="color: #409eff">{{scope.row.transferType}}</span>
            <span v-if="scope.row.transferType === '医疗缴费'" style="color: #19b46d">{{scope.row.transferType}}</span>
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
        money: "",
      },
      departmentClassification: [],
      pageTotal: "",
      departmentPageDto: {},
    };
  },
  created() {
    this.user = this.$getSessionStorage("user");
    //this.selectDepartmentClassification();
    this.paging(1);
    console.log(this.$route.query.row.userName);
  },
  methods: {
    pay() {
      this.$confirm(
        "确认给" +
          " [ " +
          this.$route.query.row.realName +
          " ] " +
          "转账" +
          " [ " +
          this.formInline.money +
          " ] " +
          "元"
      )
        .then((_) => {
          //axios转账请求
          this.$axios
            .post("/salary/newSalary", {
              userAccount: this.$route.query.row.userName,
              userName: this.$route.query.row.realName,
              transferAccount: this.user.userName,
              transferName: this.user.realName,
              transferMoney: this.formInline.money,
            })
            .then((Response) => {
              if (Response.data == "SUCCESS") {
                alert(
                  "转账成功：" + " [ " + this.formInline.money + " ] " + "元"
                );
              } else if (Response.data == "医院总部余额不足,无法转账") {
                alert(Response.data);
              } else {
                alert("转账失败");
              }
            })
            .catch((error) => {
              console.error(error);
            });
        })
        .catch((_) => {});
    },
    diagnosis(row) {
      this.$router.push({
        path: "/index/toDocView",
        query: { row: row },
      });
    },
    patientRegister(pageNum) {
      //axios查询诊断数据请求
      this.$axios
        .post("/salary/selectAllTransferMoney", {
          pageNum: pageNum,
          userAccount: this.$route.query.row.userName,
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
      this.patientRegister(pageNumber);
    },
    resetForm() {
      location.reload();
    },
    paging(pageNum) {
      //axios查询科室列表请求(分页无参)
      this.$axios
        .post("/salary/selectAllTransferMoney", {
          pageNum: pageNum,
          userAccount: this.$route.query.row.userName,
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
      this.patientRegister(pageNumber);
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