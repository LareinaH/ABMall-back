package com.cotton.abmallback.web.controller.front;

import com.cotton.abmallback.service.MemberAddressService;
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
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/5/10
 */

@Controller
@RequestMapping("/member")
public class MemberController extends ABMallFrontBaseController {

    private Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private MemberService memberService;


    @Autowired
    private MemberAddressService memberAddressService;


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


    /**
     * 绑定手机号
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/bindPhoneNum")
    public RestResponse<Map<String, Object>> bindPhoneNum() {

        RestResponse<Map<String, Object>> restResponse = new RestResponse<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        restResponse.setData(map);

        //TODO:
        //restResponse.setCode(RestResponse);
        return restResponse;

    }



    /**
     * 收货地址列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addressList")
    public RestResponse<Map<String, Object>> addressList() {

        RestResponse<Map<String, Object>> restResponse = new RestResponse<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        restResponse.setData(map);

        //TODO:
        //restResponse.setCode(RestResponse);
        return restResponse;

    }

    /**
     * 增加收货地址
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addAddress")
    public RestResponse<Map<String, Object>> addAddress() {

        RestResponse<Map<String, Object>> restResponse = new RestResponse<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        restResponse.setData(map);

        //TODO:
        //restResponse.setCod5e(RestResponse);
        return restResponse;

    }


    /**
     * 删除收货地址
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteAddress")
    public RestResponse<Map<String, Object>> deleteAddress() {

        RestResponse<Map<String, Object>> restResponse = new RestResponse<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        restResponse.setData(map);

        //TODO:
        //restResponse.setCod5e(RestResponse);
        return restResponse;

    }




}