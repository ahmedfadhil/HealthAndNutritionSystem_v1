package tests.persistence;

import hns.application.Services;
import hns.enums.ActivityLevel;
import hns.enums.Gender;
import hns.objects.Activity;
import hns.objects.Date;
import hns.objects.Food;
import hns.objects.Journal;
import hns.objects.Patient;
import hns.objects.Record;
import hns.persistence.DataAccess;

import java.util.LinkedList;

public class DataAccessStub implements DataAccess{
	
	private String dbName;
	private String dbType;
	
	private LinkedList<Food> foodList;
	private LinkedList<Activity> activityList;
	private LinkedList<Patient> patientList;
	
	public DataAccessStub(String dbName){
		
		this.dbName = dbName;
		dbType = "stub";
		
	}
	
	// Inject this test double into a test
	public void injectDataAccessStub() throws Exception{
		Services.createDataAccess(this);
	}

	public void open(String dbName) throws Exception{
		
		Food food;
		Activity activity;
		Patient patient;
		
		foodList = new LinkedList<Food>();
		activityList = new LinkedList<Activity>();
		patientList = new LinkedList<Patient>();
		
		food = new Food("ABALONE,MIXED SPECIES,RAW", 105, 100);
		foodList.add(food);
		food = new Food("ABALONE,MXD SP,CKD,FRIED", 189, 100);
		foodList.add(food);
		food = new Food("ABIYUCH,RAW", 69, 100);
		foodList.add(food);
		food = new Food("ACEROLA JUICE,RAW", 23, 100);
		foodList.add(food);
		food = new Food("ACEROLA,(WEST INDIAN CHERRY),RAW", 32, 100);
		foodList.add(food);
		food = new Food("ACORN FLOUR,FULL FAT", 501, 100);
		foodList.add(food);
		food = new Food("ACORN STEW (APACHE)", 95, 100);
		foodList.add(food);
		food = new Food("ACORNS,DRIED", 509, 100);
		foodList.add(food);
		food = new Food("ACORNS,RAW", 387, 100);
		foodList.add(food);
		food = new Food("ADOBO FRESCO", 271, 100);
		foodList.add(food);
		
		activity = new Activity("AEROBICS, GENERAL", 384, 60);
		activityList.add(activity);
		activity = new Activity("AEROBICS, HIGH IMPACT", 413, 60);
		activityList.add(activity);
		activity = new Activity("AEROBICS, LOW IMPACT", 295, 60);
		activityList.add(activity);
		activity = new Activity("AEROBICS, STEP AEROBICS", 502, 60);
		activityList.add(activity);
		activity = new Activity("ARCHERY", 207, 60);
		activityList.add(activity);
		activity = new Activity("BACKPACKING, HIKING WITH PACK", 413, 60);
		activityList.add(activity);
		activity = new Activity("BADMINTON", 266, 60);
		activityList.add(activity);
		activity = new Activity("BAGGING GRASS, LEAVES", 236, 60);
		activityList.add(activity);
		activity = new Activity("BALLET, TWIST, JAZZ, TAP", 266, 60);
		activityList.add(activity);
		activity = new Activity("BALLROOM DANCING, FAST", 325, 60);
		activityList.add(activity);
		
		patient = new Patient(0, 0, "Damon", "Matt", 41, 72, 178, Gender.MALE, ActivityLevel.LIGHT);
		patientList.add(patient);
		patient = new Patient(1, 1, "Croft", "Lara", 21, 60, 176, Gender.FEMALE, ActivityLevel.EXTRA);
		patientList.add(patient);
		patient = new Patient(2, 2, "Cullen", "Edward", 17, 68, 187, Gender.MALE, ActivityLevel.MODERATE);
		patientList.add(patient);
		patient = new Patient(3, 3, "Pitt", "Brad", 48, 74, 181, Gender.MALE, ActivityLevel.MODERATE);
		patientList.add(patient);

		System.out.println("Opened " + dbType +" database " + dbName);
		
	}
	
	public void close(){
		
		System.out.println("Closed " + dbType + " database " + dbName);
	}

	public Food searchFood(String name) {
		
		Food result = null;
		
		for(Food food : foodList){
			if(food.getName().equals(name)){
				result = food;
			}
		}
		
		return result;
	}
	
	public LinkedList<Food> getFoodList(String searchQuery) {
		
		LinkedList<Food> searchFoodList = new LinkedList<Food>();
			if(searchQuery != null){
			for(Food food : this.foodList){
				if(food.getName().contains(searchQuery.toUpperCase())){
					searchFoodList.add(food);
				}
			
			}
		}
		return searchFoodList;
	}

	public Activity searchActivity(String name) {

		Activity result = null;
		
		for(Activity activity : activityList){
			if(activity.getName().equals(name)){
				result = activity;
			}
		}
		
		return result;
	}
	
	public LinkedList<Activity> getActivityList(String searchQuery) {

		LinkedList<Activity> searchActivityList = new LinkedList<Activity>();
		
		if(searchQuery != null){
			for(Activity activity : this.activityList){
				if(activity.getName().contains(searchQuery.toUpperCase())){
					searchActivityList.add(activity);
				}
			
			}
		}
		return searchActivityList;
	}

	public LinkedList<Patient> getPatientList(){
		
		return patientList;
	}
	
	public String insertPatient(Patient patient){
		
		patientList.add(patient);
		return null;
	}
	
	public String updatePatient(Patient patient) {
		return null;
	}
	
	public String updateRecordWeight(Journal journal, Record record) {

		return null;
	}
	
	public void insertJournal(Patient patient) {
		
	}
	public String insertNewRecord(Record record, Journal journal) {
		return null;
		
	}
	public String insertFoodRecord(int recordEntryID, Patient patient, Record record, Food food) {
		return null;

	}
	public String insertActivityRecord(int recordEntryID, Patient patient, Record record, Activity activity) {
		return null;

	}
	
	public int countPatients() {
		
		return patientList.size();
	}

	public int countFoodRecord() {
		
		return foodList.size();
	}
	
	public int countActivityRecord() {
		
		return activityList.size();
	}

	
	
	
	
	
}
