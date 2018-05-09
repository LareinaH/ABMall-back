package com.cotton.abmallback.web.controller.admin;

import com.cotton.abmallback.service.MemberService;
import com.cotton.abmallback.web.controller.ABMallAdminBaseController;
import com.cotton.base.common.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/memberManager")
public class MemberManagerController extends ABMallAdminBaseController {

    private Logger logger = LoggerFactory.getLogger(MemberManagerController.class);

    @Autowired
    private MemberService memberService;


    @ResponseBody
    @RequestMapping(value = "/example")
    public RestResponse<Map<String, Object>> example() {

        RestResponse<Map<String, Object>> restResponse = new RestResponse<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        restResponse.setData(map);

        //TODO:
        //restResponse.setCode(RestResponse);
        return restResponse;

    }

}