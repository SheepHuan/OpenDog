package com.opendog.opendogserver.controller;


import com.opendog.opendogserver.entity.User;
import com.opendog.opendogserver.service.UserService;
import com.opendog.opendogserver.utils.RetJson;
import com.opendog.opendogserver.utils.RetState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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

        return null;
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

        return null;
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

        return null;
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

        return null;
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

        return null;
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

        return null;
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

        return null;
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

        return null;
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

        return null;
    }
}
