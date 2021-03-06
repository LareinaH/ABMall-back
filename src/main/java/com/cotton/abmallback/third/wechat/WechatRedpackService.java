package com.cotton.abmallback.third.wechat;

import java.math.BigDecimal;

/**
 * WechatRedpackService
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/9/23
 */
public interface WechatRedpackService {

    /**
     * 发送红包
     * @param openId
     * @param money
     * @return
     */
    JufenyunResultObject sendRedpack(String openId, BigDecimal money);

    JufenyunResultObject getRedpackInfo(String redpack_sn);
}
