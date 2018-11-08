package com.example.websocket;

import com.alibaba.fastjson.JSON;
import com.example.websocket.entity.User;
import com.sun.deploy.net.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpRequest;


import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.beans.Expression;
import java.io.IOException;
import java.rmi.server.SocketSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private WebSocketServer socketServer;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @Resource
    private SessionRepository sessionRepository;

    @RequestMapping("hello")
    public String hello(HttpServletRequest request, HttpServletResponse response) throws IOException {
        socketServer.sendMessage("---haha");
        //获取所有session
        HttpSession session = request.getSession();

        return "你嗯嗯好！";
    }

    @RequestMapping("redis")
    public String redis(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("---------redis-----");
        //1.创建两个user对象
        List<User> list= new ArrayList<>();
        for(int i=0;i<3;i++){
            User user = new User();
            user.setId(i+"");
            user.setAge(i+10+"");
            user.setName("张-"+i);
            list.add(user);
        }
        // 具体使用
        redisTemplate.opsForList().leftPush("user:list", JSON.toJSONString(list));
        stringRedisTemplate.opsForValue().set("user:name", "张三");



        return "你嗯嗯好！";
    }

    @RequestMapping(value = "/first", method = RequestMethod.GET)
    public Map<String, Object> firstResp (HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        request.getSession().setAttribute("map", request.getRequestURL());
        map.put("map", request.getRequestURL());
        return map;
    }

    @RequestMapping(value = "/sessions", method = RequestMethod.GET)
    public Object sessions (HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", request.getSession().getId());
        map.put("message", request.getSession().getAttribute("map"));
        return map;
    }

    @RequestMapping(value = "/sessionsByUser/{sessionId}", method = RequestMethod.GET)
    public Object sessionsByUser (HttpServletRequest request,@PathVariable String sessionId){
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", request.getSession().getId());
        map.put("message", request.getSession().getAttribute("map"));
        Session session = sessionRepository.findById(sessionId);
        log.info("---根据sesionId获取的session"+session+"---id="+session.getId());
        return map;
    }
}
