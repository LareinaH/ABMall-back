package com.cotton.base.third;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * GeTuiService
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/5/29
 */
@Component
public class GeTuiService {

    @Value("${getui.appId}")
    private  String appId = "";
    @Value("${getui.appKey}")
    private  String appKey = "";
    @Value("${getui.masterSecret}")
    private  String masterSecret = "";
    @Value("${getui.url}")
    private  String url = "http://sdk.open.api.igexin.com/apiex.htm";

    public  void pushMessage(String title,String text) {

        IGtPush push = new IGtPush(url, appKey, masterSecret);

        // 定义"点击链接打开通知模板"，并设置标题、内容、链接
        LinkTemplate template = new LinkTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setTitle(title);
        template.setText(text);
        template.setUrl("http://getui.com");

        List<String> appIds = new ArrayList<>();
        appIds.add(appId);

        // 定义"AppMessage"类型消息对象，设置消息内容模板、发送的目标App列表、是否支持离线发送、以及离线消息有效期(单位毫秒)
        AppMessage message = new AppMessage();
        message.setData(template);
        message.setAppIdList(appIds);
        message.setOffline(true);
        message.setOfflineExpireTime(1000 * 600);

        IPushResult ret = push.pushMessageToApp(message);
        System.out.println(ret.getResponse().toString());
    }
}
