package com.cotton.abmallback.third.alibaba.alipay;

import me.hao0.alipay.core.Alipay;
import me.hao0.alipay.core.AlipayBuilder;
import me.hao0.alipay.model.pay.AppPayDetail;
import me.hao0.alipay.model.pay.WapPayDetail;
import me.hao0.alipay.model.pay.WebPayDetail;
import me.hao0.alipay.model.refund.RefundDetail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * AlipayService
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/6/26
 */
@Service
public class AlipayServiceImpl implements AlipayService{


    @Value("${alipay.appId}")
    private String appId;

    @Value("${alipay.merchantId}")
    private String merchantId;

    @Value("${alipay.secret}")
    private String secret;

    @Value("${alipay.payNotifyUrl}")
    private String payNotifyUrl;

    @Value("${alipay.refundNotifyUrl}")
    private String refundNotifyUrl;

    @Value("${alipay.webReturnUrl}")
    private String webReturnUrl;

    @Value("${alipay.wapReturnUrl}")
    private String wapReturnUrl;

    private Alipay alipay;

    @PostConstruct
    public void initAlipay(){
        alipay = AlipayBuilder
                .newBuilder(merchantId, secret)
                .build();

        System.err.println(alipay);
    }

    /**
     * web支付
     */
    @Override
    public String webPay(WebPayDetail detail){
        detail.setNotifyUrl(payNotifyUrl);
        detail.setReturnUrl(webReturnUrl);
        return alipay.pay().webPay(detail);
    }

    /**
     * wap支付
     */
    @Override
    public String wapPay(WapPayDetail detail) {
        detail.setNotifyUrl(payNotifyUrl);
        detail.setReturnUrl(wapReturnUrl);
        return alipay.pay().wapPay(detail);
    }


    /**
     * app支付
     */
    @Override
    public String appPay(AppPayDetail detail) {
        detail.setAppId(appId);
        detail.setNotifyUrl(payNotifyUrl);
        detail.setReturnUrl(wapReturnUrl);
        return alipay.pay().appPay(detail);
    }

    /**
     * MD5验证
     */
    public Boolean notifyVerifyMd5(Map<String, String> params){
        return alipay.verify().md5(params);
    }

    /**
     * 退款申请
     */
    public Boolean refund(RefundDetail detail){
        detail.setNotifyUrl(refundNotifyUrl);
        return alipay.refund().refund(detail);
    }
}