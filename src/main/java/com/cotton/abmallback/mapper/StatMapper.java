package com.cotton.abmallback.mapper;

import javafx.util.Pair;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface StatMapper {

    @Select("select count(*) from member where is_deleted=0")
    Long getTotalMember();

    @Select("select order_status as orderStatus, count(*) as sum from orders " +
            "where is_deleted=0 and date(gmt_create) >= #{gmtStart} and date(gmt_create) <= #{gmtEnd}" +
            "group by order_status order by sum desc")
    List<Map<String, Long>> getOrderStatusStats(@Param("gmtStart") String gmtStart, @Param("gmtEnd") String gmtEnd);


    @Select("select level,count(*)  from member WHERE referrer_id = #{id} GROUP BY level; ")
    Map<String,Long> getMemberTeamCountGroupByLevel(@Param("id") Long memberId);
}
