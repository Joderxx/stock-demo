<template>
  <el-select v-model="value" clearable @change="change"
             :placeholder="generateTitle('user.user-list.please')">
    <el-option v-for="role in roles" :key="role.id"
      :label="role.name+' ('+role.description+')'" :value="role.id">
      <span :style="'float: left;'+(role.name.toLowerCase()=='admin'?'color: red':'')">
        {{ role.name }}
      </span>
      <span style="float: right; color: #8492a6; font-size: 13px">
        {{ role.description }}
      </span>
    </el-option>
  </el-select>
</template>

<script>
  import {getRoles} from "@/api/role";
  import {generateTitle} from "@/utils/i18n";

  export default {
    name: "role-select",
    props: {
      rowData: {
        type: Object,
        required: true
      },
      role: {
      }
    },
    data() {
      return {
        roles: [],
        value: this.role==0?'':this.role
      }
    },
    created() {
      if (this.$store.getters.roleList.length==0){
        this.init()
      }
      this.roles = this.$store.getters.roleList
    },
    methods: {
      async init(){
        let data = await getRoles()
        if (data){
          this.roles = data.data.data
          this.$store.commit('SET_ROLE_LIST',data.data.data)
        }
      },
      generateTitle,
      change() {
        this.$emit('selectValue',this.value,this.rowData)
      }
    }
  }
</script>

<style scoped>

</style>
