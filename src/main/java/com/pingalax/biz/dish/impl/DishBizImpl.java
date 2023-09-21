package com.pingalax.biz.dish.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pingalax.biz.dish.DishBiz;
import com.pingalax.biz.dish.bo.DishBo;
import com.pingalax.biz.dish.bo.DishPageRequestBo;
import com.pingalax.biz.dish.bo.DishPageResponseBo;
import com.pingalax.biz.dish.bo.DishRequestBo;
import com.pingalax.common.exceptions.handler.BizException;
import com.pingalax.common.util.page.PageResult;
import com.pingalax.common.util.page.PageUtil;
import com.pingalax.common.util.result.ResultUtil;
import com.pingalax.dao.category.CategoryDao;
import com.pingalax.dao.category.entity.CategoryEntity;
import com.pingalax.dao.dish.DishDao;
import com.pingalax.dao.dish.entity.DishEntity;
import com.pingalax.dao.flavor.FlavorDao;
import com.pingalax.dao.flavor.entity.FlavorEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhouxiaotao
 * @Description: 菜品数据实现层
 * @date 2023-09-20 14:04
 */
@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DishBizImpl implements DishBiz {
    private final DishDao dishDao;
    private final FlavorDao flavorDao;
    private final CategoryDao categoryDao;

    @Override
    @Transactional //多表查询记得开启事务，保证数据的一致性
    public boolean addDish(DishRequestBo dishRequestBo) {
        LambdaQueryWrapper<DishEntity> queryWrapper = new LambdaQueryWrapper<>(DishEntity.class);
        queryWrapper.eq(DishEntity::getName, dishRequestBo.getName());
        Integer selectCount = dishDao.selectCount(queryWrapper);
        if (selectCount > 0) {
            throw new BizException("400", "菜品已存在");
        }
        DishEntity dishEntity = new DishEntity();
        FlavorEntity flavorEntity = new FlavorEntity();
        BeanUtils.copyProperties(dishRequestBo, dishEntity);
        BeanUtils.copyProperties(dishRequestBo.getFlavorEntity(), flavorEntity);
        int dInsert = 0;
        int fInsert = 0;
        try {
            dInsert = dishDao.insert(dishEntity);
            flavorEntity.setDishId(dishEntity.getId());
            fInsert = flavorDao.insert(flavorEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dInsert > 0 && fInsert > 0;
    }

    @Override
    public boolean removeDish(DishBo dishBo) {
        return true;
    }

    /**
     * 分页查询
     *
     * @param dishPageRequestBo 分页菜品
     * @return 分页数据
     */
    @Override
    public PageResult<DishPageResponseBo> queryDishByPage(DishPageRequestBo dishPageRequestBo) {
        LambdaQueryWrapper<DishEntity> queryWrapper = new LambdaQueryWrapper<>(DishEntity.class);
        Page<DishEntity> dishEntityPage = dishDao.selectPage(PageUtil.createPage(dishPageRequestBo), queryWrapper);
        Page<DishPageResponseBo> dishPageResponseBoPage = new Page<>();
        List<DishEntity> records = dishEntityPage.getRecords();
        List<DishPageResponseBo> dishPageResponseBoList = records.stream().map((item) -> {
            DishPageResponseBo dishPageResponseBo = new DishPageResponseBo();
            BeanUtil.copyProperties(item, dishPageResponseBo);
            Long categoryId = item.getCategoryId();
            CategoryEntity categoryEntity = categoryDao.selectById(categoryId);
            if (categoryEntity != null) {
                dishPageResponseBo.setCategoryName(categoryEntity.getName());
            }
            return dishPageResponseBo;
        }).collect(Collectors.toList());
        BeanUtils.copyProperties(dishPageResponseBoPage, dishEntityPage);
        dishPageResponseBoPage.setRecords(dishPageResponseBoList);
        return ResultUtil.convert(dishPageResponseBoPage, DishPageResponseBo.class);
    }

    /**
     * 通过ID查找菜品
     *
     * @param id 菜品ID
     * @return
     */

    @Override
    public DishRequestBo queryDishById(Long id) {
        LambdaQueryWrapper<FlavorEntity> flavorEntityQueryWrapper = new LambdaQueryWrapper<>(FlavorEntity.class);
        flavorEntityQueryWrapper.eq(FlavorEntity::getDishId, id).orderByAsc(FlavorEntity::getCreateTime).last("LIMIT 1");
        DishRequestBo dishRequestBo = new DishRequestBo();
        try {
            DishEntity dishEntity = dishDao.selectById(id);
            if (dishEntity == null) {
                throw new BizException("400", "菜品不存在");
            }
            FlavorEntity flavorEntity = flavorDao.selectOne(flavorEntityQueryWrapper);
            BeanUtils.copyProperties(dishEntity, dishRequestBo);
            dishRequestBo.setFlavorEntity(flavorEntity);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new BizException("400", "异常");
        }
        return dishRequestBo;
    }

    @Override
    @Transactional
    public boolean editDish(DishRequestBo dishRequestBo) {
        DishEntity dishEntity = new DishEntity();
        BeanUtils.copyProperties(dishRequestBo, dishEntity);
        int dishUpdateById = dishDao.updateById(dishEntity);
        
        int flavorUpdateById = flavorDao.updateById(dishRequestBo.getFlavorEntity());
        return dishUpdateById > 0 && flavorUpdateById > 0;
    }
}
