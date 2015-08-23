package com.mymemory.interfaces;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mymemory.main.core.MyStoreProcesses;

public abstract class StoreProcesses implements StoreProcessesInterface{
	
	private static final Logger logger = LogManager.getLogger(MyStoreProcesses.class);
	private static HashMap<String, ResourceBundle> resourceMap = new HashMap<String, ResourceBundle>();
	private static Object rlock = new Object();
	private static String PROJECT_HOME = System.getProperty("catalina.home");
	public  static String RESOURCE_FILE_NAME = "/conf/griff/resources";
	private static final String RESOURCE_FILE_PATH = PROJECT_HOME + RESOURCE_FILE_NAME;
	
	public abstract RefValues storeData(String data);
	public abstract String getFileName(FileNameType type);
	public FileNameType getFileNameType(String data){
		if(data==null || data.isEmpty())
			return FileNameType.EMPTY;
		data = data.trim();
		if(data.length()==1)
			return FileNameType.ALPHABET;
		if(data.matches("^[A-Za-z]*$"))
			return FileNameType.WORD;
		return FileNameType.SENTENCE;
	}
	
	public static ResourceBundle getBundle(String file) throws IOException {
		ResourceBundle bundle = null;
		if (resourceMap.containsKey(file))
			return resourceMap.get(file);
		synchronized (rlock) {
			if (resourceMap.containsKey(file))
				return resourceMap.get(file);
			logger.debug("Checking for " + RESOURCE_FILE_PATH + "/" + file + ".properties");
			bundle = new PropertyResourceBundle(new FileInputStream(RESOURCE_FILE_PATH + "/" + file + ".properties"));
			resourceMap.put(file, bundle);
		}
		return bundle;
	}
	
	public abstract String getValForKey(String propFilename, String key, String defaultVal);
	
	class RefValuesImpl implements RefValues{

		private String file;
		private int lineno;
		private int position;
		
		@Override
		public String getFile() {
			return file;
		}
		public void setFile(String file) {
			this.file = file;
		}
		@Override
		public int getLineno() {
			return lineno;
		}
		public void setLineno(int lineno) {
			this.lineno = lineno;
		}
		@Override
		public int getPosition() {
			return position;
		}
		public void setPosition(int position) {
			this.position = position;
		}
	}
}
