<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>公告展示</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/index/bulletinBoard' }"
      >留言互动</el-breadcrumb-item>
    </el-breadcrumb>

    <el-row :gutter="20">
      <el-col :span="12" :offset="6">
        <!-- 头部排序功能 -->
        <el-button-group style="margin-top: 50px">
          <el-button type="primary">最新</el-button>
          <el-button type="primary">最热</el-button>
        </el-button-group>


        <!-- 留言列表 -->
        <div class="liuyan" v-for="(item,index) in list " :key='index'>
          <!-- 每一个留言列表3行  第一行:ueser 用户信息-->
          <div class="users">
            {{item.nikename}}:  {{item.createTime}}
          </div>
          <!-- 第二个留言内容 -->
          <div class="content">
            {{item.desc}}
          </div>
          <div class="list-bottom">
            <div class="left">
              <!-- 点赞 -->
              <span class="el-icon-star-on" @click.once="handleStar(item,index)"></span> {{item.star}}
              <el-link :underline="false">回复</el-link>
            </div>
            <!-- :underline='false' 去除下划线 -->
            <el-link type="danger" :underline='false' @click="del(index,item)">删除</el-link>
          </div>
        </div>


        <!-- 留言 -->
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="昵称">
            <!-- nikename代表留言框内的内容 -->
            <el-input v-model="form.nikename"></el-input>
          </el-form-item>
          <!-- lebel表示标签文本 -->
          <el-form-item label="留言">
            <!-- desc代表留言框内的内容 -->
            <el-input type="textarea" v-model="form.desc"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">留言</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>



  </div>

</template>



<script>
export default {
  data() {
    return {
      form: {
        id : '',
        // nikename是指昵称框内的内容
        nikename : '',
        // desc是指留言框内的内容
        desc : '',
        //createTime指的是时间
        createTime : '',
        //star是指点赞数量
        star : 0
      },
      //list 留言数据
      list : [],
      //默认没有点赞的颜色
      starColor : '#999'
    };
  },
  methods: {
    //onSubmit提交事件
    onSubmit() {
      // 创建时间
      let date = new Date();
      //将时间转化为X年 - X月 - X日 X时：X分：X秒 形式存入到form中的createTime中
      this.form.createTime = `${date.getFullYear()}-${date.getMonth()+1} - ${date.getDay()}  ${date.getHours()} : ${date.getMinutes()} : ${date.getDate()}`
      //将每一次的form中的内容追加到list(列表)中
      this.list.push(this.form)
      // 这里出现form是做的清空操作，当内容追加进去之后把所有的框内内容清空
      this.form =  {
        // nikename是指昵称框内的内容
        nikename : '',
        // desc是指留言框内的内容
        desc : '',
        //createTime指的是时间
        createTime : '',
        //star是指点赞数量
        star : 0,
      }
    },
    del(index,item){ //删除事件
      this.$confirm(`确定要删除${item.nikename}的留言吗?`, '删除提示', {
        confirmButtonText: '删除',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.list.splice(index,1)
        this.$message({
          message: '恭喜你，删除成功',
          type: 'success'
        });
      }).catch(() => {
      });
    },
    handleStar(item,index){ //点赞事件
      item.star++
    }
  },
};

</script >



<style>
/*给最新和最热两个框添加一个上边距*/
.tab {
  margin-top: 20px;
}
/*给留言部分加一个上下边距*/
.el-form{
  margin: 50px 0px;
}
/*给内容部分加一个上下边距*/
.content{
  margin: 20px 10px;
}
/*给点赞、回复、删除那个div加弹性盒子以及交叉轴两端对齐，轴线之间的间隔平均分布*/
.list-bottom{
  display: flex;
  justify-content: space-between;

}
/*给留言框设置一个下边框*/
.liuyan{
  border-bottom: 1px solid #999;
  margin: 20px 0px;
  padding: 10px 0px;
}
/*给点赞和回复设置一个划入时为手的形状*/
.left{
  cursor: pointer;
}

</style>
