package com.cotton.abmallback.web.controller.front;

import com.cotton.abmallback.model.AccountMoneyFlow;
import com.cotton.abmallback.service.AccountMoneyFlowService;
import com.cotton.base.common.RestResponse;
import com.cotton.base.controller.BaseController;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * Award
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/5/24
 */
@Controller
@RequestMapping("/accountMoneyFlow")
public class AwardController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(AwardController.class);

    private AccountMoneyFlowService accountMoneyFlowService;

    @Autowired
    public AwardController(AccountMoneyFlowService accountMoneyFlowService) {
        this.accountMoneyFlowService = accountMoneyFlowService;
    }

    @ResponseBody
    @RequestMapping(value = "/example")
    public RestResponse<Map<String, Object>> example() {

        Map<String, Object> map = new HashMap<>(2);

        return RestResponse.getSuccesseResponse(map);
    }

    /**
     * 列表
     *
     * @return List<AccountMoneyFlow>
     */
    @ResponseBody
    @RequestMapping(value = "/queryList", method = {RequestMethod.GET})
    public RestResponse<List<AccountMoneyFlow>> queryList(@RequestParam(defaultValue = "1") int pageNum,
                                                          @RequestParam(defaultValue = "4") int pageSize,
                                                          @RequestParam(required = false) String type) {

        Example example = new Example(AccountMoneyFlow.class);
        example.setOrderByClause("gmt_create desc");

        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted", "0");

        if(StringUtils.isNotBlank(type)){
            criteria.andEqualTo("accountMoneyType",type);
        }

        PageInfo<AccountMoneyFlow> accountMoneyFlowPageInfo = accountMoneyFlowService.query(pageNum, pageSize, example);
        if (null != accountMoneyFlowPageInfo) {
            return RestResponse.getSuccesseResponse(accountMoneyFlowPageInfo.getList());
        } else {
            logger.error("读取列表失败");
            return RestResponse.getFailedResponse(500, "读取列表失败");
        }
    }
}