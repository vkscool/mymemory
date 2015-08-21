package com.mymemory.main.data;

import java.util.ArrayList;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.mymemory.interfaces.Data;

public class MemoryData{
	
	private String key;
	private int hits;
	private ArrayList<String> lastaccessed;
	private ArrayList<ValueData> value;
	
	public void addLastAccessed(String s){
		if(lastaccessed==null){
			lastaccessed = new ArrayList<String>();
		}
		lastaccessed.add(s);
	}
	
	public void addData(ValueData s){
		if(value==null){
			value = new ArrayList<ValueData>();
		}
		value.add(s);
	}
	
	public MemoryData(){
		super();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public ArrayList<String> getLastaccessed() {
		return lastaccessed;
	}

	public void setLastaccessed(ArrayList<String> lastaccessed) {
		this.lastaccessed = lastaccessed;
	}

	public ArrayList<ValueData> getValue() {
		return value;
	}

	public void setValue(ArrayList<ValueData> value) {
		this.value = value;
	}

	/*@Override
	public String toString() {
		return getDataAsString();
	}

	@Override
	public String getDataAsString() {
		ObjectMapper om = new ObjectMapper();
		try{
			return om.writeValueAsString(this);
		}catch(Exception e){
			System.out.println("Exception in converting to json "+e);
		}
		return null;
	}

	@Override
	public MemoryData getDataAsObject() {
		return this;
	}*/
}