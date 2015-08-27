package com.mymemory.main.core;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.mymemory.exceptions.DependencyException;
import com.mymemory.interfaces.FileWriter;
import com.mymemory.interfaces.StoreProcesses;

import jdk.nashorn.internal.parser.JSONParser;

public class MyStoreProcesses extends StoreProcesses{

	private static final Logger logger = LogManager.getLogger(MyStoreProcesses.class);
	
	@Override
	public RefValues storeData(String data) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void setFileWriter(FileWriter ff){
		super.setFileWriter(ff);
	}

	@Override
	public String getFileName(FileNameType type) throws DependencyException {
		String data = null;
		if(type == FileNameType.ALPHABET){
			data = getValForKey(Files.FILESTORE.name(), "alphabetSettings", "[]");
		}else if(type == FileNameType.WORD){
			data = getValForKey(Files.FILESTORE.name(), "wordSettings", "[]");
		}else if(type == FileNameType.SENTENCE){
			data = getValForKey(Files.FILESTORE.name(), "sentenceSettings", "[]");
		}
		if(!ServiceUtils.isNullLength(data)){
			ObjectMapper mapper = new ObjectMapper();
			try {
				ArrayList<String> filedata = mapper.readValue(data, ArrayList.class);
				if(!filedata.isEmpty()){
					
				}else{
					String s = filedata.get(0);
					if(!ServiceUtils.isNullLength(s)){
						String maxFileSize = getValForKey(Files.FILESTORE.name(), "maxFileSize", "500");
						String ss[] = s.split("|");
						if(Integer.parseInt(ss[1])>=Integer.parseInt(maxFileSize)){
							
						}else{
							
						}
					}else{
						
					}
				}
			} catch (JsonParseException e) {
				throw new DependencyException("getFileName : "+e);
			} catch (JsonMappingException e) {
				throw new DependencyException("getFileName : "+e);
			} catch (IOException e) {
				throw new DependencyException("getFileName : "+e);
			}
		}else{
			
		}
		return null;
	}
}
