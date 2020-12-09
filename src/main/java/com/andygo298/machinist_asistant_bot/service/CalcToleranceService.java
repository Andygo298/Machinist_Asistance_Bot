package com.andygo298.machinist_asistant_bot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;


import static java.util.Objects.nonNull;

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
        String patternNumbers = "[^0-9/.]+";
        String patternLetters = "[^a-zA-z]+";
        try {
            List<String> dimensionAndToleranceValue = Arrays.asList(textMessage.split(patternNumbers));
            Double dimension = Double.parseDouble(dimensionAndToleranceValue.get(0));

            Double lowerTolerance;
            Double upperTolerance;
            Double lowerDimension;
            Double upperDimension;
            Double middleDimension;
            String kvalitetValue = textMessage.replaceAll(patternLetters, "");
            Integer defaultTolerance = dataService.getDefaultTolerance(dimension, dimensionAndToleranceValue.get(1));
            Map<String, Integer> tolerancesData;
            if (Character.isLowerCase(kvalitetValue.charAt(0))) {
                tolerancesData = dataService.getShaftData(dimension, dimensionAndToleranceValue.get(1));
            } else tolerancesData = dataService.getHoleData(dimension, dimensionAndToleranceValue.get(1));

            if (nonNull(defaultTolerance) && nonNull(tolerancesData)) {
                try {
                    if (Character.isLowerCase(kvalitetValue.charAt(0))) {
                        switch (kvalitetValue) {
                            case "a":
                            case "b":
                            case "c":
                            case "cd":
                            case "d":
                            case "e":
                            case "ef":
                            case "f":
                            case "fg":
                            case "g":
                            case "h":
                                upperTolerance = roundScale3(tolerancesData.get(kvalitetValue) / 1000.0);
                                lowerTolerance = roundScale3(upperTolerance - defaultTolerance / 1000.0);
                                upperDimension = roundScale3(dimension + upperTolerance);
                                lowerDimension = roundScale3(dimension + lowerTolerance);
                                middleDimension = roundScale3(lowerDimension + defaultTolerance / 2000.0);
                                break;
                            case "j":
                            case "k":
                            case "m":
                            case "n":
                            case "p":
                            case "r":
                            case "s":
                            case "t":
                            case "u":
                            case "v":
                            case "x":
                            case "y":
                            case "z":
                            case "za":
                            case "zb":
                            case "zc":
                                lowerTolerance = roundScale3(tolerancesData.get(kvalitetValue) / 1000.0);
                                upperTolerance = roundScale3(lowerTolerance + defaultTolerance / 1000.0);
                                upperDimension = roundScale3(dimension + upperTolerance);
                                lowerDimension = roundScale3(dimension + lowerTolerance);
                                middleDimension = roundScale3(upperDimension - defaultTolerance / 2000.0);
                                break;
                            case "js":
                                lowerTolerance = roundScale3(defaultTolerance / 2000.0);
                                upperTolerance = roundScale3(defaultTolerance / 2000.0);
                                lowerDimension = roundScale3(dimension - lowerTolerance);
                                upperDimension = roundScale3(dimension + upperTolerance);
                                middleDimension = roundScale3(dimension);
                                break;
                            default:
                                return new SendMessage(chatId, "Такой допуск остутствует! или введен неправильно.");
                        }
                    } else {
                        switch (kvalitetValue) {
                            case "A":
                            case "B":
                            case "C":
                            case "CD":
                            case "D":
                            case "E":
                            case "EF":
                            case "F":
                            case "FG":
                            case "G":
                            case "H":
                                lowerTolerance = roundScale3(tolerancesData.get(kvalitetValue) / 1000.0);
                                upperTolerance = roundScale3(lowerTolerance + defaultTolerance / 1000.0);
                                upperDimension = roundScale3(dimension + upperTolerance);
                                lowerDimension = roundScale3(dimension + lowerTolerance);
                                middleDimension = roundScale3(upperDimension - defaultTolerance / 2000.0);
                                break;
                            case "J":
                            case "K":
                            case "M":
                            case "N":
                            case "P":
                            case "R":
                            case "S":
                            case "T":
                            case "U":
                            case "V":
                            case "X":
                            case "Y":
                            case "Z":
                            case "ZA":
                            case "ZB":
                            case "ZC":
                                upperTolerance = roundScale3(tolerancesData.get(kvalitetValue) / 1000.0);
                                lowerTolerance = roundScale3(upperTolerance - defaultTolerance / 1000.0);
                                upperDimension = roundScale3(dimension + upperTolerance);
                                lowerDimension = roundScale3(dimension + lowerTolerance);
                                middleDimension = roundScale3(lowerDimension + defaultTolerance / 2000.0);
                                break;
                            case "JS":
                            case "Js":
                                lowerTolerance = roundScale3(defaultTolerance / 2000.0);
                                upperTolerance = roundScale3(defaultTolerance / 2000.0);
                                lowerDimension = roundScale3(dimension - lowerTolerance);
                                upperDimension = roundScale3(dimension + upperTolerance);
                                middleDimension = roundScale3(dimension);
                                break;
                            default:
                                return new SendMessage(chatId, "Такой допуск остутствует! или введен неправильно.");
                        }
                    }
                } catch (NullPointerException e) {
                    return new SendMessage(chatId, "Такой допуск остутствует! или введен неправильно.");
                }
            } else return new SendMessage(chatId, "Вы неправильно ввели данные.");


            StringBuilder outText = new StringBuilder();
            outText.append("---------------------ОТКЛОНЕНИЯ-----------------\n")
                    .append(messageService.getReplyText("reply.upperTolerance")).append(upperTolerance).append(messageService.getReplyText("reply.millimeter")).append("\n")
                    .append(messageService.getReplyText("reply.lowerTolerance")).append(lowerTolerance).append(messageService.getReplyText("reply.millimeter")).append("\n")
                    .append("------------------------РАЗМЕРЫ--------------------\n")
                    .append(messageService.getReplyText("reply.nominalDimension")).append(dimensionAndToleranceValue.get(0)).append(messageService.getReplyText("reply.millimeter")).append("\n")
                    .append(messageService.getReplyText("reply.upperDimension")).append(upperDimension).append(messageService.getReplyText("reply.millimeter")).append("\n")
                    .append(messageService.getReplyText("reply.lowerDimension")).append(lowerDimension).append(messageService.getReplyText("reply.millimeter")).append("\n")
                    .append(messageService.getReplyText("reply.middleDimension")).append(middleDimension).append(messageService.getReplyText("reply.millimeter"));
            return new SendMessage(chatId, outText.toString()).setParseMode(ParseMode.HTML);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return new SendMessage(chatId, "Вы неправильно ввели данные.");
        }
    }
    private Double roundScale3(Double num){
        return BigDecimal.valueOf(num).setScale(3,RoundingMode.HALF_UP).doubleValue();
    }
}
