<template>
<div class="background">
  <div>
      <el-input
    placeholder="请输入内容"
    prefix-icon="el-icon-search"
    size='mini'
    style=" width: 20%;margin-left: 30%;"
    v-model="name"
    clearable>
  </el-input>
   <el-button icon="el-icon-search" type="primary" size='mini' style="margin-left: 1%;" @click="find" round>搜索</el-button>
  </div>
   <el-table
      :data="OrderDate"
      style="width: 60%;margin:auto"
       @row-click="Order2"
    >
      <el-table-column
        prop="id"
        label="订单号"
        width="180">
      </el-table-column>
      <el-table-column
        prop="createtime"
        label="时间"
        width="180">
      </el-table-column>
      <el-table-column
        prop="product"
        label="名称"
        width="180">
      </el-table-column>
      <el-table-column
        prop="price"
        label="金额"
        width="180">
      </el-table-column>
      <el-table-column
        prop="num"
        label="数量"
        width="180">
      </el-table-column>
      <el-table-column
        prop="state"
        label="状态"
        width="180">
      </el-table-column>
    </el-table>
    <el-dialog
  title="订单信息"
  :visible.sync="productVisible"
  width="20%"
   @close="cancel"
  >
  <label>{{Order.createtime}}</label>
   <div>
       <el-carousel :interval="4000"  height="250px" >
    <el-carousel-item v-for="(item2,index) in img" :key="index" >
       <img style="width:400px;height:250px;"  :src="item2"  alt="无图片"/>
    </el-carousel-item>
  </el-carousel>
            </div>
<div id='find2'>
  <div>
             <label>{{Order.title}}</label></div>
              <div id="find3">
            <label>商品名称：{{Order.product}}</label></div>
            <div id="find3">
            <label>类型：{{Order.type}} {{Order.size}}</label>
       </div>
             <div id="find3">
            <label>价格：￥{{Order.price}}</label></div>
            <div id="find3">
            <label>数量：{{Order.num}}</label></div>
            <div id="find3">
            <label>地址：{{user.provincial}}{{user.city}}{{user.address}}</label></div>
            <div id="find3">
            <label>客户：{{user.nickname}}</label></div>
            <div id="find3">
            <label>状态：</label>
            <el-select v-model="Order.state"  style=" width: 30%"  size= 'mini'>
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value"
     >
    </el-option>
  </el-select></div>
             <div id="find3">
          <label>共计：￥{{Order.price*Order.num}}</label></div>
</div>
  <span slot="footer" class="dialog-footer">
    <el-button @click="productVisible=false">取 消</el-button>
    <el-button type="primary" @click="update">确 定</el-button>
  </span></el-dialog>
    </div>
</template>

<script>
import qs from 'qs'
export default {
  data () {
    return {
      userid: '',
      OrderDate: [],
      name: '',
      Order: '',
      user: '',
      orderid: '',
      img: [],
      img1: [],
      options: [{
        value: '未发货',
        label: '未发货'
      },
      {
        value: '已发货',
        label: '已发货'
      },
      {
        value: '已送达',
        label: '已送达'
      },
      {
        value: '已退款',
        label: '已退款'
      }],
      productVisible: false
    }
  },
  created () {
    this.userid = this.$route.query.list
    this.loading()
  },
  methods: {
    loading () {
      this.axios.post('/api/demo/order/queryByProduct', qs.stringify({userid: this.userid})).then(res => {
        this.OrderDate = res.data
      })
    },
    find () {
      this.axios.post('/api/demo/order/queryById2', qs.stringify({id: this.name})).then(res => {
        this.OrderDate = res.data
      })
    },
    Order2 (row) {
      this.productVisible = true
      this.orderid = row.id
      this.axios.post('/api/demo/order/queryById3', qs.stringify({id: row.id})).then(res => {
        this.Order = res.data
        this.axios.post('/api/demo/product/findByImg', qs.stringify({productid: res.data.productid})).then(res => {
          this.img1 = res.data
          for (var i = 0; i < this.img1.length; i++) {
            this.img.push(require('../assets/' + res.data[i]))
          }
        })
        this.axios.post('/api/demo/user/findById', qs.stringify({id: res.data.id})).then(res => {
          this.user = res.data
        })
      })
    },
    cancel () {
      // this.productVisible = false
      this.img = []
      this.img1 = []
      this.user = ''
      this.Order = ''
    },
    update () {
      this.axios.post('/api/demo/order/update', qs.stringify({id: this.orderid, state: this.Order.state})).then(res => {
        this.productVisible = false
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

</style>
