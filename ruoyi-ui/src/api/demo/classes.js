import request from '@/utils/request'

// 查询主子列表
export function listClasses(query) {
  return request({
    url: '/demo/classes/list',
    method: 'get',
    params: query
  })
}

// 查询主子详细
export function getClasses(classesId) {
  return request({
    url: '/demo/classes/' + classesId,
    method: 'get'
  })
}

// 新增主子
export function addClasses(data) {
  return request({
    url: '/demo/classes',
    method: 'post',
    data: data
  })
}

// 修改主子
export function updateClasses(data) {
  return request({
    url: '/demo/classes',
    method: 'put',
    data: data
  })
}

// 删除主子
export function delClasses(classesId) {
  return request({
    url: '/demo/classes/' + classesId,
    method: 'delete'
  })
}
