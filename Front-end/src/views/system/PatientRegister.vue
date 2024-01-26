<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统信息</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/index/patientRegister' }"
        >挂号收费</el-breadcrumb-item
      >
      <el-breadcrumb-item>科室列表</el-breadcrumb-item>
    </el-breadcrumb>

    <el-form
      :inline="true"
      :model="formInline"
      class="demo-form-inline"
      style="margin-top: 50px; line-height: 10px"
    >
      <el-form-item label="科室名称" ref="formRef">
        <el-input
          v-model="formInline.deptName"
          placeholder="科室名称"
        ></el-input>
      </el-form-item>
      <el-form-item label="科室分类">
        <el-select v-model="formInline.deptCategoryName" placeholder="科室分类">
          <el-option
            v-for="departmentClassification in departmentClassification"
            :key="departmentClassification.deptCategoryName"
            :lable="departmentClassification.deptCategoryName"
            :value="departmentClassification.deptCategoryName"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" plain @click="select">查询</el-button>
        <el-button type="danger" plain @click="resetForm('formInline')"
          >重置</el-button
        >
      </el-form-item>
    </el-form>
    <el-card class="box-card-reg">
      <el-table
        border
        text-align:
        center
        :cell-style="{ padding: '2px' }"
        :data="deptTable"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55" style="text-align: center">
        </el-table-column>
        <el-table-column fixed prop="id" label="序号"> </el-table-column>
        <el-table-column prop="deptCode" label="科室编码"> </el-table-column>
        <el-table-column prop="deptName" label="科室名称"> </el-table-column>
        <el-table-column
          prop="deptCategoryName"
          label="科室分类"
        ></el-table-column>
        <el-table-column prop="deptType" label="科室类型">
          <template scope="scope">
            <span v-if="scope.row.deptType === '医疗科室'" style="color: #19b46d">医疗科室</span>
          </template>
        </el-table-column>
        <el-table-column fixed="right" label="操作  (挂号点击下方按钮)">
          <template slot-scope="scope">
            <el-button
              el-button
              type="success"
              icon="el-icon-check"
              circle
              @click="updateDepartment(scope.row), (drawer = true)"
              size="small"
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <nobr>
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
      title="挂号"
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
        <el-form-item label="科室编号">
          <el-input v-model="form.deptCode" readonly></el-input>
        </el-form-item>
        <el-form-item label="科室名称">
          <el-input v-model="form.deptName" readonly></el-input>
        </el-form-item>
        <el-form-item label="科室分类">
          <el-input v-model="form.deptCategoryName" readonly></el-input>
        </el-form-item>
        <el-form-item label="选择医生">
          <el-select v-model="form.docName" placeholder="请选择医生">
            <el-option
              v-for="doc in doc"
              :key="doc.id"
              :lable="doc.realName"
              :value="doc.realName"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="病情描述">
          <el-input
            type="textarea"
            v-model="form.registerDepartment"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitRegister">挂号</el-button>
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
        deptName: "",
        deptCategoryName: "",
        deptType: "",
      },
      form: {
        id: "",
        deptCode: "",
        deptName: "",
        deptType: "",
        deptCategoryName: "",
        docName: "",
        registerDepartment: "",
      },
      user: {},
      deptTable: [],
      multipleSelection: [],
      pageTotal: "",
      departmentPageDto: {},
      departmentClassification: [],
      departmentType: [],
      doc: [],
      drawer: false,
      direction: "rtl",
    };
  },
  created() {
    // //axios查询全部科室列表请求
    // this.$axios
    //   .post("/department/selectDepartmentList")
    //   .then((Response) => {
    //     this.deptTable = Response.data;
    //   })
    //   .catch((error) => {
    //     console.error(error);
    //   });
    this.user = this.$getSessionStorage("user");
    //axios查询科室列表条数请求
    this.paging(1);
    //axios查询科室分类请求
    this.selectDepartmentClassification();
    this.selectDepartmentType();
  },
  methods: {
    select(pageNum) {
      //axios查询科室列表请求((分页有参))
      var a = isNaN(pageNum);
      console.log(a);
      if (isNaN(pageNum) == true) {
        pageNum = 1;
      }
      this.$axios
        .post("/department/selectDepartmentListAsPatient", {
          pageNum: pageNum,
          deptName: this.formInline.deptName,
          deptCategoryName: this.formInline.deptCategoryName,
        })
        .then((Response) => {
          this.deptTable = Response.data.list;
          this.departmentPageDto = Response.data;
          console.log(this.departmentPageDto);
        })
        .catch((error) => {
          console.error(error);
        });
    },
    updateDepartment(row) {
      console.log(row.id);
      this.form = row;

      //axios查询科室医生请求
      this.$axios
        .post("/user/selectDocByDeptType", {
          deptName: this.form.deptName,
        })
        .then((Response) => {
          this.doc = Response.data;
        })
        .catch((error) => {
          console.error(error);
        });

      //第二种策略，跳转路由（CROS）
      // this.$router.push({
      //   path: "/index/updateDepartment",
      //   query: { row: row },
      // });
    },
    submitRegister() {
      this.user = this.$getSessionStorage("user");
      this.$confirm("确认预约" + " [ " + this.form.docName + " ] " + "医生？")
        .then((_) => {
          //axios入挂号表请求（挂号）
          this.$axios
            .post("/register/newRegister", {
              userAccount: this.user.userName,
              userName: this.user.realName,
              deptName: this.form.deptName,
              docName: this.form.docName,
              registerDepartment: this.form.registerDepartment,
              deptCategoryName: this.form.deptCategoryName,
            })
            .then((Response) => {})
            .catch((error) => {
              console.error(error);
              alert("挂号失败");
            });
          alert("您已成功预约" + " [ " + this.form.docName + " ] " + "医生");
          location.reload();
        })
        .catch((_) => {});
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
    paging(pageNum) {
      //axios查询科室列表请求(分页无参)
      this.$axios
        .post("/department/selectDepartmentListAsPatient", {
          pageNum: pageNum,
        })
        .then((Response) => {
          this.deptTable = Response.data.list;
          this.departmentPageDto = Response.data;
          console.log(this.departmentPageDto);
        })
        .catch((error) => {
          console.error(error);
        });
    },
    handleCurrentChange(pageNumber) {
      this.select(pageNumber);
    },
    selectDepartmentClassification() {
      //axios查询科室分类请求
      this.$axios
        .post("/department/selectDepartmentClassification")
        .then((Response) => {
          this.departmentClassification = Response.data;
          console.log(this.departmentClassification);
        })
        .catch((error) => {
          console.error(error);
        });
    },
    selectDepartmentType() {
      //axios查询科室类型请求
      this.$axios
        .post("/department/selectDepartmentType")
        .then((Response) => {
          this.departmentType = Response.data;
          console.log(this.departmentType);
        })
        .catch((error) => {
          console.error(error);
        });
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

.box-card-reg {
  width: 1400px;
}

.el-main {
  line-height: 0px;
}

.el-table .cell,
.el-table th div {
  text-align: center;
}

</style>






