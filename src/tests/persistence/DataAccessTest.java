package tests.persistence;

import java.util.LinkedList;

import junit.framework.TestCase;
import hns.application.Main;
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
import hns.persistence.DataAccessObject;

public class DataAccessTest extends TestCase{
	
	private static String dbName = Main.dbName;
	
	public DataAccessTest(String arg0){
		
		super(arg0);
	}

	public void testDataAccess() throws Exception{
		System.out.println("\nStarting Persistence test DataAccess");
		
		DataAccess dataAccess;
		
		LinkedList<Food> foodList;
		LinkedList<Activity> activityList;
		LinkedList<Patient> patientList;
		
		Food food;
		Activity activity;
		Patient patient;
		
		Services.closeDataAccess();
		//Use this statement to run with the stub database
		dataAccess = (DataAccess)Services.createDataAccess(new DataAccessStub(dbName));
		
		//Use this statement to run with the real database
		//dataAccess = (DataAccess)Services.createDataAccess(new DataAccessObject(dbName));
		
		foodList = dataAccess.getFoodList("ABALONE,MIXED SPECIES,RAW");
		assertNotNull(foodList);
		assertNotNull(foodList.getFirst());
		assertTrue(checkFoodList(foodList, foodList.getFirst()));
		assertFalse(checkFoodList(foodList, new Food("ADOBO FRESCO", 271, 100)));	
		assertFalse(checkFoodList(foodList, new Food("ACORN STEW (APACHE)", 95, 100)));

		foodList = dataAccess.getFoodList("ACORN FLOUR,FULL FAT");
		assertNotNull(foodList);
		assertNotNull(foodList.getFirst());
		assertTrue(checkFoodList(foodList, foodList.getFirst()));
		assertFalse(checkFoodList(foodList, new Food("ACORNS,RAW", 387, 100)));
		assertFalse(checkFoodList(foodList, new Food("ACEROLA,(WEST INDIAN CHERRY),RAW", 32, 100)));
		
		foodList = dataAccess.getFoodList(null);
		foodList.isEmpty();
		
		foodList = dataAccess.getFoodList("");
		foodList.isEmpty();
		
		foodList = dataAccess.getFoodList(" ");
		foodList.isEmpty();
		
		foodList = dataAccess.getFoodList("zzzzzzzz");
		foodList.isEmpty();
		
		foodList = dataAccess.getFoodList("90210");
		foodList.isEmpty();

		activityList = dataAccess.getActivityList("AEROBICS, GENERAL");
		assertNotNull(activityList);
		assertNotNull(activityList.getFirst());
		assertTrue(checkActivity(activityList, activityList.getFirst()));
		assertFalse(checkActivity(activityList, new Activity("BACKPACKING, HIKING WITH PACK", 413, 60)));
		assertFalse(checkActivity(activityList, new Activity("BAGGING GRASS, LEAVES", 236, 60)));
		
		activityList = dataAccess.getActivityList("BADMINTON");
		assertNotNull(activityList);
		assertNotNull(activityList.getFirst());
		assertTrue(checkActivity(activityList, activityList.getFirst()));
		assertFalse(checkActivity(activityList, new Activity("AEROBICS, STEP AEROBICS", 502, 60)));
		assertFalse(checkActivity(activityList, new Activity("ARCHERY", 207, 60)));
		
		activityList = dataAccess.getActivityList(null);
		activityList.isEmpty();
		
		activityList = dataAccess.getActivityList("");
		activityList.isEmpty();
		
		activityList = dataAccess.getActivityList(" ");
		activityList.isEmpty();
		
		activityList = dataAccess.getActivityList("zzzzzzzz");
		activityList.isEmpty();
		
		activityList = dataAccess.getActivityList("90210");
		activityList.isEmpty();
		
		food = dataAccess.searchFood("ABIYUCH,RAW");
		assertNotNull(food);
		assertEquals(food.getName(), "ABIYUCH,RAW");
		assertEquals(food.getCalories(), 69);
		assertEquals(food.getWeightGram(), 100);
		
		food = dataAccess.searchFood("ACORNS,DRIED");
		assertNotNull(food);
		assertEquals(food.getName(), "ACORNS,DRIED");
		assertEquals(food.getCalories(), 509);
		assertEquals(food.getWeightGram(), 100);
		
		food = dataAccess.searchFood(null);
		assertNull(food);
		
		food = dataAccess.searchFood("");
		assertNull(food);
		
		food = dataAccess.searchFood(" ");
		assertNull(food);
		
		food = dataAccess.searchFood("ACORN, NOT DRY");
		assertNull(food);

		activity = dataAccess.searchActivity("BAGGING GRASS, LEAVES");
		assertNotNull(activity);
		assertEquals(activity.getName(), "BAGGING GRASS, LEAVES");
		assertEquals(activity.getCalories(), 236);
		
		activity = dataAccess.searchActivity("BALLET, TWIST, JAZZ, TAP");
		assertNotNull(activity);
		assertEquals(activity.getName(), "BALLET, TWIST, JAZZ, TAP");
		assertEquals(activity.getCalories(), 266);
		
		activity = dataAccess.searchActivity(null);
		assertNull(activity);
		
		activity = dataAccess.searchActivity("");
		assertNull(activity);
		
		activity = dataAccess.searchActivity(" ");
		assertNull(activity);
		
		activity = dataAccess.searchActivity("FOOSEBALL");
		assertNull(activity);

		patientList = dataAccess.getPatientList();
		assertNotNull(patientList);
		
		patient = (Patient)patientList.getFirst();
		assertNotNull(patient);
		assertEquals(patient.getUserID(), 0);
		assertEquals(patient.getFirstName(), "Matt");
		assertEquals(patient.getLastName(), "Damon");
		assertEquals(patient.getAge(), 41);
		assertEquals(patient.getWeight(), 72);
		assertEquals(patient.getHeight(), 178);
		assertEquals(patient.getGender(), Gender.MALE);
		assertEquals(patient.getActivityLevel(), ActivityLevel.LIGHT);

		assertNotNull(patientList.get(1));
		assertEquals(patientList.get(1).getUserID(), 1);
		assertEquals(patientList.get(1).getFirstName(), "Lara");
		assertEquals(patientList.get(1).getLastName(), "Croft");
		assertEquals(patientList.get(1).getAge(), 21);
		assertEquals(patientList.get(1).getWeight(), 60);
		assertEquals(patientList.get(1).getHeight(), 176);
		assertEquals(patientList.get(1).getGender(), Gender.FEMALE);
		assertEquals(patientList.get(1).getActivityLevel(), ActivityLevel.EXTRA);
		
		patient = new Patient(200,  1, "Doe", "John", 20, 70, 175, Gender.MALE, ActivityLevel.LIGHT);
		dataAccess.insertPatient(patient);
		
		patient = dataAccess.getPatientList().getLast();
		assertNotNull(patient);
		assertEquals(200, patient.getUserID());
		assertEquals(1, patient.getJournalID());
		assertEquals("Doe", patient.getLastName());
		assertEquals("John", patient.getFirstName());
		assertEquals(20, patient.getAge());
		assertEquals(70, patient.getWeight());
		assertEquals(175, patient.getHeight());
		assertEquals(Gender.MALE, patient.getGender());
		assertEquals(ActivityLevel.LIGHT, patient.getActivityLevel());
		
		Services.closeDataAccess();
		System.out.println("Finished Persistence test DataAccess");
	}
	
	public boolean checkFoodList(LinkedList<Food> foodList, Food other){
		boolean found = false;
		for(Food food : foodList){
			if(food.equals(other)){
				found = true;
			}
		}
		return found;
		
	}
	
	public boolean checkActivity(LinkedList<Activity> activityList, Activity other){
		boolean found = false;
		for(Activity activity : activityList){
			if(activity.equals(other)){
				found = true;
			}
		}
		return found;
		
	}
	

}
