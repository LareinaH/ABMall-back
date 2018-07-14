package com.cotton.abmallback.web.controller.front;

import com.cotton.abmallback.manager.DistributionManager;
import com.cotton.abmallback.manager.PromotionManager;
import com.cotton.abmallback.model.Member;
import com.cotton.abmallback.model.Orders;
import com.cotton.abmallback.service.MemberService;
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
import tk.mybatis.mapper.entity.Example;

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

    @Autowired
    private PromotionManager promotionManager;

    @Autowired
    private MemberService memberService;

    @ResponseBody
    @RequestMapping(value = "/example")
    public RestResponse<Map<String, Object>> example(String orderNo) {

        Map<String, Object> map = new HashMap<>(2);

    /*    String eventKey = "qrscene_{\"referrerId\":2}";
        String jsonStr = eventKey.substring(eventKey.indexOf("{"),eventKey.indexOf("}"));

        logger.info(jsonStr);

       Example example = new Example(Orders.class);

        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted",false);


        List<Orders> orders = ordersService.queryList(example);


        for(Orders orders1 :orders) {
            if(orders1.getId() > 43L) {
                distributionManager.orderDistribute(orders1.getOrderNo());
            }
        }*/

        //String url = jufenyunService.sendRedpack("o8HRJ0zjXTdkOJZonIDTfWsuPH7I",new BigDecimal(0.4));
        //map.put("url",url);

        //Member member = memberService.getById(8L);
        //promotionManager.memberPromotion(member,1);

        distributionManager.orderDistribute("20180714205905128280");



        return RestResponse.getSuccesseResponse(map);
    }



}
