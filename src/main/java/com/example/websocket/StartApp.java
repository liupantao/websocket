package com.example.websocket;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StartApp {
    @RequestMapping(value="/pushVideoListToWeb",method=RequestMethod.POST,consumes = "application/json")
    public @ResponseBody
    Map<String,Object> pushVideoListToWeb(@RequestBody Map<String,Object> param) {
        Map<String,Object> result =new HashMap<String,Object>();

        try {
            WebSocketServer.sendInfo("有新客户呼入,sltAccountId:"+CommonUtils.getValue(param, "sltAccountId"));
            result.put("operationResult", true);
        }catch (IOException e) {
            result.put("operationResult", true);
        }
        return result;
    }

}
