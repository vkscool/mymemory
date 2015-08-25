package com.mymemory.main.data;

import java.util.ArrayList;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.mymemory.interfaces.Data;

public class MemoryData{
	
	private String key;
	private int hits;
	private ArrayList<String> lastaccessed;
	private ArrayList<RefData> in;
	private ArrayList<RefData> mapped;
	
	public MemoryData(){
		super();
	}
	
	public void addIn(RefData s){
		if(in==null){
			in = new ArrayList<RefData>();
		}
		in.add(s);
	}
	
	public void addMapped(RefData s){
		if(mapped==null){
			mapped = new ArrayList<RefData>();
		}
		mapped.add(s);
	}
	
	public void addLastAccessed(String s){
		if(lastaccessed==null){
			lastaccessed = new ArrayList<String>();
		}
		lastaccessed.add(s);
	}
	
	public ArrayList<RefData> getIn() {
		return in;
	}

	public void setIn(ArrayList<RefData> in) {
		this.in = in;
	}

	public ArrayList<RefData> getMapped() {
		return mapped;
	}

	public void setMapped(ArrayList<RefData> mapped) {
		this.mapped = mapped;
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
}