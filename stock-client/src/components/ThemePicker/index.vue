<template>
  <el-color-picker
    v-model="color"
    :predefine="predefine"
    class="theme-picker"
    popper-class="theme-picker-dropdown"
  >
  </el-color-picker>
</template>

<script>
  import {generateTitle} from "@/utils/i18n";

  export default {
    data() {
      return {
        color: this.$store.getters.color,
        predefine: ['#304156', '#11a983', '#13c2c2', '#6959CD', '#f5222d', '#eb2f96', '#DB7093', '#e6a23c', '#8B8989', '#212121'],
      }
    },
    watch: {
      color(newVal){
        this.$store.commit('SET_COLOR',newVal)
        this.old = newVal
        this.$message({
          message: this.generateTitle('theme.success'),
          type: 'success'
        })
      }
    },
    methods: {
      generateTitle
    }
  }
</script>
<style>
  .theme-picker .el-color-picker__trigger {
    margin-top: 12px;
    height: 26px!important;
    width: 26px!important;
    padding: 2px;
  }

  .theme-picker-dropdown .el-color-dropdown__link-btn {
    display: none;
  }
</style>

