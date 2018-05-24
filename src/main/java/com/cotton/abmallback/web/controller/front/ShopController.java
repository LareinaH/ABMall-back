package com.cotton.abmallback.web.controller.front;

import com.cotton.abmallback.model.Ads;
import com.cotton.abmallback.model.Goods;
import com.cotton.abmallback.model.GoodsSpecification;
import com.cotton.abmallback.model.ShopActivities;
import com.cotton.abmallback.model.vo.GoodsVO;
import com.cotton.abmallback.service.AdsService;
import com.cotton.abmallback.service.GoodsService;
import com.cotton.abmallback.service.GoodsSpecificationService;
import com.cotton.abmallback.service.ShopActivitiesService;
import com.cotton.abmallback.web.controller.ABMallFrontBaseController;
import com.cotton.base.common.RestResponse;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Shop
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/5/9
 */
@Controller
@RequestMapping("/shop")
public class ShopController extends ABMallFrontBaseController {

    private Logger logger = LoggerFactory.getLogger(ShopController.class);

    private final GoodsService goodsService;

    private final GoodsSpecificationService goodsSpecificationService;

    private final AdsService adsService;

    private final ShopActivitiesService shopActivitiesService;

    @Autowired
    public ShopController(GoodsService goodsService, GoodsSpecificationService goodsSpecificationService, AdsService adsService, ShopActivitiesService shopActivitiesService) {
        this.goodsService = goodsService;
        this.goodsSpecificationService = goodsSpecificationService;
        this.adsService = adsService;
        this.shopActivitiesService = shopActivitiesService;
    }

    /**
     * 首页
     * @return RestResponse<Map<String, Object>> 首页信息
     */
    @ResponseBody
    @RequestMapping(value = "/index",method = {RequestMethod.GET})
    public RestResponse<Map<String, Object>> index() {

        //广告轮播
        List<Ads> adsList =  adsService.queryBanner();

        if(adsList.isEmpty()){
            logger.error("banner数据不存在");
            return RestResponse.getFailedResponse(101,"banner数据不存在",null);
        }

        Map<String, Object> map = new HashMap<>(2);
        map.put("ads",adsList);

        return RestResponse.getSuccesseResponse(map);

    }

    /**
     * 推荐码
     * @return RestResponse<Map<String, Object>> 推荐码
     */
    @ResponseBody
    @RequestMapping(value = "/invitingCode",method = {RequestMethod.GET})
    public RestResponse<Map<String, Object>> getInvitingCode() {

        //邀请码列表
        List<Ads> adsList =  adsService.queryInvitingCode();

        if(null == adsList || adsList.isEmpty()){
            logger.error("邀请码不存在");
            return RestResponse.getFailedResponse(101,"邀请码不存在",null);
        }

        //找到当前用户级别对应的邀请码
        //TODO:根据用户级别匹配

        Ads ads = adsList.get(0);

        Map<String, Object> map = new HashMap<>(2);
        map.put("invitingCode",ads.getAdUrl());
        map.put("memberId",getCurrentMemberId());

        return RestResponse.getSuccesseResponse(map);

    }

    /**
     * 团队体系
     * @return  团队体系
     */
    @ResponseBody
    @RequestMapping(value = "/teamSystem",method = {RequestMethod.GET})
    public RestResponse<String> getTeamSystem() {

        //团队体系
        Ads ads =  adsService.queryTeamSystem();

        if(null== ads){
            logger.error("团队体系数据不存在!");
            return RestResponse.getFailedResponse(101,"团队体系数据不存在!",null);
        }

        return RestResponse.getSuccesseResponse(ads.getAdUrl());

    }

    /**
     * 商品列表
     * @return 商品列表
     */
    @ResponseBody
    @RequestMapping(value = "/goodsList",method = {RequestMethod.GET})
    public RestResponse<List<Goods>> goodsList(@RequestParam(defaultValue = "1") int pageNum,
                                               @RequestParam(defaultValue = "4") int pageSize) {

        Example example = new Example(Goods.class);
        example.setOrderByClause("gmt_create desc");

        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted",false);

        PageInfo<Goods> goodsPageInfo = goodsService.query(pageNum,pageSize,example);

        if(goodsPageInfo == null ){
            logger.error("读取商品列表失败");
            return RestResponse.getSystemInnerErrorResponse();
        }
        return RestResponse.getSuccesseResponse(goodsPageInfo.getList());
    }


    /**
     * 商品详情
     * @return 商品详情
     */
    @ResponseBody
    @RequestMapping(value = "/goodsDetail",method = {RequestMethod.GET})
    public RestResponse<GoodsVO> goodsDetail(long goodsId) {

        //获取商品详情
        Goods goods = goodsService.getById(goodsId);

        if(null == goods){
            logger.error("商品编号有误,该商品不存在！");
            return RestResponse.getFailedResponse(1,"商品编号有误,该商品不存在！");
        }
        GoodsSpecification model = new GoodsSpecification();
        model.setGoodsId(goodsId);
        model.setIsDeleted(false);

        //获取规格详情
        List<GoodsSpecification> goodsSpecificationList = goodsSpecificationService.queryList(model);
        GoodsVO goodsVO = new GoodsVO();
        BeanUtils.copyProperties(goods,goodsVO);
        goodsVO.setGoodsSpecificationList(goodsSpecificationList);

        return RestResponse.getSuccesseResponse(goodsVO);
    }

    /**
     * 商城活动
     * @return 商城活动
     */
    @ResponseBody
    @RequestMapping(value = "/shopActivities",method = {RequestMethod.GET})
    public RestResponse<ShopActivities> shopActivities() {

        Example example = new Example(ShopActivities.class);
        example.setOrderByClause("gmt_start desc");

        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted",false);

        PageInfo<ShopActivities> shopActivitiesPageInfo = shopActivitiesService.query(1,1,example);

        if(null != shopActivitiesPageInfo && null != shopActivitiesPageInfo.getList() &&
                !shopActivitiesPageInfo.getList().isEmpty()){
            return RestResponse.getSuccesseResponse(shopActivitiesPageInfo.getList().get(0));
        }

        return RestResponse.getSuccesseResponse();
    }
}