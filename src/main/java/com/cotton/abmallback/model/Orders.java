package com.cotton.abmallback.model;

import com.cotton.base.model.BaseModel;
import java.util.Date;
import javax.persistence.*;

public class Orders extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 订单编号
     */
    private String order_no;

    /**
     * 会员id
     */
    private Long member_id;

    /**
     * 订单状态
     */
    private String order_status;

    /**
     * 订单总价
     */
    private Long total_money;

    /**
     * 收货人姓名
     */
    private String receiver_name;

    /**
     * 收货人联系电话
     */
    private Integer receiver_phone;

    /**
     * 收货地址-省名称
     */
    private String receiver_province_name;

    /**
     * 收货地址-省编码
     */
    private Integer receiver_province_code;

    /**
     * 收货地址-城市编码
     */
    private Integer receiver_city_code;

    /**
     * 收货地址-城市名称
     */
    private Integer receiver_city_name;

    /**
     * 收货地址：县名称
     */
    private Integer receiver_county_name;

    /**
     * 收货地址-县编码
     */
    private Integer receiver_county_code;

    /**
     * 详细地址
     */
    private String receiver_address;

    /**
     * 支付方式
     */
    private String pay_mode;

    /**
     * 是否支付：0:未支付 1:已支付
     */
    private Boolean is_paid;

    /**
     * 支付交易流水号
     */
    private String trade_no;

    /**
     * 发货时间
     */
    private Date delivery_time;

    /**
     * 收货时间
     */
    private Date receive_time;

    /**
     * 订单备注
     */
    private String remarks;

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
     * 获取会员id
     *
     * @return member_id - 会员id
     */
    public Long getMember_id() {
        return member_id;
    }

    /**
     * 设置会员id
     *
     * @param member_id 会员id
     */
    public void setMember_id(Long member_id) {
        this.member_id = member_id;
    }

    /**
     * 获取订单状态
     *
     * @return order_status - 订单状态
     */
    public String getOrder_status() {
        return order_status;
    }

    /**
     * 设置订单状态
     *
     * @param order_status 订单状态
     */
    public void setOrder_status(String order_status) {
        this.order_status = order_status == null ? null : order_status.trim();
    }

    /**
     * 获取订单总价
     *
     * @return total_money - 订单总价
     */
    public Long getTotal_money() {
        return total_money;
    }

    /**
     * 设置订单总价
     *
     * @param total_money 订单总价
     */
    public void setTotal_money(Long total_money) {
        this.total_money = total_money;
    }

    /**
     * 获取收货人姓名
     *
     * @return receiver_name - 收货人姓名
     */
    public String getReceiver_name() {
        return receiver_name;
    }

    /**
     * 设置收货人姓名
     *
     * @param receiver_name 收货人姓名
     */
    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name == null ? null : receiver_name.trim();
    }

    /**
     * 获取收货人联系电话
     *
     * @return receiver_phone - 收货人联系电话
     */
    public Integer getReceiver_phone() {
        return receiver_phone;
    }

    /**
     * 设置收货人联系电话
     *
     * @param receiver_phone 收货人联系电话
     */
    public void setReceiver_phone(Integer receiver_phone) {
        this.receiver_phone = receiver_phone;
    }

    /**
     * 获取收货地址-省名称
     *
     * @return receiver_province_name - 收货地址-省名称
     */
    public String getReceiver_province_name() {
        return receiver_province_name;
    }

    /**
     * 设置收货地址-省名称
     *
     * @param receiver_province_name 收货地址-省名称
     */
    public void setReceiver_province_name(String receiver_province_name) {
        this.receiver_province_name = receiver_province_name == null ? null : receiver_province_name.trim();
    }

    /**
     * 获取收货地址-省编码
     *
     * @return receiver_province_code - 收货地址-省编码
     */
    public Integer getReceiver_province_code() {
        return receiver_province_code;
    }

    /**
     * 设置收货地址-省编码
     *
     * @param receiver_province_code 收货地址-省编码
     */
    public void setReceiver_province_code(Integer receiver_province_code) {
        this.receiver_province_code = receiver_province_code;
    }

    /**
     * 获取收货地址-城市编码
     *
     * @return receiver_city_code - 收货地址-城市编码
     */
    public Integer getReceiver_city_code() {
        return receiver_city_code;
    }

    /**
     * 设置收货地址-城市编码
     *
     * @param receiver_city_code 收货地址-城市编码
     */
    public void setReceiver_city_code(Integer receiver_city_code) {
        this.receiver_city_code = receiver_city_code;
    }

    /**
     * 获取收货地址-城市名称
     *
     * @return receiver_city_name - 收货地址-城市名称
     */
    public Integer getReceiver_city_name() {
        return receiver_city_name;
    }

    /**
     * 设置收货地址-城市名称
     *
     * @param receiver_city_name 收货地址-城市名称
     */
    public void setReceiver_city_name(Integer receiver_city_name) {
        this.receiver_city_name = receiver_city_name;
    }

    /**
     * 获取收货地址：县名称
     *
     * @return receiver_county_name - 收货地址：县名称
     */
    public Integer getReceiver_county_name() {
        return receiver_county_name;
    }

    /**
     * 设置收货地址：县名称
     *
     * @param receiver_county_name 收货地址：县名称
     */
    public void setReceiver_county_name(Integer receiver_county_name) {
        this.receiver_county_name = receiver_county_name;
    }

    /**
     * 获取收货地址-县编码
     *
     * @return receiver_county_code - 收货地址-县编码
     */
    public Integer getReceiver_county_code() {
        return receiver_county_code;
    }

    /**
     * 设置收货地址-县编码
     *
     * @param receiver_county_code 收货地址-县编码
     */
    public void setReceiver_county_code(Integer receiver_county_code) {
        this.receiver_county_code = receiver_county_code;
    }

    /**
     * 获取详细地址
     *
     * @return receiver_address - 详细地址
     */
    public String getReceiver_address() {
        return receiver_address;
    }

    /**
     * 设置详细地址
     *
     * @param receiver_address 详细地址
     */
    public void setReceiver_address(String receiver_address) {
        this.receiver_address = receiver_address == null ? null : receiver_address.trim();
    }

    /**
     * 获取支付方式
     *
     * @return pay_mode - 支付方式
     */
    public String getPay_mode() {
        return pay_mode;
    }

    /**
     * 设置支付方式
     *
     * @param pay_mode 支付方式
     */
    public void setPay_mode(String pay_mode) {
        this.pay_mode = pay_mode == null ? null : pay_mode.trim();
    }

    /**
     * 获取是否支付：0:未支付 1:已支付
     *
     * @return is_paid - 是否支付：0:未支付 1:已支付
     */
    public Boolean getIs_paid() {
        return is_paid;
    }

    /**
     * 设置是否支付：0:未支付 1:已支付
     *
     * @param is_paid 是否支付：0:未支付 1:已支付
     */
    public void setIs_paid(Boolean is_paid) {
        this.is_paid = is_paid;
    }

    /**
     * 获取支付交易流水号
     *
     * @return trade_no - 支付交易流水号
     */
    public String getTrade_no() {
        return trade_no;
    }

    /**
     * 设置支付交易流水号
     *
     * @param trade_no 支付交易流水号
     */
    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no == null ? null : trade_no.trim();
    }

    /**
     * 获取发货时间
     *
     * @return delivery_time - 发货时间
     */
    public Date getDelivery_time() {
        return delivery_time;
    }

    /**
     * 设置发货时间
     *
     * @param delivery_time 发货时间
     */
    public void setDelivery_time(Date delivery_time) {
        this.delivery_time = delivery_time;
    }

    /**
     * 获取收货时间
     *
     * @return receive_time - 收货时间
     */
    public Date getReceive_time() {
        return receive_time;
    }

    /**
     * 设置收货时间
     *
     * @param receive_time 收货时间
     */
    public void setReceive_time(Date receive_time) {
        this.receive_time = receive_time;
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