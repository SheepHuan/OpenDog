package com.opendog.opendogserver.service.serviceImpl;

import com.opendog.opendogserver.entity.User;
import com.opendog.opendogserver.mapper.UserMapper;
import com.opendog.opendogserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;


    @Override
    public void insertUser(String userName, String passwd, String email, String question, String answer) {

    }

    @Override
    public boolean checkFiled(User user) {
        return false;
    }

    @Override
    public String login(String userName, String passwd) {
        return null;
    }

    @Override
    public boolean loginOut(String userName, String token) {
        return false;
    }

    @Override
    public String getSafeQuestion(String userName) {
        return null;
    }

    @Override
    public String checkQuestionAnswer(String userName, String answer) {
        return null;
    }

    @Override
    public boolean resetForgetPassword(String userName, String tmpToken, String newPassword) {
        return false;
    }

    @Override
    public boolean resetPassword(String userName, String token, String oldPassword, String newPassword) {
        return false;
    }

    @Override
    public String getUserDetail(String userName, String token) {
        return null;
    }

    @Override
    public boolean updateUserDetail(String userName, String passwd, String email, String question, String answer) {
        return false;
    }
}
