package com.pingalax.common.util.beancopy.mapstructcopy;

import org.mapstruct.Mapper;

/**
 * @author zhouxiaotao
 * @Description: TODO
 * @date 2023-09-19 15:08
 */
@Mapper
public interface MapStructCopier{
    Target copy(Source source);
}
