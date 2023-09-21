package com.pingalax.dao.flavor;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pingalax.dao.flavor.entity.FlavorEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhouxiaotao
 * @Description: 口味表数据操作类
 * @date 2023-09-20 13:44
 */
@Mapper
public interface FlavorDao extends BaseMapper<FlavorEntity> {
}
