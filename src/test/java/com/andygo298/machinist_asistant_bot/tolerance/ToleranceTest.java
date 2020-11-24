package com.andygo298.machinist_asistant_bot.tolerance;

import com.andygo298.machinist_asistant_bot.model.ToleranceData;
import com.andygo298.machinist_asistant_bot.repository.ToleranceDataMongoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ToleranceTest {

    @Autowired
    private ToleranceDataMongoRepository repository;

    @Test
    public void getToleranceData(){
        final String toleranceLetter = "h";
        final Integer toleranceValue = 14;
        final Double dim = 1.50;

        ToleranceData fromDb = repository.findTolerance(
                dim,toleranceLetter,toleranceValue
        );
        System.out.println(fromDb.toString());
    }
}
