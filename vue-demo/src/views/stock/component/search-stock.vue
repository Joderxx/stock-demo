<template>
  <el-select v-model="value" clearable
             @change="change"
             filterable :filter-method="filterMethod"
             :placeholder="$t('user.user-list.please')">
    <el-option
      v-for="item in stockList"
      :key="item[tsCode]"
      :label="item[name]+' ('+item[symbol]+')'"
      :value="item[tsCode]">
    </el-option>
  </el-select>
</template>

<script>
  import {deepClone} from "@/utils";
  import {stockList} from "@/api/stock";

  export default {
    name: "search-stock",
    data() {
      return {
        stockList: [],
        origin: [],
        columns: [],
        symbol: -1,
        tsCode: -1,
        name: -1,
        value: ''
      }
    },
    created() {
      this.init()
    },
    methods: {
      async init() {
        let d = this.$store.getters.stockList
        if (d==null||typeof(d)!='object'){
          let data = await stockList()
          if (data){
            this.$store.commit('SET_LIST',data.data.data)

          }
        }
        this.columns = this.$store.getters.stockList.fields
        this.stockList = this.$store.getters.stockList.items
        this.origin = this.deepClone([...this.stockList])
        this.name = this.columns.indexOf('name')
        this.symbol = this.columns.indexOf('symbol')
        this.tsCode = this.columns.indexOf('ts_code')
        this.$emit('init-finish')
        let tsCode = this.$route.query['ts_code']
        if (typeof(tsCode)=='string'){
          this.value = tsCode
        }
      },
      filterMethod(val) {
        if (val){
          this.stockList = this.origin.filter(data =>{
            if(data[this.symbol].toLowerCase().includes(val.toLowerCase())){
              return true
            }else if (data[this.name].toLowerCase().includes(val.toLowerCase())) {
              return true
            }else {
              return false
            }
          })
        }
      },
      deepClone,
      change(val){
        this.$emit('change',val)
      }
    }
  }
</script>

<style scoped>

</style>
