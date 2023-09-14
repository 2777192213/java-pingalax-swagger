package com.pingalax.biz.information;

import com.pingalax.biz.information.bo.CollectDataBo;
import com.pingalax.biz.information.bo.QueryCollectPageBo;
import com.pingalax.common.util.page.PageResult;
import com.pingalax.dao.information.entity.CollectDataEntity;

import java.util.List;

/**
 * @author zhouxiaotao
 * @Description: TODO
 * @date 2023-08-10 14:09
 */

public interface CollectDataBiz {
    String saveData(CollectDataEntity collectDataEntity);
    List<CollectDataEntity>  queryDataAll();

    PageResult<CollectDataBo> queryDataBySite(QueryCollectPageBo queryCollectPageBo);
}
