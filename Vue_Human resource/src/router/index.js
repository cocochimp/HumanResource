import Vue from 'vue'
import VueRouter from 'vue-router'
// import Login from '../components/Login.vue'
const Login = () =>
    import ( /* webpackChunkName: "login_home_welcome" */ '../components/Login.vue')
    // import Home from '../components/Home.vue'
const Home = () =>
    import ( /* webpackChunkName: "login_home_welcome" */ '../components/Home.vue')
    // import Welcome from '../components/Welcome.vue'
const Welcome = () =>
    import ( /* webpackChunkName: "login_home_welcome" */ '../components/Welcome.vue')

// import User from '../components/user/User.vue'
const User = () =>
    import ( /* webpackChunkName: "User_Rights_Roles" */ '../components/user/User.vue')
    // import Rights from '../components/power/Rights.vue'
const Rights = () =>
    import ( /* webpackChunkName: "User_Rights_Roles" */ '../components/power/Rights.vue')
    // import Roles from '../components/power/Roles.vue'
const Roles = () =>
    import ( /* webpackChunkName: "User_Rights_Roles" */ '../components/power/Roles.vue')

// import Cate from '../components/goods/Cate.vue'
const Cate = () =>
    import ( /* webpackChunkName: "Cate_Params" */ '../components/goods/Cate.vue')
    // import Params from '../components/goods/Params.vue'
const Params = () =>
    import ( /* webpackChunkName: "Cate_Params" */ '../components/goods/Params.vue')

// import GoodsList from '../components/goods/List'
const GoodsList = () =>
    import ( /* webpackChunkName: "GoodsList_Add" */ '../components/goods/List')
    // import Add from '../components/goods/Add.vue'
const Add = () =>
    import ( /* webpackChunkName: "GoodsList_Add" */ '../components/goods/Add.vue')

// import Order from '../components/order/Order.vue'
const Order = () =>
    import ( /* webpackChunkName: "Order_Report" */ '../components/order/Order.vue')
    // import Report from '../components/report/Report.vue'
const Report = () =>
    import ( /* webpackChunkName: "Order_Report" */ '../components/report/Report.vue')
const Register = () =>
    import ( /* webpackChunkName: "register" */ '../components/register.vue')
const organization = () =>
    import ( /* webpackChunkName: "organization" */ '../components/organization/organization.vue')
const position = () =>
    import ( /* webpackChunkName: "position" */ '../components/position/position.vue')
const add = () =>
    import ( /* webpackChunkName: "file" */ '../components/File/add.vue')
const check = () =>
    import ( /* webpackChunkName: "file" */ '../components/File/check.vue')
const query = () =>
    import ( /* webpackChunkName: "file" */ '../components/File/query.vue')
const add1 = () =>
    import ( /* webpackChunkName: "sal" */ '../components/sal/add.vue')
const check1 = () =>
    import ( /* webpackChunkName: "sal" */ '../components/sal/check.vue')
const query1 = () =>
    import ( /* webpackChunkName: "sal" */ '../components/sal/query.vue')
const add2 = () =>
    import ( /* webpackChunkName: "sal" */ '../components/sal/addmoney.vue')
const give = () =>
    import ( /* webpackChunkName: "sal" */ '../components/sal/give.vue')
const giveList = () =>
    import ( /* webpackChunkName: "sal" */ '../components/sal/giveList.vue')

Vue.use(VueRouter)

const routes = [
    // {
    //   path: '/',
    //   name: 'home',
    //   component: HomeView
    // },
    // {
    //   path: '/about',
    //   name: 'about',
    //   // route level code-splitting
    //   // this generates a separate chunk (about.[hash].js) for this route
    //   // which is lazy-loaded when the route is visited.
    //   component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
    // },
    {
        path: '/',
        redirect: '/login'
    },
    {
        path: '/login',
        component: Login
    },
    {
        path: '/register',
        component: Register
    },
    {
        path: '/home',
        name: 'Home',
        component: Home,
        redirect: '/Sys/user',
        children: [{
                path: '/welcome',
                component: Welcome,
                name: 'Welcome'
            },
            { path: '/Sys/organization', component: organization, name: 'User' },
            { path: '/sal/query', component: query1, },
            { path: '/sys/permission', component: Rights },
            { path: '/sys/user', component: User },
            { path: '/sal/check', component: check1 },
            { path: '/sal/give', component: give },
            { path: '/sys/position', component: position },
            { path: '/file/add', component: add },
            { path: '/file/check', component: check },
            { path: '/file/query', component: query },
            { path: '/sal/add', component: add1 },
            { path: '/reports', component: Report },
            { path: '/goods/add', component: Add },
            { path: '/sal/money', component: add2 },
            { path: '/sal/giveList', component: giveList },
            // { path: '/sal/give', component: give },
        ]
    }
]

const router = new VueRouter({
    routes
})

//挂载路由导航守卫
// router.beforeEach((to, from, next) => {
//     //to将要访问的路径
//     //from代表从哪个路径跳转而来
//     //next是一个函数，表示放行
//     //   next()放行   next('/login')强制跳转

//     if (to.path === '/login') return next()
//         //获取token
//     const tokenStr = window.sessionStorage.getItem('token')
//     if (!tokenStr) return next('/login')
//     next()
// })


export default router