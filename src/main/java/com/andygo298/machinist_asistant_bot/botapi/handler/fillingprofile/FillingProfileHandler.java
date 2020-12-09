package com.andygo298.machinist_asistant_bot.botapi.handler.fillingprofile;

import com.andygo298.machinist_asistant_bot.botapi.BotState;
import com.andygo298.machinist_asistant_bot.botapi.InputMessageHandler;
import com.andygo298.machinist_asistant_bot.cache.UserDataCache;
import com.andygo298.machinist_asistant_bot.model.UserProfileData;
import com.andygo298.machinist_asistant_bot.service.ReplyMessageService;
import com.andygo298.machinist_asistant_bot.service.UsersProfileDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Slf4j
@Component
public class FillingProfileHandler implements InputMessageHandler {

    private UserDataCache userDataCache;
    private ReplyMessageService messageService;
    private UsersProfileDataService profileDataService;

    public FillingProfileHandler(UserDataCache userDataCache, ReplyMessageService messageService, UsersProfileDataService profileDataService) {
        this.userDataCache = userDataCache;
        this.messageService = messageService;
        this.profileDataService = profileDataService;
    }

    @Override
    public SendMessage handle(Message message) {
        if (userDataCache.getUsersCurrentBotState(message.getFrom().getId()).equals(BotState.FILLING_PROFILE)) {
            userDataCache.setUsersCurrentBotState(message.getFrom().getId(), BotState.ASK_NAME);
        }
        return processUsersInput(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.FILLING_PROFILE;
    }

    private SendMessage processUsersInput(Message inputMsg) {
        String usersAnswer = inputMsg.getText();
        int userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();

        UserProfileData profileData = userDataCache.getUserProfileData(userId);
        BotState botState = userDataCache.getUsersCurrentBotState(userId);

        SendMessage replyToUser = null;

        if (botState.equals(BotState.ASK_NAME)) {
            replyToUser = messageService.getReplyMessage(chatId, "reply.done");
            userDataCache.setUsersCurrentBotState(userId, BotState.PROFILE_FILLED);
        }

        if (botState.equals(BotState.PROFILE_FILLED)) {

            profileData.setNickName(usersAnswer);
            profileData.setChatId(chatId);
            profileDataService.saveUserProfileData(profileData);

            userDataCache.setUsersCurrentBotState(userId, BotState.SHOW_MAIN_MENU);

            String profileFilledMessage = messageService.getReplyText("reply.profileFilled") + profileData.getNickName() + " ";

            replyToUser = new SendMessage(chatId, profileFilledMessage );
        }

        userDataCache.saveUserProfileData(userId, profileData);

        return replyToUser;
    }
}
