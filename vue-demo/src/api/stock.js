import request from '@/utils/request'

export function stockList() {
  return request({
    url: '/stock/list',
    method: 'get'
  })
}

export function getStockDaily(data) {
  return request({
    url: '/stock/daily',
    method: 'get',
    params: data
  })
}

export function getIndexData() {
  return request({
    url: '/stock/index_data',
    method: 'get'
  })
}

export function getStockReal(data) {
  return request({
    url: '/stock/real',
    method: 'get',
    params: data
  })
}

export function getKnow() {
  return request({
    url: '/knowledge/get',
    method: 'get',
  })
}

export function updateKnow(data) {
  return request({
    url: '/knowledge/update',
    method: 'post',
    headers: {
      'Content-Type': 'application/json',
    },
    data: data
  })
}

export function delKnow(id) {
  return request({
    url: '/knowledge/delete',
    method: 'delete',
    params: {id: id}
  })
}

export function getSub() {
  return request({
    url: '/subscribe/list',
    method: 'get'
  })
}

export function addSub(tsCode) {
  return request({
    url: '/subscribe/add',
    method: 'post',
    params: {
      "ts_code": tsCode
    }
  })
}

export function delSub(tsCode) {
  return request({
    url: '/subscribe/delete',
    method: 'delete',
    params: {
      "ts_code": tsCode
    }
  })
}

