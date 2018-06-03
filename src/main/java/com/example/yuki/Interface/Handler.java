package com.example.yuki.Interface;

import com.example.yuki.entity.DbConfig;
import com.example.yuki.entity.Message;
import com.example.yuki.entity.User;

import java.util.Collection;

public interface Handler {

    public boolean connectDB(DbConfig dbConfig);
    public boolean addUser(User user);
    public User getUser(String name);
    public void setLoginUser(String name);
    public boolean sendMessage(String username,String msg);
    public boolean subscribe(String username,String username1);
    public Collection<Message> getMessages(String username);
}
