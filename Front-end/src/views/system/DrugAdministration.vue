<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>药房管理</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/index/drugAdministration' }"
        >药品管理</el-breadcrumb-item
      >
    </el-breadcrumb>
    <el-form
      :inline="true"
      :model="formInline"
      class="demo-form-inline"
      style="margin-top: 50px; line-height: 10px"
    >
      <el-form-item label="药品名称" ref="formRef">
        <el-input
          v-model="formInline.drugName"
          placeholder="药品名称"
        ></el-input>
      </el-form-item>
      <el-form-item label="药品类型">
        <el-select v-model="formInline.drugType" placeholder="药品类型">
          <el-option
            v-for="drugType in drugType"
            :key="drugType.id"
            :lable="drugType.drugType"
            :value="drugType.drugType"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" plain @click="select">查询</el-button>
        <el-button type="success" plain @click="newDrug">新增药品</el-button>
        <el-button type="danger" plain @click="resetForm('formInline')"
          >重置</el-button
        >
      </el-form-item>
    </el-form>
    <el-card class="box-card">
      <el-table
        :data="drugTable"
        border
        style="width: 100%"
        text-align:
        right
        :cell-style="{ padding: '2px', 'text-align': 'center' }"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55" style="text-align: center">
        </el-table-column>
        <el-table-column fixed prop="id" label="药品编号"> </el-table-column>
        <el-table-column prop="drugName" label="药品名称"> </el-table-column>
        <el-table-column prop="drugType" label="药品类型"> </el-table-column>
        <el-table-column prop="drugCount" label="药品数量">
          <template scope="scope">
            <span v-if="scope.row.drugCount <= 100" style="color: red">{{
              scope.row.drugCount
            }}</span>
            <span
              v-if="scope.row.drugCount > 100 && scope.row.drugCount <= 500"
              style="color: orange"
              >{{ scope.row.drugCount }}</span
            >
            <span v-if="scope.row.drugCount > 500" style="color: #19b46d">{{
              scope.row.drugCount
            }}</span>
          </template>
        </el-table-column>
        <el-table-column label="药品数量百分比">
          <template slot-scope="scopee">
            <el-progress
              :text-inside="true"
              :stroke-width="20"
              :percentage="scopee.row.drugCount / 10"
              v-if="scopee.row.drugCount > 500"
              status="success"
            ></el-progress>
            <el-progress
              :text-inside="true"
              :stroke-width="20"
              :percentage="scopee.row.drugCount / 10"
              v-if="scopee.row.drugCount > 100 && scopee.row.drugCount <= 500"
              status="warning"
            ></el-progress>
            <el-progress
              :text-inside="true"
              :stroke-width="20"
              :percentage="scopee.row.drugCount / 10"
              v-if="scopee.row.drugCount <= 100"
              status="exception"
            ></el-progress>
          </template>
        </el-table-column>
        <el-table-column prop="drugPrice" label="药品价格"> </el-table-column>

        <el-table-column fixed="right" label="操作">
          <template slot-scope="scope">
            <el-button
              el-button
              type="primary"
              icon="el-icon-edit"
              circle
              @click="updateDrug(scope.row), (drawer = true)"
              size="small"
            ></el-button>
            <el-button
              type="danger"
              icon="el-icon-delete"
              circle
              @click="deteleDrug(scope.row)"
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
        <el-form-item label="药品名称">
          <el-input v-model="form.drugName"></el-input>
        </el-form-item>
        <el-form-item label="药品类型">
          <el-input v-model="form.drugType"></el-input>
        </el-form-item>
        <el-form-item label="药品数量">
          <!-- <el-input v-model="form.drugCount"></el-input> -->
          <el-slider v-model="form.drugCount" max="1000"></el-slider>
        </el-form-item>
        <el-form-item label="药品价格">
          <el-input v-model="form.drugPrice"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitDrug">提交编辑</el-button>
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
        drugName: "",
        drugType: "",
      },
      form: {
        id: "",
        drugName: "",
        drugType: "",
        drugCount: "",
        drugPrice: "",
      },
      user: {},
      drugTable: [],
      multipleSelection: [],
      pageTotal: "",
      departmentPageDto: {},
      drugType: [],
      drawer: false,
      direction: "rtl",
    };
  },
  created() {
    this.user = this.$getSessionStorage("user");
    //axios查询科室列表条数请求
    this.paging(1);
    //axios查询科室分类请求
    this.selectuserType();
  },
  methods: {
    select(pageNum) {
      //axios查询用户列表请求((分页有参))
      var a = isNaN(pageNum);
      console.log(a);
      if (isNaN(pageNum) == true) {
        pageNum = 1;
      }
      this.$axios
        .post("/drug/selectDrugList", {
          pageNum: pageNum,
          drugName: this.formInline.drugName,
          drugType: this.formInline.drugType,
        })
        .then((Response) => {
          this.drugTable = Response.data.list;
          this.departmentPageDto = Response.data;
          console.log(this.departmentPageDto);
        })
        .catch((error) => {
          console.error(error);
        });
    },
    updateDrug(row) {
      if (this.user.userType == "管理员") {
        console.log(row);
        this.form = row;
      } else {
        alert("很抱歉,您没有权限编辑药品信息");
        location.reload();
      }
      //第二种策略，跳转路由（CORS）
      // this.$router.push({
      //   path: "/index/updateDepartment",
      //   query: { row: row },
      // });
    },
    submitDrug() {
      //axios编辑药品信息请求
      this.$axios
        .post("/drug/updateDrugById", {
          id: this.form.id,
          drugName: this.form.drugName,
          drugCount: this.form.drugCount,
          drugPrice: this.form.drugPrice,
          drugType: this.form.drugType,
        })
        .then((Response) => {})
        .catch((error) => {
          console.error(error);
          alert("编辑药品信息失败");
        });
      alert("编辑药品信息成功");
      location.reload();
    },
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then((_) => {
          done();
        })
        .catch((_) => {});
    },
    deteleDrug(row) {
      if (this.user.userType == "管理员") {
        this.$confirm("确认删除此药品？")
          .then((_) => {
            console.log(row.id);
            //axios通过id删除药品信息
            this.$axios
              .post("/drug/deleteDrugById", {
                id: row.id,
              })
              .then((Response) => {
                alert("删除药品成功");
              })
              .catch((error) => {
                console.error(error);
                alert("删除药品失败");
              });
          })
          .catch((_) => {});
      } else {
        alert("很抱歉,您没有权限删除药品");
      }
    },
    resetForm(formName) {
      location.reload();
    },
    batchDelete() {
      if (this.user.userType == "管理员") {
        if (this.multipleSelection.length == 0) {
          alert("至少选择一条数据进行批量删除");
        } else {
          this.$confirm("确认批量删除药品信息？")
            .then((_) => {
              //axios批量删除请求
              this.$axios
                .post("/drug/removeDrugBatch", this.multipleSelection)
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
        alert("很抱歉,您没有权限批量删除药品信息");
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
      console.log(val);
    },
    paging(pageNum) {
      //axios查询药品列表请求(分页无参)
      this.$axios
        .post("/drug/selectDrugList", {
          pageNum: pageNum,
        })
        .then((Response) => {
          this.drugTable = Response.data.list;
          this.departmentPageDto = Response.data;
          console.log(this.userPageDto);
        })
        .catch((error) => {
          console.error(error);
        });
    },
    handleCurrentChange(pageNumber) {
      this.select(pageNumber);
    },
    selectuserType() {
      //axios查询药品类型请求
      this.$axios
        .post("/drug/selectDrugType")
        .then((Response) => {
          this.drugType = Response.data;
          console.log(this.userType);
        })
        .catch((error) => {
          console.error(error);
        });
    },
    newDrug() {
      if (this.user.userType == "管理员") {
        this.$router.push({
          path: "/index/newDrug",
        });
      } else {
        alert("很抱歉,您没有权限新增药品");
      }
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






