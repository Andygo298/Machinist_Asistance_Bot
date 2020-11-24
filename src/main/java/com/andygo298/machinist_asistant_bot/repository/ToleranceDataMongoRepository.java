package com.andygo298.machinist_asistant_bot.repository;

import com.andygo298.machinist_asistant_bot.model.ToleranceData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ToleranceDataMongoRepository extends MongoRepository<ToleranceData, String>, CustomToleranceRepo {
    ToleranceData findByToleranceLetterAndToleranceValueAndFromDimensionGreaterThanEqualAndToDimensionLessThan(
            String toleranceLetter,
            Integer toleranceValue,
            Double fromDimension,
            Double toDimension);

    ToleranceData findTolerance(Double dim, String letter, Integer valueTol);
}
