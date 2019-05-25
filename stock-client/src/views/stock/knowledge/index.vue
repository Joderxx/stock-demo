<template>
    <div class="body-container">
      <el-form inline :v-model="formData">
        <el-form-item :label="$t('general.name')">
          <el-input v-model="formData.name" ></el-input>
        </el-form-item>
        <el-button v-if="$store.getters.roles.includes('admin')"
          type="primary"
          size="mini"
          @click="add">添加</el-button>
      </el-form>
      <pagination-table
        :table-data="filterData"
        @delete="del" />
    </div>
</template>

<script>
  import PaginationTable from "@/views/stock/knowledge/pagination-table";
  import {getKnow,getSub} from "@/api/stock";

  export default {
    name: "index",
    components: {PaginationTable},
    data() {
      return{
        formData: {
        },
        tableData: []
      }
    },
    created() {
      this.init()
    },
    computed: {
      filterData(){
        return this.tableData.filter(
          data => !this.formData.name
          || data.name.toLowerCase().includes(this.formData.name.toLowerCase())
        )
      }
    },
    methods: {
      async init(){
        if (!this.$store.getters.subscribeList
          ||this.$store.getters.subscribeList.length==0){
          let data = await getSub()
          if (data&&data.data.data){
            this.$store.commit('SET_SUB',data.data.data)
          }
        }
        let data = await getKnow()
        if (data&&data.data.data){
          this.tableData = data.data.data
        }
      },
      del(row) {
        let i = this.tableData.indexOf(row)
        if (i!=-1){
          this.tableData.splice(i,1)
        }
      },
      add() {
        this.tableData.splice(0,0,{})
      }
    }
  }
</script>

<style scoped>

</style>
