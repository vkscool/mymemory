package com.mymemory.main;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.mymemory.exceptions.ReaderException;
import com.mymemory.exceptions.WriterException;
import com.mymemory.interfaces.Data;
import com.mymemory.interfaces.FileReader;
import com.mymemory.interfaces.FileWriter;
import com.mymemory.interfaces.MyMemoryInterface;
import com.mymemory.main.core.DataImpl;
import com.mymemory.main.data.MemoryData;
import com.mymemory.main.data.ValueData;

public class MyMemory implements MyMemoryInterface{

	FileWriter writer;
	FileReader reader;
	
	@Override
	public void setWriter(FileWriter writer) {
		this.writer = writer;
	}
	
	@Override
	public void setReader(FileReader reader) {
		this.reader = reader;
	}
	
	@Override
	public void memorize(String key, String value) throws WriterException {
		MemoryData d = new MemoryData();
		d.setKey(key);
		d.setHits(0);
		d.addLastAccessed(new Timestamp(new Date().getTime()).toString());
		ValueData vd = new ValueData();
		vd.setHits(0);
		vd.setData(value);
		vd.addLastAccessed(new Timestamp(new Date().getTime()).toString());
		d.addData(vd);
		Data<MemoryData> di = new DataImpl<MemoryData>();
		di.setObject(d);
	}

	@Override
	public List<Data> searchInMemory() throws ReaderException {
		// TODO Auto-generated method stub
		return null;
	}
}
