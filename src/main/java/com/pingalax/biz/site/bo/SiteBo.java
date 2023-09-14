package com.pingalax.biz.site.bo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @author zhouxiaotao
 * @Description: site实体
 * @date 2023-08-07 11:22
 */
@Data
public class SiteBo {
    private Integer siteId;

    private String siteLongitude;

    private String siteLatitude;

    private String siteName;

    private Integer status;
}
