package com.cotton.abmallback.enumeration;

/**
 * OrderStatusEnum
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/5/22
 */
public enum OrderStatusEnum {

    /**
     * 待付款
     */
    WAIT_BUYER_PAY("待付款"),
    WAIT_DELIVER("待发货"),
    WAIT_CONFIRM("待收货"),
    CONFIRMED("确认收货"),
    REPLENISHMENT("补货"),
    CANCEL("取消");

    private String displayName;

    OrderStatusEnum(String displayName){

        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
