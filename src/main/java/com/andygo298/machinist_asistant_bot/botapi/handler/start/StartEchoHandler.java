package com.andygo298.machinist_asistant_bot.botapi.handler.start;

import com.andygo298.machinist_asistant_bot.botapi.BotState;
import com.andygo298.machinist_asistant_bot.botapi.InputMessageHandler;
import com.andygo298.machinist_asistant_bot.service.ReplyMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class StartEchoHandler implements InputMessageHandler {

    private ReplyMessageService messageService;

    public StartEchoHandler(ReplyMessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public SendMessage handle(Message message) {
        return processUsersInput(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.START_ECHO;
    }

    private SendMessage processUsersInput(Message inputMsg) {
        long chatId = inputMsg.getChatId();

        SendMessage replyToUser = messageService.getReplyMessage(chatId, "reply.start_echo");
        replyToUser.setReplyMarkup(getInlineMessageButtons());

        return replyToUser;
    }
    private InlineKeyboardMarkup getInlineMessageButtons() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonInputName = new InlineKeyboardButton().setText("Ввести имя");
        InlineKeyboardButton buttonInfo = new InlineKeyboardButton().setText("ИНФО");

        //Every button must have callBackData, or else not work !
        buttonInputName.setCallbackData("buttonInputName");
        buttonInfo.setCallbackData("buttonInfo");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonInputName);

        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(buttonInfo);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }
}
