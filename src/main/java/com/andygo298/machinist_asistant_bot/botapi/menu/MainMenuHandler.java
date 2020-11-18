package com.andygo298.machinist_asistant_bot.botapi.menu;

import com.andygo298.machinist_asistant_bot.botapi.BotState;
import com.andygo298.machinist_asistant_bot.botapi.InputMessageHandler;
import com.andygo298.machinist_asistant_bot.cache.UserDataCache;
import com.andygo298.machinist_asistant_bot.service.MainMenuService;
import com.andygo298.machinist_asistant_bot.service.ReplyMessageService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class MainMenuHandler implements InputMessageHandler {

    private ReplyMessageService messageService;
    private MainMenuService mainMenuService;
    private UserDataCache userDataCache;

    public MainMenuHandler(ReplyMessageService messageService, MainMenuService mainMenuService, UserDataCache userDataCache) {
        this.messageService = messageService;
        this.mainMenuService = mainMenuService;
        this.userDataCache = userDataCache;
    }

    @Override
    public SendMessage handle(Message message) {
        userDataCache.setUsersCurrentBotState(message.getFrom().getId(),BotState.SHOW_FORMULA_MENU);
        return mainMenuService.getMainMenuMessage(message.getChatId(), messageService.getReplyText("reply.showMainMenu"));
    }

    @Override
    public BotState getHandlerName() {
        return BotState.SHOW_MAIN_MENU;
    }
}
