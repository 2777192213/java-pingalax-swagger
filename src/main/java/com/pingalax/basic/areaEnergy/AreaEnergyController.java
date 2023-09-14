package com.pingalax.basic.areaEnergy;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.pingalax.basic.areaEnergy.dto.AreaEnergy;
import com.pingalax.basic.areaEnergy.dto.AreaEnergyRequest;
import com.pingalax.biz.areaEnergy.AreaEnergyBiz;
import com.pingalax.common.util.baseresult.ResultData;
import com.pingalax.common.util.result.ResultUtil;
import com.pingalax.biz.areaEnergy.bo.AreaEnergyBo;
import com.pingalax.biz.areaEnergy.bo.AreaEnergyRequestBo;
import com.pingalax.biz.areaEnergy.bo.AreaEnergyResponseBo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author zhouxiaotao
 * @Description: 投资收益计算接口层
 * @date 2023-08-21 9:38
 */

@Api(value = "投资收益相关", tags = "投资收益相关")
@RestController
@RequestMapping("/pingalax/basic/areaEnergy")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))

public class AreaEnergyController {
    private final AreaEnergyBiz areaEnergyBiz;

    @ApiOperation("投资计算收益")
    @ResponseBody
    @PostMapping("/computeInvestmentIncome")
    public ResultData<AreaEnergy> computeInvestmentIncome(@RequestBody @Valid AreaEnergyRequest areaEnergyQuest) {
        AreaEnergyRequestBo areaEnergyRequestBo = BeanUtil.copyProperties(areaEnergyQuest, AreaEnergyRequestBo.class);
        AreaEnergyResponseBo areaEnergyResponseBoResultData = areaEnergyBiz.computeInvestmentIncome(areaEnergyRequestBo);
        AreaEnergy areaEnergy = new AreaEnergy();
        if (ObjectUtils.isNotNull(areaEnergyResponseBoResultData)) {
            BeanUtil.copyProperties(areaEnergyResponseBoResultData, areaEnergy);
        }
        return ResultUtil.createResultData(areaEnergy);
    }

    @ApiOperation("查询区域能源列表")
    @ResponseBody
    @PostMapping("/queryRegionalEnergyList")
    public ResultData<List<AreaEnergyBo>> queryRegionalEnergyList() {
        List<AreaEnergyBo> areaEnergyBoList = areaEnergyBiz.queryRegionalEnergyList();
        return ResultUtil.createResultData(areaEnergyBoList);
    }
}

