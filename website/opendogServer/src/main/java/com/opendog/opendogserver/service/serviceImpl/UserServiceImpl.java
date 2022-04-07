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
    public boolean insertUser(User user) {
        try{
            userMapper.insert(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean checkFiled(User user) {
        return false;
    }

    @Override
    public User login(String userName, String passwd) {
        return null;
    }


    @Override
    public String getSafeQuestion(String userName) {
        return null;
    }

    @Override
    public boolean checkQuestionAnswer(String userName, String answer) {
        return false;
    }

    @Override
    public boolean resetForgetPassword(String userName, String newPassword) {
        return false;
    }


    @Override
    public boolean resetPassword(String userName, String oldPassword, String newPassword) {
        return false;
    }

    @Override
    public User getUserDetail(int uid) {
        return null;
    }

    @Override
    public User updateUserDetail(String userName, String passwd, String email, String question, String answer) {
        return null;
    }
}
