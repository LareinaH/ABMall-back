package com.cotton.abmallback.web.controller.admin;

import com.cotton.abmallback.model.Goods;
import com.cotton.abmallback.model.GoodsSpecification;
import com.cotton.abmallback.model.vo.GoodsVO;
import com.cotton.abmallback.service.GoodsService;
import com.cotton.abmallback.service.GoodsSpecificationService;
import com.cotton.base.common.RestResponse;
import com.cotton.base.controller.BaseController;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GoodsManager
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/6/6
 */
@Controller
@RequestMapping("/admin/goods")
public class GoodsManagerController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(GoodsManagerController.class);

    private GoodsService goodsService;

    private GoodsSpecificationService goodsSpecificationService;

    @Autowired
    public GoodsManagerController(GoodsService goodsService, GoodsSpecificationService goodsSpecificationService) {
        this.goodsService = goodsService;
        this.goodsSpecificationService = goodsSpecificationService;
    }

    @ResponseBody
    @RequestMapping(value = "/example")
    public RestResponse<Map<String, Object>> example() {

        Map<String, Object> map = new HashMap<>(2);

        return RestResponse.getSuccesseResponse(map);
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public RestResponse<Void> add(@RequestBody GoodsVO goodsVO) {

        Goods goods = new Goods();
        BeanUtils.copyProperties(goodsVO,goods);
        if (goodsService.insert(goods)) {

            if(null != goodsVO.getGoodsSpecificationList() &&
                    goodsVO.getGoodsSpecificationList().size()>0){

                for (GoodsSpecification goodsSpecification: goodsVO.getGoodsSpecificationList()) {

                    goodsSpecification.setGoodsId(goods.getId());
                    goodsSpecificationService.insert(goodsSpecification);
                }

            }
            return RestResponse.getSuccesseResponse();
        } else {
            return RestResponse.getFailedResponse(500, "增加失败");
        }
    }


    @ResponseBody
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public RestResponse<Void> update(@RequestBody Goods goods) {

        if (!goodsService.update(goods)) {
            return RestResponse.getFailedResponse(500, "更新数据失败,Goods为:" + goods.toString());
        }

        return RestResponse.getSuccesseResponse();
    }

    @ResponseBody
    @RequestMapping(value = "/get", method = {RequestMethod.GET})
    public RestResponse<Goods> get(long goodsId) {

        Goods goods = goodsService.getById(goodsId);

        if (null == goods) {
            return RestResponse.getFailedResponse(500, "无法查找数据,请检查goodsId是否正确");

        }
        return RestResponse.getSuccesseResponse(goods);
    }

    @ResponseBody
    @RequestMapping(value = "/queryList", method = {RequestMethod.GET})
    public RestResponse<List<Goods>> queryList() {

        Example example = new Example(Goods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted", false);

        List<Goods> goodsList = goodsService.queryList(example);

        if (goodsList == null) {
            logger.error("读取列表失败");
            return RestResponse.getSystemInnerErrorResponse();
        }
        return RestResponse.getSuccesseResponse(goodsList);
    }

    @ResponseBody
    @RequestMapping(value = "/queryPageList", method = {RequestMethod.GET})
    public RestResponse<PageInfo<Goods>> queryPageList(@RequestParam(defaultValue = "1") int pageNum,
                                                       @RequestParam(defaultValue = "4") int pageSize) {

        Example example = new Example(Goods.class);
        example.setOrderByClause("gmt_create desc");

        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted", false);

        PageInfo<Goods> goodsPageInfo = goodsService.query(pageNum, pageSize, example);

        if (goodsPageInfo == null) {
            logger.error("读取列表失败");
            return RestResponse.getSystemInnerErrorResponse();
        }
        return RestResponse.getSuccesseResponse(goodsPageInfo);
    }


    @ResponseBody
    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE})
    public RestResponse<Map<String, Object>> delete(long goodsId) {

        Goods goods = goodsService.getById(goodsId);

        if (null == goods) {
            return RestResponse.getFailedResponse(500, "无法查找数据,请检查goodsId 是否正确");

        }

        goods.setIsDeleted(true);

        if (!goodsService.update(goods)) {
            return RestResponse.getFailedResponse(500, "删除数据失败,goodsId为:" + goodsId);
        }

        return RestResponse.getSuccesseResponse();
    }
}