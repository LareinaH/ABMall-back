package com.cotton.abmallback.web.controller.admin;

import com.cotton.abmallback.mapper.StatMapper;
import com.cotton.base.common.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * AdminLoginController
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/6/29
 */
@RestController
@RequestMapping(value = "/admin/stat", produces = "application/json; charset=UTF-8")
public class StatController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StatMapper statMapper;

    @RequestMapping(value = "/getOrderStatusStats", method = {RequestMethod.GET})
    public RestResponse<List<Map<String, Long>>> get(
            @RequestParam(value = "gmtStart") String gmtStart,
            @RequestParam(value = "gmtEnd") String gmtEnd
    ) {
        List<Map<String, Long>> listMap = statMapper.getOrderStatusStats(gmtStart, gmtEnd);
        return RestResponse.getSuccesseResponse(listMap);
    }
}
