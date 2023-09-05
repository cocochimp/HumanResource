<template>
  <div class="login_container">
    <!-- 背景图片区域 -->
  <div class="day"></div>
  <div class="night"></div>
 <div class="login_box">
          <!-- 头像区域 -->
          <div class="avatar_box">
              <img src="http://rluc5ispc.hn-bkt.clouddn.com/2022/12/18/9d51d40e5e2740b9b285c4b22d1af6af.jpg" alt="">
          </div>

      <!-- 登录表单区域 -->
      <!-- loginForm为输入框双向绑定的内容 -->
      <!-- loginFormRules为输入框合法校验的内容 -->
      <!-- ref为自身标签内容 -->
    <el-form label-width="70px" :model="loginForm" :rules="loginFormRules" ref="loginFormRef">
        <br />
         <br /><br /><br /><br />
          <!-- prop属性为输入框合法校验的内容 -->
        <el-form-item label="用户名" prop="username">
            <!-- v-model为输入框双向绑定的内容 -->
            <el-input v-model="loginForm.username" class="input1" prefix-icon="el-icon-user"></el-input>
         </el-form-item>
          <br />
          <br /><br />
         <el-form-item label="密码" prop="password">
            <el-input v-model="loginForm.password" class="input1" prefix-icon="el-icon-lock" type="password"></el-input>
         </el-form-item>
          <br />
          <br /><br />
         <el-form-item class="btns">
           <el-button type="primary" @click="login">登录</el-button>
           <el-button type="info" @click="resetLoginForm">重置</el-button>
         </el-form-item>
     </el-form>   
        <span @click="register" class="register">免费注册</span>
      </div> 
  </div>
</template>

<script>
export default {
 name:'LoginPage',
 data(){
     return{
     // loginForm为输入框双向绑定的内容 
         loginForm:{
           username:'admin',
           password:'123456'
         },
          username:'admin',
           password:'123456',
    // loginFormRules为输入框合法校验的内容 
         loginFormRules:{
           //验证用户名是否合法
           username:[
            { required: true, message: '请输入用户名', trigger: 'blur' },
            { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
           ],
           //验证密码是否合法
            password: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }
          ],
         }
     }
 },
 methods:{
     //点击重置按钮,重置登录表单
     resetLoginForm(){
      this.$refs.loginFormRef.resetFields();
     },
     //点击登录按钮，先进行验证，验证通过后再发送网络请求
     login(){
       console.log(55666);
       this.$refs.loginFormRef.validate(async valid=>{
           if(!valid) return;
            console.log(this.loginForm.username);
          const {data:res}= await this.$http.post(`/Sys/login?username=${this.loginForm.username}&password=${this.loginForm.password}`,
					);
          console.log(res);
          if(res.ok!==true) return this.$message.error("登录失败!");
          this.$message.success("登录成功！");
          //1.将登陆成功之后的token，保存到客户端的sessionStorage中
          //1.1项目中除了登录之外的其他API接口，必须在登录之后才能访问
          //1.2token只应在当前网站打开期间生效，所以将token保存在sessionStorage中
          window.sessionStorage.setItem("token",res.data.token);
          window.sessionStorage.setItem("uid",res.data[0].u_id);
          //2.通过编程式导航跳转到后台主页，路由地址是 /home
          this.$router.push({
            name:'Welcome',
                  
            params:{
              username:this.loginForm.username,
              password:this.loginForm.password
            }
          })
       })
          // this.$router.push("/home")

     },
      register(){
         this.$router.push('/register')
     },
 }
}
</script>

<style lang="less" scoped>
body {
  margin: 0;
  padding: 0;
}
 
div {
  position: absolute;
  height: 100vh;
  width: 100vw;
}
 
.day {
  background-image: url("../assets/5.jpg");
  background-size: cover;
  background-repeat: no-repeat;
}
 
.night {
  background-image: url("../assets/5.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  clip-path: polygon(100vw 0, 0% 0vh, 100vw 100vh);
}

//自己
.login_container{
    background:url("../assets/background.jpeg");
    height: 100%;
}

.login_box{
    width: 500px;
    height: 330px;
    background-color: #ccc;

    border-radius: 3px;
    opacity: 1;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%,-50%);
}
.avatar_box{
    height: 140px;
    width: 140px;
    border: 2px solid #eee;
    border-radius: 50%;
    box-shadow: 0 0 10px #ddd;
    position: absolute;
    left: 50%;
    transform: translate(-50%,-50%);
    img{
        width: 100%;
        height: 100%;
        border-radius: 50%;
    }
}
.input1{
    width: 300px!important;
}
.el-form{
    width: 100%;
   height: 100%;
    .el-form-item {
        width: 100%;
         height: 200px!important;
         padding-left: 50px;
    .el-input{
         width: 100%;
           height: 100%;
    } 
    }
}
.register{
  position: relative;
  top: -25px;
  left: 430px;
  font-weight: 700;
  color: white;
  cursor: pointer;
}
</style>