<template>
    <div>
      <el-checkbox
        :indeterminate="isIndeterminate"
        v-model="checkAll"
        @change="handleCheckAllChange">
        {{$t('general.all')}}
      </el-checkbox>
      <el-checkbox
        v-model="isShow">
        {{$t('general.hide')}}
      </el-checkbox>
      <el-checkbox-group v-if="!isShow"
        v-model="checkedProperty"
        @change="handleCheckedPrppertiesChange">
        <el-checkbox
          v-for="(property,index) in properties"
          :label="property" style="display:inline-table;"
          :key="property" >
          {{ $t('stock.list.'+property) }}

        </el-checkbox>
      </el-checkbox-group>
    </div>
</template>

<script>
  export default {
    name: "check-property",
    data() {
      return {
        isShow: true,
        checkAll: false,
        checkedProperty: [
          'ts_code','symbol',
          'name','industry',
          'market','area',

        ],
        properties: [
          'ts_code','symbol',
          'name','area',
          'industry','market',
          'fullname', 'enname',
          'exchange','curr_type',
          'list_status','list_date',
          'delist_date','is_hs'
          ],
        isIndeterminate: true
      }
    },
    created() {
      this.$emit('change',this.checkedProperty)
    },
    methods: {
      handleCheckAllChange(val) {
        this.checkedProperty = val ? this.properties : [];
        this.isIndeterminate = false;
        this.$emit('change',this.checkedProperty)
      },
      handleCheckedPrppertiesChange(value) {
        let checkedCount = value.length;
        this.checkAll = checkedCount === this.properties.length;
        this.isIndeterminate = checkedCount > 0 && checkedCount < this.properties.length;
        this.$emit('change',this.checkedProperty)
      },
      title(name){
        this.$t('stock.list.'+name)
      },

    },
    computed: {
      t(name) {
        this.title(name)
      }
    }
  }
</script>

<style scoped>

</style>
