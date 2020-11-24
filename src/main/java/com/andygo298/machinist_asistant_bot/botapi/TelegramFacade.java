package com.andygo298.machinist_asistant_bot.botapi;

import com.andygo298.machinist_asistant_bot.MachinistTelegramBot;
import com.andygo298.machinist_asistant_bot.cache.UserDataCache;
import com.andygo298.machinist_asistant_bot.service.MainMenuService;
import com.andygo298.machinist_asistant_bot.service.ReplyMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
public class TelegramFacade {

    private BotStateContext botStateContext;
    private UserDataCache userDataCache;
    private MachinistTelegramBot machinistTelegramBot;
    private ReplyMessageService messageService;
    private MainMenuService mainMenuService;

    public TelegramFacade(BotStateContext botStateContext, UserDataCache userDataCache, @Lazy MachinistTelegramBot machinistTelegramBot,
                          ReplyMessageService messageService, MainMenuService mainMenuService) {
        this.botStateContext = botStateContext;
        this.userDataCache = userDataCache;
        this.machinistTelegramBot = machinistTelegramBot;
        this.messageService = messageService;
        this.mainMenuService = mainMenuService;
    }

    public BotApiMethod<?> handleUpdate(Update update) {
        SendMessage replyMessage = null;

        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            log.info("New callbackQuery from User: {}, userId: {}, with data: {}", update.getCallbackQuery().getFrom().getUserName(),
                    callbackQuery.getFrom().getId(), update.getCallbackQuery().getData());
            return processCallbackQuery(callbackQuery);
        }


        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            log.info("New message from User:{}, userId: {}, chatId: {},  with text: {}",
                    message.getFrom().getUserName(), message.getFrom().getId(), message.getChatId(), message.getText());
            replyMessage = handleInputMessage(message);
        }

        return replyMessage;
    }

    private SendMessage handleInputMessage(Message message) {
        String inputMsg = message.getText();
        int userId = message.getFrom().getId();
        long chatId = message.getChatId();
        BotState botState;
        SendMessage replyMessage;

        switch (inputMsg) {
            case "/start":
                botState = BotState.START_ECHO;
                break;
            case "Ввести имя":
                botState = BotState.FILLING_PROFILE;
                break;
            case "ИНФО":
                botState = BotState.SHOW_INFO_MENU;
                break;
            case "Главная":
                botState = BotState.SHOW_MAIN_MENU;
                break;
            case "Свойства материалов":
                botState = BotState.MATERIAL_PROPERTIES_MENU;
                break;
            case "Допуски":
                botState = BotState.REPLY_TOLERANCE;
                break;
            default:
                botState = userDataCache.getUsersCurrentBotState(userId);
                break;
        }

        userDataCache.setUsersCurrentBotState(userId, botState);

        replyMessage = botStateContext.processInputMessage(botState, message);

        return replyMessage;
    }

    private BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery) {
        final long chatId = buttonQuery.getMessage().getChatId();
        final int userId = buttonQuery.getFrom().getId();
        BotApiMethod<?> callBackAnswer = mainMenuService.getMainMenuMessage(chatId, "Воспользуйтесь главным меню");


        //From start choose buttons
        if (buttonQuery.getData().equals("buttonInputName")) {
            callBackAnswer = new SendMessage(chatId, "Как мне к тебе обращаться?");
            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_NAME);
        } else if (buttonQuery.getData().equals("buttonInfo")) {
            callBackAnswer = new SendMessage(chatId, "куча инфы будя тута");
            userDataCache.setUsersCurrentBotState(userId, BotState.START_ECHO);
        }
        return callBackAnswer;


    }

}
