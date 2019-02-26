package com.todo.stealthspace.todo;

import java.io.Serializable;

public class Repeat implements Serializable{
    private RepeatTypeFormat repeatType;
    private CustomRepeat custom;

    // RepeatType
    public void setRepeatType(RepeatTypeFormat repeatType){
        this.repeatType = repeatType;
    }

    public RepeatTypeFormat getRepeatType(){
        return repeatType;
    }


    // Custom
    public void setCustom(CustomRepeat custom){
        this.custom = custom;
    }

    public CustomRepeat getCustom(){
        return custom;
    }
}

