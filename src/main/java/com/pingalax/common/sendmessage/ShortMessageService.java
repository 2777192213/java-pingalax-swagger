package com.pingalax.common.sendmessage;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.Common;
import com.aliyun.teautil.models.RuntimeOptions;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhouxiaotao
 * @Description: 发送短信验证
 * @date 2023-09-22 14:23
 */
@Slf4j
public class ShortMessageService {

    // ACCESS_KEY_ID
    private static String ALIBABA_CLOUD_ACCESS_KEY_ID = "ACCESS_KEY_ID";
    // ACCESS_KEY_SECRET
    private static String ALIBABA_CLOUD_ACCESS_KEY_SECRET = "ACCESS_KEY_SECRET";

    /**
     * 使用AK&SK初始化账号Client
     *
     * @param accessKeyId     accessKeyId
     * @param accessKeySecret 密码
     * @return Client
     * @throws Exception 异常
     */
    public static Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 必填，您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 必填，您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new Client(config);
    }

    public static void sendMessage() throws Exception {
        // 请确保代码运行环境设置了环境变量 ALIBABA_CLOUD_ACCESS_KEY_ID 和 ALIBABA_CLOUD_ACCESS_KEY_SECRET。
        Client client = ShortMessageService.createClient(System.getenv(ALIBABA_CLOUD_ACCESS_KEY_ID), System.getenv(ALIBABA_CLOUD_ACCESS_KEY_SECRET));
        //setPhoneNumbers发送的对象
        //setSignName签名
        SendSmsRequest sendSmsRequest = new SendSmsRequest().setPhoneNumbers("your_value").setSignName("your_value");
        try {
            // 复制代码运行请自行打印 API 的返回值
            client.sendSmsWithOptions(sendSmsRequest, new RuntimeOptions());
        } catch (TeaException e) {
            // 如有需要，请打印 error
            Common.assertAsString(e.message);
        } catch (Exception e) {
            TeaException error = new TeaException(e.getMessage(), e);
            // 如有需要，请打印 error
            Common.assertAsString(error.message);
        }
    }
}
