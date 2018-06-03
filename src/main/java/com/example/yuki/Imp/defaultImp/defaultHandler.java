package com.example.yuki.Imp.defaultImp;

import com.example.yuki.Interface.Handler;
import com.example.yuki.entity.DbConfig;
import com.example.yuki.entity.Message;
import com.example.yuki.entity.User;

import java.util.*;

public class defaultHandler implements Handler {

    private String loginUser = "";
    private Map<String,User> map;
    @Override
    public boolean connectDB(DbConfig dbConfig) {
        if(MemDB.map==null){
            MemDB.map = new HashMap<>();
        }
        this.map=MemDB.map;
        return true;
    }

    @Override
    public boolean addUser(User user) {
        if(map==null)return false;
        map.put(user.getName(),user);
        return true;
    }
    @Override
    public User getUser(String name){
        if(map==null)return null;
        return map.get(name);
    }

    @Override
    public void setLoginUser(String name) {
        this.loginUser=name;
    }

    @Override
    public boolean sendMessage(String username, String msg) {
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
        return true;
    }

    @Override
    public boolean subscribe(String username, String subname) {
        User user1 = getUser(username);
        User user2 = getUser(subname);
        if(user1==null){
            user1 = new User(username);
            addUser(user1);
        }
        if(user2==null){
            return false;
        }
        user1.addFollow(subname);
        return true;
    }

    @Override
    public Collection<Message> getMessages(String username) {
        User user = map.get(username);
        if(user==null){
            user = new User(username);
            map.put(username,user);
        }
        return user.getSortedMegList();
    }
}
