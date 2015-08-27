package com.mymemory.interfaces;

import java.util.List;

import com.mymemory.exceptions.ReaderException;
import com.sun.istack.internal.NotNull;

public interface FileReader {
	
	public List<Data> readFromFile(@NotNull String filename,@NotNull String data) throws ReaderException;
	public List<Data> readKeyFromFile(@NotNull String filename,@NotNull String data) throws ReaderException;
	
}
