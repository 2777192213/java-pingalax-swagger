package com.pingalax.dao.information.entity;

import com.pingalax.basic.equipments.dto.Equipment;
import com.pingalax.basic.site.dto.Site;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @author zhouxiaotao
 * @Description: 收集数据实体类
 * @date 2023-08-10 14:15
 */
@Data
@Document(collection = "site_data_info")
public class CollectDataEntity{
    private Site siteInfo;
    private String equipmentSnCode;
    private Equipment data;
    private LocalDateTime createTime;
}
