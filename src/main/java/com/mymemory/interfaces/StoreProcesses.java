package com.mymemory.interfaces;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mymemory.exceptions.DependencyException;
import com.mymemory.exceptions.WriterException;
import com.mymemory.main.core.MMResourceBundle;
import com.mymemory.main.core.MyFileWriter;
import com.mymemory.main.core.MyStoreProcesses;
import com.mymemory.interfaces.ResourceBundle;

public abstract class StoreProcesses implements StoreProcessesInterface{
	
	private static final Logger logger = LogManager.getLogger(MyStoreProcesses.class);
	private static HashMap<String, ResourceBundle> resourceMap = new HashMap<String, ResourceBundle>();
	private static Object rlock = new Object();
	private static String PROJECT_HOME = StoreProcesses.class.getClassLoader().getResource("").getPath();
	
	public abstract RefValues storeData(String data);
	public abstract String getFileName(FileNameType type) throws DependencyException, WriterException;

	public final void setResourcePath(String path){
		PROJECT_HOME = path;
		resourceMap = new HashMap<String, ResourceBundle>();
	}
	
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
		logger.debug("File path "+PROJECT_HOME);
		if (resourceMap.containsKey(file))
			return resourceMap.get(file);
		synchronized (rlock) {
			if (resourceMap.containsKey(file))
				return resourceMap.get(file);
			logger.debug("Checking for " + PROJECT_HOME + file + ".properties");
			bundle = new MMResourceBundle(PROJECT_HOME + file + ".properties", true);
			resourceMap.put(file, bundle);
		}
		return bundle;
	}
	
	public static String getValForKey(String propFilename, String key, String defaultVal) {
		String sVal = null;
		try {
			ResourceBundle bundle = getBundle(propFilename);
			sVal = bundle.getString(key);
			logger.info("sVal "+sVal);
			if (null == sVal)
				sVal = defaultVal;
		} catch (IOException e) {
			logger.warn(propFilename+ " file Not Found "+ e);
			writeExsistingPropertyToFile(propFilename,key,defaultVal);
			sVal = defaultVal;
		} catch (MissingResourceException e){
			logger.warn("Error while trying to read key-" + key + ",from prop file-" + propFilename + e);
			writeExsistingPropertyToFile(propFilename,key,defaultVal);
			sVal = defaultVal;
		} catch (Throwable th){
			logger.warn("Unexpected Problem Occured "+ th);
			sVal = defaultVal;
		}
		logger.debug("Key:-" + key + ", Value:-" + sVal);
		return sVal;
	}
	
	public static int writeExsistingPropertyToFile(String propFilename, String key, String defaultVal){
		try {
			ResourceBundle bundle = getBundle(propFilename);
			bundle.setKeyValue(key, defaultVal);
		} catch (FileNotFoundException e) {
			logger.debug("Unable To Write to File "+propFilename+" : "+e);
		} catch (IOException e) {
			logger.debug("Unable To Write to File "+propFilename+" : "+e);
		}
		return 1;
	}
	
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
