<template>
  <div style="margin: 1em 1em">
    <el-col :span="5" :offset="18">
      <el-input v-model="search" :placeholder="generateTitle('user.user-list.search')">
        <template slot="append">
          <i class="el-icon-search"></i>
        </template>
      </el-input>
    </el-col>
    <el-table :data="getData" v-loading="isLoad"
      style="width: 100%">
      <el-table-column prop="userId" :label="generateTitle('user.user-list.id')"
                       width="180" align="center" :sortable="true">
      </el-table-column>
      <el-table-column prop="username" :label="generateTitle('user.user-list.username')"
                       width="180" align="center">
      </el-table-column>

      <el-table-column prop="role1" :label="generateTitle('user.user-list.role1')"
                       align="center">
        <template slot-scope="scope">
          <template v-if="scope.row.show">
            <role-select :row-data="scope.row" :role="scope.row.role1"
                         @selectValue="selectRole1"/>
          </template>
          <span v-else>{{ getName(scope.row.role1) }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="role2" :label="generateTitle('user.user-list.role2')"
                       align="center">
        <template slot-scope="scope">
          <template v-if="scope.row.show">
            <role-select :row-data="scope.row" :role="scope.row.role2"
                         @selectValue="selectRole2"/>
          </template>
          <span v-else>{{ getName(scope.row.role2) }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="role3" :label="generateTitle('user.user-list.role3')"
                       align="center">
        <template slot-scope="scope">
          <template v-if="scope.row.show">
            <role-select :row-data="scope.row" :role="scope.row.role3"
                         @selectValue="selectRole3"/>
          </template>
          <span v-else>{{ getName(scope.row.role3) }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="generateTitle('user.user-list.operator')" align="center">
        <template slot-scope="scope">
          <el-button v-if="scope.row.show==true" type="success" size="mini"
                     @click="finishEdit(scope.$index, scope.row)">
            {{generateTitle("button.finish")}}
          </el-button>
          <el-button v-else type="primary"
            size="mini"
            @click="handleEdit(scope.$index, scope.row)">
            {{generateTitle("button.edit")}}
          </el-button>

        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  import {getRoles} from "@/api/role";
  import {generateTitle} from "@/utils/i18n";
  import Vue from "vue"
  import RoleSelect from "@/views/user/component/role-select";
  import {getUserList,updateUser} from "@/api/user";

  export default {
    components: {RoleSelect},
    data() {
      return {
        isLoad: false,
        roles: [],
        roleMap: {},
        tableData: [
          /*{
            userId: 1,
            username: 'abc',
            role1: 1
          },
          {
            userId: 2,
            username: 'wwd',
            role1: 1,
            role2: 2
          },*/
        ],
        search: ''
      }
    },
    created() {
      this.isLoad = true
      this.init()
      this.isLoad = false
    },
    methods: {
      async init(){
        if (this.$store.getters.roleList.length==0) {
          let data = await getRoles()
          if (data){
            this.roles = data.data.data
            this.initMap()
            this.$store.commit('SET_ROLE_LIST',data.data.data)
          }
        }
        this.roles = this.$store.getters.roleList
        this.initMap()
        let data = await getUserList()
        if (data&&data.data.data){
          this.tableData = data.data.data
        }
      },
      initMap() {
        this.roles.forEach(item=>{
          Vue.set(this.roleMap,item.id,item)
        })
      },
      getName(id) {
        let role = this.roleMap[id]
        if (!role){
          return '';
        }
        return role.name
      },
      handleEdit(index, row) {
        Vue.set(row,'show',true)
      },
      async finishEdit(index, row) {
        Vue.set(row,'show',false)
        if (!row.role1&&!row.role2&&!row.role3) {
          Vue.set(row,'role1',3)
        }
        await updateUser(row)
      },
      generateTitle,
      selectRole1(value,row) {
        Vue.set(row,'role1',value)
      },
      selectRole2(value,row) {
        Vue.set(row,'role2',value)
      },
      selectRole3(value,row) {
        Vue.set(row,'role3',value)
      }
    },
    computed: {
      getData() {
        return this.tableData.filter(
          data => !this.search
            || data.username.toLowerCase().includes(this.search.toLowerCase())
        )
      }
    }
  }
</script>

<style scoped>

</style>
