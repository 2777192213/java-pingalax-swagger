package com.pingalax.basic.boot;

import com.pingalax.biz.boot.countdown.CountDownEventHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouxiaotao
 * @Description: 测试企业微信机器
 * @date 2023-10-07 15:02
 */
@Api(value = "企业微信相关测试", tags = "企业微信相关测试")
@RestController
@RequestMapping("/wx")
public class RobotController {

    @Autowired
    private CountDownEventHandler countDownEventHandler;

    @ApiOperation("测试机器")
    @PostMapping("/robot")
    public void robot() {
        countDownEventHandler.process();
    }

    @ApiOperation("测试机器markdownProcess")
    @PostMapping("/markdownProcess")
    public void markdownProcess() {
        countDownEventHandler.SendMarkdown();
    }

}
