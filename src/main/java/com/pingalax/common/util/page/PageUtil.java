package com.pingalax.common.util.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author zhouxiaotao
 * @Description: 创建分页对象
 * @date 2023-08-09 9:26
 */
public class PageUtil {
    public static <T> Page<T> createPage(BasePage basePage){
        return new Page<>(basePage.getPageIndex(), basePage.getPageSize());
    }
}
