package com.cotton.abmallback.web.controller.admin;

import com.cotton.abmallback.model.OrderGoods;
import com.cotton.abmallback.model.Orders;
import com.cotton.abmallback.service.OrderGoodsService;
import com.cotton.abmallback.service.OrdersService;
import com.cotton.base.common.RestResponse;
import com.cotton.base.service.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class ExportController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    OrderGoodsService orderGoodsService;

    @Autowired
    StatController statController;

    @RequestMapping(value = "/exportOrder", method = RequestMethod.GET)
    public ModelAndView exportOrder(
            @RequestParam(value = "orderStatus", required = false) String orderStatus,
            @RequestParam(value = "returnStatus", required = false) String returnStatus,
            @RequestParam(value = "timeBegin", required = false) String timeBegin,
            @RequestParam(value = "timeEnd", required = false) String timeEnd,
            @RequestParam(value = "orderNo", required = false) String orderNo
    ) {
        Example example = new Example(Orders.class);
        example.setOrderByClause("gmt_create desc");

        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted", false);

        if (StringUtils.isNotBlank(timeBegin)) {
            criteria.andGreaterThanOrEqualTo("gmtCreate", timeBegin + " 00:00:00");
        }

        if (StringUtils.isNotBlank(timeEnd)) {
            criteria.andLessThanOrEqualTo("gmtCreate", timeEnd + " 23:59:59");
        }

        if (StringUtils.isNotBlank(orderStatus)) {
            criteria.andEqualTo("orderStatus", orderStatus);
        }

        if (StringUtils.isNotBlank(returnStatus)) {
            criteria.andEqualTo("returnStatus", returnStatus);
        }

        if (StringUtils.isNotBlank(orderNo)) {
            criteria.andEqualTo("orderNo", orderNo);
        }

        List<Orders> ordersList = ordersService.queryList(example);

        Example e2 = new Example(OrderGoods.class);
        Example.Criteria c2 = e2.createCriteria();
        c2.andIn("orderId", ordersList.stream().map(x -> x.getId()).collect(Collectors.toList()));

        List<OrderGoods> orderGoodsList = orderGoodsService.queryList(e2);
        Map<Long, OrderGoods> orderGoodsMap = orderGoodsList.stream().collect(Collectors.toMap(OrderGoods::getOrderId, Function.identity()));

        Map<String, Object> map = new HashMap<>();
        map.put("detail", ordersList);
        map.put("orderGoods", orderGoodsMap);
        map.put("name", String.format("%s~%s订单列表-%d", timeBegin, timeEnd, System.currentTimeMillis()));
        map.put("sheetName", "订单列表");

        return new ModelAndView(new ExportOrderView(), map);
    }

    @RequestMapping(value = "/exportSales", method = RequestMethod.GET)
    public ModelAndView exportSales(
            @RequestParam(value = "year") Integer year
    ) throws Exception {

        RestResponse<List<Map<String, Object>>> response = statController.getYearStat(year);
        if (!response.getSuccessed()) {
            throw new Exception("获取导出数据异常");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("detail", response.getData());
        map.put("name", String.format("%d年销售额统计-%d", year, System.currentTimeMillis()));
        map.put("sheetName", "销售额统计");

        return new ModelAndView(new ExportSalesView(), map);
    }
}
