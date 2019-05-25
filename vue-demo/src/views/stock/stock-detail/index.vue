<template>
    <div class="body-container">
      <div class="value" v-if="showValue&&stockReal.name">
        <el-card :body-style="{ padding: '0px' }">
          <div style="float: right">
            <i class="el-icon-close" @click="showValue = false"></i>
          </div>
          <div class="name" :style="'color: '+color">
            <strong>
              {{stockReal.name}}
            </strong>
          </div>
          <div style="height: 3em" :style="'color: '+color">
            <div class="data" >
              {{stockReal.real}}
            </div>
            <div class="desc" :style="'color: '+color">
              {{rate}}%
            </div>
          </div>
        </el-card>
      </div>
      <div>
        <span style="padding-right: 1em">{{$t('stock.list.name')}}</span>
        <search-stock @change="change" />
        <sub-button ref="sub" />
      </div>
      <div>

      </div>
    </div>
</template>

<script>
  import SearchStock from "@/views/stock/component/search-stock";
  import {getStockReal,getSub} from "@/api/stock";
  import SubButton from "@/views/component/sub-button";
  export default {
    name: "index",
    components: {SubButton, SearchStock},
    data() {
      return {
        timer: '',
        stock: '',
        stockReal: {},
        showValue: true
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
      },
      change(val) {
        this.$refs.sub.change(val)
        this.stock = val
        if (val){
          this.getData(val)
          clearInterval(this.timer)
          this.timer = setInterval(()=>this.getData(val),30000)
          this.showValue = true
        }
      },
      async getData(val){
        let p = {
          'ts_code': val
        }
        let data = await getStockReal(p)
        if (data&&data.data.data){
          this.stockReal = data.data.data[0]
        }
      }
    },
    computed: {
      rate(){
        let val1 = this.stockReal.real
        let val2 = this.stockReal['pre_close']
        return (((val1-val2)*100)/val2).toFixed(2)
      },
      color() {
        let c = 'red'
        if (this.stockReal['pre_close']>this.stockReal.real){
          c = 'green'
        } else if (this.stockReal['pre_close']==this.stockReal.real) {
          c = 'gray'
        }
        return c
      }
    }
  }
</script>

<style scoped>
  .name {
    height: 2.7em;
    text-align: center;
    padding-top: 1em;
    font-size: 2em;
  }

  .data {
    height: 2em;
    width:57%;
    text-align: center;
    display: inline-table;
    font-size: 1.5em;
  }

  .desc{
    height: 2em;
    text-align: center;
    display: inline-table;
    font-size: 1.2em
  }

  .value {
    position:fixed;
    width: 14%;
    margin-left: 67%
  }
</style>
