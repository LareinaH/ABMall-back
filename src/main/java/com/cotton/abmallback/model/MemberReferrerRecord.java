package com.cotton.abmallback.model;

import com.cotton.base.model.BaseModel;
import java.util.Date;
import javax.persistence.*;

@Table(name = "member_referrer_record")
public class MemberReferrerRecord extends BaseModel {
    /**
     * 会员id
     */
    @Column(name = "member_id")
    private Long memberId;

    /**
     * 引荐人id
     */
    @Column(name = "referrer_id")
    private Long referrerId;

    /**
     * 引荐关系开始时间
     */
    @Column(name = "gmt_begin")
    private Date gmtBegin;

    /**
     * 引荐关系结束时间
     */
    @Column(name = "gmt_end")
    private Date gmtEnd;

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
     * 获取引荐人id
     *
     * @return referrer_id - 引荐人id
     */
    public Long getReferrerId() {
        return referrerId;
    }

    /**
     * 设置引荐人id
     *
     * @param referrerId 引荐人id
     */
    public void setReferrerId(Long referrerId) {
        this.referrerId = referrerId;
    }

    /**
     * 获取引荐关系开始时间
     *
     * @return gmt_begin - 引荐关系开始时间
     */
    public Date getGmtBegin() {
        return gmtBegin;
    }

    /**
     * 设置引荐关系开始时间
     *
     * @param gmtBegin 引荐关系开始时间
     */
    public void setGmtBegin(Date gmtBegin) {
        this.gmtBegin = gmtBegin;
    }

    /**
     * 获取引荐关系结束时间
     *
     * @return gmt_end - 引荐关系结束时间
     */
    public Date getGmtEnd() {
        return gmtEnd;
    }

    /**
     * 设置引荐关系结束时间
     *
     * @param gmtEnd 引荐关系结束时间
     */
    public void setGmtEnd(Date gmtEnd) {
        this.gmtEnd = gmtEnd;
    }
}