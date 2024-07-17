package com.example.demo;

import com.example.demo.DataStructure.CvModel;
import com.example.demo.DataStructure.Entities.CvEntity;
import com.example.demo.DataStructure.Entities.SubClasses.CvTitleEntity;
import com.example.demo.DataStructure.Entities.VacancyEntity;
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
class CvEntityTest {

	@Autowired
	CvEntity cvEntity;
	@Test
	void saveCvTest() {
		try {
            CvModel cv = cvEntity.createCv("title", "experience", "chat_link", "message_link", "fromUser_link", "message");

			cv = cvEntity.save(cv);

			CvModel expected = cvEntity.getCvById(cv.getId());

            assertEquals(cv, expected);
		} catch (NotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	@Test
	void getAllTest() {
		List<CvModel> cvModels = new ArrayList<>();
		cvModels.add(cvEntity.save(CvEntity.createCv("title1", "experience1","chat_link1", "message_link1", "fromUser_link1", "message1")));
		cvModels.add(cvEntity.save(CvEntity.createCv("title2", "experience2","chat_link2", "message_link2", "fromUser_link2", "message2")));
		cvModels.add(cvEntity.save(CvEntity.createCv("title1", "experience3","chat_link3", "message_link3", "fromUser_link3", "message3")));

		assertEquals(cvModels, cvEntity.getAll());
	}
	@Test
	void getAllTitlesTest() {
		List<CvTitleEntity> titles = new ArrayList<>();
		titles.add(new CvTitleEntity(
			cvEntity.save(CvEntity.createCv("title1", "experience1", "chat_link1", "message_link1", "fromUser_link1", "message1")).getTitle()
		));
		titles.add(new CvTitleEntity(
				cvEntity.save(CvEntity.createCv("title2", "experience2","chat_link2", "message_link2", "fromUser_link2", "message2")).getTitle()
		));
		titles.add(new CvTitleEntity(
				cvEntity.save(CvEntity.createCv("title1", "experience3","chat_link3", "message_link3", "fromUser_link3", "message3")).getTitle()
		));
		assertEquals(titles, cvEntity.getAllTitles());
	}
	@Test
	void getUniqueTitlesTest() {
		Set<CvTitleEntity> titles = new HashSet<>();
		titles.add(new CvTitleEntity(
				cvEntity.save(CvEntity.createCv("title1", "experience1", "chat_link1", "message_link1", "fromUser_link1", "message1")).getTitle()
		));
		titles.add(new CvTitleEntity(
				cvEntity.save(CvEntity.createCv("title2", "experience2", "chat_link2", "message_link2", "fromUser_link2", "message2")).getTitle()
		));
		titles.add(new CvTitleEntity(
				cvEntity.save(CvEntity.createCv("title1", "experience3", "chat_link3", "message_link3", "fromUser_link3", "message3")).getTitle()
		));
		assertEquals(titles, cvEntity.getUniqueTitles());
	}
	@Test
	void getCvByIdTest() throws NotFoundException {
		CvModel cvModel = cvEntity.save(CvEntity.createCv("title", "experience", "chat_link", "message_link", "fromUser_link", "message"));
		assertEquals(cvModel, cvEntity.getCvById(cvModel.getId()));
	}
	@Test
	void getAllByTitleContainsTest() {
		List<CvModel> cvModels = new ArrayList<>();
		cvModels.add(cvEntity.save(CvEntity.createCv("title1", "experience1", "chat_link1", "message_link1", "fromUser_link1", "message1")));
		cvEntity.save(CvEntity.createCv("title2", "experience2", "chat_link2", "message_link2", "fromUser_link2", "message2"));
		cvModels.add(cvEntity.save(CvEntity.createCv("title1", "experience3", "chat_link3", "message_link3", "fromUser_link3", "message3")));

		assertEquals(cvModels, cvEntity.getAllByTitleContains("1"));
	}
	@Test
	void getAllTitlesByTitleContainsTest() {
		List<CvTitleEntity> titles = new ArrayList<>();
		titles.add(new CvTitleEntity(
				cvEntity.save(CvEntity.createCv("title1", "experience1", "chat_link1", "message_link1", "fromUser_link1", "message1")).getTitle()
		));
		cvEntity.save(CvEntity.createCv("title2", "experience2", "chat_link2", "message_link2", "fromUser_link2", "message2"));
		titles.add(new CvTitleEntity(
				cvEntity.save(CvEntity.createCv("title1", "experience3", "chat_link3", "message_link3", "fromUser_link3", "message3")).getTitle()
		));
		assertEquals(titles, cvEntity.getAllTitlesByTitleContains("1"));
	}


}
