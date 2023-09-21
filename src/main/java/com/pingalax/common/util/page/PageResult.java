package com.pingalax.common.util.page;

import com.pingalax.common.util.baseresult.Result;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhouxiaotao
 * @Description: 分页返回结果
 * @date 2023-08-08 13:07
 */
public class PageResult<T> extends Result implements Serializable {
    // 页面信息
    private PageInfo pageInfo;
    //返回数据
    private List<T> data;

    public PageResult() {
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
