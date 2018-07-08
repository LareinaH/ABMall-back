package com.cotton.abmallback.web.timer;

import com.cotton.abmallback.manager.MessageManager;
import com.cotton.abmallback.service.CashPickUpService;
import com.cotton.abmallback.service.OrdersService;
import com.cotton.abmallback.service.ShopActivitiesService;
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

    private final ShopActivitiesService shopActivitiesService;

    private final MessageManager messageManager;


    public ABMallTimer(OrdersService ordersService, CashPickUpService cashPickUpService, ShopActivitiesService shopActivitiesService, MessageManager messageManager) {
        this.ordersService = ordersService;
        this.cashPickUpService = cashPickUpService;
        this.shopActivitiesService = shopActivitiesService;
        this.messageManager = messageManager;
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

        //cashPickUpService.sendRedpack();

    }


    /**
     * 发送平台消息
     */

    @Scheduled(cron = "0 */5 * * * ?" )
    public void sendPlatformMessage() {

        messageManager.sendSystemNotice();
    }


    /**
     * 开始活动
     */
    @Scheduled(cron = "0 */1 * * * ?" )
    public void beginActivities() {

        shopActivitiesService.beginActivities();
    }

    /**
     * 开始活动
     */
    @Scheduled(cron = "0 */1 * * * ?" )
    public void finishActivities() {

        shopActivitiesService.finishActivities();
    }
}
