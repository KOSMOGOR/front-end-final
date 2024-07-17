package com.example.demo.EndPoints;

import com.example.demo.DataStructure.*;
import com.example.demo.DataStructure.Entities.*;
import com.example.demo.DataStructure.Entities.SubClasses.CvTitleEntity;
import com.example.demo.DataStructure.Entities.SubClasses.VacancyTitleEntity;
import com.example.demo.DataStructure.Entities.SubClasses.matchStateEntity;
import com.example.demo.DataStructure.Repositories.CvMatchModelRepository;
import com.example.demo.Exceptions.NotFoundException;
import com.example.demo.Services.SecurityConfig;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RestController
@RequestMapping("/home")
@AllArgsConstructor
public class EndPoints {

    CvEntity cvEntity;
    VacancyEntity vacancyEntity;
    MatchEntity matchEntity;
    EntityManager entityManager;
    UserEntity userEntity;
    @Autowired
    private PasswordEncoder passwordEncoder;



    @GetMapping("/")
    public ModelAndView indexPage() {
        return new ModelAndView("index");
    }

    @GetMapping("/cvs")
    public ModelAndView cvTitlesPage(@RequestParam(required = false) String title) {
        ModelAndView model = new ModelAndView("specialists");
        Set<CvTitleEntity> cvTitles;
        if (title == null || title.isEmpty()) {
            cvTitles = new HashSet<>(matchEntity.getAllCvNotInMatchTitles());
        } else {
            cvTitles = new HashSet<>(matchEntity.getAllCvByTitleNotInMatchTitles(title));
        }
        model.addObject("title", title == null ? "" : title);
        model.addObject("cvs", cvTitles);
        return model;
    }
    @GetMapping("/cvs/{title}")
    public ModelAndView cvPage(@PathVariable String title) {
        title = title.replaceAll("_", " ");
        ModelAndView model = new ModelAndView("specialistsPpl");
        List<CvModel> cvModels = matchEntity.getAllCvByTitleNotInMatch(title);
        model.addObject("cvs", cvModels);
        model.addObject("title", title);
        return model;
    }

    @GetMapping("/cvs/{title}/{id}")
    public ModelAndView cvFocusPage(@PathVariable String id, HttpSession session) throws NotFoundException {
        ModelAndView model = new ModelAndView("specialistsFocus");
        CvModel cvModel = cvEntity.getCvById(Long.parseLong(id));
        String userEmail = userEntity.getUserEmail(session);

        List<VacancyModel> vacancyModels = matchEntity.getAllVacancyMatchedToCv(cvModel);

        model.addObject("cv", cvModel);
        model.addObject("vacancies",vacancyModels);
        model.addObject("email",userEmail);
        return model;
    }

    @GetMapping("/vacancies")
    public ModelAndView vacancyTitlesPage(@RequestParam(required = false) String title) {
        ModelAndView model = new ModelAndView("vacancys");
        Set<VacancyTitleEntity> vacancyTitles;
        if (title == null || title.isEmpty()) {
            vacancyTitles = new HashSet<>(matchEntity.getAllVacancyNotInMatchTitles());
        } else {
            vacancyTitles = new HashSet<>(matchEntity.getAllVacancyByTitleNotInMatchTitles(title));
        }
        model.addObject("vacancies",vacancyTitles);
        model.addObject("title", title == null ? "" : title);
        return model;
    }
    @GetMapping("/vacancies/{title}")
    public ModelAndView vacanciesPage(@PathVariable String title) {
        title = title.replaceAll("_", " ");
        ModelAndView model = new ModelAndView("vacancysDesk");

        List<VacancyModel> vacancyModels = matchEntity.getAllVacancyByTitleNotInMatch(title);

        model.addObject("vacancies", vacancyModels);
        model.addObject("title", title);
        return model;
    }
    @GetMapping("/vacancies/{title}/{id}")
    public ModelAndView vacancyPage(@PathVariable String id, HttpSession session) throws NotFoundException {
        ModelAndView model = new ModelAndView("vacancyFocus");
        VacancyModel vacancyModel = vacancyEntity.getVacancyById(Long.parseLong(id));
        String userEmail = userEntity.getUserEmail(session);

        List<CvModel> cvModels = matchEntity.getAllCvMatchedToVacancy(vacancyModel);


        model.addObject("vacancy",vacancyModel);
        model.addObject("cvs",cvModels);
        model.addObject("userEmail",userEmail);
        return model;
    }

    @GetMapping("/history")
    public ModelAndView historyPage(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("history");
        modelAndView.addObject("activeMatches", matchEntity.getAllVacanciesMatchedToCvByUserActive(userEntity.getUserByEmail(userEntity.getUserEmail(session))));
        modelAndView.addObject("endedMatches", matchEntity.getAllVacanciesMatchedToCvByUserEnded(userEntity.getUserByEmail(userEntity.getUserEmail(session))));
        return modelAndView;
    }

    @PostMapping("/startMatch")
    public ModelAndView startMatch(@RequestBody Map<String, Long> requestBody, HttpSession session) throws NotFoundException {
        long cvId = requestBody.get("cvId");
        long vacancyId = requestBody.get("vacancyId");
        CvModel cvModel = cvEntity.getCvById(cvId);
        VacancyModel vacancyModel = vacancyEntity.getVacancyById(vacancyId);
        UserModel userModel = userEntity.getUserByEmail(userEntity.getUserEmail(session));
        matchEntity.match(cvModel, vacancyModel, userModel);
        ModelAndView modelAndView = new ModelAndView("history");
        modelAndView.addObject("activeMatches", matchEntity.getAllVacanciesMatchedToCvByUserActive(userModel));
        modelAndView.addObject("endedMatches", matchEntity.getAllVacanciesMatchedToCvByUserEnded(userModel));
        return new ModelAndView("history");
    }

    @GetMapping("/match/{id}")
    public ModelAndView matchPage(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView("match");
        VacancyMatchModel vacancyMatchModel = matchEntity.getVacancyMatchModelById(Long.parseLong(id));
        modelAndView.addObject("match", vacancyMatchModel);
        return modelAndView;
    }

    @PostMapping("/endMatch")
    public ResponseEntity<?> endMatch(@RequestBody Map<String, Long> requestBody, HttpSession session) {
        VacancyMatchModel matchModel = matchEntity.getVacancyMatchModelById(requestBody.get("matchId"));
        matchEntity.unapplyMatch(matchModel.getCvModel(), matchModel.getVacancyModel());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/applyMatch")
    public ResponseEntity<?> applyMatch(@RequestBody Map<String, Long> requestBody, HttpSession session) {
        VacancyMatchModel matchModel = matchEntity.getVacancyMatchModelById(requestBody.get("matchId"));
        matchEntity.applyMatch(matchModel.getCvModel(), matchModel.getVacancyModel());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/register")
    public ModelAndView registerPage() {
        return new ModelAndView("registration");
    }

    @PostMapping("/createUser")
    public ModelAndView createUser(@RequestBody UserModel user, HttpSession session) {
        String userEmail = userEntity.getUserEmail(session);
        UserModel userModel = userEntity.getUserByEmail(userEmail);
        if (userModel.getAdmin().equals("yes")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userEntity.addUser(user);
            return new ModelAndView("index");
        }

        return new ModelAndView("index");
    }

    @PostMapping("/changePassword")
    public ModelAndView changePassword(@RequestBody String prevPassword, @RequestBody String newPassword, HttpSession session) {
        String userEmail = userEntity.getUserEmail(session);
        UserModel userModel = userEntity.getUserByEmail(userEmail);
        if (passwordEncoder.encode(userModel.getPassword()).equals(prevPassword)) {
            userModel.setPassword(newPassword);
            userEntity.addUser(userModel);
            return new ModelAndView("index");
        }
        return new ModelAndView("index");
    }

}
