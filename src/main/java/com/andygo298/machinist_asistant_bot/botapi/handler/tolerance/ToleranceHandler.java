package com.andygo298.machinist_asistant_bot.botapi.handler.tolerance;

import com.andygo298.machinist_asistant_bot.botapi.BotState;
import com.andygo298.machinist_asistant_bot.botapi.InputMessageHandler;
import com.andygo298.machinist_asistant_bot.cache.UserDataCache;
import com.andygo298.machinist_asistant_bot.service.ReplyMessageService;
import com.andygo298.machinist_asistant_bot.service.ToleranceMenuService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class ToleranceHandler implements InputMessageHandler {

    private ReplyMessageService messageService;
    private UserDataCache userDataCache;
    private ToleranceMenuService toleranceMenuService;

    public ToleranceHandler(ReplyMessageService messageService, UserDataCache userDataCache, ToleranceMenuService toleranceMenuService) {
        this.messageService = messageService;
        this.userDataCache = userDataCache;
        this.toleranceMenuService = toleranceMenuService;
    }

    @Override
    public SendMessage handle(Message message) {
        userDataCache.setUsersCurrentBotState(message.getFrom().getId(), BotState.CALC_TOLERANCE);
        long chatId = message.getChatId();
        return toleranceMenuService.getToleranceMenuMessage(chatId, messageService.getReplyText("reply.tolerance"));
    }

    @Override
    public BotState getHandlerName() {
        return BotState.REPLY_TOLERANCE;
    }
}
