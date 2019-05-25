<template>
  <el-dialog :visible.sync="show" title="Info" @close="close()"
             style="width: 80%;margin-left: 15%">
    <div>
      <el-row>
        <el-col :span="labelSpan" :offset="labelOffset">
          {{generateTitle('route.select-route.i18n-name')}}：
        </el-col>
        <el-col :span="valSpan">
          {{generateTitle(tempItem.meta==null?'':tempItem.meta.title)}}
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="labelSpan" :offset="labelOffset">
          {{generateTitle('route.select-route.path')}}：
        </el-col>
        <el-col :span="valSpan">
          {{tempItem.path}}
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="labelSpan" :offset="labelOffset">
          {{generateTitle('route.select-route.permission')}}：
        </el-col>
        <el-col :span="valSpan">
          {{tempItem.meta==null?'':tempItem.meta.roles}}
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="labelSpan" :offset="labelOffset">
          {{generateTitle('route.select-route.icon')}}：
        </el-col>
        <el-col :span="valSpan">
          <i :class="'fa fa-lg fa-'+(tempItem.meta==null?'':tempItem.meta.icon)"></i>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="labelSpan" :offset="labelOffset">
          {{generateTitle('route.select-route.noCache')}}：
        </el-col>
        <el-col :span="valSpan">{{tempItem.meta==null?false:tempItem.meta.noCache?true:false}}</el-col>
      </el-row>
    </div>
    <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="close()">
          {{this.generateTitle('table.confirm')}}
        </el-button>
      </span>
  </el-dialog>
</template>

<script>
  import {generateTitle} from "@/utils/i18n";

  export default {
    name: "detail",
    props: {
      tempItem: {
        type: Object,
        default: {}
      }
    },
    data() {
      return{
        show: true,
        labelOffset: 5,
        labelSpan: 6,
        valSpan: 9,
      }
    },
    methods: {
      generateTitle,
      close() {
        this.$emit('closeDialog')
      }
    }
  }
</script>

<style scoped lang="scss">
  .el-row {
    margin-bottom: 20px;
    font-size: 1.5em;
  &:last-child {
     margin-bottom: 0;
   }
  }
</style>
