<template>
    <el-collapse @change="change">
      <el-collapse-item :title="computeName(name)"
                        :name="name"  v-for="(name,index) in items" :key="index">
        <slot :name="name"></slot>
      </el-collapse-item>
      <slot />
    </el-collapse>
</template>

<script>
    export default {
      name: "show-hide-table",
      props: {
        items: {
          type: Array,
          default: []
        }
      },
      data() {
        return{
          isActive: []
        }
      },
      methods: {
        change(val){
          this.isActive = []
          val.forEach(item => {
            this.isActive.push(item)
          })
        },
        computeName(name){
          if (name==null||typeof(name)=='undefined'){
            name=''
          }
          if (this.isActive.indexOf(name)===-1){
            return name + '(隐藏)'
          } else {
            return name + '(展开)'
          }
        }
      }
    }
</script>

<style scoped>

</style>
