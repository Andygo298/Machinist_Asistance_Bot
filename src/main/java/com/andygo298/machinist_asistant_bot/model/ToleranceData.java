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
@Document(collection = "toleranceData")
public class ToleranceData implements Serializable {

    @Id
    String id;
    Double fromDimension;
    Double toDimension;
    Map<String, Integer> tolerances;

}
