package com.pingalax.dao.site;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pingalax.dao.site.entity.SiteEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhouxiaotao
 * @Description: 站点dao层
 * @date 2023-08-07 11:31
 */
@Mapper
public interface SiteDao extends BaseMapper<SiteEntity> {
}
