-- 站点表
CREATE TABLE `p_site`
(
    `site_id` int NOT NULL AUTO_INCREMENT COMMENT '站点ID',
    `status` tinyint NOT NULL COMMENT '站点状态：0未知，1建设中，5关闭下线，6维护中，50正常使用',
    `site_latitude`  varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '纬度',
    `site_longitude` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '经度',
    `site_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '站点名称',
    PRIMARY KEY (`site_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='站点表';

-- 设备表
CREATE TABLE `p_equipment`
(
    `equipment_siteName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '所属站点',
    `equipment_sncode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '设备SN',
    `equipment_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '设备名称',
    `equipment_type` tinyint NOT NULL COMMENT '设备类型:2-储能、3-光伏、4-充电桩',
    PRIMARY KEY (`equipment_sncode`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='设备表';

-- 数据采集信息表
CREATE TABLE `pingalax_data_acquisition_info`
(
    `site_info` json DEFAULT NULL COMMENT '站点信息',
    `equipment_sncode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '设备SN',
    `data_info` json NOT NULL COMMENT '数据',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='数据采集信息表';


-- 设备上传信息
CREATE TABLE `pingalax_device_upload_info`
(
    `equipment_sncode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '设备SN',
    `data_info` json NOT NULL COMMENT '数据',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='设备上传信息表';
