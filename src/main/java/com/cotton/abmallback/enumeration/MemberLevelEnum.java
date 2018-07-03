package com.cotton.abmallback.enumeration;

/**
 * MemberLevelEnum
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/6/14
 */
public enum MemberLevelEnum {

    /**
     * 小白
     */
    WHITE("小白"),

    /**
     * 代理人
     */
    AGENT("代理人"),

    /**
     * V1
     */
    V1("V1"),

    /**
     * V2
     */
    V2("V2"),

    /**
     * V3
     */
    V3("V3");

    private String displayName;

    MemberLevelEnum(String displayName){

        this.displayName = displayName;
    }

    public static MemberLevelEnum getNextLevel(MemberLevelEnum levelEnum){
        switch (levelEnum){
            case WHITE:
                return MemberLevelEnum.AGENT;
            case AGENT:
                return MemberLevelEnum.V1;
            case V1:
                return MemberLevelEnum.V2;
            case V2:
                return MemberLevelEnum.V3;
            default:
                return null;
        }
    }
}
