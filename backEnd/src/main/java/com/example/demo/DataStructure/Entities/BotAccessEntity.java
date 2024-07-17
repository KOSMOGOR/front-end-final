package com.example.demo.DataStructure.Entities;

import com.example.demo.DataStructure.BotAccessModel;
import com.example.demo.DataStructure.Repositories.BotAccessModelRepository;
import com.example.demo.Services.Config;
import com.example.demo.Services.FinalVariables;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BotAccessEntity {

    private FinalVariables config;
    private BotAccessModelRepository botAccessModelRepository;

    public boolean isBotRegistered(String token) {
        return token.equals(config.getBotToken());
    }

    public BotAccessModel createAccessForChat(String chatId) {
        BotAccessModel botAccessModel = new BotAccessModel();
        botAccessModel.setChatId(chatId);

        return botAccessModelRepository.save(botAccessModel);
    }
    public void removeAccessForChat(String chatId) {
        Optional<BotAccessModel> botAccessModelOp = botAccessModelRepository.findByChatId(chatId);
        botAccessModelOp.ifPresent(botAccessModel -> botAccessModelRepository.delete(botAccessModel));
    }

    public boolean isChatRegistered(String chatId) {
        Optional<BotAccessModel> botAccessModelOp = botAccessModelRepository.findByChatId(chatId);
        return botAccessModelOp.isPresent();
    }

}
