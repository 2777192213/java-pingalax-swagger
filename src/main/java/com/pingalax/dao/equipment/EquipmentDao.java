package com.pingalax.dao.equipment;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.pingalax.dao.equipment.entity.EquipmentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * @author zhouxiaotao
 * @Description: mapper
 * @date 2023-08-09 14:46
 */
@Mapper
public interface EquipmentDao extends BaseMapper<EquipmentEntity> {
}
