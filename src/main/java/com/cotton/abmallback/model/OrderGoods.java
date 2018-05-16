package com.cotton.abmallback.model;

import com.cotton.base.model.BaseModel;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "order_goods")
public class OrderGoods extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 订单编号
     */
    private String order_no;

    /**
     * 商品编号
     */
    private Long good_no;

    /**
     * 商品名称
     */
    private String good_name;

    /**
     * 商品缩略图
     */
    private String good_thum;

    /**
     * 商品价格
     */
    private BigDecimal good_price;

    /**
     * 商品数量
     */
    private Integer good_num;

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
     * 获取订单编号
     *
     * @return order_no - 订单编号
     */
    public String getOrder_no() {
        return order_no;
    }

    /**
     * 设置订单编号
     *
     * @param order_no 订单编号
     */
    public void setOrder_no(String order_no) {
        this.order_no = order_no == null ? null : order_no.trim();
    }

    /**
     * 获取商品编号
     *
     * @return good_no - 商品编号
     */
    public Long getGood_no() {
        return good_no;
    }

    /**
     * 设置商品编号
     *
     * @param good_no 商品编号
     */
    public void setGood_no(Long good_no) {
        this.good_no = good_no;
    }

    /**
     * 获取商品名称
     *
     * @return good_name - 商品名称
     */
    public String getGood_name() {
        return good_name;
    }

    /**
     * 设置商品名称
     *
     * @param good_name 商品名称
     */
    public void setGood_name(String good_name) {
        this.good_name = good_name == null ? null : good_name.trim();
    }

    /**
     * 获取商品缩略图
     *
     * @return good_thum - 商品缩略图
     */
    public String getGood_thum() {
        return good_thum;
    }

    /**
     * 设置商品缩略图
     *
     * @param good_thum 商品缩略图
     */
    public void setGood_thum(String good_thum) {
        this.good_thum = good_thum == null ? null : good_thum.trim();
    }

    /**
     * 获取商品价格
     *
     * @return good_price - 商品价格
     */
    public BigDecimal getGood_price() {
        return good_price;
    }

    /**
     * 设置商品价格
     *
     * @param good_price 商品价格
     */
    public void setGood_price(BigDecimal good_price) {
        this.good_price = good_price;
    }

    /**
     * 获取商品数量
     *
     * @return good_num - 商品数量
     */
    public Integer getGood_num() {
        return good_num;
    }

    /**
     * 设置商品数量
     *
     * @param good_num 商品数量
     */
    public void setGood_num(Integer good_num) {
        this.good_num = good_num;
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