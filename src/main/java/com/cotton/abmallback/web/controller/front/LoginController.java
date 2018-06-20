package com.cotton.abmallback.web.controller.front;

import com.cotton.abmallback.enumeration.DeviceType;
import com.cotton.abmallback.enumeration.MemberLevelEnum;
import com.cotton.abmallback.manager.SmsManager;
import com.cotton.abmallback.model.Member;
import com.cotton.abmallback.model.vo.front.LoginMemberVO;
import com.cotton.abmallback.service.MemberService;
import com.cotton.abmallback.web.controller.ABMallFrontBaseController;
import com.cotton.base.common.RestResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

/**
 * Login
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/5/9
 */
@Controller
public class LoginController extends ABMallFrontBaseController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    private MemberService memberService;

    private SmsManager smsManager;

    @Autowired
    public LoginController(MemberService memberService, SmsManager smsManager) {
        this.memberService = memberService;
        this.smsManager = smsManager;
    }

    @ResponseBody
    @RequestMapping(value = "un/member/login",method = {RequestMethod.GET})
    public RestResponse<LoginMemberVO> login(@RequestParam(defaultValue = "false") boolean bWechat,
                                      @RequestParam(required = false)  String phoneNum,
                                      @RequestParam(required = false)  String code,
                                      @RequestParam(required = false)  String unionId,
                                      @RequestParam(required = false)  String openId,
                                      @RequestParam(required = false)  String headImageUrl,
                                      @RequestParam(required = false)  String nickname,
                                      @RequestHeader(value = "DEVICE-TYPE", defaultValue = "IOS") String deviceType){

        if(!DeviceType.IOS.name().equalsIgnoreCase(deviceType) && !DeviceType.ANDROID.name().equalsIgnoreCase(deviceType)){

            return RestResponse.getFailedResponse(500,"请填入正确的设备类型");
        }

        if(bWechat){
            if (StringUtils.isBlank(unionId) || StringUtils.isBlank(openId)) {
                return RestResponse.getFailedResponse(1, "unionId,openId 不能为空!");
            }

            return loginWeChatApp(unionId,openId,headImageUrl,nickname,deviceType);
        }else {
            if(StringUtils.isBlank(phoneNum) || StringUtils.isBlank(code)){
                return RestResponse.getFailedResponse(1, "手机号和验证码不能为空!");
            }
            return loginMobile(phoneNum,code,deviceType);
        }
    }

    /**
     * 手机登录
     * @return RestResponse
     */
    private RestResponse<LoginMemberVO> loginMobile(String phoneNum,String code,String deviceType) {

        //校验验证码
    /*    if(!smsManager.checkCaptcha(phoneNum,code)){
            return RestResponse.getFailedResponse(500,"验证码错误");
        }*/
        //根据手机号查找用户
        Member model = new Member();
        model.setPhoneNum(phoneNum);
        model.setIsDeleted(false);

        Member member = memberService.selectOne(model);

        //仅仅通过手机号不能注册
        if(member != null){

            String token =  UUID.randomUUID().toString();

            if(deviceType.equalsIgnoreCase(DeviceType.IOS.name())){
                member.setTokenIos(token);
            }else {
                member.setTokenAndroid(token);
            }

            memberService.update(member);

            return RestResponse.getSuccesseResponse(translateLoginVO(member,token));
        }else {
            return RestResponse.getFailedResponse(1,"该手机号用户不存在");
        }

    }

    /**
     * APP微信授权登录
     * @return RestResponse
     */
    private RestResponse<LoginMemberVO> loginWeChatApp(String unionId,String openId,String headImageUrl, String nickname, String deviceType) {


        //根据openid号查找用户
        Member model = new Member();
        model.setOpenId(openId);
        model.setUnionId(unionId);
        model.setIsDeleted(false);

        Member member = memberService.selectOne(model);

        String token =  UUID.randomUUID().toString();

        if(member != null){

            if(deviceType.equalsIgnoreCase(DeviceType.IOS.name())){
                member.setTokenIos(token);
            }else {
                member.setTokenAndroid(token);
            }
            memberService.update(member);

            return RestResponse.getSuccesseResponse(translateLoginVO(member,token));
        }else {

            //如果member不存在,根据微信自动驻车一个member
            Member newMember = new Member();
            newMember.setUnionId(unionId);
            newMember.setOpenId(openId);
            newMember.setName(nickname);
            newMember.setWechatName(nickname);
            newMember.setIsDeleted(false);
            newMember.setLevel(MemberLevelEnum.WHITE.name());
            newMember.setPhoto(headImageUrl);

            if(deviceType.equalsIgnoreCase(DeviceType.IOS.name())){
                newMember.setTokenIos(token);
            }else {
                newMember.setTokenAndroid(token);
            }

            if(memberService.insert(newMember)){

                return RestResponse.getSuccesseResponse(translateLoginVO(newMember,token));
            }

            return RestResponse.getFailedResponse(1,"系统异常,登录失败");
        }

    }


    /**
     * 退出登录
     * @return RestResponse
     */
    @ResponseBody
    @RequestMapping(value = "/member/logout",method = {RequestMethod.GET})
    public RestResponse<Void> logout(@RequestParam String deviceType,
                                     @RequestParam boolean bWechat) {

        Member member = memberService.getById(getCurrentMemberId());

        if(deviceType.equalsIgnoreCase(DeviceType.IOS.name())){
            if(bWechat){
                member.setTokenIosWechat("-");
            }else {
                member.setTokenIos("-");
            }
        }else {
            if(bWechat){
                member.setTokenAndroidWechat("-");
            }else {
                member.setTokenAndroid("-");
            }
        }

        return RestResponse.getSuccesseResponse();

    }

    /**
     * 发送验证码
     * @param params 手机号
     * @return RestResponse
     */
    @ResponseBody
    @RequestMapping(value = "un/member/sendVerifyCode",method = {RequestMethod.POST})
    public RestResponse<Void> sendVerifyCode(@RequestBody Map<String,Object> params) {

        String phoneNum = params.get("phoneNum").toString();

        if(StringUtils.isBlank(phoneNum)){
            return RestResponse.getFailedResponse(500,"手机号码不能为空!");
        }

        if(smsManager.sendCaptcha(phoneNum)) {

            return RestResponse.getSuccesseResponse();
        }else {
            return RestResponse.getFailedResponse(500,"验证码发送失败!");
        }
    }

    private LoginMemberVO translateLoginVO(Member member,String token){
        LoginMemberVO loginMemberVO = new LoginMemberVO();

        loginMemberVO.setName(member.getName());
        loginMemberVO.setLevel(member.getLevel());
        loginMemberVO.setPhoto(member.getPhoto());
        loginMemberVO.setSex(member.getSex());
        loginMemberVO.setBindPhone(StringUtils.isNotBlank(member.getPhoneNum()));
        loginMemberVO.setTicket(token);

        return loginMemberVO;
    }
}