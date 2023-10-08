package com.pingalax.dao.setmealdish;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pingalax.dao.setmealdish.entity.SetmealDishEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhouxiaotao
 * @Description: 套餐和菜品关系实现层
 * @date 2023-09-21 17:01
 */
@Mapper
public interface SetmealDishDao extends BaseMapper<SetmealDishEntity> {
    int batchInsert(@Param("list") List<SetmealDishEntity> list);
}
