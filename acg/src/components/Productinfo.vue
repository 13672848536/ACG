<template>
<div>
   <div  id='find'>
       <el-carousel   height="300px"  :autoplay = false>
    <el-carousel-item v-for="(item2,index) in companyCut(ProductInfo.img)" :key="index" >
       <img style="width:300px;height:300px;"  :src="item2"  alt="无图片"/>
    </el-carousel-item>
  </el-carousel>
            </div>
<div id='find2'>
  <div>
             <label>{{ProductInfo.title}}</label></div>
              <div id="find3">
            <label>商品名称：{{ProductInfo.product}}</label></div>
             <div id="find3">
            <label>价格：￥{{ProductInfo.price}}</label></div>
             <div id="find3">
          <label>物流：{{product.provincial}} {{product.city}} 至 {{user.provincial}} {{user.city}}</label></div>
          <div id="find3">
          <el-input-number v-model="num"  :min="1"  label="描述文字"></el-input-number>
          <label>库存：{{ProductInfo.stock}}</label>
          </div>
            <div id="find3" v-if=" ProductType3.length">
              <label>类型：</label>
              <el-radio v-model="type" v-for="(item2,index) in ProductType3" :key="index" :label = "item2" @change="findSize (item2)">{{item2}}</el-radio>
          </div>
           <div id="find3" v-if=" ProductType2.length">
             <label>大小：</label>
          <el-radio v-model="size" v-for="(item,index) in ProductType2" :key="index+1" :label = "item.size">{{item.size}}({{item.stock}})</el-radio></div>
           <div id="find3">
          <label>店铺：{{ProductInfo.username}}</label></div>
           <div id="find3">
          <label>共计：￥{{ProductInfo.price*num}}</label></div>
          <div id="find3">
            <label>销售量：{{ProductInfo.amount}}</label></div>
            <div style="background:#FFFF6F;
   color:#FFF" v-if="ProductInfo.state === '1'">
            <label>产品已下架</label></div>
             <div id="find3">
            <!-- <el-button plain @click="addOrder">立即购买</el-button> -->
            <el-button plain @click="addOrder">立即购买</el-button>
            <el-button plain @click="addShoppingCart"><i class="el-icon-shopping-cart-full"></i>加入购物车</el-button></div>
</div>
<div id='find4'>
<i class="el-icon-star-off"  v-if="star===false" @click="addFavorites">收藏商品</i>
<i class="el-icon-star-on" v-if="star" @click="deleteFavorites">收藏商品</i>
</div>
<div id='find4'>
<el-tabs :tab-position="tabPosition" style="height: 200px;" >
    <el-tab-pane label="详细介绍" name="first">
      <div>{{ProductInfo.content}}</div></el-tab-pane>
    <el-tab-pane label="评论" name="second">
      <div>
        <el-input v-model="name" placeholder="请输入内容" size='mini' style=" width: 60%;margin-left: 10%;">
          </el-input>
          <el-button type="primary" size='mini' @click="addcomment">发送</el-button>
          </div>
          <div v-for="(item,i) in Comment" :key="i" style="margin-top: 2%;">
            <div><label>{{item.createtime}}</label></div>
            <div>
              <img v-if="item.img" style="width:20px;height:20px;border-radius:100%;overflow:hidden;" :src="require('../assets/' + item.img)">
              <label>{{item.username}}:</label>
              <label>{{item.comment}}</label>
              <a id="reply" @click="item.value=true , Reply=[]">回复</a>
            </div>
            <div v-if="item.value===true">
              <a id="reply" @click="replydate(item.id)">查看评论</a>
              <div v-if="Reply">
              <div v-for="(item,i) in Reply" :key="i" style="margin-top: 2%;">
            <div><label>{{item.createtime}}</label></div>
            <div>
              <img v-if="item.img" style="width:20px;height:20px;border-radius:100%;overflow:hidden;" :src="require('../assets/' + item.img)">
              <label>{{item.username}}:</label>
              <label>{{item.comment}}</label>
            </div>
              </div>
              </div>
               <el-input
    placeholder="请输入内容"
    size='mini'
    style=" width: 30%;"
    v-model="reply1"
    clearable>
  </el-input>
              <a id="reply" @click="item.value=false , Reply=[]">取消回复</a>
              <a id="reply" @click="reply(item.id)">回复</a>
            </div>
          </div>
          </el-tab-pane>
  </el-tabs>
</div>
    </div>
</template>

<script>
import qs from 'qs'
export default {
  data () {
    return {
      userid: '',
      productid: '',
      ProductInfo: '',
      user: '',
      product: '',
      num: 1,
      type: '',
      size: '',
      star: false,
      tabPosition: 'top',
      name: '',
      reply1: '',
      OrderDate: {
        userid: '',
        productid: '',
        total: '',
        type: '',
        size: '',
        style: ''
      },
      Favorites: {
        userid: '',
        productid: ''
      },
      ShoppingCart: {
        userid: '',
        productid: '',
        amount: '',
        type: '',
        size: ''
      },
      Comment: [],
      Reply: [],
      ProductType2: [],
      ProductType3: []
    }
  },
  created () {
    this.userid = this.$route.query.id
    this.productid = this.$route.query.productid
    this.loading()
  },
  methods: {
    loading () {
      this.axios.post('/api/demo/product/findByProductId', qs.stringify({productid: this.productid})).then(res => {
        this.ProductInfo = res.data
        this.axios.post('/api/demo/user/findById', qs.stringify({id: res.data.id})).then(res => {
          this.product = res.data
        })
      })
      this.axios.post('/api/demo/user/findById', qs.stringify({id: this.userid})).then(res => {
        this.user = res.data
      })
      this.setFavorites()
      this.axios.post('/api/demo/favorites/findFavorites', this.Favorites).then(res => {
        if (res.data === true) {
          this.star = true
        } else {
          this.star = false
        }
      })
      this.axios.post('/api/demo/Comment/queryById', qs.stringify({productid: this.productid})).then(res => {
        this.Comment = res.data
      })
      this.axios.post('/api/demo/productType/findType', qs.stringify({productid: this.productid})).then(res => {
        this.ProductType3 = res.data
      })
    },
    addOrder () {
      if (this.userid) {
        this.OrderDate.userid = this.userid
        this.OrderDate.productid = this.productid
        this.OrderDate.total = this.num
        this.OrderDate.type = this.type
        this.OrderDate.size = this.size
        this.OrderDate.style = this.ProductInfo.style
        if (this.type) {
          if (this.ProductInfo.state !== '1') {
            this.axios.post('/api/demo/order/addOrder', this.OrderDate).then(res => {
              if (res.data === true) {
                this.$message({message: '购买成功', type: 'success', duration: 1000})
              } else {
                this.$message.error('购买数量超过库存')
              }
              this.loading()
            })
          } else {
            this.$message.error('产品已下架')
          }
        } else {
          this.$message.error('请选择产品类型')
        }
      } else {
        this.$message.error('请登录')
      }
    },
    addType (name) {
      this.type = name.type
      this.size = name.size
    },
    findSize (type) {
      this.axios.post('/api/demo/productType/findSize', qs.stringify({productid: this.productid, type: type})).then(res => {
        this.ProductType2 = res.data
      })
    },
    setFavorites () {
      this.Favorites.productid = this.productid
      this.Favorites.userid = this.userid
    },
    deleteFavorites () {
      this.setFavorites()
      this.axios.post('/api/demo/favorites/deleteFavorites', this.Favorites).then(res => {
        this.star = false
        this.loading()
      })
    },
    addFavorites () {
      if (this.userid) {
        this.setFavorites()
        this.axios.post('/api/demo/favorites/addFavorites', this.Favorites).then(res => {
          this.star = true
          this.loading()
        })
      } else {
        this.$message.error('请登录')
      }
    },
    addShoppingCart () {
      // this.ShoppingCart.userid = this.userid
      this.ShoppingCart.productid = this.productid
      this.ShoppingCart.amount = this.num
      this.ShoppingCart.type = this.type
      this.ShoppingCart.size = this.size
      if (this.type) {
      //   this.axios.post('/api/demo/ShoppingCart/addShoppingCart', this.ShoppingCart).then(res => {
      //     this.$message({message: '添加成功', type: 'success', duration: 1000})
      //   })
      // } else {
        if (this.ProductInfo.state !== '1') {
          this.axios.post('/api/demo/ShoppingCart/addCat', this.ShoppingCart).then(res => {
            this.$message({message: '添加成功', type: 'success', duration: 1000})
          })
        } else {
          this.$message.error('产品已下架')
        }
      } else {
        this.$message.error('请选择产品类型')
      }
    },
    addcomment () {
      this.axios.post('/api/demo/Comment/addComment', qs.stringify({userid: this.userid, productid: this.productid, comment: this.name})).then(res => {
        this.name = ''
        this.loading()
      })
    },
    replydate (id) {
      this.axios.post('/api/demo/Reply/queryById', qs.stringify({id: id})).then(res => {
        this.Reply = res.data
      })
    },
    reply (id) {
      this.axios.post('/api/demo/Reply/addReply', qs.stringify({userid: this.userid, id: id, reply: this.reply1})).then(res => {
        this.Reply = res.data
        this.reply1 = ''
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
  margin-top: 15%;
  margin-left: 20%;
  width:300px;
  border: 1px;
  display: inline-block
   }
   #find2{
  margin-top: 15%;
  margin-left: 2%;
  width:500px;
  border: 1px;
  display: inline-block
   }
  #find3{
  margin-top: 3%;
  margin-left: 2%;
  width:500px;
  border: 1px;
  display: inline-block
   }
   #find4{
  margin-left: 20%;
  margin-right: 45%;
   }
   #reply{
     color: #0000ff;
   }
</style>
