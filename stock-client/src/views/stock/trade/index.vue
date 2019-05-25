<template>
  <div class="body-container">
    <el-form v-model="queryData" :inline="true">
      <el-form-item :label="$t('stock.list.name')">
        <search-stock @change="changeStock"/>
      </el-form-item>
      <el-form-item :label="$t('general.date')">
        <date-select @change="changeDate" />
      </el-form-item>
      <el-form-item :label="$t('stock.trade.type')">
        <el-radio-group v-model="queryData.api" size="mini" @change="changeApi">
          <el-radio-button label="daily">{{$t('stock.trade.daily')}}</el-radio-button>
          <el-radio-button label="weekly">{{$t('stock.trade.weekly')}}</el-radio-button>
          <el-radio-button label="monthly">{{$t('stock.trade.monthly')}}</el-radio-button>
        </el-radio-group>
      </el-form-item>
      <el-form-item>
        <sub-button ref="sub" />
      </el-form-item>

    </el-form>
    <stock-line ref="kLine"
           :slot="$t('stock.trade.daily')"
           :query-data="queryData" />
  </div>
</template>

<script>
  import SearchStock from "@/views/stock/component/search-stock";
  import SelectTab from "@/views/component/select-tab";
  import StockLine from "@/views/stock/component/stock-line";
  import DateSelect from "@/views/stock/component/date-select";
  import Vue from 'vue';
  import SubButton from "@/views/component/sub-button";
  export default {
    name: "index",
    components: {SubButton, StockLine, DateSelect, SelectTab, SearchStock},
    data() {
      return {
        queryData: {
          api: 'daily'
        }
      }
    },
    methods: {
      changeStock(val){
        Vue.set(this.queryData,'ts_code',val)
        this.$refs.kLine.init()
        this.$refs.sub.change(val)
      },
      changeDate(val){
        Vue.set(this.queryData,'start_date',val[0])
        Vue.set(this.queryData,'end_date',val[1])
        this.$refs.kLine.init()
      },
      changeApi(val){
        Vue.set(this.queryData,'api',val)
        this.$refs.kLine.init()
      }
    }
  }
</script>

<style scoped>

</style>
