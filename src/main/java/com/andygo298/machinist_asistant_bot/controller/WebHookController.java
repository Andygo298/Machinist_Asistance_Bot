package com.andygo298.machinist_asistant_bot.controller;

import com.andygo298.machinist_asistant_bot.MachinistTelegramBot;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class WebHookController {
    private final MachinistTelegramBot machinistTelegramBot;

    public WebHookController(MachinistTelegramBot machinistTelegramBot) {
        this.machinistTelegramBot = machinistTelegramBot;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update){
        return machinistTelegramBot.onWebhookUpdateReceived(update);
    }
}
