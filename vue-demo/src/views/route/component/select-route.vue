<template>
  <div class="app-container">
    <tree-table
      ref="TreeTable"
      :key="key"
      :data="routeData"
      :columns="columns"
      border>
      <template slot="operation" slot-scope="{scope}">
        <el-button type="info" size="mini" @click="click(scope)">
          {{generateTitle('button.detail')}}
        </el-button>
      </template>
    </tree-table>
    <detail v-if="dialogVisible" :temp-item="tempItem" @closeDialog="dialogVisible=false"/>
  </div>
</template>

<script>
  import TreeTable from '@/components/TreeTable/index';
  import { generateTitle } from '@/utils/i18n';
  import Detail from "@/views/route/component/detail";
  export default {
    name: "select-route",
    components: {Detail, TreeTable },
    data() {
      return {
        dialogVisible:false,
        key: 1,
        tempItem: {},
        columns: [
          {
            label: this.generateTitle('route.select-route.select'),
            checkbox: true,
            width: 50
          },
          {
            label: this.generateTitle('route.select-route.expand'),
            expand: true,
            width: 50
          },
          {
            label: this.generateTitle('route.select-route.path'),
            key: 'path'
          },
          {
            label: this.generateTitle('route.select-route.name'),
            key: 'name'
          },
          {
            label: this.generateTitle('route.select-route.redirect'),
            key: 'redirect'
          },
          {
            label: this.generateTitle('route.select-route.i18n-name'),
            key: 'meta.title',
            method: item => this.generateTitle(item),
          },
          {
            label: this.generateTitle('route.select-route.permission'),
            key: 'meta.roles',
            method: item => item==null||item.length==0?this.generateTitle('general.none'):item.toString(),
          },
          {
            label: this.generateTitle('route.select-route.operate'),
            key: 'operation'
          }
        ],
      }
    },
    created() {
      this.$store.commit('INIT_ROUTE',this.$store.state)
    },
    computed: {
      routeData() {
        return this.$store.getters.routeData
      }
    },
    watch: {
      showCheckbox(val) {
        if (val) {
          this.columns.unshift({
            label: 'Checkbox',
            checkbox: true
          })
        } else {
          this.columns.shift()
        }
        this.reset()
      }
    },
    methods: {
      reset() {
        ++this.key
      },
      click(scope) {
        this.dialogVisible = true
        const row = scope.row
        this.tempItem = row
      },
      generateTitle
    }
  }
</script>

<style scoped lang="scss">
  .option-item{
    display: inline-block;
    margin-right: 15px;
  }
</style>
