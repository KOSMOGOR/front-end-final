package com.example.demo.DataStructure.Repositories;

import com.example.demo.DataStructure.CvModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CvModelRepository extends JpaRepository<CvModel, Long> {
    List<CvModel> findAll();
    Optional<CvModel> findById(long id);
    List<CvModel> findAllByTitleContains(String title);

    List<CvModel> findAllByMessageContains(String message);
}
