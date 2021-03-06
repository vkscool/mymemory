package com.mymemory.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;

import com.mymemory.interfaces.Data;
import com.mymemory.main.core.DataImpl;

public class TestJSON {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*String data = "{\"key\":\"FirstKey\",\"hits\":10,\"lastaccessed\":[\"2015-05-08 00:00:00.0\",\"2014-12-28 00:00:00.0\",\"2015-07-21 00:00:00.0\"],\"value\":[{\"data\":\"Key Data 2\",\"hits\":10,\"lastaccessed\":[\"2015-05-08 00:00:00.0\",\"2014-12-28 00:00:00.0\",\"2015-07-21 00:00:00.0\"],\"matchcount\":{\"data\":10,\"meaning\":1,\"junk\":15}},{\"data\":\"Key Data 1\",\"hits\":10,\"lastaccessed\":[\"2015-05-08 00:00:00.0\",\"2014-12-28 00:00:00.0\",\"2015-07-21 00:00:00.0\"],\"matchcount\":{\"data\":10,\"meaning\":1,\"junk\":15}}]}";
		
		System.out.println("Data is \n"+data);
		
		Data<ExampleData> d = new DataImpl<ExampleData>();
		
		ExampleData md = d.getObjectFromString(data, ExampleData.class);
		
		System.out.println("Key "+md.getKey());
		System.out.println("Accessed Count "+md.getLastaccessed().size());
		System.out.println("Hits "+md.getHits());
		System.out.println("Datas "+md.getValue().size());*/
		
		//URL s1 = TestJSON.class.getResource("");
		URL s2 = TestJSON.class.getClassLoader().getResource("vks.json");
		System.out.println(s2);
		
		try {
			RandomAccessFile file = new RandomAccessFile(s2.getPath(), "rw");
			byte[] bytes = new byte[1024];
			String s = file.readLine();
			while(s!=null){
				long pointer = file.getFilePointer();
				System.out.println("data:"+s + " pointer:"+pointer);
				s = file.readLine();
			}
			file.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
