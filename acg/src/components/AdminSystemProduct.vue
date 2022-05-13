<template>
<div>
    <el-tabs v-model="activeName" type="card" >
    <el-tab-pane label="商品管理" name="first">
      <div>
      <el-select v-model="types" placeholder="请选择" size='mini' style=" width: 6%;margin-left: 10%" @change ='findtype'>
    <el-option
      v-for="item in options"
      :key="item"
      :label="item"
      :value="item"
      >
    </el-option>
  </el-select>
  <el-input
  placeholder="请输入商品名称"
  v-model="productname"
  size='mini'
  style=" width: 7%"
  clearable>
</el-input>
 <el-button type="primary" size='mini' style=" width: 5%" @click="find">搜索</el-button>
 <el-button type="primary" size='mini' style=" width: 5%" @click="Visible = true">添加</el-button>
 <el-button type="primary" size='mini' style=" width: 5%" @click="deletebyproduct">删除</el-button>
      </div>
      <div>
        <el-table
      :data=" ProductInfo"
      style="width: 80%;margin:auto"
     @row-click="productinfo2"
    @selection-change="selsChange">
     <el-table-column
      type="selection"
      width="55"
     >
    </el-table-column>
    <el-table-column
        prop="productid"
        label="ID"
        width="180">
      </el-table-column>
      <el-table-column
        prop="product"
        label="商品名称"
        width="180">
      </el-table-column>
      <el-table-column
        prop="type"
        label="类型"
        width="180">
      </el-table-column>
      <el-table-column
        prop="price"
        label="价格"
        width="180">
      </el-table-column>
      <el-table-column
        prop="amount"
        label="购买量"
        width="180">
      </el-table-column>
      <el-table-column
        prop="apv"
        label="点击量"
        width="180">
      </el-table-column>
      <el-table-column
        prop="stock"
        label="库存"
        width="180">
      </el-table-column>
    </el-table>
      </div>
      <el-dialog
  title="添加商品"
  :visible.sync="Visible"
  width="20%"
  >
<div class="form">
          <label>商品名称：</label><input type="text" v-model="product" style="margin-left: 10%;"><br/>
        </div>
        <div class="form">
          <label>类型：</label><input type="text" v-model="type" style="margin-left: 10%;"><br/>
        </div>
        <div class="form">
          <label>价格：</label><input type="text" v-model="price" style="margin-left: 5%;"><br/>
        </div>
        <div class="form">
          <label>标题：</label><input type="text" v-model="title" style="margin-left: 0.5px;"><br/>
  </div>
  <div class="form">
          <label>详细介绍：</label><el-input type="textarea"
  :autosize="{ minRows: 2, maxRows: 4}"
  v-model="content" style="margin-left: 0.5px;"></el-input><br/>
  </div>
  <span slot="footer" class="dialog-footer">
    <el-button @click="Visible = false">取 消</el-button>
    <el-button type="primary" @click="addProduct">确 定</el-button>
  </span>
</el-dialog>
<el-dialog
  title="商品信息"
  :visible.sync="productVisible"
  width="50%"
  @close="cancel"
  >
  <div id="find">
  <template v-if="img">
  <el-carousel indicator-position="none" trigger="click" height="250px" :autoplay = false>
    <el-carousel-item v-for="(item,index) in img1" :key="index">
       <img id='img' :src="item" alt="无图片"/>
    </el-carousel-item>
  </el-carousel>
  </template>
   <el-upload
            class="avatar-uploader"
            action="/api/demo/product/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
            :data= "{'productid':productid}"
            style="display: inline-block;width: 300px"
          >
          <!-- :before-upload="beforeAvatarUpload" -->
          <el-button type="primary"><i class="el-icon-upload el-icon--right"></i>添加图片</el-button>
          </el-upload>
          <template v-if="img">
          <div  v-for="(item,i) in img" :key="i">
            <label>{{item}}</label>
            <a @click="deleteimg(item)" >删除</a>
            </div>
          </template></div>
          <div id="find2">
            <div>
               <el-button type="primary" @click="productTypeVisible = true">添加类型</el-button>
            </div>
            <div v-if=" ProductType2">
        <el-table
      :data=" ProductType2"
      style="width: 80%"
   >
      <el-table-column
        prop="type"
        label="商品类型"
        width="200">
      </el-table-column>
      <el-table-column
        prop="size"
        label="大小"
        width="50">
      </el-table-column>
      <el-table-column
        prop="stock"
        label="库存"
        width="80">
      </el-table-column>
      <el-table-column
      fixed="right"
      label="操作"
      width="50">
      <template slot-scope="scope">
        <a @click="deleteType(scope.row)"  >删除</a>
      </template>
    </el-table-column>
      </el-table>
      </div>
<div class="form">
          <label>商品名称：</label><el-input type="text" v-model="product" size="mini"></el-input><br/>
        </div>
        <div class="form">
          <label>类型：</label><el-input type="text" v-model="type" size="mini"></el-input><br/>
        </div>
        <div class="form">
          <label>价格：</label><el-input type="text" v-model="price" size="mini"></el-input><br/>
        </div>
        <div class="form">
          <label>标题：</label><el-input type="textarea"
   v-model="title" :rows="1"></el-input><br/>
  </div>
  <div class="form">
          <label>库存：</label><label>{{stock}}</label><br/>
  </div>
   <div id='find4'>
<el-tabs style="height: 200px;" >
    <el-tab-pane label="详细介绍" name="first">
      <el-input
  type="textarea"
  :autosize="{ minRows: 4, maxRows: 6}"
  placeholder="请输入内容"
  v-model="content">
</el-input>
    </el-tab-pane>
    <el-tab-pane label="评论" name="second">
          <div v-for="(item,i) in Comment" :key="i" style="margin-top: 2%;">
            <div><label>{{item.createtime}}</label></div>
            <div>
              <img v-if="item.img" style="width:20px;height:20px;border-radius:100%;overflow:hidden;" :src="require('../assets/' + item.img)">
              <label>{{item.username}}:</label>
              <label>{{item.comment}}</label>
              <a id="reply" @click="item.value=true, Reply=[]">回复</a>
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
  </el-tabs></div>
          </div>
  <span slot="footer" class="dialog-footer">
    <el-button @click="productVisible = false">取 消</el-button>
    <el-button type="primary" @click="updateProduct">确 定</el-button>
  </span>
</el-dialog>
<el-dialog
  title="商品类型信息"
  :visible.sync="productTypeVisible"
  width="20%"
  >
  <div class="form">
          <label>类型：</label><input type="text" v-model="producttype" style="margin-left: 5%;"><br/>
        </div>
        <div class="form">
          <label>大小：</label><input type="text" v-model="size" style="margin-left: 5%;"><br/>
        </div>
        <div class="form">
          <label>库存：</label><input type="text" v-model="Stock" style="margin-left: 5%;"><br/>
        </div>
        <span slot="footer" class="dialog-footer">
    <el-button @click="productTypeVisible = false">取 消</el-button>
    <el-button type="primary" @click="addProductType">确 定</el-button>
  </span>
</el-dialog>
    </el-tab-pane>
    <el-tab-pane label="恢复商品数据" name="three">
      <div>
  <el-input
  placeholder="请输入商品名称"
  v-model="product2"
  size='mini'
  style=" width: 7%;margin-left: 10%"
  clearable>
</el-input>
 <el-button type="primary" size='mini' style=" width: 5%" @click="find2">搜索</el-button>
 <el-button type="primary" size='mini' style=" width: 5%" @click="RecoveryProduct ">还原</el-button>
      </div>
      <div>
        <el-table
      :data=" ProductInfo2"
      style="width: 80%;margin:auto"
    @selection-change="selsChange">
     <el-table-column
      type="selection"
      width="55"
     >
    </el-table-column>
    <el-table-column
        prop="productid"
        label="ID"
        width="180">
      </el-table-column>
      <el-table-column
        prop="product"
        label="商品名称"
        width="180">
      </el-table-column>
      <el-table-column
        prop="type"
        label="类型"
        width="180">
      </el-table-column>
      <el-table-column
        prop="price"
        label="价格"
        width="180">
      </el-table-column>
      <el-table-column
        prop="amount"
        label="购买量"
        width="180">
      </el-table-column>
      <el-table-column
        prop="apv"
        label="点击量"
        width="180">
      </el-table-column>
      <el-table-column
        prop="stock"
        label="库存"
        width="180">
      </el-table-column>
    </el-table>
      </div>
    </el-tab-pane>
    <el-tab-pane label="数据可视化" name="second">
      <div>
      <el-input
  placeholder="请输入商品ID"
  v-model="id"
  size='mini'
  style=" width: 7%"
  clearable>
</el-input>
 <el-button type="primary" size='mini' style=" width: 5%" @click="selectechart">搜索</el-button></div>
  <div id='find3'>
        <div id="Chart" :style="{width: '500px', height: '400px'}"></div></div>
 <div id='find3'>
      <div id="yearChart" :style="{width: '500px', height: '400px'}"></div></div>
       </el-tab-pane>
  </el-tabs>
</div>
</template>

<script>
import qs from 'qs'
export default {
  data () {
    return {
      userid: '',
      id: '',
      product: '',
      product2: '',
      productname: '',
      productid: '',
      title: '',
      content: '',
      type: '',
      price: '',
      stock: '',
      types: '',
      reply1: '',
      producttype: '',
      size: '',
      Stock: '',
      img1: [],
      img: [],
      activeName: 'first',
      options: [],
      ProductInfo: [],
      ProductInfo2: [],
      Comment: [],
      Reply: [],
      ProductType2: [],
      ProductType: {
        type: '',
        id: ''
      },
      images: [
        require('../assets/login4.jpeg')
      ],
      productName: {
        product: '',
        id: ''
      },
      ProductDTO: {
        id: '',
        product: '',
        title: '',
        content: '',
        type: '',
        price: '',
        stock: ''
      },
      productinfo1: {
        productid: '',
        product: '',
        title: '',
        content: '',
        type: '',
        price: ''
      },
      productType: {
        productid: '',
        type: '',
        size: '',
        stock: ''
      },
      imgdata: {
        img: '',
        productid: ''
      },
      yearsdata: [],
      totalvalue: [],
      sels: [], // 选中的值显示
      Visible: false,
      productVisible: false,
      productTypeVisible: false,
      imgSrc: require('../assets/login4.jpeg')
    }
  },
  created () {
    this.userid = this.$route.query.list
    this.loading()
  },
  // mounted () {
  //   this.drawLine()
  //   this.addressdata()
  // },
  methods: {
    loading () {
      this.axios.post('/api/demo/product/findById', qs.stringify({id: this.userid})).then(res => {
        this.ProductInfo = res.data
      })
      this.axios.post('/api/demo/product/findById2', qs.stringify({id: this.userid})).then(res => {
        this.ProductInfo2 = res.data
      })
      this.axios.post('/api/demo/product/findType', qs.stringify({id: this.userid})).then(res => {
        this.options = res.data
      })
    },
    setUserinfo () {
      this.ProductDTO.id = this.userid
      this.ProductDTO.product = this.product
      this.ProductDTO.title = this.title
      this.ProductDTO.content = this.content
      this.ProductDTO.type = this.type
      this.ProductDTO.price = this.price
      this.ProductDTO.stock = this.stock
    },
    addProduct () {
      this.setUserinfo()
      this.axios.post('/api/demo/product/addProduct', this.ProductDTO).then((rse) => {
        this.loading()
      })
      this.Visible = false
      this.product = ''
      this.title = ''
      this.content = ''
      this.type = ''
      this.price = ''
      this.stock = ''
    },
    find () {
      this.productName.product = this.productname
      this.productName.id = this.userid
      this.axios.post('/api/demo/product/find', qs.stringify(this.productName)).then(res => {
        this.ProductInfo = res.data
        this.product = ''
      })
    },
    find2 () {
      this.productName.product = this.product2
      this.productName.id = this.userid
      this.axios.post('/api/demo/product/find4', qs.stringify(this.productName)).then(res => {
        this.ProductInfo2 = res.data
        this.product = ''
      })
    },
    findtype () {
      this.ProductType.type = this.types
      this.ProductType.id = this.userid
      this.axios.post('/api/demo/product/findByType', qs.stringify(this.ProductType)).then(res => {
        this.ProductInfo = res.data
      })
    },
    productinfo2 (row) {
      this.productVisible = true
      this.product = row.product
      this.title = row.title
      this.content = row.content
      this.type = row.type
      this.price = row.price
      this.stock = row.stock
      this.productid = row.productid
      this.axios.post('/api/demo/product/findByImg', qs.stringify({productid: row.productid})).then(res => {
        this.img = res.data
        for (var i = 0; i < this.img.length; i++) {
          this.img1.push(require('../assets/' + res.data[i]))
        }
      })
      this.axios.post('/api/demo/Comment/queryById', qs.stringify({productid: this.productid})).then(res => {
        this.Comment = res.data
      })
      this.axios.post('/api/demo/productType/queryById', qs.stringify({productid: this.productid})).then(res => {
        this.ProductType2 = res.data
      })
    },
    setproductinfo () {
      this.productinfo1.productid = this.productid
      this.productinfo1.product = this.product
      this.productinfo1.title = this.title
      this.productinfo1.content = this.content
      this.productinfo1.type = this.type
      this.productinfo1.price = this.price
    },
    updateProduct () {
      this.setproductinfo()
      this.axios.post('/api/demo/product/updateProduct', qs.stringify(this.productinfo1)).then((rse) => {
        this.loading()
      })
      this.productVisible = false
      this.img = ''
    },
    cancel () {
      // this.productVisible = false
      this.img = ''
      this.img1 = []
      this.product = ''
      this.title = ''
      this.content = ''
      this.type = ''
      this.price = ''
      this.stock = ''
      this.Comment = []
      this.Reply = []
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
    deleteimg (item) {
      this.imgdata.img = item
      this.imgdata.productid = this.productid
      this.axios.post('/api/demo/product/deleteImg', qs.stringify(this.imgdata)).then((rse) => {
        this.img = rse.data
      })
    },
    selsChange (sels) {
      this.sels = sels
    },
    deleteType (name) {
      this.axios.post('/api/demo/productType/deleteByProductType', qs.stringify({productid: this.productid, type: name.type, size: name.size})).then((rse) => {
      })
    },
    deletebyproduct () {
      var productids = this.sels.map(item => item.productid).join() // 获取所有选中行的id组成的字符串，以逗号分隔
      this.axios.post('/api/demo/product/delete', qs.stringify({productids: productids})).then((rse) => {
        this.loading()
        // this.axios.post('/api/demo/productType/queryById', qs.stringify({productid: this.productid})).then(res => {
        //   this.ProductType2 = res.data
        // })
      })
    },
    RecoveryProduct () {
      var productids = this.sels.map(item => item.productid).join() // 获取所有选中行的id组成的字符串，以逗号分隔
      this.axios.post('/api/demo/product/recoveryProduct', qs.stringify({productids: productids})).then((rse) => {
        this.loading()
        // this.axios.post('/api/demo/productType/queryById', qs.stringify({productid: this.productid})).then(res => {
        //   this.ProductType2 = res.data
        // })
      })
    },
    addProductType () {
      this.productType.productid = this.productid
      this.productType.type = this.producttype
      this.productType.size = this.size
      this.productType.stock = this.Stock
      this.axios.post('/api/demo/productType/addProductType', this.productType).then((rse) => {
        this.axios.post('/api/demo/productType/queryById', qs.stringify({productid: this.productid})).then(res => {
          this.ProductType2 = res.data
        })
        this.producttype = ''
        this.size = ''
        this.Stock = ''
        this.productTypeVisible = false
      })
    },
    handleAvatarSuccess (res, file) {
      this.imageUrl = URL.createObjectURL(file.raw)
    },
    beforeAvatarUpload (file) {
      const isJPG = file.type === 'image/png'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 PNG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    },
    selectechart () {
      this.Totaldata()
      this.yeardata()
    },
    yeardata () {
      // 基于准备好的dom，初始化echarts实例
      let yearChart = this.$echarts.init(document.getElementById('yearChart'))
      // 绘制图表
      this.axios.post('/api/demo/order/SumTotal', qs.stringify({productid: this.id})).then(res => {
        this.yearsdata = res.data
        yearChart.setOption({
          title: {
            text: '商品购买量的年龄分布',
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
            data: this.yearsdata,
            type: 'bar'
          }
          ]
        })
      })
    },
    Totaldata () {
      // 基于准备好的dom，初始化echarts实例
      let Chart = this.$echarts.init(document.getElementById('Chart'))
      // 绘制图表
      this.axios.post('/api/demo/order/queryByTotal', qs.stringify({productid: this.id})).then(res => {
        this.totaldata = res.data
        Chart.setOption({
          title: {
            text: '商品每月销量折线图',
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
            data: this.totaldata.map(item => item.name)
          },
          yAxis: {
            type: 'value'
          },
          series: [
            {
              data: this.totaldata,
              type: 'line'
            }
          ]
        })
      })
    }
    // addressdata () {
    //   // 基于准备好的dom，初始化echarts实例
    //   let addressChart = this.$echarts.init(document.getElementById('addressChart'))
    //   // 绘制图表
    //   this.axios.post('/api/demo/user/findAddress').then(res => {
    //     addressChart.setOption({
    //       title: {
    //         text: '用户地区分布',
    //         left: 'center'
    //       },
    //       tooltip: {
    //         trigger: 'item'
    //       },
    //       legend: {
    //         orient: 'vertical',
    //         left: 'left'
    //       },
    //       series: [
    //         {
    //           type: 'pie',
    //           radius: '50%',
    //           label: { // 图形上的文本标签
    //             normal: {
    //               show: true,
    //               formatter: '{d}%'}},
    //           data: res.data
    //         }
    //       ]
    //     })
    //   })
    // }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.form{
  color:#000;
  margin-left: 3%;
  margin-top: 10px;
  font-size: 20px;
  text-align: left;
}
#img{
width:300px;
height:250px;
}
#find{
  width:300px;
  border: 1px;
  display: inline-block
   }
   #find2{
  margin-left: 2%;
  width:500px;
  border: 1px;
  display: inline-block
   }
    #reply{
     color: #0000ff;
   }
   #find3{
width:400px;
margin-top: 15%;
  border: 1px;
  margin: 30px;
  display: inline-block}
</style>
