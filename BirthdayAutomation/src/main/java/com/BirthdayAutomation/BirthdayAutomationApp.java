package com.BirthdayAutomation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BirthdayAutomationApp {
	static String toMailAdd ;
	static String Name ;
	EmailUtility emailUtility = new EmailUtility() ;



	// Creating Method for reading Database 
	public String readDataBase(String dbnm , String dbUnm , String dbPwd)
	{

		Connection connection = null;
		try {
			//forName() of Class class which is a static method and is used to register the drivers.
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Creating Connection
			connection = DriverManager.getConnection(dbnm , dbUnm ,dbPwd);

			// dbNm is database
			// dbUnm is name of database
			// dbPwd is password of database

			Statement statement;
			// Creating Statement
			statement = connection.createStatement();
			ResultSet resultSet;
			// execute the query using executeQuery() method that returns the object of ResultSet.
			resultSet = statement.executeQuery("Select * From Friends Where DATE_FORMAT(DOB,'%m-%d') = DATE_FORMAT(NOW(),'%m-%d') ;;");
			// Applying Loop for getting data from db
			while (resultSet.next()) {
				toMailAdd = resultSet.getString("GmailId") ;
				emailUtility.setMailServerProperties();
				Name = resultSet.getString("Name").trim();
				emailUtility.createEmailMessage(Name) ;
				emailUtility.sendMail();

				System.out.println(Name  + "   " + toMailAdd);
			}
			resultSet.close();
			statement.close();
			connection.close();
		}
		catch (Exception exception) {
			System.out.println(exception);
		}
		return Name ;

	}

	public static void main(String[] args) {
		BirthdayAutomationApp birthdayU = new BirthdayAutomationApp() ;
		birthdayU.readDataBase("jdbc:mysql://localhost:3306/automation" , "root" , "root") ;
		

	}
}
