package com.example.websocket.job;

import com.example.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;

//@Component
@Configurable
@EnableScheduling
public class task {

 @Autowired
 private WebSocketServer  socketServer;

        @Scheduled(fixedRate = 1000 * 30)
        public void reportCurrentTime(){
            Date date = new Date();
            System.out.println ("Scheduling Tasks Examples: The time is now " + dateFormat ().format (date));
        }

        //每1分钟执行一次
        @Scheduled(cron = "0 */1 *  * * * ")
        public void reportCurrentByCron(){
            Date date = new Date();
            System.out.println ("--------Scheduling Tasks Examples By Cron:"+ dateFormat ().format (date));


        }

        private SimpleDateFormat dateFormat(){
            return new SimpleDateFormat ("HH:mm:ss");
        }


}
