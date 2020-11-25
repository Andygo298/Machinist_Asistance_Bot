package com.andygo298.machinist_asistant_bot.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "deviationData")
public class DeviationData {

    @Id
    String id;
    Double fromDimension;
    Double toDimension;
    Map<String,Integer> toleranceValue;
}
