<template>
  <div>
    <div class="title">
      <i class="el-icon-house">系统账号信息修改</i>
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
        margin-left: 450px;
        margin-top: 200px;
      "
    >
      <el-form-item label="用户名" prop="age">
        <el-input v-model.number="ruleForm.age"></el-input>
      </el-form-item>
      <el-form-item label="旧密码" prop="oldPass">
        <el-input v-model="ruleForm.oldPass"></el-input>
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
        oldPass :"",
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
    //   this.$refs[formName].validate((valid) => {
    //     if (valid) {
    //       alert("submit!");
    //     } else {
    //       console.log("error submit!!");
    //       return false;
    //     }
    //   });
       //axios修改密码请求
      this.$axios
        .post("/user/updatePassword", {
          oldPassword: this.ruleForm.oldPass,
          userName: this.ruleForm.age,
          password: this.ruleForm.pass,
        })
        .then((Response) => {
          let user = Response.data;
          if (user == "") {
            alert("用户名或密码错误");
          } else {
            this.$setSessionStorage("user", user);
            this.$router.push({
              path: "/index",
            });
            alert("账户密码修改成功");
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
  margin-left: 500px;
  margin-top: 50px;
  color: white;
  font-size: 50px;
}
</style>