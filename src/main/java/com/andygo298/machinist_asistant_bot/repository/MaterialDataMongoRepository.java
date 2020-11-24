package com.andygo298.machinist_asistant_bot.repository;

import com.andygo298.machinist_asistant_bot.model.MaterialData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialDataMongoRepository extends MongoRepository<MaterialData, String> {
    MaterialData findByMaterialName(String materialName);
}
