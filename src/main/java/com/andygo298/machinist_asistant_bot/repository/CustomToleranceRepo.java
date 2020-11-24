package com.andygo298.machinist_asistant_bot.repository;

import com.andygo298.machinist_asistant_bot.model.ToleranceData;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomToleranceRepo {
    ToleranceData findTolerance( Double dim, String letter, Integer valueTol);
}
