package com.cotton.abmallback.model;

import com.alibaba.fastjson.annotation.JSONField;
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