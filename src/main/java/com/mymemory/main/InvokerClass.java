package com.mymemory.main;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.mymemory.main.data.MemoryData;
import com.mymemory.main.data.ValueData;

public class InvokerClass {

	public static void main(String...arg) throws ParseException{
		
		MemoryData md = new MemoryData();
		md.setKey("FirstKey");
		md.setHits(10);
		ArrayList<String> aa = new ArrayList<String>();
		aa.add(new Timestamp((new SimpleDateFormat("dd-MMM-yy").parse("08-MAY-15")).getTime()).toString());
		aa.add(new Timestamp((new SimpleDateFormat("dd-MMM-YY").parse("10-JUN-15")).getTime()).toString());
		aa.add(new Timestamp((new SimpleDateFormat("dd-MMM-yy").parse("21-JUL-15")).getTime()).toString());
		md.setLastaccessed(aa);
		
		ArrayList<ValueData> vdl = new ArrayList<ValueData>();
		
		ValueData vd = new ValueData();
		vd.setData("Key Data 2");
		vd.setLastaccessed(aa);
		vd.setHits(10);
		Map<String,Integer> mm = new HashMap<String,Integer>();
		mm.put("meaning", 1);
		mm.put("data", 10);
		mm.put("junk", 15);
		vd.setMatchcount(mm);
		vdl.add(vd);
		
		vd = new ValueData();
		vd.setData("Key Data 1");
		vd.setLastaccessed(aa);
		vd.setHits(10);
		mm = new HashMap<String,Integer>();
		mm.put("meaning", 1);
		mm.put("data", 10);
		mm.put("junk", 15);
		vd.setMatchcount(mm);
		vdl.add(vd);
		
		md.setValue(vdl);
		
		ObjectMapper om = new ObjectMapper();
		try{
			String sss = om.writeValueAsString(md);
			System.out.println(sss);
		}catch(Exception e){
			System.out.println("Exception in converting to json ");
			e.printStackTrace();
		}
		
	}
}
