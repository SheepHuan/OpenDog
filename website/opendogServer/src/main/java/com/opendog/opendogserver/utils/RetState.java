package com.opendog.opendogserver.utils;

public enum RetState {

<<<<<<< HEAD
    SUCCESS(1000,"SUCCESS"),
    ERROR(2000,"ERROR");
=======
    SUCCESS(1000,"成功"),
    ERROR(2000,"错误"),
    UNAUTHORIZED(2001,"未授权"),
    BADHEADER(2002,"错误的请求头"),
    BADPARAMS(2003,"错误的请求参数"),
    DATABASEERROR(2004,"数据库操作异常");
>>>>>>> upstream/main

    private int state;
    private String description;

    RetState(int state,String desc){
        this.state = state;
        this.description = desc;
    }

    public static RetState valueOf(int value){
        for (RetState state: RetState.values()){
            if (state.state == value)
                return state;
        }
        return null;
    }

    public int getState(){
        return this.state;
    }
    public String getDescription(){
        return this.description;
    }
}
