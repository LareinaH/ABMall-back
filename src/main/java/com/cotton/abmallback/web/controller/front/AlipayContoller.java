package com.cotton.abmallback.web.controller.front;

import com.cotton.abmallback.third.alibaba.alipay.AlipayServiceImpl;
import me.hao0.alipay.model.pay.AppPayDetail;
import me.hao0.alipay.model.pay.WapPayDetail;
import me.hao0.alipay.model.pay.WebPayDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AlipayContoller
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/6/21
 */
@Controller
@RequestMapping("/alipay")
public class AlipayContoller {

    private static final Logger logger = LoggerFactory.getLogger(AlipayContoller.class);

    private final AlipayServiceImpl alipayService;


    @Autowired
    public AlipayContoller(AlipayServiceImpl alipayService) {
        this.alipayService = alipayService;
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
            // ignore
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
            // ignore
        }
    }


    /**
     * APP支付
     */
    @RequestMapping("/app")
    public void appPay(@RequestParam("orderNumber") String orderNumber, HttpServletResponse resp){

        AppPayDetail detail = new AppPayDetail(orderNumber, "测试订单-" + orderNumber, "0.01","");
        String form = alipayService.appPay(detail);
        logger.info("wap pay form: {}", form);
        try {
            resp.setContentType("text/html;charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(form);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException e) {
            // ignore
        }
    }
}

