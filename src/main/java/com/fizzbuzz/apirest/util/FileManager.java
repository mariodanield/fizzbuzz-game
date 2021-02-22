package com.fizzbuzz.apirest.util;

import java.io.*;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileManager {

	private static final Logger logger = LogManager.getLogger(FileManager.class);

	private ArrayList<String> msg;
	
	@Value("${com.fizzbuzz.apirest.util.fileManager.name}")
	private String fileName ;

	public FileManager() {
		msg = new ArrayList<String>();
	}

	public ArrayList<String> getMsg() {
		return msg;
	}

	public void setMsg(ArrayList<String> msg) {
		this.msg = msg;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void addToWrite(String s) {
		msg.add(s);
	}

	public void addToWrite(ArrayList<String> sList) {
		for (String s : sList)
			addToWrite(s);
	}
	
	public void save(String filePath) {
		BufferedWriter bufferedWriter = null;
		 try
		    {
		        File file = new File(fileName);
		        if (!file.exists()) 
		        {
		             file.createNewFile();
		        }
		        FileWriter filewriter = new FileWriter(file.getAbsoluteFile(), true);
		        bufferedWriter = new BufferedWriter(filewriter);
		        for (String s : this.msg) {
		            bufferedWriter.write(s + "\n"); 
		        } 
		    }
		    catch (IOException e)
		    {
		    	logger.error(e.getMessage());
		    } finally {
				if (bufferedWriter != null) {
					try {
						bufferedWriter.close();
					} catch (IOException e) {
						logger.error(e.getMessage());
					}
				}
				if (!this.msg.isEmpty()) {
					this.msg.clear();
				}
			}
	}

	public void save() {
		logger.info("Start function save().");
		save(fileName);
		logger.info("End function save().");
	}

}