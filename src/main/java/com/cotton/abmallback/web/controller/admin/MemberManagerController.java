package com.cotton.abmallback.web.controller.admin;

import com.cotton.abmallback.model.Member;
import com.cotton.abmallback.service.MemberService;
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
 * MemberManager
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/6/28
 */
@Controller
@RequestMapping("/admin/member")
public class MemberManagerController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(MemberManagerController.class);

    private MemberService memberService;

    @Autowired
    public MemberManagerController(MemberService memberService) {
        this.memberService = memberService;
    }

    @ResponseBody
    @RequestMapping(value = "/example")
    public RestResponse<Map<String, Object>> example() {

        Map<String, Object> map = new HashMap<>(2);

        return RestResponse.getSuccesseResponse(map);
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public RestResponse<Void> add(@RequestBody Member member) {

        if (memberService.insert(member)) {
            return RestResponse.getSuccesseResponse();
        } else {
            return RestResponse.getFailedResponse(500, "增加失败");
        }
    }


    @ResponseBody
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public RestResponse<Void> update(@RequestBody Member member) {

        if (!memberService.update(member)) {
            return RestResponse.getFailedResponse(500, "更新数据失败,Member为:" + member.toString());
        }

        return RestResponse.getSuccesseResponse();
    }

    @ResponseBody
    @RequestMapping(value = "/get", method = {RequestMethod.GET})
    public RestResponse<Member> get(long memberId) {

        Member member = memberService.getById(memberId);

        if (null == member) {
            return RestResponse.getFailedResponse(500, "无法查找数据,请检查memberId是否正确");

        }
        return RestResponse.getSuccesseResponse(member);
    }

    @ResponseBody
    @RequestMapping(value = "/queryList", method = {RequestMethod.GET})
    public RestResponse<List<Member>> queryList() {

        Example example = new Example(Member.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted", false);

        List<Member> memberList = memberService.queryList(example);

        if (memberList == null) {
            logger.error("读取列表失败");
            return RestResponse.getSystemInnerErrorResponse();
        }
        return RestResponse.getSuccesseResponse(memberList);
    }

    @ResponseBody
    @RequestMapping(value = "/queryPageList", method = {RequestMethod.GET})
    public RestResponse<PageInfo<Member>> queryPageList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "4") int pageSize) {

        Example example = new Example(Member.class);
        example.setOrderByClause("gmt_create desc");

        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted", false);

        PageInfo<Member> memberPageInfo = memberService.query(pageNum, pageSize, example);

        if (memberPageInfo == null) {
            logger.error("读取列表失败");
            return RestResponse.getSystemInnerErrorResponse();
        }
        return RestResponse.getSuccesseResponse(memberPageInfo);
    }


    @ResponseBody
    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE})
    public RestResponse<Map<String, Object>> delete(long memberId) {

        Member member = memberService.getById(memberId);

        if (null == member) {
            return RestResponse.getFailedResponse(500, "无法查找数据,请检查memberId 是否正确");

        }

        member.setIsDeleted(true);

        if (!memberService.update(member)) {
            return RestResponse.getFailedResponse(500, "删除数据失败,memberId为:" + memberId);
        }

        return RestResponse.getSuccesseResponse();
    }
}