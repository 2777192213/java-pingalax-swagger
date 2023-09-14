package com.pingalax.biz.information.bo;

import com.pingalax.basic.equipments.dto.Equipment;
import com.pingalax.basic.site.dto.Site;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhouxiaotao
 * @Description: Dao映射数据库表
 * @date 2023-08-10 14:11
 */
@Data
public class CollectDataBo implements Serializable {
    private Site siteInfo;
    private String equipmentSnCode;
    private Equipment data;
    private LocalDateTime createTime;
}