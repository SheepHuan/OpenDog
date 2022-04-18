import request from '@/utils/request'

export function deleteCase(data) {
    return request({
    url: '/case/delete_case',
    method: 'post',
    data
    })
}

export function updateCase(data) {
return request({
        url: '/case/update_case',
        method: 'post',
        data
    })
}

export function selectCase(data) {
    return request({
        url: '/case/select_case',
        method: 'post',
        data
    })
}

export function getCaseDetail(data) {
    return request({
      url: '/case/get_case_detail',
      method: 'post',
      data
    })
}

export function getCaseDetailWithPasswd(data) {
    return request({
      url: '/case/get_case_detail_with_passwd',
      method: 'post',
      data
    })
}

export function pigeonholeCase(data) {
    return request({
      url: '/case/pigeonhole_case',
      method: 'post',
      data
    })
}

export function removeCasesFromProject(data) {
    return request({
      url: '/case/remove_cases_from_project',
      method: 'post',
      data
    })
}

export function getCaseData(data) {
    return request({
      url: '/case/get_case_data',
      method: 'post',
      data
    })
}