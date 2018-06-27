package com.cotton.abmallback.web.controller.admin;

import com.cotton.abmallback.model.DistributionConfig;
import com.cotton.abmallback.service.DistributionConfigService;
import com.cotton.abmallback.web.controller.ABMallAdminBaseController;
import com.cotton.base.common.RestResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DistributionConfigController
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/6/27
 */
@Controller
@RequestMapping("/admin")
public class DistributionConfigController extends ABMallAdminBaseController {

    private final DistributionConfigService distributionConfigService;

    public DistributionConfigController(DistributionConfigService distributionConfigService) {
        this.distributionConfigService = distributionConfigService;
    }

    @ResponseBody
    @RequestMapping(value = "/distribution/config")
    public RestResponse<Map<String, Object>> getConfig(String namespace) {

        Map<String, Object> map = new HashMap<>(2);

        DistributionConfig model = new DistributionConfig();
        model.setType(namespace);
        model.setIsDeleted(false);

        List<DistributionConfig> distributionConfigList =  distributionConfigService.queryList(model);

        for(DistributionConfig distributionConfig : distributionConfigList){

            Map<String,String> obj = new HashMap<>(5);
            obj.put("item",distributionConfig.getItem());
            obj.put("value",distributionConfig.getValue());
            obj.put("defaultValue",distributionConfig.getDefaultValue());
            map.put(distributionConfig.getItem(),obj);
        }
        return RestResponse.getSuccesseResponse(map);
    }
}
