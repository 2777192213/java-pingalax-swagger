package com.pingalax.common.util.sendhttprequest;

import cn.hutool.http.HttpRequest;

/**
 * @author zhouxiaotao
 * @Description: 发送http请求消息体
 * @date 2023-10-07 11:15
 */
public class SendHttpRequestUtil {
    public static void SendHttpRequest(String url, String key, String notificationData) {
        HttpRequest.post(url + key)
                .body(notificationData)
                .header("Content-Type", "application/json;charset=utf-8")
                .timeout(3000)
                .execute().body();
    }
    //https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=f52f2a4f-9795-45e9-a868-a1d6628a59ac

}
