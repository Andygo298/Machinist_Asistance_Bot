package com.andygo298.machinist_asistant_bot.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Map;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "holeToleranceData")
public class HoleToleranceData implements Serializable {

    @Id
    String id;
    Double fromDimension;
    Double toDimension;
    Map<String,Integer> toleranceValueOther;
    Map<String,Integer> toleranceValue5;
    Map<String,Integer> toleranceValue6;
    Map<String,Integer> toleranceValue7;
    Map<String,Integer> toleranceValue8;

}
