package com.cotton.abmallback.web.controller.front;

import com.cotton.abmallback.service.OrdersService;
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
 * OrdersController
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/5/10
 */

@Controller
@RequestMapping("/orders")
public class OrdersController extends ABMallFrontBaseController {

    private Logger logger = LoggerFactory.getLogger(OrdersController.class);

    @Autowired
    private OrdersService ordersService;

    @ResponseBody
    @RequestMapping(value = "/example")
    public RestResponse<Map<String, Object>> example() {

        Map<String, Object> map = new HashMap<>(2);

        return RestResponse.getSuccesseResponse(map);

    }

    /**
     * 订单列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/ordersList")
    public RestResponse<Map<String, Object>> ordersList() {


        Map<String, Object> map = new HashMap<>(2);

        return RestResponse.getSuccesseResponse(map);

    }


    /**
     * 确认收货
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/confirmReceipt")
    public RestResponse<Map<String, Object>> confirmReceipt() {


        Map<String, Object> map = new HashMap<>(2);

        return RestResponse.getSuccesseResponse(map);
    }

    /**
     * 取消订单
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cancelOrder")
    public RestResponse<Map<String, Object>> cancelOrder() {


        Map<String, Object> map = new HashMap<>(2);

        return RestResponse.getSuccesseResponse(map);

    }

    /**
     * 查看物流
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/showLogistics")
    public RestResponse<Map<String, Object>> showLogistics() {


        Map<String, Object> map = new HashMap<>(2);

        return RestResponse.getSuccesseResponse(map);

    }
}