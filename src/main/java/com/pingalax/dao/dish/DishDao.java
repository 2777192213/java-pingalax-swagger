package com.pingalax.dao.dish;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pingalax.dao.dish.entity.DishEntity;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author zhouxiaotao
 * @Description: 菜品数据操作层
 * @date 2023-09-19 19:28
 */
@Mapper
public interface DishDao extends BaseMapper<DishEntity> {
}
