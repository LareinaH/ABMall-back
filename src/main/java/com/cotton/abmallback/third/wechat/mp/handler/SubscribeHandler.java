package com.cotton.abmallback.third.wechat.mp.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cotton.abmallback.enumeration.MemberLevelEnum;
import com.cotton.abmallback.model.Member;
import com.cotton.abmallback.service.MemberService;
import com.cotton.abmallback.third.wechat.mp.builder.TextBuilder;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpTemplateMsgService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class SubscribeHandler extends AbstractHandler {

  private final MemberService memberService;




  public SubscribeHandler(MemberService memberService) {
    this.memberService = memberService;
    }

  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService weixinService, WxSessionManager sessionManager) throws WxErrorException {


    // 获取微信用户基本信息
    WxMpUser userWxInfo = weixinService.getUserService().userInfo(wxMessage.getFromUser(), null);

    this.logger.info("关注用户" + userWxInfo);

    if (userWxInfo != null) {
      //查看用户是否存在
      Member model = new Member();
      model.setUnionId(userWxInfo.getUnionId());
      model.setIsDeleted(false);

      List<Member> memberList = memberService.queryList(model);

      if (null != memberList && memberList.size() > 0) {
        this.logger.info("该用户已经存在: " + wxMessage.getFromUser());

        Member member = memberList.get(0);
        if (null == member.getReferrerId()) {
          getRefferUser(wxMessage, member);
          memberService.update(member);
        }

        sendWxMessage(member.getOpenId(),member.getName());

      } else {

        //注册新用户
        Member newMember = new Member();
        newMember.setUnionId(userWxInfo.getUnionId());
        newMember.setOpenId(userWxInfo.getOpenId());
        newMember.setName(userWxInfo.getNickname());
        newMember.setWechatName(userWxInfo.getNickname());
        newMember.setIsDeleted(false);
        newMember.setLevel(MemberLevelEnum.WHITE.name());
        newMember.setPhoto(userWxInfo.getHeadImgUrl());

        //获取引荐人信息
        getRefferUser(wxMessage, newMember);

        memberService.insert(newMember);

        sendWxMessage(newMember.getOpenId(),newMember.getName());

      }

      try {
        return new TextBuilder().build("感谢关注绿色云鼎公众号！", wxMessage, weixinService);
      } catch (Exception e) {
        this.logger.error(e.getMessage(), e);
      }
    }

    return new TextBuilder().build("感谢关注绿色云鼎公众号！", wxMessage, weixinService);
  }

  private void getRefferUser(WxMpXmlMessage wxMessage, Member member) {
    String eventKey = wxMessage.getEventKey();

    this.logger.info("eventKey: " + eventKey);

    if (!StringUtils.isBlank(eventKey)) {

      String jsonStr = eventKey.substring(eventKey.indexOf("{"),eventKey.indexOf("}"));

      JSONObject jsonObject = JSON.parseObject(jsonStr);

      if (null != jsonObject && jsonObject.get("referrerId") != null) {
        member.setReferrerId(Long.valueOf(jsonObject.get("referrerId").toString()));
      }

    }
  }

  private boolean sendWxMessage(String memberOpenId,String memberName) {
/*    WxMpTemplateMessage mpTemplateMessage = new WxMpTemplateMessage();
    mpTemplateMessage.setToUser(memberOpenId);
    mpTemplateMessage.setTemplateId("eD6ie0CFoDVS7S0y0SJCStPLgJgpoOGRCJadsAj0his");
    List<WxMpTemplateData> list = new ArrayList<>();
    WxMpTemplateData data1 = new WxMpTemplateData("first", "感谢您关注绿色云鼎,恭喜您已经成为我们的会员");
    list.add(data1);
    WxMpTemplateData data2 = new WxMpTemplateData("keyword1", memberName);
    list.add(data2);
    WxMpTemplateData data3 = new WxMpTemplateData("keyword2", LocalDateTime.now().toString());
    list.add(data3);
    WxMpTemplateData data4 = new WxMpTemplateData("remark", "赶快开启您新的旅程吧！");
    list.add(data4);
    mpTemplateMessage.setData(list);

    try {
      String string = wxMpTemplateMsgService.sendTemplateMsg(mpTemplateMessage);
      logger.info(string);

      return true;
    } catch (WxErrorException e) {
      logger.error("发送消息失败", e);
    }*/
    return false;
  }
}
