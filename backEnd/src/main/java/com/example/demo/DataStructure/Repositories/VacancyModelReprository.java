package com.example.demo.DataStructure.Repositories;

import com.example.demo.DataStructure.CvModel;
import com.example.demo.DataStructure.VacancyModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VacancyModelReprository extends JpaRepository<VacancyModel, Long> {
    List<VacancyModel> findAll();
    Optional<VacancyModel> findById(long id);
    List<VacancyModel> findAllByTitleContains(String title);
    List<VacancyModel> findAllByMessageContains(String message);

}
