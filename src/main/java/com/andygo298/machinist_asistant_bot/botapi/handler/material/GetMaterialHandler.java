package com.andygo298.machinist_asistant_bot.botapi.handler.material;

import com.andygo298.machinist_asistant_bot.botapi.BotState;
import com.andygo298.machinist_asistant_bot.botapi.InputMessageHandler;
import com.andygo298.machinist_asistant_bot.cache.UserDataCache;
import com.andygo298.machinist_asistant_bot.model.MaterialData;
import com.andygo298.machinist_asistant_bot.service.MaterialDataService;
import com.andygo298.machinist_asistant_bot.service.MaterialPropertiesService;
import com.andygo298.machinist_asistant_bot.service.ReplyMessageService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Objects;

@Component
public class GetMaterialHandler implements InputMessageHandler {

    private ReplyMessageService messageService;
    private MaterialDataService materialDataService;
    private UserDataCache userDataCache;

    public GetMaterialHandler(ReplyMessageService messageService, MaterialDataService materialDataService, UserDataCache userDataCache) {
        this.messageService = messageService;
        this.materialDataService = materialDataService;
        this.userDataCache = userDataCache;
    }

    @Override
    public SendMessage handle(Message message) {
        if (message.getText().equals("Свойства материалов")){
            userDataCache.setUsersCurrentBotState(message.getFrom().getId(), BotState.MATERIAL_PROPERTIES_MENU);
            return new SendMessage(message.getChatId(), messageService.getReplyText("reply.materialInfo"));
        }
        userDataCache.setUsersCurrentBotState(message.getFrom().getId(),BotState.GET_MATERIAL_INFO);
        String materialName = message.getText().trim().toLowerCase().replaceAll(" ","");
        MaterialData materialData = materialDataService.getMaterialData(materialName);

        if (Objects.nonNull(materialData)){
            return new SendMessage(message.getChatId(), materialData.getMetPortalUrl());
        } else return new SendMessage
                (message.getChatId(), messageService.getReplyText("reply.missingFromTheDBmaterial"))
                .setParseMode(ParseMode.HTML);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.GET_MATERIAL_INFO;
    }
}
