package com.mymemory.interfaces;

public interface Data<K>{
	public String getObjectAsString();
	public K getObjectFromString(String s, Class<K> c);
	public K getObject();
	public void setObject(K k);
}
