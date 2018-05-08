package com.cotton.abmallback.model;

import com.cotton.base.model.BaseModel;
import java.util.Date;
import javax.persistence.*;

@Table(name = "goods_group")
public class GoodsGroup extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 商品分组编号
     */
    private String goods_group_no;

    /**
     * 商品分组名字
     */
    private String goods_group_name;

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
     * 获取商品分组编号
     *
     * @return goods_group_no - 商品分组编号
     */
    public String getGoods_group_no() {
        return goods_group_no;
    }

    /**
     * 设置商品分组编号
     *
     * @param goods_group_no 商品分组编号
     */
    public void setGoods_group_no(String goods_group_no) {
        this.goods_group_no = goods_group_no == null ? null : goods_group_no.trim();
    }

    /**
     * 获取商品分组名字
     *
     * @return goods_group_name - 商品分组名字
     */
    public String getGoods_group_name() {
        return goods_group_name;
    }

    /**
     * 设置商品分组名字
     *
     * @param goods_group_name 商品分组名字
     */
    public void setGoods_group_name(String goods_group_name) {
        this.goods_group_name = goods_group_name == null ? null : goods_group_name.trim();
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