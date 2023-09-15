package com.pingalax.basic.solarpower;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouxiaotao
 * @Description: 光伏管理接口
 * @date 2023-08-06 21:28
 */
@Api(value = "光伏管理", tags = "光伏管理")
@RestController
@RequestMapping("/pingalax/basic/solarPowerSite")
public class SolarPowerController {
    
    @ApiOperation("增加光伏点")
    @PostMapping("/addSolarPower")
    public void addSolarPower() {

    }
    
    @ApiOperation("查询光伏点")
    @PostMapping("/selectSolarPower")
    public void selectSolarPower() {

    }
}
