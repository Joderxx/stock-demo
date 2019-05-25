import request from '@/utils/request'
import {removeToken} from "@/utils/auth";

export function loginByUsername(username, password) {
  const data = {
    username,
    password
  }
  return request({
    url: '/login',
    method: 'post',
    params: data
  })
}

export function logout() {
  return request({
    url: '/logout',
    method: 'get'
  })
}

export function getUserInfo(token) {
  return request({
    url: `/user/info`,
    method: 'get',
    params: { token }
  })
}

