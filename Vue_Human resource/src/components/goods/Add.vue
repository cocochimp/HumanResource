<template>
  <div>
       <!-- 面包屑导航区域 -->
      <el-breadcrumb separator-class="el-icon-arrow-right">
  <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
  <el-breadcrumb-item>档案管理</el-breadcrumb-item>
  <el-breadcrumb-item>添加档案</el-breadcrumb-item>
</el-breadcrumb>
     
     <!-- 卡片视图区域 -->
     <el-card>
         <!-- 提示区域 -->
         <el-alert title="添加档案信息" type="info" center show-icon :closable="false"></el-alert>

         <!-- 步骤条区域 -->
         <!-- active用于指定激活项 -->
         <!--   -0的目的是将activeIndex转化为数值类型 -->
         <el-steps :space="200" :active="activeIndex-0" finish-status="success" align-center>
            <el-step title="基本信息"></el-step>
            <el-step title="档案参数"></el-step>
            <el-step title="档案属性"></el-step>
            <el-step title="个人图片"></el-step>
            <el-step title="个人介绍"></el-step>
            <el-step title="完成"></el-step>
        </el-steps>

        <!-- Tabs栏区域 -->
        <!-- 只能在tabs表单外面包裹form，不允许在tabs和tab-pane中间放form -->
        <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="100px" label-position="top">
          <el-tabs :tab-position="'left'" v-model="activeIndex" :before-leave="beforeTabLeave" @tab-click="tabClicked">
            <el-tab-pane label="基本信息" name="0">
                <el-form-item label="名称" prop="name">
                   <el-input v-model="addForm.name"></el-input>
                </el-form-item>
                <el-form-item label="性别" prop="sex">
                   <el-input v-model="addForm.sex" ></el-input>
                </el-form-item>
                <el-form-item label="年龄" prop="age">
                   <el-input v-model="addForm.age" type="number"></el-input>
                </el-form-item>
                <el-form-item label="职称" prop="professionId">
                   <el-cascader
                    v-model="addForm.professionId"
                    :options="catelist"
                    :props="cateProps"
                    @change="handleChange">
              </el-cascader>
                </el-form-item>
                <el-form-item label="民族" prop="nationId">
                   <el-cascader
                    v-model="addForm.nationId"
                    :options="nationlist"
                    :props="nationProps"
                    @change="handleChange">
              </el-cascader>
                </el-form-item>
            </el-tab-pane>

            <el-tab-pane label="档案参数" name="1">
                <!-- 渲染表单的item项 -->
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
                   <el-form-item label="所属三级组织" prop="organizionId">
                   <el-cascader
                    v-model="addForm.organizionId"
                    :options="parentCateListfour"
                    :props="parentCatePropsfour"
                    @change="handleChange">
              </el-cascader>
                </el-form-item>
                <el-form-item label="学历" prop="academicId">
                   <el-cascader
                    v-model="addForm.academicId"
                    :options="academiclist"
                    :props="academicProps"
                    @change="handleChange">
              </el-cascader>
                </el-form-item>
                 <el-form-item label="信仰宗教" prop="religionId">
                   <el-cascader
                    v-model="addForm.religionId"
                    :options="religionlist"
                    :props="religionProps"
                    @change="handleChange">
              </el-cascader>
                </el-form-item>
                
            </el-tab-pane>
            <el-tab-pane label="档案属性" name="2">
                 <el-form-item label="qq" prop="qq">
                   <el-input v-model="addForm.qq"></el-input>
                </el-form-item>
                <el-form-item label="地址" prop="address">
                   <el-input v-model="addForm.address" ></el-input>
                </el-form-item>
                <el-form-item label="个人履历" prop="personalHistory">
                   <el-input v-model="addForm.personalHistory" ></el-input>
                </el-form-item>
                <el-form-item label="家庭关系信息" prop="familyRelationship">
                   <el-input v-model="addForm.familyRelationship" ></el-input>
                </el-form-item>
                 <el-form-item label="邮箱" prop="email">
                   <el-input v-model="addForm.email"></el-input>
                </el-form-item>
                <el-form-item label="电话" prop="phone">
                   <el-input v-model="addForm.phone" ></el-input>
                </el-form-item>
            </el-tab-pane>
            <el-tab-pane label="个人图片" name="3">
                <el-upload
                    action="http://localhost:9109/File/add/picture"
                    :on-preview="handlePreview"
                    :on-remove="handleRemove"
                    list-type="picture"
                    :headers="headerObj"
                    :on-success="handleSuccess">
                    <el-button size="small" type="primary">点击上传</el-button>
                    <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
                </el-upload>
            </el-tab-pane>
            <el-tab-pane label="个人介绍" name="4">
                <!-- 富文本编辑器组件 -->
                <quill-editor v-model="addForm.remarks"></quill-editor>
                <!-- 添加商品的按钮 -->
                <el-button type="primary" class="btnAdd" @click="add">添加档案</el-button>
            </el-tab-pane>
        </el-tabs>
        </el-form>
     </el-card>

     <!-- 预览图片的对话框 -->
      <el-dialog
                title="预览图片"
                :visible.sync="previewVisible"
                width="50%"
                >
                <!-- 内容主体区域 -->
                <img :src="previewPath" alt="" class="previewImg">
        </el-dialog>
  </div>
</template>

<script>
import a from 'lodash'

export default {
 name:'addPage',
 data(){
     return{
        activeIndex:'0',
        //添加商品的表单数据对象
        addForm:{
            uId:'',
            name:'',
            sex:'',
            age:18,
            professionId:'',
             //商品所属的分类数组
           nationId:'',
           organizionId:'',
           academicId:'',
           religionId:'',
             //图片的数组
            //pics:[],
            //商品的详情描述
            //goods_introduce:'',
            //商品的属性或参数
            //attrs:[],
            email:'',
            phone:'',
            qq:'',
            address:'',
            personalHistory:'',
            familyRelationship:'',
            remarks:'',
            image:''
        },
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
         parentCatePropsfour:{
            value:'o3_id',
             label:'o3_name',
             children:'children',
             expandTrigger: 'hover'
         },
         //选中的父级分类的id数组
         selectedKeysthree:'',
        addFormRules:{
            goods_name:[
                {required:true,message:'请输入商品名称',trigger:'blur'}
            ],
            goods_price:[
                {required:true,message:'请输入商品价格',trigger:'blur'}
            ],
            goods_weight:[
                {required:true,message:'请输入商品重量',trigger:'blur'}
            ],
             goods_number:[
                {required:true,message:'请输入商品数量',trigger:'blur'}
            ],
            goods_cat:[
                {required:true,message:'请选择商品分类',trigger:'blur'}
            ],
        },
        //商品分类列表
        catelist:[],
        //级联选择器的配置对象
        cateProps:{
           value:'p_id',
           label:'profession',
           children:'children',
           expandTrigger: 'hover',
       },
       //动态参数列表数据
       manyTableData:[],
       //静态属性列表数据
       onlyTableData:[],
       //图片上传组件的headers请求头对象
       headerObj:{
           Authorization:window.sessionStorage.getItem('token')
       },
       //图片预览路径
       previewPath:'',
       previewVisible:false,
       nationlist:[],
       nationProps:{
           value:'n_id',
           label:'nation',
           children:'children',
           expandTrigger: 'hover',
       },
        academiclist:[],
       academicProps:{
           value:'a_id',
           label:'academic',
           children:'children',
           expandTrigger: 'hover',
       },
        religionlist:[],
       religionProps:{
           value:'r_id',
           label:'religion',
           children:'children',
           expandTrigger: 'hover',
       },
       parentCateListfour:[]
     }
 },
 methods:{
 //获取所有商品分类数据
 async getCateList(){
     const {data:res1}=await this.$http.post('/File/add/queryProfession')
     const {data:res2}=await this.$http.post('/File/add/queryNation')
     const {data:res3}=await this.$http.post('/File/add/queryAcademic')
     const {data:res4}=await this.$http.post('/File/add/queryReligion')

        //  if(res.meta.status!==200){
        //     return this.$message.error('获取商品分类数据失败!')
        //  }
         this.catelist=res1.data
         this.nationlist=res2.data
         this.academiclist=res3.data
         this.religionlist=res4.data
          const {data:res5}=await this.$http.post('/Sys/organ/queryOrgan1')
        //  const {data:res2}=await this.$http.post('/Sys/organ/queryOrgan2')

        //  if(res.meta.status!==200){
        //     return this.$message.error('获取父级分类数据失败!')
        //  }
     
         this.parentCateList=res5.data
 },
 //级联选择器选中项变化，会触发这个函数
 handleChange(){
     if(this.addForm.goods_cat.length!==3){
         this.addForm.goods_cat=[]
     }
 },
  async parentCateChangedtwo(){
     const {data:res2}=await this.$http.post(`/Sys/organ/queryOrgan2/${this.selectedKeys}`)
         this.parentCateListthree=res2.data
    },
    async parentCateChangedthree(){
     const {data:res2}=await this.$http.post(`/Sys/organ/queryOrgan3/${this.selectedKeysthree}`)
         this.parentCateListfour=res2.data
    },
//  beforeTabLeave(activeName,oldActiveName){
//    if(oldActiveName==='0'&& this.addForm.goods_cat.length!==3){
//        this.$message.error('请先选择商品分类')
//        return false
//    }
//  },
 //标签页切换时马上触发
  async tabClicked(){
     //根据activeIndex来发起对应的请求
    //  if(this.activeIndex==='1'){
    //      const {data:res}=await this.$http.get(`categories/${this.cateId}/attributes`,{
    //          params: { sel: 'many'}
    //      })
    //      if(res.meta.status!==200){
    //          return this.$message.error('获取动态参数列表失败！')
    //      }
    //      res.data.forEach(item=>{
    //          item.attr_vals=item.attr_vals.length===0?[]:item.attr_vals.split(/,|，|\s+/)
    //      })
    //      this.manyTableData=res.data
    //  }else if(this.activeIndex==='2'){
    //      const {data:res}=await this.$http.get(`categories/${this.cateId}/attributes`,{
    //          params:{sel:'only'}
    //      })
    //      if(res.meta.status!==200){
    //          return this.$message.error('获取静态参数失败！')
    //      }
    //      this.onlyTableData=res.data
    //  }
 },
 //处理图片预览效果
 handlePreview(file){
    this.previewPath=file.response.data.url
    this.previewVisible=true
 },
 //处理移除图片的操作(file为将要被移除的图片)
 handleRemove(file){
    //1.获取将要删除的图片的临时路径
    //2.从pics数组中，找到这个图片对应的索引值
    //3.调用数组的splice方法，把图片信息对象，从pics数组中移除
    const filePath=file.response.data.tmp_path
    this.addForm.pics= this.addForm.pics.filter(item=>item.pic!==filePath)

    // const i=this.addForm.pics.findIndex(x=>x.pic===filePath)
    // this.addForm.pics.splice(i,1)
    
 },
 //监听图片上传成功的事件
 handleSuccess(response){
   //1.拼接得到一个图片信息对象
   console.log(response);
//    const picInfo={pic:response.data.tmp_path}
   //2.将图片信息对象，push到pics数组中
//    this.addForm.pics.push(picInfo)
this.addForm.image=response.data
 },
 //先对全部的表单数据进行验证，后添加商品
 add(){
     this.$refs.addFormRef.validate(async valid=>{
         if(!valid){
             return this.$message.error('请填写必要的表单项！')
         }
         //执行添加的业务逻辑
         //lodash cloneDeep(obj)
        //  const form =_.cloneDeep(this.addForm)
        //  form.goods_cat=form.goods_cat.join(',')
         //处理动态参数(动态属性的attr_vals是数组，要进行处理转化为字符串)
        //  this.manyTableData.forEach(item=>{
        //      const newInfo={attr_id:item.attr_id,attr_value:item.attr_vals.join(' ')}
        //      form.attrs.push(newInfo)
        //  })
         //处理静态属性
        //   this.onlyTableData.forEach(item=>{
        //      const newInfo={attr_id:item.attr_id,attr_value:item.attr_vals}
        //      form.attrs.push(newInfo)
        //  })
      

         //发起请求添加商品
         //商品的名称，必须是唯一的
         this.addForm.uId=window.sessionStorage.getItem('uid')
         console.log(window.sessionStorage.getItem('uid'));
         const {data:res} =await this.$http.post(`/File/add/${this.addForm.name}/${this.addForm.age}/${this.addForm.professionId[0]}/${this.addForm.organizionId[0]}/${this.addForm.sex}/xxx`
            )

        //  if(res.meta.status!==201){
        //      return this.$message.error('添加商品失败！')
        //  }
         this.$message.success('添加档案成功！')
         this.$router.push('/file/add')
     })
 }
 },
 created(){
   this.getCateList()
 },
 computed:{
     //方便函数调用时使用分类的三级iD
     cateId(){
         if(this.addForm.goods_cat.length===3){
             return this.addForm.goods_cat[2]
         }
         return null
     }
 }
}
</script>

<style lang="less" scoped>
.el-checkbox{
    margin:0 7px 0 0!important;
}
.previewImg{
    width: 100%;
}
.btnAdd{
    margin-top: 15px;
}
</style>