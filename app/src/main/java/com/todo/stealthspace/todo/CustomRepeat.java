package com.todo.stealthspace.todo;

import java.io.Serializable;

public class CustomRepeat implements Serializable {
    private CustomRepeatTypeFormat customRepeatType;
    private String customString;

    // RepeatType
    public void setCustomRepeatType(CustomRepeatTypeFormat customRepeatType){
        this.customRepeatType = customRepeatType;
    }

    public CustomRepeatTypeFormat getCustomRepeatType(){
        return customRepeatType;
    }


    // Custom
    public void setCustomString(String customString){
        this.customString = customString;
    }

    public String getCustomString(){
        return customString;
    }
}