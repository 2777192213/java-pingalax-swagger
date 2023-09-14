package com.pingalax.basic.site.dto;

import com.pingalax.common.annotations.AsserEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * @author zhouxiaotao
 * @Description: 站点实体
 * @date 2023-08-06 22:11
 */
@Data
@ApiModel("站点")
public class Site implements Serializable {
    //@NotNull(message = "经度不能为空")
    @ApiModelProperty(value = "经度",example = "106.23617459748075")
    @Length(max = 300, message = "最大长度255")
    private String siteLongitude;

    //@NotNull(message = "纬度不能为空")
    @ApiModelProperty(value = "纬度", example = "29.581268809310224")
    private String siteLatitude;

    @NotNull(message = "站点名称不能为空")
    @ApiModelProperty(value = "站点名称", required = true)
    private String siteName;

    @NotNull(message = "站点状态不能为空")
    @ApiModelProperty(value = "站点状态：0未知，1建设中，5关闭下线，6维护中，50正常使用", required = true)
    @AsserEnum(value = SiteStatusEnum.class, message = "站点类别不正确")
    private Integer status;

}
