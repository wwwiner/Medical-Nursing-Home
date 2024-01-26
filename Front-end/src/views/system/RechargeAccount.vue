<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统信息</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/index/rechargeAccount' }"
      >充值中心</el-breadcrumb-item
      >
    </el-breadcrumb>


    <el-descriptions direction="vertical" :column="6" border style="margin-top: 50px">
      <el-descriptions-item label="用户编号">{{ user.id }}</el-descriptions-item>
      <el-descriptions-item label="用户账号">{{ user.userName }}</el-descriptions-item>
      <el-descriptions-item label="用户密码">{{ user.password }}</el-descriptions-item>
      <el-descriptions-item label="用户姓名">{{ user.realName }}</el-descriptions-item>
      <el-descriptions-item label="账户余额">{{ user.money }}</el-descriptions-item>
      <el-descriptions-item label="充值">
        <el-button type="primary"  icon="el-icon-edit" @click="drawer = true" style="margin-top: 20px"
        >充值</el-button
        >
      </el-descriptions-item>
    </el-descriptions>

<!--    <el-button-->
<!--        el-button-->
<!--        type="primary"-->
<!--        label="充值"-->
<!--        icon="el-icon-edit"-->
<!--        circle-->
<!--        @click="drawer = true"-->
<!--        size="small"-->
<!--        style="margin-top: 10px"-->
<!--    ></el-button>-->
    <el-drawer
        title="充值"
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
        <el-form-item label="用户编号">
          <el-input v-model="form.id"></el-input>
        </el-form-item>
        <el-form-item label="用户账号">
          <el-input v-model="form.userName"></el-input>
        </el-form-item>
        <el-form-item label="用户密码">
          <el-input v-model="form.password"></el-input>
        </el-form-item>
        <el-form-item label="用户姓名">
          <el-input v-model="form.realName"></el-input>
        </el-form-item>
        <el-form-item label="用户权限">
          <el-input v-model="form.userType" ></el-input>
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
        id:"",
        userName: "",
        realName: "",
        passWord: "",
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

  },
  methods: {

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

    resetForm(formName) {
      location.reload();
    },

    handleSelectionChange(val) {
      this.multipleSelection = val;
      console.log(val);
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






