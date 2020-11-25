package com.andygo298.machinist_asistant_bot.repository;

import com.andygo298.machinist_asistant_bot.model.DeviationData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviationDataMongoRepository extends MongoRepository<DeviationData,String> {
}
