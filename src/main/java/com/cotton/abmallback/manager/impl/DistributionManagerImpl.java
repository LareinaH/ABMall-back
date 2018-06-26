package com.cotton.abmallback.manager.impl;

import com.cotton.abmallback.enumeration.OrderStatusEnum;
import com.cotton.abmallback.manager.DistributionManager;
import com.cotton.abmallback.model.Member;
import com.cotton.abmallback.model.Orders;
import com.cotton.abmallback.service.MemberService;
import com.cotton.abmallback.service.OrdersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * DistributionManagerImpl
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/6/26
 */
@Service
public class DistributionManagerImpl implements DistributionManager {

    private Logger logger = LoggerFactory.getLogger(DistributionManagerImpl.class);

    private final OrdersService ordersService;

    private final MemberService memberService;

    public DistributionManagerImpl(OrdersService ordersService, MemberService memberService) {
        this.ordersService = ordersService;
        this.memberService = memberService;
    }


    @Override
    public void orderDistribute(String orderNo) {

        //根据orderNo 找到订单
        Orders model = new Orders();
        model.setOrderNo(orderNo);
        model.setIsDeleted(false);
        Orders orders = ordersService.selectOne(model);

        if(null == orders){

            logger.error("分销错误,订单不存在!");
            return;
        }

        //查找该订单用户的 订单个数.
        Example example = new Example(Orders.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("memberId",orders.getMemberId());
        criteria.andEqualTo("isDelete",false);

        List<String> orderStatusList = new ArrayList<>();
        orderStatusList.add(OrderStatusEnum.CANCEL.name());
        criteria.andNotIn("orderStauts",orderStatusList);

        long count = ordersService.count(example);

        if(count <= 1){

            //首次购物 不需要分销
            return;
        }

        //获取参与分销的人员

        Member first = null;
        Member second = null;
        Member third = null;

        Member member = memberService.getById(orders.getMemberId());

        if(null != member.getReferrerId()){
            first = memberService.getById(member.getReferrerId());
        }

        if(null != first && null != first.getReferrerId()){
            second = memberService.getById(first.getReferrerId());
        }

        if(null != second && null != second.getReferrerId()){
            third = memberService.getById(second.getReferrerId());
        }

        //分享奖励





        //高管奖励




    }
}
