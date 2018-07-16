package com.cotton.abmallback.web.controller.front;

import com.cotton.abmallback.model.ContentStudy;
import com.cotton.abmallback.service.ContentStudyService;
import com.cotton.base.common.RestResponse;
import com.cotton.base.controller.BaseController;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ContentStudy
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/7/16
 */
@Controller
@RequestMapping("/study")
public class ContentStudyController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(ContentStudyController.class);

    private ContentStudyService contentStudyService;

    @Autowired
    public ContentStudyController(ContentStudyService contentStudyService) {
        this.contentStudyService = contentStudyService;
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
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryList", method = {RequestMethod.GET})
    public RestResponse<List<ContentStudy>> queryList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "4") int pageSize) {

        Example example = new Example(ContentStudy.class);
        example.setOrderByClause("gmt_create desc");

        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted", "0");

        PageInfo<ContentStudy> contentStudyPageInfo = contentStudyService.query(pageNum, pageSize, example);
        if (null != contentStudyPageInfo) {
            return RestResponse.getSuccesseResponse(contentStudyPageInfo.getList());
        } else {
            logger.error("读取列表失败");
            return RestResponse.getFailedResponse(500, "读取列表失败");
        }
    }
}