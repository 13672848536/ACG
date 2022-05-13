<template>
<div class="background">
<img :src="imgSrc" width="100%" height="100%" alt="" />
    <div id="contain">
        <h1>注册</h1>

        <div class="form">
          <label>用户名：</label><input type="text" v-model="nickname"><br/>
        </div>
        <div class="form">
          <label>密码：</label><input type="password" v-model="password"><br/>
        </div>
        <div class="form">
          <label>确认密码：</label><input type="password" v-model="password2"><br/>
        </div>
        <div class="form">
          <label>姓名：</label><input type="text" v-model="name"><br/>
        </div>
        <div class="form">
          <label>身份证号码：</label><input type="text" v-model="identification"><br/>
        </div>
        <div class="form">
          <label>邮箱：</label><input type="text" v-model="email"><br/>
        </div>
        <div class="form">
          <label>手机号：</label><input type="text" v-model="phone"><br/>
        </div>
           <div class="form">
          <label>省份：</label>
      <el-select v-model="provincial" placeholder="请选择" size='mini' class="select" @change ='findcity'>
    <el-option
        v-for="item in Provincial"
      :key="item.pid"
      :label="item.provincial"
      :value="item.pid"
      >
    </el-option>
  </el-select></div>
  <div class=" form">
  <label>市区：</label>
   <el-select v-model="city" placeholder="请选择" size='mini' class="select" >
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
          <label>详细地址：</label><input  type="text" v-model="address"><br/>
        </div>
        <button @click="registered">提交</button>
        <button @click="exit">返回</button>
      </div>
    </div>
</template>

<script>
import qs from 'qs'
export default {
  data () {
    return {
      nickname: '',
      password: '',
      password2: '',
      name: '',
      identification: '',
      email: '',
      phone: '',
      address: '',
      Provincial: [],
      City: [],
      provincial: '',
      city: '',
      UserinfoDTO: {
        nickname: '',
        password: '',
        name: '',
        identification: '',
        email: '',
        phone: '',
        address: '',
        provincial: '',
        city: ''
      },
      imgSrc: require('../assets/login5.jpeg')
    }
  },
  created () {
    this.axios.post('/api/demo/provincial/queryAll').then(rse => {
      this.Provincial = rse.data
    })
  },
  methods: {
    setUserinfo () {
      this.UserinfoDTO.nickname = this.nickname
      this.UserinfoDTO.password = this.password
      this.UserinfoDTO.name = this.name
      this.UserinfoDTO.identification = this.identification
      this.UserinfoDTO.email = this.email
      this.UserinfoDTO.phone = this.phone
      this.UserinfoDTO.address = this.address
      this.UserinfoDTO.provincial = this.provincial
      this.UserinfoDTO.city = this.city
    },
    registered () {
      this.setUserinfo()
      let sfz = /^[1-9]\d{5}(18|19|20|(3\d))\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/
      let sjhm = /^1[3|4|5|7|8][0-9]\d{4,8}$/
      if (this.password === this.password2) {
        if (sfz.test(this.UserinfoDTO.identification)) {
          if (sjhm.test(this.UserinfoDTO.phone)) {
            this.axios.post('/api/demo/user/adminRegister', this.UserinfoDTO).then(rse => {
              this.$router.push({path: '/AdminLogin'})
            })
          } else {
            this.$message.error('电话号码不符合')
          }
        } else {
          this.$message.error('身份证号码不符合')
        }
      } else {
        this.$message.error('密码不一致')
      }
    },
    exit () {
      this.$router.push({path: '/AdminLogin'})
    },
    findcity () {
      this.axios.post('/api/demo/city/queryById', qs.stringify({pid: this.provincial})).then(rse => {
        this.City = rse.data
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.background{
    width:100%;
    height:100%;  /**宽高100%是为了图片铺满屏幕 */
    z-index:-1;
    position: absolute;
}
#contain{
  width: 560px;
  height: 666px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%,-50%);
  background:#00000090;
  text-align: center;
  border-radius: 20px;
}
#contain h1{
  color: #fff;
    text-shadow:0 0 10px;
    letter-spacing: 1px;
    text-align: center;
}
.form{
  color: white;
  margin-left: 3%;
  margin-top: 20px;
  font-size: 20px;
  text-align: left;
}
label{
  float:left;
  width: 8em;
  margin-right: 3px;
  text-align: right;
}
input,textarea{
  margin-left: 5px;
  padding: 4px;
  border: solid 1px #4e5ef3;
  outline: 0;
  font: normal 13px/100% Verdana,Tahoma,sans-serif;
  width: 200px;
  height: 20px;
  background:#f1f1f190;
  box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 8px;
}
input:hover,textarea:hover,input:focus,textarea:focus{border-color:#0d0aa1;}
button{
  position: relative;
  height: 33px;
  width: 150px;
  background: rgba(35, 19, 252, 0.425);
  border-radius: 10px;
  margin-top: 38px;
  box-shadow: none;
  color: white;
  margin-left: 40px;
}
.select{
   margin-left: 5px;
  padding: 4px;
  outline: 0;
  font: normal 13px/100% Verdana,Tahoma,sans-serif;
  width: 200px;
  height: 20px;
  box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 8px;
}
</style>
