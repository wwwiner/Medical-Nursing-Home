<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统信息</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/index/userList' }"
        >用户管理</el-breadcrumb-item
      >
    </el-breadcrumb>
    <el-form
      :inline="true"
      :model="formInline"
      class="demo-form-inline"
      style="margin-top: 50px; line-height: 10px"
    >
      <el-form-item label="用户姓名" ref="formRef">
        <el-input
          v-model="formInline.realName"
          placeholder="用户姓名"
        ></el-input>
      </el-form-item>
      <el-form-item label="用户类型">
        <el-select v-model="formInline.userType" placeholder="用户类型">
          <el-option
            v-for="userType in userType"
            :key="userType.id"
            :lable="userType.userType"
            :value="userType.userType"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item style="margin-bottom: 0px">
        <el-button type="primary" plain @click="select">查询</el-button>
        <el-button type="danger" plain @click="resetForm('formInline')"
          >重置</el-button
        >
      </el-form-item>
    </el-form>
    <el-card class="box-card">
      <el-table
        :data="userTable"
        border
        style="width: 100%"
        text-align:
        right
        :cell-style="{ padding: '2.5px', 'text-align': 'center' }"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55" style="text-align: center">
        </el-table-column>
        <el-table-column fixed prop="id" label="序号"> </el-table-column>
        <el-table-column prop="userName" label="用户账号"> </el-table-column>
        <el-table-column prop="password" label="用户密码"> </el-table-column>
        <el-table-column prop="realName" label="用户姓名"></el-table-column>
        <el-table-column prop="userType" label="用户类型"> 
          <template scope="scope">
          <span v-if="scope.row.userType === '医院总部'" style="color: gold">{{scope.row.userType}}</span>
          <span v-if="scope.row.userType === '用户'" style="color: black">{{scope.row.userType}}</span>
          <span v-if="scope.row.userType.indexOf('管理')>=0" style="color: #409eff">{{scope.row.userType}}</span>
          <span v-if="scope.row.userType.indexOf('医生')>=0 || scope.row.userType.indexOf('主任')>=0" style="color: #19b46d">{{scope.row.userType}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="money" label="用户余额"> </el-table-column>
        <el-table-column fixed="right" label="编辑/发放工资/删除" width="160">
          <template slot-scope="scope">
            <el-button
              el-button
              type="primary"
              icon="el-icon-edit"
              circle
              @click="updateUser(scope.row), (drawer = true)"
              size="small"
            ></el-button>
            <el-button
              type="warning"
              icon="el-icon-bank-card"
              circle
              @click="salary(scope.row)"
              size="small"
            ></el-button>
            <el-button
              type="danger"
              icon="el-icon-delete"
              circle
              @click="deteleDepartment(scope.row)"
              size="small"
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <nobr>
      <el-button
        type="danger"
        plain
        @click="batchDelete"
        style="position: absolute; margin-left: 10px; margin-top: 25px"
        :disabled="multipleSelection.length == 0"
        >批量删除</el-button
      >
      <el-pagination
        background
        layout="prev, pager, next,total"
        :total="departmentPageDto.totalRow"
        :page-size="10"
        style="position: absolute; margin-left: 380px; margin-top: 30px"
        @current-change="handleCurrentChange"
      >
      </el-pagination>
    </nobr>
    <el-drawer
      title="编辑科室"
      :visible.sync="drawer"
      :direction="direction"
      :before-close="handleClose"
    >
      <el-form
        ref="form"
        :model="form"
        label-width="80px"
        style="
          position: absolute;
          margin-left: 100px;
          margin-top: 100px;
          width: 300px;
        "
      >
        <el-form-item label="用户账号">
          <el-input v-model="form.userName"></el-input>
        </el-form-item>
        <el-form-item label="用户密码">
          <el-input v-model="form.password"></el-input>
        </el-form-item>
        <el-form-item label="用户姓名">
          <el-input v-model="form.realName"></el-input>
        </el-form-item>
        <el-form-item label="用户类型">
          <el-input v-model="form.userType"></el-input>
        </el-form-item>
        <el-form-item label="用户余额" style="margin-top: 20px">
          <el-input v-model="form.money"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitUser" style="margin-top: 20px"
            >提交编辑</el-button
          >
        </el-form-item>
      </el-form>
    </el-drawer>
  </div>
</template>

<script>
export default {
  data() {
    return {
      formInline: {
        realName: "",
        userType: "",
      },
      form: {
        id: "",
        realName: "",
        passWord: "",
        userType: "",
        money: "",
      },
      user: {},
      userTable: [],
      multipleSelection: [],
      pageTotal: "",

      departmentPageDto: {},
      userType: [],
      drawer: false,
      direction: "rtl",
    };
  },
  created() {
    this.user = this.$getSessionStorage("user");
    //axios查询科室列表条数请求
    this.paging(1);
    //axios查询科室分类请求
    this.selectuserType();
  },
  methods: {
    select(pageNum) {
      //axios查询用户列表请求((分页有参))
      var a = isNaN(pageNum);
      console.log(a);
      if (isNaN(pageNum) == true) {
        pageNum = 1;
      }
      this.$axios
        .post("/user/selectUserList", {
          pageNum: pageNum,
          realName: this.formInline.realName,
          userType: this.formInline.userType,
        })
        .then((Response) => {
          this.userTable = Response.data.list;
          this.departmentPageDto = Response.data;
          console.log(this.departmentPageDto);
        })
        .catch((error) => {
          console.error(error);
        });
    },
    updateUser(row) {
      if (this.user.userType == "管理员") {
        console.log(row);
        this.form = row;
      } else {
        alert("很抱歉,您没有权限编辑用户信息");
        location.reload();
      }
      //第二种策略，跳转路由（CROS）
      // this.$router.push({
      //   path: "/index/updateDepartment",
      //   query: { row: row },
      // });
    },
    submitUser() {
      //axios编辑用户信息请求
      this.$axios
        .post("/user/updateUserById", {
          id: this.form.id,
          userName: this.form.userName,
          password: this.form.password,
          realName: this.form.realName,
          userType: this.form.userType,
          money: this.form.money,
        })
        .then((Response) => {})
        .catch((error) => {
          console.error(error);
          alert("编辑用户信息失败");
        });
      alert("编辑用户信息成功");
      location.reload();
    },
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then((_) => {
          done();
        })
        .catch((_) => {});
    },
    deteleDepartment(row) {
      if (this.user.userType == "管理员") {
        this.$confirm("确认删除此条用户信息？")
          .then((_) => {
            console.log(row.id);
            //axios通过id删除科室
            this.$axios
              .post("/user/deleteUserById", {
                id: row.id,
              })
              .then((Response) => {
                alert("删除用户成功");
              })
              .catch((error) => {
                console.error(error);
                alert("删除用户失败");
              });
          })
          .catch((_) => {});
      } else {
        alert("很抱歉,您没有权限删除用户信息");
      }
    },
    resetForm(formName) {
      location.reload();
    },
    batchDelete() {
      if (this.user.userType == "管理员") {
        if (this.multipleSelection.length == 0) {
          alert("至少选择一条数据进行批量删除");
        } else {
          this.$confirm("确认批量删除用户信息？")
            .then((_) => {
              //axios批量删除请求
              this.$axios
                .post("/user/removeUserBatch", this.multipleSelection)
                .then((Response) => {
                  alert("批量删除成功");
                })
                .catch((error) => {
                  console.error(error);
                });
            })
            .catch((_) => {});
        }
      } else {
        alert("很抱歉,您没有权限批量删除用户信息");
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
      console.log(val);
    },
    paging(pageNum) {
      //axios查询用户列表请求(分页无参)
      this.$axios
        .post("/user/selectUserList", {
          pageNum: pageNum,
        })
        .then((Response) => {
          this.userTable = Response.data.list;
          this.departmentPageDto = Response.data;
          console.log(this.userPageDto);
        })
        .catch((error) => {
          console.error(error);
        });
    },
    handleCurrentChange(pageNumber) {
      this.select(pageNumber);
    },
    selectuserType() {
      //axios查询用户类型请求
      this.$axios
        .post("/user/selectuserType")
        .then((Response) => {
          this.userType = Response.data;
          console.log(this.userType);
        })
        .catch((error) => {
          console.error(error);
        });
    },
    salary(row) {
      if (
        this.user.userType == "管理员" ||
        this.user.userType.indexOf("财务" >= 0)
      ) {
        this.$router.push({
          path: "/index/salary",
          query: { row: row },
        });
      }
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
  width: 1400px;
}

.el-main {
  line-height: 0px;
}
</style>






