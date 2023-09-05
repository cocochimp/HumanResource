<template>
  <div>
      <!-- 面包屑导航区域 -->
      <el-breadcrumb separator-class="el-icon-arrow-right">
  <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
  <el-breadcrumb-item>商品管理</el-breadcrumb-item>
  <el-breadcrumb-item>商品列表</el-breadcrumb-item>
</el-breadcrumb>


    <!-- 卡片试图区域 -->
 <el-card >
     <!-- 搜索与添加区域 -->
     <!-- gutter为设置右边间隔 -->
        <el-row :gutter="20">
            <el-col :span="8">
                <!-- clearable添加了为可清空的文本框 -->
                 <el-input placeholder="请输入内容"  class="input-with-select" v-model="queryInfo.query"  clearable @clear="getGoodsList">
                    <el-button slot="append" icon="el-icon-search" @click="getGoodsList"></el-button>
                 </el-input>
            </el-col>
            <el-col :span="4">
                <el-button type="primary" @click="goAddpage">添加商品</el-button>
            </el-col>
        </el-row>

        <!-- 商品列表区域 -->
        <!-- border为边框线,stripe为滑动后会变化颜色 -->
        <el-table :data="goodslist" border stripe>
            <el-table-column type="index" label="#"></el-table-column>
            <el-table-column label="商品名称" prop="goods_name"></el-table-column>
            <el-table-column label="商品价格(元)" prop="goods_price" width="110px"></el-table-column>
            <el-table-column label="商品重量" prop="goods_weight" width="80px"></el-table-column>
            <el-table-column label="创建时间" prop="add_time" width="170px">
                <template slot-scope="scope">
                        {{scope.row.add_time | dateFormat}}
                </template>
            </el-table-column>
            <el-table-column label="操作" width="130px">
                <template slot-scope="scope">
                        <!-- 修改按钮 -->
                        <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditDialog(scope.row.id)"></el-button>
                        <!-- 删除按钮 -->
                        <el-button type="danger" icon="el-icon-delete"  size="mini" @click="removeById(scope.row.goods_id)"></el-button>
                </template>
            </el-table-column>
        </el-table>


        <!-- 分页区域 -->
         <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="queryInfo.pagenum"
            :page-sizes="[5,10,15,20]"
            :page-size="queryInfo.pagesize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total" background>
    </el-pagination>
     </el-card>

  </div>
</template>

<script>
export default {
 name:'listPage',
 data(){
     return{
      //查询参数对象
      queryInfo:{
          query:'',
          pagenum:1,
          pagesize:10
      },
      //商品列表
      goodslist:[],
      //总数据条数
      total:0
     }
 },
 created(){
   this.getGoodsList()
 },
 methods:{
   //根据分页获取对应的商品列表
   async getGoodsList(){
       const {data:res} = await this.$http.get('goods',{params:this.queryInfo})
      if(res.meta.status!==200){
          return this.$message.error('获取商品列表失败！')
      }
      
      this.goodslist=res.data.goods
      this.total=res.data.total
   },
   handleSizeChange(newSize){
       this.queryInfo.pagesize=newSize
       this.getGoodsList()
   },
   handleCurrentChange(newPage){
       this.queryInfo.pagenum=newPage
       this.getGoodsList()
   },
    //根据Id删除对应的商品
     async removeById(goods_id){
            const confirmResult= await this.$confirm('此操作将永久删除该商品, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
                }).catch(err=>err)

                //如果用户确认删除，则返回值为字符串confirm
                //如果用户取消了删除，则返回值为字符串 cancel
                if(confirmResult!=='confirm'){
                    return this.$message.info('已取消删除')
                }
                //删除的业务逻辑
                const {data:res}=await this.$http.delete(`goods/${goods_id}`)

                if(res.meta.status!==200){
                    return this.$message.error('删除失败！')
                }
                this.$message.success('删除成功！')
                this.getGoodsList()
     },
     goAddpage(){
         this.$router.push('/goods/add')
     }
 }
}
</script>

<style lang="less" scoped>

</style>