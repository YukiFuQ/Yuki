package com.example.yuki.Imp.redisImp;

import com.example.yuki.Interface.Handler;
import com.example.yuki.entity.DbConfig;
import com.example.yuki.entity.Message;
import com.example.yuki.entity.User;

import redis.clients.jedis.Jedis;

import java.util.Collection;

public class redisHandler implements Handler {
    Jedis jedis;
    private String loginUser = "";
    @Override
    public boolean connectDB(DbConfig dbConfig) {
        if(jedis==null){
            System.out.println(dbConfig.getIp()+" "+Integer.parseInt(dbConfig.getPort()));
            jedis = new Jedis(dbConfig.getIp(),Integer.parseInt(dbConfig.getPort()));
        }
        System.out.println(jedis.ping());
        if("PONG".equals(jedis.ping())){
            return true;
        }
        return false;
    }

    @Override
    public boolean addUser(User user) {
        jedis.set(user.getName().getBytes(), ObjectAndByte.toByteArray(user));
        return true;
    }

    @Override
    public User getUser(String name) {
        byte[] b = jedis.get(name.getBytes());
        if(b==null)return null;
        return (User)ObjectAndByte.toObject(b);
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
        addUser(user1);
        return true;
    }

    @Override
    public Collection<Message> getMessages(String username) {
        byte[] b = jedis.get(username.getBytes());
        if(b==null){
            User user = new User(username);
            jedis.set(username.getBytes(), ObjectAndByte.toByteArray(user));
            return null;
        }
        User user = (User)ObjectAndByte.toObject(b);
        return user.getSortedMegList();
    }
}
