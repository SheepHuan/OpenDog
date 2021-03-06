package com.opendog.opendogserver.service.serviceImpl;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.opendog.opendogserver.entity.User;
import com.opendog.opendogserver.mapper.UserMapper;
import com.opendog.opendogserver.service.UserService;
import com.opendog.opendogserver.utils.MD5Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean insertUser(User user) {
        try{
            user.setUpdatedTime(new Date(System.currentTimeMillis()));
            user.setCreatedTime(new Date(System.currentTimeMillis()));
            user.setRole(0);
            userMapper.insert(user);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean checkFiled(User user) {


        boolean isLegal = true;
        if(user.getUserName().equals("")) {
           isLegal = false;
            return isLegal;
        }
        if (user.getPassword().equals("")){
            isLegal = false;
            return isLegal;
        }
        if(user.getEmail().equals("")){
            isLegal = false;
            return isLegal;
        }
        //都为空或都不为空
//        System.out.println(String.format("%b\n%b", user.getQuestion().equals(""),user.getAnswer().equals("")));
        if ((!user.getQuestion().equals("") && user.getAnswer().equals("")) || (user.getQuestion().equals("") && !user.getAnswer().equals(""))){
            isLegal = false;
            return isLegal;
        }
        //检查是否重名
        User sameNameUser = selectUserByName(user.getUserName());
        if (sameNameUser!=null){
            isLegal = false;
            return isLegal;
        }

        return isLegal;
    }

    @Override
    public boolean isExistUserName(String userName) {
        Map<String,Object> map = new HashMap<>();
        map.put("user_mame",userName);
        List<User> users = userMapper.selectByMap(map);
        return users.size() > 0;
    }

    @Override
    public User login(String userName, String password) {

        return userMapper.selectOne(
                Wrappers.<User>query().eq("user_name",userName).eq("passwd",password));

    }




    @Override
    public String getSafeQuestion(String userName) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);

        String question = userMapper.selectOne(Wrappers.<User>query().eq("user_name",userName)).getQuestion();
        if(!question.equals("")){
            return question;
        }


        return null;
    }

    @Override
    public boolean checkQuestionAnswer(String userName, String answer) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName).eq("answer",answer);
        long rows = userMapper.selectCount(queryWrapper);
        return rows > 0;
    }

    @Override
    public boolean resetForgetPassword(String userName, String newPassword) {



        User user = new User();
        user.setUserName(userName);
        user.setPassword(newPassword);

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_name", userName);
        updateWrapper.set("passwd", user.getPassword());
        int rows = userMapper.update(user, updateWrapper);

        return rows > 0;


    }

    @Override
    public boolean resetForgetPassword(int uid, String newPassword) {
        User user = userMapper.selectById(uid);
        if (user!=null){
            user.setPassword(newPassword);
            userMapper.updateById(user);
            return true;
        }
        return false;

    }


    @Override
    public boolean resetPassword(int uid, String oldPassword, String newPassword) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",uid);
        queryWrapper.eq("passwd", oldPassword);
        User user = userMapper.selectOne(queryWrapper);
        if(user == null){
            return false;
        }
        user.setPassword(newPassword);
        userMapper.updateById(user);

        return true;
    }

    @Override
    public User getUserDetail(int uid) {

        User userDetail = userMapper.selectById(uid);
        if(userDetail == null){
            return null;
        }
        userDetail.setPassword("");
        return userDetail;
    }

    @Override
    public User updateUserDetail(User user) {
        user.setUpdatedTime(new Date(System.currentTimeMillis()));
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("uid",user.getUid());
        //是否存在这个user
        User oldUser = userMapper.selectOne(updateWrapper);
        if (oldUser!=null){
            user.setPassword(oldUser.getPassword());
            user.setUpdatedTime(new Date(System.currentTimeMillis()));
            userMapper.updateById(user);
        }else{
            return null;
        }



        return user;
    }

    @Override
    public User selectUserByName(String userName) {
        Map<String,Object> map = new HashMap<>();
        map.put("user_name",userName);
       List<User> users = userMapper.selectByMap(map);
        if (users.size()>0)
            return users.get(0);
        return null;
    }

}
