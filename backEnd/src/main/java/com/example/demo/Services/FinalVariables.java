package com.example.demo.Services;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FinalVariables {
    @Getter
    @Value("${myapp.bot.token}")
    private String botToken;
}
