import Vue from 'vue'
import Router from 'vue-router'
import AdminLogin from '../components/AdminLogin'
import AdminRegistered from '../components/AdminRegistered'
import Forget from '../components/Forget'
import Login from '../components/Login'
import Registered from '../components/Registered'
import Forget2 from '../components/Forget2'
import AdminSystem from '../components/AdminSystem'
import System from '../components/System'
import AdminSystemUser from '../components/AdminSystemUser'
import AdminSystemProduct from '../components/AdminSystemProduct'
import AdminSystemOrder from '../components/AdminSystemOrder'
import User from '../components/User'
import Home from '../components/Home'
import Productinfo from '../components/Productinfo'
Vue.use(Router)
export default new Router({
  routes: [
    {
      path: '/AdminLogin',
      name: 'AdminLogin',
      component: AdminLogin
    },
    {
      path: '/adminRegistered',
      name: 'adminRegistered',
      component: AdminRegistered
    },
    {
      path: '/forget',
      name: 'forget',
      component: Forget
    },
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/Registered',
      name: 'Registered',
      component: Registered
    },
    {
      path: '/forget2',
      name: 'forget2',
      component: Forget2
    },
    {
      path: '/productinfo',
      name: 'productinfo',
      component: Productinfo
    },
    {
      path: '/System',
      name: 'System',
      component: System,
      redirect: '/System/Home',
      children: [
        {
          path: '/System/User',
          name: 'user',
          component: User
        },
        {
          path: '/System/Home',
          name: 'home',
          component: Home
        }
      ]
    },
    {
      path: '/adminSystem',
      name: 'adminSystem',
      component: AdminSystem,
      redirect: '/adminSystem/User',
      children: [
        {
          path: '/adminSystem/User',
          name: 'User',
          component: AdminSystemUser
        },
        {
          path: '/adminSystem/Product',
          name: 'product',
          component: AdminSystemProduct
        },
        {
          path: '/adminSystem/Order',
          name: 'adminSystemOrder',
          component: AdminSystemOrder
        }
      ]
    }
  ],
  mode: 'history'// 干掉地址栏里边的#号键
})
