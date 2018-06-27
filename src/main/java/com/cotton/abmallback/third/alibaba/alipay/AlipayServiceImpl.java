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

    private String appPrikey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDqSizbURHRoDmC\n"
            + "ma0vgqFfyRxXrM+bs1z+erGd7PvGLURWWS3hiGol0k69CiL1vza17SzIi4kLDC4V\n"
            + "4K9ApwAsyD9/tHq3Eg+YvY2jkZuBV98lJt1wJDKJkF7HcKopq73wdV1RpK5oO8RZ\n"
            + "IsUZtrW5KJ9/p/bqlFIRBsolxaUBZPb6WgKaN+1t1hO/c4Fuk0XhhmFQYYN8v79+\n"
            + "+Iik+rKF9huzPnxG8vruHq6hbSpVhKI1YOnPIojeFfgQiPP3AY6qf0f0ZqwNO1id\n"
            + "4S0bzMra4elDl6RibbxTQpH95PTm3xz183Cg/elC8GoiQl+vsRmYFcssnIJ9H+P+\n"
            + "ocsbGuIxAgMBAAECggEAdbf5W+Ua/+nym4VduE55iOhCvmbNFZ2ErzS0w/YL92JN\n"
            + "QNmkLPre4swN1fwe3r1J3xUheE5r+EoRadqwE9sVQmsHRMJJGm0Pux4a6cHCkbW2\n"
            + "IDOr9amqXuZUSP958GAjmotN7TTCkQreuC65PBdKrZMUuQ6LCQinR3jS3zG0m8LJ\n"
            + "8pAqae6jMcRdtGxEXCX8a/XdGK1U5ANOuX34D5Hg+S4zHQiyUA4mIwui9vVFAnZh\n"
            + "HoBuxJROeoGoCKj64wBqufNSfBQjWs26PNU4T4zJfygiE8yyN1MdaZ6envOwe+XN\n"
            + "nRNYEsTPa3408G2hE7y8eHJcvde3JtThqOKac/LH8QKBgQD+Lw5/qanW253gAsxS\n"
            + "o7Q1ddjqwmib/pFlAVzS4P+IeRbQ/H2hzE7mnDArjW/w8pdXNo6CTRJg/1VxXMC2\n"
            + "XcUTRhU04YOPcUOowfVzU9sNk/Ifr2b00aYmerOmrAXHUmUmF8IL0wMsPpKmAW6T\n"
            + "rACEs3OvihK3nlmhQioZJwuUZQKBgQDr9rqnMMM5e3+o6bCXZHkrccszCdBHdMnF\n"
            + "h2AJt085O6gZjCDZCvGmXdf7ZXZHMBpxCzO6jZWEAzpgW2Fn2iBBcJKvH82exlwz\n"
            + "WWj39jFfJXKmgm94j+wAkNsLFsTlw6ITZI55bc/ue4qc2vSLz7ZVPlOf+atIwVc3\n"
            + "bSjsW3673QKBgQCq28y+KbjdkVCFJLxdjGb1TJsb6sRQn4TyRUE1C0MZZHPe1OpK\n"
            + "GUCsKS8EB5XIe/kZCbYvhkklZFz1z6hGra9sbj6RBknd4P/e70njVOm5LcqiW9A0\n"
            + "Hry1vuMF1TopKoyNV4j7U8MdOY5wAiRnJUZP7SSFSaWdQdbz27ran3FcNQKBgQCB\n"
            + "SgZJt3EwnNd88NaejSHLSSWCiJ0Dmh04Sw23JSaWgHaB0QLqiZGQi5jdGWHubY//\n"
            + "YpjsXcmPtMkWpNtBMQY9dPYaWH2swpkgVZwrSU0SCg3A6HU1hP5V7QjoEYi/MCst\n"
            + "Hwrlw+KLlEuF2H7n4F7SZD0jyYQtcCpep0QmBZfyTQKBgQDJwvekrl3bEc5c7ZWw\n"
            + "EMrRynfYMmW7CuKjOikEYSYCM7aZhsJ31BSIOLBcT8ARr1ob8DAGbIV7FSfC697E\n"
            + "7BNFKW5hJ82RLe2qZn78rvYkebJ8p+22JEx4eGImHGFPaX7qbCClp6oXDSwLKFqm\n"
            + "I5bfaHvVreBm+RNCq+KevrkNxg==";

    private String appPubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6kos21ER0aA5gpmtL4Kh\n"
            + "X8kcV6zPm7Nc/nqxnez7xi1EVlkt4YhqJdJOvQoi9b82te0syIuJCwwuFeCvQKcA\n"
            + "LMg/f7R6txIPmL2No5GbgVffJSbdcCQyiZBex3CqKau98HVdUaSuaDvEWSLFGba1\n"
            + "uSiff6f26pRSEQbKJcWlAWT2+loCmjftbdYTv3OBbpNF4YZhUGGDfL+/fviIpPqy\n"
            + "hfYbsz58RvL67h6uoW0qVYSiNWDpzyKI3hX4EIjz9wGOqn9H9GasDTtYneEtG8zK\n"
            + "2uHpQ5ekYm28U0KR/eT05t8c9fNwoP3pQvBqIkJfr7EZmBXLLJyCfR/j/qHLGxri\n"
            + "MQIDAQAB";

    private Alipay alipay;

    @PostConstruct
    public void initAlipay(){
        alipay = AlipayBuilder
                .newBuilder(merchantId, secret).appPriKey(appPrikey).appPubKey(appPubKey)
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