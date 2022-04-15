package com.opendog.opendogserver.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class RetJson {

    private int state;
    private String message;
    private Map data;

    public static RetJson retJson(RetState state,String message,Map data){
        RetJson ret = new RetJson();
        ret.setData(data);
        if(!message.equals(""))
            ret.setMessage(message);
        else
            ret.setMessage(state.getDescription());
        ret.setState(state.getState());
        return ret;
    }

    public static RetJson retJson(RetState state,Map data){
        RetJson ret = new RetJson();
        ret.setData(data);
        ret.setMessage(state.getDescription());
        ret.setState(state.getState());
        return ret;
    }

    public static RetJson retJson(RetState state){
        RetJson ret = new RetJson();
        ret.setMessage(state.getDescription());
        ret.setState(state.getState());
        return ret;
    }

}
