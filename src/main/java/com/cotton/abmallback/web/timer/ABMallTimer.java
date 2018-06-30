package com.cotton.abmallback.web.timer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * ABMallTimmer
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/6/26
 */

@Component
public class ABMallTimer {

    @Scheduled(cron = "0 0 12 * * ?" )
    //@Scheduled(cron = "0 47 10 ? * *")
    public void reportCurrentTime() {


    }


    @Scheduled(cron = "0 0 12 * * ?" )
    public void systemCancelOrder() {


    }


}
