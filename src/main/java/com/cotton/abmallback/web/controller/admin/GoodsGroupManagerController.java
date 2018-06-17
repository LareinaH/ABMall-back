package com.cotton.abmallback.web.controller.admin;

import com.cotton.abmallback.model.GoodsGroup;
import com.cotton.abmallback.service.GoodsGroupService;
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
 * GoodsGroupManager
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/6/17
 */
@Controller
@RequestMapping("/admin/goodsGroup")
public class GoodsGroupManagerController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(GoodsGroupManagerController.class);

    private GoodsGroupService goodsGroupService;

    @Autowired
    public GoodsGroupManagerController(GoodsGroupService goodsGroupService) {
        this.goodsGroupService = goodsGroupService;
    }

    @ResponseBody
    @RequestMapping(value = "/example")
    public RestResponse<Map<String, Object>> example() {

        Map<String, Object> map = new HashMap<>(2);

        return RestResponse.getSuccesseResponse(map);
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public RestResponse<Void> add(@RequestBody GoodsGroup goodsGroup) {

        if (goodsGroupService.insert(goodsGroup)) {
            return RestResponse.getSuccesseResponse();
        } else {
            return RestResponse.getFailedResponse(500, "增加失败");
        }
    }


    @ResponseBody
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public RestResponse<Void> update(@RequestBody GoodsGroup goodsGroup) {

        if (!goodsGroupService.update(goodsGroup)) {
            return RestResponse.getFailedResponse(500, "更新数据失败,GoodsGroup为:" + goodsGroup.toString());
        }

        return RestResponse.getSuccesseResponse();
    }

    @ResponseBody
    @RequestMapping(value = "/get", method = {RequestMethod.GET})
    public RestResponse<GoodsGroup> get(long goodsGroupId) {

        GoodsGroup goodsGroup = goodsGroupService.getById(goodsGroupId);

        if (null == goodsGroup) {
            return RestResponse.getFailedResponse(500, "无法查找数据,请检查goodsGroupId是否正确");

        }
        return RestResponse.getSuccesseResponse(goodsGroup);
    }

    @ResponseBody
    @RequestMapping(value = "/queryList", method = {RequestMethod.GET})
    public RestResponse<List<GoodsGroup>> queryList() {

        Example example = new Example(GoodsGroup.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted", false);

        List<GoodsGroup> goodsGroupList = goodsGroupService.queryList(example);

        if (goodsGroupList == null) {
            logger.error("读取列表失败");
            return RestResponse.getSystemInnerErrorResponse();
        }
        return RestResponse.getSuccesseResponse(goodsGroupList);
    }

    @ResponseBody
    @RequestMapping(value = "/queryPageList", method = {RequestMethod.GET})
    public RestResponse<PageInfo<GoodsGroup>> queryPageList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "4") int pageSize) {

        Example example = new Example(GoodsGroup.class);
        example.setOrderByClause("gmt_create desc");

        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted", false);

        PageInfo<GoodsGroup> goodsGroupPageInfo = goodsGroupService.query(pageNum, pageSize, example);

        if (goodsGroupPageInfo == null) {
            logger.error("读取列表失败");
            return RestResponse.getSystemInnerErrorResponse();
        }
        return RestResponse.getSuccesseResponse(goodsGroupPageInfo);
    }


    @ResponseBody
    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE})
    public RestResponse<Map<String, Object>> delete(long goodsGroupId) {

        GoodsGroup goodsGroup = goodsGroupService.getById(goodsGroupId);

        if (null == goodsGroup) {
            return RestResponse.getFailedResponse(500, "无法查找数据,请检查goodsGroupId 是否正确");

        }

        goodsGroup.setIsDeleted(true);

        if (!goodsGroupService.update(goodsGroup)) {
            return RestResponse.getFailedResponse(500, "删除数据失败,goodsGroupId为:" + goodsGroupId);
        }

        return RestResponse.getSuccesseResponse();
    }
}