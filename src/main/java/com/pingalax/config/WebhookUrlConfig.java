package com.pingalax.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * @author zhouxiaotao
 * @Description: 企业微信Webhook地址配置
 * @date 2023-10-07 14:50
 */
@Data
@Component
public class WebhookUrlConfig {

    @Value("${url:https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=}")
    private String url;
}
