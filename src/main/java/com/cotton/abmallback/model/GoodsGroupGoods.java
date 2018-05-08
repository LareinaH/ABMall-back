package com.cotton.abmallback.model;

import com.cotton.base.model.BaseModel;
import java.util.Date;
import javax.persistence.*;

@Table(name = "goods_group_goods")
public class GoodsGroupGoods extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 商品分组id
     */
    private String goods_group_id;

    /**
     * 商品id
     */
    private String goods_id;

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
     * 获取商品分组id
     *
     * @return goods_group_id - 商品分组id
     */
    public String getGoods_group_id() {
        return goods_group_id;
    }

    /**
     * 设置商品分组id
     *
     * @param goods_group_id 商品分组id
     */
    public void setGoods_group_id(String goods_group_id) {
        this.goods_group_id = goods_group_id == null ? null : goods_group_id.trim();
    }

    /**
     * 获取商品id
     *
     * @return goods_id - 商品id
     */
    public String getGoods_id() {
        return goods_id;
    }

    /**
     * 设置商品id
     *
     * @param goods_id 商品id
     */
    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id == null ? null : goods_id.trim();
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