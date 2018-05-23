package com.cotton.abmallback.model;

import com.cotton.base.model.BaseModel;
import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "order_goods")
public class OrderGoods extends BaseModel {
    /**
     * 订单编号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 商品编号
     */
    @Column(name = "good_no")
    private Long goodNo;

    /**
     * 商品名称
     */
    @Column(name = "good_name")
    private String goodName;

    /**
     * 商品缩略图
     */
    @Column(name = "good_thum")
    private String goodThum;

    /**
     * 商品价格
     */
    @Column(name = "good_price")
    private BigDecimal goodPrice;

    /**
     * 商品数量
     */
    @Column(name = "good_num")
    private Integer goodNum;

    /**
     * 获取订单编号
     *
     * @return order_no - 订单编号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置订单编号
     *
     * @param orderNo 订单编号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     * 获取商品编号
     *
     * @return good_no - 商品编号
     */
    public Long getGoodNo() {
        return goodNo;
    }

    /**
     * 设置商品编号
     *
     * @param goodNo 商品编号
     */
    public void setGoodNo(Long goodNo) {
        this.goodNo = goodNo;
    }

    /**
     * 获取商品名称
     *
     * @return good_name - 商品名称
     */
    public String getGoodName() {
        return goodName;
    }

    /**
     * 设置商品名称
     *
     * @param goodName 商品名称
     */
    public void setGoodName(String goodName) {
        this.goodName = goodName == null ? null : goodName.trim();
    }

    /**
     * 获取商品缩略图
     *
     * @return good_thum - 商品缩略图
     */
    public String getGoodThum() {
        return goodThum;
    }

    /**
     * 设置商品缩略图
     *
     * @param goodThum 商品缩略图
     */
    public void setGoodThum(String goodThum) {
        this.goodThum = goodThum == null ? null : goodThum.trim();
    }

    /**
     * 获取商品价格
     *
     * @return good_price - 商品价格
     */
    public BigDecimal getGoodPrice() {
        return goodPrice;
    }

    /**
     * 设置商品价格
     *
     * @param goodPrice 商品价格
     */
    public void setGoodPrice(BigDecimal goodPrice) {
        this.goodPrice = goodPrice;
    }

    /**
     * 获取商品数量
     *
     * @return good_num - 商品数量
     */
    public Integer getGoodNum() {
        return goodNum;
    }

    /**
     * 设置商品数量
     *
     * @param goodNum 商品数量
     */
    public void setGoodNum(Integer goodNum) {
        this.goodNum = goodNum;
    }
}