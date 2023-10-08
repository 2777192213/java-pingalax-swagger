package com.pingalax.biz.setmeal.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pingalax.biz.setmeal.SetmealBiz;
import com.pingalax.biz.setmeal.bo.SetmealBo;
import com.pingalax.biz.setmeal.bo.SetmealRequestBo;
import com.pingalax.common.exceptions.handler.BizException;
import com.pingalax.dao.setmeal.SetmealDao;
import com.pingalax.dao.setmeal.entity.SetmealEntity;
import com.pingalax.dao.setmealdish.SetmealDishDao;
import com.pingalax.dao.setmealdish.entity.SetmealDishEntity;
import com.pingalax.ext.setmealdish.dto.SetmealDish;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhouxiaotao
 * @Description: 套餐业务层实现类
 * @date 2023-09-20 14:21
 */
@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SetmealBizImpl implements SetmealBiz {
    private final SetmealDao setmealDao;
    private final SetmealDishDao setmealDishDao;

    @Override
    @Transactional
    public boolean addSetmeal(SetmealRequestBo setmealRequestBo) {
        boolean bool = false;
        LambdaQueryWrapper<SetmealEntity> queryWrapper = new LambdaQueryWrapper<>(SetmealEntity.class);
        queryWrapper.eq(setmealRequestBo.getCategoryName() != null, SetmealEntity::getName, setmealRequestBo.getCategoryName());
        if (setmealDao.selectCount(queryWrapper) != 0) {
            throw new BizException("E400", "分类已存在");
        }
        SetmealEntity setmealEntity = new SetmealEntity();
        BeanUtils.copyProperties(setmealRequestBo, setmealEntity);
        setmealDao.insert(setmealEntity);
        List<SetmealDish> setmealDishList = setmealRequestBo.getSetmealDishList();
        long startTime = System.currentTimeMillis();
        for (SetmealDish setmealDish : setmealDishList) {
            SetmealDishEntity setmealDishEntity = new SetmealDishEntity();
            BeanUtils.copyProperties(setmealDish, setmealDishEntity);
            try {
                int insert = setmealDishDao.insert(setmealDishEntity);
                if (insert > 0) {
                    bool = true;
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        // 记录结束时间
        long endTime = System.currentTimeMillis();
        // 计算运行时间（毫秒）
        long elapsedTime = endTime - startTime;
        log.info("程序块运行时间（毫秒）: {}", elapsedTime);
        return bool;
    }

    @Override
    public Integer removeSetmeal(SetmealBo SetmealBo) {
        return null;
    }
}
