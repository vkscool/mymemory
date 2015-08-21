package com.mymemory.interfaces;

import java.util.List;

import com.mymemory.exceptions.ReaderException;
import com.mymemory.exceptions.WriterException;

public interface MyMemoryInterface {

	public void setWriter(FileWriter writer);
	
	public void setReader(FileReader reader);
	
	public void memorize(String key, String value) throws WriterException;
	
	public List<Data> searchInMemory() throws ReaderException;
	
}
