import request from '@/utils/request'

export function createProject() {
    return request({
      url: '/project/create_project',
      method: 'post'
    })
}

export function deleteProject() {
    return request({
      url: '/project/delete_project',
      method: 'post'
    })
}

export function updateProject() {
    return request({
      url: '/project/update_project',
      method: 'post'
    })
}

export function selectUserProjects() {
    return request({
      url: '/project/select_user_projects',
      method: 'post'
    })
}

export function selectProjectCases() {
    return request({
      url: '/project/select_project_cases',
      method: 'post'
    })
}

export function get_project_detail() {
    return request({
      url: '/project/get_project_detail',
      method: 'post'
    })
}
