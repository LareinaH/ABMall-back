package com.cotton.abmallback.manager.impl;

import com.cotton.abmallback.enumeration.MessageTypeEnum;
import com.cotton.abmallback.manager.MessageManager;
import com.cotton.abmallback.model.MsgMemberMessage;
import com.cotton.abmallback.service.MsgMemberMessageService;
import com.cotton.abmallback.service.MsgMessageTemplateService;
import com.cotton.base.third.GeTuiService;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.stereotype.Service;

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

    private final GeTuiService geTuiService;

    private final WxMpService wxMpService;

    public MessageManagerImpl(MsgMemberMessageService msgMemberMessageService, MsgMessageTemplateService msgMessageTemplateService, GeTuiService geTuiService, WxMpService wxMpService) {
        this.msgMemberMessageService = msgMemberMessageService;
        this.msgMessageTemplateService = msgMessageTemplateService;
        this.geTuiService = geTuiService;
        this.wxMpService = wxMpService;
    }

    @Override
    public void sendSystemNotice(long systemMessageId) {

    }

    @Override
    public void sendShareAward(long memberId) {

        geTuiService.pushMessage("分享奖励","hahah",memberId);

    }

    @Override
    public void sendExecutiveAward(long memberId) {

    }

    @Override
    public void sendPromotionAward(long memberId) {

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
}
