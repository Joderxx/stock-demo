<template>
  <el-tabs type="card" v-model="items[0]">
    <el-tab-pane :label="name" :name="name" v-for="(name,index) in items" :key="index">
      <slot :name="name"></slot>
    </el-tab-pane>
  </el-tabs>
</template>

<script>
    export default {
      name: "select-tab",
      props: {
        items: {
          type: Array,
          default: []
        }
      }
    }
</script>

<style scoped>

</style>
