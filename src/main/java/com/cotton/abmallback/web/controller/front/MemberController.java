package com.cotton.abmallback.web.controller.front;

import com.cotton.abmallback.manager.SmsManager;
import com.cotton.abmallback.model.Member;
import com.cotton.abmallback.model.MemberAddress;
import com.cotton.abmallback.service.MemberAddressService;
import com.cotton.abmallback.service.MemberService;
import com.cotton.abmallback.web.controller.ABMallFrontBaseController;
import com.cotton.base.common.RestResponse;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
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
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/5/10
 */

@Controller
@RequestMapping("/member")
public class MemberController extends ABMallFrontBaseController {

    private Logger logger = LoggerFactory.getLogger(MemberController.class);

    private MemberService memberService;

    private MemberAddressService memberAddressService;

    private SmsManager smsManager;

    @Autowired
    public MemberController(MemberAddressService memberAddressService, MemberService memberService, SmsManager smsManager) {
        this.memberAddressService = memberAddressService;
        this.memberService = memberService;
        this.smsManager = smsManager;
    }


    @ResponseBody
    @RequestMapping(value = "/myInfo",method = {RequestMethod.GET})
    public RestResponse<Map<String, Object>> myInfo() {

        Map<String, Object> map = new HashMap<>(2);
        map.put("totalSales",1394.40);
        map.put("availablePickUpCashAmount",900.34);

        return RestResponse.getSuccesseResponse(map);
    }


    /**
     * 绑定手机号
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/bindPhoneNum",method = {RequestMethod.POST})
    public RestResponse<Void> bindPhoneNum(String phoneNum,String code) {

        //校验验证码
        if(!smsManager.checkCaptcha(phoneNum,code)){
            return RestResponse.getFailedResponse(500,"验证码错误");
        }

        //更新手机号
        Member member = getCurrentMember();
        member.setPhoneNum(phoneNum);

        if(memberService.update(member)){
            return RestResponse.getSuccesseResponse();
        }else {
            return RestResponse.getFailedResponse(1,"绑定手机号失败！");
        }
    }


    /**
     * 绑定手机号
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/changePhoneNum",method = {RequestMethod.POST})
    public RestResponse<Void> changePhoneNum(String oldPhoneNum,String phoneNum,String code) {

        if(oldPhoneNum.equals(phoneNum)){
            return RestResponse.getFailedResponse(1,"新手机号与原有手机号相同！");
        }
        //更新手机号
        Member member = memberService.getById(getCurrentMemberId());

        if(!oldPhoneNum.equals(member.getPhoneNum())){
            return RestResponse.getFailedResponse(1,"原手机号错误！");

        }

        //校验验证码
        if(!smsManager.checkCaptcha(phoneNum,code)){
            return RestResponse.getFailedResponse(500,"验证码错误");
        }

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
                                                         @RequestParam(defaultValue = "4") int pageSize,
                                                         @RequestParam(defaultValue = "ADDRESS")String addressType) {

        Example example = new Example(MemberAddress.class);
        example.setOrderByClause("is_default desc");

        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("memberId", getCurrentMemberId());
        criteria.andEqualTo("isDeleted","0");
        criteria.andEqualTo("addressType",addressType);

        PageInfo<MemberAddress> memberAddressPageInfo = memberAddressService.query(pageNum,pageSize,example);

        if(null != memberAddressPageInfo) {
            return RestResponse.getSuccesseResponse(memberAddressPageInfo.getList());
        }else {
            return RestResponse.getFailedResponse(500,"读取列表失败");
        }

    }

    /**
     * 默认地址
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/defaultAddress",method = {RequestMethod.GET})
    public RestResponse<MemberAddress> defaultAddress(@RequestParam(defaultValue = "ADDRESS")String addressType) {

        Example example = new Example(MemberAddress.class);
        example.setOrderByClause("is_default desc");

        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("memberId", getCurrentMemberId());
        criteria.andEqualTo("isDeleted","0");
        criteria.andEqualTo("addressType",addressType);

        PageInfo<MemberAddress> memberAddressPageInfo = memberAddressService.query(1,1,example);

        if(null != memberAddressPageInfo) {
            if(memberAddressPageInfo.getList().size()>0) {
                return RestResponse.getSuccesseResponse(memberAddressPageInfo.getList().get(0));
            }else {
                return RestResponse.getSuccesseResponse();
            }
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
    public RestResponse<Void> addAddress(@RequestBody MemberAddress memberAddress) {

        //MemberAddress memberAddress = new MemberAddress();
        memberAddress.setMemberId(getCurrentMemberId());
        if(memberAddressService.insert(memberAddress)){
            return RestResponse.getSuccesseResponse();
        }else {
            return RestResponse.getFailedResponse(500,"增加收货地址失败");
        }
    }


    /**
     * 增加收货地址
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/editAddress",method = {RequestMethod.POST})
    public RestResponse<Void> editAddress(@RequestBody MemberAddress memberAddress) {

        //MemberAddress memberAddress = new MemberAddress();
        if(memberAddressService.update(memberAddress)){
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
    public RestResponse<Void> setDefaultAddress(long addressId, String addressType) {

        //取消默认地址
        MemberAddress model = new MemberAddress();
        model.setMemberId(getCurrentMemberId());
        model.setAddressType(addressType);
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