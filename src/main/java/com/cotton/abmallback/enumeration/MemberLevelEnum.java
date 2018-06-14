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

    private String name;

    MemberLevelEnum(String name){

        this.name = name;
    }
}
