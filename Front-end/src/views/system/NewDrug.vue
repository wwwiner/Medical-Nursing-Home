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
        margin-left: 100px;
        margin-top: 100px;
        width: 400px;
      "
    >
      <el-form-item label="药品名称">
        <el-input v-model="form.drugName"></el-input>
      </el-form-item>
      <el-form-item label="药品分类">
        <el-input v-model="form.drugType"></el-input>
      </el-form-item>
      <el-form-item label="药品数量">
        <el-input v-model="form.drugCount"></el-input>
      </el-form-item>
      <el-form-item label="药品价格">
        <el-input v-model="form.drugPrice"></el-input>
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
        drugName: "",
        drugType: "",
        drugCount: "",
        drugPrice: "",
        user: {},
      },
    };
  },
  methods: {
    createDepartment() {
      this.user = this.$getSessionStorage("user");
      //axios新建科室请求
      this.$axios
        .post("/drug/insertDrug", {
          drugName: this.form.drugName,
          drugType: this.form.drugType,
          drugCount: this.form.drugCount,
          drugPrice: this.form.drugPrice,
        })
        .then((Response) => {})
        .catch((error) => {
          console.error(error);
        });
      alert("新增" + " [ " + this.form.drugName + " ] " + "药品成功");
    },
    clear() {
      location.reload();
    },
  },
};
</script>