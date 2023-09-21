package com.pingalax.biz.setmeal;

import com.pingalax.biz.setmeal.bo.SetmealBo;

/**
 * @author zhouxiaotao
 * @Description: 套餐接口
 * @date 2023-09-20 14:20
 */
public interface SetmealBiz{
    /**
     * 新增菜品
     * @param SetmealBo 菜品
     * @return int
     */
    Integer addSetmeal(SetmealBo SetmealBo);

    /**
     * 删除菜品
     * @param SetmealBo 菜品
     * @return int
     */
    Integer removeSetmeal(SetmealBo SetmealBo);
}
