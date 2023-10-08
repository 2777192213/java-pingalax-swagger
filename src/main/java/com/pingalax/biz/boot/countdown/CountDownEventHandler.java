package com.pingalax.biz.boot.countdown;

import cn.hutool.json.JSONObject;
import com.pingalax.common.util.sendhttprequest.SendHttpRequestUtil;
import com.pingalax.config.WebhookKeyConfig;
import com.pingalax.config.WebhookUrlConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author zhouxiaotao
 * @Description: 测试企业微信机器人消息推送
 * @date 2023-10-07 11:20
 */
@Slf4j
@Component("CountDownEventHandler")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CountDownEventHandler {
    private final WebhookUrlConfig webhookUrlConfig;
    private final WebhookKeyConfig webhookKeyConfig;

    public void process() {
        String notificationData;
        notificationData = "{\"msgtype\": \"text\",\"text\": {\"content\": \"测试boot\"}}";
        //发送消息
        log.info("测试发送");
        log.info(webhookUrlConfig.getUrl() + webhookKeyConfig.getKey());
        SendHttpRequestUtil.SendHttpRequest(webhookUrlConfig.getUrl(), webhookKeyConfig.getKey(), notificationData);
    }


    public void SendMarkdown() {

        // Markdown格式的消息内容
        String markdownContent = "### Markdown格式的消息\n\n" +
                "> 示例\n\n" +
                "> - 列表项1\n" +
                "> - 列表项2\n\n";

        // 创建JSON对象，构建消息内容
        JSONObject messageJson = new JSONObject();
        messageJson.set("msgtype", "markdown");
        JSONObject markdown = new JSONObject();
        markdown.set("content", markdownContent);

        messageJson.set("markdown", markdown);

        //发送消息
        log.info("测试发送");
        log.info(webhookUrlConfig.getUrl() + webhookKeyConfig.getKey());
        SendHttpRequestUtil.SendHttpRequest(webhookUrlConfig.getUrl(), webhookKeyConfig.getKey(), messageJson.toString());
    }

}
