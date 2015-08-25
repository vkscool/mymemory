package com.mymemory.interfaces;

public interface StoreProcessesInterface {

	public abstract RefValues storeData(String data);
	public abstract String getFileName(FileNameType type);
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
		CONFIGURATION("configuration.properties"),
		FileStore("filestore.properties");
		private String name;
		private Files(String name){
			this.name = name;
		}
	}
}
