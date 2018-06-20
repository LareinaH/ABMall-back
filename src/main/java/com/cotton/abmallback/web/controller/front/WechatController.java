package com.cotton.abmallback.web.controller.front;


import com.cotton.abmallback.model.Orders;
import com.cotton.abmallback.service.OrdersService;
import com.cotton.base.common.RestResponse;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;


/**
 * WechatController
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/6/15
 */

@Controller
@RequestMapping("/wechat")
public class WechatController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "wxPayService")
    private WxPayService wxPayService;

    @Autowired
    private OrdersService ordersService;

    /**
     * 统一下单(详见https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1)
     * 在发起微信支付前，需要调用统一下单接口，获取"预支付交易会话标识"
     * 接口地址：https://api.mch.weixin.qq.com/pay/unifiedorder
     *
     * @param orderId 订单id
     */
    @PostMapping("/pay/unifiedOrder")
    public RestResponse<WxPayUnifiedOrderResult> unifiedOrder(long orderId) throws WxPayException {

        //根据orderId 获取订单信息
        Orders orders = ordersService.getById(orderId);

        //构建WxPayUnifiedOrderRequest
        WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest();

        WxPayUnifiedOrderResult wxPayUnifiedOrderResult= this.wxPayService.unifiedOrder(request);

        return RestResponse.getSuccesseResponse(wxPayUnifiedOrderResult);
    }

}
