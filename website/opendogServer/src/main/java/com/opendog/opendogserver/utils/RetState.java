package com.opendog.opendogserver.utils;

public enum RetState {

    SUCCESS(1000,"成功"),
    ERROR(2000,"失败"),
    UNAUTHORIZED(2001,"未授权"),
    ILLEGALPARAMS(2002,"不合法的参数");


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
