package com.example.demo.Services;

import com.example.demo.DataStructure.BotAccessModel;
import com.example.demo.DataStructure.Entities.BotAccessEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class PreChecker implements HandlerInterceptor {
    private final BotAccessEntity botAccessEntity;

    @Autowired
    public PreChecker(BotAccessEntity botAccessEntity) {
        this.botAccessEntity = botAccessEntity;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        String urlPath = request.getRequestURI();

        if (urlPath.contains("/bot/")) {
            if (botAccessEntity.isBotRegistered(request.getHeader("Bot-Key"))) {
                return true;
            } else {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("Access Denied");
                return false;
            }
        }

        return true;
    }
}
