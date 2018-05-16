package com.example.yuki;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DB {
    private Map<String,User> map;
    public static String name="";

    public DB(){
        map = new HashMap<>();
    }

    public void addUser(User user){
        map.put(user.getName(),user);
    }

    public User getUser(String name){
        return map.get(name);
    }

    public List<Message> getWall(String name){
        User user = map.get(name);
        if(user==null){
            user = new User(name);
            map.put(name,user);
        }
        return user.getSortedMegList();
    }
    public void sendMessage(String username,String msg){
        Message newMsg = new Message(System.currentTimeMillis(),msg);
        User nuser = getUser(username);
        if(nuser==null){
            nuser = new User(username);
             nuser.addMsg(newMsg);
             addUser(nuser);
        }else{
            nuser.addMsg(newMsg);
            addUser(nuser);
        }
    }

    public Set<String> sub(String username, String subname){
        User user1 = getUser(username);
        User user2 = getUser(subname);
        if(user1==null){
            user1 = new User(username);
            addUser(user1);
        }
        if(user2==null){
            return user1.getFollowList();
        }
        user1.addFollow(subname);
        return user1.getFollowList();
    }

    public Set<String> getSubList(String username){
        User user = getUser(username);
        if(user==null)
            return null;
        return user.getFollowList();
    }

}
