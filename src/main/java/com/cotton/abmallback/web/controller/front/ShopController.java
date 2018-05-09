package com.cotton.abmallback.web.controller.front;

import com.cotton.abmallback.service.AdsService;
import com.cotton.abmallback.service.GoodsGroupService;
import com.cotton.abmallback.service.GoodsService;
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
 * Shop
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/5/9
 */
@Controller
@RequestMapping("/shop")
public class ShopController extends ABMallFrontBaseController {

    private Logger logger = LoggerFactory.getLogger(ShopController.class);

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsGroupService goodsGroupService;

    @Autowired
    private AdsService adsService;



    /**
     * 首页
     * @return
     */
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
     * 首页
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/index")
    public RestResponse<Map<String, Object>> index() {

        RestResponse<Map<String, Object>> restResponse = new RestResponse<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();

        restResponse.setData(map);


        //TODO:
        //restResponse.setCode(RestResponse);
        return restResponse;

    }

    /**
     * 商品列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/goodsList")
    public RestResponse<Map<String, Object>> goodsList() {

        RestResponse<Map<String, Object>> restResponse = new RestResponse<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();



        restResponse.setData(map);


        //TODO:
        //restResponse.setCode(RestResponse);
        return restResponse;

    }



    /**
     * 商品详情
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/goodsDetail")
    public RestResponse<Map<String, Object>> goodsDetail(String goodsId) {

        RestResponse<Map<String, Object>> restResponse = new RestResponse<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();



        restResponse.setData(map);


        //TODO:
        //restResponse.setCode(RestResponse);
        return restResponse;

    }

}