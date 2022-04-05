package com.opendog.opendogserver.utils;

public enum RetState {

    SUCCESS(1000,"SUCCESS"),
    ERROR(2000,"ERROR");

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
