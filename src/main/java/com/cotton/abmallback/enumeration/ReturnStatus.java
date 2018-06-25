package com.cotton.abmallback.enumeration;

/**
 * ReturnStatus
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/6/25
 */
public enum ReturnStatus {

    /**
     * 正常
     */
    NORMAL("正常"),
    RETURN("退货中"),
    REPLENISHMENT("补货");

    private String displayName;

    ReturnStatus(String displayName){

        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
