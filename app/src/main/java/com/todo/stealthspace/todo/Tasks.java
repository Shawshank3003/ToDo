package com.todo.stealthspace.todo;

import java.sql.Date;
import java.sql.Time;

public class Tasks {
    private String taskDescription;

    private long id;
    private Date taskDate;
    private Time taskTime;
    private Repeat repeat;

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public long getId(){
        return  id;
    }

    public void setId(long id){
        this.id=id;
    }

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    public Time getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(Time taskTime) {
        this.taskTime = taskTime;
    }

    public Repeat getRepeat() {
        return repeat;
    }

    public void setRepeat(Repeat repeat) {
        this.repeat = repeat;
    }


}
