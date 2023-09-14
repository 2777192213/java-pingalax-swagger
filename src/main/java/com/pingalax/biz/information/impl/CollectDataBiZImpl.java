package com.pingalax.biz.information.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pingalax.biz.information.CollectDataBiz;
import com.pingalax.biz.information.bo.CollectDataBo;
import com.pingalax.biz.information.bo.QueryCollectPageBo;
import com.pingalax.common.util.page.PageResult;
import com.pingalax.dao.information.CollectDataDao;
import com.pingalax.dao.information.entity.CollectDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhouxiaotao
 * @Description: collect业务层实现层
 * @date 2023-08-10 14:10
 */
@Component
public class CollectDataBiZImpl implements CollectDataBiz {

    @Autowired
    private CollectDataDao collectDataDao;

    @Override
    @Transactional
    public String saveData(CollectDataEntity collectDataEntity) {
        CollectDataEntity insert = collectDataDao.insert(collectDataEntity);
        String equipmentSnCode = insert.getEquipmentSnCode();
        return equipmentSnCode;
    }

    @Override
    public List<CollectDataEntity> queryDataAll() {
        List<CollectDataEntity> collectDataDaoAll = collectDataDao.findAll();
        return collectDataDaoAll;
    }

    @Override
    public PageResult<CollectDataBo> queryDataBySite(QueryCollectPageBo queryCollectPageBo) {
        LambdaQueryWrapper<CollectDataEntity> queryWrapper = new LambdaQueryWrapper<>(CollectDataEntity.class);
        collectDataDao.findAll();
        return null;
    }

}
