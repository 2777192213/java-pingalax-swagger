package com.pingalax.biz.site.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pingalax.biz.site.SiteBiz;
import com.pingalax.biz.site.bo.QuerySitePageBo;
import com.pingalax.biz.site.bo.SiteBo;
import com.pingalax.common.util.baseresult.ResultData;
import com.pingalax.common.util.page.PageResult;
import com.pingalax.common.util.page.PageUtil;
import com.pingalax.common.util.result.ResultUtil;
import com.pingalax.dao.site.SiteDao;
import com.pingalax.dao.site.entity.SiteEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhouxiaotao
 * @Description: 站点相关实现类
 * @date 2023-08-07 11:22
 */
@Component
@Transactional
@Slf4j
public class SiteBizImpl implements SiteBiz {
    @Autowired
    private SiteDao siteDao;

    @Override
    public ResultData<SiteEntity> addSite(SiteBo siteBo) {
        LambdaQueryWrapper<SiteEntity> queryWrapper = new LambdaQueryWrapper<>(SiteEntity.class);
        queryWrapper.eq(SiteEntity::getSiteId, siteBo.getSiteId());
        SiteEntity siteEntity = BeanUtil.copyProperties(siteBo, SiteEntity.class);
        siteDao.insert(siteEntity);
        return ResultUtil.createResultData(siteEntity);
    }

    @Override
    public Integer removeSite(Integer SiteId) {
        //查询站点
        SiteEntity siteEntity = siteDao.selectById(SiteId);
        //删除站点
        if (siteEntity != null)
            siteDao.deleteById(SiteId);
        else {
            return -1;
        }
        return SiteId;
    }

    @Override
    public Integer modifySite(SiteBo bookBO) {
        SiteEntity siteEntity = BeanUtil.copyProperties(bookBO, SiteEntity.class);
        siteDao.updateById(siteEntity);
        return siteDao.updateById(siteEntity);
    }

    @Cacheable(value = "PageResult",key = "#querySitePageBo")
    @Override
    public PageResult<SiteBo> querySitesPage(QuerySitePageBo querySitePageBo) {
        LambdaQueryWrapper<SiteEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>(SiteEntity.class);
        lambdaQueryWrapper.eq(querySitePageBo.getStatus() != null, SiteEntity::getStatus, querySitePageBo.getStatus());
        IPage<SiteEntity> siteEntityIPage = siteDao.selectPage(PageUtil.createPage(querySitePageBo), lambdaQueryWrapper);
        return ResultUtil.convert(siteEntityIPage, SiteBo.class);
    }

    @Override
    public List<SiteBo> querySitesList() {
        return null;
    }

}
