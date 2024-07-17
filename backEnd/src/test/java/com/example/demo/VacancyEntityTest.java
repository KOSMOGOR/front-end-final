package com.example.demo;

import com.example.demo.DataStructure.CvModel;
import com.example.demo.DataStructure.Entities.CvEntity;
import com.example.demo.DataStructure.Entities.VacancyEntity;
import com.example.demo.DataStructure.Entities.SubClasses.VacancyTitleEntity;
import com.example.demo.DataStructure.VacancyModel;
import com.example.demo.Exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class VacancyEntityTest {

	@Autowired
	VacancyEntity vacancyEntity;
	@Test
	void saveVacancyTest() {
		try {
			VacancyModel vacancy = VacancyEntity.createVacancy("title", "experience", "salary", "chat_link", "message_link", "fromUser_link", "message");

			vacancy = vacancyEntity.save(vacancy);

			VacancyModel expected = vacancyEntity.getVacancyById(vacancy.getId());

			assertEquals(vacancy, expected);
		} catch (NotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	@Test
	void getAllTest() {
		List<VacancyModel> vacancyModels = new ArrayList<>();
		vacancyModels.add(vacancyEntity.save(VacancyEntity.createVacancy("title1", "experience1", "salary1", "chat_link1", "message_link1", "fromUser_link1", "message1")));
		vacancyModels.add(vacancyEntity.save(VacancyEntity.createVacancy("title2", "experience2", "salary2", "chat_link2", "message_link2", "fromUser_link2", "message2")));
		vacancyModels.add(vacancyEntity.save(VacancyEntity.createVacancy("title1", "experience3", "salary3", "chat_link3", "message_link3", "fromUser_link3", "message3")));

		assertEquals(vacancyModels, vacancyEntity.getAll());
	}
	@Test
	void getAllTitlesTest() {
		List<VacancyTitleEntity> titles = new ArrayList<>();
		titles.add(new VacancyTitleEntity(
			vacancyEntity.save(VacancyEntity.createVacancy("title1", "experience1", "salary1", "chat_link1", "message_link1", "fromUser_link1", "message1")).getTitle()
		));
		titles.add(new VacancyTitleEntity(
				vacancyEntity.save(VacancyEntity.createVacancy("title2", "experience2", "salary2", "chat_link2", "message_link2", "fromUser_link2", "message2")).getTitle()
		));
		titles.add(new VacancyTitleEntity(
				vacancyEntity.save(VacancyEntity.createVacancy("title1", "experience3", "salary3", "chat_link3", "message_link3", "fromUser_link3", "message3")).getTitle()
		));
		assertEquals(titles, vacancyEntity.getAllTitles());
	}
	@Test
	void getUniqueTitlesTest() {
		Set<VacancyTitleEntity> titles = new HashSet<>();
		titles.add(new VacancyTitleEntity(
				vacancyEntity.save(VacancyEntity.createVacancy("title1", "experience1", "salary1", "chat_link1", "message_link1", "fromUser_link1", "message1")).getTitle()
		));
		titles.add(new VacancyTitleEntity(
				vacancyEntity.save(VacancyEntity.createVacancy("title2", "experience2", "salary2", "chat_link2", "message_link2", "fromUser_link2", "message2")).getTitle()
		));
		titles.add(new VacancyTitleEntity(
				vacancyEntity.save(VacancyEntity.createVacancy("title1", "experience3", "salary3", "chat_link3", "message_link3", "fromUser_link3", "message3")).getTitle()
		));
		assertEquals(titles, vacancyEntity.getUniqueTitles());
	}
	@Test
	void getVacancyByIdTest() throws NotFoundException {
		VacancyModel vacancyModel = vacancyEntity.save(VacancyEntity.createVacancy("title", "experience", "salary", "chat_link", "message_link", "fromUser_link", "message"));
		assertEquals(vacancyModel, vacancyEntity.getVacancyById(vacancyModel.getId()));
	}
	@Test
	void getAllByTitleContainsTest() {
		List<VacancyModel> vacancyModels = new ArrayList<>();
		vacancyModels.add(vacancyEntity.save(VacancyEntity.createVacancy("title1", "experience1", "salary1", "chat_link1", "message_link1", "fromUser_link1", "message1")));
		vacancyEntity.save(VacancyEntity.createVacancy("title2", "experience2", "salary2", "chat_link2", "message_link2", "fromUser_link2", "message2"));
		vacancyModels.add(vacancyEntity.save(VacancyEntity.createVacancy("title1", "experience3", "salary3", "chat_link3", "message_link3", "fromUser_link3", "message3")));

		assertEquals(vacancyModels, vacancyEntity.getAllByTitleContains("1"));
	}
	@Test
	void getAllTitlesByTitleContainsTest() {
		List<VacancyTitleEntity> titles = new ArrayList<>();
		titles.add(new VacancyTitleEntity(
				vacancyEntity.save(VacancyEntity.createVacancy("title1", "experience1", "salary1", "chat_link1", "message_link1", "fromUser_link1", "message1")).getTitle()
		));
		vacancyEntity.save(VacancyEntity.createVacancy("title2", "experience2", "salary2", "chat_link2", "message_link2", "fromUser_link2", "message2"));
		titles.add(new VacancyTitleEntity(
				vacancyEntity.save(VacancyEntity.createVacancy("title1", "experience3", "salary3", "chat_link3", "message_link3", "fromUser_link3", "message3")).getTitle()
		));
		assertEquals(titles, vacancyEntity.getAllTitlesByTitleContains("1"));
	}


}
