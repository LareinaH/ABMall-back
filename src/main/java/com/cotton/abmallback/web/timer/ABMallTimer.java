package com.cotton.abmallback.web.timer;

import com.cotton.abmallback.service.OrdersService;
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

    private final OrdersService ordersService;

    public ABMallTimer(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @Scheduled(cron = "0 0 12 * * ?" )
    //@Scheduled(cron = "0 47 10 ? * *")
    public void reportCurrentTime() {


    }

    /**
     * 定时取消已经超时的订单
     */
    @Scheduled(cron = "0 */5 * * * ?" )
    public void systemCancelOrder() {

        ordersService.systemCancelOrder();
    }

    /**
     * 定时确认收货
     */
    @Scheduled(cron = "0 */30 * * * ?" )
    public void systemConfirmedOrder() {

        ordersService.systemConfirmedOrder();
    }


}
