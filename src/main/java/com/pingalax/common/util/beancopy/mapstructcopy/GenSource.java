package com.pingalax.common.util.beancopy.mapstructcopy;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Random;

/**
 * @author zhouxiaotao
 * @Description: TODO
 * @date 2023-09-19 16:58
 */
public class GenSource {
    private static Random random = new Random();
    public static Source genSourceData(){
        Source source=new Source();
        source.setId(random.nextInt());
        source.setIds(Arrays.asList(random.nextLong(),random.nextLong(),random.nextLong()));
        source.setMarketPrice(new BigDecimal(random.nextFloat()));
        source.setPrice(random.nextInt(120)/10.0d);
        source.setUser_name("一灰灰Blog");
        return source;
    }
}
