package com.fizzbuzz.apirest.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FileManagerTest {

	@Autowired
	private FileManager fileManager;
	
	ArrayList<String> expectedList;
	
	@BeforeEach
    void init() {
		expectedList = new ArrayList<>();

		expectedList.add("Writing line Test 1");
		expectedList.add("Writing line Test 2");
		expectedList.add("Writing line Test 3");
		fileManager.getMsg().clear();;
	}
	@AfterEach
	void teardown() {
        expectedList.clear();
    }
	
	@Test
	void fileManagerIsNotNull() {
	  assertThat(fileManager).isNotNull();
	 }
	
	@Test
	void addLinesToWrite() {
		fileManager.addToWrite("Writing line Test 1");
		fileManager.addToWrite("Writing line Test 2");
		fileManager.addToWrite("Writing line Test 3");
		
	  assertThat(fileManager.getMsg().size()).isEqualTo(3);
	 }
	
	@Test
	void addLinesToWriteListEqual() {
		
		
		fileManager.addToWrite("Writing line Test 1");
		fileManager.addToWrite("Writing line Test 2");
		fileManager.addToWrite("Writing line Test 3");
		
	  assertThat(fileManager.getMsg()).isEqualTo(expectedList);
	 }
	
	
	@Test
	void saveLinesDoesNotThrow() {
		assertDoesNotThrow(() -> {
			fileManager.addToWrite("Writing line Test 1");
			fileManager.addToWrite("Writing line Test 2");
			fileManager.addToWrite("Writing line Test 3");
			fileManager.addToWrite("Writing line Test 4");
			fileManager.addToWrite("Writing line Test 5");
			fileManager.save();
	    });
		assertDoesNotThrow(() -> {
			fileManager.addToWrite(expectedList);
			fileManager.save();
	    });
	 }

}
