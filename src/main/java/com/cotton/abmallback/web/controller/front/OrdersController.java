package com.cotton.abmallback.web.controller.front;

import com.cotton.abmallback.enumeration.OrderStatusEnum;
import com.cotton.abmallback.model.*;
import com.cotton.abmallback.service.*;
import com.cotton.abmallback.web.controller.ABMallFrontBaseController;
import com.cotton.base.common.RestResponse;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OrdersController
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/5/10
 */

@Controller
@RequestMapping("/orders")
public class OrdersController extends ABMallFrontBaseController {

    private Logger logger = LoggerFactory.getLogger(OrdersController.class);

    private final OrdersService ordersService;

    private final GoodsService goodsService;

    private final GoodsSpecificationService goodsSpecificationService;

    private final MemberAddressService memberAddressService;

    private final OrderGoodsService orderGoodsService;

    @Autowired
    public OrdersController(OrdersService ordersService, GoodsService goodsService, OrderGoodsService orderGoodsService, GoodsSpecificationService goodsSpecificationService, MemberAddressService memberAddressService) {
        this.ordersService = ordersService;
        this.goodsService = goodsService;
        this.orderGoodsService = orderGoodsService;
        this.goodsSpecificationService = goodsSpecificationService;
        this.memberAddressService = memberAddressService;
    }

    @ResponseBody
    @RequestMapping(value = "/createOrder")
    @Transactional(rollbackFor = Exception.class)
    public RestResponse<Void> createOrder(long goodsSpecificationServiceId, int count,long addressId) {

        //根据goodsSpecificationServiceId查找商品
        GoodsSpecification goodsSpecification = goodsSpecificationService.getById(goodsSpecificationServiceId);
        if(null == goodsSpecification || goodsSpecification.getIsDeleted() || !goodsSpecification.getIsOnSell()){
            return RestResponse.getFailedResponse(500,"商品不存在或者已经下架");
        }

        Goods goods = goodsService.getById(goodsSpecification.getGoodsId());

        if(null == goods){
            return RestResponse.getFailedResponse(500,"商品不存在");
        }

        if(goodsSpecification.getStock() < count){
            return RestResponse.getFailedResponse(500,"商品库存不足");
        }

        MemberAddress memberAddress = memberAddressService.getById(addressId);

        if(null == memberAddress || memberAddress.getIsDeleted()){
            return RestResponse.getFailedResponse(500,"收货地址不存在");
        }

        //创建订单
        Orders orders = new Orders();
        orders.setOrderStatus(OrderStatusEnum.INVITING_CODE.name());
        orders.setMemberId(getCurrentMemberId());
        //根据时间戳生成订单编号
        orders.setOrderNo("");

        orders.setReceiverName(memberAddress.getReceiverName());
        orders.setReceiverPhone(memberAddress.getReceiverPhone());
        orders.setReceiverProvinceName(memberAddress.getReceiverProvinceName());
        orders.setReceiverProvinceCode(memberAddress.getReceiverProvinceCode());
        orders.setReceiverCityName(memberAddress.getReceiverCityName());
        orders.setReceiverCityCode(memberAddress.getReceiverCityCode());
        orders.setReceiverCountyName(memberAddress.getReceiverCountyName());
        orders.setReceiverCountyCode(memberAddress.getReceiverCountyCode());
        orders.setReceiverAddress(memberAddress.getReceiverAddress());

        orders.setTotalMoney(goodsSpecification.getPreferentialPrice().multiply(new BigDecimal(Double.valueOf(count))));

        if(ordersService.insert(orders)){
            OrderGoods orderGoods = new OrderGoods();

            orderGoods.setGoodName(goods.getGoodsName());
            orderGoods.setGoodNo(goods.getId());
            orderGoods.setOrderNo(orders.getOrderNo());
            orderGoods.setGoodThum(goods.getThums());
            orderGoods.setGoodNum(count);

            orderGoodsService.insert(orderGoods);

            //扣库存  加销量



        }



        return RestResponse.getFailedResponse(500,"内部错误");

    }

    /**
     * 订单列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/ordersList")
    public RestResponse<List<Orders>> ordersList(@RequestParam(defaultValue = "1") int pageNum,
                                                 @RequestParam(defaultValue = "4") int pageSize) {

        Example example = new Example(Orders.class);
        example.setOrderByClause("gmt_create desc");

        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted",false);
        criteria.andEqualTo("memberId",getCurrentMemberId());
        PageInfo<Orders> ordersPageInfo = ordersService.query(pageNum,pageSize,example);

        if(ordersPageInfo == null ){
            logger.error("读取订单列表失败");
            return RestResponse.getSystemInnerErrorResponse();
        }
        return RestResponse.getSuccesseResponse(ordersPageInfo.getList());
    }


    /**
     * 确认收货
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/confirmReceipt")
    public RestResponse<Void> confirmReceipt(@RequestParam long orderId) {


        Orders orders = ordersService.getById(orderId);
        if(null == orders){
            return RestResponse.getFailedResponse(500,"订单编号不存在");
        }
        orders.setOrderStatus(OrderStatusEnum.TEAM_SYSTEM.name());

        if(ordersService.update(orders)){

            return RestResponse.getSuccesseResponse();
        }

        return RestResponse.getFailedResponse(500,"确认收获失败!");
    }

    /**
     * 取消订单
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cancelOrder")
    public RestResponse<Void> cancelOrder(@RequestParam long orderId) {

        Orders orders = ordersService.getById(orderId);
        if(null == orders){
            return RestResponse.getFailedResponse(500,"订单编号不存在");
        }

        orders.setOrderStatus(OrderStatusEnum.CANCLE.name());

        if(ordersService.update(orders)){

            //处理库存和销量

            return RestResponse.getSuccesseResponse();
        }

        return RestResponse.getFailedResponse(500,"取消订单失败!");
    }

    /**
     * 查看物流
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/showLogistics")
    public RestResponse<Map<String, Object>> showLogistics(@RequestParam long orderId) {


        Map<String, Object> map = new HashMap<>(2);

        return RestResponse.getSuccesseResponse(map);

    }
}