package com.cotton.abmallback.web.controller.admin;

import com.cotton.abmallback.model.ShopActivities;
import com.cotton.abmallback.service.ShopActivitiesService;
import com.cotton.abmallback.service.ShopActivityGoodsService;
import com.cotton.base.common.RestResponse;
import com.cotton.base.controller.BaseController;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ShopActivitiesManager
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/6/6
 */
@Controller
@RequestMapping("/admin/shopActivities")
public class ShopActivitiesManagerController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(ShopActivitiesManagerController.class);

    private ShopActivitiesService shopActivitiesService;

    private ShopActivityGoodsService shopActivityGoodsService;

    @Autowired
    public ShopActivitiesManagerController(ShopActivitiesService shopActivitiesService, ShopActivityGoodsService shopActivityGoodsService) {
        this.shopActivitiesService = shopActivitiesService;
        this.shopActivityGoodsService = shopActivityGoodsService;
    }

    @ResponseBody
    @RequestMapping(value = "/example")
    public RestResponse<Map<String, Object>> example() {

        Map<String, Object> map = new HashMap<>(2);

        return RestResponse.getSuccesseResponse(map);
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public RestResponse<Void> add(@RequestBody ShopActivities shopActivities) {

        if (shopActivitiesService.insert(shopActivities)) {
            return RestResponse.getSuccesseResponse();
        } else {
            return RestResponse.getFailedResponse(500, "增加失败");
        }
    }


    @ResponseBody
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public RestResponse<Void> update(@RequestBody ShopActivities shopActivities) {

        if (!shopActivitiesService.update(shopActivities)) {
            return RestResponse.getFailedResponse(500, "更新数据失败,ShopActivities为:" + shopActivities.toString());
        }

        return RestResponse.getSuccesseResponse();
    }

    @ResponseBody
    @RequestMapping(value = "/get", method = {RequestMethod.GET})
    public RestResponse<ShopActivities> get(long shopActivitiesId) {

        ShopActivities shopActivities = shopActivitiesService.getById(shopActivitiesId);

        if (null == shopActivities) {
            return RestResponse.getFailedResponse(500, "无法查找数据,请检查shopActivitiesId是否正确");

        }
        return RestResponse.getSuccesseResponse(shopActivities);
    }

    @ResponseBody
    @RequestMapping(value = "/queryList", method = {RequestMethod.GET})
    public RestResponse<List<ShopActivities>> queryList() {

        Example example = new Example(ShopActivities.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted", false);

        List<ShopActivities> shopActivitiesList = shopActivitiesService.queryList(example);

        if (shopActivitiesList == null) {
            logger.error("读取列表失败");
            return RestResponse.getSystemInnerErrorResponse();
        }
        return RestResponse.getSuccesseResponse(shopActivitiesList);
    }

    @ResponseBody
    @RequestMapping(value = "/queryPageList", method = {RequestMethod.POST})
    public RestResponse<PageInfo<ShopActivities>> queryPageList(@RequestParam(defaultValue = "1") int pageNum,
                                                                @RequestParam(defaultValue = "4") int pageSize,
                                                                @RequestBody()Map<String,Object> conditions) {

        Example example = new Example(ShopActivities.class);
        example.setOrderByClause("gmt_create desc");

        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted", false);

        PageInfo<ShopActivities> shopActivitiesPageInfo = shopActivitiesService.query(pageNum, pageSize, example);

        if (shopActivitiesPageInfo == null) {
            logger.error("读取列表失败");
            return RestResponse.getSystemInnerErrorResponse();
        }
        return RestResponse.getSuccesseResponse(shopActivitiesPageInfo);
    }


    @ResponseBody
    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE})
    public RestResponse<Map<String, Object>> delete(long shopActivitiesId) {

        ShopActivities shopActivities = shopActivitiesService.getById(shopActivitiesId);

        if (null == shopActivities) {
            return RestResponse.getFailedResponse(500, "无法查找数据,请检查shopActivitiesId 是否正确");

        }

        shopActivities.setIsDeleted(true);

        if (!shopActivitiesService.update(shopActivities)) {
            return RestResponse.getFailedResponse(500, "删除数据失败,shopActivitiesId为:" + shopActivitiesId);
        }

        return RestResponse.getSuccesseResponse();
    }
}