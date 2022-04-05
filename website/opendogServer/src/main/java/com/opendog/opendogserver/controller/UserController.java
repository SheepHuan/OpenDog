package com.opendog.opendogserver.controller;


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


    @PostMapping(value = "register")
    public RetJson userRegister(HttpServletRequest request){
        RetState state = RetState.SUCCESS;
        String msg = "";
        Map<String,String> data =new HashMap<>();
        try{
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String question = request.getParameter("question");
            String answer = request.getParameter("answer");

            msg = "注册成功";

        }catch (Exception e){
            state = RetState.ERROR;
            msg = "注册失败";
        }
        return RetJson.retJson(state,msg,data);
    }



}
