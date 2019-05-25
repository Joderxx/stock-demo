<template>
  <el-switch
    v-model="active"
    @change="changeActive"
    active-text="订阅">
  </el-switch>
</template>

<script>
  import {getSub,addSub,delSub} from "@/api/stock";
  import Vue from 'vue';
  export default {
    name: "sub-button",
    data() {
      return {
        active: false,
        tsCode: ''
      }
    },
    created() {
      this.init()
    },
    methods: {
      async init() {
        if (!this.$store.getters.subscribeList
          ||this.$store.getters.subscribeList.length==0){
          let data = await getSub()
          if (data&&data.data.data){
            this.$store.commit('SET_SUB',data.data.data)
          }
        }
        let tsCode = this.$route.query['ts_code']
        if (typeof(tsCode)=='string'){
          this.tsCode = tsCode
          this.active = this.$store.getters.subscribeList.indexOf(tsCode)!=-1
        }
      },
      change(val){
        if (typeof (val)=='string'){
          this.tsCode = val
          this.active = this.$store.getters.subscribeList.indexOf(val)!=-1
        }
      },
      async changeActive(val){
        if (typeof (this.tsCode)!='string'||!this.tsCode){
          return;
        }
        if (val){
          await addSub(this.tsCode)
          this.$store.commit('ADD_SUB',this.tsCode)
        } else {
          await delSub(this.tsCode)
          this.$store.commit('POP_SUB',this.tsCode)
        }
      }
    }
  }
</script>

<style scoped>

</style>
