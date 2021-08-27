package com.yainnixdev.springleaf.server.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/server")
public class ServerDataController {
    // 20 minutes of real life is a day in a game
    private final static  int SECONDS_IN_GAME_DAY = 20*60;
    @GetMapping("/get_time")
    public int getTime(){
        LocalTime localTime = java.time.LocalTime.now();
        int preparedMinutes =  localTime.getMinute()%20;
        int totalSeconds = localTime.getSecond() + preparedMinutes * 60;
        return ((int) TimeUnit.DAYS.toSeconds(1)*totalSeconds /SECONDS_IN_GAME_DAY);
    }
}
