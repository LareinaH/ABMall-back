package com.cotton.abmallback.web.controller.admin;

import com.cotton.abmallback.model.SysUser;
import com.cotton.abmallback.service.SysUserService;
import com.cotton.base.common.RestResponse;
import com.cotton.base.controller.BaseController;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SysUser
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/5/25
 */
@Controller
@RequestMapping("/admin/sysUser")
public class SysUserController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(SysUserController.class);

    private SysUserService sysUserService;

    @Autowired
    public SysUserController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @ResponseBody
    @RequestMapping(value = "/example")
    public RestResponse<Map<String, Object>> example() {

        Map<String, Object> map = new HashMap<>(2);

        return RestResponse.getSuccesseResponse(map);
    }

    @ResponseBody
    @RequestMapping(value = "/add")
    public RestResponse<Map<String, Object>> add() {

        Map<String, Object> map = new HashMap<>(2);

        return RestResponse.getSuccesseResponse(map);
    }

    @ResponseBody
    @RequestMapping(value = "/get")
    public RestResponse<SysUser> get(long sysUserId) {

        SysUser sysUser = sysUserService.getById(sysUserId);

        if(null == sysUser){
            return RestResponse.getFailedResponse(500,"无法查找数据,请检查sysUserId是否正确");

        }
        return RestResponse.getSuccesseResponse(sysUser);
    }


    @ResponseBody
    @RequestMapping(value = "/delete")
    public RestResponse<Map<String, Object>> delete(long sysUserId) {

        SysUser sysUser = sysUserService.getById(sysUserId);

        if(null == sysUser){
            return RestResponse.getFailedResponse(500,"无法查找数据,请检查sysUserId 是否正确");

        }
        sysUser.setIsDeleted(true);

        if(!sysUserService.update(sysUser)){
            return RestResponse.getFailedResponse(500,"更新数据失败,sysUserId为:"+sysUserId);
        }

        return RestResponse.getSuccesseResponse();
    }


    @ResponseBody
    @RequestMapping(value = "/queryList")
    public RestResponse<List<SysUser>> queryList() {

        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted",false);
        //TODO:查询条件

        List<SysUser> sysUserPageInfo = sysUserService.queryList(example);

        if(sysUserPageInfo == null ){
            logger.error("读取列表失败");
            return RestResponse.getSystemInnerErrorResponse();
        }
        return RestResponse.getSuccesseResponse(sysUserPageInfo);
    }

    @ResponseBody
    @RequestMapping(value = "/queryPageList")
    public RestResponse<PageInfo<SysUser>> queryPageList(@RequestParam(defaultValue = "1") int pageNum,
                                                         @RequestParam(defaultValue = "4") int pageSize) {

        Example example = new Example(SysUser.class);
        example.setOrderByClause("gmt_create desc");

        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted",false);

        //TODO:查询条件

        PageInfo<SysUser> sysUserPageInfo = sysUserService.query(pageNum,pageSize,example);

        if(sysUserPageInfo == null ){
            logger.error("读取列表失败");
            return RestResponse.getSystemInnerErrorResponse();
        }
        return RestResponse.getSuccesseResponse(sysUserPageInfo);
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    public RestResponse<Void> update(long sysUserId) {

        SysUser sysUser = sysUserService.getById(sysUserId);

        if(null == sysUser){
            return RestResponse.getFailedResponse(500,"无法查找数据,请检查sysUserId 是否正确");

        }
        //TODO:修改数据

        if(!sysUserService.update(sysUser)){
            return RestResponse.getFailedResponse(500,"更新数据失败,sysUserId为:"+sysUserId);
        }

        return RestResponse.getSuccesseResponse();
    }
}