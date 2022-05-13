<template>
 <el-tabs :tab-position="tabPosition">
    <el-tab-pane label="已买到的宝贝">
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
      <div   v-for="(item,i) in ProductInfo4" :key="i">
       <div id="find">
         <div>
            <label>{{item.createtime}}</label>
       </div>
        <div>
            <label>订单号：{{item.id}}</label>
       </div>
       <el-carousel   height="180px" :autoplay = false>
    <el-carousel-item v-for="(item2,index) in companyCut(item.img)" :key="index" >
       <img style="width:200px;height:150px;"  :src="item2"  alt="无图片"/>
    </el-carousel-item>
  </el-carousel>
  </div>
  <div id='find2'>
       <div id="find5">
            <label>{{item.title}}</label>
       </div>
       <div id="find5">
            <label>￥{{item.price}}</label>
       </div>
       <div id="find5">
            <label>类型：{{item.type}} {{item.size}}</label>
       </div>
        <div id="find5">
            <label>数量：{{item.num}}</label>
       </div>
        <div id="find5">
            <label>总价: ￥{{item.price*item.num}}</label>
       </div>
       <div id="find5">
          <label>状态：{{item.state}}</label></div>
       <div id="find5">
          <label>店铺：{{item.username}}</label>
            <label>销售量：{{item.amount}}</label>
       </div>
  </div>
            </div>
    </el-tab-pane>
    <el-tab-pane label="我的足迹">
     <div   v-for="(item,i) in ProductInfo" :key="i">
       <div id="find">
         <div>
            <label>{{item.createtime}}</label>
       </div>
       <el-carousel   height="180px" :autoplay = false>
    <el-carousel-item v-for="(item2,index) in companyCut(item.img)" :key="index" >
       <img style="width:200px;height:150px;"  :src="item2" @click="findproduct(item.productid)" alt="无图片"/>
    </el-carousel-item>
  </el-carousel>
  </div>
  <div id='find2'>
       <div id="find3">
            <label>{{item.title}}</label>
       </div>
       <div id="find3">
            <label>￥{{item.price}}</label>
       </div>
       <div id="find3">
          <label>店铺：{{item.username}}</label>
            <label>销售量：{{item.amount}}</label>
       </div>
  </div>
            </div>
    </el-tab-pane>
    <el-tab-pane label="购物车">
      <div v-for="(item,i) in ProductInfo3" :key="i">
        <div id='find'>
       <el-carousel   height="180px" :autoplay = false>
    <el-carousel-item v-for="(item2,index) in companyCut(item.img)" :key="index" :autoplay = false>
       <img style="width:200px;height:150px;"  :src="item2" @click="findproduct(item.productid)" alt="无图片"/>
    </el-carousel-item>
  </el-carousel>
  </div>
  <div id='find2'>
          <div id="find4">
            <label>{{item.title}}</label>
       </div>
       <div id="find4">
            <label>类型：{{item.type}} {{item.size}}</label>
       </div>
       <div id="find4">
            <label>￥{{item.price}}</label>
       </div>
       <div id="find4">
          <el-input-number v-model="item.num"  :min="1"  label="描述文字" size ='mini'></el-input-number>
          </div>
       <div id="find4">
          <label>共计：￥{{item.price*item.num}}</label></div>
       <div id="find4">
          <label>店铺：{{item.username}}</label>
            <label>销售量：{{item.amount}}</label>
       </div>
       <div id="find4">
            <el-button plain  @click="addOrder(item)" size='mini'>立即购买</el-button>
            <el-button plain  @click="deletecart (item)" size='mini'><i class="el-icon-shopping-cart-full"></i>删除</el-button></div>
  </div>
            </div>
    </el-tab-pane>
    <el-tab-pane label="收藏">
      <div v-for="(item,i) in ProductInfo2" :key="i">
        <div id='find'>
       <el-carousel   height="180px" :autoplay = false>
    <el-carousel-item v-for="(item2,index) in companyCut(item.img)" :key="index" :autoplay = false>
       <img style="width:200px;height:150px;"  :src="item2" @click="findproduct(item.productid)" alt="无图片"/>
    </el-carousel-item>
  </el-carousel>
  </div>
  <div id='find2'>
          <div id="find3">
            <label>{{item.title}}</label>
       </div>
       <div id="find3">
            <label>￥{{item.price}}</label>
       </div>
       <div id="find3">
          <label>店铺：{{item.username}}</label>
            <label>销售量：{{item.amount}}</label>
       </div>
  </div>
            </div>
    </el-tab-pane>
  </el-tabs>
</template>

<script>
import qs from 'qs'
export default {
  data () {
    return {
      tabPosition: 'left',
      userid: '',
      ProductInfo: [],
      ProductInfo2: [],
      ProductInfo3: [],
      ProductInfo4: [],
      browse: {
        userid: '',
        productid: ''
      },
      OrderDate: {
        userid: '',
        productid: '',
        total: '',
        size: '',
        type: ''
      },
      ShoppingCart: {
        id: '',
        userid: '',
        productid: '',
        amount: '',
        type: '',
        size: ''
      },
      name: ''
    }
  },
  created () {
    this.userid = this.$route.query.list
    this.loading()
  },
  methods: {
    loading () {
      this.axios.post('/api/demo/browse/queryById', qs.stringify({userid: this.userid})).then(res => {
        this.ProductInfo = res.data
      })
      this.axios.post('/api/demo/favorites/queryById', qs.stringify({userid: this.userid})).then(res => {
        this.ProductInfo2 = res.data
      })
      // if (this.userid) {
      //   this.axios.post('/api/demo/ShoppingCart/queryById', qs.stringify({userid: this.userid})).then(res => {
      //     this.ProductInfo3 = res.data
      //   })
      // } else {
      this.axios.post('/api/demo/ShoppingCart/showCat').then(res => {
        this.ProductInfo3 = res.data
      })
      // }
      this.axios.post('/api/demo/order/queryById', qs.stringify({userid: this.userid})).then(res => {
        this.ProductInfo4 = res.data
      })
    },
    find () {
      this.axios.post('/api/demo/order/queryById2', qs.stringify({id: this.name})).then(res => {
        this.ProductInfo4 = res.data
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
    addOrder (cat) {
      if (this.userid) {
        this.OrderDate.userid = this.userid
        this.OrderDate.productid = cat.productid
        this.OrderDate.total = cat.num
        this.OrderDate.size = cat.size
        this.OrderDate.type = cat.type
        this.axios.post('/api/demo/order/addOrder', this.OrderDate).then(res => {
          if (res.data === true) {
            this.$message({message: '购买成功', type: 'success', duration: 1000})
            this.ShoppingCart.productid = cat.productid
            this.ShoppingCart.amount = cat.num
            this.ShoppingCart.type = cat.type
            this.ShoppingCart.size = cat.size
            this.ShoppingCart.id = cat.id
            this.axios.post('/api/demo/ShoppingCart/deleteId', this.ShoppingCart).then(res => {
              this.loading()
            })
          } else {
            this.$message.error('购买数量超过库存或者产品已下架')
          }
        })
      } else {
        this.$message.error('请登录账号')
      }
    },
    deletecart (cat) {
      // this.ShoppingCart.userid = this.userid
      this.ShoppingCart.productid = cat.productid
      this.ShoppingCart.amount = cat.num
      this.ShoppingCart.type = cat.type
      this.ShoppingCart.size = cat.size
      this.ShoppingCart.id = cat.id
      this.axios.post('/api/demo/ShoppingCart/deleteId', this.ShoppingCart).then(res => {
        this.$message({message: '删除成功', type: 'success', duration: 6000})
        this.loading()
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
#find{
  width:200px;
  border: 1px;
  margin: 15px;
  display: inline-block
   }
    #find2{
  width:300px;
  border: 1px;
  display: inline-block
   }
   #find3{
  margin-top: 10%;
  width:300px;
  border: 1px;
  display: inline-block
   }
   #find4{
  margin-top: 3%;
  width:300px;
  border: 1px;
  display: inline-block
   }
   #find5{
  margin-top: 2%;
  width:300px;
  border: 1px;
  display: inline-block
   }
</style>
