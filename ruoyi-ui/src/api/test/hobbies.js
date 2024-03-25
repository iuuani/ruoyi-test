import request from '@/utils/request'

// 查询学生爱好列表
export function listHobbies(query) {
  return request({
    url: '/test/hobbies/list',
    method: 'get',
    params: query
  })
}

// 查询学生爱好详细
export function getHobbies(id) {
  return request({
    url: '/test/hobbies/' + id,
    method: 'get'
  })
}

// 新增学生爱好
export function addHobbies(data) {
  return request({
    url: '/test/hobbies',
    method: 'post',
    data: data
  })
}

// 修改学生爱好
export function updateHobbies(data) {
  return request({
    url: '/test/hobbies',
    method: 'put',
    data: data
  })
}

// 删除学生爱好
export function delHobbies(id) {
  return request({
    url: '/test/hobbies/' + id,
    method: 'delete'
  })
}
