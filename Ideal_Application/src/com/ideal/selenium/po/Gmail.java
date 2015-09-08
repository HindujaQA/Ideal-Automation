package com.ideal.selenium.po;


import com.ideal.selenium.basePage.page;

import java.util.*;

import javax.mail.*;

import org.apache.commons.lang3.time.DateUtils;

public class Gmail extends page {
	static String Gmailid ="publicportal3@gmail.com";
	static String password="Defiance123";
			public int totalmailcount(){
				int total =0;
				Properties props = new Properties();
		        props.setProperty("mail.store.protocol", "imaps");
				//props.put("mail.store.protocol", "smtp.yahoomail.com");
		        try {
		            Session session = Session.getInstance(props, null);
		            Store store = session.getStore();
		            //store.connect(host, user, password);
		            store.connect("imap.gmail.com", Gmailid,password);
		          //  store.connect("imap.mail.yahoo.com", "publicportal3@yahoo.com","Defaince123");
		            Folder inbox = store.getFolder("INBOX");
		            inbox.open(Folder.READ_ONLY);
		            total=inbox.getMessageCount();
		           }
		            catch (Exception mex) {
			            mex.printStackTrace();
			        }
		        return total;
		    }

public String getinsurancemailcontent() {
		    	String sub=null;
		        Properties props = new Properties();
		        props.setProperty("mail.store.protocol", "imaps");
		        try {
		            Session session = Session.getInstance(props, null);
		            Store store = session.getStore();
		            store.connect("imap.gmail.com", Gmailid, password);
		            Folder inbox = store.getFolder("INBOX");
		            inbox.open(Folder.READ_ONLY);
		            int count=inbox.getMessageCount();
		            for(int i=1;i<=count;i++){
		            	 sub=null;
				    	String content=null;
				    	String from =null;
				    	Message msg=null;
				    	Date d= null;
		            msg = inbox.getMessage(i);
		            Address[] in = msg.getFrom();
		           for (Address address : in) {
		//              System.out.println("FROM:" + address.toString());
		                from = address.toString();
		           }
		         	if(from.matches("RPX Insurance Team <insuranceservices@rpxcorp.com>")&&CONFG.getProperty("URL_HOME").equals("https://st-cp.rpxcorp.com/users/sign_in")){
		         		System.out.println(i);
		         		 d=msg.getSentDate();
				            Date today= new Date();
				            String date = d.toString();
				            String todaydate = today.toString();
				            String mdate = date.substring(0, 13);
				            String mtodaydate = todaydate.substring(0, 13);
				            Date addhr= DateUtils.addHours(today, 1);
				            String addhrdate = addhr.toString();
				            String addhrdatestring = addhrdate.substring(0, 13);
				            System.out.println(mdate);
				            System.out.println(mtodaydate);
				        Object o = msg.getContent();
			         	if(o.toString().contains("javax.mail.internet.MimeMultipart")){
			            Multipart mp = (Multipart) msg.getContent();	//getcontent
			            BodyPart bp = mp.getBodyPart(0);				//content bodypart
			           System.out.println(bp.getContentType());
			            content = bp.getContent().toString();
			            }else{
			            	}
				           int startindex =  content.indexOf("http");
				            int endindex = content.indexOf("/application");
				            if (mdate.equals(mtodaydate)||mdate.equals(addhrdatestring)) {
				            	 sub = content.substring(startindex, endindex+12);
							}
				          
				         // sub = msg.getContent().toString(); 
				          System.out.println(sub);
				            }else{
				            	//System.out.println("NOT AN INSURANCE MAIL");
				            }
		         	if(from.matches("RPX Insurances Team <insuranceservices@rpxcorp.com>")&&CONFG.getProperty("URL_HOME").equals("https://qa-cp.rpxcorp.com/users/sign_in")){
		         		System.out.println(i);
		         		 d=msg.getSentDate();
		         		 Date today= new Date();
				            String date = d.toString();
				            String todaydate = today.toString();
				            String mdate = date.substring(0, 13);
				            String mtodaydate = todaydate.substring(0, 13);
				            Date addhr= DateUtils.addHours(today, 1);
				            String addhrdate = addhr.toString();
				            String addhrdatestring = addhrdate.substring(0, 13);
		         		Object o = msg.getContent();
			         	if(o.toString().contains("javax.mail.internet.MimeMultipart")){
			            Multipart mp = (Multipart) msg.getContent();	//getcontent
			            BodyPart bp = mp.getBodyPart(0);				//content bodypart
			           System.out.println(bp.getContentType());
			            content = bp.getContent().toString();
			            }else{
			            	}
				           int startindex =  content.indexOf("http");
				            int endindex = content.indexOf("/insurances");
				            if (mdate.equals(mtodaydate)||mdate.equals(addhrdatestring)) {
				            	 sub = content.substring(startindex, endindex+10);
							}
				          
				         // sub = msg.getContent().toString(); 
				          System.out.println(sub);
				            }else{
				            	//System.out.println("NOT AN INSURANCE MAIL");
				            }
		            }    	
		        }
		            catch (Exception mex) {
			            mex.printStackTrace();
			        }
		        return sub;
		    }
		    
public boolean chkmailreceived() {
		    	boolean flag=false;
		    	int mailcount=0;
		        Properties props = new Properties();
		        props.setProperty("mail.store.protocol", "imaps");
		        try {
		            Session session = Session.getInstance(props, null);
		            Store store = session.getStore();
		            store.connect("imap.gmail.com", Gmailid, password);
		            Folder inbox = store.getFolder("INBOX");
		            inbox.open(Folder.READ_ONLY);
		            int count=inbox.getMessageCount();
		            for(int i=1;i<=count;i++){
		            	String sub=null;
				    	String content=null;
				    	String from =null;
				    	Message msg=null;
				    	Date d= null;
		            msg = inbox.getMessage(i);
		            Address[] in = msg.getFrom();
		           for (Address address : in) {
		//              System.out.println("FROM:" + address.toString());
		                from = address.toString();
		           }
		         	if(from.matches("RPX Insurance Team <insuranceservices@rpxcorp.com>")&&CONFG.getProperty("URL_HOME").equals("https://st-cp.rpxcorp.com/users/sign_in")){
		         		System.out.println(i);
		         		mailcount=1;
		         		 d=msg.getSentDate();
				            Date today= new Date();
				            String date = d.toString();
				            String todaydate = today.toString();
				            String mdate = date.substring(0, 13);
				            String mtodaydate = todaydate.substring(0, 13);
				            Date addhr= DateUtils.addHours(today, 1);
				            String addhrdate = addhr.toString();
				            String addhrdatestring = addhrdate.substring(0, 13);
				            /*System.out.println(mdate);
				            System.out.println(mtodaydate);
				            System.out.println(addhrdatestring);*/
				       if (mdate.equals(mtodaydate)||mdate.equals(addhrdatestring)) {
						flag=true;
					}else{
						flag=false;
					}
				            }else{
				            	//System.out.println("NOT AN INSURANCE MAIL");
				            }
		         	if(from.matches("RPX Insurances Team <insuranceservices@rpxcorp.com>")&&CONFG.getProperty("URL_HOME").equals("https://qa-cp.rpxcorp.com/users/sign_in")){
		         		System.out.println(i);
		         		mailcount=2;
		         		 d=msg.getSentDate();
		         		 Date today= new Date();
				            String date = d.toString();
				            String todaydate = today.toString();
				            String mdate = date.substring(0, 13);
				            String mtodaydate = todaydate.substring(0, 13);
				            Date addhr= DateUtils.addHours(today, 1);
				            String addhrdate = addhr.toString();
				            String addhrdatestring = addhrdate.substring(0, 13);
				            /*System.out.println(mdate);
				            System.out.println(mtodaydate);*/
				       if (mdate.equals(mtodaydate)||mdate.equals(addhrdatestring)) {
						flag=true;
					}else{
						flag=false;
					}
		         	}
				       else{
				            	//System.out.println("NOT AN INSURANCE MAIL");
				            }
		            }  
		            if(mailcount==0){
		            	flag=false;
		            }
		        }
		            catch (Exception mex) {
			            mex.printStackTrace();
			        }
		        return flag;
		    }   
		    
		    public Date getmaildate(int mailnumber) {
		    	Date d= null;
		        Properties props = new Properties();
		        props.setProperty("mail.store.protocol", "imaps");
		        try {
		            Session session = Session.getInstance(props, null);
		            Store store = session.getStore();
		            store.connect("imap.gmail.com", Gmailid, password);
		            Folder inbox = store.getFolder("INBOX");
		            inbox.open(Folder.READ_ONLY);
		            int count=inbox.getMessageCount();
		            Message msg = inbox.getMessage(count+1-mailnumber);
		            Multipart mp = (Multipart) msg.getContent();
		           // System.out.println(mp.getCount());
		            d= msg.getSentDate();
		             } catch (Exception mex) {
		            mex.printStackTrace();
		        }
		        return d;
		    }
		        
		    public String getmailsubject(int mailnumber) {
		    	String sub= null;
		        Properties props = new Properties();
		        props.setProperty("mail.store.protocol", "imaps");
		        try {
		            Session session = Session.getInstance(props, null);
		            Store store = session.getStore();
		            store.connect("imap.gmail.com", Gmailid, password);
		            Folder inbox = store.getFolder("INBOX");
		            inbox.open(Folder.READ_ONLY);
		            int count=inbox.getMessageCount();
		            Message msg = inbox.getMessage(count+1-mailnumber);
		            sub= msg.getSubject();
		            } catch (Exception mex) {
		            mex.printStackTrace();
		        }
		        return sub;
		    }
		     
		    public String getmailcontent(int mailnumber) {
		    	String content= null;
		        Properties props = new Properties();
		        props.setProperty("mail.store.protocol", "imaps");
		        try {
		            Session session = Session.getInstance(props, null);
		            Store store = session.getStore();
		          store.connect("imap.gmail.com", Gmailid, password);
		          //  store.connect("imap.yahoo.com", "publicportal6@yahoo.com","Defaince123");
		            Folder inbox = store.getFolder("INBOX");
		            inbox.open(Folder.READ_ONLY);
		            int count=inbox.getMessageCount();
		            Message msg = inbox.getMessage(count+1-mailnumber);
		            Multipart mp = (Multipart) msg.getContent();
		            BodyPart bp = mp.getBodyPart(0);
		            System.out.println(bp.getContentType());
		            content = bp.getContent().toString();
		            /*int startindex =  content.indexOf("http");
		            int endindex = content.indexOf("/application");
		            String sub = content.substring(startindex, endindex+12);*/
		            
		            } catch (Exception mex) {
		            mex.printStackTrace();
		        }
		        return content;
		    }
		    
		    public String getinsurancemailconten(int mailnumber) {
		    	String sub= null;
		        Properties props = new Properties();
		        props.setProperty("mail.store.protocol", "imaps");
		        try {
		            Session session = Session.getInstance(props, null);
		            Store store = session.getStore();
		            store.connect("imap.gmail.com", Gmailid, password);
		            Folder inbox = store.getFolder("INBOX");
		            inbox.open(Folder.READ_ONLY);
		            int count=inbox.getMessageCount();
		            Message msg = inbox.getMessage(count+1-mailnumber);
		            Multipart mp = (Multipart) msg.getContent();
		            BodyPart bp = mp.getBodyPart(0);
		            System.out.println(bp.getContentType());
		            sub = bp.getContent().toString();
		           /* int startindex =  content.indexOf("http");
		            int endindex = content.indexOf("/application");
		          //  sub = content.substring(startindex, endindex+12);
*/		         // sub = msg.getContent().toString();  
		            } catch (Exception mex) {
		            mex.printStackTrace();
		        }
		        return sub;
		    }
		
	}

