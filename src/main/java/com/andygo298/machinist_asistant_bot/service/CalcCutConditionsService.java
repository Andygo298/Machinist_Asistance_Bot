package com.andygo298.machinist_asistant_bot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.Arrays;
import java.util.List;

@Service
public class CalcCutConditionsService {

    private ReplyMessageService messageService;

    public CalcCutConditionsService(ReplyMessageService messageService) {
        this.messageService = messageService;
    }

    public SendMessage getMainMenuMessage(final long chatId, final String textMessage) {
        return createMessage(chatId, textMessage);
    }

    private SendMessage createMessage(long chatId, String textMessage) {

        List<String> conditions = Arrays.asList(textMessage.split(" "));

        try {
            int cutSpeed = Integer.parseInt(conditions.get(0));
            int diameterTool = Integer.parseInt(conditions.get(1));
            String result = String.valueOf(Math.round((1000 * cutSpeed) / (Math.PI * diameterTool)));
            return new SendMessage(chatId, messageService.getReplyText("reply.resultCutCondition") + "\n" + result + " об/мин.");
        } catch (NumberFormatException e){
            return new SendMessage(chatId, messageService.getReplyText("reply.wrongInputCutConditions")).setParseMode(ParseMode.HTML);
        }




    }
}
