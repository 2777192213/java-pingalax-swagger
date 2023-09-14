package com.pingalax.biz.site;

import com.pingalax.biz.site.bo.QuerySitePageBo;
import com.pingalax.biz.site.bo.SiteBo;
import com.pingalax.common.util.baseresult.ResultData;
import com.pingalax.common.util.page.PageResult;
import com.pingalax.dao.site.entity.SiteEntity;

import java.util.List;

/**
 * @author zhouxiaotao
 * @Description: site操作
 * @date 2023-08-07 11:21
 */
public interface SiteBiz {
    /**
     * 添加site
     *
     * @param siteBo
     * @return
     */
    ResultData<SiteEntity> addSite(SiteBo siteBo);

    /**
     * 通过ID删除
     *
     * @param SiteId
     */
    Integer removeSite(Integer SiteId);

    /**
     * 修改site
     *
     * @param siteBO
     * @return
     */
    Integer modifySite(SiteBo siteBO);

    //void modifySite(Integer siteID, Integer newStatus);

    /**
     * 查询全部site
     *
     * @param
     * @return
     */
    PageResult<SiteBo> querySitesPage(QuerySitePageBo querySitePageBo);

    /**
     * 分页查找site
     *
     * @return
     */
    List<SiteBo> querySitesList();


    //PageResult<SiteBo> queryBookPage(QueryBookPageBO queryBookPageBO);

    //List<SiteBo> queryBooksPage(List<Integer> bookIdList);

}
