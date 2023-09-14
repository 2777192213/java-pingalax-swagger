package com.pingalax.basic.information.dto;

import com.pingalax.basic.equipments.dto.Equipment;
import com.pingalax.basic.site.dto.Site;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhouxiaotao
 * @Description: TODO
 * @date 2023-08-10 15:02
 */
@Data
@ApiModel("数据上传类")
public class CollectData {
    @ApiModelProperty(value = "站点信息",required = true)
    private Site siteInfo;
    @ApiModelProperty(value = "设备SN码",required = true)
    private String equipmentSnCode;
    @ApiModelProperty(value = "数据",required = true)
    private Equipment data;
    @ApiModelProperty(value = "创建时间",required = true)
    private LocalDateTime createTime;
}
