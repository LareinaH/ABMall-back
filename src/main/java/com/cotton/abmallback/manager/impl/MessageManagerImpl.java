package com.cotton.abmallback.manager.impl;

import com.cotton.abmallback.enumeration.MessageTypeEnum;
import com.cotton.abmallback.manager.MessageManager;
import com.cotton.abmallback.model.MsgMemberMessage;
import com.cotton.abmallback.model.MsgMessageTemplate;
import com.cotton.abmallback.service.DistributionConfigService;
import com.cotton.abmallback.service.MsgMemberMessageService;
import com.cotton.abmallback.service.MsgMessageTemplateService;
import com.cotton.base.third.GeTuiService;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * MessageManagerImpl
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/6/26
 */
@Service
public class MessageManagerImpl implements MessageManager {

    private final MsgMemberMessageService msgMemberMessageService;

    private final MsgMessageTemplateService msgMessageTemplateService;

    private final DistributionConfigService distributionConfigService;

    private final GeTuiService geTuiService;

    private final WxMpService wxMpService;

    public MessageManagerImpl(MsgMemberMessageService msgMemberMessageService, MsgMessageTemplateService msgMessageTemplateService, DistributionConfigService distributionConfigService, GeTuiService geTuiService, WxMpService wxMpService) {
        this.msgMemberMessageService = msgMemberMessageService;
        this.msgMessageTemplateService = msgMessageTemplateService;
        this.distributionConfigService = distributionConfigService;
        this.geTuiService = geTuiService;
        this.wxMpService = wxMpService;
    }

    @Override
    public void sendSystemNotice(long systemMessageId) {


    }

    @Override
    public void sendShareAward(long memberId) {

        String context = "";
        insertMessage(memberId,"分享奖励",context,MessageTypeEnum.SHARE_AWARD,1,null);

    }

    @Override
    public void sendExecutiveAward(long memberId) {

        String context = "";
        insertMessage(memberId,"高管奖励",context,MessageTypeEnum.EXECUTIVE_AWARD,1,null);


    }

    @Override
    public void sendPromotionAward(long memberId,String promotionLevel) {

        String context = "";
        insertMessage(memberId,"高管奖励",context,MessageTypeEnum.PROMOTION_AWARD,1,promotionLevel);
    }


    private void insertMessage(long memberId, String title, String content, MessageTypeEnum messageTypeEnum,
                               long systemMessageId,String promotionLevel){

        //发送系统内部消息
        MsgMemberMessage msgMemberMessage = new MsgMemberMessage();
        msgMemberMessage.setMemberId(memberId);
        msgMemberMessage.setTitle(title);
        msgMemberMessage.setContent(content);
        msgMemberMessage.setType(messageTypeEnum.name());

        if(messageTypeEnum.equals(MessageTypeEnum.SYSTEM_NOTICE)){
            msgMemberMessage.setSystemMessageId(systemMessageId);
        }else if(messageTypeEnum.equals(MessageTypeEnum.PROMOTION_AWARD)){
            msgMemberMessage.setPromotionLevel(promotionLevel);
        }

        msgMemberMessageService.insert(msgMemberMessage);

        //发送个推消息
        geTuiService.pushMessage(title,content,memberId);

        //TODO:发送微信消息

    }

    private String buildContext(MessageTypeEnum messageTypeEnum){

        MsgMessageTemplate model = new MsgMessageTemplate();
        model.setType(messageTypeEnum.name());
        model.setIsDeleted(false);
        List<MsgMessageTemplate> msgMessageTemplates = msgMessageTemplateService.queryList(model);

        return "";
    }
}
