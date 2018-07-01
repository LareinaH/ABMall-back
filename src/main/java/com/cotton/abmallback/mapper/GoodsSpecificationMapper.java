package com.cotton.abmallback.mapper;

import com.cotton.abmallback.model.GoodsSpecification;
import com.cotton.base.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GoodsSpecificationMapper extends BaseMapper<GoodsSpecification> {

    @Select("select distinct(goods_specification_name) from goods_specification order by goods_specification_name")
    public List<String> getSpecUnitList();
}