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
            <el-col :span="10" style="margin:10px 0">
                <!-- clearable添加了为可清空的文本框 -->
                 <el-input placeholder="搜索一级机构"  class="input-with-select" v-model="queryInfo.query1" clearable @clear="getUserList">
                    <el-button slot="append" icon="el-icon-search" @click="getUserListByName1"></el-button>
                 </el-input>
            </el-col>
           
            <el-col :span="10" style="margin:10px 0">
               <el-input placeholder="搜索二级机构"  class="input-with-select" v-model="queryInfo.query2" clearable @clear="getUserList">
                    <el-button slot="append" icon="el-icon-search" @click="getUserListByName2"></el-button>
                 </el-input>
            </el-col>
            
             <el-col :span="10" >
               <el-input placeholder="搜索三级机构"  class="input-with-select" v-model="queryInfo.query3" clearable @clear="getUserList">
                    <el-button slot="append" icon="el-icon-search" @click="getUserListByName3"></el-button>
                 </el-input>
            </el-col>
             <el-col :span="10">
               <el-input placeholder="职位名称"  class="input-with-select" v-model="queryInfo.query4" clearable @clear="getUserList">
                    <el-button slot="append" icon="el-icon-search" @click="getUserListByName4"></el-button>
                 </el-input>
            </el-col>
        </el-row>

        <!-- 用户列表区域 -->
        <!-- border为边框线,stripe为滑动后会变化颜色 -->
        <el-table :data="userlist" border stripe>
            <el-table-column type="index"></el-table-column>
            <el-table-column label="职位" prop="profession"></el-table-column>
            <el-table-column label="名字" prop="name"></el-table-column>
            <el-table-column label="所属一级机构" prop="o1_name"></el-table-column>
            <el-table-column label="所属二级机构" prop="o2_name"></el-table-column>
            <el-table-column label="所属三级机构" prop="o3_name"></el-table-column>
            <el-table-column label="性别" prop="sex"></el-table-column>
            <!-- <el-table-column label="邮箱" prop="email"></el-table-column>
            <el-table-column label="电话" prop="mobile"></el-table-column> -->
         
            <!-- <el-table-column label="状态" prop="mg_state">
                <template slot-scope="scope">
                        <el-switch
                    v-model="scope.row.mg_state" @change="userStateChanged(scope.row)"
                   >
                    </el-switch>
                </template>
                
            </el-table-column> -->
            <el-table-column label="操作">
                <template slot-scope="scope">
                        <!-- 修改按钮 -->
                        <el-button type="success" icon="el-icon-zoom-in" size="mini" @click="showEditDialog(scope.row.f_id)"></el-button>
                        <!-- 删除按钮 -->
                        <!-- <el-button type="danger" icon="el-icon-delete"  size="mini" @click="removeUserById(scope.row.id)"></el-button> -->
                        <!-- 分配角色按钮 -->
                         <!-- <el-tooltip  effect="dark" content="分配角色" placement="top" :enterable="false">
                           <el-button type="warning" icon="el-icon-setting"  size="mini" @click="setRole(scope.row)"></el-button>
                         </el-tooltip> -->
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
            title="添加一级机构"
            :visible.sync="addDialogVisible"
            width="50%"
            @close="addDialogClosed"
            >
            <!-- 内容主体区域 -->
            <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="110px" >
                <el-form-item label="一级机构名称" prop="username">
                    <el-input v-model="addForm.username"></el-input>
                </el-form-item>
            
            </el-form>    
            <!-- 底部区域 -->
            <span slot="footer" class="dialog-footer">
                <el-button @click="addDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="addUser">确 定</el-button>
            </span>
    </el-dialog>

        <!--修改用户的对话框  -->
        <el-dialog
            title="档案复核"
            :visible.sync="editDialogVisible"
            width="50%"
            @close="editDialogClosed"
            >
          
            <el-form ref="editFormRef" :model="editForm" :rules="editFormRules" label-width="100px" >
                <!-- prop是绑定验证规则的属性 -->
          
                     <el-image
      style="width: 140px; height: 180px; transform:translateX(200%)"
      src="http://rluc5ispc.hn-bkt.clouddn.com/2022/12/18/9d51d40e5e2740b9b285c4b22d1af6af.jpg"
      ></el-image>
               
                <el-form-item label="职位名称" >
                    <el-input  v-model="editForm.academic" disabled></el-input>
                </el-form-item>
                <el-form-item label="姓名">
                    <el-input v-model="editForm.name" disabled></el-input>
                </el-form-item>
                 <el-form-item label="所属一级机构">
                    <el-input v-model="editForm.o1_name" disabled></el-input>
                </el-form-item>
                <el-form-item label="所属二级机构">
                    <el-input v-model="editForm.o2_name" disabled></el-input>
                </el-form-item>
                <el-form-item label="所属三级机构">
                    <el-input v-model="editForm.o3_name" disabled></el-input>
                </el-form-item>
                <el-form-item label="性别">
                    <el-input v-model="editForm.sex" disabled></el-input>
                </el-form-item>
             </el-form>
            
        </el-dialog>

<!-- 添加二级机构 -->
         <el-dialog
                title="添加二级机构"
                :visible.sync="addCateDialogVisibletwo"
                width="50%"
                @close="addCateDialogClosed"
               >
                <!-- 添加分类的表单 -->
                <el-form :model="addCateForm" :rules="addCateFormRules" ref="addCateFormRef" label-width="130px">
                    <el-form-item label="二级机构名称" prop="cat_name">
                        <el-input v-model="addCateForm.cat_name"></el-input>
                    </el-form-item>
                    <el-form-item label="所属一级机构名称">
                        <!-- options用来指定数据源 , props用来指定配置对象（如展示什么与得到什么） change-on-select可以选择任意一级的数据-->
                            <el-cascader
                            v-model="selectedKeys"
                            :options="parentCateList"
                            :props="cascaderProps"
                            @change="parentCateChanged"
                            clearable
                            change-on-select
                           >
                        </el-cascader>
                         
                    </el-form-item>
                </el-form>    
                <span slot="footer" class="dialog-footer">
                    <el-button @click="addCateDialogVisibletwo = false">取 消</el-button>
                    <el-button type="primary" @click="addCate">确 定</el-button>
                </span>
            </el-dialog>
        <!-- 添加三级机构 -->
        <el-dialog
                title="添加三级机构"
                :visible.sync="addCateDialogVisiblethree"
                width="50%"
                @close="addCateDialogClosed"
               >
                <!-- 添加分类的表单 -->
                <el-form :model="addCateFormthree" :rules="addCateFormRules" ref="addCateFormRef" label-width="130px">
                    <el-form-item label="三级机构名称" prop="cat_name">
                        <el-input v-model="addCateFormthree.cat_name"></el-input>
                    </el-form-item>
                      <el-form-item label="所属一级机构名称">
                        <!-- options用来指定数据源 , props用来指定配置对象（如展示什么与得到什么） change-on-select可以选择任意一级的数据-->
                            <el-cascader
                            v-model="selectedKeys"
                            :options="parentCateList"
                            :props="cascaderProps"
                            @change="parentCateChangedtwo"
                            clearable
                            change-on-select
                           >
                        </el-cascader>
                         
                    </el-form-item>
                    <el-form-item label="所属二级机构名称">
                        <!-- options用来指定数据源 , props用来指定配置对象（如展示什么与得到什么） change-on-select可以选择任意一级的数据-->
                            <el-cascader
                            v-model="selectedKeysthree"
                            :options="parentCateListthree"
                            :props="cascaderPropsthree"
                            @change="parentCateChangedthree"
                            clearable
                            change-on-select
                           >
                        </el-cascader>
                         
                    </el-form-item>
                </el-form>    
                <span slot="footer" class="dialog-footer">
                    <el-button @click="addCateDialogVisiblethree = false">取 消</el-button>
                    <el-button type="primary" @click="addCatethree">确 定</el-button>
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
         addCateForm:{
             //将要添加的分类的名称
             cat_name:'',
             //父级分类的id
             cat_pid:0,
             //分类的等级，默认要添加的是1级分类(0为1级分类，1为2级分类，2为3级分类)
             cat_level:0
         },
         addCateFormthree:{
             //将要添加的分类的名称
             cat_name:'',
             //父级分类的id
             cat_pid:0,
             //分类的等级，默认要添加的是1级分类(0为1级分类，1为2级分类，2为3级分类)
             cat_level:0
         },
         //添加分类表单的验证规则对象
         addCateFormRules:{
             cat_name:[
                 {required:true,message:'请输入分类名称',trigger:'blur'}
             ]
         },
      //获取用户列表的参数对象
      queryInfo:{
          //搜索关键字
          query1:'',
          query2:'',
          query3:'',
          query4:'',
          //当前的页数
          pagenum: 1,
          //当前每页显示多少条数据
          pagesize: 10
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
              {required:true,message:'请输入一级机构名称',trigger:'blur' },
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
      addCateDialogVisiblethree:false,
      addCateDialogVisibletwo:false,
      //修改用户信息时查询到的用户信息对象
      editForm:{},
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
      selectedRoleId:'',
      //父级分类的列表
         parentCateList:[],
         //指定级联选择器的配置对象
         cascaderProps:{
             value:'o1_id',
             label:'o1_name',
             children:'children',
             expandTrigger: 'hover'
         },
         //选中的父级分类的id数组
         selectedKeys:'',
         parentCateListthree:[],
         //指定级联选择器的配置对象
         cascaderPropsthree:{
             value:'o2_id',
             label:'o2_name',
             children:'children',
             expandTrigger: 'hover'
         },
         //选中的父级分类的id数组
         selectedKeysthree:''
     }
 },
 methods:{
    async parentCateChangedtwo(){
        console.log(555);
     const {data:res2}=await this.$http.post(`/Sys/organ/queryOrgan2/${this.selectedKeys}`)
         this.parentCateListthree=res2.data
    },

     //获取用户列表
   async getUserList(){
       this.queryInfo.pagenum=0
      const {data:res} = await this.$http.post(`/File/query/showFileSelect/${this.queryInfo.pagenum}/${this.queryInfo.pagesize}`)
    //   if(res.meta.status!==200){
    //       return this.$message.error('获取用户列表失败！')
    //   }


      this.userlist=res.data
      this.total=8
    const {data:res1}=await this.$http.post('/Sys/organ/queryOrgan1')
        //  const {data:res2}=await this.$http.post('/Sys/organ/queryOrgan2')

        //  if(res.meta.status!==200){
        //     return this.$message.error('获取父级分类数据失败!')
        //  }
     
         this.parentCateList=res1.data
  


   },
    addCate(){
         this.$refs.addCateFormRef.validate(async valid=>{
             if(!valid) return
             const {data:res} =await this.$http.post(`/Sys/organ/insertOrgan2?o1Id=${this.selectedKeys}&o2Name=${this.addCateForm.cat_name}`)

            //  if(res.meta.status!==201){
            //      return this.$message.error('添加分类失败！')
            //  }

             this.$message.success('添加分类成功！')
            //  this.getCateList()
             this.addCateDialogVisibletwo=false
         })
     },
      addCatethree(){
         this.$refs.addCateFormRef.validate(async valid=>{
             if(!valid) return
             const {data:res} =await this.$http.post(`/Sys/organ/insertOrgan3?o2Id=${this.selectedKeysthree}&o3Name=${this.addCateFormthree.cat_name}`)

            //  if(res.meta.status!==201){
            //      return this.$message.error('添加分类失败！')
            //  }

             this.$message.success('添加分类成功！')
            //  this.getCateList()
             this.addCateDialogVisiblethree=false
         })
     },
   //获取一级权限分类列表
    async getParentCateList(){
         const {data:res1}=await this.$http.post('/Sys/organ/queryOrgan1')
        //  const {data:res2}=await this.$http.post('/Sys/organ/queryOrgan2')

        //  if(res.meta.status!==200){
        //     return this.$message.error('获取父级分类数据失败!')
        //  }
     
         this.parentCateList=res1.data
         this.parentCateListthree=res2.data
     },
    async getUserListByName1(){
       this.queryInfo.pagenum=0
      const {data:res} = await this.$http.post(`/File/query/showFileSelectByOrgan1/${this.queryInfo.pagenum}/${this.queryInfo.pagesize}/${this.queryInfo.query1}`)
    //   if(res.meta.status!==200){
    //       return this.$message.error('获取用户列表失败！')
    //   }
      console.log(res.data);
      this.userlist=res.data
      this.total=8
    
   },
     async getUserListByName2(){
       this.queryInfo.pagenum=0
      const {data:res} = await this.$http.post(`/File/query/showFileSelectByOrgan2/${this.queryInfo.pagenum}/${this.queryInfo.pagesize}/${this.queryInfo.query2}`)
    //   if(res.meta.status!==200){
    //       return this.$message.error('获取用户列表失败！')
    //   }
      console.log(res.data);
      this.userlist=res.data
      this.total=8
    
   },
     async getUserListByName3(){
       this.queryInfo.pagenum=0
      const {data:res} = await this.$http.post(`/File/query/showFileSelectByOrgan3/${this.queryInfo.pagenum}/${this.queryInfo.pagesize}/${this.queryInfo.query3}`)
    //   if(res.meta.status!==200){
    //       return this.$message.error('获取用户列表失败！')
    //   }
      console.log(res.data);
      this.userlist=res.data
      this.total=8
    
   },
     async getUserListByName4(){
       this.queryInfo.pagenum=0
      const {data:res} = await this.$http.post(`/File/query/showFileSelectByProfession/${this.queryInfo.pagenum}/${this.queryInfo.pagesize}/${this.queryInfo.query4}`)
    //   if(res.meta.status!==200){
    //       return this.$message.error('获取用户列表失败！')
    //   }
      console.log(res.data);
      this.userlist=res.data
      this.total=8
    
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
 addUser(){
     this.$refs.addFormRef.validate(async valid=>{
         if(!valid) return     
         //可以发起添加用户的网络请求
         const {data:res}=await this.$http.post(`/Sys/organ/insertOrgan1?o1Name=${this.addForm.username}`)

        //  if(res.meta.status!==201){
        //      this.$message.error('添加用户失败！')
        //  }
         this.$message.success('添加用户成功!')
         //隐藏添加用户的对话框
         this.addDialogVisible=false
         //重新获取用户列表数据
         this.getUserList()


     })
 },
 //展示编辑用户的对话框
 async showEditDialog(id){
  this.editDialogVisible=true
window.sessionStorage.setItem("id",id)
    const {data:res}= await this.$http.post('/File/showFileInfoByFid/'+id)

    // if(res.meta.status!==200){
    //     return this.$message.error('查询用户信息失败！')
    // }
    this.editForm=res.data[0]
    if(this.editForm.image==''){
        this.editForm.image='http://rluc5ispc.hn-bkt.clouddn.com/2022/12/18/9d51d40e5e2740b9b285c4b22d1af6af.jpg'
    }
    this.editDialogVisible=true
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

        const {data:res}=await this.$http.post(`/Sys/organ/deleteOrgan3/${id}`)

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
 },
mounted(){
     this.getUserList()



}

}
</script>

<style lang="less" scoped>

</style>