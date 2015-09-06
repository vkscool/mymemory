package com.mymemory.interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public interface ResourceBundle {

	public void loadStream(InputStream stream) throws IOException;
	
	public void loadReader(Reader reader) throws IOException;
	
	public String getString(String key);
	
	public void setKeyValue(String key, String value) throws FileNotFoundException, IOException;
}
