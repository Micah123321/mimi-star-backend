import request from '@/utils/request'

// 查询单列表
export function listStudent(query) {
  return request({
    url: '/demo/student/list',
    method: 'get',
    params: query
  })
}

// 查询单详细
export function getStudent(studentId) {
  return request({
    url: '/demo/student/' + studentId,
    method: 'get'
  })
}

// 新增单
export function addStudent(data) {
  return request({
    url: '/demo/student',
    method: 'post',
    data: data
  })
}

// 修改单
export function updateStudent(data) {
  return request({
    url: '/demo/student',
    method: 'put',
    data: data
  })
}

// 删除单
export function delStudent(studentId) {
  return request({
    url: '/demo/student/' + studentId,
    method: 'delete'
  })
}
