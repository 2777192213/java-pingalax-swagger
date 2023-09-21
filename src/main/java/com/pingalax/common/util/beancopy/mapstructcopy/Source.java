package com.pingalax.common.util.beancopy.mapstructcopy;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zhouxiaotao
 * @Description: TODO
 * @date 2023-09-19 16:54
 */
@Data
public class Source {
    private Integer id;
    private String user_name;
    private Double price;
    private List<Long> ids;
    private BigDecimal marketPrice;
}
