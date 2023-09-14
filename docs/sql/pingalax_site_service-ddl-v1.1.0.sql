# 创建区域能源表
CREATE TABLE `p_area_energy`
(
    `id`                       int                                                           NOT NULL AUTO_INCREMENT COMMENT '区域能源ID',
    `area_name`                varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '区域',
    `charge_discharge_type`    json                                                                   DEFAULT NULL COMMENT '充放类型：1一充一放、2两充两放',
    `horizontal_radiation`     decimal(12, 2)                                                         DEFAULT NULL COMMENT '水平面总辐照量平均值（kWh/㎡）',
    `best_radiation`           decimal(12, 2)                                                         DEFAULT NULL COMMENT '最佳斜面总辐照量平均值（kWh/㎡）',
    `grid_electricity_price`   decimal(15, 10)                                                        DEFAULT NULL COMMENT '上网电价（元/kWh）',
    `average_use_price`        decimal(15, 10)                                                        DEFAULT NULL COMMENT '平均用电电价（元/kWh）',
    `peak_electricity_price`   decimal(15, 10)                                                        DEFAULT NULL COMMENT '峰电价（元/kWh）',
    `flat_electricity_price`   decimal(15, 10)                                                        DEFAULT NULL COMMENT '平电价（元/kWh）',
    `valley_electricity_price` decimal(15, 10)                                                        DEFAULT NULL COMMENT '谷电价（元/kWh）',
    `sort`                     int                                                                    DEFAULT NULL COMMENT '排序',
    `create_time`              timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`              timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='区域能源表';

# 插入数据