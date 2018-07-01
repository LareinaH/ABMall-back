package com.cotton.abmallback.manager.impl;

import com.cotton.abmallback.enumeration.DistributionItemEnum;
import com.cotton.abmallback.enumeration.MemberLevelEnum;
import com.cotton.abmallback.enumeration.MessageTypeEnum;
import com.cotton.abmallback.enumeration.OrderStatusEnum;
import com.cotton.abmallback.manager.DistributionManager;
import com.cotton.abmallback.manager.MessageManager;
import com.cotton.abmallback.model.AccountMoneyFlow;
import com.cotton.abmallback.model.DistributionConfig;
import com.cotton.abmallback.model.Member;
import com.cotton.abmallback.model.Orders;
import com.cotton.abmallback.service.AccountMoneyFlowService;
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

    private final AccountMoneyFlowService accountMoneyFlowService;

    private final MessageManager messageManager;

    public DistributionManagerImpl(OrdersService ordersService, MemberService memberService, DistributionConfigService distributionConfigService, AccountMoneyFlowService accountMoneyFlowService, MessageManager messageManager) {
        this.ordersService = ordersService;
        this.memberService = memberService;
        this.distributionConfigService = distributionConfigService;
        this.accountMoneyFlowService = accountMoneyFlowService;
        this.messageManager = messageManager;
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

        //获取全部分销配置
        Map<String, DistributionConfig> map = distributionConfigService.getAllDistributionConfig();

        //获取参与分销的人员
        Member first = null;
        Member second = null;
        Member third = null;

        Member self = memberService.getById(orders.getMemberId());
        String selfSharePercent = getLevelSharePercent(self.getLevel(), map);

        String firstSharePercent = null;
        String secondSharePercent = null;
        String thirdSharePercent = null;

        String firstExecutivePercent = null;
        String secondExecutivePercent = null;
        String thirdExecutivePercent = null;

        if (null != self.getReferrerId()) {
            first = memberService.getById(self.getReferrerId());

            if (null != first) {
                firstSharePercent = getLevelSharePercent(first.getLevel(), map);
                firstExecutivePercent = getLevelExecutivePercent(first.getLevel(), map);
            }
        }

        if (null != first && null != first.getReferrerId()) {
            second = memberService.getById(first.getReferrerId());

            if (null != second) {
                secondSharePercent = getLevelSharePercent(second.getLevel(), map);
                secondExecutivePercent = getLevelExecutivePercent(second.getLevel(), map);
            }
        }

        if (null != second && null != second.getReferrerId()) {
            third = memberService.getById(second.getReferrerId());

            if (null != third) {
                thirdSharePercent = getLevelSharePercent(third.getLevel(), map);
                thirdExecutivePercent = getLevelExecutivePercent(third.getLevel(), map);
            }
        }

        BigDecimal totalDistrubtionMoney = new BigDecimal(0);

        //1 分享奖励

        //1.1 self
        totalDistrubtionMoney = totalDistrubtionMoney.add(distributionShareAward(orders, self, selfSharePercent));
        //1.2 第一层
        if (null != first) {
            totalDistrubtionMoney = totalDistrubtionMoney.add(distributionShareAward(orders, first, firstSharePercent));
        }
        //1.2 第二层
        if (null != second) {
            totalDistrubtionMoney = totalDistrubtionMoney.add(distributionShareAward(orders, second, secondSharePercent));
        }
        //1.2 第三层
        if (null != third) {
            totalDistrubtionMoney = totalDistrubtionMoney.add(distributionShareAward(orders, third, thirdSharePercent));
        }

        //高管奖励 级别高于才分高管奖励
        //2.1 第一层
        if(first != null && compareLevel(first.getLevel(),self.getLevel()) > 0){

            totalDistrubtionMoney = totalDistrubtionMoney.add(distributionExecutiveAward(orders,first,firstExecutivePercent));
        }
        //2.2 第二层
        if(second != null && compareLevel(second.getLevel(),self.getLevel()) > 0
                && compareLevel(second.getLevel(),first.getLevel()) > 0){

            totalDistrubtionMoney = totalDistrubtionMoney.add(distributionExecutiveAward(orders,second,secondExecutivePercent));
        }
        //2.3 第三层
        if(third != null && compareLevel(third.getLevel(),self.getLevel()) > 0
                && compareLevel(third.getLevel(),first.getLevel()) > 0
                && compareLevel(third.getLevel(),second.getLevel()) > 0){

            totalDistrubtionMoney = totalDistrubtionMoney.add(distributionExecutiveAward(orders,third,thirdExecutivePercent));
        }

        //更新订单信息
        orders.setRebateMoney(totalDistrubtionMoney);

        ordersService.update(orders);
    }

    private BigDecimal distributionShareAward(Orders orders, Member member, String sharePercent) {
        //计算分润钱数 = 订单总数 * 分润比例 / 100
        BigDecimal selfShareMoney = orders.getTotalMoney().multiply(new BigDecimal(sharePercent)).divide(new BigDecimal(100));

        //创建流水
        AccountMoneyFlow accountMoneyFlow = new AccountMoneyFlow();
        accountMoneyFlow.setOrderId(orders.getId());
        accountMoneyFlow.setAccountMoneyType(MessageTypeEnum.SHARE_AWARD.name());
        accountMoneyFlow.setDistributMoney(selfShareMoney);
        accountMoneyFlow.setMemberId(member.getId());
        accountMoneyFlowService.insert(accountMoneyFlow);

        //发送消息
        messageManager.sendShareAward(member.getId());
        //可提现余额增加
        member.setMoneyTotalEarn(member.getMoneyTotalEarn().add(selfShareMoney));

        return selfShareMoney;
    }

    private BigDecimal distributionExecutiveAward(Orders orders, Member member, String sharePercent) {
        //计算分润钱数 = 订单总数 * 分润比例 / 100
        BigDecimal selfShareMoney = orders.getTotalMoney().multiply(new BigDecimal(sharePercent)).divide(new BigDecimal(100));

        //创建流水
        AccountMoneyFlow accountMoneyFlow = new AccountMoneyFlow();
        accountMoneyFlow.setOrderId(orders.getId());
        accountMoneyFlow.setAccountMoneyType(MessageTypeEnum.EXECUTIVE_AWARD.name());
        accountMoneyFlow.setDistributMoney(selfShareMoney);
        accountMoneyFlow.setMemberId(member.getId());
        accountMoneyFlowService.insert(accountMoneyFlow);

        //发送消息
        messageManager.sendExecutiveAward(member.getId());
        //可提现余额增加
        member.setMoneyTotalEarn(member.getMoneyTotalEarn().add(selfShareMoney));

        return selfShareMoney;
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

    private int compareLevel(String level1,String level2){

        return 0;

    }
}
