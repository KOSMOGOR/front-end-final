package com.example.demo.DataStructure;

import com.example.demo.DataStructure.Entities.SubClasses.matchStateEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "cvMatchModel")
public class CvMatchModel {
    @Id
    private long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id" , referencedColumnName = "id")
    private CvModel cvModel;


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "cvMatchState")
    private List<matchStateEntity> matchStates;

    private boolean inMatch;

    @Nullable
    @OneToOne
    @JoinColumn(name = "vacancyId", referencedColumnName = "id")
    private VacancyModel vacancyModel;

    private long userId = 0L;

    private boolean applied;

}
