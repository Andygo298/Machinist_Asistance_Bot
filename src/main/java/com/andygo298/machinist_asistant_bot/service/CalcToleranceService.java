package com.andygo298.machinist_asistant_bot.service;

import com.andygo298.machinist_asistant_bot.model.ToleranceData;
import com.andygo298.machinist_asistant_bot.repository.ToleranceDataMongoRepository;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class CalcToleranceService {

    private ReplyMessageService messageService;
    private ToleranceDataService dataService;

    public CalcToleranceService(ReplyMessageService messageService, ToleranceDataService dataService) {
        this.messageService = messageService;
        this.dataService = dataService;
    }

    public SendMessage getCalcToleranceMessage(final long chatId, final String textMessage) {
        return createMessage(chatId, textMessage);
    }

    private SendMessage createMessage(long chatId, String textMessage) {
        String pattern1 = "[a-zA-z]";
        String pattern2 = "[^a-zA-z]+";
        double upperTolerance;
        double lowerTolerance;
        double middleDimension;
        List<String> dimensionAndToleranceValue = Arrays.asList(textMessage.split(pattern1));
        String letterTolerance = textMessage.replaceAll(pattern2, "");

        try {
            ToleranceData toleranceData = dataService.findTolerance(
                    Double.parseDouble(dimensionAndToleranceValue.get(0)), letterTolerance.toLowerCase(), Integer.parseInt(dimensionAndToleranceValue.get(1))
            );
            if (Objects.isNull(toleranceData)){
                return new SendMessage(chatId, "Данные о допуске отстутвуют в базе. Данная страница еще в разработке.");
            }

        if (Character.isLowerCase(letterTolerance.charAt(0))) {
            upperTolerance = toleranceData.getUpperTolerance() / 1000;
            lowerTolerance = toleranceData.getLowerTolerance() / 1000;
            middleDimension = Double.parseDouble(dimensionAndToleranceValue.get(0)) - lowerTolerance / 2;
        } else {
            upperTolerance = toleranceData.getLowerTolerance() / 1000;
            lowerTolerance = toleranceData.getUpperTolerance() / 1000;
            middleDimension = Double.parseDouble(dimensionAndToleranceValue.get(0)) + upperTolerance / 2;
        }

        StringBuilder outText = new StringBuilder();
        outText.append(messageService.getReplyText("reply.upperTolerance"))
                .append("\n+ ").append(upperTolerance).append(messageService.getReplyText("reply.millimeter")).append("\n")
                .append(messageService.getReplyText("reply.lowerTolerance"))
                .append("\n- ").append(lowerTolerance).append(messageService.getReplyText("reply.millimeter")).append("\n")
                .append(messageService.getReplyText("reply.middleDimension"))
                .append("\n ").append(middleDimension).append(messageService.getReplyText("reply.millimeter"));
            return new SendMessage(chatId, outText.toString()).setParseMode(ParseMode.HTML);
        } catch (NumberFormatException | IndexOutOfBoundsException e){
            return new SendMessage(chatId, "Вы неправильно ввели данные. бла бла");
        }

    }
}
