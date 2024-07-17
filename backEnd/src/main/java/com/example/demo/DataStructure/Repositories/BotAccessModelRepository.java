package com.example.demo.DataStructure.Repositories;

import com.example.demo.DataStructure.BotAccessModel;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BotAccessModelRepository extends JpaRepository<BotAccessModel, Long> {
    Optional<BotAccessModel> findByChatId(String chatId);
}
