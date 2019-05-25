import { deepClone,reFinishData } from '@/utils'
import { asyncRoutes, constantRoutes } from '@/router'


const routes = {
  state: {
    routeData: []
  },
  mutations: {
    INIT_ROUTE: (state) => {
      let m = deepClone([...constantRoutes, ...asyncRoutes])
      reFinishData(m,null,null,'')
      state.routeData = m
    },
    ADD_ROUTE: (state, route) => {
      state.routeData.push(route)
    },
    DEL_ROUTE: (state,route) => {
      let index = state.routeData.indexOf(route)
      if (index!=-1){
        state.routeData.splice(index,1)
      }
    }

  }

}

export default routes
