package com.mymemory.main;

import java.text.ParseException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InvokerClass {

	private static final Logger logger = LogManager.getLogger(InvokerClass.class);
	
	public static void main(String...arg) throws ParseException{
		
		
		
		/*ObjectMapper om = new ObjectMapper();

		try {
			String sss = om.writeValueAsString(md);
			System.out.println(sss);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		logger.debug("Now moving on");
		
	}
}
