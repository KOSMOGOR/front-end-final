package com.example.demo.DataStructure.Entities;

import com.example.demo.DataStructure.Entities.SubClasses.VacancyTitleEntity;
import com.example.demo.DataStructure.Repositories.VacancyModelReprository;
import com.example.demo.DataStructure.VacancyModel;
import com.example.demo.Exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VacancyEntity {

    VacancyModelReprository vacancyModelReprository;

    public VacancyModel save(VacancyModel vac) {
        return vacancyModelReprository.save(vac);
    }

    public List<VacancyModel> getAll() {
        return vacancyModelReprository.findAll();
    }

    public List<VacancyTitleEntity> getAllTitles() {
        return getAll().stream().map(vacancyModel -> new VacancyTitleEntity(vacancyModel.getTitle())).toList();
    }

    public Set<VacancyTitleEntity> getUniqueTitles() {
        return getAll().stream().map(vacancyModel -> new VacancyTitleEntity(vacancyModel.getTitle())).collect(Collectors.toSet());
    }

    public VacancyModel getVacancyById(long id) throws NotFoundException {
        Optional<VacancyModel> vacancyModel = vacancyModelReprository.findById(id);
        if (vacancyModel.isPresent()) {
            return vacancyModel.get();
        }
        throw new NotFoundException("vacancy not found");
    }

    public List<VacancyModel> getAllByTitleContains(String title) {
        return vacancyModelReprository.findAllByTitleContains(title);
    }

    public List<VacancyTitleEntity> getAllTitlesByTitleContains(String title) {
        return getAllByTitleContains(title).stream().map(vacancyModel -> new VacancyTitleEntity(vacancyModel.getTitle())).toList();
    }

    public List<VacancyModel> getAllByMessage(String message) {
        return vacancyModelReprository.findAllByMessageContains(message);
    }

    public static VacancyModel createVacancy(String title, String experience, String salary, String chat_link, String message_link, String fromUser_link, String message) {
        VacancyModel vac = new VacancyModel();
        vac.setTitle(title);
        vac.setExperience(experience);
        vac.setSalary(salary);
        vac.setChat_link(chat_link);
        vac.setMessage_link(message_link);
        vac.setFromUser_link(fromUser_link);
        vac.setMessage(message);
        return vac;
    }
}
