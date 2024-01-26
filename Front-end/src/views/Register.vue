<template>
  <div>
    <div class="title">
      <i class="el-icon-house" style="margin-top: 70px; margin-left: -80px">系统账号注册</i>
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
        color: #fff;
        position: absolute;
        z-index: 1;
        width: 500px;
        margin-left: 420px;
        margin-top: 230px;
        color: white;
      "
    >
      <el-form-item label="用户名" prop="age" style="color: #fff">
        <el-input v-model.number="ruleForm.age"></el-input>
      </el-form-item>
      <el-form-item label="真实姓名" prop="realName" style="color: #fff">
        <el-input v-model="ruleForm.realName" style="color: #fff"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="pass">
        <el-input
          type="password"
          v-model="ruleForm.pass"
          autocomplete="off"
        ></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="checkPass">
        <el-input
          type="password"
          v-model="ruleForm.checkPass"
          autocomplete="off"
        ></el-input>
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
        pass: "",
        checkPass: "",
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
      //验证两次密码是否一致
      if (this.ruleForm.pass != this.ruleForm.checkPass) {
        alert("两次密码不一致，注册失败");
      } else {
        //axios注册的请求
        this.$axios
          .post("/user/register", {
            realName: this.ruleForm.realName,
            userName: this.ruleForm.age,
            password: this.ruleForm.pass,
          })
          .then((Response) => {
            let user = Response.data;
            if (user == "") {
              alert("此账号已存在，注册失败");
            } else {
              this.$setSessionStorage("user", user);
              this.$router.push({
                path: "/index",
              });
              alert("账户注册成功，即将自动登录");
            }
          })
          .catch((error) => {
            console.error(error);
          });
      }
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
  margin-left: 630px;
  margin-top: 50px;
  color: white;
  font-size: 50px;
}
label,
.el-form-item__label {
  color: #fff;
}
</style>