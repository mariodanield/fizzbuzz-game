package com.fizzbuzz.apirest.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DateConvertTest {

	@Test
	void testFormatDateToString() {
		String dateString="22:50:20:999 21/02/2021";
		Date date = null;
		try {
			date = new SimpleDateFormat("HH:mm:ss:SSS dd/MM/yyyy").parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertThat(DateConvert.formatDateToString(date)).isNotNull();
		assertEquals(DateConvert.formatDateToString(date),dateString);
		assertNotEquals(DateConvert.formatDateToString(new Date()),dateString);
	}

}
