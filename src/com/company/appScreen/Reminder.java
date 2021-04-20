package com.company.appScreen;

import java.sql.Time;

public class Reminder {
    private String message;
    private String time;

    public Reminder(){}


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
