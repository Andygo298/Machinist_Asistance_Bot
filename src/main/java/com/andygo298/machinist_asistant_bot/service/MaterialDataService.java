package com.andygo298.machinist_asistant_bot.service;

import com.andygo298.machinist_asistant_bot.model.MaterialData;
import com.andygo298.machinist_asistant_bot.repository.MaterialDataMongoRepository;
import org.springframework.stereotype.Service;

@Service
public class MaterialDataService {

    private MaterialDataMongoRepository materialDataMongoRepository;

    public MaterialDataService(MaterialDataMongoRepository materialDataMongoRepository) {
        this.materialDataMongoRepository = materialDataMongoRepository;
    }

    public MaterialData getMaterialData(String materialName){
        return materialDataMongoRepository.findByMaterialName(materialName);
    }
}
