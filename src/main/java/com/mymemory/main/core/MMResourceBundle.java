package com.mymemory.main.core;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

public class MMResourceBundle{
	
	private Map<String,Object> lookup;
	private boolean flagSet = false;
	private String path;
	
	@SuppressWarnings({"unchecked", "rawtypes"})
    public MMResourceBundle(String path, boolean stream) throws IOException {
       this.path = path;
       if(stream)
    	   loadStream(new FileInputStream(path));
       else
    	   loadReader(new FileReader(path));
    }
	
	
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void loadStream(InputStream stream) throws IOException {
        Properties properties = new Properties();
        properties.load(stream);
        lookup = new HashMap(properties);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void loadReader(Reader reader) throws IOException {
        Properties properties = new Properties();
        properties.load(reader);
        lookup = new HashMap(properties);
    }

    private Object handleGetObject(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        return lookup.get(key);
    }
    
    public String getString(String key){
    	return (String) handleGetObject(key);
    }
    
    public void setKeyValue(String key, String value){
    	Object v = lookup.put(key, value);
    	if(v==null){
    		flagSet = true;
    	}else{
    		flagSet = true;
    	}
    }
    
    

    public Set<String> getKeys() {
    	return lookup.keySet();
    }

    protected Set<String> handleKeySet() {
        return lookup.keySet();
    }

}
