package com.pingalax.biz.category;

import com.pingalax.biz.category.bo.CategoryBo;
import com.pingalax.biz.category.bo.CategoryPageBo;
import com.pingalax.common.util.page.PageResult;

import java.util.List;

/**
 * @author zhouxiaotao
 * @Description: 分类业务层
 * @date 2023-09-18 11:10
 */
public interface CategoryBiz {
    /**
     * 增加分类
     *
     * @param categoryBo
     */
    boolean addCategory(CategoryBo categoryBo);

    /**
     * 删除分类
     *
     * @param name 分类名
     */
    boolean removeCategory(Long name);

    /**
     * 分页查找
     *
     * @param categoryPageBo 分页
     * @return 分页结果列表
     */
    PageResult<CategoryBo> queryCategoryByPage(CategoryPageBo categoryPageBo);

    /**
     * 更具条件查找分类列表
     *
     * @param type 分类类型
     * @return 列表
     */
    List<CategoryBo> queryCategoryList(Integer type);
}
