package com.example.demo.EndPoints;

import com.example.demo.DataStructure.CvModel;
import com.example.demo.DataStructure.Entities.CvEntity;
import com.example.demo.DataStructure.Entities.MatchEntity;
import com.example.demo.DataStructure.Entities.UserEntity;
import com.example.demo.DataStructure.Entities.VacancyEntity;
import com.example.demo.DataStructure.UserModel;
import com.example.demo.DataStructure.VacancyModel;
import com.example.demo.Services.AuthService;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/")
@AllArgsConstructor
public class AnonimusEndPoints {

    CvEntity cvEntity;
    VacancyEntity vacancyEntity;
    MatchEntity matchEntity;
    EntityManager entityManager;


    UserEntity userEntity;
    AuthService authService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserModel userModel, HttpServletRequest request) {
        authService.autoLogin(userModel, userModel.getPassword(), request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @Transactional
    @PostMapping("/addVacancy")
    public ResponseEntity addVacancy(@RequestBody VacancyModel vacancyModel) {

        vacancyModel = entityManager.merge(vacancyModel);
        vacancyEntity.save(vacancyModel);
        matchEntity.createConnectionToVacancy(vacancyModel);
        return ResponseEntity.status(200).build();
    }
    @Transactional
    @PostMapping("/addCv")
    public ResponseEntity addCv(@RequestBody CvModel cvModel) {

        cvModel = entityManager.merge(cvModel);
        cvEntity.save(cvModel);
        matchEntity.createConnectionToCv(cvModel);
        return ResponseEntity.status(200).build();
    }
    @Transactional
    @GetMapping("/getCvs")
    public ResponseEntity getCvs() {
        return ResponseEntity.ok(matchEntity.getAllCvNotInMatchTitles());
    }
    @Transactional
    @GetMapping("/getVacancies")
    public ResponseEntity getVacancies() {
        return ResponseEntity.ok(matchEntity.getAllCvNotInMatchTitles());
    }
    @GetMapping("/test1")
    public void test1() {
        System.out.println(passwordEncoder.encode("123"));
    }
}
