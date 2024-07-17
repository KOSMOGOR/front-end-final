package com.example.demo.DataStructure.Repositories;

import com.example.demo.DataStructure.CvMatchModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CvMatchModelRepository extends JpaRepository<CvMatchModel, Long> {

    List<CvMatchModel> findAllByInMatch(boolean inMatch);
    Optional<CvMatchModel> findById(long id);
}
