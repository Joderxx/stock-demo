import request from '@/utils/request'

export function updateUser(data) {
  return request({
    url: '/user/update',
    method: 'put',
    headers: {
      'Content-Type': 'application/json',
    },
    data: data
  })
}

export function getUserList() {
  return request({
    url: '/user/list',
    method: 'get',
  })
}

export function registerUser(data) {
  return request({
    url: '/register',
    method: 'post',
    params: data
  })
}

