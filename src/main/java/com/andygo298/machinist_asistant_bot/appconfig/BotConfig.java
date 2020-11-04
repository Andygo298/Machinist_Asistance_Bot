package com.andygo298.machinist_asistant_bot.appconfig;

import com.andygo298.machinist_asistant_bot.MachinistTelegramBot;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "telegrambot")
public class BotConfig {

    private String webHookPath;
    private String botUserName;
    private String botToken;

    @Bean
    public MachinistTelegramBot machinistTelegramBot() {
        MachinistTelegramBot machinistTelegramBot = new MachinistTelegramBot();
        machinistTelegramBot.setBotUserName(botUserName);
        machinistTelegramBot.setBotToken(botToken);
        machinistTelegramBot.setWebHookPath(webHookPath);
        return machinistTelegramBot;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
