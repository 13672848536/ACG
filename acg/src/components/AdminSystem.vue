<template>
<div class="background">
    <div id="login">
    <img  id='img' :src="img" @click=" userinfo"/>
    <label>{{user.nickname}}</label>
    <label>{{user.type}}</label>
  </div>
  <div id="tab">
  <el-menu
     mode="horizontal"
     style=" background-color: transparent !important"
     @select="handleSelect"
     router
     >
      <el-menu-item v-for="(item,i) in navList" :key="i">
          <template slot="title">
             <span @click="$router.push({path: item.name,query:{list}})"> {{ item.navItem }}</span>
          </template>
       </el-menu-item>
    </el-menu>
  <el-main >
    <router-view/>
  </el-main>
</div>
<el-dialog
  title="修改密码"
  :visible.sync="dialogVisible"
  width="20%"
  >
  <div class="form">
          <label>用户名：</label><label style="margin-left: 5%;">{{ user.nickname }}</label><br/>
        </div>
        <div class="form">
          <label>密码：</label><input type="password" v-model="password" style="margin-left: 10%;"><br/>
        </div>
        <div class="form">
          <label>新密码：</label><input type="password" v-model="newpassword" style="margin-left: 5%;"><br/>
        </div>
        <div class="form">
          <label>确认密码：</label><input type="password" v-model="password2" style="margin-left: 0.5px;"><br/>
  </div>
  <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="update">确 定</el-button>
  </span>
</el-dialog>
<el-dialog
  title="用户信息"
  :visible.sync="Visible"
  width="20%"
  >
  <el-upload
            class="avatar-uploader"
            action="/api/demo/user/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
            :data="{'userId':user.id}"
            style="width: 300px"
          >
                      <!-- :before-upload="beforeAvatarUpload" -->
            <img :src="img" id="img">
          </el-upload>
  <div class="form">
          <label>电话号码：</label><el-input type="text" v-model="phone" label='123456' size='mini' style="width: 60%"></el-input><br/>
        </div>
        <div class="form">
          <label>邮箱：</label><el-input type="text" v-model="email" style="margin-left: 10%; width: 60%" size='mini' value="Mickey"></el-input><br/>
        </div>
        <div class="form">
          <label>省份：</label>
      <el-select v-model="provincial" placeholder="请选择" size='mini' style=" width: 25%" @change ='findcity'>
    <el-option
        v-for="item in Provincial"
      :key="item.pid"
      :label="item.provincial"
      :value="item.pid"
      >
    </el-option>
  </el-select>
  <label>市区：</label>
   <el-select v-model="city" placeholder="请选择" size='mini' style=" width: 25%">
    <el-option
       v-for="item in City"
      :key="item.cid"
      :label="item.city"
      :value="item.city"
      >
    </el-option>
  </el-select>
  </div>
        <div class="form">
          <label>地址：</label><el-input type="text" v-model="address" style="margin-left: 10%; width: 60%" size='mini'></el-input><br/>
        </div>
        <div class="form">
         <label id="text" @click="dialogVisible = true">修改密码</label>
     <label id='text' @click="exit">退出</label></div>
  <span slot="footer" class="dialog-footer">
    <el-button @click="Visible = false">取 消</el-button>
    <el-button type="primary" @click=' updateuser' >确 定</el-button>
  </span>
</el-dialog>
  <!-- <img :src="imgSrc" width="100%" height="100%" alt="" /> -->
</div>
</template>

<script>
import qs from 'qs'
export default {
  data () {
    return {
      navList: [
        {name: '/adminSystem/User', navItem: '用户管理'},
        {name: '/adminSystem/Product', navItem: '商品管理'},
        {name: '/adminSystem/Order', navItem: '订单管理'}
      ],
      password: '',
      password2: '',
      newpassword: '',
      dialogVisible: false,
      Visible: false,
      phone: '',
      email: '',
      address: '',
      list: '',
      Provincial: [],
      City: [],
      provincial: '',
      city: '',
      user: {},
      UserDTO: {
        nickname: '',
        password: ''
      },
      UserinfoDTO: {
        phone: '',
        email: '',
        address: '',
        nickname: '',
        provincial: '',
        city: ''
      },
      url: '',
      img: ''
    }
  },
  created () {
    this.list = this.$route.query.list
    this.axios.post('/api/demo/user/findById', qs.stringify({id: this.list})).then(res => {
      this.user = res.data
      this.img = require('../assets/' + this.user.img)
    })
  },
  methods: {
    findcity () {
      this.axios.post('/api/demo/city/queryById', qs.stringify({pid: this.provincial})).then(rse => {
        this.City = rse.data
      })
    },
    handleSelect (key, keyPath) {
      console.log(key, keyPath)
    },
    exit () {
      this.$router.push({path: '/AdminLogin'})
    },
    setUserinfo () {
      this.UserinfoDTO.phone = this.phone
      this.UserinfoDTO.email = this.email
      this.UserinfoDTO.address = this.address
      this.UserinfoDTO.nickname = this.user.nickname
      this.UserinfoDTO.provincial = this.provincial
      this.UserinfoDTO.city = this.city
    },
    updateuser () {
      this.setUserinfo()
      let sjhm = /^1[3|4|5|7|8][0-9]\d{4,8}$/
      if (sjhm.test(this.UserinfoDTO.phone)) {
        this.axios.post('/api/demo/user/updateUserInfo', qs.stringify(this.UserinfoDTO)).then((rse) => {
          this.axios.post('/api/demo/user/findById', qs.stringify({id: this.list})).then(res => {
            this.user = res.data
            this.img = require('../assets/' + this.user.img)
          })
        })
      } else {
        this.$message.error('电话号码不符合')
      }
      this.Visible = false
      this.phone = ''
      this.address = ''
      this.email = ''
    },
    update () {
      // qs.stringify({newpassword: this.newpassword})
      this.UserDTO.nickname = this.user.nickname
      this.UserDTO.password = this.password
      this.url = '/api/demo/user/update/' + this.newpassword
      if (this.newpassword === this.password2 && this.newpassword) {
        this.axios.post(this.url, this.UserDTO).then(res => {})
      } else {
        this.$message.error('密码不一致')
      }
      this.password = ''
      this.newpassword = ''
      this.password2 = ''
      this.dialogVisible = false
    },
    userinfo () {
      this.Visible = true
      this.phone = this.user.phone
      this.address = this.user.address
      this.email = this.user.email
      this.axios.post('/api/demo/provincial/queryAll').then(rse => {
        this.Provincial = rse.data
      })
      this.provincial = this.user.provincial
      this.city = this.user.city
    },
    handleAvatarSuccess (res, file) {
      this.imageUrl = URL.createObjectURL(file.raw)
      console.log(URL.createObjectURL(file.raw))
      console.log(this.imageUrl)
      console.log(res)
    },
    beforeAvatarUpload (file) {
      const isJPEG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPEG) {
        this.$message.error('上传头像图片只能是 JPEG格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPEG && isLt2M
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.user{
   margin-left: 10%;
}
#text{
  font-size: 10px;
}
#img{
width:40px;
height:40px;
border-radius:100%;
overflow:hidden;
}
  #login{
    position: absolute;
    top: 17%;
    right: 2%;
    margin: -150px 0 0 -150px;
   }
   #do{
    position: absolute;
    top: 23%;
    right: 2%;
    margin: -150px 0 0 -150px;
   }
    #tab{
    position: absolute;
    top: 20%;
   left: 7%;
    margin: -150px 0 0 -150px;
     background-color: transparent !important;
    width: 100%;
    height: 3%;
   }
.background{
    width:100%;
    height:100%;  /**宽高100%是为了图片铺满屏幕 */
    z-index:-1;
    position: absolute;
}
.form{
  color:#000;
  margin-left: 3%;
  margin-top: 10px;
  font-size: 20px;
  text-align: left;
}
#contain{
  position: absolute;
  transform: translate(-50%,-50%);
  text-align: center;
  border-radius: 20px;
}
</style>
