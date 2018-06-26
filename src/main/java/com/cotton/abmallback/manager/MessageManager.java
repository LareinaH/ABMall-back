package com.cotton.abmallback.manager;

/**
 * MessageManager
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/6/26
 */
public interface MessageManager {


    /**
     * 发送平台消息
     * @param systemMessageId
     */
    void sendSystemNotice(long systemMessageId);

    /**
     * 发送分享奖励消息
     * @param memberId
     */
    void sendShareAward(long memberId);

    /**
     * 发送高管奖励消息
     * @param memberId
     */
    void sendExecutiveAward(long memberId);

    /**
     * 发送升级消息
     * @param memberId
     */
    void sendPromotionAward(long memberId);


}
