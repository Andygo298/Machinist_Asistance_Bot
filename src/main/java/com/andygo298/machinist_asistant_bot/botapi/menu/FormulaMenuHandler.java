package com.andygo298.machinist_asistant_bot.botapi.menu;

import com.andygo298.machinist_asistant_bot.botapi.BotState;
import com.andygo298.machinist_asistant_bot.botapi.InputMessageHandler;
import com.andygo298.machinist_asistant_bot.cache.UserDataCache;
import com.andygo298.machinist_asistant_bot.service.FormulaMenuService;
import com.andygo298.machinist_asistant_bot.service.ReplyMessageService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class FormulaMenuHandler implements InputMessageHandler {

    private ReplyMessageService messageService;
    private FormulaMenuService formulaMenuService;
    private UserDataCache userDataCache;

    public FormulaMenuHandler(ReplyMessageService messageService, FormulaMenuService formulaMenuService, UserDataCache userDataCache) {
        this.messageService = messageService;
        this.formulaMenuService = formulaMenuService;
        this.userDataCache = userDataCache;
    }

    @Override
    public SendMessage handle(Message message) {
        userDataCache.setUsersCurrentBotState(message.getFrom().getId(), BotState.REPLY_CUT_CONDITIONS);
        return formulaMenuService.getMainMenuMessage(message.getChatId(), messageService.getReplyText("reply.showFormulaMenu"));
    }

    @Override
    public BotState getHandlerName() {
        return BotState.SHOW_FORMULA_MENU;
    }
}
