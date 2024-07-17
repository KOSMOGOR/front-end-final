package com.example.demo.DataStructure.Repositories;

import com.example.demo.DataStructure.UserModel;
import com.example.demo.DataStructure.VacancyMatchModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VacancyMatchModelRepository extends JpaRepository<VacancyMatchModel, Long> {
    Optional<VacancyMatchModel> findById(long id);
    List<VacancyMatchModel> findAllByInMatch(boolean inMatch);

    List<VacancyMatchModel> findAllByInMatchAndUserIdAndApplied(boolean inMatch, long userModel, boolean isApplied);
}
