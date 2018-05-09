package com.cotton.abmallback.web.controller.front;

import com.cotton.abmallback.service.CashPickUpService;
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
 * Cash
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/5/10
 */
@Controller
@RequestMapping("/cash")
public class CashController extends ABMallFrontBaseController {

    private Logger logger = LoggerFactory.getLogger(CashController.class);

    @Autowired
    private CashPickUpService cashPickUpService;

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
     * 提现记录
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cashPickUpList")
    public RestResponse<Map<String, Object>> cashPickUpList() {

        RestResponse<Map<String, Object>> restResponse = new RestResponse<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        restResponse.setData(map);

        //TODO:
        //restResponse.setCode(RestResponse);
        return restResponse;

    }

    /**
     * 提现记录
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/applyPickUpCash")
    public RestResponse<Map<String, Object>> applyPickUpCash() {

        RestResponse<Map<String, Object>> restResponse = new RestResponse<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        restResponse.setData(map);

        //TODO:
        //restResponse.setCode(RestResponse);
        return restResponse;

    }

}