package com.cotton.abmallback.mapper;

import com.cotton.abmallback.model.Member;
import com.cotton.base.mapper.BaseMapper;

import java.util.List;

public interface MemberMapper extends BaseMapper<Member> {

    List<Member> queryUnbuyMemberIn48Hours();

}