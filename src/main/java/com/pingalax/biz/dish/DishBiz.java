package com.pingalax.biz.dish;

import com.pingalax.biz.dish.bo.DishBo;
import com.pingalax.biz.dish.bo.DishPageRequestBo;
import com.pingalax.biz.dish.bo.DishPageResponseBo;
import com.pingalax.biz.dish.bo.DishRequestBo;
import com.pingalax.common.util.page.PageResult;

import java.util.List;

/**
 * @author zhouxiaotao
 * @Description: 菜品接口
 * @date 2023-09-20 14:03
 */
public interface DishBiz {
    /**
     * 新增菜品
     *
     * @param DishRequestBo 菜品
     * @return int
     */
    boolean addDish(DishRequestBo DishRequestBo);

    /**
     * 删除菜品
     *
     * @param dishBo 菜品
     * @return int
     */
    boolean removeDish(DishBo dishBo);

    /**
     * 通过分页查找菜品
     *
     * @param dishPageRequestBo 分页菜品
     * @return 分页结果
     */
    PageResult<DishPageResponseBo> queryDishByPage(DishPageRequestBo dishPageRequestBo);

    /**
     * @param id 菜品ID
     * @return 菜品
     */
    DishRequestBo queryDishById(Long id);

    /**
     * 编辑菜品
     *
     * @param DishRequestBo 菜品
     * @return int
     */
    boolean editDish(DishRequestBo DishRequestBo);


    List<DishBo> queryDishByCategoryId(Long categoryId);
}
