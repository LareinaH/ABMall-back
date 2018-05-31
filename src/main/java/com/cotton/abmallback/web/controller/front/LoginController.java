package com.cotton.abmallback.web.controller.front;

import com.cotton.abmallback.manager.SmsManager;
import com.cotton.abmallback.model.Member;
import com.cotton.abmallback.service.MemberService;
import com.cotton.abmallback.web.controller.ABMallFrontBaseController;
import com.cotton.base.common.RestResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Login
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/5/9
 */
@Controller
@RequestMapping("/member")
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
    @RequestMapping(value = "/login")
    public RestResponse<Member> login() {



        return RestResponse.getSuccesseResponse();
    }


    @ResponseBody
    @RequestMapping(value = "/logout")
    public RestResponse<Map<String, Object>> logout() {


        Map<String, Object> map = new HashMap<>(2);

        return RestResponse.getSuccesseResponse(map);

    }

    @ResponseBody
    @RequestMapping(value = "/sendVerifyCode",method = {RequestMethod.POST})
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
}