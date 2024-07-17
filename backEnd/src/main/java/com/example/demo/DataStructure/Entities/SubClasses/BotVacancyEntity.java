package com.example.demo.DataStructure.Entities.SubClasses;

import lombok.Data;

@Data
public class BotVacancyEntity {

    private String title;
    private String experience;
    private String salary;


    private String chat_link;
    private String message_link;
    private String fromUser_link;

    private String message;

    private String chatId;
}
