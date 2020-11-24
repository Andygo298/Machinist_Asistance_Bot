package com.andygo298.machinist_asistant_bot.botapi.handler.tolerance;

import com.andygo298.machinist_asistant_bot.botapi.BotState;
import com.andygo298.machinist_asistant_bot.botapi.InputMessageHandler;
import com.andygo298.machinist_asistant_bot.cache.UserDataCache;
import com.andygo298.machinist_asistant_bot.service.CalcToleranceService;
import com.andygo298.machinist_asistant_bot.service.MaterialDataService;
import com.andygo298.machinist_asistant_bot.service.ReplyMessageService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class CalcToleranceHandler implements InputMessageHandler {

    private ReplyMessageService messageService;
    private UserDataCache userDataCache;
    private CalcToleranceService calcToleranceService;

    public CalcToleranceHandler(ReplyMessageService messageService, UserDataCache userDataCache, CalcToleranceService calcToleranceService) {
        this.messageService = messageService;
        this.userDataCache = userDataCache;
        this.calcToleranceService = calcToleranceService;
    }

    @Override
    public SendMessage handle(Message message) {
        if (message.getText().equals("Главная")){
            userDataCache.setUsersCurrentBotState(message.getFrom().getId(),BotState.SHOW_MAIN_MENU);
            return new SendMessage(message.getChatId(), messageService.getReplyText("reply.showMainMenu"));
        }
        userDataCache.setUsersCurrentBotState(message.getFrom().getId(),BotState.CALC_TOLERANCE);
        return calcToleranceService.getCalcToleranceMessage(message.getChatId(), message.getText());
    }

    @Override
    public BotState getHandlerName() {
        return BotState.CALC_TOLERANCE;
    }
}
