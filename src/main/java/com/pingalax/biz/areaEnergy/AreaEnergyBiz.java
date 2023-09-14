package com.pingalax.biz.areaEnergy;


import com.pingalax.biz.areaEnergy.bo.AreaEnergyBo;
import com.pingalax.biz.areaEnergy.bo.AreaEnergyRequestBo;
import com.pingalax.biz.areaEnergy.bo.AreaEnergyResponseBo;

import java.util.List;

/**
 * @author zhouxiaotao
 * @Description: 区域能源业务层
 * @date 2023-08-17 14:48
 */
public interface AreaEnergyBiz {
    /**
     * 查询区域数据列表
     *
     * @return list
     */
    List<AreaEnergyBo> queryRegionalEnergyList();


    /**
     * 测算能源
     *
     * @param areaEnergyRequestBo
     * @return
     */
    AreaEnergyResponseBo computeInvestmentIncome(AreaEnergyRequestBo areaEnergyRequestBo);
}
