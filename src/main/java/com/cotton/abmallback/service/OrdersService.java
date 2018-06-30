package com.cotton.abmallback.service;

import com.cotton.base.service.BaseService;
import com.cotton.abmallback.model.Orders;

/**
 * Orders
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/5/9
 */
public interface OrdersService extends BaseService<Orders> {

    /**
     * 付款成功
     */
    boolean paySuccess(String orderNo,String tradeNo,String payMode);

    void  systemCancelOrder();

    void  systemConfirmedOrder();
}
