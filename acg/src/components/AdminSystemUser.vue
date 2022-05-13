<template>
<div>
    <el-tabs v-model="activeName" type="card" >
    <el-tab-pane label="用户管理" name="first">
      <div>
      <!-- <el-select v-model="type" placeholder="请选择" size='mini' style=" width: 6%;margin-left: 5%" @change ='findtype'>
    <el-option
      v-for="item in options"
      :key="item.type"
      :label="item.label"
      :value="item.type"
      >
    </el-option>
  </el-select> -->
  <el-input
  placeholder="请输入用户名"
  v-model="nickname"
  size='mini'
  style=" width: 7%;margin-left: 5%"
  clearable>
</el-input>
 <el-button type="primary" size='mini' style=" width: 5%" @click="find">搜索</el-button>
      </div>
      <div>
        <el-table
      :data=" UserInfo"
      style="width: 90%;margin:auto">
      <el-table-column
        prop="nickname"
        label="用户名"
        width="100">
      </el-table-column>
      <el-table-column
        prop="sex"
        label="性别"
        width="100">
      </el-table-column>
      <el-table-column
        prop="year"
        label="年龄"
        width="100">
      </el-table-column>
      <el-table-column
        prop="type"
        label="类型"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="100">
      </el-table-column>
      <el-table-column
        prop="phone"
        label="电话号码"
        width="180">
      </el-table-column>
      <el-table-column
        prop="email"
        label="e-mail"
        width="180">
      </el-table-column>
       <el-table-column
        prop="provincial"
        label="provincial"
        width="180">
      </el-table-column>
       <el-table-column
        prop="city"
        label="city"
        width="180">
      </el-table-column>
      <el-table-column
        prop="address"
        label="地址"
        width="200">
      </el-table-column>
    </el-table>
      </div><el-pagination
            background
            id="fenye"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[10, 20]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
    </el-pagination>
    </el-tab-pane>
    <el-tab-pane label="数据可视化" name="second">
      <div><label>各年龄段最喜欢的商品类型</label></div>
      <div>
        <label>20岁以下:</label>
        <label>{{typedata[2]}}</label>
         <label>20岁至25岁之间:</label>
        <label>{{typedata[1]}}</label>
         <label>25岁以上:</label>
        <label>{{typedata[0]}}</label>
      </div>
      <div id="find">
      <div id="myChart" :style="{width: '400px', height: '400px'}"></div>
      </div>
       <div id="find">
        <div id="Chart" :style="{width: '400px', height: '400px'}"></div></div>
         <div id="find">
        <div id="addressChart" :style="{width: '550px', height: '400px'}"></div>
         </div></el-tab-pane>
  </el-tabs>
</div>
</template>

<script>
import qs from 'qs'
export default {
  data () {
    return {
      nickname: '',
      activeName: 'first',
      options: [{
        type: '普通用户',
        label: '普通用户'
      },
      {
        type: '管理员',
        label: '管理员'
      }],
      UserInfo: [],
      type: '',
      agedata: [],
      typedata: [],
      imgSrc: require('../assets/login4.jpeg'),
      currentPage: 1, // 当前页码
      pageSize: 10, // 每页大小
      total: 150,
      name: ''
    }
  },
  created () {
    this.axios.post('/api/demo/user/find', qs.stringify({nickname: this.nickname, currentPage: 1, pageSize: 10})).then(res => {
      this.UserInfo = res.data
    })
    this.axios.post('/api/demo/user/findTotal', qs.stringify({name: this.name})).then(res => {
      this.total = res.data
    })
  },
  mounted () {
    this.drawLine()
    this.yeardata()
    this.addressdata()
    this.Typedata()
  },
  methods: {
    handleSizeChange (val) {
      this.axios.post('/api/demo/user/find', qs.stringify({nickname: this.name, currentPage: this.currentPage, pageSize: val})).then(res => {
        this.UserInfo = res.data
      })
    },
    // 当前改变----当前页码改变之后，触发这个函数
    handleCurrentChange (val) {
      this.axios.post('/api/demo/user/find', qs.stringify({nickname: this.name, currentPage: val, pageSize: this.pageSize})).then(res => {
        this.UserInfo = res.data
      })
    },
    find () {
      this.name = this.nickname
      this.axios.post('/api/demo/user/findTotal', qs.stringify({name: this.name})).then(res => {
        this.total = res.data
      })
      this.axios.post('/api/demo/user/find', qs.stringify({nickname: this.nickname, currentPage: 1, pageSize: 10})).then(res => {
        this.UserInfo = res.data
      })
    },
    findtype () {
      this.axios.post('/api/demo/user/findByType', qs.stringify({type: this.type})).then(res => {
        this.UserInfo = res.data
      })
    },
    drawLine () {
      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById('myChart'))
      // 绘制图表
      this.axios.post('/api/demo/user/findSex').then(res => {
        myChart.setOption({
          title: {
            text: '用户男女比例',
            left: 'center'
          },
          tooltip: {
            trigger: 'item'
          },
          legend: {
            orient: 'vertical',
            left: 'left'
          },
          series: [
            {
              type: 'pie',
              radius: '50%',
              label: { // 饼图图形上的文本标签
                normal: {
                  show: true,
                  formatter: '{d}%'}},
              data: res.data
            }
          ]
        })
      })
    },
    yeardata () {
      // 基于准备好的dom，初始化echarts实例
      let Chart = this.$echarts.init(document.getElementById('Chart'))
      // 绘制图表
      this.axios.post('/api/demo/user/findAge').then(res => {
        this.agedata = res.data
        Chart.setOption({
          title: {
            text: '用户年龄比例',
            left: 'center'
          },
          tooltip: {
            trigger: 'item'
          },
          legend: {
            orient: 'vertical',
            left: 'left'
          },
          xAxis: {
            type: 'category',
            data: ['26-', '21-25', '-20']
          },
          yAxis: {
            type: 'value'
          },
          series: [{
            data: this.agedata,
            type: 'bar'
          }
          ]
        })
      })
    },
    Typedata () {
      this.axios.post('/api/demo/product/SumType').then(res => {
        this.typedata = res.data
      })
    },
    addressdata () {
      // 基于准备好的dom，初始化echarts实例
      let addressChart = this.$echarts.init(document.getElementById('addressChart'))
      // 绘制图表
      this.axios.post('/api/demo/user/findAddress').then(res => {
        addressChart.setOption({
          title: {
            text: '用户地区分布',
            left: 'center'
          },
          tooltip: {
            trigger: 'item'
          },
          legend: {
            orient: 'vertical',
            left: 'left'
          },
          series: [
            {
              type: 'pie',
              radius: '50%',
              label: { // 饼图图形上的文本标签
                normal: {
                  show: true,
                  formatter: '{d}%'}},
              data: res.data
            }
          ]
        })
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#img{
width:40px;
height:40px
}
#find{
width:550px;
  border: 1px;
  margin: 15px;
  display: inline-block}
#fenye{
margin-top: 1%;
margin-left: 55%;
  }
</style>
