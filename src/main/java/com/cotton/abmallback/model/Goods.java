package com.cotton.abmallback.model;

import com.cotton.base.model.BaseModel;
import java.util.Date;
import javax.persistence.*;

public class Goods extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 商品编号
     */
    private String goods_no;

    /**
     * 商品名字
     */
    private String goods_name;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 缩略图
     */
    private String thums;

    /**
     * 商品详情图
     */
    private String images;

    /**
     * 商品单位
     */
    private String unit;

    /**
     * 原价
     */
    private String price;

    /**
     * 分组id
     */
    private Long group_id;

    /**
     * 是否上架 0:不上架 1:上架
     */
    private Boolean is_on_sell;

    /**
     * 是否在回收站
     */
    private Boolean is_recycled;

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
     * 关键字
     */
    private String keywords;

    /**
     * 商品描述
     */
    private String description;

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
     * 获取商品编号
     *
     * @return goods_no - 商品编号
     */
    public String getGoods_no() {
        return goods_no;
    }

    /**
     * 设置商品编号
     *
     * @param goods_no 商品编号
     */
    public void setGoods_no(String goods_no) {
        this.goods_no = goods_no == null ? null : goods_no.trim();
    }

    /**
     * 获取商品名字
     *
     * @return goods_name - 商品名字
     */
    public String getGoods_name() {
        return goods_name;
    }

    /**
     * 设置商品名字
     *
     * @param goods_name 商品名字
     */
    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name == null ? null : goods_name.trim();
    }

    /**
     * 获取库存
     *
     * @return stock - 库存
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * 设置库存
     *
     * @param stock 库存
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * 获取缩略图
     *
     * @return thums - 缩略图
     */
    public String getThums() {
        return thums;
    }

    /**
     * 设置缩略图
     *
     * @param thums 缩略图
     */
    public void setThums(String thums) {
        this.thums = thums == null ? null : thums.trim();
    }

    /**
     * 获取商品详情图
     *
     * @return images - 商品详情图
     */
    public String getImages() {
        return images;
    }

    /**
     * 设置商品详情图
     *
     * @param images 商品详情图
     */
    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    /**
     * 获取商品单位
     *
     * @return unit - 商品单位
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 设置商品单位
     *
     * @param unit 商品单位
     */
    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    /**
     * 获取原价
     *
     * @return price - 原价
     */
    public String getPrice() {
        return price;
    }

    /**
     * 设置原价
     *
     * @param price 原价
     */
    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    /**
     * 获取分组id
     *
     * @return group_id - 分组id
     */
    public Long getGroup_id() {
        return group_id;
    }

    /**
     * 设置分组id
     *
     * @param group_id 分组id
     */
    public void setGroup_id(Long group_id) {
        this.group_id = group_id;
    }

    /**
     * 获取是否上架 0:不上架 1:上架
     *
     * @return is_on_sell - 是否上架 0:不上架 1:上架
     */
    public Boolean getIs_on_sell() {
        return is_on_sell;
    }

    /**
     * 设置是否上架 0:不上架 1:上架
     *
     * @param is_on_sell 是否上架 0:不上架 1:上架
     */
    public void setIs_on_sell(Boolean is_on_sell) {
        this.is_on_sell = is_on_sell;
    }

    /**
     * 获取是否在回收站
     *
     * @return is_recycled - 是否在回收站
     */
    public Boolean getIs_recycled() {
        return is_recycled;
    }

    /**
     * 设置是否在回收站
     *
     * @param is_recycled 是否在回收站
     */
    public void setIs_recycled(Boolean is_recycled) {
        this.is_recycled = is_recycled;
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

    /**
     * 获取关键字
     *
     * @return keywords - 关键字
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * 设置关键字
     *
     * @param keywords 关键字
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    /**
     * 获取商品描述
     *
     * @return description - 商品描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置商品描述
     *
     * @param description 商品描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}