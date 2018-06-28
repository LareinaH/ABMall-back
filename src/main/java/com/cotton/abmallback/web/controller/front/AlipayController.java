package com.cotton.abmallback.web.controller.front;

import com.cotton.abmallback.model.Orders;
import com.cotton.abmallback.service.OrdersService;
import com.cotton.abmallback.third.alibaba.alipay.AlipayService;
import com.cotton.base.common.RestResponse;
import me.hao0.alipay.model.enums.AlipayField;
import me.hao0.alipay.model.enums.TradeStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * AlipayController
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/6/21
 */
@Controller
@RequestMapping("/alipay")
public class AlipayController {

    private static final Logger logger = LoggerFactory.getLogger(AlipayController.class);

    private final AlipayService alipayService;

    private final OrdersService ordersService;


    @Autowired
    public AlipayController(AlipayService alipayService, OrdersService ordersService) {
        this.alipayService = alipayService;
        this.ordersService = ordersService;
    }


    /**
     * APP支付
     */
    @ResponseBody
    @RequestMapping("/app")
    public RestResponse<Object> appPay2(@RequestParam("orderId") long orderId){

        //根据订单号获取订单信息
        Orders orders = ordersService.getById(orderId);

        if(null == orders){
            return RestResponse.getFailedResponse(500,"订单不存在");
        }

        Map<String, Object> result = alipayService.payWithAlipay(orders.getOrderNo(),orders.getTotalMoney());
        logger.info("app pay form: {}", result);

        return RestResponse.getSuccesseResponse(result);
    }


    /**
     * 支付宝服务器通知
     */
    @RequestMapping("/backend")
    public String backend(HttpServletRequest request){

        Map<String,String> params = new HashMap<>(10);
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        logger.info("backend notify params: {}", params);

        if(!alipayService.notifyVerify(params)) {

            logger.info("backend notify failed");
            return "FAIL";

        }

        String tradeStatus = params.get(AlipayField.TRADE_STATUS.field());
        if (TradeStatus.TRADE_FINISHED.value().equals(tradeStatus) || TradeStatus.TRADE_SUCCESS.value().equals(tradeStatus)) {

            //获取订单号
            String orderNo = params.get(AlipayField.OUT_TRADE_NO.field());

            String tradeNo = params.get(AlipayField.TRADE_NO.field());

            if (ordersService.paySuccess(orderNo, tradeNo, "Alipay")) {
                //TODO:分润

                logger.info("backend notify success");
                return "SUCCESS";
            }
        }
        logger.info("backend notify failed");

        return "FAIL";
    }

}

