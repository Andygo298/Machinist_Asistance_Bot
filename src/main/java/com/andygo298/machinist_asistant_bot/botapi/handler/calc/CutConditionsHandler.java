package com.andygo298.machinist_asistant_bot.botapi.handler.calc;

import com.andygo298.machinist_asistant_bot.botapi.BotState;
import com.andygo298.machinist_asistant_bot.botapi.InputMessageHandler;
import com.andygo298.machinist_asistant_bot.cache.UserDataCache;
import com.andygo298.machinist_asistant_bot.service.CalcCutConditionsService;
import com.andygo298.machinist_asistant_bot.service.FormulaMenuService;
import com.andygo298.machinist_asistant_bot.service.ReplyMessageService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class CutConditionsHandler implements InputMessageHandler {

    private ReplyMessageService messageService;
    private UserDataCache userDataCache;

    public CutConditionsHandler(ReplyMessageService messageService,
                                UserDataCache userDataCache) {
        this.messageService = messageService;
        this.userDataCache = userDataCache;
    }

    @Override
    public SendMessage handle(Message message) {
        userDataCache.setUsersCurrentBotState(message.getFrom().getId(),BotState.CALC_CUT_CONDITIONS);
        long chatId = message.getChatId();

        return messageService.getReplyMessage(chatId, "reply.cutConditions");
    }

    @Override
    public BotState getHandlerName() {
        return BotState.REPLY_CUT_CONDITIONS;
    }
}
