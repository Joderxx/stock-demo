<template>
  <el-scrollbar wrap-class="scrollbar-wrapper">
    <el-menu
      :default-active="$route.path"
      :collapse="isCollapse"
      :background-color="color"
      :text-color="variables.menuText"
      :active-text-color="variables.menuActiveText"
      :collapse-transition="false"
      mode="vertical"
      class="menu-class"
    >
      <sidebar-item
        v-for="route in permission_routes"
        :key="route.path"
        :item="route"
        :base-path="route.path" />
    </el-menu>
  </el-scrollbar>
</template>

<script>
import { mapGetters } from 'vuex'
import SidebarItem from './SidebarItem'
import variables from '@/styles/variables.scss'

export default {
  components: { SidebarItem },
  computed: {
    ...mapGetters([
      'permission_routes',
      'sidebar'
    ]),
    color() {
      return this.$store.getters.color
    },
    reverseColor() {
      return this.colorReverse()
    },
    variables() {
      return variables
    },
    isCollapse() {
      return !this.sidebar.opened
    }
  },
  methods: {
    colorReverse(){
      let val = this.$store.getters.color
      val ="0x"+val.replace(/#/g,"");
      let str="000000"+(0xFFFFFF-val).toString(16);
      console.log(str)
      return str.substring(str.length-6,str.length);
    },


  }
}
</script>
<style>
  .menu-class:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
  }
</style>
