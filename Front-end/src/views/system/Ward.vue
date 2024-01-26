<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>入住管理</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/index/inWard' }"
        >房间管理</el-breadcrumb-item
      >
    </el-breadcrumb>
    <el-form
      :inline="true"
      :model="formInline"
      class="demo-form-inline"
      style="margin-top: 50px; line-height: 10px"
    >
      <el-form-item label="老人姓名">
        <el-input
          v-model="formInline.userName"
          placeholder="老人姓名"
        ></el-input>
      </el-form-item>
      <el-form-item label=" 房间分类">
        <el-select v-model="formInline.wardType" placeholder="房间分类">
          <el-option
            v-for="ward in wardType"
            :key="ward.id"
            :label="ward.wardType"
            :value="ward.wardType"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" plain @click="select">查询</el-button>

        <el-button type="danger" plain @click="resetForm"
          >重置</el-button
        ></el-form-item
      >
      <el-card class="box-card-wardView">
        <p style="padding:10px;margin-left:15px;">普通护理房</p>
        <p style="padding:10px;margin-left:15px;">剩余：{{ wardRemainingProportion.ptcount }}</p>
        <el-progress type="circle" :percentage="wardRemainingProportion.pt"></el-progress>
        <p style="padding:10px;margin-left:15px;">高级护理房</p>
        <p style="padding:10px;margin-left:15px;">剩余：{{ wardRemainingProportion.gjcount }}</p>
        <el-progress
          type="circle"
          :percentage="wardRemainingProportion.gj"
          color="rgb(20, 189, 133)"
        ></el-progress>
        <p style="padding:10px;margin-left:15px;">临终护理房</p>
        <p style="padding:10px;margin-left:15px;">剩余：{{ wardRemainingProportion.lzcount }}</p>
        <el-progress
          type="circle"
          :percentage="wardRemainingProportion.lz"
          color="orange"
        ></el-progress>
        <p style="padding:10px;margin-left:15px;">重症监护室</p>
        <p style="padding:10px;margin-left:15px;">剩余：{{ wardRemainingProportion.icucount }}</p>
        <el-progress
          type="circle"
          :percentage="wardRemainingProportion.icu"
          color="red"
        ></el-progress>
      </el-card>
    </el-form>
    <el-card class="box-card-wardViewThree">
      <el-table
        border
        text-align:
        center
        :cell-style="{ padding: '2px' }"
        :data="tableData"
        style="width: 100%"
        @selection-change="handleSelectionChange"
      >
        <el-table-column prop="id" label="入住编号"> </el-table-column>
        <el-table-column prop="wardCode" label="房间编号"> </el-table-column>
        <el-table-column prop="wardName" label="房间名称"> </el-table-column>
        <el-table-column prop="userName" label="老人姓名"> </el-table-column>
        <el-table-column prop="disease" label="诊断结果"> </el-table-column>
        <el-table-column prop="docName" label="负责医生"> </el-table-column>
        <el-table-column prop="inTime" label="入住时间" width="238">
        </el-table-column>
        <el-table-column prop="continuedTime" label="入住时长(月)">
        </el-table-column>
        <el-table-column prop="wardPrice" label="入住缴费金额">
        </el-table-column>
      </el-table>
    </el-card>
    <el-pagination
      background
      layout="prev, pager, next,total"
      :total="departmentPageDto.totalRow"
      :page-size="10"
      style="position: absolute; margin-left: 380px; margin-top: 30px"
      @current-change="handleCurrentChange"
    >
    </el-pagination>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData: [],
      user: {},
      formInline: {
        userName: "",
        wardType: "",
      },
      wardType: [],
      pageTotal: "",
      departmentPageDto: {},
      wardRemainingProportion: {
        pt: 0,
        gj: 0,
        lz: 0,
        icu: 0,
        ptcount: 0,
        gjcount: 0,
        lzcount: 0,
        icucount: 0,
      },
    };
  },
  created() {
    this.user = this.$getSessionStorage("user");
    this.selectWardRemainingProportion();
    this.paging(1);
    this.selectWardType();
  },
  methods: {
    select(pageNum) {
      //axios查询待入住列表请求(分页有参)
      if (isNaN(pageNum) == true) {
        pageNum = 1;
      }
      this.$axios
        .post("/ward/selectWardList", {
          userName: this.formInline.userName,
          wardType: this.formInline.wardType,
          pageNum: pageNum,
        })
        .then((Response) => {
          this.tableData = Response.data.list;
          this.departmentPageDto = Response.data;
          console.log(this.departmentPageDto);
        })
        .catch((error) => {
          console.error(error);
        });
    },
    selectWardType() {
      //axios查询房间类型请求
      this.$axios
        .post("/ward/selectWardType")
        .then((Response) => {
          this.wardType = Response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    },
    handleSelectionChange(val) {
      this.select(pageNumber);
    },
    resetForm() {
      location.reload();
    },
    paging(pageNum) {
      //axios查询待入住房间列表请求(分页无参)
      this.$axios
        .post("/ward/selectWardList", {
          pageNum: pageNum,
        })
        .then((Response) => {
          this.tableData = Response.data.list;
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
    selectWardRemainingProportion() {
      //axios查询房间剩余占比（数据可视化）
      this.$axios
        .post("/ward/selectWardRemainingProportion")
        .then((Response) => {
          this.wardRemainingProportion = Response.data;
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

.box-card-wardView {
  position: absolute;
  margin-left: 1150px;
  margin-top: -210px;
  width: 200px;
  font-size: 15px;
}
.box-card-wardViewThree {
  margin-top: 0px;
  width: 1150px;
}
</style>