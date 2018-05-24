package com.cotton.abmallback.web.controller.front;

import com.cotton.abmallback.model.MsgMemberMessage;
import com.cotton.abmallback.model.MsgPlatformMessage;
import com.cotton.abmallback.service.MsgMemberMessageService;
import com.cotton.abmallback.service.MsgPlatformMessageService;
import com.cotton.abmallback.web.controller.ABMallFrontBaseController;
import com.cotton.base.common.RestResponse;
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
 * Message
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/5/24
 */
@Controller
@RequestMapping("/message")
public class MessageController extends ABMallFrontBaseController {

    private Logger logger = LoggerFactory.getLogger(MessageController.class);

    private final MsgMemberMessageService msgMemberMessageService;

    private final MsgPlatformMessageService msgPlatformMessageService;

    @Autowired
    public MessageController(MsgMemberMessageService msgMemberMessageService, MsgPlatformMessageService msgPlatformMessageService) {
        this.msgMemberMessageService = msgMemberMessageService;
        this.msgPlatformMessageService = msgPlatformMessageService;
    }

    @ResponseBody
    @RequestMapping(value = "/example")
    public RestResponse<Map<String, Object>> example() {

        Map<String, Object> map = new HashMap<>(2);

        return RestResponse.getSuccesseResponse(map);
    }

    @ResponseBody
    @RequestMapping(value = "/list")
    public RestResponse<List<MsgMemberMessage>> list(@RequestParam(defaultValue = "1") int pageNum,
                                                     @RequestParam(defaultValue = "4") int pageSize) {

        Example example = new Example(MsgMemberMessage.class);
        example.setOrderByClause("gmt_create desc");

        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted",false);
        criteria.andEqualTo("memberId",getCurrentMemberId());
        PageInfo<MsgMemberMessage> msgMemberMessagePageInfo = msgMemberMessageService.query(pageNum,pageSize,example);

        if(msgMemberMessagePageInfo == null ){
            logger.error("读取消息列表失败");
            return RestResponse.getSystemInnerErrorResponse();
        }
        return RestResponse.getSuccesseResponse(msgMemberMessagePageInfo.getList());
    }


    @ResponseBody
    @RequestMapping(value = "/readMessage")
    public RestResponse<Void> readMessage(long messageId) {

        MsgMemberMessage msgMemberMessage = new MsgMemberMessage();
        msgMemberMessage.setId(messageId);
        msgMemberMessage.setIsRead(true);

        if(msgMemberMessageService.update(msgMemberMessage)){
            return RestResponse.getSuccesseResponse();
        }else {
            return RestResponse.getFailedResponse(500,"更新阅读状态失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/platformMessage")
    public RestResponse<MsgPlatformMessage> platformMessage(long messageId) {

        MsgPlatformMessage msgPlatformMessage = msgPlatformMessageService.getById(messageId);

        if(null != msgPlatformMessage){
            return RestResponse.getSuccesseResponse(msgPlatformMessage);
        }else {
            return RestResponse.getFailedResponse(500,"读取消息失败");
        }
    }

}