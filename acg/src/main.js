
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import VueAxios from 'vue-axios'
import qs from 'qs' // 引入qs
// 引入echarts
import echarts from 'echarts'
Vue.prototype.$echarts = echarts
Vue.prototype.$qs = qs
Vue.use(VueAxios, axios)
Vue.use(ElementUI)
Vue.config.productionTip = false
// eslint-disable-next-line no-new
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }

})
