package com.andygo298.machinist_asistant_bot.repository;

import com.andygo298.machinist_asistant_bot.model.ShaftToleranceData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShaftToleranceMongoRepository extends MongoRepository<ShaftToleranceData, String> {
   ShaftToleranceData findShaftToleranceDataByFromDimensionLessThanAndToDimensionGreaterThanEqual(Double dim1, Double dim2);
}
