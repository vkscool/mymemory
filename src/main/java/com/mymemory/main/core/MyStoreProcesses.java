package com.mymemory.main.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.mymemory.exceptions.DependencyException;
import com.mymemory.exceptions.WriterException;
import com.mymemory.interfaces.FileWriter;
import com.mymemory.interfaces.StoreProcesses;

public class MyStoreProcesses extends StoreProcesses{

	private static final Logger logger = LogManager.getLogger(MyStoreProcesses.class);
	private FileWriter ff;
	
	@Override
	public RefValues storeData(String data) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setFileWriter(FileWriter ff){
		this.ff = ff;
	}

	@Override
	public String getFileName(FileNameType type) throws DependencyException, WriterException {
		String data = null;
		String proptype = "";
		if(type == FileNameType.ALPHABET){
			proptype = "alphabetSettings";
		}else if(type == FileNameType.WORD){
			proptype = "wordSettings";
		}else if(type == FileNameType.SENTENCE){
			proptype = "sentenceSettings";
		}
		data = getValForKey(Files.FILESTORE.name(), proptype, "[]");
		logger.debug("Found Data for filestore property "+proptype+" data : "+data);
		String maxFileSize = getValForKey(Files.CONFIGURATION.name(), "maxFileSize", "500");
		String fileNamePrefix = getValForKey(Files.CONFIGURATION.name(), "fileNamePrefix", "s");
		if(!ServiceUtils.isNullLength(data)){
			ObjectMapper mapper = new ObjectMapper();
			try {
				@SuppressWarnings("unchecked")
				ArrayList<String> filedata = mapper.readValue(data, ArrayList.class);
				if(filedata==null || filedata.isEmpty()){
					logger.debug("ArrayList is Empty so creating new");
					data = fileNamePrefix+"0";
					String tostore = data+"|0|1|"+new Date().getTime();
					logger.debug("Tostore data "+tostore);
					if(filedata==null){
						filedata = new ArrayList<String>();
					}
					filedata.add(0,tostore);
					writeExsistingPropertyToFile(Files.FILESTORE.name(), proptype, mapper.writeValueAsString(filedata));
					logger.debug("Successfully wrote new file property to filestore ");
				}else{
					logger.debug("Parsed data to Arraylist of size "+filedata.size());
					String s = filedata.get(0);
					logger.debug("Found top most file in file property "+s);
					if(!ServiceUtils.isNullLength(s)){
						String ss[] = s.split("\\|");
						logger.debug("After split "+ss.length);
						if(Integer.parseInt(ss[1])>=Integer.parseInt(maxFileSize)){
							logger.debug("File size has crossed the limit "+ss[1]);
							data = fileNamePrefix+""+ss[2];
							logger.debug("creating new file with name "+data);
							int count = Integer.parseInt(ss[2])+1;
							String tostore = data+"|0|"+count+"|"+new Date().getTime();
							logger.debug("Tostore data "+tostore);
							filedata.add(0,tostore);
							writeExsistingPropertyToFile(Files.FILESTORE.name(), proptype, mapper.writeValueAsString(filedata));
						}else{
							logger.debug("File Size is under the maxfilesize "+ss[1]);
							data = ss[0];
						}
					}else{
						throw new DependencyException("Corrupt Data in Filestore Configuration");
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
		return data;
	}
}