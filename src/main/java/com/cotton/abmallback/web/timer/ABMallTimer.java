package com.cotton.abmallback.web.timer;

import com.cotton.abmallback.service.CashPickUpService;
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

    private final CashPickUpService cashPickUpService;


    public ABMallTimer(OrdersService ordersService, CashPickUpService cashPickUpService) {
        this.ordersService = ordersService;
        this.cashPickUpService = cashPickUpService;
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

    /**
     * 提现到账（通过发红包的方式）
     */
    @Scheduled(cron = "0 */30 * * * ?" )
    public void sendRedpack() {

        cashPickUpService.sendRedpack();

    }
}
