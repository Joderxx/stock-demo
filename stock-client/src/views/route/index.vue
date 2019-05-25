<template>
  <div class="body-container">
    <select-route />
  </div>
</template>

<script>
    import ShowHideTable from "@/views/component/show-hide-table";
    import SelectRoute from "@/views/route/component/select-route";
    import { generateTitle } from '@/utils/i18n';
    export default {
      components: {SelectRoute, ShowHideTable},
      data(){
        return{

        }
      },
      methods: {
        t: generateTitle
      }
    }
</script>

<style scoped>

</style>
