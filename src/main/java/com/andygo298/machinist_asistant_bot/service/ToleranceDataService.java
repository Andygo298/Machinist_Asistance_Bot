package com.andygo298.machinist_asistant_bot.service;

import com.andygo298.machinist_asistant_bot.model.ToleranceData;
import com.andygo298.machinist_asistant_bot.repository.ToleranceDataMongoRepository;
import org.springframework.stereotype.Service;

@Service
public class ToleranceDataService {

    private ToleranceDataMongoRepository repository;

    public ToleranceDataService(ToleranceDataMongoRepository repository) {
        this.repository = repository;
    }

    ToleranceData findTolerance(Double dimension,
                                   String toleranceLetter,
                                   Integer toleranceValue) {
        return repository.findTolerance(dimension, toleranceLetter, toleranceValue);
    }
}
