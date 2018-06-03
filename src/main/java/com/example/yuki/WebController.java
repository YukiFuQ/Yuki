package com.example.yuki;

import com.example.yuki.Imp.defaultImp.defaultHandler;
import com.example.yuki.Imp.redisImp.redisHandler;
import com.example.yuki.Interface.Handler;
import com.example.yuki.entity.DbConfig;
import com.example.yuki.entity.Message;
import com.example.yuki.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;

@Controller
public class WebController {
    DB db = new DB();
    Handler handler;
    DbConfig dbConfig;
    @Value("${dbtype}")
    private String dbtype;
    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private String port;


    @RequestMapping("/")
    public String showLogin(){
        System.out.println(dbtype +host+port);
        switch (dbtype) {
            case "default":
                System.out.println("Memory as DB");
                handler = new defaultHandler();
                break;
            case "redis":
                System.out.println("Redis as DB");
                dbConfig = new DbConfig();
                dbConfig.setIp(host);
                dbConfig.setPort(port);
                handler = new redisHandler();
                break;
        }
        return "login";
    }

    @RequestMapping("/login")
    public void login(@RequestParam("name") String name){
        if(handler==null){
            handler=new defaultHandler();
        }
        handler.setLoginUser(name);
        handler.connectDB(dbConfig);
        System.out.println("Login name: "+name);
    }

    @RequestMapping("/sendMsg")
    @ResponseBody
    public Collection<Message> SendMessage(@RequestParam("username")String username, @RequestParam("msg")String msg){
        handler.sendMessage(username,msg);
        return handler.getMessages(username);
    }

    @RequestMapping("/subscibe")
    @ResponseBody
    public Set<String> subscibe(@RequestParam("username")String username, @RequestParam("subname")String subname){
        System.out.println("subscribe");
        handler.subscribe(username,subname);
        return handler.getUser(username).getFollowList();
    }

    @RequestMapping("/subList")
    @ResponseBody
    public Collection<String> getSubList(@RequestParam("username")String username){
        User user = handler.getUser(username);
        if(user==null)
            return null;
        return user.getFollowList();
    }

    @RequestMapping("/getWall")
    @ResponseBody
    public Collection<Message> getWallList(@RequestParam("username")String username){
        return handler.getMessages(username);
    }
}
