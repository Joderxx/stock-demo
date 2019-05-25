<template>
  <div>
    <el-row>
    </el-row>
    <el-row>
      <el-col :span="24" align="center">
        <div id="main-chart" style="height: 30em;width: 90%">
        </div>
      </el-col>
    </el-row>
  </div>


</template>

<script>
  import echarts from 'echarts';
  import {getStockDaily} from "@/api/stock";
  import Vue from 'vue';
  export default {
    name: "stock-line",
    props: {
      queryData: {
        type: Object,
        required: true
      }
    },
    data() {
      return {
        fields: [],
        rawData: [],
        dates: [],
        data: [],
        option: {},
        charts: {}
      }
    },
    created() {
      let tsCode = this.$route.query['ts_code']
      if (typeof(tsCode)=='string'){
        Vue.set(this.queryData,'ts_code',tsCode)
        this.init()
      }
    },
    methods: {
      async init() {
        if (!this.queryData['ts_code']){
          return ;
        }
        let data = await getStockDaily(this.queryData)
        if (data&&data.data.data){
          this.fields = data.data.data.fields
          this.rawData = data.data.data.items.reverse()
        }
        data = {}
        this.fields.forEach((item,i) => {
          data[item] = i
        })
        this.fields = data
        this.dates = this.rawData.map((item) => {
          return item[this.fields['trade_date']];
        });
        this.data = this.rawData.map((item) => {
          return [
            +item[this.fields['open']],
            +item[this.fields['close']],
            +item[this.fields['high']],
            +item[this.fields['low']],
            +item[this.fields['pre_close']]
          ];
        });
        this.close = this.rawData.map((item) => {
          return [+item[2]];
        });
        this.option = {
          backgroundColor: '#21202D',
          legend: {
            data: [this.$t('stock.trade.'+this.queryData.api), 'MA5', 'MA10', 'MA20', 'MA30', 'MA120'],
            inactiveColor: '#777',
            textStyle: {
              color: '#fff'
            }
          },
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              animation: false,
              type: 'cross',
              lineStyle: {
                color: '#376df4',
                width: 2,
                opacity: 1
              }
            },
            formatter: function (params) {
              let res = params[0].seriesName + ' ' + params[0].name;
              let color = 'red'
              if (params[0].value[2]<params[0].value[5]){
                color = 'green'
              }else if (params[0].value[2]==params[0].value[5]){
                color = 'gray'
              }
              let pre = (((params[0].value[2]-params[0].value[5])*100)/(params[0].value[5])).toFixed(2)
              res +=  '<br/>  <span style="color: yellow;">开盘 : </span>' +
                '<span style="color: '+color+';">'+params[0].value[1] + '</span>'+
                '<br/>  <span style="color: yellow">最高 : </span>' +
                '<span style="color: '+color+';">'+params[0].value[4]+ '</span>';
              res +=  '<br/>  <span style="color: yellow">收盘 : </span>' +
                '<span style="color: '+color+';">'+params[0].value[2] + '</span>'+
                '<br/>  <span style="color: yellow">最低 : </span>' +
                '<span style="color: '+color+';">'+params[0].value[3] + '</span>'+
                '<br/>  <span style="color: yellow">涨幅 : </span>' +
                '<span style="color: '+color+';">'+pre + '%</span>';
              for (let i=1;i<params.length;i++){
                res += '<br/> <span style="color: yellow">'+params[i].seriesName+':</span> '+params[i].value
              }

              return res;
            }
          },
          xAxis: {
            type: 'category',
            data: this.dates,
            axisLine: { lineStyle: { color: '#8392A5' } }
          },
          yAxis: {
            scale: true,
            axisLine: { lineStyle: { color: '#8392A5' } },
            splitLine: { show: false }
          },
          grid: {
            bottom: 80
          },
          dataZoom: [
            {
              textStyle: {
                color: '#8392A5'
              },
              handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
              handleSize: '80%',
              dataBackground: {
                areaStyle: {
                  color: '#8392A5'
                },
                lineStyle: {
                  opacity: 0.8,
                  color: '#8392A5'
                }
              },
              handleStyle: {
                color: '#fff',
                shadowBlur: 3,
                shadowColor: 'rgba(0, 0, 0, 0.6)',
                shadowOffsetX: 2,
                shadowOffsetY: 2
              }
            },
            {
              type: 'inside'
            }
          ],
          animation: false,
          series: [
            {
              type: 'candlestick',
              name: this.$t('stock.trade.'+this.queryData.api),
              data: this.data,
              itemStyle: {
                normal: {
                  color: '#FD1050',
                  color0: '#0CF49B',
                  borderColor: '#FD1050',
                  borderColor0: '#0CF49B'
                }
              }
            },
            {
              name: 'MA5',
              type: 'line',
              data: this.calculateMA(5, this.data),
              smooth: true,
              showSymbol: false,
              lineStyle: {
                normal: {
                  width: 1
                }
              }
            },
            {
              name: 'MA10',
              type: 'line',
              data: this.calculateMA(10, this.data),
              smooth: true,
              showSymbol: false,
              lineStyle: {
                normal: {
                  width: 1
                }
              }
            },
            {
              name: 'MA20',
              type: 'line',
              data: this.calculateMA(20, this.data),
              smooth: true,
              showSymbol: false,
              lineStyle: {
                normal: {
                  width: 1
                }
              }
            },
            {
              name: 'MA30',
              type: 'line',
              data: this.calculateMA(30, this.data),
              smooth: true,
              showSymbol: false,
              lineStyle: {
                normal: {
                  width: 1
                }
              }
            },
            {
              name: 'MA120',
              type: 'line',
              data: this.calculateMA(120, this.data),
              smooth: true,
              showSymbol: false,
              lineStyle: {
                normal: {
                  width: 1
                }
              }
            }
          ]
        }
        this.$nextTick(function() {
          this.draw('main-chart')
        })
      },
      calculateMA(dayCount, data) {
        let result = [];
        for (let i = 0, len = data.length; i < len; i++) {
          if (i < dayCount) {
            result.push('-');
            continue;
          }
          let sum = 0;
          for (let j = 0; j < dayCount; j++) {
            sum += data[i - j][1];
          }
          result.push((sum / dayCount).toFixed(2));
        }
        return result;
      },
      draw(name) {
        this.charts = echarts.init(document.getElementById(name))
        this.charts.setOption(this.option, true);
      }
    }
  }
</script>

<style scoped>

</style>
