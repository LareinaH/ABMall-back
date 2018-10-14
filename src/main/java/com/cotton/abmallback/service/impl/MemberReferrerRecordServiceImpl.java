package com.cotton.abmallback.service.impl;

import com.cotton.abmallback.mapper.MemberMapper;
import com.cotton.abmallback.model.Member;
import com.cotton.base.service.impl.BaseServiceImpl;
import com.cotton.abmallback.model.MemberReferrerRecord;
import com.cotton.abmallback.service.MemberReferrerRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * memberReferrerRecord
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/10/6
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MemberReferrerRecordServiceImpl extends BaseServiceImpl<MemberReferrerRecord> implements MemberReferrerRecordService {

    private final MemberMapper memberMapper;

    public MemberReferrerRecordServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public void init() {

        List<Member> memberList = memberMapper.queryUnbuyMemberIn48Hours();

        for(Member member : memberList){
            MemberReferrerRecord memberReferrerRecord = new MemberReferrerRecord();
            memberReferrerRecord.setMemberId(member.getId());
            memberReferrerRecord.setReferrerId(member.getReferrerId());
            memberReferrerRecord.setGmtBegin(new Date());
            insert(memberReferrerRecord);
        }

    }
}