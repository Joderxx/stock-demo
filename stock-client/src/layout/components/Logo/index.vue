<template>
  <div :style="'background-color: '+color">
    <div class="logo">
      <img src="/favicon.ico" width="45" height="45">
    </div>
    <div class="title" v-if="show">
      <span class="font">{{$t('logo')}}</span>
    </div>
  </div>
</template>

<script>
    export default {
      data() {
        return {

        }
      },
      computed: {
        color() {
          return this.$store.getters.color
        },
        show() {
          return this.$store.getters.sidebar.opened
        }
      }
    }
</script>

<style scoped>
  .font {
    color: white;
    font-size: 23px;
    margin-top: 10px;
  }
  .logo {
    display: inline-table;
    width: 30%;
    text-align: center
  }
  .title {
    display: inline-table;
    float: right;
    width: 70%;
    text-align: center;
    margin-top: 5%
  }
</style>
