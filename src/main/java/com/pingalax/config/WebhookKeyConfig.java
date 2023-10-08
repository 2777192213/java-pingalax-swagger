package com.pingalax.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zhouxiaotao
 * @Description: 企业微信Webhook Key配置
 * @date 2023-10-07 14:53
 */
@Data
@Component
public class WebhookKeyConfig {

    @Value("${key:f52f2a4f-9795-45e9-a868-a1d6628a59ac}")
    private String key;
}
