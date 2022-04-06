package com.opendog.opendogserver.service;

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
    String allocNewToken(int uid);
    
    /*
     * @Author opendog
     * @Description 
     * @Date 2022/4/6 23:56
     * @Param [uid, token]
     * @return boolean
     **/
    boolean checkTokenIsValid(int uid,String token);
    
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
    String updateToken(int uid);
}
