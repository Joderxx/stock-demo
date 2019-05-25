<template>
  <div class="body-container">
    <el-row>
      <el-col :span="4"
              v-for="(item, index) in indexData"
              :key="index"
              :offset="index > 0 ? 1 : 0">
        <el-card :body-style="{ padding: '0px' }">
          <div class="name" :style="'color: '+item[5]">
            <strong>
              {{item[2]}}
            </strong>
            <i class="fa fa-arrow-up" v-if="item[5]=='red'"></i>
            <i class="fa fa-arrow-down" v-else-if="item[5]=='green'"></i>
          </div>
          <div style="height: 3em">
            <div class="data" :style="'color: '+item[5]">
              {{item[3]}}
            </div>
            <div class="desc" :style="'color: '+item[5]">
              {{item[4]}}
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <subscribe />
  </div>
</template>

<script>
import {getIndexData} from "@/api/stock";
import Subscribe from "@/views/dashboard/subscribe";

export default {
  components: {Subscribe},
  data() {
    return {
      timer: '',
      indexData: [],

    }
  },
  created() {
    this.init()
    this.setTimer()
  },
  distroyed: function () {
    clearInterval(this.timer)
  },
  methods: {
    async init() {
      let data = await getIndexData()
      if (data&&data.data.data){
        this.indexData = data.data.data
        this.indexData.forEach((item,index) => {
          this.indexData[index].push(this.color(item[4]))
        })
      }
    },
    setTimer(){
      this.timer = setInterval(()=>{
        this.init()
      },30000)
    },
    color(val){
      val = val.substr(0,val.length-1)
      val = parseFloat(val)
      return val>0? 'red': val<0? 'green':'gray'
    },

  },
  computed: {

  }
}
</script>
<style>
  .name {
    height: 3em;
    text-align: center;
    padding-top: 1em;
    font-size: 2em;
  }

  .data {
    height: 2em;
    width:60%;
    text-align: center;
    display: inline-table;
    font-size: 1.5em;
  }

  .desc{
    height: 2em;
    text-align: center;
    display: inline-table;
    font-size: 1.2em
  }
</style>
