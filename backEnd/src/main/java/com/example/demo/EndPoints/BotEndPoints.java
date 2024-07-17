package com.example.demo.EndPoints;


import com.example.demo.DataStructure.CvModel;
import com.example.demo.DataStructure.Entities.BotAccessEntity;
import com.example.demo.DataStructure.Entities.CvEntity;
import com.example.demo.DataStructure.Entities.MatchEntity;
import com.example.demo.DataStructure.Entities.SubClasses.BotCvEntity;
import com.example.demo.DataStructure.Entities.SubClasses.BotVacancyEntity;
import com.example.demo.DataStructure.Entities.VacancyEntity;
import com.example.demo.DataStructure.VacancyModel;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bot")
@AllArgsConstructor
public class BotEndPoints {
    CvEntity cvEntity;
    VacancyEntity vacancyEntity;
    MatchEntity matchEntity;
    EntityManager entityManager;
    BotAccessEntity botAccessEntity;

    @PostMapping("/findCv")
    public boolean findCv(@RequestBody String message) {
        return cvEntity.getAllByMessage(message).isEmpty();
    }

    @PostMapping("/findVacancy")
    public boolean findVacancy(@RequestBody String message) {
        return vacancyEntity.getAllByMessage(message).isEmpty();
    }

    @PostMapping("/checkChat")
    public boolean checkChat(@RequestBody String chatId) {
        return botAccessEntity.isChatRegistered(chatId);
    }

    @PostMapping("/addChat")
    public ResponseEntity addChat(@RequestBody String chatId) {
        botAccessEntity.createAccessForChat(chatId);
        return ResponseEntity.status(200).build();
    }

    @PostMapping("/removeChat")
    public ResponseEntity removeChat(@RequestBody String chatId) {
        botAccessEntity.removeAccessForChat(chatId);
        return ResponseEntity.status(200).build();
    }

    @Transactional
    @PostMapping("/addVacancy")
    public ResponseEntity addVacancy(@RequestBody BotVacancyEntity botVacancyEntity) {
        if (!botAccessEntity.isChatRegistered(botVacancyEntity.getChatId())) {
            return ResponseEntity.status(403).build();
        }
        VacancyModel vacancyModel = VacancyEntity.createVacancy(
                botVacancyEntity.getTitle(),
                botVacancyEntity.getExperience(),
                botVacancyEntity.getSalary(),
                botVacancyEntity.getChat_link(),
                botVacancyEntity.getMessage_link(),
                botVacancyEntity.getFromUser_link(),
                botVacancyEntity.getMessage()
        );
        vacancyModel = entityManager.merge(vacancyModel);
        vacancyEntity.save(vacancyModel);
        matchEntity.createConnectionToVacancy(vacancyModel);
        return ResponseEntity.status(200).build();
    }

    @Transactional
    @PostMapping("/addCv")
    public ResponseEntity addCv(@RequestBody BotCvEntity botCvEntity) {
        if (!botAccessEntity.isChatRegistered(botCvEntity.getChatId())) {
            return ResponseEntity.status(403).build();
        }
        CvModel cvModel = CvEntity.createCv(
                botCvEntity.getTitle(),
                botCvEntity.getExperience(),
                botCvEntity.getChat_link(),
                botCvEntity.getMessage_link(),
                botCvEntity.getFromUser_link(),
                botCvEntity.getMessage()
        );
        cvModel = entityManager.merge(cvModel);
        cvEntity.save(cvModel);
        matchEntity.createConnectionToCv(cvModel);
        return ResponseEntity.status(200).build();
    }
}
