package com.example.yuki;

import java.text.SimpleDateFormat;

public class Message {
    private String body;
    private long timeStamp;
    private String time;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public Message(long timeStamp,String body){
        this.body=body;
        this.timeStamp=timeStamp;
        this.time=df.format(timeStamp);
    }

    public long getTimeStamp(){
        return timeStamp;
    }

    public String getBody(){
        return body;
    }

    public String getTime(){
        return time;
    }
}
