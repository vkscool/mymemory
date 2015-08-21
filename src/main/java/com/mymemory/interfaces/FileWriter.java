package com.mymemory.interfaces;

import com.mymemory.exceptions.WriterException;
import com.sun.istack.internal.NotNull;

public interface FileWriter {

	public int writeToFile(@NotNull String filename,Data data) throws WriterException;
	
}
