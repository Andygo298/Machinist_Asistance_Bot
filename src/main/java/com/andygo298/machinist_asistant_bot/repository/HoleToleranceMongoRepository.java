package com.andygo298.machinist_asistant_bot.repository;

import com.andygo298.machinist_asistant_bot.model.HoleToleranceData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoleToleranceMongoRepository extends MongoRepository<HoleToleranceData, String> {
    HoleToleranceData findHoleToleranceDataByFromDimensionLessThanAndToDimensionGreaterThanEqual(Double dim1, Double dim2);
}
