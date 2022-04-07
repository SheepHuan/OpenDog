package com.opendog.opendogserver.service.serviceImpl;

import com.opendog.opendogserver.entity.Token;
import com.opendog.opendogserver.mapper.TokenMapper;
import com.opendog.opendogserver.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.*;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    TokenMapper tokenMapper;

    //有效时间30分钟
    private final static long longValidTime = 30 * 60 * 1000;
    private final static long shortValidTime = 5 * 60 * 1000;


    @Override
    public Token allocNewToken(int uid) {

        //先检查有没有这个uid对应的token
        Token token = selectToken(uid);

        //表中有这个token，更新一下token
        if (token!=null){
            token = updateToken(uid,longValidTime,0);

        }else{//表中没有这个token，新建一个token
            token = new Token();
            token.setUserId(uid);
            // state,0:表示长期token，用来登录和验证身份; 1:表示临时token,用于找回密码
            token.setState(0);
            // 设置token
            token.setToken(makeToken());
            // 设置有效时间10分钟
            token.setValidTime(new Date(System.currentTimeMillis()+ longValidTime));
            tokenMapper.insert(token);
        }

        return token;
    }


    @Override
    public Token allocTmpToken(int uid) {

        //先检查有没有这个uid对应的token
        Token token = selectToken(uid);

        //表中有这个token，更新一下token
        if (token!=null){
            token = updateToken(uid,shortValidTime,1);

        }else{//表中没有这个token，新建一个token
            token = new Token();
            token.setUserId(uid);
            // state,0:表示长期token，用来登录和验证身份; 1:表示临时token,用于找回密码
            token.setState(1);
            // 设置token
            token.setToken(makeToken());
            // 设置有效时间10分钟
            token.setValidTime(new Date(System.currentTimeMillis()+ shortValidTime));
            tokenMapper.insert(token);
        }

        return token;
    }


    @Override
    public boolean checkTokenIsValid(int uid, String md5) {
        Token token = selectToken(uid);
        if (token!=null){
            if (!token.getToken().equals(md5))
                return false;
            return token.getValidTime().getTime() >= System.currentTimeMillis();
        }else {
            return false;
        }
    }


    @Override
    public boolean freeToken(int uid, String md5) {
        Token token = selectToken(uid);
        if (token!=null){
            tokenMapper.deleteById(token);
            return true;
        }else {
            return false;
        }
    }


    @Override
    public Token updateToken(int uid,long lag, int state) {
        Token token = selectToken(uid);

        if (token==null){
            //不存在token,应该申请token
            return null;
        }else{
            String md5 = makeToken();
            token.setToken(md5);
            token.setValidTime(new Date(System.currentTimeMillis()+lag));
            token.setState(state);
            tokenMapper.updateById(token);
            return token;
        }
    }


    @Override
    public Token selectToken(int uid) {
        Map select = new HashMap<String, Integer>();
        select.put("uid",uid);
        List<Token> tokens = tokenMapper.selectByMap(select);
        if (tokens == null || tokens.size() == 0){
            return null;
        }
        return tokens.get(0);
    }

    /*
     * @Author opendog
     * @Description
     * 根据当前时间戳生成一个随机token
     * @Date 2022/4/7 18:56
     * @Param []
     * @return java.lang.String
     **/
    public String makeToken() {
        String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] md5 =  md.digest(token.getBytes());
            return Base64Utils.encodeToString(md5);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
