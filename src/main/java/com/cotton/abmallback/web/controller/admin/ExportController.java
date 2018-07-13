package com.cotton.abmallback.web.controller.admin;

import com.cotton.abmallback.model.Orders;
import com.cotton.abmallback.service.OrdersService;
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

@Controller
@RequestMapping("/admin")
public class ExportController {

    @Autowired
    private OrdersService ordersService;

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

        Map<String, Object> map = new HashMap<>();
        map.put("detail", ordersList);
        map.put("name", String.format("%s~%s订单列表-%d", timeBegin, timeEnd, System.currentTimeMillis()));
        map.put("sheetName", "订单列表");

        return new ModelAndView(new ExportOrderView(), map);
    }
}
