
const stock = {
  state: {
    stockList: null,
    subscribeList: []
  },

  mutations: {
    SET_LIST: (state,stockList) => {
      state.stockList = stockList
    },

    SET_SUB: (state,item) => {
      if (item){
        state.subscribeList = item
      }

    },
    ADD_SUB: (state,item) => {
      if (state.subscribeList.indexOf(item)==-1){
        state.subscribeList.push(item)
      }
    },
    POP_SUB: (state,item) => {
      let i = state.subscribeList.indexOf(item)
      if (i!=-1){
        state.subscribeList.splice(i,1)
      }
    }
  }
}

export default stock
