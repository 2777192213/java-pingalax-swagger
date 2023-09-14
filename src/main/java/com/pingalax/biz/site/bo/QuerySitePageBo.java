package com.pingalax.biz.site.bo;

import com.pingalax.common.util.page.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhouxiaotao
 * @Description: 分页条件
 * @date 2023-08-09 8:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QuerySitePageBo extends BasePage {
    private Integer status;
}
