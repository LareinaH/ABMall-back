package com.cotton.abmallback.model;

import com.cotton.base.model.BaseModel;
import javax.persistence.*;

@Table(name = "ads")
public class Ads extends BaseModel {
    /**
     * 广告类型（位置）
     */
    @Column(name = "ad_type")
    private String adType;

    /**
     * 邀请码码对应的会员等级,当type=INVITING_CODE_BACKGROUND时有效
     */
    private String level;

    /**
     * 图片地址
     */
    @Column(name = "ad_url")
    private String adUrl;

    /**
     * 链接地址
     */
    @Column(name = "link_url")
    private String linkUrl;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 获取广告类型（位置）
     *
     * @return ad_type - 广告类型（位置）
     */
    public String getAdType() {
        return adType;
    }

    /**
     * 设置广告类型（位置）
     *
     * @param adType 广告类型（位置）
     */
    public void setAdType(String adType) {
        this.adType = adType == null ? null : adType.trim();
    }

    /**
     * 获取邀请码码对应的会员等级,当type=INVITING_CODE_BACKGROUND时有效
     *
     * @return level - 邀请码码对应的会员等级,当type=INVITING_CODE_BACKGROUND时有效
     */
    public String getLevel() {
        return level;
    }

    /**
     * 设置邀请码码对应的会员等级,当type=INVITING_CODE_BACKGROUND时有效
     *
     * @param level 邀请码码对应的会员等级,当type=INVITING_CODE_BACKGROUND时有效
     */
    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    /**
     * 获取图片地址
     *
     * @return ad_url - 图片地址
     */
    public String getAdUrl() {
        return adUrl;
    }

    /**
     * 设置图片地址
     *
     * @param adUrl 图片地址
     */
    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl == null ? null : adUrl.trim();
    }

    /**
     * 获取链接地址
     *
     * @return link_url - 链接地址
     */
    public String getLinkUrl() {
        return linkUrl;
    }

    /**
     * 设置链接地址
     *
     * @param linkUrl 链接地址
     */
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    /**
     * 获取排序
     *
     * @return sort - 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }
}