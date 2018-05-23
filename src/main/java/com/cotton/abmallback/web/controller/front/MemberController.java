package com.cotton.abmallback.web.controller.front;

import com.cotton.abmallback.model.Member;
import com.cotton.abmallback.model.MemberAddress;
import com.cotton.abmallback.service.MemberAddressService;
import com.cotton.abmallback.service.MemberService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/5/10
 */

@Controller
@RequestMapping("/member")
public class MemberController extends ABMallFrontBaseController {

    private Logger logger = LoggerFactory.getLogger(MemberController.class);

    private final MemberService memberService;

    private final MemberAddressService memberAddressService;

    @Autowired
    public MemberController(MemberAddressService memberAddressService, MemberService memberService) {
        this.memberAddressService = memberAddressService;
        this.memberService = memberService;
    }


    @ResponseBody
    @RequestMapping(value = "/example")
    public RestResponse<Map<String, Object>> example() {

        Map<String, Object> map = new HashMap<>(2);

        return RestResponse.getSuccesseResponse(map);
    }


    /**
     * 绑定手机号
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/bindPhoneNum",method = {RequestMethod.POST})
    public RestResponse<Void> bindPhoneNum(@RequestParam() String phoneNum) {

        Member member = getCurrentMember();
        member.setPhoneNum(phoneNum);

        if(memberService.update(member)){
            return RestResponse.getSuccesseResponse();
        }else {
            return RestResponse.getFailedResponse(1,"绑定手机号失败！");
        }
    }

    /**
     * 收货地址列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addressList",method = {RequestMethod.GET})
    public RestResponse<List<MemberAddress>> addressList(@RequestParam(defaultValue = "1") int pageNum,
                                                         @RequestParam(defaultValue = "4") int pageSize) {

        Example example = new Example(MemberAddress.class);
        example.setOrderByClause("isDefault desc");

        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("memberId", getCurrentMemberId());
        criteria.andEqualTo("isDeleted","0");
        criteria.andEqualTo("memberId","");

        PageInfo<MemberAddress> memberAddressPageInfo = memberAddressService.query(pageNum,pageSize,example);

        if(null != memberAddressPageInfo) {
            return RestResponse.getSuccesseResponse(memberAddressPageInfo.getList());
        }else {
            return RestResponse.getFailedResponse(500,"读取列表失败");
        }

    }

    /**
     * 增加收货地址
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addAddress",method = {RequestMethod.POST})
    public RestResponse<Void> addAddress(MemberAddress memberAddress) {

        //MemberAddress memberAddress = new MemberAddress();
        memberAddress.setMemberId(getCurrentMemberId());
        if(memberAddressService.insert(memberAddress)){
            return RestResponse.getSuccesseResponse();
        }else {
            return RestResponse.getFailedResponse(500,"增加收货地址失败");
        }
    }

    /**
     * 删除收货地址
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteAddress",method = {RequestMethod.DELETE})
    public RestResponse<Void> deleteAddress(long addressId) {

        MemberAddress model = new MemberAddress();
        model.setId(addressId);
        model.setIsDeleted(true);

        if(memberAddressService.update(model)){
            return RestResponse.getSuccesseResponse();
        }else {
            return RestResponse.getFailedResponse(500,"删除收货地址失败");
        }
    }

    /**
     * 设为默认收货地址
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/setDefaultAddress",method = {RequestMethod.POST})
    public RestResponse<Void> setDefaultAddress(long addressId) {

        //取消默认地址
        MemberAddress model = new MemberAddress();
        model.setMemberId(getCurrentMemberId());
        model.setIsDefault(true);
        model.setIsDeleted(false);

        List<MemberAddress> memberAddressList = memberAddressService.queryList(model);

        for(MemberAddress memberAddress : memberAddressList){
            memberAddress.setIsDefault(false);
            memberAddressService.update(memberAddress);
        }

        //设为默认地址
        model.setId(addressId);

        if(memberAddressService.update(model)){
            return RestResponse.getSuccesseResponse();
        }else {
            return RestResponse.getFailedResponse(500,"设置默认收货地址失败");
        }
    }
}