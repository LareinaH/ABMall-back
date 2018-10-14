package com.cotton.abmallback.service;

import com.cotton.base.service.BaseService;
import com.cotton.abmallback.model.Member;

import java.util.List;

/**
 * Member
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/5/9
 */
public interface MemberService extends BaseService<Member> {

    /**
     * 定时任务，解除绑定关系
     */
    void unbindingRelationship();

    /**
     * 解除绑定关系
     * @param member
     */
    void unbindingRelationship(Member member);


    /**
     * 查询注册48小时没有发生购买的用户
     * @return
     */
    List<Member>  queryUnbuyMemberIn48Hours();



}
