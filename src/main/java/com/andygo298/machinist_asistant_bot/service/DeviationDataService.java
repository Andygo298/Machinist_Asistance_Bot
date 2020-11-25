package com.andygo298.machinist_asistant_bot.service;

import com.andygo298.machinist_asistant_bot.model.DeviationData;
import com.andygo298.machinist_asistant_bot.repository.DeviationDataMongoRepository;
import org.springframework.stereotype.Service;

@Service
public class DeviationDataService {

    public DeviationDataService(DeviationDataMongoRepository repository) {
        this.repository = repository;
    }

    private DeviationDataMongoRepository repository;

    public void saveToDeviationData(DeviationData deviationData){
        repository.save(deviationData);
    }

}
