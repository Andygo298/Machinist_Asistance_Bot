package com.andygo298.machinist_asistant_bot.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.io.Serializable;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "userProfileData")
public class UserProfileData implements Serializable {

    @Id
    String id;
    String nickName;
    long chatId;
}
