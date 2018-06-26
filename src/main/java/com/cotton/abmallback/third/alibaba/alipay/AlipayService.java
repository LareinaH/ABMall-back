package com.cotton.abmallback.third.alibaba.alipay;

import me.hao0.alipay.model.pay.AppPayDetail;
import me.hao0.alipay.model.pay.WapPayDetail;
import me.hao0.alipay.model.pay.WebPayDetail;

/**
 * AlipayService
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/6/26
 */
public interface AlipayService {

    /**
     * webPay
     * @param detail 入参
     * @return string
     */
     String webPay(WebPayDetail detail);

    /**
     * wapPay
     * @param detail 入参
     * @return string
     */
     String wapPay(WapPayDetail detail);

    /**
     * appPay
     * @param detail 入参
     * @return string
     */
     String appPay(AppPayDetail detail);
}
