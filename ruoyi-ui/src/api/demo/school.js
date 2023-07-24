import request from '@/utils/request'

// 查询树列表
export function listSchool(query) {
  return request({
    url: '/demo/school/list',
    method: 'get',
    params: query
  })
}

// 查询树详细
export function getSchool(schoolId) {
  return request({
    url: '/demo/school/' + schoolId,
    method: 'get'
  })
}

// 新增树
export function addSchool(data) {
  return request({
    url: '/demo/school',
    method: 'post',
    data: data
  })
}

// 修改树
export function updateSchool(data) {
  return request({
    url: '/demo/school',
    method: 'put',
    data: data
  })
}

// 删除树
export function delSchool(schoolId) {
  return request({
    url: '/demo/school/' + schoolId,
    method: 'delete'
  })
}
