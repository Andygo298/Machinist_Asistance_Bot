package com.andygo298.machinist_asistant_bot.botapi.handler.calc;

import com.andygo298.machinist_asistant_bot.botapi.BotState;
import com.andygo298.machinist_asistant_bot.botapi.InputMessageHandler;
import com.andygo298.machinist_asistant_bot.cache.UserDataCache;
import com.andygo298.machinist_asistant_bot.service.CalcCutConditionsService;
import com.andygo298.machinist_asistant_bot.service.ReplyMessageService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class CalcCutConditionsHandler implements InputMessageHandler {

    private ReplyMessageService messageService;
    private CalcCutConditionsService calcCutConditionsService;
    private UserDataCache userDataCache;

    public CalcCutConditionsHandler(ReplyMessageService messageService,
                                    UserDataCache userDataCache,
                                    CalcCutConditionsService calcCutConditionsService) {
        this.messageService = messageService;
        this.userDataCache = userDataCache;
        this.calcCutConditionsService = calcCutConditionsService;
    }

    @Override
    public SendMessage handle(Message message) {
        if (message.getText().equals("Расчёт режимов резания")){
            userDataCache.setUsersCurrentBotState(message.getFrom().getId(),BotState.CALC_CUT_CONDITIONS);
            return new SendMessage(message.getChatId(), messageService.getReplyText("reply.cutConditions"));
        }
        userDataCache.setUsersCurrentBotState(message.getFrom().getId(),BotState.CALC_CUT_CONDITIONS);
        return calcCutConditionsService.getMainMenuMessage(message.getChatId(), message.getText());
    }

    @Override
    public BotState getHandlerName() {
        return BotState.CALC_CUT_CONDITIONS;
    }
}
