package com.pingalax.common.util.page;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhouxiaotao
 * @Description: PageInfo实体
 * @date 2023-08-08 13:16
 */
@Data
@NoArgsConstructor
public class PageInfo implements Serializable {
    private Long pageIndex;
    private Long pageSize;
    private Long totalRecord;
    private Long totalPage;
}
