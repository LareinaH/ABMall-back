package com.cotton.abmallback.web.controller.front;

import com.cotton.abmallback.third.wechat.mp.config.WechatMpProperties;
import com.cotton.base.common.RestResponse;
import com.cotton.base.controller.BaseController;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * WechatMpController
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/6/20
 */
@Controller
@RequestMapping("/wechat/portal")
public class WechatMpController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final WxMpService wxService;

    private final WxMpMessageRouter router;

    private final WechatMpProperties wechatMpProperties;

    @Autowired
    public WechatMpController(WxMpService wxService, WxMpMessageRouter router, WechatMpProperties wechatMpProperties) {
        this.wxService = wxService;
        this.router = router;
        this.wechatMpProperties = wechatMpProperties;
    }

    @GetMapping(produces = "text/plain;charset=utf-8")
    public void authGet(HttpServletResponse httpServletResponse,
            @RequestParam(name = "signature",
                    required = false) String signature,
            @RequestParam(name = "timestamp",
                    required = false) String timestamp,
            @RequestParam(name = "nonce", required = false) String nonce,
            @RequestParam(name = "echostr", required = false) String echostr) {

        this.logger.info("\n接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", signature,
                timestamp, nonce, echostr);

        if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
            throw new IllegalArgumentException("请求参数非法，请核实!");
        }

        try {
            PrintWriter printWriter =httpServletResponse.getWriter();


            if (this.wxService.checkSignature(timestamp, nonce, signature)) {

                logger.info("\n 输出的参数为: " + echostr);


                printWriter.write(echostr);
                return;
            }

            logger.error("\n 校验失败");

            printWriter.write("校验失败");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping(produces = "application/xml; charset=UTF-8")
    public String post(@RequestBody String requestBody,
                       @RequestParam("signature") String signature,
                       @RequestParam("timestamp") String timestamp,
                       @RequestParam("nonce") String nonce,
                       @RequestParam(name = "encrypt_type",
                               required = false) String encType,
                       @RequestParam(name = "msg_signature",
                               required = false) String msgSignature) {
        this.logger.info(
                "\n接收微信请求：[signature=[{}], encType=[{}], msgSignature=[{}],"
                        + " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
                signature, encType, msgSignature, timestamp, nonce, requestBody);

        if (!this.wxService.checkSignature(timestamp, nonce, signature)) {
            throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
        }

        String out = null;
        if (encType == null) {
            // 明文传输的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
            WxMpXmlOutMessage outMessage = this.route(inMessage);
            if (outMessage == null) {
                return "";
            }

            out = outMessage.toXml();
        } else if ("aes".equals(encType)) {
            // aes加密的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(
                    requestBody, this.wxService.getWxMpConfigStorage(), timestamp,
                    nonce, msgSignature);
            this.logger.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
            WxMpXmlOutMessage outMessage = this.route(inMessage);
            if (outMessage == null) {
                return "";
            }

            out = outMessage
                    .toEncryptedXml(this.wxService.getWxMpConfigStorage());
        }

        this.logger.debug("\n组装回复信息：{}", out);

        return out;
    }


    @ResponseBody
    @RequestMapping(value = "/config", method = {RequestMethod.GET})
    public RestResponse<WxJsapiSignature> getConfig(String url){
        try {
            WxJsapiSignature signature = wxService.createJsapiSignature(url);
            return RestResponse.getSuccesseResponse(signature);
        } catch (WxErrorException e) {
            this.logger.error(e.getMessage(), e);
            return RestResponse.getFailedResponse(500,"获取配置失败");
        }
    }

    private WxMpXmlOutMessage route(WxMpXmlMessage message) {
        try {
            return this.router.route(message);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        return null;
    }
}
