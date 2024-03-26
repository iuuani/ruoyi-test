import request from '@/utils/request'

// 查询学生信息列表
export function listManage(query) {
  return request({
    url: '/student/manage/list',
    method: 'get',
    params: query
  })
}

// 查询学生信息详细
export function getManage(studentId) {
  return request({
    url: '/student/manage/' + studentId,
    method: 'get'
  })
}

// 新增学生信息
export function addManage(data) {
  return request({
    url: '/student/manage',
    method: 'post',
    data: data
  })
}

// 修改学生信息
export function updateManage(data) {
  return request({
    url: '/student/manage',
    method: 'put',
    data: data
  })
}

// 删除学生信息
export function delManage(studentId) {
  return request({
    url: '/student/manage/' + studentId,
    method: 'delete'
  })
}