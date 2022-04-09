package com.opendog.opendogserver.utils;

import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;

/**
 * @className: MHttpHeader
 * @description: TODO 类描述
 * @author: opendog
 * @date: 2022/4/9
 **/

@Getter
@Setter
public class MHttpHeader {

    int uid;
    String token;
    String contentType;

    public static MHttpHeader getHeadFromRequest(HttpServletRequest request){
        MHttpHeader mHttpHeader =  new MHttpHeader();
        mHttpHeader.setContentType(request.getContentType());
        mHttpHeader.setToken(request.getHeader("token"));
        mHttpHeader.setUid(request.getIntHeader("uid"));
        return mHttpHeader;
    }
}
