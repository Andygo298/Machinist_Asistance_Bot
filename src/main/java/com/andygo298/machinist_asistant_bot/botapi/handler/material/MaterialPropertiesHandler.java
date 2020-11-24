package com.andygo298.machinist_asistant_bot.botapi.handler.material;

import com.andygo298.machinist_asistant_bot.botapi.BotState;
import com.andygo298.machinist_asistant_bot.botapi.InputMessageHandler;
import com.andygo298.machinist_asistant_bot.cache.UserDataCache;
import com.andygo298.machinist_asistant_bot.service.MainMenuService;
import com.andygo298.machinist_asistant_bot.service.MaterialPropertiesService;
import com.andygo298.machinist_asistant_bot.service.ReplyMessageService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class MaterialPropertiesHandler implements InputMessageHandler {

    private ReplyMessageService messageService;
    private MaterialPropertiesService materialService;
    private UserDataCache userDataCache;

    public MaterialPropertiesHandler(ReplyMessageService messageService, MaterialPropertiesService materialService, UserDataCache userDataCache) {
        this.messageService = messageService;
        this.materialService = materialService;
        this.userDataCache = userDataCache;
    }

    @Override
    public SendMessage handle(Message message) {
        userDataCache.setUsersCurrentBotState(message.getFrom().getId(), BotState.GET_MATERIAL_INFO);
        return materialService.getMaterialMenuMessage(message.getChatId(), messageService.getReplyText("reply.materialInfo"));
    }

    @Override
    public BotState getHandlerName() {
        return BotState.MATERIAL_PROPERTIES_MENU;
    }
}
