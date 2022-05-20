package com.opendog.opendogserver.controller;


import com.alibaba.fastjson.JSONObject;
import com.opendog.opendogserver.entity.Token;
import com.opendog.opendogserver.entity.User;
import com.opendog.opendogserver.service.TokenService;
import com.opendog.opendogserver.service.UserService;
import com.opendog.opendogserver.utils.IOUtils;
import com.opendog.opendogserver.utils.MHttpHeader;

import com.opendog.opendogserver.utils.RetJson;
import com.opendog.opendogserver.utils.RetState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    /*
     * @Author opendog
     * @Description
     * @Date 2022/4/6 23:45
     * @Param [request]
     * @return com.opendog.opendogserver.utils.RetJson
     **/
    @PostMapping(value = "register")
    public RetJson register(HttpServletRequest request) {
        RetState state = RetState.SUCCESS;
        String msg = "";
        Map<String, String> data = new HashMap<>();
        try {
            JSONObject params = IOUtils.stringConvert2Json(IOUtils.inputStream2String(request.getInputStream()));
            String username = params.getString("username");
            String password = params.getString("password");
            String email = params.getString("email");
            String question = params.getString("question");
            String answer = params.getString("answer");
            User user = new User();
            user.setUserName(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setQuestion(question);
            user.setAnswer(answer);
            //先检查User合法性
            if (userService.checkFiled(user)) {

                if (userService.insertUser(user)) {
                    state = RetState.SUCCESS;
                    msg = "注册成功";

                } else {
                    state = RetState.ERROR;
                    msg = "记录插入失败";
                }

            } else {
                state = RetState.ERROR;
                msg = "字段合法性检查不通过";
            }

        } catch (Exception e) {
            state = RetState.ERROR;
            msg = "注册失败";
        }
        return RetJson.retJson(state, msg, data);
    }

    /*
     * @Author opendog
     * @Description
     * 检查字段合法性
     * @Date 2022/4/6 23:45
     * @Param [request]
     * @return com.opendog.opendogserver.utils.RetJson
     **/
    @PostMapping(value = "check_field")
    public RetJson checkField(HttpServletRequest request) {

        RetState state = RetState.SUCCESS;
        String msg = "";
        Map<String, String> data = new HashMap<>();

        try {
            JSONObject params = IOUtils.stringConvert2Json(IOUtils.inputStream2String(request.getInputStream()));
            String username = params.getString("username");
            String password = params.getString("password");
            String email = params.getString("email");
            String question = params.getString("question");
            String answer = params.getString("answer");

            User user = new User();
            user.setUserName(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setQuestion(question);
            user.setAnswer(answer);

            if (!userService.checkFiled(user)) {
                state = RetState.ILLEGALPARAMS;

            }
        } catch (Exception e) {
            state = RetState.UNAUTHORIZED;
        }
        return RetJson.retJson(state, msg, data);

    }

    /*
     * @Author opendog
     * @Description
     * 登录之前，请先检查字段合法性
     * @Date 2022/4/6 23:45
     * @Param [request]
     * @return com.opendog.opendogserver.utils.RetJson
     **/
    @PostMapping(value = "login")
    public RetJson login(HttpServletRequest request) {

        RetState state = RetState.SUCCESS;
        String msg = "";
        Map<String, Object> data = new HashMap<>();
        try {
            JSONObject params = IOUtils.stringConvert2Json(IOUtils.inputStream2String(request.getInputStream()));
            String userName = params.getString("username");
            String password = params.getString("password");

            User loginUser = userService.login(userName, password);
            if (loginUser != null) {
                Token token=tokenService.allocNewToken(loginUser.getUid());
                data.put("user", loginUser);
                data.put("token", token.getToken());
                state = RetState.SUCCESS;
                msg = "登录成功";
            }else{
                state = RetState.ERROR;
            }
        } catch (Exception e) {
            state = RetState.ERROR;
            msg = "登录失败";
        }
        return RetJson.retJson(state, msg, data);


    }

    /*
     * @Author opendog
     * @Description
     * 等出接口,调用TokeService删除对应uid的token记录
     * @Date 2022/4/6 23:45
     * @Param [request]
     * @return com.opendog.opendogserver.utils.RetJson
     **/
    @PostMapping(value = "login_out")
    public RetJson loginout(HttpServletRequest request) {


        RetState state = RetState.SUCCESS;
        String msg = "退出成功";
        Map<String, String> data = new HashMap<>();
        try {
            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
            int uid = mHttpHeader.getUid();
            String token = mHttpHeader.getToken();
            if (!tokenService.freeToken(uid, token)) {
                msg = "退出失败";
                state = RetState.ERROR;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return RetJson.retJson(state, msg, data);


    }

    /*
     * @Author opendog
     * @Description
     * 获取username对应的安全问题
     * @Date 2022/4/6 23:45
     * @Param [request]
     * @return com.opendog.opendogserver.utils.RetJson
     **/
    @PostMapping(value = "get_safe_question")
    public RetJson getSafeQuestion(HttpServletRequest request) {

        RetState state = RetState.SUCCESS;
        String msg = "";
        Map<String, Object> data = new HashMap<>();
        try {
            JSONObject params = IOUtils.stringConvert2Json(IOUtils.inputStream2String(request.getInputStream()));
            String userName = params.getString("username");

            //先检查User合法性
            User user = userService.selectUserByName(userName);

            if (user!=null) {

                state = RetState.SUCCESS;
                msg = "获取问题成功";
                data.put("question",user.getQuestion());

            } else {
                state = RetState.ERROR;
            }

        } catch (Exception e) {
            state = RetState.ERROR;
            msg = "获取问题失败";
        }
        return RetJson.retJson(state, msg, data);


    }

    /*
     * @Author opendog
     * @Description
     * 检查安全问题的答案
     * @Date 2022/4/6 23:45
     * @Param [request]
     * @return com.opendog.opendogserver.utils.RetJson
     **/
    @PostMapping(value = "check_question_answer")
    public RetJson checkQuestionAnswer(HttpServletRequest request) {

        RetState state = RetState.SUCCESS;
        String msg = "";
        Map<String, Object> data = new HashMap<>();
        try {
            JSONObject params = IOUtils.stringConvert2Json(IOUtils.inputStream2String(request.getInputStream()));
            String userName = params.getString("username");
            String answer = params.getString("answer");

            //TODO 这里也要改先检查User合法性
            User user = userService.selectUserByName(userName);
            if (user!=null) {
                boolean flag = userService.checkQuestionAnswer(userName, answer);
                if (!flag) {
                    state = RetState.SUCCESS;
                    msg = "确认答案失败";
                } else {
                    state = RetState.ERROR;
                    msg = "确认答案成功";
                    data.put("tmpToken",tokenService.allocTmpToken(user.getUid()));
                }
            } else {
                state = RetState.ERROR;
                msg = "字段合法性检查不通过";
            }

        } catch (Exception e) {
            state = RetState.ERROR;
            msg = "确认答案失败";
        }
        return RetJson.retJson(state, msg, data);


    }

    /*
     * @Author opendog
     * @Description
     * 重置忘记密码
     * @Date 2022/4/6 23:45
     * @Param [request]
     * @return com.opendog.opendogserver.utils.RetJson
     **/
    @PostMapping(value = "reset_forget_password")
    public RetJson resetForgetPassword(HttpServletRequest request) {

        RetState state = RetState.SUCCESS;
        String msg = "";
        Map<String, String> data = new HashMap<>();
        try {
            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
            int uid = mHttpHeader.getUid();
            String token = mHttpHeader.getToken();

            JSONObject params = IOUtils.stringConvert2Json(IOUtils.inputStream2String(request.getInputStream()));
            String newPassword = params.getString("newPassword");

            //检查token是否有效
            if (tokenService.checkTokenIsValid(uid, token)) {

                if (!userService.resetForgetPassword(uid, newPassword)) {
                    state = RetState.ERROR;
                    msg = "重置失败";
                } else {
                    state = RetState.SUCCESS;
                    msg = "重置成功";
                }
            }else{
                state = RetState.ERROR;
                msg = "授权不合法";
            }

        } catch (Exception e) {
            state = RetState.ERROR;
            msg = "重置失败";
        }
        return RetJson.retJson(state, msg, data);


    }

    /*
     * @Author opendog
     * @Description
     *
     * @Date 2022/4/6 23:45
     * @Param [request]
     * @return com.opendog.opendogserver.utils.RetJson
     **/
    @PostMapping(value = "reset_password")
    public RetJson resetPassword(HttpServletRequest request) {

        RetState state = RetState.SUCCESS;
        String msg = "";
        Map<String, String> data = new HashMap<>();
        try {
            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
            int uid = mHttpHeader.getUid();
            String token = mHttpHeader.getToken();
            JSONObject params = IOUtils.stringConvert2Json(IOUtils.inputStream2String(request.getInputStream()));

            String newPassword = params.getString("newPassword");
            String oldPassword = params.getString("oldPassword");

            //先检查token合法性
            if (tokenService.checkTokenIsValid(uid, token)) {

                if (!userService.resetPassword(uid, oldPassword, newPassword)) {
                    state = RetState.ERROR;
                    msg = "重置失败";
                } else {
                    state = RetState.SUCCESS;
                    msg = "重置成功";
                }
            } else {
                state = RetState.ERROR;
                msg = "授权不合法";
            }
        } catch (Exception e) {
            state = RetState.ERROR;
            msg = "重置失败";
        }
        return RetJson.retJson(state, msg, data);


    }

    /*
     * @Author opendog
     * @Description
     * @Date 2022/4/6 23:46
     * @Param [request]
     * @return com.opendog.opendogserver.utils.RetJson
     **/
    @PostMapping(value = "get_user_detail")
    public RetJson getUserDetail(HttpServletRequest request) {
        RetState state = RetState.SUCCESS;
        String msg = "";
        Map<String, User> data = new HashMap<>();

        try {
            //获取请求头
            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
            int uid = mHttpHeader.getUid();
            String token = mHttpHeader.getToken();

            if (tokenService.checkTokenIsValid(uid, token)) {
                User user = userService.getUserDetail(uid);
                if (user == null) {
                    state = RetState.ERROR;
                    msg = "用户不存在";
                } else {
                    state = RetState.SUCCESS;
                    data.put("user", user);
                }
            } else {
                state = RetState.ERROR;
                msg = "授权失败";
            }
        } catch (Exception e) {
            state = RetState.ERROR;
            msg = "获取失败";
        }

        return RetJson.retJson(state, msg, data);


    }

    /*
     * @Author opendog
     * @Description
     *
     * @Date 2022/4/6 23:46
     * @Param [request]
     * @return com.opendog.opendogserver.utils.RetJson
     **/
    @PostMapping(value = "update_user_detail")
    public RetJson updateDetail(HttpServletRequest request) {

        RetState state = RetState.SUCCESS;
        String msg = "";
        Map<String, User> data = new HashMap<>();

        try {
            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
            int uid = mHttpHeader.getUid();
            String token = mHttpHeader.getToken();

            JSONObject params = IOUtils.stringConvert2Json(IOUtils.inputStream2String(request.getInputStream()));
            //获取请求头
            String userName = params.getString("username");
            String email = params.getString("email");
            String question = params.getString("question");
            String answer = params.getString("answer");
            User user = new User();
            user.setUid(uid);
            user.setUserName(userName);
            //随便设置一个通过字段检查
            user.setPassword("xxx");
            user.setEmail(email);
            user.setQuestion(question);
            user.setAnswer(answer);
            if (tokenService.checkTokenIsValid(uid,token)){

                    User userUpdate = userService.updateUserDetail(user);
                    if (userUpdate == null) {
                        state = RetState.ERROR;
                        msg = "用户未登录!";
                    } else {
                        state = RetState.SUCCESS;
                        msg = "更新成功!";
                        data.put("user",userUpdate);
                    }

            }else{
                state = RetState.UNAUTHORIZED;
            }



        } catch (Exception e) {
            e.printStackTrace();
            state = RetState.ERROR;
            msg = "更新失败";
        }
        return RetJson.retJson(state, msg, data);


    }
}
