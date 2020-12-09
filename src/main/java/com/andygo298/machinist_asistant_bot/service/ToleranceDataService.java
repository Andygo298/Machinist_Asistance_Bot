package com.andygo298.machinist_asistant_bot.service;

import com.andygo298.machinist_asistant_bot.model.HoleToleranceData;
import com.andygo298.machinist_asistant_bot.model.ShaftToleranceData;
import com.andygo298.machinist_asistant_bot.model.ToleranceData;
import com.andygo298.machinist_asistant_bot.repository.HoleToleranceMongoRepository;
import com.andygo298.machinist_asistant_bot.repository.ShaftToleranceMongoRepository;
import com.andygo298.machinist_asistant_bot.repository.ToleranceDataMongoRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ToleranceDataService {

    private ShaftToleranceMongoRepository shaftRepository;
    private HoleToleranceMongoRepository holeRepository;
    private ToleranceDataMongoRepository toleranceRepository;

    public ToleranceDataService(ShaftToleranceMongoRepository shaftRepository,
                                HoleToleranceMongoRepository holeRepository,
                                ToleranceDataMongoRepository toleranceRepository) {
        this.shaftRepository = shaftRepository;
        this.holeRepository = holeRepository;
        this.toleranceRepository = toleranceRepository;
    }

    Map<String, Integer> getShaftData(Double dimension, String kvalitetValue) {
        ShaftToleranceData data = shaftRepository.
                findShaftToleranceDataByFromDimensionLessThanAndToDimensionGreaterThanEqual(dimension, dimension);
        return getShaftToleranceDataByValue(data, kvalitetValue);
    }

    Map<String, Integer> getHoleData(Double dimension, String kvalitetValue) {
        HoleToleranceData data = holeRepository
                .findHoleToleranceDataByFromDimensionLessThanAndToDimensionGreaterThanEqual(dimension, dimension);
        return getHoleToleranceDataByValue(data, kvalitetValue);
    }

    Integer getDefaultTolerance(Double dimension, String kvalitetValue) {
        Map<String, Integer> tolerances = toleranceRepository.findToleranceDataByFromDimensionLessThanAndToDimensionGreaterThanEqual
                (dimension, dimension)
                .getTolerances();
        return tolerances.entrySet().stream()
                .filter(key -> key.getKey().equals(kvalitetValue))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);
    }

    private Map<String, Integer> getShaftToleranceDataByValue(ShaftToleranceData data, String kvalitetValue) {
        switch (kvalitetValue) {
            case "5":
                return data.getToleranceValue5();
            case "6":
                return data.getToleranceValue6();
            case "7":
                return data.getToleranceValue7();
            case "8":
                return data.getToleranceValue8();
            default:
                return data.getToleranceValueOther();
        }
    }

    private Map<String, Integer> getHoleToleranceDataByValue(HoleToleranceData data, String kvalitetValue) {
        switch (kvalitetValue) {
            case "5":
                return data.getToleranceValue5();
            case "6":
                return data.getToleranceValue6();
            case "7":
                return data.getToleranceValue7();
            case "8":
                return data.getToleranceValue8();
            default:
                return data.getToleranceValueOther();
        }
    }
}
