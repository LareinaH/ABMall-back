package com.cotton.abmallback.service.impl;

import com.cotton.abmallback.enumeration.OrderStatusEnum;
import com.cotton.base.service.impl.BaseServiceImpl;
import com.cotton.abmallback.model.Orders;
import com.cotton.abmallback.service.OrdersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Orders
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/5/9
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrdersServiceImpl extends BaseServiceImpl<Orders> implements OrdersService {
    @Override
    public boolean paySuccess(String orderNo, String tradeNo, String payMode) {

        //根据orderNo 找到订单
        Orders model = new Orders();
        model.setOrderNo(orderNo);
        model.setIsDeleted(false);
        Orders orders = selectOne(model);

        if(null == orders){
            return false;
        }

        //更新状态
        model.setTradeNo(tradeNo);
        model.setPayMode(payMode);
        model.setOrderStatus(OrderStatusEnum.WAIT_DELIVER.name());

        return update(orders);
    }
}