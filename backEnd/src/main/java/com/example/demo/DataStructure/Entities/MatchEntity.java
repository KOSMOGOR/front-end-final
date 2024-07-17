package com.example.demo.DataStructure.Entities;

import com.example.demo.DataStructure.*;
import com.example.demo.DataStructure.Entities.SubClasses.CvTitleEntity;
import com.example.demo.DataStructure.Entities.SubClasses.VacancyTitleEntity;
import com.example.demo.DataStructure.Entities.SubClasses.matchStateEntity;
import com.example.demo.DataStructure.Repositories.CvMatchModelRepository;
import com.example.demo.DataStructure.Repositories.VacancyMatchModelRepository;
import com.example.demo.Exceptions.NotFoundException;
import com.example.demo.Services.Utils;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class MatchEntity {

    CvMatchModelRepository cvMatchModelRepository;
    VacancyMatchModelRepository vacancyMatchModelRepository;
    CvEntity cvEntity;
    VacancyEntity vacancyEntity;

    private VacancyMatchModel createMatchToVacancy(VacancyModel vacancyModel) {
        VacancyMatchModel vacancyMatchModel = new VacancyMatchModel();
        vacancyMatchModel.setVacancyModel(vacancyModel);
        vacancyMatchModel.setInMatch(false);
        vacancyMatchModel.setMatchStates(new ArrayList<>());
        return vacancyMatchModelRepository.save(vacancyMatchModel);
    }

    private CvMatchModel createMatchToCv(CvModel cvModel) {
        CvMatchModel cvMatchModel = new CvMatchModel();
        cvMatchModel.setCvModel(cvModel);
        cvMatchModel.setInMatch(false);
        cvMatchModel.setMatchStates(new ArrayList<>());
        return cvMatchModelRepository.save(cvMatchModel);
    }

    private Pair<Set<CvModel>, Set<CvModel>> getAllMatchedToVacancy(VacancyModel vacancyModel) {
        String[] vacancyTitle = vacancyModel.getTitle().split(" ");
        String[] experience =  vacancyModel.getExperience().split(" ");
        int cvLevel =  Utils.getLevelInt(Utils.getLevelString(vacancyModel.getTitle()));

        Set<CvModel> cvModelsG = new HashSet<>();
        Set<CvModel> cvModelsB = new HashSet<>();
        Arrays.stream(vacancyTitle).forEach(path -> {
            if (!(path.toLowerCase().contains("middle") || path.toLowerCase().contains("junior") || path.toLowerCase().contains("senior"))) {
                List<CvModel> cvModel = getAllCvByTitleNotInMatch(path);
                cvModel.forEach(cv -> {
                    if (Integer.parseInt(experience[0]) <= Integer.parseInt(cv.getExperience().split(" ")[0])
                            || Utils.getLevelInt(Utils.getLevelString(cv.getTitle())) >= cvLevel) {
                        cvModelsG.add(cv);
                    } else {
                        cvModelsB.add(cv);
                    }
                });
            }
        });
        return Pair.of(cvModelsG,  cvModelsB);
    }

    private Pair<Set<VacancyModel>, Set<VacancyModel>> getAllMatchedToCv(CvModel cvModel) {
        String[] cvTitle = cvModel.getTitle().split(" ");
        int experience =  Integer.parseInt(cvModel.getExperience().split(" ")[0]);
        int cvLevel =  Utils.getLevelInt(Utils.getLevelString(cvModel.getTitle()));

        Set<VacancyModel> vacancyModelsG = new HashSet<>();
        Set<VacancyModel> vacancyModelsB = new HashSet<>();

        Arrays.stream(cvTitle).forEach(path -> {
            if (!(path.toLowerCase().contains("middle") || path.toLowerCase().contains("junior") || path.toLowerCase().contains("senior"))) {
                List<VacancyModel> vacancyModel = getAllVacancyByTitleNotInMatch(path);
                vacancyModel.forEach(vacancy -> {
                    if (Integer.parseInt(vacancy.getExperience().split(" ")[0]) <= experience
                            || Utils.getLevelInt(Utils.getLevelString(vacancy.getTitle())) <= cvLevel) {
                        vacancyModelsG.add(vacancy);
                    } else {
                        vacancyModelsB.add(vacancy);
                    }
                });
            }
        });
        return Pair.of(vacancyModelsG,  vacancyModelsB);
    }

    public VacancyMatchModel createConnectionToVacancy(VacancyModel vacancyModel) {
        VacancyMatchModel vacancyMatchModel = createMatchToVacancy(vacancyModel);
        vacancyMatchModel.setInMatch(false);
        Pair<Set<CvModel>, Set<CvModel>> pair = getAllMatchedToVacancy(vacancyModel);
        pair.getFirst().forEach(cvModel -> {
            Optional<CvMatchModel> cvMatchModelOp = cvMatchModelRepository.findById(cvModel.getId());
            if (cvMatchModelOp.isEmpty()) {
                throw new RuntimeException("cv not found");
            }
            CvMatchModel cvMatchModel = cvMatchModelOp.get();

            vacancyMatchModel.getMatchStates().add(new matchStateEntity(cvModel.getId(), MatchState.best.name));

            List<matchStateEntity> matchStates = cvMatchModel.getMatchStates();
            matchStates.add(new matchStateEntity(vacancyMatchModel.getId(), MatchState.best.name));
            cvMatchModel.setMatchStates(matchStates);

            cvMatchModelRepository.save(cvMatchModel);

        });
        pair.getSecond().forEach(cvModel -> {
            Optional<CvMatchModel> cvMatchModelOp = cvMatchModelRepository.findById(cvModel.getId());
            if (cvMatchModelOp.isEmpty()) {
                throw new RuntimeException("cv not found");
            }
            CvMatchModel cvMatchModel = cvMatchModelOp.get();

            vacancyMatchModel.getMatchStates().add(new matchStateEntity(cvModel.getId(), MatchState.worst.name));

            List<matchStateEntity> matchStates = cvMatchModel.getMatchStates();
            matchStates.add(new matchStateEntity(vacancyMatchModel.getId(), MatchState.worst.name));
            cvMatchModel.setMatchStates(matchStates);

            cvMatchModelRepository.save(cvMatchModel);

        });
        return vacancyMatchModelRepository.save(vacancyMatchModel);
    }

    public CvMatchModel createConnectionToCv(CvModel cvModel) {
        CvMatchModel cvMatchModel = createMatchToCv(cvModel);
        cvMatchModel.setInMatch(false);
        Pair<Set<VacancyModel>, Set<VacancyModel>> pair = getAllMatchedToCv(cvModel);
        pair.getFirst().forEach(vacancyModel -> {

            Optional<VacancyMatchModel> vacancyMatchModelOp = vacancyMatchModelRepository.findById(vacancyModel.getId());
            if (vacancyMatchModelOp.isEmpty()) {
                throw new RuntimeException("vacancy not found");
            }
            VacancyMatchModel vacancyMatchModel = vacancyMatchModelOp.get();

            cvMatchModel.getMatchStates().add(new matchStateEntity(vacancyModel.getId(), MatchState.best.name));

            List<matchStateEntity> matchStates = vacancyMatchModel.getMatchStates();
            matchStates.add(new matchStateEntity(cvMatchModel.getId(), MatchState.best.name));
            vacancyMatchModel.setMatchStates(matchStates);

            vacancyMatchModelRepository.save(vacancyMatchModel);

        });
        pair.getSecond().forEach(vacancyModel -> {

            Optional<VacancyMatchModel> vacancyMatchModelOp = vacancyMatchModelRepository.findById(vacancyModel.getId());
            if (vacancyMatchModelOp.isEmpty()) {
                throw new RuntimeException("vacancy not found");
            }
            VacancyMatchModel vacancyMatchModel = vacancyMatchModelOp.get();

            cvMatchModel.getMatchStates().add(new matchStateEntity(vacancyModel.getId(), MatchState.worst.name));

            List<matchStateEntity> matchStates = vacancyMatchModel.getMatchStates();
            matchStates.add(new matchStateEntity(cvMatchModel.getId(), MatchState.worst.name));
            vacancyMatchModel.setMatchStates(matchStates);

            vacancyMatchModelRepository.save(vacancyMatchModel);

        });
        return cvMatchModelRepository.save(cvMatchModel);
    }

    public List<CvModel> getAllCvMatchedToVacancy(VacancyModel vacancyModel) {
        Optional<VacancyMatchModel> vacancyMatchModelOp = vacancyMatchModelRepository.findById(vacancyModel.getId());
        if (vacancyMatchModelOp.isEmpty()) {
            throw new RuntimeException("vacancy not found");
        }

        return vacancyMatchModelOp
                .get()
                .getMatchStates().stream()
                .filter(matchStateEntity -> matchStateEntity.getState().equals(MatchState.best.name))
                .map(matchStateEntity -> {
                        try {
                            CvModel cvModel = cvEntity.getCvById(matchStateEntity.getEntityId());
                            Optional<CvMatchModel> cvMatchModelOp = cvMatchModelRepository.findById(cvModel.getId());
                            if (cvMatchModelOp.isEmpty()) {
                                throw new RuntimeException("cv not found");
                            }
                            if (!cvMatchModelOp.get().isInMatch()) {
                                return cvModel;
                            }
                            return null;
                        } catch (NotFoundException e) {
                            throw new RuntimeException(e);
                        }
                }).filter(Objects::nonNull).toList();
    }

    public List<VacancyModel> getAllVacancyMatchedToCv(CvModel cvModel) {
        Optional<CvMatchModel> cvMatchModelOp = cvMatchModelRepository.findById(cvModel.getId());
        if (cvMatchModelOp.isEmpty()) {
            throw new RuntimeException("cv not found");
        }

        return cvMatchModelOp
                .get()
                .getMatchStates().stream()
                .filter(matchStateEntity -> matchStateEntity.getState().equals(MatchState.best.name))
                .map(matchStateEntity -> {
                    try {
                        VacancyModel vacancyModel = vacancyEntity.getVacancyById(matchStateEntity.getEntityId());
                        Optional<VacancyMatchModel> vacancyMatchModelOp = vacancyMatchModelRepository.findById(vacancyModel.getId());
                        if (vacancyMatchModelOp.isEmpty()) {
                            throw new RuntimeException("vacancy not found");
                        }
                        if (!vacancyMatchModelOp.get().isInMatch()) {
                            return vacancyModel;
                        }
                        return null;
                    } catch (NotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }).filter(Objects::nonNull).toList();
    }

    public List<CvModel> getAddCvMatchedToVacancy(VacancyModel vacancyModel) {
        Optional<VacancyMatchModel> vacancyMatchModelOp = vacancyMatchModelRepository.findById(vacancyModel.getId());
        if (vacancyMatchModelOp.isEmpty()) {
            throw new RuntimeException("vacancy not found");
        }

        return vacancyMatchModelOp
                .get()
                .getMatchStates().stream()
                .filter(matchStateEntity -> matchStateEntity.getState().equals(MatchState.worst.name))
                .map(matchStateEntity -> {
                    try {
                        return cvEntity.getCvById(matchStateEntity.getEntityId());
                    } catch (NotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }).toList();
    }

    public List<VacancyModel> getAddVacancyMatchedToCv(CvModel cvModel) {
        Optional<CvMatchModel> cvMatchModelOp = cvMatchModelRepository.findById(cvModel.getId());
        if (cvMatchModelOp.isEmpty()) {
            throw new RuntimeException("cv not found");
        }

        return cvMatchModelOp
                .get()
                .getMatchStates().stream()
                .filter(matchStateEntity -> matchStateEntity.getState().equals(MatchState.worst.name))
                .map(matchStateEntity -> {
                    try {
                        return vacancyEntity.getVacancyById(matchStateEntity.getEntityId());
                    } catch (NotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }).toList();
    }

    public List<CvModel> getAllCvNotInMatch() {
        return cvMatchModelRepository.findAllByInMatch(false).stream().map(CvMatchModel::getCvModel).toList();
    }

    public List<VacancyModel> getAllVacancyNotInMatch() {
        return vacancyMatchModelRepository.findAllByInMatch(false).stream().map(VacancyMatchModel::getVacancyModel).toList();
    }

    public List<CvModel> getAllCvByTitleNotInMatch(String title) {
        return getAllCvNotInMatch().stream().filter(cvModel -> cvModel.getTitle().contains(title)).toList();
    }

    public List<VacancyModel> getAllVacancyByTitleNotInMatch(String title) {
        return getAllVacancyNotInMatch().stream().filter(vacancyModel -> vacancyModel.getTitle().contains(title)).toList();
    }

    public List<CvTitleEntity> getAllCvNotInMatchTitles() {
        return getAllCvNotInMatch().stream().map(cvModel -> new CvTitleEntity(cvModel.getTitle())).toList();
    }

    public List<VacancyTitleEntity> getAllVacancyNotInMatchTitles() {
        return getAllVacancyNotInMatch().stream().map(vacancyModel -> new VacancyTitleEntity(vacancyModel.getTitle())).toList();
    }

    public List<CvTitleEntity> getAllCvByTitleNotInMatchTitles(String title) {
        return getAllCvNotInMatch().stream().filter(cvModel -> cvModel.getTitle().contains(title)).map(cvModel -> new CvTitleEntity(cvModel.getTitle())).toList();
    }

    public List<VacancyTitleEntity> getAllVacancyByTitleNotInMatchTitles(String title) {
        return getAllVacancyNotInMatch().stream().filter(vacancyModel -> vacancyModel.getTitle().contains(title)).map(vacancyModel -> new VacancyTitleEntity(vacancyModel.getTitle())).toList();
    }

    public List<CvModel> getAllCvInMatch() {
        return cvMatchModelRepository.findAllByInMatch(true).stream().map(CvMatchModel::getCvModel).toList();
    }

    public List<VacancyModel> getAllVacancyInMatch() {
        return vacancyMatchModelRepository.findAllByInMatch(true).stream().map(VacancyMatchModel::getVacancyModel).toList();
    }

    public List<CvModel> getAllCvByTitleInMatch(String title) {
        return getAllCvInMatch().stream().filter(cvModel -> cvModel.getTitle().contains(title)).toList();
    }

    public List<VacancyModel> getAllVacancyByTitleInMatch(String title) {
        return getAllVacancyInMatch().stream().filter(vacancyModel -> vacancyModel.getTitle().contains(title)).toList();
    }

    public enum MatchState {
        best ("best"),
        worst ("worst");

        final String name;
        MatchState(String name) {
            this.name = name;
        }
    }

    public List<VacancyMatchModel> getAllVacanciesMatchedToCvByUserActive(UserModel userModel) {
        return vacancyMatchModelRepository.findAllByInMatchAndUserIdAndApplied(true, userModel.getId(), false);
    }

    public List<VacancyMatchModel> getAllVacanciesMatchedToCvByUserEnded(UserModel userModel) {
        return vacancyMatchModelRepository.findAllByInMatchAndUserIdAndApplied(true, userModel.getId(), true);
    }

    public VacancyMatchModel getVacancyMatchModelById(long id) {
        Optional<VacancyMatchModel> vacancyMatchModelOp = vacancyMatchModelRepository.findById(id);
        if (vacancyMatchModelOp.isEmpty()) {
            throw new RuntimeException("vacancy not found");
        }
        return vacancyMatchModelOp.get();
    }

    public void match(CvModel cvModel, VacancyModel vacancyModel, UserModel userModel) {
        Optional<CvMatchModel> cvMatchModel = cvMatchModelRepository.findById(cvModel.getId());
        Optional<VacancyMatchModel> vacancyMatchModel = vacancyMatchModelRepository.findById(vacancyModel.getId());

        if (cvMatchModel.isEmpty() || vacancyMatchModel.isEmpty()) {
            throw new RuntimeException("cv or vacancy not found");
        }
        cvMatchModel.get().setInMatch(true);
        cvMatchModel.get().setVacancyModel(vacancyModel);
        cvMatchModel.get().setApplied(false);
        cvMatchModel.get().setUserId(userModel.getId());
        vacancyMatchModel.get().setInMatch(true);
        vacancyMatchModel.get().setCvModel(cvModel);
        vacancyMatchModel.get().setApplied(false);
        vacancyMatchModel.get().setUserId(userModel.getId());
        vacancyMatchModelRepository.save(vacancyMatchModel.get());
        cvMatchModelRepository.save(cvMatchModel.get());
    }

    public void applyMatch(CvModel cvModel, VacancyModel vacancyModel) {
        Optional<CvMatchModel> cvMatchModel = cvMatchModelRepository.findById(cvModel.getId());
        Optional<VacancyMatchModel> vacancyMatchModel = vacancyMatchModelRepository.findById(vacancyModel.getId());

        if (cvMatchModel.isEmpty() || vacancyMatchModel.isEmpty()) {
            throw new RuntimeException("cv or vacancy not found");
        }
        cvMatchModel.get().setInMatch(true);
        cvMatchModel.get().setApplied(true);
        vacancyMatchModel.get().setInMatch(true);
        vacancyMatchModel.get().setApplied(true);
        vacancyMatchModelRepository.save(vacancyMatchModel.get());
        cvMatchModelRepository.save(cvMatchModel.get());
    }

    public void unapplyMatch(CvModel cvModel, VacancyModel vacancyModel) {
        Optional<CvMatchModel> cvMatchModel = cvMatchModelRepository.findById(cvModel.getId());
        Optional<VacancyMatchModel> vacancyMatchModel = vacancyMatchModelRepository.findById(vacancyModel.getId());

        if (cvMatchModel.isEmpty() || vacancyMatchModel.isEmpty()) {
            throw new RuntimeException("cv or vacancy not found");
        }
        cvMatchModel.get().setInMatch(false);
        cvMatchModel.get().setVacancyModel(null);
        cvMatchModel.get().setApplied(false);
        cvMatchModel.get().setUserId(0);
        vacancyMatchModel.get().setInMatch(false);
        vacancyMatchModel.get().setCvModel(null);
        vacancyMatchModel.get().setApplied(false);
        vacancyMatchModel.get().setUserId(0);
        cvMatchModelRepository.save(cvMatchModel.get());
        vacancyMatchModelRepository.save(vacancyMatchModel.get());
    }
}
