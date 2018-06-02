package com.cotton.abmallback.model;

import com.cotton.base.model.BaseModel;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

public class Orders extends BaseModel {
    /**
     * 订单编号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 会员id
     */
    @Column(name = "member_id")
    private Long memberId;

    /**
     * 订单状态
     */
    @Column(name = "order_status")
    private String orderStatus;

    /**
     * 订单总价
     */
    @Column(name = "total_money")
    private BigDecimal totalMoney;

    /**
     * 收货人姓名
     */
    @Column(name = "receiver_name")
    private String receiverName;

    /**
     * 收货人联系电话
     */
    @Column(name = "receiver_phone")
    private Integer receiverPhone;

    /**
     * 收货地址-省名称
     */
    @Column(name = "receiver_province_name")
    private String receiverProvinceName;

    /**
     * 收货地址-省编码
     */
    @Column(name = "receiver_province_code")
    private Integer receiverProvinceCode;

    /**
     * 收货地址-城市名称
     */
    @Column(name = "receiver_city_name")
    private String receiverCityName;

    /**
     * 收货地址-城市编码
     */
    @Column(name = "receiver_city_code")
    private Integer receiverCityCode;

    /**
     * 收货地址：县名称
     */
    @Column(name = "receiver_county_name")
    private String receiverCountyName;

    /**
     * 收货地址-县编码
     */
    @Column(name = "receiver_county_code")
    private Integer receiverCountyCode;

    /**
     * 详细地址
     */
    @Column(name = "receiver_address")
    private String receiverAddress;

    /**
     * 支付方式
     */
    @Column(name = "pay_mode")
    private String payMode;

    /**
     * 是否支付：0:未支付 1:已支付
     */
    @Column(name = "is_paid")
    private Boolean isPaid;

    /**
     * 支付交易流水号
     */
    @Column(name = "trade_no")
    private String tradeNo;

    @Column(name = "logistic_code")
    private String logisticCode;

    /**
     * 发货时间
     */
    @Column(name = "delivery_time")
    private Date deliveryTime;

    /**
     * 收货时间
     */
    @Column(name = "receive_time")
    private Date receiveTime;

    /**
     * 订单备注
     */
    private String remarks;

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
     * 获取会员id
     *
     * @return member_id - 会员id
     */
    public Long getMemberId() {
        return memberId;
    }

    /**
     * 设置会员id
     *
     * @param memberId 会员id
     */
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取订单状态
     *
     * @return order_status - 订单状态
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置订单状态
     *
     * @param orderStatus 订单状态
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    /**
     * 获取订单总价
     *
     * @return total_money - 订单总价
     */
    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    /**
     * 设置订单总价
     *
     * @param totalMoney 订单总价
     */
    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    /**
     * 获取收货人姓名
     *
     * @return receiver_name - 收货人姓名
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * 设置收货人姓名
     *
     * @param receiverName 收货人姓名
     */
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    /**
     * 获取收货人联系电话
     *
     * @return receiver_phone - 收货人联系电话
     */
    public Integer getReceiverPhone() {
        return receiverPhone;
    }

    /**
     * 设置收货人联系电话
     *
     * @param receiverPhone 收货人联系电话
     */
    public void setReceiverPhone(Integer receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    /**
     * 获取收货地址-省名称
     *
     * @return receiver_province_name - 收货地址-省名称
     */
    public String getReceiverProvinceName() {
        return receiverProvinceName;
    }

    /**
     * 设置收货地址-省名称
     *
     * @param receiverProvinceName 收货地址-省名称
     */
    public void setReceiverProvinceName(String receiverProvinceName) {
        this.receiverProvinceName = receiverProvinceName == null ? null : receiverProvinceName.trim();
    }

    /**
     * 获取收货地址-省编码
     *
     * @return receiver_province_code - 收货地址-省编码
     */
    public Integer getReceiverProvinceCode() {
        return receiverProvinceCode;
    }

    /**
     * 设置收货地址-省编码
     *
     * @param receiverProvinceCode 收货地址-省编码
     */
    public void setReceiverProvinceCode(Integer receiverProvinceCode) {
        this.receiverProvinceCode = receiverProvinceCode;
    }

    /**
     * 获取收货地址-城市名称
     *
     * @return receiver_city_name - 收货地址-城市名称
     */
    public String getReceiverCityName() {
        return receiverCityName;
    }

    /**
     * 设置收货地址-城市名称
     *
     * @param receiverCityName 收货地址-城市名称
     */
    public void setReceiverCityName(String receiverCityName) {
        this.receiverCityName = receiverCityName == null ? null : receiverCityName.trim();
    }

    /**
     * 获取收货地址-城市编码
     *
     * @return receiver_city_code - 收货地址-城市编码
     */
    public Integer getReceiverCityCode() {
        return receiverCityCode;
    }

    /**
     * 设置收货地址-城市编码
     *
     * @param receiverCityCode 收货地址-城市编码
     */
    public void setReceiverCityCode(Integer receiverCityCode) {
        this.receiverCityCode = receiverCityCode;
    }

    /**
     * 获取收货地址：县名称
     *
     * @return receiver_county_name - 收货地址：县名称
     */
    public String getReceiverCountyName() {
        return receiverCountyName;
    }

    /**
     * 设置收货地址：县名称
     *
     * @param receiverCountyName 收货地址：县名称
     */
    public void setReceiverCountyName(String receiverCountyName) {
        this.receiverCountyName = receiverCountyName == null ? null : receiverCountyName.trim();
    }

    /**
     * 获取收货地址-县编码
     *
     * @return receiver_county_code - 收货地址-县编码
     */
    public Integer getReceiverCountyCode() {
        return receiverCountyCode;
    }

    /**
     * 设置收货地址-县编码
     *
     * @param receiverCountyCode 收货地址-县编码
     */
    public void setReceiverCountyCode(Integer receiverCountyCode) {
        this.receiverCountyCode = receiverCountyCode;
    }

    /**
     * 获取详细地址
     *
     * @return receiver_address - 详细地址
     */
    public String getReceiverAddress() {
        return receiverAddress;
    }

    /**
     * 设置详细地址
     *
     * @param receiverAddress 详细地址
     */
    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress == null ? null : receiverAddress.trim();
    }

    /**
     * 获取支付方式
     *
     * @return pay_mode - 支付方式
     */
    public String getPayMode() {
        return payMode;
    }

    /**
     * 设置支付方式
     *
     * @param payMode 支付方式
     */
    public void setPayMode(String payMode) {
        this.payMode = payMode == null ? null : payMode.trim();
    }

    /**
     * 获取是否支付：0:未支付 1:已支付
     *
     * @return is_paid - 是否支付：0:未支付 1:已支付
     */
    public Boolean getIsPaid() {
        return isPaid;
    }

    /**
     * 设置是否支付：0:未支付 1:已支付
     *
     * @param isPaid 是否支付：0:未支付 1:已支付
     */
    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }

    /**
     * 获取支付交易流水号
     *
     * @return trade_no - 支付交易流水号
     */
    public String getTradeNo() {
        return tradeNo;
    }

    /**
     * 设置支付交易流水号
     *
     * @param tradeNo 支付交易流水号
     */
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    /**
     * @return logistic_code
     */
    public String getLogisticCode() {
        return logisticCode;
    }

    /**
     * @param logisticCode
     */
    public void setLogisticCode(String logisticCode) {
        this.logisticCode = logisticCode == null ? null : logisticCode.trim();
    }

    /**
     * 获取发货时间
     *
     * @return delivery_time - 发货时间
     */
    public Date getDeliveryTime() {
        return deliveryTime;
    }

    /**
     * 设置发货时间
     *
     * @param deliveryTime 发货时间
     */
    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    /**
     * 获取收货时间
     *
     * @return receive_time - 收货时间
     */
    public Date getReceiveTime() {
        return receiveTime;
    }

    /**
     * 设置收货时间
     *
     * @param receiveTime 收货时间
     */
    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    /**
     * 获取订单备注
     *
     * @return remarks - 订单备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置订单备注
     *
     * @param remarks 订单备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}