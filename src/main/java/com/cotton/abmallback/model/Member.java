package com.cotton.abmallback.model;

import com.cotton.base.model.BaseModel;
import java.util.Date;
import javax.persistence.*;

public class Member extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private String display_name;

    /**
     * 头像
     */
    private String photo;

    /**
     * 微信号
     */
    private String wechat_no;

    /**
     * 微信名
     */
    private String wechat_name;

    /**
     * openId
     */
    private String openId;

    /**
     * 性别：male,female,unknown
     */
    private String sex;

    /**
     * 删除状态
     */
    private Boolean is_deleted;

    private Date gmt_create;

    private Date gmt_modify;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

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
    public String getDisplay_name() {
        return display_name;
    }

    /**
     * 设置显示名
     *
     * @param display_name 显示名
     */
    public void setDisplay_name(String display_name) {
        this.display_name = display_name == null ? null : display_name.trim();
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
    public String getWechat_no() {
        return wechat_no;
    }

    /**
     * 设置微信号
     *
     * @param wechat_no 微信号
     */
    public void setWechat_no(String wechat_no) {
        this.wechat_no = wechat_no == null ? null : wechat_no.trim();
    }

    /**
     * 获取微信名
     *
     * @return wechat_name - 微信名
     */
    public String getWechat_name() {
        return wechat_name;
    }

    /**
     * 设置微信名
     *
     * @param wechat_name 微信名
     */
    public void setWechat_name(String wechat_name) {
        this.wechat_name = wechat_name == null ? null : wechat_name.trim();
    }

    /**
     * 获取openId
     *
     * @return openId - openId
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 设置openId
     *
     * @param openId openId
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
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
     * 获取删除状态
     *
     * @return is_deleted - 删除状态
     */
    public Boolean getIs_deleted() {
        return is_deleted;
    }

    /**
     * 设置删除状态
     *
     * @param is_deleted 删除状态
     */
    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    /**
     * @return gmt_create
     */
    public Date getGmt_create() {
        return gmt_create;
    }

    /**
     * @param gmt_create
     */
    public void setGmt_create(Date gmt_create) {
        this.gmt_create = gmt_create;
    }

    /**
     * @return gmt_modify
     */
    public Date getGmt_modify() {
        return gmt_modify;
    }

    /**
     * @param gmt_modify
     */
    public void setGmt_modify(Date gmt_modify) {
        this.gmt_modify = gmt_modify;
    }
}