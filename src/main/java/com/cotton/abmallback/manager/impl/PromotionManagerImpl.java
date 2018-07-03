package com.cotton.abmallback.manager.impl;

import com.cotton.abmallback.enumeration.AccountMoneyTypeEnum;
import com.cotton.abmallback.enumeration.DistributionItemEnum;
import com.cotton.abmallback.enumeration.MemberLevelEnum;
import com.cotton.abmallback.manager.MessageManager;
import com.cotton.abmallback.manager.PromotionManager;
import com.cotton.abmallback.model.AccountMoneyFlow;
import com.cotton.abmallback.model.DistributionConfig;
import com.cotton.abmallback.model.Member;
import com.cotton.abmallback.service.AccountMoneyFlowService;
import com.cotton.abmallback.service.DistributionConfigService;
import com.cotton.abmallback.service.MemberService;

import java.math.BigDecimal;
import java.util.Map;

/**
 * PromotionManagerImpl
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/7/3
 */
public class PromotionManagerImpl implements PromotionManager {

    private final DistributionConfigService distributionConfigService;

    private final MemberService memberService;

    private final AccountMoneyFlowService accountMoneyFlowService;

    private final MessageManager messageManager;

    public PromotionManagerImpl(DistributionConfigService distributionConfigService, MemberService memberService, AccountMoneyFlowService accountMoneyFlowService, MessageManager messageManager) {
        this.distributionConfigService = distributionConfigService;
        this.memberService = memberService;
        this.accountMoneyFlowService = accountMoneyFlowService;
        this.messageManager = messageManager;
    }


    @Override
    public void memberPromotion(Member member, long orderId) {

        //对于达到未v3级别的 晋级
        if (!MemberLevelEnum.V3.name().equalsIgnoreCase(member.getLevel())) {
            //晋级
            MemberLevelEnum newLevel = MemberLevelEnum.getNextLevel(MemberLevelEnum.valueOf(member.getLevel()));

            if (null != newLevel) {

                //判断是否达到晋级条件
                if (checkCanBePromited(member, newLevel)) {

                    member.setLevel(newLevel.toString());
                    memberService.update(member);
                    //发放奖励
                    sendPromotionReward(member, newLevel, orderId);
                    //发送消息通知
                    sendPromotionMessage(member, newLevel);
                }
            }
        } else {
            //达到v3级别的   不晋级，仅发放额外奖励
        }
    }


    private boolean checkCanBePromited(Member member, MemberLevelEnum newLevel) {

        Map<String, DistributionConfig> map = distributionConfigService.getAllDistributionConfig();

        String totalMoney = null;
        String totalMemberConut = null;
        String shopTimes = null;

        //获取newLevel的晋级条件
        switch (newLevel) {
            case AGENT:
                totalMoney = map.get(DistributionItemEnum.PROMOTION_AGENT_MONEY).getValue();
                shopTimes = map.get(DistributionItemEnum.PROMOTION_AGENT_TIMES).getValue();
                break;
            case V1:
                totalMoney = map.get(DistributionItemEnum.PROMOTION_V1_MONEY).getValue();
                totalMemberConut = map.get(DistributionItemEnum.PROMOTION_V1_SHARE_PEOPLE).getValue();
                break;
            case V2:
                totalMoney = map.get(DistributionItemEnum.PROMOTION_V2_MONEY).getValue();
                totalMemberConut = map.get(DistributionItemEnum.PROMOTION_V2_SHARE_PEOPLE).getValue();
                break;
            case V3:
                totalMoney = map.get(DistributionItemEnum.PROMOTION_V3_MONEY).getValue();
                totalMemberConut = map.get(DistributionItemEnum.PROMOTION_V3_SHARE_PEOPLE).getValue();
                break;
            default:
                break;
        }

        if (newLevel.equals(MemberLevelEnum.AGENT)) {
            return member.getMoneyTotalSpend().compareTo(BigDecimal.valueOf(Double.valueOf(totalMoney))) > 0 && member.getReferTotalCount() > Integer.valueOf(shopTimes);
        } else {
            return member.getMoneyTotalSpend().compareTo(BigDecimal.valueOf(Double.valueOf(totalMoney))) > 0 && member.getReferTotalCount() > Integer.valueOf(totalMemberConut);
        }
    }

    /**
     * 给升级的会员发放奖励
     */
    private void sendPromotionReward(Member member, MemberLevelEnum newLevel, long orderId) {

        Map<String, DistributionConfig> map = distributionConfigService.getAllDistributionConfig();

        String awardMoney = null;

        //获取newLevel的晋级奖励
        switch (newLevel) {
            case AGENT:
                //awardMoney = map.get(DistributionItemEnum.PROMOTION_AGENT_TIMES).getValue();
                awardMoney = null;
                break;
            case V1:
                awardMoney = map.get(DistributionItemEnum.PROMOTION_AWARD_V1).getValue();
                break;
            case V2:
                awardMoney = map.get(DistributionItemEnum.PROMOTION_AWARD_V2).getValue();
                break;
            case V3:
                awardMoney = map.get(DistributionItemEnum.PROMOTION_AWARD_V3).getValue();
                break;
            default:
                break;
        }

        if (null != awardMoney) {
            AccountMoneyFlow accountMoneyFlow = new AccountMoneyFlow();
            accountMoneyFlow.setMemberId(member.getId());
            accountMoneyFlow.setDistributMoney(new BigDecimal(awardMoney));
            accountMoneyFlow.setAccountMoneyType(AccountMoneyTypeEnum.PROMOTION_AWARD.name());
            accountMoneyFlow.setOrderId(orderId);
            accountMoneyFlow.setPromotionLevel(newLevel.name());

            if (accountMoneyFlowService.insert(accountMoneyFlow)) {

                member.setMoneyTotalEarn(member.getMoneyTotalEarn().add(accountMoneyFlow.getDistributMoney()));
                memberService.update(member);
            }
        }

    }

    /**
     * 发送消息通知
     */
    void sendPromotionMessage(Member member, MemberLevelEnum newLevel) {

        messageManager.sendPromotionAward(member.getId(),newLevel.name());
    }

}
