package com.pingalax.dao.category;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pingalax.dao.category.entity.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhouxiaotao
 * @Description: 分类数据操作层
 * @date 2023-09-18 11:08
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
}
