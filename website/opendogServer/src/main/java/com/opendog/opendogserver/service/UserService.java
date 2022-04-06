package com.opendog.opendogserver.service;


import com.opendog.opendogserver.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {



    void insertUser(String userName,String passwd,String email,String question,String answer );

    boolean checkFiled(User user);

    String login(String userName, String passwd);

    boolean loginOut(String userName,String token);

    String getSafeQuestion(String userName);

     String checkQuestionAnswer(String userName,String answer);

    boolean resetForgetPassword(String userName,String tmpToken,String newPassword);

   boolean resetPassword(String userName,String token,String oldPassword,String newPassword);

   String getUserDetail(String userName,String token);

   boolean updateUserDetail(String userName,String passwd,String email,String question,String answer);

}
