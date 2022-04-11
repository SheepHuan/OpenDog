package com.opendog.opendogserver.service;


import com.opendog.opendogserver.entity.User;
import org.springframework.stereotype.Service;


@Service
public interface UserService {


    /*
     * @Author opendog
     * @Description
     * 数据库表插入一个新的 User
     * @Date 2022/4/7 19:37
     * @Param [user]
     * @return boolean
     **/
    boolean insertUser(User user );

    /*
     * @Author opendog
     * @Description
     * 检查User字段合法性:
     * username,password,email不为空,email格式符合正常邮箱格式;
     * question,answer 要么都不为空,要么都为空。
     * @Date 2022/4/7 19:10
     * @Param [user]
     * @return boolean
     **/
    boolean checkFiled(User user);

    /*
     * @Author opendog
     * @Description
     * 实现登录,登录是验证账号密码,返回用户信息
     * @Date 2022/4/7 19:17
     * @Param [userName, passwd]
     * @return com.opendog.opendogserver.entity.User
     **/
<<<<<<< HEAD
    User login(String userName, String password);
=======
    User login(String userName, String passwd);
>>>>>>> upstream/main


    /*
     * @Author opendog
     * @Description
     * 根据username获取安全问题
     * @Date 2022/4/7 19:25
     * @Param [userName]
     * @return java.lang.String
     **/
    String getSafeQuestion(String userName);

    /*
     * @Author opendog
     * @Description
     * 根据username 回答安全问题的答案
     * @Date 2022/4/7 19:25
     * @Param [userName, answer]
     * @return java.lang.String
     **/
    boolean checkQuestionAnswer(String userName,String answer);

    /*
     * @Author opendog
     * @Description
     * 根据用户名重置密码
     * @Date 2022/4/7 19:28
     * @Param [userName, newPassword]
     * @return boolean
     **/
    boolean resetForgetPassword(String userName,String newPassword);

    /*
     * @Author opendog
     * @Description
     * 先检查旧密码，再更新用户新密码
     * @Date 2022/4/7 19:28
     * @Param [userName, oldPassword, newPassword]
     * @return boolean
     **/
    boolean resetPassword(String userName,String oldPassword,String newPassword);

    /*
     * @Author opendog
     * @Description
     * @Date 2022/4/7 19:29
     * 根据uid查询User
     * @Param [uid]
     * @return com.opendog.opendogserver.entity.User
     **/
    User getUserDetail(int uid);

    /*
     * @Author opendog
     * @Description
     * @Date 2022/4/7 19:26
     * @Param [userName, passwd, email, question, answer]
     * @return com.opendog.opendogserver.entity.User
     **/
    User updateUserDetail(String userName,String passwd,String email,String question,String answer);

}
