package com.ideal.selenium.po;


import com.ideal.selenium.basePage.page;

import java.util.*;

import javax.mail.*;

public class EMAIL  {
	static String Gmailid ="publicportal3@gmail.com";
	static String password="Defiance123";
	String Gmailid1 ="babu.panju@gmail.com";
	String password1="laughter";
	static int alertmailnumer = 0;
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
		        
		    public String getmailsubject(String mailid,int mailnumber) {
		    	String sub= null;
		        Properties props = new Properties();
		        props.setProperty("mail.store.protocol", "imaps");
		        try {
		            Session session = Session.getInstance(props, null);
		            Store store = session.getStore();
		            store.connect("imap.gmail.com", mailid, password);
		            Folder inbox = store.getFolder("INBOX");
		            inbox.open(Folder.READ_ONLY);
		            int count=inbox.getMessageCount();
		            Message msg = inbox.getMessage(mailnumber);
		            sub= msg.getSubject();
		            } catch (Exception mex) {
		            mex.printStackTrace();
		        }
		        return sub;
		    }
		    
		    
		    public String getmailcontent(String yourmailid,int mailnumber) {
		    	String content= null;
		        Properties props = new Properties();
		        props.setProperty("mail.store.protocol", "imaps");
		        try {
		            Session session = Session.getInstance(props, null);
		            Store store = session.getStore();
		          store.connect("imap.gmail.com", yourmailid, password);
		          //  store.connect("imap.yahoo.com", "publicportal6@yahoo.com","Defaince123");
		            Folder inbox = store.getFolder("INBOX");
		            inbox.open(Folder.READ_ONLY);
		            int count=inbox.getMessageCount();
		            Message msg = inbox.getMessage(count+1-mailnumber);
		            Object o = msg.getContent();
		            System.out.println(o);
		         	if(o.toString().contains("javax.mail.internet.MimeMultipart")){
		         		System.out.println("inside ooo");
		            Multipart mp = (Multipart) msg.getContent();	//getcontent
		            BodyPart bp = mp.getBodyPart(0);				//content bodypart
		           System.out.println(bp.getContentType());
		            content = bp.getContent().toString();
		            int startindex =  content.indexOf("http");
		            int endindex = content.indexOf("If");
		          //  System.out.println(endindex);
		          //  if (mdate.equals(mtodaydate)||mdate.equals(addhrdatestring)) {
		            	 content = content.substring(startindex, endindex);
		            }else{
		            	Multipart mp = (Multipart) msg.getContent();
			            BodyPart bp = mp.getBodyPart(0);
			            System.out.println(bp.getContentType());
			            content = bp.getContent().toString();
		            	int startindex =  content.indexOf("http");
			            int endindex = content.indexOf("/activate");
			          //  if (mdate.equals(mtodaydate)||mdate.equals(addhrdatestring)) {
			            	 content = content.substring(startindex, endindex+10);
					//	}
		            	}
			           
		            
		            /*int startindex =  content.indexOf("http");
		            int endindex = content.indexOf("/application");
		            String sub = content.substring(startindex, endindex+12);*/
		            
		            } catch (Exception mex) {
		            mex.printStackTrace();
		        }
		        return content;
		    }
		       
		    public String getmailfrom(int mailnumber) {
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
		            for(int i=1;i<=count;i++){
		            	
				    	String from =null;
				    	Message msg=null;
				    	Date d= null;
		            msg = inbox.getMessage(i);
		            Address[] in = msg.getFrom();
		           for (Address address : in) {
		              System.out.println("FROM:" + address.toString());
		                from = address.toString();
		           }
		            
		            }
		        }catch (Exception mex) {
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
		          store.connect("imap.gmail.com", Gmailid1, password1);
		          //  store.connect("imap.yahoo.com", "publicportal6@yahoo.com","Defaince123");
		            Folder inbox = store.getFolder("INBOX");
		            inbox.open(Folder.READ_ONLY);
		            int count=inbox.getMessageCount();
		            Message msg = inbox.getMessage(mailnumber);
		            Object o = msg.getContent();
		            System.out.println(o);
		         	if(o.toString().contains("javax.mail.internet.MimeMultipart")){
		         		System.out.println("inside ooo");
		            Multipart mp = (Multipart) msg.getContent();	//getcontent
		            BodyPart bp = mp.getBodyPart(0);				//content bodypart
		           System.out.println(bp.getContentType());
		            content = bp.getContent().toString();
		            }else{
		            	Multipart mp = (Multipart) msg.getContent();
			            BodyPart bp = mp.getBodyPart(0);
			            System.out.println(bp.getContentType());
			            content = bp.getContent().toString();
		            	}
			           
		            
		            /*int startindex =  content.indexOf("http");
		            int endindex = content.indexOf("/application");
		            String sub = content.substring(startindex, endindex+12);*/
		            
		            } catch (Exception mex) {
		            mex.printStackTrace();
		        }
		        return content;
		    }
		    
		    
public boolean chkalertmailreceived(String yourmailid , String mailid) {
	
		    	boolean flag=false;
		    	int mailcount=0;
		        Properties props = new Properties();
		        props.setProperty("mail.store.protocol", "imaps");
		        try {
		            Session session = Session.getInstance(props, null);
		            Store store = session.getStore();
		            store.connect("imap.gmail.com", yourmailid, password);
		            Folder inbox = store.getFolder("INBOX");
		            inbox.open(Folder.READ_ONLY);
		            int count=inbox.getMessageCount();
		    //        System.out.println(count);
		            if(count>50){
		            for(int i=1;i<10;i++){
		     //       	System.out.println("count inside loop"+count);
		           	System.out.println("greater than 50 mail");
		            	String sub=null;
				    	String content=null;
				    	String from =null;
				    	Message msg=null;
				    	Date d= null;
				    	int booleanvalue=0;
		            msg = inbox.getMessage(count);
		            Address[] in = msg.getFrom();
		            Address address = in[0];
		          // for (Address address : in) {
		              System.out.println("FROM:" + address.toString());
		                from = address.toString();
		           				 
		         	//if(from.matches("RPX Daily Litigation Alert <litigationalert@rpxcorp.com>")){
		              if(from.matches(mailid)){
		         	//	System.out.println("alert mail came");
		         		//System.out.println(i);
		         		mailcount=1;
		         		 d=msg.getSentDate();
		         //		 System.out.println(i+"mail date"+d);
				            Date today= new Date();
				            String date = d.toString();
				            String todaydate = today.toString();
				            String mdate = date.substring(0, 11);
				            String mtodaydate = todaydate.substring(0, 11);
				           // Date addhr= DateUtils.setDays(today, 06);
				          // String addhrdate = addhr.toString();
				          // String addhrdatestring = addhrdate.substring(0, 11);
				            /*System.out.println(mdate);
				            System.out.println(mtodaydate);
				            System.out.println(addhrdatestring);*/
				       if (mdate.equals(mtodaydate)) {
				    	   flag=true;
				    	   booleanvalue = 1;
				    	   alertmailnumer = count;
				//    	   System.out.println(count);
				    	   break;
					}else{
						flag=false;
					}
				            }else{
				   //         	System.out.println("alert mail not received");
				            	//flag=false;
				            }
		         	
		            if(mailcount==0){
		            	flag=false;
		            }
		            if(booleanvalue==1){
		            	flag=true;
		            }else{
		            	flag=false;
		            }
		            count = count-1;
		        }
		            }
		            else{
		            	for(int i=1;i<=10;i++){
		            		System.out.println("count value"+i+"is "+count);
			            	String sub=null;
					    	String content=null;
					    	String from =null;
					    	Message msg=null;
					    	Date d= null;
					    	int booleanvalue =0;
			            msg = inbox.getMessage(count);
			            Address[] in = msg.getFrom();
			           //for (Address address : in) {
			        //      System.out.println("FROM:" + address.toString());
			            Address address = in[0];
			                from = address.toString();
			         //  }				 
			           if(from.matches(mailid)){
				         	//	System.out.println("alert mail came");
				         		//System.out.println(i);
				         		mailcount=1;  //here mail count is set as 1 if no mail comes then it will return 0 and flag is fail
				         		 d=msg.getSentDate();
				         //		 System.out.println(i+"mail date"+d);
						            Date today= new Date();
						            String date = d.toString();
						            String todaydate = today.toString();
						            String mdate = date.substring(0, 11);
						            String mtodaydate = todaydate.substring(0, 11);
						          //  Date addhr= DateUtils.setDays(today,6);
						          // String addhrdate = addhr.toString();
						           //String addhrdatestring = addhrdate.substring(0, 11);
						            System.out.println("maildate"+mdate);
						           // System.out.println(mtodaydate);*/
						            System.out.println("date"+mtodaydate);
						       if (mdate.equals(mtodaydate)) {
						    	   flag=true;
						    	   booleanvalue = 1;
						    	   alertmailnumer = count;
						    	   System.out.println("alertmailcount"+count);
						    	//   System.out.println("inside method"+i);
						    	   break;
							}else{
								flag=false;
							}
						            }else{
						   //         	System.out.println("alert mail not received");
						            	//flag=false;
						            }
			           if(mailcount==0){
			            	flag=false;
			            }
			            if(booleanvalue==1){
			            	flag=true;
			            }else{
			            	flag=false;
			            }
			            count = count-1;
			        }
			            }
		            
		        }
		            catch (Exception mex) {
			            mex.printStackTrace();
			        }
		        return flag;
		   }

public int getalertmailnumber(){
	return alertmailnumer;
}

}		    

