package com.pingalax.biz.equipment;

import com.pingalax.biz.equipment.bo.EquipmentBo;
import com.pingalax.biz.equipment.bo.QueryEquipmentPageBO;
import com.pingalax.common.util.baseresult.ResultData;
import com.pingalax.common.util.page.PageResult;
import com.pingalax.dao.equipment.entity.EquipmentEntity;


/**
 * @author zhouxiaotao
 * @Description: 执行类
 * @date 2023-08-09 14:38
 */
public interface EquipmentBiz {
    /**
     * 增加设备
     */
    ResultData<EquipmentEntity> addEquipment(EquipmentBo equipmentBo);

    /**
     * 删除设备
     */
    String removeEquipment(String equipment_sn_code);

    /**
     * 编辑设备
     *
     * @return
     */
    Integer modifyEquipment(EquipmentBo equipmentBo);

    /**
     * 通过snCode查找设备
     */
    EquipmentBo queryEquipment(String snCode);

    /**
     * 分页查找设备
     * @param queryEquipmentPageBO 分页
     * @return PageResult<EquipmentBo>
     */
    PageResult<EquipmentBo> queryEquipmentByPage(QueryEquipmentPageBO queryEquipmentPageBO);

}
