<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统信息</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/index/deptartmentList' }"
        >科室管理</el-breadcrumb-item
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
      <el-form-item label="科室类型">
        <el-select v-model="formInline.deptType" placeholder="科室类型">
          <el-option
            v-for="departmentType in departmentType"
            :key="departmentType.deptType"
            :lable="departmentType.deptType"
            :value="departmentType.deptType"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item style="margin-bottom: 0px">
        <el-button type="primary" plain @click="select">查询</el-button>
        <el-button type="success" plain @click="newDepartment"
          >新建科室</el-button
        >
        <el-button type="danger" plain @click="resetForm('formInline')"
          >重置</el-button
        >
      </el-form-item>
    </el-form>
    <el-card class="box-card-departemt">
      <el-table
        :data="deptTable"
        border
        style="width: 100%"
        text-align:
        right
        :cell-style="{ padding: '2px', 'text-align': 'center' }"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55" style="text-align: center">
        </el-table-column>
        <el-table-column fixed prop="id" label="序号"> </el-table-column>
        <el-table-column prop="deptCode" label="科室编码"> </el-table-column>
        <el-table-column prop="deptName" label="科室名称" width="120"> </el-table-column>
        <el-table-column
          prop="deptCategoryName"
          label="科室分类"
        ></el-table-column>
        <el-table-column prop="deptType" label="科室类型"> 
          <template scope="scope">
            <span v-if="scope.row.deptType === '医疗科室'" style="color: #19b46d">医疗科室</span>
            <span v-if="scope.row.deptType === '财务科室'" style="color: rgb(255, 208, 75)">财务科室</span>
            <span v-if="scope.row.deptType === '行政科室'" style="color: #409eff">行政科室</span>
          </template>
        </el-table-column>
        <el-table-column prop="createName" label="创建人"></el-table-column>
        <el-table-column
          prop="createDate"
          label="创建时间"
          width="240"
        ></el-table-column>
        <el-table-column fixed="right" label="操作" width="150">
          <template slot-scope="scope">
            <el-button
              el-button
              type="primary"
              icon="el-icon-edit"
              circle
              @click="updateDepartment(scope.row), (drawer = true)"
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
        <el-form-item label="科室编号">
          <el-input v-model="form.deptCode"></el-input>
        </el-form-item>
        <el-form-item label="科室名称">
          <el-input v-model="form.deptName"></el-input>
        </el-form-item>
        <el-form-item label="科室分类">
          <el-input v-model="form.deptCategoryName"></el-input>
        </el-form-item>
        <el-form-item label="科室类型">
          <el-select v-model="form.deptType" placeholder="请选择科室分类">
            <el-option label="医疗科室" value="医疗科室"></el-option>
            <el-option label="财务科室" value="财务科室"></el-option>
            <el-option label="行政科室" value="行政科室"></el-option>
            <el-option label="科研科室" value="科研科室"></el-option>
            <el-option label="其他科室" value="其他科室"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitDepartment"
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
      },
      user: {},
      deptTable: [],
      multipleSelection: [],
      pageTotal: "",
      departmentPageDto: {},
      departmentClassification: [],
      departmentType: [],
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
        .post("/department/selectCountFromDepartmentList", {
          pageNum: pageNum,
          deptName: this.formInline.deptName,
          deptCategoryName: this.formInline.deptCategoryName,
          deptType: this.formInline.deptType,
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
      if (this.user.userType == "管理员") {
        console.log(row.id);
        this.form = row;
      } else {
        alert("很抱歉,您没有权限编辑科室");
        location.reload();
      }
      //第二种策略，跳转路由（CROS）
      // this.$router.push({
      //   path: "/index/updateDepartment",
      //   query: { row: row },
      // });
    },
    submitDepartment() {
      this.user = this.$getSessionStorage("user");
      //axios编辑科室请求
      this.$axios
        .post("/department/updateDeptartment", {
          id: this.form.id,
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
        this.$confirm("确认删除此科室？")
          .then((_) => {
            console.log(row.id);
            //axios通过id删除科室
            this.$axios
              .post("/department/deleteDepartmentbyId", {
                id: row.id,
              })
              .then((Response) => {
                alert("删除科室成功");
              })
              .catch((error) => {
                console.error(error);
                alert("删除科室失败");
              });
          })
          .catch((_) => {});
      } else {
        alert("很抱歉,您没有权限删除科室");
      }
    },
    resetForm(formName) {
      location.reload();
    },
    newDepartment() {
      if (this.user.userType == "管理员") {
        this.$router.push({
          path: "/index/newDepartment",
        });
      } else {
        alert("很抱歉,您没有权限新建科室");
      }
    },
    batchDelete() {
      if (this.user.userType == "管理员") {
        if (this.multipleSelection.length == 0) {
          alert("至少选择一条数据进行批量删除");
        } else {
          this.$confirm("确认批量删除科室？")
            .then((_) => {
              //axios批量删除请求
              this.$axios
                .post(
                  "/department/removeDepartmentBatch",
                  this.multipleSelection
                )
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
        alert("很抱歉,您没有权限批量删除科室");
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
      console.log(val);
    },
    paging(pageNum) {
      //axios查询科室列表请求(分页无参)
      this.$axios
        .post("/department/selectCountFromDepartmentList", {
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

.box-card-departemt {
  width: 1300px;
  margin-top: 0px;
}

.el-main {
  line-height: 0px;
}
</style>






