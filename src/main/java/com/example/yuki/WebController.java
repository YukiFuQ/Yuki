package com.example.yuki;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
public class WebController {
    DB db = new DB();
    @RequestMapping("/")
    public String showLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public void login(@RequestParam("name") String name){
        DB.name=name;
        System.out.println("Login name: "+name);
    }

    @RequestMapping("/sendMsg")
    @ResponseBody
    public List<Message> SendMessage(@RequestParam("username")String username, @RequestParam("msg")String msg){
        db.sendMessage(username,msg);
        return db.getWall(username);
    }

    @RequestMapping("/subscibe")
    @ResponseBody
    public Set<String> subscibe(@RequestParam("username")String username, @RequestParam("subname")String subname){
        System.out.println("subscribe");
        return db.sub(username,subname);
    }

    @RequestMapping("/subList")
    @ResponseBody
    public Set<String> getSubList(@RequestParam("username")String username){
        return db.getSubList(username);
    }

    @RequestMapping("/getWall")
    @ResponseBody
    public List<Message> getWallList(@RequestParam("username")String username){
        return db.getWall(username);
    }
}
