package com.example.websocket;

import org.springframework.util.StringUtils;

import java.util.Map;

public class CommonUtils {
    /**
     * 从map中根据key取值
     * @param map
     * @param key
     * @return
     */
    public static String getValue(Map<String,Object> map, String key){
        if(map!=null){
            Object obj = map.get(key);
            if(obj!=null){
                String value = String.valueOf(obj);
                return value;
            }
        }
        return null;
    }
}
