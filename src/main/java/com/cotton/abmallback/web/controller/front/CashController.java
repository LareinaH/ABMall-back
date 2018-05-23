package com.cotton.abmallback.web.controller.front;

import com.cotton.abmallback.enumeration.CashStatusEnum;
import com.cotton.abmallback.model.CashPickUp;
import com.cotton.abmallback.service.CashPickUpService;
import com.cotton.abmallback.web.controller.ABMallFrontBaseController;
import com.cotton.base.common.RestResponse;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Cash
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/5/10
 */
@Controller
@RequestMapping("/cash")
public class CashController extends ABMallFrontBaseController {

    private Logger logger = LoggerFactory.getLogger(CashController.class);

    private final CashPickUpService cashPickUpService;

    @Autowired
    public CashController(CashPickUpService cashPickUpService) {
        this.cashPickUpService = cashPickUpService;
    }

    @ResponseBody
    @RequestMapping(value = "/example")
    public RestResponse<Map<String, Object>> example() {


        Map<String, Object> map = new HashMap<>(2);

        return RestResponse.getSuccesseResponse(map);
    }


    /**
     * 提现记录
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cashPickUpList",method = {RequestMethod.GET})
    public RestResponse<List<CashPickUp>> cashPickUpList(@RequestParam(defaultValue = "1") int pageNum,
                                                         @RequestParam(defaultValue = "10") int pageSize) {

        Example example = new Example(CashPickUp.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("memberId",getCurrentMemberId());
        criteria.andEqualTo("isDeleted",false);
        PageInfo<CashPickUp> goodsPageInfo = cashPickUpService.query(pageNum,pageSize,example);

        if(goodsPageInfo == null ){
            logger.error("读取提现记录失败");
            return RestResponse.getSystemInnerErrorResponse();
        }
        return RestResponse.getSuccesseResponse(goodsPageInfo.getList());
    }

    /**
     * 申请提现
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/applyPickUpCash" ,method = {RequestMethod.POST})
    public RestResponse<Void> applyPickUpCash(@RequestParam()BigDecimal money) {


        //TODO:判断提现金额是否小于可用余额
        CashPickUp cashPickUp = new CashPickUp();
        cashPickUp.setMemberId(getCurrentMemberId());
        cashPickUp.setMoney(money);
        cashPickUp.setCashStatus(CashStatusEnum.APPLY.name());

        if(cashPickUpService.insert(cashPickUp)) {

            //TODO:锁定账户可用额度
            return RestResponse.getSuccesseResponse();
        }else {
            return RestResponse.getFailedResponse(500,"申请提现失败");
        }

    }

}