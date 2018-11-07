package com.cotton.abmallback.web.timer;

import com.cotton.abmallback.manager.MessageManager;
import com.cotton.abmallback.manager.PromotionManager;
import com.cotton.abmallback.service.CashPickUpService;
import com.cotton.abmallback.service.MemberService;
import com.cotton.abmallback.service.OrdersService;
import com.cotton.abmallback.service.ShopActivitiesService;
import com.dingtalk.chatbot.DingtalkChatbotClient;
import com.dingtalk.chatbot.SendResult;
import com.dingtalk.chatbot.demo.TestConfig;
import com.dingtalk.chatbot.message.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * ABMallTimer
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

    private final PromotionManager promotionManager;

    private final MessageManager messageManager;

    private final MemberService memberService;

    private Logger logger = LoggerFactory.getLogger(ABMallTimer.class);

    private DingtalkChatbotClient client = new DingtalkChatbotClient();

    private String CHATBOT_WEBHOOK = "https://oapi.dingtalk.com/robot/send?access_token=963b6104ea2d0c8c304d126a2b3996f1e6af5ff3bc3f2ca50a0fc4058df2724e";



    public ABMallTimer(OrdersService ordersService, CashPickUpService cashPickUpService, ShopActivitiesService shopActivitiesService, PromotionManager promotionManager, MessageManager messageManager, MemberService memberService) {
        this.ordersService = ordersService;
        this.cashPickUpService = cashPickUpService;
        this.shopActivitiesService = shopActivitiesService;
        this.promotionManager = promotionManager;
        this.messageManager = messageManager;
        this.memberService = memberService;
    }


    /**
     * 定时取消已经超时的订单
     */
    @Scheduled(cron = "0 */5 * * * ?" )
    public void systemCancelOrder() {

        logger.info("定时取消已经超时的订单定时器benin");

        ordersService.systemCancelOrder();
    }

    /**
     * 定时确认收货
     */
    @Scheduled(cron = "0 */30 * * * ?" )
    public void systemConfirmedOrder() {

        logger.info("定时确认收货定时器benin");
        ordersService.systemConfirmedOrder();
    }

    /**
     * 查看红包发送状态
     */
    @Scheduled(cron = "0 */1 * * * ?" )
    public void checkRedpack() {

        logger.info("查看红包发送状态");
        cashPickUpService.checkRedpack();

    }

    /**
     * 发送平台消息
     */
    @Scheduled(cron = "0 */5 * * * ?" )
    public void sendPlatformMessage() {

        logger.info("发送平台消息定时器benin");
        messageManager.sendSystemNotice();
    }

    /**
     * 关闭已经结束的活动
     */
    @Scheduled(cron = "0 */1 * * * ?" )
    public void finishActivities() {

        logger.info("关闭已经结束的活动定时器benin");
        shopActivitiesService.finishActivities();
    }

    /**
     * 校验v2用户能否升级v3
     */
    @Scheduled(cron = "0 */1 * * * ?" )
    public void promotMember() {

        logger.info("校验v2用户能否升级v3");
        promotionManager.memberPromotionAll();

    }


    /**
     * 解除绑定关系
     */
    @Scheduled(cron = "0 * */1 * * ?" )
    public void unbindingRelationship() {

       // logger.info("定时查询解除绑定关系");
       // memberService.unbindingRelationship();

    }



    @Scheduled(cron = "0 0 16 ? * MON-FRI" )
    public void tishichifan() {
        TextMessage message = new TextMessage("开始订餐了，订明天午饭的，在群里 +1  不+1 明天中午没有饭吃哦！！");
        message.setIsAtAll(true);

        try {
            client.send(CHATBOT_WEBHOOK, message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0 0 20 ? * MON-FRI" )
    public void jieshuchifan() {
        TextMessage message = new TextMessage("订餐时间结束了哦，+1的小哥哥、小姐姐，明天午餐看不到你了哦！！");
        message.setIsAtAll(true);

        try {
            client.send(CHATBOT_WEBHOOK, message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
