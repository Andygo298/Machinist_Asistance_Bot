package com.andygo298.machinist_asistant_bot.cache;

import com.andygo298.machinist_asistant_bot.botapi.BotState;
import com.andygo298.machinist_asistant_bot.model.UserProfileData;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.*;


@Component
public class UserDataCache implements DataCache {

    private Map<Integer, BotState> usersBotStates = new HashMap<>();
    private Map<Integer, UserProfileData> usersProfileData = new HashMap<>();

    @Override
    public void setUsersCurrentBotState(int userId, BotState botState) {
        usersBotStates.put(userId, botState);
    }

    @Override
    public BotState getUsersCurrentBotState(int userId) {
        BotState botState = usersBotStates.get(userId);
        if (isNull(botState)) {
            botState = BotState.START_ECHO;
        }
        return botState;
    }

    @Override
    public UserProfileData getUserProfileData(int userId) {
        UserProfileData userProfileData = usersProfileData.get(userId);
        if (isNull(userProfileData)) {
            userProfileData = new UserProfileData();
        }
        return userProfileData;
    }

    @Override
    public void saveUserProfileData(int userId, UserProfileData userProfileData) {
        usersProfileData.put(userId, userProfileData);
    }
}
