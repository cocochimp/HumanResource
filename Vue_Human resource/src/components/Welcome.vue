<template>
  <div>
      <h1 style="font-size:40px">欢迎用户<span class="span">{{admin}}</span>进入人力资源管理系统!!</h1>
  </div>
</template>

<script>
export default {
  name:'welcomePage',
  data(){
    return{
      admin:window.sessionStorage.getItem("username")
    }
  }
}
</script>

<style scoped>
div{
  display: flex;
  justify-content: center;
  align-items: center;
}
.span{
  color: red;
}
</style>