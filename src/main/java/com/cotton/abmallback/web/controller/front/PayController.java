package com.cotton.abmallback.web.controller.front;

import com.cotton.abmallback.manager.DistributionManager;
import com.cotton.abmallback.service.OrdersService;
import com.cotton.abmallback.third.wechat.JufenyunService;
import com.cotton.base.common.RestResponse;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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


    @Autowired
    private JufenyunService jufenyunService;

    @Autowired
    private WxMpService wxMpService;

    @ResponseBody
    @RequestMapping(value = "/example")
    public RestResponse<Map<String, Object>> example() {

        Map<String, Object> map = new HashMap<>(2);

        String eventKey = "qrscene_{\"referrerId\":2}";
        String jsonStr = eventKey.substring(eventKey.indexOf("{"),eventKey.indexOf("}"));

        logger.info(jsonStr);

        //distributionManager.orderDistribute("20180710073044029703");

        //String url = jufenyunService.sendRedpack("o8HRJ0zjXTdkOJZonIDTfWsuPH7I",new BigDecimal(0.4));
        //map.put("url",url);



        return RestResponse.getSuccesseResponse(map);
    }



}
