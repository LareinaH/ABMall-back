package com.cotton.abmallback.third.wechat;

import com.github.binarywang.wxpay.bean.request.WxPaySendRedpackRequest;
import com.github.binarywang.wxpay.bean.result.WxPayRedpackQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPaySendRedpackResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * WechatRedpackServiceImpl
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/9/23
 */
@Service
public class WechatRedpackServiceImpl implements WechatRedpackService {

    private Logger logger = LoggerFactory.getLogger(WechatRedpackServiceImpl.class);

    @Resource(name = "wxMpPayService")
    private WxPayService wxPayService;

    @Override
    public JufenyunResultObject sendRedpack(String openId, BigDecimal money) {

        WxPaySendRedpackRequest request = new WxPaySendRedpackRequest();
        Integer moneyInt = money.multiply(new BigDecimal(100)).intValue();
        request.setTotalAmount( moneyInt);
        request.setTotalNum(1);
        String reqtick = String.valueOf(System.currentTimeMillis()/1000);
        request.setMchBillNo(reqtick);
        request.setSendName("绿色云鼎");
        request.setWishing("恭喜领取红包");
        request.setReOpenid(openId);
        request.setClientIp("47.104.174.30");
        request.setActName("福利来啦");
        request.setRemark("提现红包");
        try {
            WxPaySendRedpackResult wxPaySendRedpackResult =  wxPayService.sendRedpack(request);

            if(wxPaySendRedpackResult.getResultCode().equalsIgnoreCase("success")) {

                JufenyunResultObject resultObject = new JufenyunResultObject();
                resultObject.setRedpack_sn(wxPaySendRedpackResult.getMchBillno());

                return resultObject;
            }else {
                JufenyunResultObject resultObject = new JufenyunResultObject();
                resultObject.setMessage(wxPaySendRedpackResult.getReturnMsg() + wxPaySendRedpackResult.getErrCodeDes());
                logger.error("发送红包失败" + wxPaySendRedpackResult.getErrCodeDes());
                return resultObject;
            }

        } catch (WxPayException e) {
            JufenyunResultObject resultObject = new JufenyunResultObject();
            resultObject.setMessage(e.toString());

            logger.error("发送红包失败",e);
        }


        return null;
    }

    @Override
    public JufenyunResultObject getRedpackInfo(String ticket) {

        try {
            WxPayRedpackQueryResult redpackQueryResult = wxPayService.queryRedpack(ticket);

            if(redpackQueryResult.getResultCode().equalsIgnoreCase("success")){

                //查询红包状态
                String statusStr = redpackQueryResult.getStatus();

                int status = 5;

                if(statusStr.equalsIgnoreCase("RECEIVED")){
                    status = 1;
                }else if(statusStr.equalsIgnoreCase("RFUND_ING")){
                    status = 2;
                }else if(statusStr.equalsIgnoreCase("REFUND")){
                    status = 3;
                } else if(statusStr.equalsIgnoreCase("FAILED")){
                    status = 0;
                }

                JufenyunResultObject jufenyunResultObject = new JufenyunResultObject();
                JufenyunResultObject.Redpack redpack = new JufenyunResultObject.Redpack();
                jufenyunResultObject.setRedpack(redpack);
                redpack.setStatus(status);


            }else {
                logger.error("查询红包失败" + redpackQueryResult.getErrCodeDes());

            }
        } catch (WxPayException e) {
            logger.error("查询红包失败",e);
        }
        return null;
    }


}
