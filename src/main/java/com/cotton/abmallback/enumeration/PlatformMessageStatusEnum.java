package com.cotton.abmallback.enumeration;

/**
 * MessageTypeEnum
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/6/1
 */
public enum PlatformMessageStatusEnum {

    /**
     * 待发布
     */
    WAIT_PUBLISH("待发布"),
    /**
     * 发布中
     */
    PUBLISHED("发布中"),
    /**
     * 取消
     */
    CANCEL("取消");

    private String name;

    PlatformMessageStatusEnum(String name){

        this.name = name;
    }
}
