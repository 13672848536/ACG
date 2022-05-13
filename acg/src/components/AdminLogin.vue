<template>
<div class="background">
    <img :src="imgSrc" width="100%" height="100%" alt="" />
    <div id="login" style="note">
        <h1>登录</h1>
          <div>
            <input type="text" required="required" placeholder="用户名" v-model='nickname'/>
            <input type="password" required="required" placeholder="密码" v-model="password"/>
            <router-link to="/forget">忘记密码</router-link>
            <router-link to="/adminRegistered">马上注册</router-link>
            <button class="but" type="submit" @click="login">登录</button>
          </div>
    </div>
    </div>
</template>

<script>
export default {
  data () {
    return {
      nickname: '',
      password: '',
      keyword: '',
      UserDTO: {
        nickname: '',
        password: ''
      },
      imgSrc: require('../assets/login4.jpeg')
    }
  },
  methods: {
    login: function () {
      this.UserDTO.nickname = this.nickname
      this.UserDTO.password = this.password
      this.axios.post('/api/demo/user/administratorLogin', this.UserDTO).then((res) => {
        if (res.data !== null && res.data) {
          var list = res.data
          this.$message({message: '登录成功', type: 'success', duration: 1000})
          this.$router.push({name: 'adminSystem', query: {list}})
        } else {
          this.$message.error('登录失败，请检查')
        }
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#pr{
color: #fff;
}
  #login{
    position: absolute;
    top: 50%;
    left:50%;
    margin: -150px 0 0 -150px;
    width: 300px;
    height: 300px;
   }
#login h1{
    color: #fff;
    text-shadow:0 0 10px;
    letter-spacing: 1px;
    text-align: center;
}
h1{
    font-size: 2em;
    margin: 0.67em 0;
}
    input{
    width: 278px;
    height: 18px;
    margin-bottom: 10px;
    outline: none;
    padding: 10px;
    font-size: 13px;
    color: #fff;
    text-shadow:1px 1px 1px;
    border-top: 1px solid #312E3D;
    border-left: 1px solid #312E3D;
    border-right: 1px solid #312E3D;
    border-bottom: 1px solid #56536A;
    border-radius: 4px;
    background-color: #2D2D3F;
}
  .but{
    width: 300px;
    min-height: 20px;
    display: block;
    background-color: #4a77d4;
    border: 1px solid #3762bc;
    color: #fff;
    padding: 9px 14px;
    font-size: 15px;
    line-height: normal;
    border-radius: 5px;
    margin: 0;
    margin-top: 10px;
}
.background{
    width:100%;
    height:100%;  /**宽高100%是为了图片铺满屏幕 */
    z-index:-1;
    position: absolute;
}
</style>
