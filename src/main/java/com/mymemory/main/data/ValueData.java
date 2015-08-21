package com.mymemory.main.data;

import java.util.ArrayList;
import java.util.Map;

public class ValueData {

	private String data;
	private int hits;
	private ArrayList<String> lastaccessed;
	private Map<String,Integer> matchcount;
	
	public void addLastAccessed(String s){
		if(lastaccessed==null){
			lastaccessed = new ArrayList<String>();
		}
		lastaccessed.add(s);
	}
	
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public ArrayList<String> getLastaccessed() {
		return lastaccessed;
	}
	public void setLastaccessed(ArrayList<String> lastaccessed) {
		this.lastaccessed = lastaccessed;
	}
	public Map<String, Integer> getMatchcount() {
		return matchcount;
	}
	public void setMatchcount(Map<String, Integer> matchcount) {
		this.matchcount = matchcount;
	}
}
