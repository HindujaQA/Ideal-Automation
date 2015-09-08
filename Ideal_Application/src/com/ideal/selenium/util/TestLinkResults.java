package com.ideal.selenium.util;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.jamesmurty.utils.XMLBuilder;


public class TestLinkResults {

	
	public static void createXmlResults() {

		try {
			XMLBuilder builder = XMLBuilder.create("results");
			Properties outputProperties = new Properties();
			outputProperties.put(javax.xml.transform.OutputKeys.METHOD, "xml");			
			PrintWriter writer = new PrintWriter(new FileOutputStream("testLinkResults.xml"));
			builder.toWriter(writer, outputProperties);
			writer.close();
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {			
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {			
			e.printStackTrace();
		}

	}
	
	//Appending test case results
	public static void appendTestCaseResults(String tcId,String tcResult,String resultNotes){
		XMLBuilder builder2;
		
		try {
			Properties config = new Properties();
			String configPath = System.getProperty("user.dir")+"\\src\\com\\rpxcorp\\cp\\selenium\\config\\config.properties";
			FileInputStream inp = new FileInputStream(configPath);
			config.load(inp);
			
			builder2 = XMLBuilder.parse(new InputSource(new FileReader("testLinkResults.xml")));
			builder2.e("testcase")
					.a("external_id", tcId)
					.e("tester").t(config.getProperty("TESTLINK_USER")).up()
					.e("timestamp").t(getCurrentTime()).up()
					.e("result").t(tcResult).up()
					.e("notes").t(resultNotes).up()					
					.up();
			
			Properties outputProperties = new Properties();
			outputProperties.put(javax.xml.transform.OutputKeys.METHOD, "xml");			
						
			PrintWriter writer= new PrintWriter(new FileOutputStream("testLinkResults.xml"));
			builder2.toWriter(writer, outputProperties);
			writer.close();
			
		} catch (TransformerException e) {			
			e.printStackTrace();			
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (ParserConfigurationException e) {			
			e.printStackTrace();
		} catch (SAXException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		
	}
	
	//Adding time stamp for test case results
	private static String getCurrentTime(){
		Calendar calendar = new GregorianCalendar();
		Date date = calendar.getTime();
		//2014-09-25 07:01:00
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String newDate = df.format(date);		
		return newDate;
	}
	
		
	

}
