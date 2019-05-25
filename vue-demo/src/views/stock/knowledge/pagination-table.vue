<template>
  <div>
    <div style="float: right">
      <el-pagination
        @size-change="sizeChange"
        @current-change="currentChange"
        @prev-click="currentChange"
        @next-click="currentChange"
        :current-page="currentPage"
        :page-sizes="[5, 10, 20, 50, 100, 200]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="tableData?tableData.length:0">
      </el-pagination>
    </div>
    <div style="width: 100%">
      <el-table :data="filterData" @expand-change="expandRow">
        <el-table-column type="expand" v-if="$store.getters.roles.includes('admin')">
          <template slot-scope="scope">
            <el-form v-model="scope.row" inline>
              <el-form-item  label="名称">
                <el-input size="mini" v-model="scope.row.name" >
                </el-input>
              </el-form-item>
              <el-form-item  label="描述" >
                <el-input
                  type="textarea"
                  style="width: 30em"
                  autosize
                  placeholder="请输入内容"
                  v-model="scope.row.description">
                </el-input>
              </el-form-item>
              <el-button type="primary" size="mini" @click="query(scope.row)">完成</el-button>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column prop="name" :label="$t('general.name')" align="center" >

        </el-table-column>

        <el-table-column prop="description" label="描述" align="center">

        </el-table-column>

        <el-table-column label="操作"
                         align="center"
                         v-if="$store.getters.roles.includes('admin')">
          <template slot-scope="scope">
            <el-button type="danger" size="mini" @click="del(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
  import {updateKnow,delKnow} from "@/api/stock";

  export default {
    name: "pagination-table",
    props: {
      tableData: {
        type: Array,
        required: true
      }
    },
    data() {
      return {
        rowArray: [],
        currentPage: 1,
        pageSize: 5
      }
    },
    methods: {
      handleSizeChange(val){

      },
      handleCurrentChange(val) {

      },
      async query(row){
        let i = this.rowArray.indexOf(row)
        this.rowArray.splice(i,1)
        await updateKnow(row)
        this.$message({
          message: '修改完成',
          type: 'success'
        })
      },

      async del(row) {
        if (row.id){
          await delKnow(row.id)
        }
        let start = (this.currentPage-1)*this.pageSize
        let end = this.currentPage*this.pageSize
        start = start>0?start:0
        end = end<this.tableData.length?end:this.tableData.length
        end -= 1
        if (start>=end){
          this.currentPage -= 1
        }
        this.$emit("delete",row)
        this.$message({
          message: '删除完成',
          type: 'success'
        })
      },
      expandRow(val,rowArray){
        this.rowArray = rowArray
      },

      sizeChange(val) {
        this.pageSize = val
      },
      currentChange(val) {
        this.currentPage = val
      }
    },
    computed: {
      filterData() {
        let start = (this.currentPage-1)*this.pageSize
        let end = this.currentPage*this.pageSize
        start = start>0?start:0
        end = end<this.tableData.length?end:this.tableData.length
        return this.tableData.slice(start,end)
      }
    }
  }
</script>

<style scoped>

</style>
