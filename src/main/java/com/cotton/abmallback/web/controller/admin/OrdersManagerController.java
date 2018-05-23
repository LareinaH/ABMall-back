package com.cotton.abmallback.web.controller.admin;

import com.cotton.abmallback.service.OrdersService;
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

/**
 * Orders
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/5/9
 */
@Controller
@RequestMapping("/ordersManager")
public class OrdersManagerController extends ABMallAdminBaseController {

    private Logger logger = LoggerFactory.getLogger(OrdersManagerController.class);

    @Autowired
    private OrdersService ordersService;



    @ResponseBody
    @RequestMapping(value = "/example")
    public RestResponse<Map<String, Object>> example() {


        Map<String, Object> map = new HashMap<>(2);

        return RestResponse.getSuccesseResponse(map);

    }

}