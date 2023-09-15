package com.pingalax.basic.site;

import cn.hutool.core.bean.BeanUtil;
import com.pingalax.basic.site.dto.QuerySitePageRequest;
import com.pingalax.basic.site.dto.Site;
import com.pingalax.biz.site.SiteBiz;
import com.pingalax.biz.site.bo.QuerySitePageBo;
import com.pingalax.biz.site.bo.SiteBo;
import com.pingalax.common.exceptions.ResultExceptionEnum;
import com.pingalax.common.util.baseresult.ResultData;
import com.pingalax.common.util.page.PageResult;
import com.pingalax.common.util.result.ResultUtil;
import com.pingalax.dao.site.entity.SiteEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author zhouxiaotao
 * @Description: 站点管理接口
 * @date 2023-08-06 21:07
 */
@Slf4j
@RestController
@Api(value = "站点管理", tags = "站点管理")
@RequestMapping("/pingalax/basic/site")
public class SiteController {
    @Autowired
    private SiteBiz siteBiz;

    @ApiOperation("增加站点")
    @PostMapping("/addSite")
    public ResultData<SiteEntity> addSite(@Validated Site site){
        SiteBo siteBo = BeanUtil.copyProperties(site, SiteBo.class);
        ResultData<SiteEntity> siteEntityResultData = siteBiz.addSite(siteBo);
        return siteEntityResultData;
    }

    @ApiOperation("删除站点")
    @PostMapping("/removeSite")
    public Object removeSite(Integer SiteId){
        Integer integer = siteBiz.removeSite(SiteId);
        if (integer == -1){
            return ResultUtil.createResultData(ResultExceptionEnum.SITE_DOES_NOT_EXIST.getDesc());
        }
        return ResultUtil.createNoDataResult();
    }

    @ApiOperation("编辑站点")
    @PostMapping("/editSite")
    public Integer modifySite(@Valid SiteBo siteBo){

        return siteBiz.modifySite(siteBo);
    }

    @ApiOperation("通过条件查询站点列表(分页)")
    //@Cacheable(value = "PageResult<Site>",key = "#querySitePageRequest")
    @ResponseBody
    @PostMapping("/selectSiteByPage")
    public PageResult<Site> selectSiteByPage(@RequestBody @Valid QuerySitePageRequest querySitePageRequest){
        PageResult<SiteBo> siteBoPageResult = siteBiz.querySitesPage(BeanUtil.copyProperties(querySitePageRequest, QuerySitePageBo.class));
        return ResultUtil.convert(siteBoPageResult, Site.class);
    }

}
