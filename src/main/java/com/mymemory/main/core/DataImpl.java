package com.mymemory.main.core;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.mymemory.interfaces.Data;

public class DataImpl<K> implements Data<K> {

	private K data;

	@Override
	public K getObjectFromString(String s, Class<K> c) {
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("Got object mapper "+mapper);
		try {
			data = mapper.readValue(s, c);
			return data;
		} catch (JsonGenerationException e) {
			System.err.println("Exception in json generation "+e);
			e.printStackTrace();

		} catch (JsonMappingException e) {
			System.err.println("Exception in json mapping "+e);
			e.printStackTrace();

		} catch (IOException e) {
			System.err.println("Exception in json IOException "+e);
			e.printStackTrace();

		}
		return null;
	}

	@Override
	public String getObjectAsString() {
		ObjectMapper om = new ObjectMapper();
		try{
			return om.writeValueAsString(data);
		}catch(Exception e){
			System.out.println("Exception in converting to json "+e);
		}
		return null;
	}
	
	@Override
	public K getObject() {
		return data;
	}

	@Override
	public void setObject(K k) {
		data = k;
	}
}
