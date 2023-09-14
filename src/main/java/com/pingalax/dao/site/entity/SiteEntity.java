package com.pingalax.dao.site.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zhouxiaotao
 * @Description: site实体
 * @date 2023-08-07 11:30
 */
@Data
@TableName("p_site")
public class SiteEntity {

    @TableId(type = IdType.AUTO)
    private Integer siteId;

    private String siteLongitude;

    private String siteLatitude;

    private String siteName;

    private Integer status;
}
