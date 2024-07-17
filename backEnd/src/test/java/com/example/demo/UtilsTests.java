package com.example.demo;

import com.example.demo.Services.Utils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class UtilsTests {

	@Test
	void getLevelStringTest() {
		HashMap<String, String> titleMap = new HashMap<>();
		titleMap.put("Junior Spring", "junior");
		titleMap.put("Middle Spring", "middle");
		titleMap.put("Senior Spring", "senior");
		titleMap.put("1C junior", "junior");
		titleMap.put("1C Senior developer", "senior");
		titleMap.put("C#", "");

		titleMap.forEach((key, value) -> {
			assertEquals(value, Utils.getLevelString(key));
		});
	}

	@Test
	void getLevelIntTest() {
		HashMap<String, Integer> titleMap = new HashMap<>();
		titleMap.put("junior", 1);
		titleMap.put("middle", 2);
		titleMap.put("senior", 3);
		titleMap.put("", 0);

		titleMap.forEach((key, value) -> {
			assertEquals(value, Utils.getLevelInt(key));
		});
	}

}
