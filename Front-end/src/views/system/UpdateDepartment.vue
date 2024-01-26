<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统信息</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/index/deptartmentList' }"
        >科室管理</el-breadcrumb-item
      >
      <el-breadcrumb-item :to="{ path: '/index/newDepartment' }"
        >新建科室</el-breadcrumb-item
      >
    </el-breadcrumb>
    <el-form
      ref="form"
      :model="form"
      label-width="80px"
      style="
        position: absolute;
        margin-left: 100px;
        margin-top: 100px;
        width: 400px;
      "
    >
      <el-form-item label="科室编号">
        <el-input v-model="form.deptCode"></el-input>
      </el-form-item>
      <el-form-item label="科室名称">
        <el-input v-model="form.deptName"></el-input>
      </el-form-item>
      <el-form-item label="科室类型">
        <el-input v-model="form.deptCategoryName"></el-input>
      </el-form-item>
      <el-form-item label="科室分类">
        <el-select v-model="form.deptType" placeholder="请选择科室分类">
          <el-option label="医疗科室" value="医疗科室"></el-option>
          <el-option label="财务科室" value="财务科室"></el-option>
          <el-option label="行政科室" value="行政科室"></el-option>
          <el-option label="科研科室" value="科研科室"></el-option>
          <el-option label="其他科室" value="其他科室"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitDepartment">立即创建</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
    created() {
        //this.form =this.$route.query.row;
        console.log(this.$route.query.row.id);
        //axios通过id查科室
      this.$axios
        .post("/department/selectDepartmentbyId", {
           id: this.$route.query.row.id,
          
        })
        .then((Response) => {
          this.from = Response.data;
        })
        .catch((error) => {
          console.error(error);
        });
  },
  data() {
    return {
      //id: this.$route.query.row.id,
      form: { 
        id: "",
        deptCode: "",
        deptName: "",
        deptType: "",
        deptCategoryName: "",
      },
      user: {},
    };
  },
  methods: {
    submitDepartment() {
      this.user = this.$getSessionStorage("user");
      //axios编辑科室请求
      this.$axios
        .post("/department/updateDeptartment", {
          id: this.user.id,
          createName: this.user.realName,
          deptCode: this.form.deptCode,
          deptName: this.form.deptName,
          deptType: this.form.deptType,
          deptCategoryName: this.form.deptCategoryName,
        })
        .then((Response) => {})
        .catch((error) => {
          console.error(error);
          alert("编辑科室失败");
        });
      alert("编辑科室成功");
    }
  },
};
</script>