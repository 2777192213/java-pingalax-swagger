package com.pingalax.biz.category.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pingalax.biz.category.CategoryBiz;
import com.pingalax.biz.category.bo.CategoryBo;
import com.pingalax.biz.category.bo.CategoryPageBo;
import com.pingalax.common.exceptions.ArgumentException;
import com.pingalax.common.util.page.PageResult;
import com.pingalax.common.util.page.PageUtil;
import com.pingalax.common.util.result.ResultUtil;
import com.pingalax.dao.category.CategoryDao;
import com.pingalax.dao.category.entity.CategoryEntity;
import com.pingalax.dao.dish.DishDao;
import com.pingalax.dao.dish.entity.DishEntity;
import com.pingalax.dao.setmeal.SetmealDao;
import com.pingalax.dao.setmeal.entity.SetmealEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhouxiaotao
 * @Description: 分类业务实现层
 * @date 2023-09-18 11:10
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryBizImpl implements CategoryBiz {
    private final CategoryDao categoryDao;
    private final DishDao dishDao;
    private final SetmealDao setmealDao;


    @Override
    public boolean addCategory(CategoryBo categoryBo) {
        CategoryEntity categoryEntity = new CategoryEntity();
        BeanUtils.copyProperties(categoryBo, categoryEntity);
        int insert;
        try {
            insert = categoryDao.insert(categoryEntity);
        } catch (RuntimeException e) {
            throw new ArgumentException("E40010001", "分类名称已存在");
        }
        return insert != 0;
    }

    @Override
    public boolean removeCategory(Long id) {
        if (categoryDao.selectById(id) == null) {
            throw new ArgumentException("E400", "分类不存在");
        }
        LambdaQueryWrapper<SetmealEntity> setmealEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(SetmealEntity.class);
        setmealEntityLambdaQueryWrapper.eq(SetmealEntity::getId, id);
        if (setmealDao.selectCount(setmealEntityLambdaQueryWrapper) > 0) {
            throw new ArgumentException("E400", "分类关联有套餐");
        }
        LambdaQueryWrapper<DishEntity> dishEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(DishEntity.class);
        dishEntityLambdaQueryWrapper.eq(DishEntity::getId, id);
        if (dishDao.selectCount(dishEntityLambdaQueryWrapper) > 0) {
            throw new ArgumentException("E400", "分类关联有菜品");
        }
        return categoryDao.deleteById(id) != 0;
    }

    @Override
    public PageResult<CategoryBo> queryCategoryByPage(CategoryPageBo categoryPageBo) {
        LambdaQueryWrapper<CategoryEntity> queryWrapper = new LambdaQueryWrapper<>(CategoryEntity.class);
        //更具类型筛选，更具升序排序
        queryWrapper.eq(CategoryEntity::getType, categoryPageBo.getType()).orderByAsc(CategoryEntity::getSort);
        Page<CategoryEntity> categoryEntityPage = categoryDao.selectPage(PageUtil.createPage(categoryPageBo), queryWrapper);
        return ResultUtil.convert(categoryEntityPage, CategoryBo.class);
    }

    @Override
    public List<CategoryBo> queryCategoryList(Integer type) {
        LambdaQueryWrapper<CategoryEntity> queryWrapper = new LambdaQueryWrapper<>(CategoryEntity.class);
        queryWrapper.eq(type != null, CategoryEntity::getType, type).orderByAsc(CategoryEntity::getSort);
        List<CategoryEntity> categoryEntities = categoryDao.selectList(queryWrapper);
        return ResultUtil.convert(categoryEntities, CategoryBo.class);
    }

}
