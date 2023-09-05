<template>
  <div>
      <!-- 面包屑导航区域 -->
      <el-breadcrumb separator-class="el-icon-arrow-right">
  <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
  <el-breadcrumb-item>用户管理</el-breadcrumb-item>
  <el-breadcrumb-item>用户列表</el-breadcrumb-item>
</el-breadcrumb>


    <!-- 卡片试图区域 -->
 <el-card >
     <!-- 搜索与添加区域 -->
        <el-row :gutter="20">
            <el-col :span="8">
                <!-- clearable添加了为可清空的文本框 -->
                 <!-- <el-input placeholder="请输入内容"  class="input-with-select" v-model="queryInfo.query" clearable @clear="getUserList">
                    <el-button slot="append" icon="el-icon-search" @click="getUserListByName"></el-button>
                 </el-input> -->
            </el-col>
            <el-col :span="4">
                <el-button type="primary" @click="addDialogVisible=true">添加薪酬</el-button>
            </el-col>
        </el-row>

        <!-- 用户列表区域 -->
        <!-- border为边框线,stripe为滑动后会变化颜色 -->
        <el-table :data="userlist" border stripe>
            <el-table-column type="index"></el-table-column>
            <el-table-column label="制定人姓名" prop="f_name"></el-table-column>
            <el-table-column label="薪资标准" prop="o3_name"></el-table-column>
            <el-table-column label="总薪资" prop="allmoney"></el-table-column>
            <el-table-column label="受理人" prop="username"></el-table-column>
            <el-table-column label="创建时间" prop="create_time"></el-table-column>
            <!-- <el-table-column label="性别" prop="sex"></el-table-column> -->
            <!-- <el-table-column label="状态" prop="mg_state">
                <template slot-scope="scope">
                        <el-switch
                    v-model="scope.row.mg_state" @change="userStateChanged(scope.row)"
                   >
                    </el-switch>
                </template>
                
            </el-table-column> -->
            <el-table-column label="操作"  width="200px">
                <template slot-scope="scope">
                        <!-- 修改按钮 -->
                        <!-- <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditDialog(scope.row.s_id)"></el-button> -->
                        <!-- 删除按钮 -->
                        <el-button type="danger" icon="el-icon-delete"  size="mini" @click="removeUserById(scope.row.s_id)"></el-button>
                        <!-- 分配角色按钮 -->
                         <el-tooltip  effect="dark" content="查看详情" placement="top" :enterable="false">
                           <el-button type="warning" icon="el-icon-setting"  size="mini" @click="showEditDialog(scope.row.s_id)"></el-button>
                         </el-tooltip>
                </template>
            </el-table-column>
        </el-table>


        <!-- 分页区域 -->
         <el-pagination
            @size-change="handleSizeChanged"
            @current-change="handleCurrentChange"
            :current-page="queryInfo.pagenum"
            :page-sizes="[1,2,5,10]"
            :page-size="queryInfo.pagesize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
    </el-pagination>
     </el-card>


     <!-- 添加用户的对话框 -->
     <!-- visible.sync为控制显示和隐藏 -->
     <el-dialog
            title="薪酬标准登记"
            :visible.sync="addDialogVisible"
            width="50%"
            @close="addDialogClosed"
            >
            <!-- 内容主体区域 -->
            <el-row :gutter="20">
           
                <!-- clearable添加了为可清空的文本框 -->
                 <!-- <el-input placeholder="请输入内容"  class="input-with-select" v-model="queryInfo.query" clearable @clear="getUserList">
                    <el-button slot="append" icon="el-icon-search" @click="getUserListByName"></el-button>
                 </el-input> -->
                  <el-col :span="8">
                  
                     </el-col>
                 <el-form ref="editFormRef" :model="editForm" :rules="editFormRules" label-width="100px" >
                <!-- prop是绑定验证规则的属性 -->
                
                  <el-form-item label="制定人">
                    <el-input v-model="editForm.name"></el-input>
                </el-form-item>
                <el-form-item label="薪酬标准名称" >
                     <el-cascader
                    v-model="editForm.professionId"
                    :options="catelist"
                    :props="cateProps"
                   >
              </el-cascader>
                </el-form-item>
              
         
            <el-col :span="8">
            <el-form-item label="薪酬标准编号">
                    <el-input v-model="editForm.username" disabled ></el-input>
                </el-form-item>
              
                <el-form-item label="登记人">
                    <el-input v-model="editForm.admin" disabled></el-input>
                </el-form-item>
                
            </el-col>
            <el-col :span="8">
                <el-form-item label="薪酬总额">
                    <el-input v-model="editForm.money" disabled></el-input>
                </el-form-item>
                <el-form-item label="登记时间">
                    <el-input v-model="editForm.time" disabled></el-input>
                </el-form-item>
                
            </el-col>
            <el-col :span="8" style="color:white">
            12255555555555
            </el-col>
            <el-col :span="8" style="color:white">
            12255555555555
            </el-col>
            <el-col :span="8" style="color:white">
            12255555555555
            </el-col>
            <el-col :span="8" style="color:white">
            12255555555555
            </el-col>
            <el-col :span="8" style="color:white">
            12255555555555
            </el-col>
            <el-col :span="8" style="color:white">
            12255555555555
            </el-col>
  <el-col :span="8" style="color:white">
            12255555555555
            </el-col>
             </el-form>
                 <el-form ref="editFormRef"  label-width="100px" >

              <el-form-item label="基本工资">
                    <el-input v-model="time1"></el-input>
                </el-form-item>
<el-form-item label="交通补助">
                    <el-input v-model="editForm1.time2" disabled style="margin:0"></el-input>
                </el-form-item><el-form-item label="午餐补助">
                    <el-input v-model="editForm1.time3" disabled></el-input>
                </el-form-item><el-form-item label="通信补助">
                    <el-input v-model="editForm1.time4" disabled></el-input>
                </el-form-item><el-form-item label="养老保险">
                    <el-input v-model="editForm1.time5" disabled></el-input>
                </el-form-item>
                <el-form-item label="失业保险">
                    <el-input v-model="editForm1.time6" disabled></el-input>
                </el-form-item>
                <el-form-item label="医疗保险">
                    <el-input v-model="editForm1.time7" disabled></el-input>
                </el-form-item>
                <el-form-item label="住房公积金">
                    <el-input v-model="editForm1.time8" disabled></el-input>
                </el-form-item>
             </el-form>
       
           
        </el-row>
            <!-- 底部区域 -->
            <span slot="footer" class="dialog-footer">
                <el-button @click="addDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="addUser">确 定</el-button>
            </span>
    </el-dialog>

        <!--修改用户的对话框  -->
        <el-dialog
            title="详情页面"
            :visible.sync="editDialogVisible"
            width="50%"
            @close="editDialogClosed"
            >
             <el-row :gutter="20">
           
                <!-- clearable添加了为可清空的文本框 -->
                 <!-- <el-input placeholder="请输入内容"  class="input-with-select" v-model="queryInfo.query" clearable @clear="getUserList">
                    <el-button slot="append" icon="el-icon-search" @click="getUserListByName"></el-button>
                 </el-input> -->
                  <el-col :span="8">
                  
                     </el-col>
                 <el-form ref="editFormRef" :model="editForm" :rules="editFormRules" label-width="100px" >
                <!-- prop是绑定验证规则的属性 -->
                
                  <el-form-item label="制定人">
                    <el-input v-model="editForm.name" disabled></el-input>
                </el-form-item>
                <el-form-item label="薪酬标准名称" >
                     <el-cascader
                    v-model="editForm.professionId"
                    :options="catelist"
                    :props="cateProps"
                    disabled
                   >
              </el-cascader>
                </el-form-item>
              
         
            <el-col :span="8">
            <el-form-item label="薪酬标准编号">
                    <el-input v-model="editForm.username" disabled ></el-input>
                </el-form-item>
              
                <el-form-item label="登记人">
                    <el-input v-model="editForm.admin" disabled></el-input>
                </el-form-item>
                
            </el-col>
            <el-col :span="8">
                <el-form-item label="薪酬总额">
                    <el-input v-model="editForm.money" disabled></el-input>
                </el-form-item>
                <el-form-item label="登记时间">
                    <el-input v-model="editForm.time" disabled></el-input>
                </el-form-item>
                
            </el-col>
            <el-col :span="8" style="color:white">
            12255555555555
            </el-col>
            <el-col :span="8" style="color:white">
            12255555555555
            </el-col>
            <el-col :span="8" style="color:white">
            12255555555555
            </el-col>
            <el-col :span="8" style="color:white">
            12255555555555
            </el-col>
            <el-col :span="8" style="color:white">
            12255555555555
            </el-col>
            <el-col :span="8" style="color:white">
            12255555555555
            </el-col>
  <el-col :span="8" style="color:white">
            12255555555555
            </el-col>
             </el-form>
                 <el-form ref="editFormRef"  label-width="100px" >

              <el-form-item label="基本工资">
                    <el-input v-model="time1" disabled></el-input>
                </el-form-item>
<el-form-item label="交通补助">
                    <el-input v-model="editForm1.time2" disabled style="margin:0"></el-input>
                </el-form-item><el-form-item label="午餐补助">
                    <el-input v-model="editForm1.time3" disabled></el-input>
                </el-form-item><el-form-item label="通信补助">
                    <el-input v-model="editForm1.time4" disabled></el-input>
                </el-form-item><el-form-item label="养老保险">
                    <el-input v-model="editForm1.time5" disabled></el-input>
                </el-form-item>
                <el-form-item label="失业保险">
                    <el-input v-model="editForm1.time6" disabled></el-input>
                </el-form-item>
                <el-form-item label="医疗保险">
                    <el-input v-model="editForm1.time7" disabled></el-input>
                </el-form-item>
                <el-form-item label="住房公积金">
                    <el-input v-model="editForm1.time8" disabled></el-input>
                </el-form-item>
             </el-form>
       
           
        </el-row>
           
        </el-dialog>

        <!-- 分配角色的对话框 -->
        <el-dialog
            title="分配角色"
            :visible.sync="setRolesDialogVisible"
            width="50%"
            @close="setRoleDialogClosed"
            >
            <div>
                <p>当前的用户：{{userInfo.username}}</p>
                <p>当前的角色：{{userInfo.role_name}}</p>
                <p>分配新角色：
                    <!-- select中v-model的值是选择到的值，里面的label是要显示的值，value是实际选择的值 -->
                     <el-select v-model="selectedRoleId" placeholder="请选择">
                        <el-option
                        v-for="item in rolesList"
                        :key="item.id"
                        :label="item.roleName"
                        :value="item.id">
                        </el-option>
                    </el-select>
                </p>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="setRolesDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveRoleInfo">确 定</el-button>
            </span>
        </el-dialog>
  </div>
</template>

<script>
export default {
 name:'UserPage',
 data(){
     //验证邮箱的规则
    var checkEmail=(rule,value,cb)=>{
        //验证邮箱的正则表达式
        const regEmail=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/

        if(regEmail.test(value)){
            //合法的邮箱
            return cb()
        }
        cb(new Error('请输入合法的邮箱'))
    }
    //验证手机号的规则
       var checkMobile=(rule,value,cb)=>{
        //验证手机号的正则表达式
        const regMobile=/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/

        if(regMobile.test(value)){
            //合法的手机号
            return cb()
        }
        cb(new Error('请输入合法的手机号'))
    }
    return{
         catelist:[],
        //级联选择器的配置对象
        cateProps:{
           value:'o3_id',
           label:'o3_name',
           children:'children',
           expandTrigger: 'hover',
       },
      //获取用户列表的参数对象
      queryInfo:{
          //搜索关键字
          query:'',
          //当前的页数
          pagenum: 1,
          //当前每页显示多少条数据
          pagesize: 5
      },
      editForm1:{
    time2:'',
    time3:'',
    time4:'',
    time5:'',
    time6:'',
    time7:'',
    time8:'',
      },
      userlist:[],
      total:0,
      //控制添加用户对话框的显示与隐藏
      addDialogVisible:false,
      //添加用户的表单数据
      addForm:{
          username:'',
          password:'',
          email:'',
          mobile:''
      },
      //添加表单的验证规则对象
      addFormRules:{
          username:[
              {required:true,message:'请输入用户邮箱',trigger:'blur' },
              {min:3,max:10,message:'用户名的长度在3~10个字符之间',trigger:'blur'}
              ],
          password:[
              {required:true,message:'请输入密码',trigger:'blur' },
              {min:6,max:15,message:'用户名的长度在6~15个字符之间',trigger:'blur'}
              ],
          email:[
              {required:true,message:'请输入邮箱',trigger:'blur' },
              {validator:checkEmail,trigger:'blur'}
              ],  
          mobile:[
              {required:true,message:'请输入手机号',trigger:'blur' },
              {validator:checkMobile,trigger:'blur'}
              ],            
      },
      //控制修改用户对话框的显示与隐藏
      editDialogVisible:false,
      time1:'',

      //修改用户信息时查询到的用户信息对象
      editForm:{
           username:100000000001,
          name:'小明',
professionId:'',
money:'',
time:new Date().toLocaleDateString(),
admin:window.sessionStorage.getItem("username")
      },
      //修改表单的验证规则对象
      editFormRules:{
          email:[
              {required:true,message:'请输入用户邮箱',trigger:'blur'},
              {validator:checkEmail,trigger:'blur'}
          ],
          mobile:[
               {required:true,message:'请输入用户手机',trigger:'blur'},
              {validator:checkMobile,trigger:'blur'}
          ]
      },
      //控制分配角色对话框的显示与隐藏
      setRolesDialogVisible:false,
      //需要被分配角色的用户信息
      userInfo:{},
      //所有角色的数据列表
      rolesList:[],
      //已选中的角色Id值
      selectedRoleId:''
     }
 },
 watch:{
      time1(newVal,oldVal){
      this.editForm1.time2=newVal*0.1
      this.editForm1.time3=newVal*0.1
      this.editForm1.time4=newVal*0.1
      this.editForm1.time5=newVal*0.08
      this.editForm1.time6=newVal*0.005
      this.editForm1.time7=newVal*0.02+3
      this.editForm1.time8=newVal*0.08
      this.editForm.money=this.editForm1.time2+this.editForm1.time3+this.editForm1.time4+this.editForm1.time5+this.editForm1.time6+this.editForm1.time7+this.editForm1.time8+(this.time1-0)
      }

 },
 methods:{
       goAddpage(){
         this.$router.push('/goods/add')
     },
     //获取用户列表
   async getUserList(){
       this.queryInfo.pagenum=0
      const {data:res} = await this.$http.post(`/Sal/add/showSalApplication/${this.queryInfo.pagenum}/${this.queryInfo.pagesize}`)
    //   if(res.meta.status!==200){
    //       return this.$message.error('获取用户列表失败！')
    //   }
      console.log(res.data);
      this.userlist=res.data
      this.total=4
      const {data:res1} = await this.$http.post('/Sal/add/queryXinZiBiaoZhun')
  this.catelist=res1.data
      
    
   },
    async getUserListByName(){
       this.queryInfo.pagenum=this.queryInfo.pagenum-1
      const {data:res} = await this.$http.post(`/Sys/user/queryUserByName/${this.queryInfo.query}/${this.queryInfo.pagenum}/${this.queryInfo.pagesize}`)
    //   if(res.meta.status!==200){
    //       return this.$message.error('获取用户列表失败！')
    //   }
      console.log(res.data);
      this.userlist=res.data
      this.total=4
    
   },
    //监听pagesize改变的事件
 handleSizeChanged(newSize){
   
     this.queryInfo.pagesize=newSize
     this.getUserList()
 },
 //监听页码值改变的事件
 handleCurrentChange(newPage){
  console.log(6);
     this.queryInfo.pagenum=newPage
     this.getUserList()
 },
 //监听switch开关状态的改变
 async userStateChanged(userinfo){
    const {data:res}=await this.$http.put(`users/${userinfo.id}/state/${userinfo.mg_state}`)
    if(res.meta.status!==200){
        userinfo.mg_state=!userinfo.mg_state
        return this.$message.error('更新用户状态失败！')
    }
    this.$message.success('更新用户状态成功！')
 },
 //监听添加用户对话框的关闭事件
 addDialogClosed(){
     this.$refs.addFormRef.resetFields()
 },
 //点击按钮 添加新用户
async addUser(){
    
         //可以发起添加用户的网络请求
         const {data:res}=await this.$http.post(`/Sal/add/insertInfo/${window.sessionStorage.getItem('uid')}/${this.editForm.professionId}/${this.editForm.name}/${this.time1}`)

        //  if(res.meta.status!==201){
        //      this.$message.error('添加用户失败！')
        //  }
         this.$message.success('添加薪酬成功!')
         //隐藏添加用户的对话框
         this.addDialogVisible=false
         //重新获取用户列表数据
         this.getUserList()
  
 },
 //展示编辑用户的对话框
 async showEditDialog(id){
  this.editDialogVisible=true

    const {data:res}= await this.$http.post('/Sal/showSalInfoBySid/'+id)

   
    this.editForm.name=res.data[0].f_name
    this.editForm.professionId=res.data[0].o3_id
    this.editForm.admin=res.data[0].username
    this.time1=res.data[0].basic
 },
 //监听修改用户对话框的关闭事件
 editDialogClosed(){
     this.$refs.editFormRef.resetFields()
 },
 //修改用户信息并提交
 editUserInfo(){
     this.$refs.editFormRef.validate(async valid=>{
         if(!valid) return
         //发起修改用户信息的数据请求
         const {data:res} =await this.$http.put('users/'+this.editForm.id,{
             email:this.editForm.email,
             mobile:this.editForm.mobile
         })

         if(res.meta.status!==200){
             return this.$message.error('更新用户信息失败！')
         }

         //关闭对话框
         this.editDialogVisible=false
         //刷新数据列表
         this.getUserList()
         //提示用户
         this.$message.success('更新用户信息成功！')
     })
 },
 //根据Id删除对应的用户信息
async removeUserById(id){
    //弹框询问用户是否删除数据
    const confirmResult= await this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).catch(err=>err)

        //如果用户确认删除，则返回值为字符串confirm
        //如果用户取消了删除，则返回值为字符串 cancel
        if(confirmResult!=='confirm'){
            return this.$message.info('已取消删除')
        }

        const {data:res}=await this.$http.post(`/Sal/add/deleteInfoBySid/${id}`)

        // if(res.meta.status!==200){
        //     return this.$message.error('删除用户失败！')
        // }
        this.$message.success('删除用户成功！')
        this.getUserList()
},
//展示分配角色的对话框
async setRole(userInfo){
    this.userInfo=userInfo
   
    //在展示对话框之前，获取所有角色的列表
    const {data:res}=await this.$http.get('roles')
    if(res.meta.status!==200){
        return this.$message.error('获取角色列表失败！')
    }

    this.rolesList=res.data

    this.setRolesDialogVisible=true
},
//点击按钮，分配角色
async saveRoleInfo(){
   if(!this.selectedRoleId){
       return this.$message.error('请选择要分配的角色!')
   }

   const {data:res}= await this.$http.put(`users/${this.userInfo.id}/role`,{
       rid:this.selectedRoleId
   })

   if(res.meta.status!==200){
       return this.$message.error('更新角色失败！')
   }

   this.$message.success('更新角色成功！')
   this.getUserList()
   this.setRolesDialogVisible=false
},
//监听分配角色对话框的关闭事件
setRoleDialogClosed(){
    this.selectedRoleId=''
    this.userInfo={}
}
 },
 created(){
     this.getUserList()
 },


}
</script>

<style lang="less" scoped>

</style>