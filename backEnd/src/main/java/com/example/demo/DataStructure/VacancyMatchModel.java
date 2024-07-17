package com.example.demo.DataStructure;

import com.example.demo.DataStructure.Entities.SubClasses.matchStateEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "vacancyMatchModel")
public class VacancyMatchModel {
    @Id
    private long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id" , referencedColumnName = "id")
    private VacancyModel vacancyModel;


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "vacancyMatchState")
    private List<matchStateEntity> matchStates;

    private boolean inMatch;

    @Nullable
    @OneToOne
    @JoinColumn(name = "cvId", referencedColumnName = "id")
    private CvModel cvModel;

    private long userId = 0L;

    private boolean applied;
}
