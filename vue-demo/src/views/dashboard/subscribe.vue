<template>
  <div class="sub">
    <div v-if="!show" @click="show=!show" class="plus">
      <i class="el-icon-plus" style="margin-top: 20%"></i>
    </div>
    <el-card v-if="show">
      <template slot="header">
        <span>{{$t('dashboard.subscribe.name')}}</span>
        <i class="el-icon-minus"
           style="float: right" @click="show=!show"></i>
      </template>
      <el-table :data="subscribeData"
                size="mini"
                max-height="250">
        <el-table-column :label="$t('dashboard.subscribe.stockName')">
          <template slot-scope="scope" >
            <router-link :to="'/stock/trade?ts_code='+scope.row['ts_code']">
                <span :style="'color: '+color(scope.row)">
                  {{scope.row.name}}
                </span>
            </router-link>
          </template>
        </el-table-column>
        <el-table-column :label="$t('dashboard.subscribe.current')">
          <template slot-scope="scope" >
                <span :style="'color: '+color(scope.row)">
                  {{scope.row.real}}
                </span>
          </template>
        </el-table-column>
        <el-table-column
          :label="$t('dashboard.subscribe.rate')"
          sortable
          :sort-method="compareTo"
          :sort-orders="['descending','ascending', null]">
          <template slot-scope="scope">
                <span :style="'color: '+color(scope.row)">
                  {{rate(scope.row)}}%
                </span>
          </template>
        </el-table-column>
        <el-table-column width="40">
          <template slot-scope="scope">
                <span :style="'color: '+color(scope.row)">
                  <i class="fa fa-arrow-up" v-if="rate(scope.row)>0"></i>
                  <i class="fa fa-arrow-down" v-else-if="rate(scope.row)<0"></i>
                </span>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
  import {getStockReal,getSub} from "@/api/stock";

  export default {
    name: "subscribe",
    data() {
      return {
        timer: '',
        show: true,
        sub: [],
        subscribeData: []
      }
    },
    created() {
      clearInterval(this.timer)
      this.init()
    },
    destroyed() {
      clearInterval(this.timer)
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
        this.sub = this.$store.getters.subscribeList
        this.initData()
        this.timer = setInterval(this.initData,29*1000)

      },
      async initData() {
        if (! this.sub||this.sub.length==0){
          return ;
        }
        let data = await getStockReal({
          'ts_code': this.toString(this.sub)
        })
        if (data&&data.data.data){
          this.subscribeData = data.data.data
        }
      },
      toString(arr){
        if (arr.length<=1){
          return arr[0]
        }
        let s = ''
        arr.forEach(item => {
          s += ','+item
        })
        return s.substring(1)
      },
      rate(row){
        let val1 = row.real
        let val2 = row['pre_close']
        return (((val1-val2)*100)/val2).toFixed(2)
      },
      color(row) {
        let c = 'red'
        if (row['pre_close']>row.real){
          c = 'green'
        } else if (row['pre_close']==row.real) {
          c = 'gray'
        }
        return c
      },
      compareTo(a,b){
        return this.rate(a)-this.rate(b)
      }
    }
  }
</script>

<style scoped>
  .sub {
    margin-top: 1%;
    position: fixed;
    margin-left: 57%;
  }

  .plus{
    position: fixed;
    margin-left: 22%;
    width: 2em;
    height: 2em;
    /*background-color: #0a76a4;*/
    border: 1px solid #8c939d;
    text-align: center;
  }
</style>
