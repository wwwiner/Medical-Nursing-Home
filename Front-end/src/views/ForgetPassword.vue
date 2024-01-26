<template>
  <div>
    <div class="title">
      <i class="el-icon-house">系统账号密码找回</i>
    </div>
    <video
      style="position: absolute; z-index: -1; width: 1900px"
      class="mp41"
      src="../img/sunflower.mp4"
      autoplay="autoplay"
      loop="loop"
      muted="muted"
    ></video>
    <el-form
      :model="ruleForm"
      status-icon
      :rules="rules"
      ref="ruleForm"
      label-width="100px"
      class="demo-ruleForm"
      style="
        color: #FFF;
        position: absolute;
        z-index: 1;
        width: 500px;
        margin-left: 550px;
        margin-top: 200px;
      "
    >
     <i class="tips">请输入您注册时的账号和真实姓名</i>
      <el-form-item label="用户名" prop="age">
        <el-input v-model.number="ruleForm.age"></el-input>
      </el-form-item>
      <el-form-item label="真实姓名" prop="realName">
        <el-input v-model="ruleForm.realName"></el-input>
      </el-form-item>
      <el-form-item>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        <el-button type="primary" @click="submitForm('ruleForm')"
          >提交</el-button
        >     
      </el-form-item>
    </el-form>
  </div>
</template>

<script scoped>
export default {
  data() {
    var checkAge = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("用户名不能为空"));
      }
    };
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.ruleForm.checkPass !== "") {
          this.$refs.ruleForm.validateField("checkPass");
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        realName: "",
        age: "",
      },
      rules: {
        pass: [{ validator: validatePass, trigger: "blur" }],
        checkPass: [{ validator: validatePass2, trigger: "blur" }],
        age: [{ validator: checkAge, trigger: "blur" }],
      },
    };
  },
  methods: {
    submitForm(formName) {
       //axios修改密码请求
      this.$axios
        .post("/user/forgetPassword", {
          userName: this.ruleForm.age,
          realName: this.ruleForm.realName,
        })
        .then((Response) => {
          let user = Response.data;
          if (user == "") {
            alert("用户名或真实姓名错误，请重新输入");
          } else {
            this.$setSessionStorage("user", user);
             alert("您的账号信息为:\n"+"用户编号："+user.id+"\n"+"用户名："+user.userName+"\n"+"用户密码："+user.password+"\n"+"真实姓名："+user.realName+"\n"+"点击确定将跳转到登录页面，请妥善保存账号信息");
            this.$router.push({
              path: "/login",
            });
          }
        })
        .catch((error) => {
          console.error(error);
        });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
};
</script>

<style scoped>
.title {
  position: absolute;
  z-index: 0;
  margin-left: 580px;
  margin-top: 50px;
  color: white;
  font-size: 50px;
}
.tips{
     position: absolute;
        z-index: 1;
        width: 500px;
        margin-left: 270px;
        margin-top: 200px;
}
</style>