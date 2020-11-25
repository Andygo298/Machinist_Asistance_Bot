package com.andygo298.machinist_asistant_bot.tolerance;

import com.andygo298.machinist_asistant_bot.model.DeviationData;
import com.andygo298.machinist_asistant_bot.model.ToleranceData;
import com.andygo298.machinist_asistant_bot.repository.DeviationDataMongoRepository;
import com.andygo298.machinist_asistant_bot.repository.ToleranceDataMongoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class ToleranceTest {

    @Autowired
    private ToleranceDataMongoRepository repository;
    @Autowired
    private DeviationDataMongoRepository deviationDataMongoRepository;

    @Test
    public void getToleranceDataTest(){
        final String toleranceLetter = "h";
        final Integer toleranceValue = 14;
        final Double dim = 1.50;

        ToleranceData fromDb = repository.findTolerance(
                dim,toleranceLetter,toleranceValue
        );
        System.out.println(fromDb.toString());
    }

    @Test
    public void saveToDeviationDataTest(){
        final DeviationData deviationData = new DeviationData();
        deviationData.setFromDimension(0.0);
        deviationData.setToDimension(3.0);
        Map<String,Integer> testmap = new HashMap<>();
        testmap.put("a", -270);
        testmap.put("b", -140);
        testmap.put("c", -60);
        deviationData.setToleranceValue(testmap);
//        deviationDataMongoRepository.save(deviationData);
        List<DeviationData> all = deviationDataMongoRepository.findAll();
    }
}
