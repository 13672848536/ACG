<template>
<div>
  <div>
    <h1>ACG商城</h1>
  <!-- <img id="img" :src="require('../assets/4.jpg')"/> -->
  </div>
  <div>
  <el-input
    placeholder="请输入内容"
    prefix-icon="el-icon-search"
    size='mini'
    style=" width: 30%;margin-left: 30%;"
    v-model="name"
    clearable>
  </el-input>
   <el-button icon="el-icon-search" type="primary" size='mini' style="margin-left: 1%;" @click="find" round>搜索</el-button>
  </div>
  <div style="margin-left: 58%;">
    <a v-if="type === false" @click="type = true">分类</a>
    <a v-if="type === true" @click="type = false" style=" color: #0000ff;">分类</a>
   </div>
   <div style="margin-left: 30%; width:30%">
    <template v-if="type === true">
  <div id='type' v-for="(item,index) in options" :key="index+10">
    <a @click=" findtype (item)">{{item}}</a>
  </div>
    </template>
   </div>
   <div v-if="ProductInfo2.length >0">
    <div id="find2"><label>猜你喜欢</label></div>
     <div  id='find' v-for="(item,i) in ProductInfo2" :key="i">
       <template v-if="item.img">
       <el-carousel   height="250px" :autoplay = false >
    <el-carousel-item v-for="(item2,index) in companyCut(item.img)" :key="index" >
       <img style="width:300px;height:250px;"  :src="item2" @click="findproduct(item.productid)" alt="无图片"/>
    </el-carousel-item>
  </el-carousel>
       </template>
       <div>
            <label>{{item.title}}</label>
       </div>
       <div>
            <label>￥{{item.price}}</label>
       </div>
       <div>
          <label>店铺：{{item.username}}</label>
            <label>销售量：{{item.amount}}</label>
       </div>
            </div></div>
   <div id="find2"><label>商品栏</label></div>
     <div  id='find' v-for="(item,i) in ProductInfo" :key="i">
       <template v-if="item.img">
       <el-carousel   height="250px" :autoplay = false >
    <el-carousel-item v-for="(item2,index) in companyCut(item.img)" :key="index" >
       <img style="width:300px;height:250px;"  :src="item2" @click="findproduct(item.productid)" alt="无图片"/>
    </el-carousel-item>
  </el-carousel>
       </template>
       <div>
            <label>{{item.title}}</label>
       </div>
       <div>
            <label>￥{{item.price}}</label>
       </div>
       <div>
          <label>店铺：{{item.username}}</label>
            <label>销售量：{{item.amount}}</label>
       </div>
            </div>
            <el-pagination
            background
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[10, 20]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
    </el-pagination>
</div>
</template>

<script>
import qs from 'qs'
export default {
  data () {
    return {
      userid: '',
      name: '',
      ProductInfo: [],
      ProductInfo2: [],
      options: [],
      browse: {
        userid: '',
        productid: ''
      },
      currentPage: 1, // 当前页码
      pageSize: 10, // 每页大小
      total: 1000,
      type: false,
      names: ''
    }
  },
  created () {
    this.userid = this.$route.query.list
    this.loading()
  },
  methods: {
    handleSizeChange (val) {
      this.axios.post('/api/demo/product/find2', qs.stringify({name: this.names, currentPage: this.currentPage, pageSize: val})).then(res => {
        this.ProductInfo = res.data
      })
    },
    // 当前改变----当前页码改变之后，触发这个函数
    handleCurrentChange (val) {
      this.axios.post('/api/demo/product/find2', qs.stringify({name: this.names, currentPage: val, pageSize: this.pageSize})).then(res => {
        this.ProductInfo = res.data
      })
    },
    loading () {
      // this.axios.post('/api/demo/product/findAll').then(res => {
      //   this.ProductInfo = res.data
      // })
      this.axios.post('/api/demo/product/find2', qs.stringify({name: this.name, currentPage: 1, pageSize: 10})).then(res => {
        this.ProductInfo = res.data
      })
      this.axios.post('/api/demo/product/find3', qs.stringify({id: this.userid})).then(res => {
        this.ProductInfo2 = res.data
      })
      this.axios.post('/api/demo/product/findType2').then(res => {
        this.options = res.data
      })
      this.axios.post('/api/demo/product/findTotal', qs.stringify({name: this.name})).then(res => {
        this.total = res.data
      })
    },
    findtype (item) {
      this.names = item
      this.ProductInfo2 = []
      this.axios.post('/api/demo/product/findTotal', qs.stringify({name: item})).then(res => {
        this.total = res.data
      })
      this.axios.post('/api/demo/product/find2', qs.stringify({name: item, currentPage: 1, pageSize: 10})).then(res => {
        this.ProductInfo = res.data
      })
    },
    find () {
      this.names = this.name
      this.ProductInfo2 = []
      this.axios.post('/api/demo/product/findTotal', qs.stringify({name: this.name})).then(res => {
        this.total = res.data
      })
      this.axios.post('/api/demo/product/find2', qs.stringify({name: this.name, currentPage: 1, pageSize: 10})).then(res => {
        this.ProductInfo = res.data
      })
    },
    findproduct (productid) {
      this.browse.userid = this.userid
      this.browse.productid = productid
      let routeData = this.$router.resolve({name: 'productinfo', query: { id: this.userid, productid: productid }})
      window.open(routeData.href, '_blank')
      this.axios.post('/api/demo/browse/addBrowse', qs.stringify(this.browse)).then(res => {
      })
      this.axios.post('/api/demo/product/updateapv', qs.stringify({productid: productid})).then(res => {
      })
    },
    companyCut (name) {
      let company1 = []
      let company = (name || '').split(',')
      for (var i = 0; i < company.length; i++) {
        company1.push(require('../assets/' + company[i]))
      }
      return company1
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#img{
width:200px;
height:80px;
margin-left: 40%;
}
#find{
  width:300px;
  border: 1px;
  margin: 30px;
  display: inline-block
   }
#type{
  border: 1px;
  margin: 5px;
  display: inline-block
   }
   h1{
    font-size: 2em;
    width:200px;
    margin-left: 40%;
    color:#000;
    text-shadow:0 0 10px;
    letter-spacing: 1px;
    text-align: center
}
#find2{
  background:#BEBEBE;
   color:#FFF
}
</style>
