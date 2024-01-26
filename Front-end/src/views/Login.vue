<template>
  <div>
    <div class="title"><i class="el-icon-house">智慧养老院</i></div>
    <video
      style="position: absolute; z-index: -1; width: 1900px"
      class="mp41"
      src="../img/sunflower.mp4"
      autoplay="autoplay"
      loop="loop"
      muted="muted"
    ></video>
    <el-card class="box-card" style="position: absolute; z-index: 0">
      <div slot="header" class="clearfix">
        <span style="font-size: 18px">登录</span>
        <el-button
          style="float: right; padding: 3px 0"
          type="text"
          @click="forgetPassword"
          >忘记密码</el-button
        >
      </div>
      <div class="text item">
        <el-form ref="userForm" :model="userForm" label-width="80px">
          <el-form-item label="账号">
            <el-input v-model="userForm.userName"></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input
              type="password"
              v-model="userForm.passWord"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="login">登录</el-button>
            <el-button @click="register">注册</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userForm: {
        userName: "",
        passWord: "",
      },
    };
  },
  methods: {
    login() {
      console.log("账号" + this.userForm.userName);
      console.log("密码" + this.userForm.passWord);
      if (this.userForm.userName == "") {
        alert("账号不能为空");
      } else if (this.userForm.passWord == "") {
        alert("密码不能为空");
      }

      //axios登录请求
      this.$axios
        .post("/user/login", {
          userName: this.userForm.userName,
          password: this.userForm.passWord,
        })
        .then((Response) => {
          let user = Response.data;
          if (user == "") {
            alert("用户名或密码不正确");
          } else {
            this.$setSessionStorage("user", user);
            console.log(user);
            this.$router.push({
              path: "/index",
            });
            alert("登录成功");
          }
        })
        .catch((error) => {
          console.error(error);
        });
    },
    register() {
      this.$router.push({
        path: "/register",
      });
    },
    forgetPassword() {
      this.$router.push({

        path: "/forgetPassword",
      });
    },
  },
};
</script>

<style scoped>
.title {
  position: absolute;
  z-index: 0;
  margin-left: 550px;
  margin-top: 200px;
  color: white;
  font-size: 50px;
}
.text {
  font-size: 14px;
}

.item {
  margin-top: 0px ;
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}

.box-card {
  width: 450px;
  height: 350px;
  margin-left: 475px;
  margin-top: 300px;
}

</style>