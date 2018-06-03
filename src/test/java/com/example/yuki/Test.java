package com.example.yuki;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.yuki.Imp.redisImp.ObjectAndByte;
import com.example.yuki.entity.User;
import redis.clients.jedis.Jedis;

import javax.xml.bind.SchemaOutputResolver;

public class Test {
    public static void main(String[] args) {
        User user = new User("test");
        byte[] b = ObjectAndByte.toByteArray(user);
        User s = (User)ObjectAndByte.toObject(b);
        System.out.println(s.getName());
    }
}
