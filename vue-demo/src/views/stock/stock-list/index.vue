<template>
    <div class="body-container">
      <el-form v-model="formData">
        <el-form-item :label="$t('stock.list.name')">
          <search-stock @init-finish="init" @change="change"/>
        </el-form-item>
        <el-form-item :label="$t('stock.list.property')">
          <check-property @change="selectProperty"/>
        </el-form-item>
      </el-form>
      <show-hide-table :items="[$t('general.resultList')]">
        <el-table
          :slot="$t('general.resultList')"
          v-loading="loading"
          :data="filterMap"
          style="width: 100%">
          <el-table-column :sortable="true"
            v-for="(item,index) in columns"
            :key="index"
            :prop="fields[item]+''"
            :label="$t('stock.list.'+item)"
            align="center">
          </el-table-column>
        </el-table>
      </show-hide-table>

    </div>
</template>

<script>
  import SearchStock from "@/views/stock/component/search-stock";
  import {generateTitle} from "@/utils/i18n";
  import ShowHideTable from "@/views/component/show-hide-table";
  import CheckProperty from "@/views/stock/stock-list/check-property";

  export default {
    name: "index",
    components: {CheckProperty, ShowHideTable, SearchStock},
    data() {
      return {
        formData: {},
        selectStock: '',
        tableData: [],
        fields: [],
        loading: false,
        columns: ['ts_code','name']
      }
    },
    created() {
      this.loading = true
    },
    methods: {
      init() {
        this.fields = this.$store.getters.stockList.fields
        let m = {}
        this.fields.forEach((item,index)=>{
          m[item] = index
        })
        this.fields = m
        this.tableData = this.$store.getters.stockList.items
        this.loading = false
      },
      generateTitle,
      change(val){
        this.selectStock=val
      },
      selectProperty(val){
        this.columns = val
      }
    },
    computed: {
      filterMap() {
        return this.tableData.filter(
          data => !this.selectStock
            || data[this.fields['ts_code']].toLowerCase()==this.selectStock.toLowerCase()
        )
      }
    }
  }
</script>

<style scoped>

</style>
