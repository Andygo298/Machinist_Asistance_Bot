package com.andygo298.machinist_asistant_bot.tolerance;

import com.andygo298.machinist_asistant_bot.model.ShaftToleranceData;
import com.andygo298.machinist_asistant_bot.repository.ToleranceDataMongoRepository;
import com.andygo298.machinist_asistant_bot.repository.ShaftToleranceMongoRepository;
//import com.andygo298.machinist_asistant_bot.repository.ToleranceDataMongoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ToleranceTest {

//    @Autowired
//    private ToleranceDataMongoRepository repository;
    @Autowired
    private ToleranceDataMongoRepository deviationDataMongoRepository;
    @Autowired
    private ShaftToleranceMongoRepository shaftToleranceMongoRepository;

//    @Test
//    public void getToleranceDataTest(){
//        final String toleranceLetter = "h";
//        final Integer toleranceValue = 14;
//        final Double dim = 1.50;
//
//        ToleranceData fromDb = repository.findTolerance(
//                dim,toleranceLetter,toleranceValue
//        );
//        System.out.println(fromDb.toString());
//    }

    @Test
    public void getShaftData(){
        Integer dim = 5;
//        ShaftToleranceData shaftTolerance = shaftToleranceMongoRepository.findShaftToleranceDataByFromDimensionLessThanAndToDimensionGreaterThanEqual(dim, dim);

    }


//    @Test
//    public void saveToDeviationDataTest(){
//        final ShaftToleranceData shaftToleranceData = new ShaftToleranceData();
//        shaftToleranceData.setFromDimension(0.0);
//        shaftToleranceData.setToDimension(3.0);
//        Map<String,Integer> testmap = new HashMap<>();
//        testmap.put("a", -270);
//        testmap.put("b", -140);
//        testmap.put("c", -60);
//        shaftToleranceData.setToleranceValue(testmap);
//        deviationDataMongoRepository.save(deviationData);
//        List<ShaftToleranceData> all = deviationDataMongoRepository.findAll();
//    }
}
