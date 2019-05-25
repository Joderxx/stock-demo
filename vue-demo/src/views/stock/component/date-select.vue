<template>
  <div>
    <el-date-picker
      v-model="value"
      type="daterange"
      align="right"
      unlink-panels
      value-format="yyyyMMdd"
      :range-separator="$t('general.to')"
      :start-placeholder="$t('general.startDate')"
      :end-placeholder="$t('general.endDate')"
      :picker-options="pickerOptions" @change="change">
    </el-date-picker>
  </div>
</template>

<script>
  export default {
    name: "date-select",
    data() {
      return {
        value: [],
        pickerOptions: {
          shortcuts: [{
            text: this.$t('general.recentWeek'),
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: this.$t('general.recentMonth'),
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: this.$t('general.recent3Month'),
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
            }
          },
            {
              text: this.$t('general.recentYear'),
              onClick(picker) {
                const end = new Date();
                const start = new Date();
                start.setTime(start.getTime() - 3600 * 1000 * 24 * 365);
                picker.$emit('pick', [start, end]);
              }
            }
          ]
        },
      }
    },
    methods:{
      change(val) {
        this.$emit("change",val)
      }
    }
  }
</script>

<style scoped>

</style>
