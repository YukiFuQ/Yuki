package com.example.yuki.entity;

import java.io.Serializable;
import java.util.*;

public class User implements Serializable {
    private String name;
    private List<Message> msgList;
    private Set<String> followList;

    public User(String name){
        this.name = name;
        msgList = new LinkedList<>();
        followList = new HashSet<>();
    }

    public void addMsg(Message msg){
        msgList.add(msg);
    }

    public void addFollow(String username){
        followList.add(username);
    }

    public Set<String> getFollowList(){
        return followList;
    }
    public String getName(){
        return name;
    }
    public List<Message> getSortedMegList(){
        Collections.sort(msgList, new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                if(o2.getTimeStamp()>o1.getTimeStamp())
                    return 1;
                return -1;
            }
        });
        return msgList;
    }

    public static void main(String[] args) {
        User user = new User("test");
        for(int i=0;i<10;i++){
            user.addMsg(new Message(System.currentTimeMillis(),"message"+i));
        }
        for(Message msg:user.getSortedMegList()){
            System.out.println(msg.getBody());
        }
    }
}
