import request from '@/utils/request'

export function createTask(data) {
  return request({
    url: '/task/create_task',
    method: 'post',
    data
  })
}

export function deleteTask(data) {
    return request({
      url: '/task/delete_task',
      method: 'post',
      data
    })
}

export function updateTask(data) {
    return request({
      url: '/task/update_task',
      method: 'post',
      data
    })
}

export function getUserTasks(data) {
    return request({
      url: '/task/get_users_task',
      method: 'post',
      data
    })
}

export function getTaskProjects(data) {
    return request({
      url: '/task/get_task_projects',
      method: 'post',
      data
    })
}


export function getTaskCases(data) {
    return request({
      url: '/task/get_task_projects',
      method: 'post',
      data
    })
}

export function getTaskDetail(data) {
    return request({
      url: '/task/get_task_detail',
      method: 'post',
      data
    })
}

export function addTaskMembers(data) {
    return request({
      url: '/task/add_task_members',
      method: 'post',
      data
    })
}

export function selectTaskMembers(data) {
    return request({
      url: '/task/select_task_members',
      method: 'post',
      data
    })
}

export function removeTaskMembers(data) {
    return request({
      url: '/task/remove_task_members',
      method: 'post',
      data
    })
}