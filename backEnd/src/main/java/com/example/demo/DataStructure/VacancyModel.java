package com.example.demo.DataStructure;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "vacancies")
public class VacancyModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String experience;
    private String salary;


    private String chat_link;
    private String message_link;
    private String fromUser_link;

    private String message;
}
