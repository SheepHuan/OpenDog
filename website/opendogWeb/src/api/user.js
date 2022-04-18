import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/user/login',
    method: 'post',
    data
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post',
    data
  })
}

export function register(data){
  return request({
    url: '/user/register',
    method: 'post',
    data
  })
}

export function checkField(data){
  return request({
    url: '/user/check_field',
    method: 'post',
    data
  })
}

export function getSafeQuestion(data){
  return request({
    url: '/user/get_safe_question',
    method: 'post',
    data
  })
}

export function checkQuestionAnswer(data){
  return request({
    url: '/user/check_question_answer',
    method: 'post',
    data
  })
}

export function resetForgetPassword(data){
  return request({
    url: '/user/reset_forget_password',
    method: 'post',
    data
  })
}

export function resetPassword(data){
  return request({
    url: '/user/reset_password',
    method: 'post',
    data
  })
}

export function getUserDetail(data){
  return request({
    url: '/user/get_user_detail',
    method: 'post',
    data
  })
}

export function updateUserDetail(data){
  return request({
    url: '/user/update_user_detail',
    method: 'post',
    data
  })
}