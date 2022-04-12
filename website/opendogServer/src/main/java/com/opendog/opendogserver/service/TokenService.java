package com.opendog.opendogserver.service;

import com.opendog.opendogserver.entity.Token;
import org.springframework.stereotype.Service;

@Service
public interface TokenService {

    /*
     * @Author opendog
     * @Description
     * 为uid申请一个新的token,表中没有则新建一个，有的话则更新
     * @Date 2022/4/7 18:53
     * @Param [uid]
     * @return com.opendog.opendogserver.entity.Token
     **/
    Token allocNewToken(int uid);


    /*
     * @Author opendog
     * @Description
     * 申请一个临时token
     * @Date 2022/4/7 18:53
     * @Param [uid]
     * @return com.opendog.opendogserver.entity.Token
     **/
    Token allocTmpToken(int uid);

    /*
     * @Author opendog
     * @Description
     * 用于检查前端提交的token是否有效,返回true表示有效；否则无效
     * @Date 2022/4/7 18:54
     * @Param [uid, md5]
     * @return boolean
     **/
    boolean checkTokenIsValid(int uid,String md5);




    /*
     * @Author opendog
     * @Description
     * 删除某个uid的token,返回是否删除成功
     * @Date 2022/4/7 18:54
     * @Param [uid, md5]
     * @return boolean
     **/
    boolean freeToken(int uid,String token);

    /*
     * @Author opendog
     * @Description
     * @Date 2022/4/7 18:55
     * @Param [uid, lag, state]
     * @return com.opendog.opendogserver.entity.Token
     **/
    Token updateToken(int uid, long lag, int state);

    /*
     * @Author opendog
     * @Description
     * 检查某个uid的token存在情况，存在token返回该记录，否则返回null
     * @Date 2022/4/7 18:55
     * @Param [uid]
     * @return com.opendog.opendogserver.entity.Token
     **/
    Token selectToken(int uid);
}
