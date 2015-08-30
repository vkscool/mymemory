package com.mymemory.interfaces;

import com.mymemory.exceptions.WriterException;
import com.sun.istack.internal.NotNull;

public interface FileWriter {

	public int writeToFile(@NotNull String filename,Data data) throws WriterException;

	public int writeToFile(String filename, String s) throws WriterException;

	int writeToFile(String filename, String s, boolean flag) throws WriterException;
	
}
