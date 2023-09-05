<template>
  <div>
       <!-- 面包屑导航区域 -->
      <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>权限管理</el-breadcrumb-item>
            <el-breadcrumb-item>角色列表</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 卡片视图 -->
        <el-card>
            <!-- 添加角色按钮区域 -->
            <el-row>
                <el-col>
                    <el-button type="primary" @click="addRolesVisible=true">添加角色</el-button>
                </el-col>
            </el-row>

            <!-- 角色列表区域 -->
            <!-- 通过el-row来循环渲染，因为一级权限对应着二级，二级对应着三级 -->
            <el-table :data="rolelist" border stripe>
                  <!-- 展开列 -->
                  <el-table-column type="expand">
                      <template slot-scope="scope">
                          <el-row v-for="(item1,i1) in scope.row.children" :key="item1.id" :class="['bdbottom',i1===0?'bdtop':'','vcenter']">
                              <!-- 渲染一级权限 -->
                              <el-col :span="5">
                                  <el-tag closable @close="removeRightById(scope.row,item1.id)">{{item1.authName}}</el-tag>
                                  <i class="el-icon-caret-right"></i>
                              </el-col>
                              <!-- 渲染二级和三级权限 -->
                              <el-col :span="19">
                                  <!-- 通过for循环 嵌套渲染二级权限 -->
                                  <el-row v-for="(item2,i2) in item1.children" :key="item2.id" :class="[i2===0?'':'bdtop','vcenter']">
                                      <!-- 二列 -->
                                     <el-col :span="6">
                                         <el-tag type="success" closable @close="removeRightById(scope.row,item2.id)">{{item2.authName}}</el-tag>
                                          <i class="el-icon-caret-right"></i>
                                     </el-col>
                                     <!-- 三列 -->
                                     <el-col :span="18">
                                         <el-tag type="warning" v-for="(item3) in item2.children" 
                                         :key="item3.id" closable @close="removeRightById(scope.row,item3.id)">
                                             {{item3.authName}}
                                         </el-tag>
                                     </el-col>
                                  </el-row>
                              </el-col>
                          </el-row>
                      </template>  
                    </el-table-column>     
                  <!-- 索引列 -->
                  <el-table-column type="index"></el-table-column>     
                  <el-table-column label="角色名称" prop="roleName"></el-table-column>     
                  <el-table-column label="角色描述" prop="roleDesc"></el-table-column>     
                  <el-table-column label="操作">
                      <template slot-scope="scope">
                        <el-button size="mini" type="primary" icon="el-icon-search" @click="showEditRoles(scope.row.id)">编辑</el-button>      
                        <el-button size="mini" type="danger" icon="el-icon-delete" @click="removeRolesById(scope.row.id)">删除</el-button>      
                        <el-button size="mini" type="warning" icon="el-icon-setting" @click="showSetRightDialog(scope.row)">分配权限</el-button>      
                      </template>    
                  </el-table-column>     
            </el-table>
        </el-card>


          <!-- 添加角色的对话框 -->
        <!-- visible.sync为控制显示和隐藏 -->
        <el-dialog
                title="添加角色"
                :visible.sync="addRolesVisible"
                width="50%"
                @close="addRolesClosed"
                >
                <!-- 内容主体区域 -->
                <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="80px" >
                    <el-form-item label="角色名称" prop="roleName">
                        <el-input v-model="addForm.roleName"></el-input>
                    </el-form-item>
                    <el-form-item label="角色描述" prop="roleDesc">
                        <el-input v-model="addForm.roleDesc"></el-input>
                    </el-form-item>
                </el-form>    
                <!-- 底部区域 -->
                <span slot="footer" class="dialog-footer">
                    <el-button @click="addRolesVisible = false">取 消</el-button>
                    <el-button type="primary" @click="addRoles">确 定</el-button>
                </span>
        </el-dialog>

        <!--修改角色的对话框  -->
        <el-dialog
            title="修改角色"
            :visible.sync="editRolesVisible"
            width="50%"
            @close="editRolesClosed"
            >
            <el-form ref="editFormRef" :model="editForm" :rules="editFormRules" label-width="80px" >
                <!-- prop是绑定验证规则的属性 -->
                <el-form-item label="角色ID">
                    <el-input v-model="editForm.roleId" disabled></el-input>
                </el-form-item>
                <el-form-item label="角色名称" prop="roleName">
                    <el-input v-model="editForm.roleName"></el-input>
                </el-form-item>
                <el-form-item label="角色描述" prop="roleDesc">
                    <el-input v-model="editForm.roleDesc"></el-input>
                </el-form-item>
             </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editRolesVisible = false">取 消</el-button>
                <el-button type="primary" @click="editRolesInfo">确 定</el-button>
            </span>
        </el-dialog>

        <!-- 分配权限的对话框 -->
        <el-dialog
            title="分配权限"
            :visible.sync="setRightDialogVisible"
            width="50%"
            @close="setRightDialogClosed"
            >
            <!-- 树形控件 -->
            <!-- data属性是其中树形绑定的数据，prop是显示在树中的内容 -->
            <el-tree :data="rightslist" :props="treeProps" ref="treeRef" show-checkbox node-key="id" default-expand-all :default-checked-keys="defKeys"></el-tree>
            <span slot="footer" class="dialog-footer">
                <el-button @click="setRightDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="allotRights">确 定</el-button>
            </span>
        </el-dialog>
  </div>
</template>

<script>
export default {
  name:'rolesPage',
  data(){
      return{
          //所有角色列表数据
          rolelist:[],
          //添加角色的表单数据
            addForm:{
                roleName:'',
                roleDesc:'',
            },
          //控制添加用户对话框的显示与隐藏
          addRolesVisible:false,
           //控制修改角色对话框的显示与隐藏
          editRolesVisible:false,
           //控制分配权限对话框的显示与隐藏
           setRightDialogVisible:false,
           //所有权限的数据（树形的）
           rightslist:[],
            //修改用户信息时查询到的角色信息对象
          editForm:{},
           //添加表单的验证规则对象
            addFormRules:{
                roleName:[
                    {required:true,message:'请输入角色名称',trigger:'blur' },
                    {min:3,max:10,message:'角色名的长度在3~10个字符之间',trigger:'blur'}
                    ],
                roleDesc:[
                    {required:true,message:'请输入角色描述',trigger:'blur' },
                    {min:6,max:15,message:'角色描述的长度在6~15个字符之间',trigger:'blur'}
                    ],         
            },
            // 修改表单的验证规则对象
            editFormRules:{
                roleName:[
                    {required:true,message:'请输入角色名称',trigger:'blur'},
                    {min:3,max:10,message:'角色名的长度在3~10个字符之间',trigger:'blur'}
                ],
               roleDesc:[
                    {required:true,message:'请输入角色描述',trigger:'blur'},
                    {min:5,max:10,message:'角色描述的长度在5~10个字符之间',trigger:'blur'}
                ]
            },
            //树形控件的属性绑定对象
            treeProps:{
                label:'authName',
                children:'children'
            },
            //树形控件默认选中的节点Id值数组
            defKeys:[],
            //当前即将分配权限的角色id
            roleId:''
            
      }
  },
  created(){
      this.getRolesList()
  },
  methods:{
      //获取所有角色的列表
      async getRolesList(){
        const {data:res}= await this.$http.get('roles')
        

        if(res.meta.status!==200){
            return this.$message.error('获取角色列表失败！')
        }

        this.rolelist=res.data
      },
      //展示编辑角色的对话框
        async showEditRoles(id){
           
            const {data:res}= await this.$http.get('roles/'+id)

            if(res.meta.status!==200){
                return this.$message.error('查询角色信息失败！')
            }
            this.editForm=res.data
        this.editRolesVisible=true
        },
        //监听添加用户对话框的关闭事件
        addRolesClosed(){
            this.$refs.addFormRef.resetFields()
        },
         //点击按钮 添加新用户
        addRoles(){
            this.$refs.addFormRef.validate(async valid=>{
                if(!valid) return     
                //可以发起添加用户的网络请求
                const {data:res}=await this.$http.post('roles',this.addForm)

                if(res.meta.status!==201){
                    this.$message.error('添加角色失败！')
                }
                this.$message.success('添加角色成功!')
                //隐藏添加角色的对话框
                this.addRolesVisible=false
                //重新获取角色列表数据
                this.getRolesList()
            })
        },
         //监听修改角色对话框的关闭事件
        editRolesClosed(){
            this.$refs.editFormRef.resetFields()
        },
         //修改角色信息并提交
        editRolesInfo(){
            this.$refs.editFormRef.validate(async valid=>{
                if(!valid) return
                //发起修改用户信息的数据请求
                const {data:res} =await this.$http.put('roles/'+this.editForm.roleId,{
                    roleName:this.editForm.roleName,
                    roleDesc:this.editForm.roleDesc
                })

                if(res.meta.status!==200){
                    return this.$message.error('更新用户信息失败！')
                }

                //关闭对话框
                this.editRolesVisible=false
                //刷新数据列表
                this.getRolesList()
                //提示用户
                this.$message.success('更新用户信息成功！')
            })
        },
        //根据Id删除对应的角色信息
        async removeRolesById(id){
            //弹框询问用户是否删除数据
            const confirmResult= await this.$confirm('此操作将永久删除该角色, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
                }).catch(err=>err)

                //如果用户确认删除，则返回值为字符串confirm
                //如果用户取消了删除，则返回值为字符串 cancel
                if(confirmResult!=='confirm'){
                    return this.$message.info('已取消删除')
                }

                const {data:res}=await this.$http.delete('roles/'+id)

                if(res.meta.status!==200){
                    return this.$message.error('删除角色失败！')
                }
                this.$message.success('删除角色成功！')
                this.getRolesList()
        },
        //根据id删除对应的权限
        async removeRightById(role,rightId){
           //弹框提示用户是否要删除
           const confirmResult=await this.$confirm('此操作将删除该权限，是否继续？','提示',
           {
               confirmButtonText:'确定',
               cancelButtonText:'取消',
               type:'warning'
           }
           ).catch(err=>err)

           if(confirmResult!=='confirm'){
               return this.$message.info('取消了删除！')
           }

           const {data:res}= await this.$http.delete(`roles/${role.id}/rights/${rightId}`)

           if(res.meta.status!==200){
               return this.$message.error('删除权限失败！')
           }

        //    this.getRolesList()
        role.children=res.data
        },
        //展示分配权限的对话框
        async showSetRightDialog(role){
            //由于弹出框拿不到用户id，因此只能通过在结构中的部分保存到data中
            this.roleId=role.id
           //获取所有权限的数据
           const {data:res}=await this.$http.get('rights/tree')

           if(res.meta.status!==200){
               return this.$message.error('获取权限数据失败！')
           }
           
           //把获取到的权限数据保存到data中
           this.rightslist=res.data
           
           //递归获取三级节点的Id
           this.getLeafKeys(role,this.defKeys)

           this.setRightDialogVisible=true
        },
        //通过递归的形式，获取角色下所有三级权限的id，并保存到defKeys数组中
        getLeafKeys(node,arr){
           //如果当前node节点不包含children属性，则是三级节点
           if(!node.children){
               return arr.push(node.id)
           }

           node.children.forEach(item=>
           this.getLeafKeys(item,arr))
        },
        //由于每次点开新的分配权限，会造成数组中的数据叠加，因此每次对话框关闭，要清空数组
        setRightDialogClosed(){
            this.defKeys=[]
        },
        //点击为角色分配权限
        async allotRights(){
            const keys=[
                ...this.$refs.treeRef.getCheckedKeys(),
                ...this.$refs.treeRef.getHalfCheckedKeys(),
            ]
            
            const idStr=keys.join(',')

            const {data:res}= await this.$http.post(`roles/${this.roleId}/rights`,{rids:idStr})

            if(res.meta.status!==200){
                return this.$message.error('分配权限失败！')
            }
            this.$message.success('分配权限成功！')
            this.getRolesList()
            this.setRightDialogVisible=false
        }
        
  }
}
</script>

<style lang="less" scoped>
.el-tag{
    margin: 7px;
    margin-left: 50px;
}

.bdtop{
    border-top: 1px solid #eee;
}

.bdbottom{
    border-bottom: 1px solid #eee;
}

.vcenter{
    display: flex;
    align-items: center;
}
</style>