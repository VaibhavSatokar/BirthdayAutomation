package com.BirthdayAutomation;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailUtility {
	Properties properties ;
	MimeMessage mmessage ;
	Session session ;

	public void setMailServerProperties()
	{
		properties = System.getProperties() ;
		properties.put("mail.smtp.port", "587") ;
		properties.put("mail.smtp.auth", "true") ;
		properties.put("mail.smtp.starttls.enable", "true") ;
	}

	public void createEmailMessage(String Name) throws AddressException, MessagingException
	{
		String[] toMails = {BirthdayAutomationApp.toMailAdd} ;
		String mailSubject = "Happy Birthday 🎉🎉🎉🎉" ;
		session = Session.getInstance(properties, new javax.mail.Authenticator() {

			//override the getPasswordAuthentication method
			@SuppressWarnings("unused")
			protected PasswordAuthentication
			getPasswordAuthentication(String fromUser, String fromPwd) {

				return new PasswordAuthentication(fromUser , fromPwd);
			}
		});
		
		mmessage = new MimeMessage(session) ;
		for(int i = 0 ; i< toMails.length ; i++) {
			mmessage.addRecipient(Message.RecipientType.TO,new InternetAddress(toMails[i]));	
		}
		mmessage.setSubject(mailSubject);
			
		  MimeMultipart multipart = new MimeMultipart("related") ; BodyPart bodypart =
		  new MimeBodyPart() ;
		  
		  bodypart.setText(mailSubject);
		  
		  
		  String htmlText = "<!DOCTYPE html>\r\n"
		  		+ "<html>\r\n"
		  		+ "<head>\r\n"
		  		+ "	<meta charset=\"utf-8\">\r\n"
		  		+ "	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
		  		+ "	<title></title>\r\n"
		  		+ "	<style type=\"text/css\">\r\n"
		  		+ "		body {\r\n"
		  		+ " margin: 0;\r\n"
		  		+ "  padding: 16px;\r\n"
		  		+ "  background-color: #512DA8;\r\n"
		  		+ "}\r\n"
		  		+ "\r\n"
		  		+ ".birthday-card {\r\n"
		  		+ "  margin: 40px auto;\r\n"
		  		+ "  padding: 16px;\r\n"
		  		+ "  max-width: 400px;\r\n"
		  		+ "  background-color: white;\r\n"
		  		+ "  text-align: center;\r\n"
		  		+ "  box-shadow: 0 24px 40px -8px #311B92;\r\n"
		  		+ "}\r\n"
		  		+ ".birthday-card img {\r\n"
		  		+ "  width: 100%;\r\n"
		  		+ "}\r\n"
		  		+ "\r\n"
		  		+ ".source-link {\r\n"
		  		+ "  text-align: center;\r\n"
		  		+ "  color: #311B92;\r\n"
		  		+ "  text-decoration: underline;\r\n"
		  		+ "}\r\n"
		  		+ "\r\n"
		  		+ ".source-link a {\r\n"
		  		+ "  color: #311B92;\r\n"
		  		+ "  text-decoration: none;\r\n"
		  		+ "}\r\n"
		  		+ "\r\n"
		  		+ "	</style>\r\n"
		  		+ "</head>\r\n"
		  		+ "<body>\r\n"
		  		+ "<div class=\"birthday-card\">\r\n"
		  		+ "    \r\n"
		  		+ "    <img src=\"https://image.freepik.com/free-vector/surprise-theme-happy-birthday-card-illustration_1344-199.jpg\"  alt=\"Birthday image\">\r\n"
		  		+ "    <h1>Happy Birthday <i><u><mark>"+ Name +"</mark></u></i></h1>\r\n"
		  		+ "    \r\n"
		  		+ "    <h3>Hope you are having a great day.</h3>\r\n"
		  		+ "    \r\n"
		  		+ "  </div>\r\n"
		  		+ "  <h6 class=\"source-link\"><Mail Send By Java</h6>\r\n"
		  		+ "</body>\r\n"
		  		+ "</html>" ;
		  
		  bodypart.setContent(htmlText, "text/html"); 
		  multipart.addBodyPart(bodypart) ;
		  
		  mmessage.setContent(multipart) ;
		 

	}

	public void sendMail() throws MessagingException
	{
		String host = "smtp.gmail.com";
		String fromUser = "vrsatokar2000@gmail.com" ;
		String fromUserPwd = "lekiunqzbzzfmdxj" ;
		Transport transport = session.getTransport("smtp") ;
		transport.connect(host , fromUser, fromUserPwd );
		transport.sendMessage(mmessage, mmessage.getAllRecipients());
		transport.close();
		System.out.println("Email sent Successfully......");


	}



}
