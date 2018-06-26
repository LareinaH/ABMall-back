package com.cotton.abmallback.web.timer;

import com.dingtalk.chatbot.DingtalkChatbotClient;
import com.dingtalk.chatbot.SendResult;
import com.dingtalk.chatbot.demo.TestConfig;
import com.dingtalk.chatbot.message.TextMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

/**
 * ABMallTimmer
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/6/26
 */

@Component
public class ABMallTimer {

    @Scheduled(cron = "0 0 12 * * ?" )
    //@Scheduled(cron = "0 47 10 ? * *")
    public void reportCurrentTime() {

        DingtalkChatbotClient client = new DingtalkChatbotClient();

        TextMessage message = new TextMessage("吃饭啦,吃饭啦!吃饭不积极,态度有问题!");

        message.setIsAtAll(true);

        SendResult result = null;
        try {
            result = client.send("https://oapi.dingtalk.com/robot/send?access_token=ff34b543367e3c66a035126d521f692f99aa75ad985bf814064fb5725489eaf2", message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);

    }


}
