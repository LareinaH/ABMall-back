package com.cotton.abmallback.model;

import com.cotton.base.model.BaseModel;
import javax.persistence.*;

public class Member extends BaseModel {
    /**
     * uuid
     */
    private String uuid;

    /**
     * 会员名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 显示名
     */
    @Column(name = "display_name")
    private String displayName;

    /**
     * 手机号码
     */
    @Column(name = "phone_num")
    private String phoneNum;

    /**
     * 头像
     */
    private String photo;

    /**
     * 微信号
     */
    @Column(name = "wechat_no")
    private String wechatNo;

    /**
     * 微信名
     */
    @Column(name = "wechat_name")
    private String wechatName;

    /**
     * openId
     */
    @Column(name = "openId")
    private String openid;

    /**
     * 性别：male,female,unknown
     */
    private String sex;

    /**
     * 获取uuid
     *
     * @return uuid - uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置uuid
     *
     * @param uuid uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * 获取会员名
     *
     * @return name - 会员名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置会员名
     *
     * @param name 会员名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取显示名
     *
     * @return display_name - 显示名
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * 设置显示名
     *
     * @param displayName 显示名
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }

    /**
     * 获取手机号码
     *
     * @return phone_num - 手机号码
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * 设置手机号码
     *
     * @param phoneNum 手机号码
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    /**
     * 获取头像
     *
     * @return photo - 头像
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * 设置头像
     *
     * @param photo 头像
     */
    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    /**
     * 获取微信号
     *
     * @return wechat_no - 微信号
     */
    public String getWechatNo() {
        return wechatNo;
    }

    /**
     * 设置微信号
     *
     * @param wechatNo 微信号
     */
    public void setWechatNo(String wechatNo) {
        this.wechatNo = wechatNo == null ? null : wechatNo.trim();
    }

    /**
     * 获取微信名
     *
     * @return wechat_name - 微信名
     */
    public String getWechatName() {
        return wechatName;
    }

    /**
     * 设置微信名
     *
     * @param wechatName 微信名
     */
    public void setWechatName(String wechatName) {
        this.wechatName = wechatName == null ? null : wechatName.trim();
    }

    /**
     * 获取openId
     *
     * @return openId - openId
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 设置openId
     *
     * @param openid openId
     */
    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    /**
     * 获取性别：male,female,unknown
     *
     * @return sex - 性别：male,female,unknown
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别：male,female,unknown
     *
     * @param sex 性别：male,female,unknown
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }
}