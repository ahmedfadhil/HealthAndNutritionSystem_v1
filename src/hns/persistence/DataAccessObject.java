package hns.persistence;

import hns.enums.ActivityLevel;
import hns.enums.Gender;
import hns.objects.Activity;
import hns.objects.Date;
import hns.objects.Food;
import hns.objects.Journal;
import hns.objects.Patient;
import hns.objects.Record;

import java.sql.SQLWarning;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class DataAccessObject implements DataAccess{
	
	private Statement st1, st2, st3, st4, st5, st6, st7, st8, st9, st10, st11, st12;
	private Connection c1;
	private ResultSet rs1, rs2, rs3, rs4;

	private String dbName;
	private String dbType;

	private String cmdString;
	private int updateCount;
	private String result;
	private static String EOF = "  ";

	public DataAccessObject(String dbName){
		this.dbName = dbName;
	}
		
	public void open(String dbName){
		
		String url;
		try{
			
			// Setup for HSQL
			dbType = "HSQL";
			Class.forName("org.hsqldb.jdbcDriver").newInstance();
			url = "jdbc:hsqldb:database/" + dbName;  // stored on disk mode
			c1 = DriverManager.getConnection(url, "SA", "");
			st1 = c1.createStatement();
			st2 = c1.createStatement();
			st3 = c1.createStatement();
			st4 = c1.createStatement();
			st5 = c1.createStatement();
			st6 = c1.createStatement();
			st7 = c1.createStatement();
			st8 = c1.createStatement();
			st9 = c1.createStatement();
			st10 = c1.createStatement();
			st11 = c1.createStatement();
			st12 = c1.createStatement();
		}
		catch (Exception e){
			
			processSQLError(e);
		}
		
		System.out.println("Opened " + dbType + " database " + dbName);
	
	}
	
	public void close(){
		
		try{
			cmdString = "SHUTDOWN COMPACT";
			rs1 = st1.executeQuery(cmdString);
			c1.close();
		}
		catch(Exception e){
			
			processSQLError(e);
		}
		
		System.out.println("Closed " + dbType + " database " + dbName);
	}
	
	public LinkedList<Food> extractFood(ResultSet rs){
		
		LinkedList<Food> foodList = new LinkedList<Food>();
		Food food = null;
		String name = EOF;
		int calories;
		int weightGram;
		
		try{
			//move down rows
			while(rs.next()){
				name = rs.getString("Name");
				calories = rs.getInt("Calories");
				weightGram = rs.getInt("Weight_Gram");

				food = new Food(name, calories, weightGram);
				foodList.add(food);

			}
			rs.close();
			
		}
		catch(Exception e){
			processSQLError(e);
			
		}
		return foodList;
	}

	public Food searchFood(String name){
		
		LinkedList<Food> foodList = null;
		Food food = null;
		
		try{
			Statement st = c1.createStatement();
			ResultSet rs;
			
			cmdString = "Select * from Food where Name = '" + name.toUpperCase() + "'";
			rs = st.executeQuery(cmdString);
			
			foodList = extractFood(rs);
			food = foodList.getFirst();	//search item will be the only one in the list
			
		}
		catch(Exception e){
			processSQLError(e);
		}

		return food;
		
	}
	
	public LinkedList<Food> getFoodList(String searchQuery){

		LinkedList<Food> foodList = null;
		
		try{
			Statement st = c1.createStatement();
			ResultSet rs;
			
			//LIKE is case sensitive
			cmdString = "Select * from Food";
			if(searchQuery != null){
				cmdString += " where Name like '%" + searchQuery.toUpperCase() + "%'";
				
			}
			rs = st.executeQuery(cmdString);
			
			foodList = extractFood(rs);
		}
		catch(Exception e){
			processSQLError(e);
		}

		return foodList;
	}
	
	public LinkedList<Activity> extractActivity(ResultSet rs){
		
		LinkedList<Activity> activityList = new LinkedList<Activity>();
		Activity activity = null;
		
		String name = EOF;
		int caloriesBurned;
		int durationMinutes;

		try{
			//move down rows
			while(rs.next()){
				name = rs.getString("Name");
				caloriesBurned = rs.getInt("Calories_Burned");
				durationMinutes = rs.getInt("Duration_Minutes");

				activity = new Activity(name, caloriesBurned, durationMinutes);
				activityList.add(activity);
			}
			rs.close();
			
		}
		catch(Exception e){
			processSQLError(e);
			
		}
		
		return activityList;
	}
	
	public Activity searchActivity(String name) {
		
		LinkedList<Activity> activityList = null;
		Activity activity = null;
		
		try{
			Statement st = c1.createStatement();
			ResultSet rs;
			
			cmdString = "Select * from Activity where Name = '" + name.toUpperCase() + "'";
			rs = st.executeQuery(cmdString);
			
			activityList = extractActivity(rs);
			activity = activityList.getFirst();
			
		}
		catch(Exception e){
			processSQLError(e);
		}

		return activity;
	}
	
	public LinkedList<Activity> getActivityList(String searchQuery) {

		LinkedList<Activity> activityList = null;

		try{
			Statement st = c1.createStatement();
			ResultSet rs;
			
			//LIKE is case sensitive
			cmdString = "Select * from Activity";
			if(searchQuery != null){
				cmdString += " where Name like '%" + searchQuery.toUpperCase() + "%'";
				
			}
			rs = st.executeQuery(cmdString);
			
			activityList = extractActivity(rs);
			
		}
		catch(Exception e){
			processSQLError(e);
		}

		return activityList;
	}
	
	public String insertPatient(Patient patient){
		
		result = null;
		try{
			
			updateCount = st4.executeUpdate
			(
				"INSERT INTO PATIENTS " +
				"(USERID, " +
				"JOURNAL_ID, " +
				"LAST_NAME, " +
				"FIRST_NAME, " +
				"AGE, " +
				"WEIGHT, " +
				"HEIGHT, " +
				"GENDER, " +
				"ACTIVITY_LEVEL) " +
				"VALUES(" +
				patient.getUserID() +
				"," + patient.getJournalID() +
				",'" + patient.getLastName() + "'" + 
				",'" + patient.getFirstName() + "'" + 
				"," + patient.getAge() + 
				"," + patient.getWeight() +
				"," + patient.getHeight() +
				",'" + patient.getGender().toString() + "'" + 
				",'" + patient.getActivityLevel().toString() + "')"
			);
			result = checkWarning(st4, updateCount);

		}
		catch(Exception e){
			result = processSQLError(e);
		}
		
		return result;
	}
	
	public LinkedList<Patient> getPatientList(){
		
		//need to restore journal and records
		LinkedList<Patient> patientList = new LinkedList<Patient>();
		Patient patient;

		int userID;
		int journalID;
		String lastName = EOF;
		String firstName = EOF;
		int age;
		int weight;
		int height;
		String nameGender;
		String nameActivityLevel;
		Gender gender;
		ActivityLevel activityLevel;
		
		try{
			cmdString = "select * from patients";
			rs2 = st3.executeQuery(cmdString);
			
		}
		catch(Exception e){
			processSQLError(e);
			
		}
		try{
			while(rs2.next()){
				userID = rs2.getInt("UserID");
				journalID = rs2.getInt("Journal_ID");
				lastName = rs2.getString("Last_Name");
				firstName = rs2.getString("First_Name");
				age = rs2.getInt("Age");
				weight = rs2.getInt("Weight");
				height = rs2.getInt("Height");
				nameGender = rs2.getString("Gender");
				if(nameGender.equals("Male")){
					gender = Gender.MALE;
				}
				else if(nameGender.equals("Female")){
					gender = Gender.FEMALE;
				}
				else{
					gender = Gender.UNSPECIFIED;
				}
				nameActivityLevel = rs2.getString("Activity_Level");
				if(nameActivityLevel.equals("Sedentary")){
					activityLevel = ActivityLevel.SEDENTARY;
				}
				else if(nameActivityLevel.equals("Light")){
					activityLevel = ActivityLevel.LIGHT;
				}
				else if(nameActivityLevel.equals("Moderate")){
					activityLevel = ActivityLevel.MODERATE;
				}
				else if(nameActivityLevel.equals("Very")){
					activityLevel = ActivityLevel.VERY;
				}
				else if(nameActivityLevel.equals("Extra")){
					activityLevel = ActivityLevel.EXTRA;
				}
				else{
					activityLevel = ActivityLevel.UNSPECIFIED;
				}
				patient = new Patient(userID, journalID, lastName, firstName, age, weight, height, gender, activityLevel);
				patientList.add(patient);
				
				extractRecords(patient);

			}
			
			rs2.close();
		}
		catch(Exception e){
			processSQLError(e);
		}
		
		
		return patientList;

	}
	
	public String updatePatient(Patient patient){
		
		result = null;
		try{
			updateCount = st6.executeUpdate
			(
				"UPDATE PATIENTS" +
				" SET LAST_NAME='" + patient.getLastName() + "'" +
				", FIRST_NAME='" + patient.getFirstName() + "'" +
				", AGE=" + patient.getAge() +
				", HEIGHT=" + patient.getHeight() +
				", WEIGHT=" + patient.getWeight() +
				" WHERE USERID=" + patient.getUserID()
			);
			result = checkWarning(st6, updateCount);
		}
		catch(Exception e){
			result = processSQLError(e);
		}
		
		return result;
	}
	
	public String updateRecordWeight(Journal journal, Record record){
		
		result = null;
		try{
			updateCount = st12.executeUpdate
			(
				"UPDATE RECORD" +
				" SET WEIGHT=" + record.getWeight() +
				" WHERE CALENDAR_DATE='" + record.getDateDelimited() + "'" +
				" AND JOURNAL_ID=" + journal.getJournalID()
			);
			result = checkWarning(st12, updateCount);
		}
		catch(Exception e){
			result = processSQLError(e);
		}
		
		return result;
	}
	
	public void extractRecords(Patient patient){
		
		Journal journal = patient.getJournal();
		Record record;
		String date;
		int weight;
		
		try{
			cmdString = "select calendar_date, weight" +
		                " from record" +
		                " where record.journal_id = " + patient.getJournalID();
			
			rs3 = st10.executeQuery(cmdString);
		}
		catch(Exception e){
			processSQLError(e);
			
		}
		try{
			
			while(rs3.next()){
				date = rs3.getString("calendar_date");
				
				StringTokenizer st = new StringTokenizer(date, "-");
				int year = Integer.parseInt(st.nextToken());
				int month = Integer.parseInt(st.nextToken());
				int day = Integer.parseInt(st.nextToken());
				weight = rs3.getInt("weight");
				record = new Record(new Date(month, day, year), weight);
				journal.insertNewRecord(record);
				
				extractFoodRecord(journal, record);
				extractActivityRecord(journal, record);
				
			}

			rs3.close();
		}
		catch(Exception e){
			processSQLError(e);
		}
	}
	
	public void extractFoodRecord(Journal journal, Record record) {
		
		LinkedList<Food> foodList = new LinkedList<Food>();
		
		result = null;
		try{
			Statement st = c1.createStatement();
			ResultSet rs;
			cmdString = "select distinct food.name, food.calories, record_food.entry_id, record_food.weight_gram" +
						" from food, record_food, patients, record" + 
						" where food.name = record_food.name" +
						" and patients.journal_id = record.journal_id" +
						" and record_food.calendar_date = '" + record.getDateDelimited() + "'"+
						" and record_food.journal_id = " + journal.getJournalID();
			rs = st.executeQuery(cmdString);
			
			foodList = extractFood(rs);
			for(int i = 0; i < foodList.size(); i ++){
				record.AddToList(foodList.get(i));
			}
			
		}
		catch(Exception e){
			processSQLError(e);
		}
	}
	
	public void extractActivityRecord(Journal journal, Record record){
		
		String name;
		int caloriesBurned;
		int durationMinutes;
		Activity activity;
		
		result = null;
		try{
			cmdString = "select distinct activity.name, activity.calories_burned, record_activity.entry_id, record_activity.duration" +
						" from activity, record_activity, record, patients" +
						" where activity.name = record_activity.name" +
						" and patients.journal_id = record.journal_id" +
						" and record_activity.calendar_date = '" + record.getDateDelimited() + "'" +
						" and record_activity.journal_id = " + journal.getJournalID();
			rs4 = st11.executeQuery(cmdString);
			
		}
		catch(Exception e){
			processSQLError(e);
		}
		
		//calling extractActivities doesn't seem to work
		try{
			while(rs4.next()){
				name = rs4.getString("Name");
				caloriesBurned = rs4.getInt("Calories_Burned");
				durationMinutes = rs4.getInt("Duration");
				
				activity = new Activity(name, caloriesBurned, durationMinutes);				
				record.AddToList(activity);
			}

			rs4.close();
		}
		
		catch(Exception e){
			processSQLError(e);
		}
		
	}

	public void insertJournal(Patient patient){
		result = null;
		try{
			updateCount = st5.executeUpdate(
				"INSERT INTO JOURNAL " + 
				"(JOURNAL_ID) " + 
				"VALUES(" +
				patient.getJournalID() + ")"
			);
			result = checkWarning(st5, updateCount);
		}
		catch(Exception e){
			result = processSQLError(e);
		}
		
	}
	
	public String insertNewRecord(Record record, Journal journal) {
		String date = record.getDateDelimited();

		result = null;
		try{
			updateCount = st9.executeUpdate
			(
				"INSERT INTO RECORD(CALENDAR_DATE, JOURNAL_ID, WEIGHT) VALUES(" +
				"'" + date + "'," + journal.getJournalID() + "," + record.getWeight() + ")"
			);
			result = checkWarning(st9, updateCount);
		}
		catch(Exception e){
			result = processSQLError(e);
		}
		return result;

	}

	public String insertFoodRecord(int recordEntryID, Patient patient, Record record, Food food) {
		String date = record.getDateDelimited();
		
		result = null;
		try{
			updateCount = st7.executeUpdate
			(
				"INSERT INTO RECORD_FOOD(ENTRY_ID, JOURNAL_ID, CALENDAR_DATE, NAME, WEIGHT_GRAM) VALUES(" +
				recordEntryID + "," + patient.getJournalID() + ",'" + date + "'" +
				",'" + food.getName() + "'," + food.getWeightGram() + ")"
			);
			result = checkWarning(st7, updateCount);
			
		}
		catch(Exception e){
			result = processSQLError(e);
		}
		return result;
	}
	
	
	public String insertActivityRecord(int recordEntryID, Patient patient, Record record, Activity activity) {
		String date = record.getDateDelimited();
		
		result = null;
		try{
			updateCount = st8.executeUpdate
			(
				"INSERT INTO RECORD_ACTIVITY(ENTRY_ID, JOURNAL_ID, CALENDAR_DATE, NAME, DURATION) VALUES(" +
				recordEntryID + "," + patient.getJournalID() + ",'" + date + "'" +
				",'" + activity.getName() + "'," + activity.getDuration() + ")" 
			);
			result = checkWarning(st8, updateCount);
		}
		catch(Exception e){
			result = processSQLError(e);
		}		
		return result;
	}
	
	
	public int countPatients(){
		
		int rowCount=0;
		
		try{
			cmdString = "Select count(*) from Patients";
			rs1 = st2.executeQuery(cmdString);
			rs1.next();
		    rowCount = rs1.getInt(1);

		}
		catch(Exception e){
			processSQLError(e);
		}		
		
		return rowCount;
	}	
	
	public int countFoodRecord(){
		
		int rowCount=0;
		try{
			cmdString = "Select count(*) from RECORD_FOOD";
			rs1 = st2.executeQuery(cmdString);
			rs1.next();
		    rowCount = rs1.getInt(1);

		}
		catch(Exception e){
			processSQLError(e);
		}		
		
		return rowCount;
	}
	
	public int countActivityRecord(){
		
		int rowCount=0;
		try{
			cmdString = "Select count(*) from RECORD_ACTIVITY";
			rs1 = st2.executeQuery(cmdString);
			rs1.next();
		    rowCount = rs1.getInt(1);

		}
		catch(Exception e){
			processSQLError(e);
		}		
		
		return rowCount;
	}
	
	public String checkWarning(Statement st, int updateCount){
		
		String result;
		result = null;
		
		try{
			SQLWarning warning = st.getWarnings();
			if (warning != null){
				result = warning.getMessage();
			}
		}
		catch (Exception e){
			result = processSQLError(e);
		}
		if (updateCount != 1){
			result = "Tuple not inserted correctly.";
		}
		
		return result;
	}
	
	
	public String processSQLError(Exception e){
		String result;
		result = "*** SQL Error: " + e.getMessage();
		//e.printStackTrace();
		return result;
	}











	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
