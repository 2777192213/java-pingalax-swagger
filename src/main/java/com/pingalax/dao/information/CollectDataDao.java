package com.pingalax.dao.information;

import com.pingalax.dao.information.entity.CollectDataEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author zhouxiaotao
 * @Description: TODO
 * @date 2023-08-10 14:14
 */
@Mapper
public interface CollectDataDao extends MongoRepository<CollectDataEntity,String> {
}
