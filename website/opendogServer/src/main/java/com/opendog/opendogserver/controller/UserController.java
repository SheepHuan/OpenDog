package com.opendog.opendogserver.controller;


import com.opendog.opendogserver.common.CONSTANT;
import com.opendog.opendogserver.entity.User;
import com.opendog.opendogserver.service.UserService;
import com.opendog.opendogserver.utils.MHttpHeader;
import com.opendog.opendogserver.utils.RetJson;
import com.opendog.opendogserver.utils.RetState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    UserService userService;


    /*
     * @Author opendog
     * @Description
     * @Date 2022/4/6 23:45
     * @Param [request]
     * @return com.opendog.opendogserver.utils.RetJson
     **/
    @PostMapping(value = "register")
    public RetJson register(HttpServletRequest request){
        RetState state = RetState.SUCCESS;
        String msg = "";
        Map<String,String> data =new HashMap<>();
        try{
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String question = request.getParameter("question");
            String answer = request.getParameter("answer");
            User user = new User();
            user.setUserName(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setQuestion(question);
            user.setAnswer(answer);
            //先检查User合法性
            if (userService.checkFiled(user)){

                if (userService.insertUser(user)){
                    state = RetState.ERROR;
                    msg = "数据表记录插入失败";
                }else{
                    state = RetState.SUCCESS;
                    msg = "注册成功";
                }

            }else{
                state = RetState.ERROR;
                msg = "字段合法性检查不通过";
            }

        }catch (Exception e){
            state = RetState.ERROR;
            msg = "注册失败";
        }
        return RetJson.retJson(state,msg,data);
    }

    /*
     * @Author opendog
     * @Description
     * 检查字段合法性
     * @Date 2022/4/6 23:45
     * @Param [request]
     * @return com.opendog.opendogserver.utils.RetJson
     **/
    @PostMapping(value = "checkField")
    public RetJson checkField(HttpServletRequest request){
        RetState state = RetState.SUCCESS;
        String msg = "字段合法!";
        Map<String,String> data =new HashMap<>();
        User user=new User();
        boolean flag=userService.checkFiled(user);
        try {
            if (flag==false){
                state = RetState.ERROR;
                msg = "字段非法失败";
            }else {
                state = RetState.SUCCESS;
                msg = "字段合法";
            }
        }catch (Exception e){
            state = RetState.ERROR;
            msg = "字段非法失败";
        }
        return RetJson.retJson(state,msg,data);
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
    public RetJson login(HttpServletRequest request){
        RetState state = RetState.SUCCESS;
        String msg = "成功登录 ";
        Map<String,User> data =new HashMap<>();
        try{
            String userName=request.getParameter("userName");
            String password=request.getParameter("password");
            User loginUser=userService.login(userName,password);
            if(loginUser!=null){
                request.getSession().setAttribute("login",loginUser);
                state = RetState.ERROR;
                msg = "登录成功";
            }
        }catch (Exception e){
            state = RetState.ERROR;
            msg = "登录失败";
        }
        return RetJson.retJson(state,msg,data);
    }

    /*
     * @Author opendog
     * @Description
     * 等出接口,调用TokeService删除对应uid的token记录
     * @Date 2022/4/6 23:45
     * @Param [request]
     * @return com.opendog.opendogserver.utils.RetJson
     **/
    @PostMapping(value = "loginout")
    public RetJson loginout(HttpServletRequest request){
        request.getSession().removeAttribute(CONSTANT.LOGIN_USER);
        RetState state = RetState.SUCCESS;
        String msg = "退出成功";
        Map<String,String> data =new HashMap<>();
        return RetJson.retJson(state,msg,data);
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
    public RetJson getSafeQuestion(HttpServletRequest request){
        RetState state = RetState.SUCCESS;
        String msg = "";
        Map<String,String> data =new HashMap<>();
        try{
            String userName=request.getParameter("userName");
            User user=new User();
            //先检查User合法性
            if (userService.checkFiled(user)){
                if(userService.getSafeQuestion(userName)!=user.getUserName()){
                    state = RetState.ERROR;
                    msg = "获取问题失败";
                }else{
                    state = RetState.ERROR;
                    msg = "获取问题成功";
                }
            }else{
                state = RetState.ERROR;
                msg = "字段合法性检查不通过";
            }

        }catch (Exception e){
            state = RetState.ERROR;
            msg = "获取问题失败";
        }
        return RetJson.retJson(state,msg,data);
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
    public RetJson checkQuestionAnswer(HttpServletRequest request){
        RetState state = RetState.SUCCESS;
        String msg = "";
        Map<String,String> data =new HashMap<>();
        try{
            String userName=request.getParameter("userName");
            String answer=request.getParameter("answer");
            boolean flag=userService.checkQuestionAnswer(userName,answer);
            //先检查User合法性
            User user=new User();
            if (userService.checkFiled(user)){
                if(flag==false){
                    state = RetState.SUCCESS;
                    msg = "确认答案失败";
                }else {
                    state = RetState.SUCCESS;
                    msg = "确认答案成功";
                }
            }else{
                state = RetState.ERROR;
                msg = "字段合法性检查不通过";
            }

        }catch (Exception e){
            state = RetState.ERROR;
            msg = "确认答案失败";
        }
        return RetJson.retJson(state,msg,data);
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
    public RetJson resetForgetPassword(HttpServletRequest request){
        RetState state = RetState.SUCCESS;
        String msg = "";
        Map<String,String> data =new HashMap<>();
        try{
            String userName=request.getParameter("userName");
            String newPassword=request.getParameter("newPassword");
            User user = new User();
            boolean flag=userService.resetForgetPassword(userName,newPassword);
            //先检查User合法性
            if (userService.checkFiled(user)){
                User loginUser = (User) request.getSession().getAttribute(CONSTANT.LOGIN_USER);
                if(loginUser==null){
                    state = RetState.ERROR;
                    msg = "用户未登录";
                }else if(flag==false){
                    state = RetState.ERROR;
                    msg = "重置失败";
                }else {
                    state = RetState.ERROR;
                    msg = "重置成功";
                }
            }else{
                state = RetState.ERROR;
                msg = "字段合法性检查不通过";
            }

        }catch (Exception e){
            state = RetState.ERROR;
            msg = "重置失败";
        }
        return RetJson.retJson(state,msg,data);
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
    public RetJson resetPassword(HttpServletRequest request){
        RetState state = RetState.SUCCESS;
        String msg = "";
        Map<String,String> data =new HashMap<>();
        try{
            String userName=request.getParameter("userName");
            String newPassword=request.getParameter("newPassword");
            String oldPassword=request.getParameter("oldPassword");
            boolean flag=userService.resetPassword(userName,oldPassword,newPassword);
            User user = new User();
            //先检查User合法性
            if (userService.checkFiled(user)){
                User loginUser = (User) request.getSession().getAttribute(CONSTANT.LOGIN_USER);
                if(loginUser==null){
                    state = RetState.ERROR;
                    msg = "用户未登录";
                }else if(flag==false){
                    state = RetState.ERROR;
                    msg = "重置失败";
                }else{
                    state = RetState.ERROR;
                    msg = "重置成功";
                }
            }else{
                state = RetState.ERROR;
                msg = "字段合法性检查不通过";
            }
        }catch (Exception e){
            state = RetState.ERROR;
            msg = "重置失败";
        }
        return RetJson.retJson(state,msg,data);
    }

    /*
     * @Author opendog
     * @Description
     * @Date 2022/4/6 23:46
     * @Param [request]
     * @return com.opendog.opendogserver.utils.RetJson
     **/
    @PostMapping(value = "get_detail")
    public RetJson getUserDetail(HttpServletRequest request){
        User loginUser = (User) request.getSession().getAttribute(CONSTANT.LOGIN_USER);
        RetState state = RetState.SUCCESS;
        String msg = "";
        Map<String, List<Object>> data =new HashMap<>();
        try {
            //获取请求头
            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
            int uid = mHttpHeader.getUid();
            User user=userService.getUserDetail(uid);
            if(loginUser == null){
                state = RetState.ERROR;
                msg = "用户未登录!";
            }else if(user==null){
                state = RetState.ERROR;
                msg = "获取失败!";
            }else{
                state = RetState.ERROR;
                msg = "获取成功!";
            }
        }catch (Exception e){
            state = RetState.ERROR;
            msg = "获取失败";
        }

        return RetJson.retJson(state,msg,data);

    }

    /*
     * @Author opendog
     * @Description
     *
     * @Date 2022/4/6 23:46
     * @Param [request]
     * @return com.opendog.opendogserver.utils.RetJson
     **/
    @PostMapping(value = "modify_detail")
    public RetJson modifyDetail(HttpServletRequest request){
        RetState state = RetState.SUCCESS;
        String msg = "";
        Map<String,String> data =new HashMap<>();
        User loginUser = (User) request.getSession().getAttribute(CONSTANT.LOGIN_USER);
        try {
            //获取请求头
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String question = request.getParameter("question");
            String answer = request.getParameter("answer");
            User user = new User();
            user.setUserName(userName);
            user.setPassword(password);
            user.setEmail(email);
            user.setQuestion(question);
            user.setAnswer(answer);
            User userUpdate=userService.updateUserDetail(userName,password,email,question,answer);
            if(loginUser == null){
                state = RetState.ERROR;
                msg = "用户未登录!";
            }else if(userUpdate==null){
                state = RetState.ERROR;
                msg = "更新失败!";
            }else {
                state = RetState.SUCCESS;
                msg = "更新成功!";
            }
        }catch (Exception e){
            state = RetState.ERROR;
            msg = "更新失败";
        }
        return RetJson.retJson(state,msg,data);
    }
}
