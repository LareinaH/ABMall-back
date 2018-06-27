package com.cotton.abmallback.web.controller.front;

import com.cotton.abmallback.model.Orders;
import com.cotton.abmallback.service.OrdersService;
import com.cotton.abmallback.third.alibaba.alipay.AlipayServiceImpl;
import com.cotton.base.common.RestResponse;
import me.hao0.alipay.model.AlipayFields;
import me.hao0.alipay.model.enums.AlipayField;
import me.hao0.alipay.model.enums.TradeStatus;
import me.hao0.alipay.model.pay.AppPayDetail;
import me.hao0.alipay.model.pay.WapPayDetail;
import me.hao0.alipay.model.pay.WebPayDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
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

    private final AlipayServiceImpl alipayService;

    private final OrdersService ordersService;


    @Autowired
    public AlipayController(AlipayServiceImpl alipayService, OrdersService ordersService) {
        this.alipayService = alipayService;
        this.ordersService = ordersService;
    }

    /**
     * WEB支付
     */
    @RequestMapping("/web")
    public void webPay(@RequestParam("orderNumber") String orderNumber, HttpServletResponse resp){

        WebPayDetail detail = new WebPayDetail(orderNumber, "测试订单-" + orderNumber, "0.01");
        String form = alipayService.webPay(detail);
        logger.info("web pay form: {}", form);
        try {
            resp.setContentType("text/html;charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(form);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException e) {
            logger.error("支付异常",e);
        }
    }

    /**
     * WAP支付
     */
    @RequestMapping("/wap")
    public void wapPay(@RequestParam("orderNumber") String orderNumber, HttpServletResponse resp){

        WapPayDetail detail = new WapPayDetail(orderNumber, "测试订单-" + orderNumber, "0.01");
        String form = alipayService.wapPay(detail);
        logger.info("wap pay form: {}", form);
        try {
            resp.setContentType("text/html;charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(form);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException e) {
            logger.error("支付异常",e);
        }
    }


    /**
     * APP支付
     */
    @ResponseBody
    @RequestMapping("/app")
    public RestResponse<Object> appPay(@RequestParam("orderId") long orderId){

        //根据订单号获取订单信息
        Orders orders = ordersService.getById(orderId);

        if(null == orders){
            return RestResponse.getFailedResponse(500,"订单不存在");
        }

        AppPayDetail detail = new AppPayDetail(orders.getOrderNo(), "订单", String.valueOf(orders.getTotalMoney()),"商品详情");
        String form = alipayService.appPay(detail);
        logger.info("wap pay form: {}", form);

        return RestResponse.getSuccesseResponse(form);
    }

    /**
     * 支付宝服务器通知
     */
    @RequestMapping("/backend")
    public String backend(HttpServletRequest request){
        Map<String, String> notifyParams = new HashMap<>(10);

        for (AlipayField f : AlipayFields.APP_PAY_NOTIFY){
            notifyParams.put(f.field(), request.getParameter(f.field()));
        }
        logger.info("backend notify params: {}", notifyParams);
        if (!alipayService.notifyVerifyMd5(notifyParams)){
            logger.error("backend sign verify failed");
            return "FAIL";
        }

        String tradeStatus = notifyParams.get(AlipayField.TRADE_STATUS.field());
        if (TradeStatus.TRADE_FINISHED.value().equals(tradeStatus)
                || TradeStatus.TRADE_SUCCESS.value().equals(tradeStatus)){

            //获取订单号
            String orderNo = notifyParams.get(AlipayField.OUT_TRADE_NO.field());

            String tradeNo = notifyParams.get(AlipayField.TRADE_NO.field());

            if(ordersService.paySuccess(orderNo,tradeNo,"Alipay")){
                //TODO:分润


                logger.info("backend notify success");
                return "SUCCESS";
            }
        }

        return "FAIL";
    }


}

