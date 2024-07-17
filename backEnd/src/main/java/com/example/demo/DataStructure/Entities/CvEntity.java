package com.example.demo.DataStructure.Entities;

import com.example.demo.DataStructure.CvModel;
import com.example.demo.DataStructure.Entities.SubClasses.CvTitleEntity;
import com.example.demo.DataStructure.Repositories.CvModelRepository;
import com.example.demo.Exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CvEntity {
    CvModelRepository cvModelRepository;

    public CvModel save(CvModel cv) {
        return cvModelRepository.save(cv);
    }

    public List<CvModel> getAll() {
        return cvModelRepository.findAll();
    }

    public List<CvTitleEntity> getAllTitles() {
        return getAll().stream().map(cvModel -> new CvTitleEntity(cvModel.getTitle())).toList();
    }

    public Set<CvTitleEntity> getUniqueTitles() {
        return getAll().stream().map(cvModel -> new CvTitleEntity(cvModel.getTitle())).collect(Collectors.toSet());
    }

    public CvModel getCvById(long id) throws NotFoundException {
        Optional<CvModel> cvModel = cvModelRepository.findById(id);
        if (cvModel.isPresent()) {
            return cvModel.get();
        }
        throw new NotFoundException("vacancy not found");
    }

    public List<CvModel> getAllByTitleContains(String title) {
        return cvModelRepository.findAllByTitleContains(title);
    }

    public List<CvTitleEntity> getAllTitlesByTitleContains(String title) {
        return getAllByTitleContains(title).stream().map(cvModel -> new CvTitleEntity(cvModel.getTitle())).toList();
    }

    public List<CvModel> getAllByMessage(String message) {
        return cvModelRepository.findAllByMessageContains(message);
    }

    public static CvModel createCv(String title, String experience, String chat_link, String message_link, String fromUser_link, String message) {
        CvModel cv = new CvModel();
        cv.setTitle(title);
        cv.setExperience(experience);
        cv.setChat_link(chat_link);
        cv.setMessage_link(message_link);
        cv.setFromUser_link(fromUser_link);
        cv.setMessage(message);
        return cv;
    }
}
