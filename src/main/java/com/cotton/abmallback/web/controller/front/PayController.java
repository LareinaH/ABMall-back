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

        //distributionManager.orderDistribute("20180710073044029703");

        //String url = jufenyunService.sendRedpack("o8HRJ0zjXTdkOJZonIDTfWsuPH7I",new BigDecimal(0.4));
        //map.put("url",url);

        WxMpTemplateMessage mpTemplateMessage = new WxMpTemplateMessage();
        mpTemplateMessage.setToUser("o8HRJ0zjXTdkOJZonIDTfWsuPH7I");
        mpTemplateMessage.setTemplateId("tT2nGgVk-m4R-oCylqHHmbSsSRNJVFy2tnJvqklOgYY");
        mpTemplateMessage.setUrl("https://www.jufenyun.com/ticket/b041f1b83c41854cdfa673c65bd97a6a");
        List<WxMpTemplateData> list = new ArrayList<>();
        WxMpTemplateData data1 = new WxMpTemplateData("first","客官您好,您在云鼎绿色的返利提现，已经飞奔而来了。");
        list.add(data1);
        WxMpTemplateData data2 = new WxMpTemplateData("keyword1","2018年07月12日");
        list.add(data2);
        WxMpTemplateData data3 = new WxMpTemplateData("keyword2","￥100000000.0元");
        list.add(data3);
        WxMpTemplateData data4 = new WxMpTemplateData("remark","点击查看,赶快领取吧！");
        list.add(data4);
        mpTemplateMessage.setData(list);

        try {
            String string =wxMpService.getTemplateMsgService().sendTemplateMsg(mpTemplateMessage);
            logger.info(string);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        return RestResponse.getSuccesseResponse(map);
    }



}
