package com.cotton.abmallback.model;

import com.cotton.base.model.BaseModel;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "cash_pick_up")
public class CashPickUp extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 会员id
     */
    private Long member_id;

    /**
     * 提现金额
     */
    private BigDecimal money;

    /**
     * 提现账号
     */
    private String account_no;

    /**
     * 体现状态
     */
    private String cash_status;

    /**
     * 备注
     */
    private String remark;

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
     * 获取提现金额
     *
     * @return money - 提现金额
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * 设置提现金额
     *
     * @param money 提现金额
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * 获取提现账号
     *
     * @return account_no - 提现账号
     */
    public String getAccount_no() {
        return account_no;
    }

    /**
     * 设置提现账号
     *
     * @param account_no 提现账号
     */
    public void setAccount_no(String account_no) {
        this.account_no = account_no == null ? null : account_no.trim();
    }

    /**
     * 获取体现状态
     *
     * @return cash_status - 体现状态
     */
    public String getCash_status() {
        return cash_status;
    }

    /**
     * 设置体现状态
     *
     * @param cash_status 体现状态
     */
    public void setCash_status(String cash_status) {
        this.cash_status = cash_status == null ? null : cash_status.trim();
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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