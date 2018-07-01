package com.cotton.abmallback.manager.impl;

import com.cotton.abmallback.enumeration.DistributionItemEnum;
import com.cotton.abmallback.enumeration.MemberLevelEnum;
import com.cotton.abmallback.enumeration.OrderStatusEnum;
import com.cotton.abmallback.manager.DistributionManager;
import com.cotton.abmallback.model.DistributionConfig;
import com.cotton.abmallback.model.Member;
import com.cotton.abmallback.model.Orders;
import com.cotton.abmallback.service.DistributionConfigService;
import com.cotton.abmallback.service.MemberService;
import com.cotton.abmallback.service.OrdersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    private final DistributionConfigService distributionConfigService;

    public DistributionManagerImpl(OrdersService ordersService, MemberService memberService, DistributionConfigService distributionConfigService) {
        this.ordersService = ordersService;
        this.memberService = memberService;
        this.distributionConfigService = distributionConfigService;
    }


    @Override
    public void orderDistribute(String orderNo) {

        //根据orderNo 找到订单
        Orders model = new Orders();
        model.setOrderNo(orderNo);
        model.setIsDeleted(false);
        Orders orders = ordersService.selectOne(model);

        if (null == orders) {

            logger.error("分销错误,订单不存在!");
            return;
        }

        //查找该订单用户的 订单个数.
        Example example = new Example(Orders.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("memberId", orders.getMemberId());
        criteria.andEqualTo("isDelete", false);

        List<String> orderStatusList = new ArrayList<>();
        orderStatusList.add(OrderStatusEnum.CANCEL.name());
        criteria.andNotIn("orderStauts", orderStatusList);

        long count = ordersService.count(example);

        if (count <= 1) {
            //首次购物 不需要分销
            return;
        }

        //获取参与分销的人员
        Member first = null;
        Member second = null;
        Member third = null;

        Member self = memberService.getById(orders.getMemberId());

        if (null != self.getReferrerId()) {
            first = memberService.getById(self.getReferrerId());
        }

        if (null != first && null != first.getReferrerId()) {
            second = memberService.getById(first.getReferrerId());
        }

        if (null != second && null != second.getReferrerId()) {
            third = memberService.getById(second.getReferrerId());
        }

        //获取全部分销配置
        Map<String, DistributionConfig> map = distributionConfigService.getAllDistributionConfig();

        //1 分享奖励

        //1.1 自己
        String percent = getLevelSharePercent(self.getLevel(), map);

        //计算分润钱数 = 订单总数 * 分润比例 / 100
        BigDecimal decimal = orders.getTotalMoney().multiply(new BigDecimal(percent)).divide(new BigDecimal(100));

        //创建流水




        //

        //if()

        //1.2 第一层

        //1.3 第二层

        //1.4 第三层


        //高管奖励


    }

    private String getLevelSharePercent(String level, Map<String, DistributionConfig> map) {
        String percent = "0";

        MemberLevelEnum memberLevelEnum = MemberLevelEnum.valueOf(level);
        switch (memberLevelEnum) {
            case WHITE:
                percent = map.get(DistributionItemEnum.SHARE_AWARD_WHITE.name()).getValue();
                break;
            case AGENT:
                percent = map.get(DistributionItemEnum.SHARE_AWARD_AGENT.name()).getValue();
                break;
            case V1:
                percent = map.get(DistributionItemEnum.SHARE_AWARD_V1.name()).getValue();
                break;
            case V2:
                percent = map.get(DistributionItemEnum.SHARE_AWARD_V2.name()).getValue();
                break;
            case V3:
                percent = map.get(DistributionItemEnum.SHARE_AWARD_V3.name()).getValue();
                break;
            default:
                break;
        }
        return percent;
    }

    private String getLevelExecutivePercent(String level, Map<String, DistributionConfig> map) {

        String percent = "0";

        MemberLevelEnum memberLevelEnum = MemberLevelEnum.valueOf(level);
        switch (memberLevelEnum) {
            case WHITE:
                break;
            case AGENT:
                break;
            case V1:
                percent = map.get(DistributionItemEnum.EXECUTIVE_AWARD_V1.name()).getValue();
                break;
            case V2:
                percent = map.get(DistributionItemEnum.EXECUTIVE_AWARD_V2.name()).getValue();
                break;
            case V3:
                percent = map.get(DistributionItemEnum.EXECUTIVE_AWARD_V3.name()).getValue();
                break;
            default:
                break;
        }
        return percent;
    }
}
