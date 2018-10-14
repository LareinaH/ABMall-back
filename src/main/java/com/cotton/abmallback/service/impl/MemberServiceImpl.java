package com.cotton.abmallback.service.impl;

import com.cotton.abmallback.mapper.MemberMapper;
import com.cotton.abmallback.model.MemberReferrerRecord;
import com.cotton.abmallback.service.MemberReferrerRecordService;
import com.cotton.base.service.impl.BaseServiceImpl;
import com.cotton.abmallback.model.Member;
import com.cotton.abmallback.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Member
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/5/9
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MemberServiceImpl extends BaseServiceImpl<Member> implements MemberService {

    private final MemberMapper memberMapper;
    private final MemberReferrerRecordService memberReferrerRecordService;

    public MemberServiceImpl(MemberMapper memberMapper, MemberReferrerRecordService memberReferrerRecordService) {
        this.memberMapper = memberMapper;
        this.memberReferrerRecordService = memberReferrerRecordService;
    }

    @Override
    public void unbindingRelationship() {

        //查找注册超过48小时 并且没有发生购买行为的用户。
        List<Member > memberList = queryUnbuyMemberIn48Hours();

        //解除绑定关系
        if(!memberList.isEmpty()){
            for (Member member : memberList){
               unbindingRelationship(member);
            }
        }
    }

    @Override
    public void unbindingRelationship(Member member) {

        //1 查找引荐人
        if(null == member.getReferrerId()){
            return;
        }

        Member partner = getById(member.getReferrerId());

        if(partner == null){
            return;
        }

        //2 自己的引荐人设置为0
        member.setReferrerId(0L);
        update(member);

        //3 引荐人推荐个数-1
        partner.setReferTotalCount(partner.getReferTotalCount() -1);
        update(partner);

        //4 更新引荐关系记录
        MemberReferrerRecord model = new MemberReferrerRecord();
        model.setMemberId(member.getId());
        model.setIsDeleted(false);
        List<MemberReferrerRecord> memberReferrerRecordList = memberReferrerRecordService.queryList(model);

        for (MemberReferrerRecord memberReferrerRecord:memberReferrerRecordList) {
            memberReferrerRecord.setGmtEnd(new Date());
            memberReferrerRecord.setIsDeleted(true);
            memberReferrerRecordService.update(memberReferrerRecord);
        }
    }

    @Override
    public List<Member> queryUnbuyMemberIn48Hours() {
        //return memberMapper.queryUnbuyMemberIn48Hours;
        return null;
    }
}