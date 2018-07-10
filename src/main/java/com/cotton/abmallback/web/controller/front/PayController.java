package com.cotton.abmallback.web.controller.front;

import com.cotton.abmallback.manager.DistributionManager;
import com.cotton.abmallback.service.OrdersService;
import com.cotton.base.common.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * PayController
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/6/21
 */
@RestController
@RequestMapping("/wechat/portal")
public class PayController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private DistributionManager distributionManager;

    @ResponseBody
    @RequestMapping(value = "/example")
    public RestResponse<Map<String, Object>> example() {

        Map<String, Object> map = new HashMap<>(2);

        distributionManager.orderDistribute("20180710073044029703");

        return RestResponse.getSuccesseResponse(map);
    }



}
