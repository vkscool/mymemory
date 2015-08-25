package com.mymemory.main.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mymemory.interfaces.StoreProcesses;
import com.mymemory.main.InvokerClass;

public class MyStoreProcesses extends StoreProcesses{

	private static final Logger logger = LogManager.getLogger(MyStoreProcesses.class);
	
	
	static{
		
	}
	
	@Override
	public RefValues storeData(String data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFileName(FileNameType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValForKey(String propFilename, String key, String defaultVal) {
		String sVal = null;
		try {
			ResourceBundle bundle = getBundle(propFilename);
			sVal = bundle.getString(key);
			if (null == sVal)
				sVal = defaultVal;

		} catch (Exception e) {
			logger.warn("Error while trying to read key-" + key + ",from prop file-" + propFilename);
			sVal = defaultVal;
		}
		logger.debug("Key:-" + key + ", Value:-" + sVal);
		return sVal;
	}
}
