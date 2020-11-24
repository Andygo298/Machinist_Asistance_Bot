package com.andygo298.machinist_asistant_bot.repository;

import com.andygo298.machinist_asistant_bot.model.ToleranceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class CustomToleranceRepoImpl implements CustomToleranceRepo {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public ToleranceData findTolerance(Double dim, String letter, Integer valueTol) {

        Query query = new Query();
        query.addCriteria(Criteria.
                where("fromDimension").lte(dim).
                and("toDimension").gt(dim).
                and("toleranceLetter").is(letter).
                and("toleranceValue").is(valueTol));
        return mongoTemplate.findOne(query, ToleranceData.class);
    }
}
