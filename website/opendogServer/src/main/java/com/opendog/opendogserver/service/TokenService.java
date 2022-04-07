package com.opendog.opendogserver.service;

import com.opendog.opendogserver.entity.Token;
import org.springframework.stereotype.Service;

@Service
public interface TokenService {
    
    /*
     * @Author opendog
     * @Description 
     * @Date 2022/4/6 23:56
     * @Param [uid]
     * @return java.lang.String
     **/
    Token allocNewToken(int uid);



    Token allocTmpToken(int uid);
    
    /*
     * @Author opendog
     * @Description 
     * @Date 2022/4/6 23:56
     * @Param [uid, token]
     * @return boolean
     **/
    boolean checkTokenIsValid(int uid,String md5);
    
    /*
     * @Author opendog
     * @Description 
     * @Date 2022/4/6 23:56
     * @Param [uid, token]
     * @return boolean
     **/
    boolean freeToken(int uid,String token);
    
    /*
     * @Author opendog
     * @Description 
     * @Date 2022/4/7 0:00
     * @Param [uid]
     * @return java.lang.String
     **/
    Token updateToken(int uid, long tag, int state);
    
    /*
     * @Author opendog
     * @Description 
     * @Date 2022/4/7 16:50
     * @Param [uid]
     * @return com.opendog.opendogserver.entity.Token
     **/
    Token selectToken(int uid);
}
