package com.mymemory.main.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import com.mymemory.exceptions.WriterException;
import com.mymemory.interfaces.Data;
import com.mymemory.interfaces.FileWriter;

public class MyFileWriter implements FileWriter {

	@Override
	public int writeToFile(String filename, Data d) throws WriterException {
		if(filename==null || filename.isEmpty()){
			throw new WriterException("Filename is Null OR Empty");
		}
		File f = new File(filename);
		try(BufferedWriter br = new BufferedWriter(new java.io.FileWriter(f))){
			br.write(d.getObjectAsString());
		}catch(IOException ioex){
			throw new WriterException("Writting to a file is inturupted");
		}catch(Exception ex){
			throw new WriterException("Some Unknown Exception Occured While Writting to a file");
		}
		return 1;
	}

	
	@Override
	public int writeToFile(String filename, String s) throws WriterException {
		if(filename==null || filename.isEmpty()){
			throw new WriterException("Filename is Null OR Empty");
		}
		File f = new File(filename);
		try(BufferedWriter br = new BufferedWriter(new java.io.FileWriter(f,true))){
			br.write(s+System.lineSeparator());
		}catch(IOException ioex){
			throw new WriterException("Writting to a file is inturupted");
		}catch(Exception ex){
			throw new WriterException("Some Unknown Exception Occured While Writting to a file");
		}
		return 1;
	}
	
}