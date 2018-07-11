package com.cotton.abmallback.third.wechat.mp.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cotton.abmallback.enumeration.MemberLevelEnum;
import com.cotton.abmallback.model.Member;
import com.cotton.abmallback.service.MemberService;
import com.cotton.abmallback.third.wechat.mp.builder.TextBuilder;
import com.cotton.base.enumeration.Sex;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class SubscribeHandler extends AbstractHandler {

  private final MemberService memberService;

  @Autowired
  public SubscribeHandler(MemberService memberService) {
    this.memberService = memberService;
  }

  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                  Map<String, Object> context, WxMpService weixinService,
                                  WxSessionManager sessionManager) throws WxErrorException {

    this.logger.info("新关注用户 OPENID: " + wxMessage.getFromUser());

    // 获取微信用户基本信息
    WxMpUser userWxInfo = weixinService.getUserService()
        .userInfo(wxMessage.getFromUser(), null);

    if (userWxInfo != null) {
      //查看用户是否存在
      Member model = new Member();
      model.setUnionId(userWxInfo.getUnionId());
      model.setIsDeleted(false);

      List<Member> memberList =  memberService.queryList(model);

      if(null != memberList && memberList.size()> 0){
        this.logger.info("该用户已经存在: " + wxMessage.getFromUser());

        Member member = memberList.get(0);
        if( null == member.getReferrerId()){
          getRefferUser(wxMessage, member);
          memberService.update(member);
        }

      }else {

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

      }
    }

    WxMpXmlOutMessage responseResult = null;
    try {
      responseResult = handleSpecial(wxMessage);
    } catch (Exception e) {
      this.logger.error(e.getMessage(), e);
    }

    if (responseResult != null) {
      return responseResult;
    }

    try {
      return new TextBuilder().build("感谢关注", wxMessage, weixinService);
    } catch (Exception e) {
      this.logger.error(e.getMessage(), e);
    }

    return null;
  }

  private void getRefferUser(WxMpXmlMessage wxMessage, Member member) {
    String eventKey =  wxMessage.getEventKey();

    this.logger.info("eventKey: " + eventKey);

    if(!StringUtils.isBlank(eventKey)){
      JSONObject jsonObject = JSON.parseObject(eventKey);

      if(null != jsonObject && jsonObject.get("referrerId") != null){
        member.setReferrerId(Long.valueOf(jsonObject.get("referrerId").toString()));
      }

    }
  }

  /**
   * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
   */
  private WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage)
      throws Exception {
    //TODO
    return null;
  }

}
