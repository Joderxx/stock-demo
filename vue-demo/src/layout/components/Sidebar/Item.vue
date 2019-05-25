<template>
  <div>
    <div style="display: inline-table;width: 20%;min-width: 50px;">
      <i v-if="icon" :class="'fa fa-'+icon" style="font-size: 1.5em;margin-left: 20%"/>
    </div>
    <div style="display: inline-table;">
      <span v-if="title">{{title}}</span>
    </div>
  </div>
</template>
<script>
  import variables from '@/styles/variables.scss'
  export default {
    name: 'Item',
    props: {
      icon: {
        type: String,
        default: ''
      },
      title: {
        type: String,
        default: ''
      }
    },
    computed: {
      variables() {
        return variables
      },
    },
  }
</script>
<style>
  i .fa-lg {
    font-size: 1.33333333em;
    line-height: 0.75em;
    vertical-align: -15%;
  }
</style>
