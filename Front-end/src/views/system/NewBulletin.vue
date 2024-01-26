<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统信息</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/index/deptartmentList' }"
      >科室管理</el-breadcrumb-item
      >
      <el-breadcrumb-item :to="{ path: '/index/newDrug' }"
      >新增药品</el-breadcrumb-item
      >
    </el-breadcrumb>
    <el-form
        ref="form"
        :model="form"
        label-width="80px"
        style="
        position: absolute;
        margin-left: 200px;
        margin-top: 100px;
        width: 400px;
      "
    >
      <el-form-item label="留言标题">
        <el-input v-model="form.bullTitle"></el-input>
      </el-form-item>
      <el-form-item label="留言分类">
        <el-input v-model="form.bullType"></el-input>
      </el-form-item>
      <el-form-item label="留言内容">
        <el-input  v-model="form.content" type="textarea" :autosize="{ minRows: 3, maxRows: 6}"></el-input>
      </el-form-item>
      <el-form-item label="留言用户">
        <el-input v-model="form.userName"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="createDepartment">立即创建</el-button>
        <el-button @click="clear">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        bullTitle: "",
        bullType: "",
        content: "",
        userName: "",
        user: {},
      },
    };
  },
  methods: {
    createDepartment() {
      this.user = this.$getSessionStorage("user");
      //axios新建留言请求
      this.$axios
          .post("/bulletin/insertBulletin", {
            bullTitle: this.form.bullTitle,
            bullType: this.form.bullType,
            content: this.form.content,
            userName: this.form.userName,
          })
          .then((Response) => {})
          .catch((error) => {
            console.error(error);
          });
      alert("新增留言成功");
      this.$router.push(
      {
        path:"/index/bulletinBoard",
      })
    },
    clear() {
      location.reload();
    },
  },
};
</script>