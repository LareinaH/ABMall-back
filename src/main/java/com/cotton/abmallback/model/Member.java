package com.cotton.abmallback.model;

import com.cotton.base.model.BaseModel;
import java.math.BigDecimal;
import javax.persistence.*;
@Table(name = "member")
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
     * 会员等级
     */
    private String level;

    /**
     * 直推人数
     */
    @Column(name = "refer_total_count")
    private Integer referTotalCount;

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
     * 总消费
     */
    @Column(name = "money_total_spend")
    private BigDecimal moneyTotalSpend;

    /**
     * 总赚取
     */
    @Column(name = "money_total_earn")
    private BigDecimal moneyTotalEarn;

    /**
     * 总提取
     */
    @Column(name = "money_total_take")
    private BigDecimal moneyTotalTake;

    /**
     * 锁定费用
     */
    @Column(name = "money_lock")
    private BigDecimal moneyLock;

    /**
     * 引荐人id
     */
    @Column(name = "referrer_id")
    private String referrerId;

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
     * 获取会员等级
     *
     * @return level - 会员等级
     */
    public String getLevel() {
        return level;
    }

    /**
     * 设置会员等级
     *
     * @param level 会员等级
     */
    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    /**
     * 获取直推人数
     *
     * @return refer_total_count - 直推人数
     */
    public Integer getReferTotalCount() {
        return referTotalCount;
    }

    /**
     * 设置直推人数
     *
     * @param referTotalCount 直推人数
     */
    public void setReferTotalCount(Integer referTotalCount) {
        this.referTotalCount = referTotalCount;
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

    /**
     * 获取总消费
     *
     * @return money_total_spend - 总消费
     */
    public BigDecimal getMoneyTotalSpend() {
        return moneyTotalSpend;
    }

    /**
     * 设置总消费
     *
     * @param moneyTotalSpend 总消费
     */
    public void setMoneyTotalSpend(BigDecimal moneyTotalSpend) {
        this.moneyTotalSpend = moneyTotalSpend;
    }

    /**
     * 获取总赚取
     *
     * @return money_total_earn - 总赚取
     */
    public BigDecimal getMoneyTotalEarn() {
        return moneyTotalEarn;
    }

    /**
     * 设置总赚取
     *
     * @param moneyTotalEarn 总赚取
     */
    public void setMoneyTotalEarn(BigDecimal moneyTotalEarn) {
        this.moneyTotalEarn = moneyTotalEarn;
    }

    /**
     * 获取总提取
     *
     * @return money_total_take - 总提取
     */
    public BigDecimal getMoneyTotalTake() {
        return moneyTotalTake;
    }

    /**
     * 设置总提取
     *
     * @param moneyTotalTake 总提取
     */
    public void setMoneyTotalTake(BigDecimal moneyTotalTake) {
        this.moneyTotalTake = moneyTotalTake;
    }

    /**
     * 获取锁定费用
     *
     * @return money_lock - 锁定费用
     */
    public BigDecimal getMoneyLock() {
        return moneyLock;
    }

    /**
     * 设置锁定费用
     *
     * @param moneyLock 锁定费用
     */
    public void setMoneyLock(BigDecimal moneyLock) {
        this.moneyLock = moneyLock;
    }

    /**
     * 获取引荐人id
     *
     * @return referrer_id - 引荐人id
     */
    public String getReferrerId() {
        return referrerId;
    }

    /**
     * 设置引荐人id
     *
     * @param referrerId 引荐人id
     */
    public void setReferrerId(String referrerId) {
        this.referrerId = referrerId == null ? null : referrerId.trim();
    }
}