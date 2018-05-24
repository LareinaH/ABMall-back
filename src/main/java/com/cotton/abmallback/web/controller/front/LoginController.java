package com.cotton.abmallback.web.controller.front;

import com.cotton.abmallback.model.Member;
import com.cotton.abmallback.service.MemberService;
import com.cotton.abmallback.web.controller.ABMallFrontBaseController;
import com.cotton.base.common.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    private final MemberService memberService;

    @Autowired
    public LoginController(MemberService memberService) {
        this.memberService = memberService;
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
    @RequestMapping(value = "/sendVerifyCode")
    public RestResponse<Map<String, Object>> sendVerifyCode() {

        Map<String, Object> map = new HashMap<>(2);

        return RestResponse.getSuccesseResponse(map);

    }




}