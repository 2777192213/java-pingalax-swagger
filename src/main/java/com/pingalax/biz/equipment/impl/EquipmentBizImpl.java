package com.pingalax.biz.equipment.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pingalax.biz.equipment.EquipmentBiz;
import com.pingalax.biz.equipment.bo.EquipmentBo;
import com.pingalax.biz.equipment.bo.QueryEquipmentPageBO;
import com.pingalax.common.exceptions.ResultExceptionEnum;
import com.pingalax.common.util.baseresult.ResultData;
import com.pingalax.common.util.page.PageResult;
import com.pingalax.common.util.page.PageUtil;
import com.pingalax.common.util.result.ResultUtil;
import com.pingalax.dao.equipment.EquipmentDao;
import com.pingalax.dao.equipment.entity.EquipmentEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;


/**
 * @author zhouxiaotao
 * @Description: 设备实现类
 * @date 2023-08-09 14:40
 */
@Component
@Slf4j
public class EquipmentBizImpl implements EquipmentBiz {
    @Autowired
    private EquipmentDao equipmentDao;

    @Override
    public ResultData<EquipmentEntity> addEquipment(EquipmentBo equipmentBo) {
        LambdaQueryWrapper<EquipmentEntity> queryWrapper = new LambdaQueryWrapper<>(EquipmentEntity.class);
        queryWrapper.eq(EquipmentEntity::getEquipmentSnCode, equipmentBo.getEquipmentSnCode());
        if (equipmentDao.selectCount(queryWrapper) != 0) {
            ResultUtil.throwResultException(ResultExceptionEnum.DEVICE_EXISTS_SN);
        }
        EquipmentEntity equipmentEntity = BeanUtil.copyProperties(equipmentBo, EquipmentEntity.class);
        equipmentDao.insert(equipmentEntity);
        return ResultUtil.createResultData(equipmentEntity);
    }

    @Override
    public String removeEquipment(String equipmentSnCode) {

        LambdaQueryWrapper<EquipmentEntity> queryWrapper = new LambdaQueryWrapper<>(EquipmentEntity.class);
        queryWrapper.eq(equipmentSnCode != null, EquipmentEntity::getEquipmentSnCode, equipmentSnCode);
        if (equipmentDao.delete(queryWrapper)<1) {
            ResultUtil.throwResultException(ResultExceptionEnum.EQUIPMENT_DOES_NOT_EXIST);
        }
        return equipmentSnCode;
    }

    @Override
    public Integer modifyEquipment(EquipmentBo equipmentBo) {
        LambdaUpdateWrapper<EquipmentEntity> updateWrapper = new LambdaUpdateWrapper<>(EquipmentEntity.class);
        EquipmentEntity equipmentEntity = BeanUtil.copyProperties(equipmentBo, EquipmentEntity.class);
        updateWrapper.eq(EquipmentEntity::getEquipmentSnCode,equipmentEntity.getEquipmentSnCode())
                .set(EquipmentEntity::getEquipmentName,equipmentBo.getEquipmentName())
                .set(EquipmentEntity::getEquipmentSiteName,equipmentBo.getEquipmentSiteName())
                .set(EquipmentEntity::getEquipmentType,equipmentBo.getEquipmentType());

        return equipmentDao.update(equipmentEntity, updateWrapper);
    }

    @Cacheable(value = "EquipmentCache",key = "#snCode") //缓存
    @Override
    public EquipmentBo queryEquipment(String snCode) {
        LambdaQueryWrapper<EquipmentEntity> queryWrapper = new LambdaQueryWrapper<>(EquipmentEntity.class);
        queryWrapper.eq(EquipmentEntity::getEquipmentSnCode,snCode);
        EquipmentEntity equipmentEntity = equipmentDao.selectOne(queryWrapper);
        return BeanUtil.copyProperties(equipmentEntity, EquipmentBo.class);
    }

    @Override
    public PageResult<EquipmentBo> queryEquipmentByPage(QueryEquipmentPageBO queryEquipmentPageBO) {
        LambdaQueryWrapper<EquipmentEntity> queryWrapper = new LambdaQueryWrapper<>(EquipmentEntity.class);
        queryWrapper.eq(EquipmentEntity::getEquipmentSiteName,queryEquipmentPageBO.getEquipmentSiteName())
                .eq(EquipmentEntity::getEquipmentType,queryEquipmentPageBO.getEquipmentType());
        IPage<EquipmentEntity> EntityPage = equipmentDao.selectPage(PageUtil.createPage(queryEquipmentPageBO), queryWrapper);
        return ResultUtil.convert(EntityPage, EquipmentBo.class);
    }

}
