package com.cotton.abmallback.enumeration;

/**
 * OrderStatusEnum
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/5/22
 */
public enum OrderStatusEnum {

    INVITING_CODE("待付款"),
    INVITING_CODE_BACKGROUND("待发货"),
    INVITING_CODE_BAC("待收货"),
    TEAM_SYSTEM("确认收货"),
    TEAM_SYST("补货"),
    CANCLE("取消");

    private String name;

    OrderStatusEnum(String name){

        this.name = name;
    }
}
