package com.cotton.abmallback.model.vo;

import com.cotton.abmallback.model.OrderGoods;
import com.cotton.abmallback.model.Orders;

public class OrdersWithGoodsInfo {
    private Orders orders;

    private OrderGoods orderGoods;

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public OrderGoods getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(OrderGoods orderGoods) {
        this.orderGoods = orderGoods;
    }
}
