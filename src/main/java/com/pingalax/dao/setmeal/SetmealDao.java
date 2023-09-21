package com.pingalax.dao.setmeal;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pingalax.dao.setmeal.entity.SetmealEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhouxiaotao
 * @Description: 套餐数据操作层
 * @date 2023-09-19 19:29
 */
@Mapper
public interface SetmealDao extends BaseMapper<SetmealEntity> {
}
