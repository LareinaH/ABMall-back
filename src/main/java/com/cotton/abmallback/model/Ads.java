package com.cotton.abmallback.model;

import com.cotton.base.model.BaseModel;
import java.util.Date;
import javax.persistence.*;

public class Ads extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 广告类型（位置）
     */
    private String ad_type;

    /**
     * 图片地址
     */
    private String ad_url;

    /**
     * 链接地址
     */
    private String link_url;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 删除状态
     */
    private Boolean is_deleted;

    /**
     * 创建时间
     */
    private Date createAt;

    /**
     * 修改时间
     */
    private Date modifyAt;

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
     * 获取广告类型（位置）
     *
     * @return ad_type - 广告类型（位置）
     */
    public String getAd_type() {
        return ad_type;
    }

    /**
     * 设置广告类型（位置）
     *
     * @param ad_type 广告类型（位置）
     */
    public void setAd_type(String ad_type) {
        this.ad_type = ad_type == null ? null : ad_type.trim();
    }

    /**
     * 获取图片地址
     *
     * @return ad_url - 图片地址
     */
    public String getAd_url() {
        return ad_url;
    }

    /**
     * 设置图片地址
     *
     * @param ad_url 图片地址
     */
    public void setAd_url(String ad_url) {
        this.ad_url = ad_url == null ? null : ad_url.trim();
    }

    /**
     * 获取链接地址
     *
     * @return link_url - 链接地址
     */
    public String getLink_url() {
        return link_url;
    }

    /**
     * 设置链接地址
     *
     * @param link_url 链接地址
     */
    public void setLink_url(String link_url) {
        this.link_url = link_url == null ? null : link_url.trim();
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
     * 获取创建时间
     *
     * @return createAt - 创建时间
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * 设置创建时间
     *
     * @param createAt 创建时间
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * 获取修改时间
     *
     * @return modifyAt - 修改时间
     */
    public Date getModifyAt() {
        return modifyAt;
    }

    /**
     * 设置修改时间
     *
     * @param modifyAt 修改时间
     */
    public void setModifyAt(Date modifyAt) {
        this.modifyAt = modifyAt;
    }
}