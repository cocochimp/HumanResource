
<template>
  <el-container class="home-container">
      <!-- 头部区域 -->
  <el-header>
      <div class="header_logo">
          <img src="http://rluc5ispc.hn-bkt.clouddn.com/2022/12/18/9d51d40e5e2740b9b285c4b22d1af6af.jpg" alt="">
          <span>人力资源管理系统</span>
      </div>
      <div class="header_right">
          <span>系统管理员</span>
        <el-button type="default" @click="logout">退出</el-button> 
      </div>
      </el-header>
      <!-- 页面主体区域 -->
  <el-container>
      <!-- 侧边栏 -->
    <el-aside :width="isCollapse?'64px':'200px'">
        <div class="toggle-button" @click="toggleCollapse"></div>
        <!-- 侧边栏菜单区域 -->
          <!-- router表示以每个菜单的index作为路由进行跳转 -->
         <el-menu
      background-color="#000"
      text-color="#fff"
      active-text-color="#ffd04b" unique-opened :collapse="isCollapse" :collapse-transition="false" router
      :default-active="activePath">

      
      <!-- index只接收字符串，所以加个’‘ -->
      <el-submenu :index="item.path+''" v-for="item in menulist" :key="item.path">
        <template slot="title">
          <i :class="iconsObj[item.id]"></i>
          <span>{{item.title}}</span>
        </template>
        <!-- 点击事件保存路径对应的到token中，页面创建时取出 -->
        <!-- index为修改路由时该二级菜单对应的路由 -->
          <el-menu-item :index="subItem.path" v-for="subItem in item.children" :key="subItem.path" @click="saveNavState(subItem.path)">
              <template slot="title">
          <i class="el-icon-menu"></i>
          <span>{{subItem.title}}</span>
        </template>
          </el-menu-item>
      </el-submenu>
    </el-menu>
    </el-aside>
      <!-- 右侧内容主体 -->
    <el-main>
        <!-- 路由占位符 -->
        <router-view></router-view>
    </el-main>
  </el-container>
</el-container>
</template>

<script>

export default {
    data(){
       return {
           //左侧菜单数据
           menulist:[],
           //左侧菜单图标数据
           iconsObj:{
               '125':'el-icon-user',
               '103':'el-icon-set-up',
               '101':'el-icon-shopping-bag-1',
               '102':'el-icon-s-order',
               '145':'el-icon-data-line',
           },
           //控制菜单是否折叠的属性
           isCollapse:false,
           //被激活的链接地址
           activePath:'',
           username:'',
           password:''
       }
    },
 components:{

 },
 created(){
      if(this.$route.params.username){
    window.sessionStorage.setItem("username",this.$route.params.username)
     window.sessionStorage.setItem("password",this.$route.params.password)
      }

     this.username=window.sessionStorage.getItem("username")
     this.password=window.sessionStorage.getItem("password")
   this.activePath=window.sessionStorage.getItem('activePath')
 },
 mounted(){

    
     this.getMenuList()
 },
 name:'homePage',
 methods:{
     logout(){
         window.sessionStorage.clear()
         this.$router.push('/login')
     },
     //获取所有的菜单
     async getMenuList(){
       const {data:res}=await this.$http.post(`/Sys/login/loginPermission?username=${this.username}&password=${this.password}`)
    //    if(res.meta.status!==200) return this.$message.error(res.meta.msg)
  
       this.menulist=res.data
     },
     //左侧菜单的缩放
     toggleCollapse(){
      this.isCollapse=!this.isCollapse
     },
     //保存菜单链接的激活状态
     saveNavState(activePath){
         window.sessionStorage.setItem('activePath',activePath)
         this.activePath=activePath
     }
 }
}
</script>

<style lang="less" scoped>

.home-container{
    height: 100%;
}
.template{
    height: 100%;
}

.el-header{
    background-color: #790a0e;
    display: flex;
    justify-content:space-between;
    align-items: center;
     background: url('../assets/5.jpg');
     div{
         display: flex;
         align-items: center;
     }
}
.el-aside{
    background-color: #000;
}
.el-menu{
    border-right: 0;
}
.el-main{
    background-color: #fff;
}

.header_logo{
 color: #fff;
 font-size: 20px;
 display: flex;
 margin-left: 10px;
 img{
     width: 50px;
     height: 50px;
     border-radius: 50%;
     padding-right:10px ;
 }
}
.header_right{
    color:#fff;
    span{
        padding-right: 10px;
    }
}
//左侧样式
.incofont{
    margin-right: 5px;
}
.toggle-button{
    background-color: #87878f;
    font-size: 14px;
    line-height: 28px;
    color: #fff;
    text-align: center;
    letter-spacing: 0.2em;
    cursor: pointer;
}
</style>

