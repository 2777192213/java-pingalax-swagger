package com.pingalax.common.util.beancopy.mapstructcopy;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.lang.annotation.Target;

/**
 * @author zhouxiaotao
 * @Description: TODO
 * @date 2023-09-19 16:36
 */
@Component
public class MapsCopier {
    private MapStructCopier mapStructCopier = Mappers.getMapper(MapStructCopier.class);

    public static Target copy(Source source, Class<Target> target) {
        //return (Target) mapStructCopier.copy(source);
        return null;
    }
}
