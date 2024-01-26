<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>公告展示</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/index/bulletinBoard' }"
      >留言互动</el-breadcrumb-item
      >
    </el-breadcrumb>
    <el-form
        :inline="true"
        :model="formInline"
        class="demo-form-inline"
        style="margin-top: 50px; line-height: 10px"
    >
      <el-form-item label="留言标题" ref="formRef">
        <el-input
            v-model="formInline.bullTitle"
            placeholder="留言标题"
        ></el-input>
      </el-form-item>
      <el-form-item label="留言类型">
        <el-select v-model="formInline.bullType" placeholder="留言类型">
          <el-option
              v-for="bullType in bullType"
              :key="bullType.id"
              :lable="bullType.bullType"
              :value="bullType.bullType"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" plain @click="select">查询</el-button>
        <el-button type="success" plain @click="newBull">新增留言</el-button>
        <el-button type="danger" plain @click="resetForm('formInline')"
        >重置</el-button
        >
      </el-form-item>
    </el-form>
    <el-card class="box-card">
      <el-table
          :data="bullTable"
          border
          style="width: 100%"
          text-align:
          right
          :cell-style="{ padding: '2px', 'text-align': 'center' }"
          @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55" style="text-align: center">
        </el-table-column>
        <el-table-column fixed prop="id" label="留言编号"> </el-table-column>
        <el-table-column prop="bullTitle" label="留言标题"> </el-table-column>
        <el-table-column prop="bullType" label="留言类型"> </el-table-column>
        <el-table-column prop="content" label="留言正文"></el-table-column>
        <el-table-column prop="userName" label="留言账号"></el-table-column>
<!--        <el-table-column  label="留言账号">{{user.userName}}</el-table-column>-->

        <el-table-column fixed="right" label="操作">
          <template slot-scope="scope">
            <el-button
                el-button
                type="primary"
                icon="el-icon-edit"
                circle
                @click="updateBulletin(scope.row), (drawer = true)"
                size="small"
            ></el-button>
            <el-button
                type="danger"
                icon="el-icon-delete"
                circle
                @click="deteleBulletin(scope.row)"
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
        title="编辑留言"
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
        <el-form-item label="留言标题">
          <el-input v-model="form.bullTitle"></el-input>
        </el-form-item>
        <el-form-item label="留言类型">
          <el-input v-model="form.bullType"></el-input>
        </el-form-item>
        <el-form-item label="留言内容">
           <el-input v-model="form.content"></el-input>
        </el-form-item>
        <el-form-item label="留言账号">
          <el-input v-model="form.userName"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitBulletin">提交编辑</el-button>
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
        bullTitle: "",
        bullType: "",
        content:""
      },
      form: {
        id: "",
        bullTitle: "",
        bullType: "",
        content: "",
        userName: "",
      },
      user: {},
      bullTable: [],
      multipleSelection: [],
      pageTotal: "",
      departmentPageDto: {},
      bullType: [],
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
          .post("/bulletin/selectBulletinList", {
            pageNum: pageNum,
            bullTitle: this.formInline.bullTitle,
            bullType: this.formInline.bullType,

          })
          .then((Response) => {
            this.bullTable = Response.data.list;
            this.departmentPageDto = Response.data;
            console.log(this.departmentPageDto);
          })
          .catch((error) => {
            console.error(error);
          });
    },
    updateBulletin(row) {
      if (this.user.userType == "管理员") {
        console.log(row);
        this.form = row;
      } else {
        alert("很抱歉,您没有权限编辑留言信息");
        location.reload();
      }
      //第二种策略，跳转路由（CROS）
      // this.$router.push({
      //   path: "/index/updateDepartment",
      //   query: { row: row },
      // });
    },
    submitBulletin() {
      //axios编辑留言信息请求
      this.$axios
          .post("/bulletin/updateBulletinById", {
            id: this.form.id,
            bullTitle: this.form.bullTitle,
            bullType: this.form.bullType,
            content: this.form.content,
            userName: this.form.userName,
          })
          .then((Response) => {})
          .catch((error) => {
            console.error(error);
            alert("编辑留言信息失败");
          });
      alert("编辑留言信息成功");
      location.reload();
    },
    handleClose(done) {
      this.$confirm("确认关闭？")
          .then((_) => {
            done();
          })
          .catch((_) => {});
    },
    deteleBulletin(row) {
      if (this.user.userType == "管理员") {
        this.$confirm("确认删除此消息？")
            .then((_) => {
              console.log(row.id);
              //axios通过id删除药品信息
              this.$axios
                  .post("/bulletin/deleteBulletinById", {
                    id: row.id,
                  })
                  .then((Response) => {
                    alert("删除留言成功");
                  })
                  .catch((error) => {
                    console.error(error);
                    alert("删除留言失败");
                  });
            })
            .catch((_) => {});
      } else {
        alert("很抱歉,您没有权限删除留言");
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
          this.$confirm("确认批量删除留言信息？")
              .then((_) => {
                //axios批量删除请求
                this.$axios
                    .post("/bulletin/removeBulletinBatch", this.multipleSelection)
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
        alert("很抱歉,您没有权限批量删除留言信息");
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
      console.log(val);
    },
    paging(pageNum) {
      //axios查询药品列表请求(分页无参)
      this.$axios
          .post("/bulletin/selectBulletinList", {
            pageNum: pageNum,
          })
          .then((Response) => {
            this.bullTable = Response.data.list;
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
          .post("/bulletin/selectBullType")
          .then((Response) => {
            this.bullType = Response.data;
            console.log(this.userType);
          })
          .catch((error) => {
            console.error(error);
          });
    },
    newBull() {
        this.$router.push({
          path: "/index/newBulletin",
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

.box-card {
  width: 1400px;
}

.el-main {
  line-height: 0px;
}
</style>






