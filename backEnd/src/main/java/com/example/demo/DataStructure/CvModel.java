package com.example.demo.DataStructure;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cv")
public class CvModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String experience;


    private String chat_link;
    private String message_link;
    private String fromUser_link;

    private String message;
}
