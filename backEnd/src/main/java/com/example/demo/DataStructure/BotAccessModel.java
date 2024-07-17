package com.example.demo.DataStructure;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "bot_access")
public class BotAccessModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String chatId;

}
