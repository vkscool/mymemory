package com.mymemory.test;

import java.util.ArrayList;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.mymemory.interfaces.Data;
import com.mymemory.main.data.RefData;

public class ExampleData{
	
	private String key;
	private int hits;
	private ArrayList<String> lastaccessed;
	private ArrayList<ExampleValueData> value;
	
	public ExampleData(){
		super();
	}

	public void addLastAccessed(String s){
		if(lastaccessed==null){
			lastaccessed = new ArrayList<String>();
		}
		lastaccessed.add(s);
	}
	
	public void addData(ExampleValueData s){
		if(value==null){
			value = new ArrayList<ExampleValueData>();
		}
		value.add(s);
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

	public ArrayList<ExampleValueData> getValue() {
		return value;
	}

	public void setValue(ArrayList<ExampleValueData> value) {
		this.value = value;
	}
}