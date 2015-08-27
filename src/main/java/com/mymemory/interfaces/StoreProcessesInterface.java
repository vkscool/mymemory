package com.mymemory.interfaces;

import com.mymemory.exceptions.DependencyException;

public interface StoreProcessesInterface {

	public RefValues storeData(String data);
	public String getFileName(FileNameType type) throws DependencyException;
	public FileNameType getFileNameType(String data);
	
	interface RefValues{
		public String getFile();
		public void setFile(String file);
		public int getLineno();
		public void setLineno(int lineno);
		public int getPosition();
		public void setPosition(int position);
	}
	
	enum FileNameType{
		ALPHABET,WORD,SENTENCE,EMPTY;
	}
	
	enum Files{
		CONFIGURATION("configuration"),
		FILESTORE("filestore");
		private String name;
		private Files(String name){
			this.name = name;
		}
	}
}
