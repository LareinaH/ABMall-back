package com.cotton.abmallback.model;

import com.cotton.base.model.BaseModel;
import java.util.Date;
import javax.persistence.*;

@Table(name = "member_address")
public class MemberAddress extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 会员id
     */
    private Long member_id;

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
     * 是否默认地址	0:否 1:是
     */
    private Boolean is_default;

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
     * 获取是否默认地址	0:否 1:是
     *
     * @return is_default - 是否默认地址	0:否 1:是
     */
    public Boolean getIs_default() {
        return is_default;
    }

    /**
     * 设置是否默认地址	0:否 1:是
     *
     * @param is_default 是否默认地址	0:否 1:是
     */
    public void setIs_default(Boolean is_default) {
        this.is_default = is_default;
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