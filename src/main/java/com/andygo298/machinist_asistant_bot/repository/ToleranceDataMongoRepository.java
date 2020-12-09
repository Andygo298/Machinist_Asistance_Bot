package com.andygo298.machinist_asistant_bot.repository;

import com.andygo298.machinist_asistant_bot.model.ToleranceData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToleranceDataMongoRepository extends MongoRepository<ToleranceData, String> {
    ToleranceData findToleranceDataByFromDimensionLessThanAndToDimensionGreaterThanEqual(Double dim1,Double dim2);
}
