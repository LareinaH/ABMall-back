package com.cotton.abmallback.service;

import com.cotton.base.service.BaseService;
import com.cotton.abmallback.model.GoodsSpecification;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * GoodsSpecification
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/5/22
 */
public interface GoodsSpecificationService extends BaseService<GoodsSpecification> {
    @Select("select distinct(goods_specification_name) from goods_specification order by goods_specification_name")
    public List<String> getSpecUnitList();
}
