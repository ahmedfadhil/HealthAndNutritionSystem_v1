package hns.persistence;

import java.util.LinkedList;

import hns.objects.Activity;
import hns.objects.Food;
import hns.objects.Journal;
import hns.objects.Patient;
import hns.objects.Record;

public interface DataAccess {
	
	public void open(String dbName) throws Exception;
	
	public void close();
	
	public LinkedList<Patient> getPatientList();

	public String insertPatient(Patient patient);
	
	public String updatePatient(Patient patient);
	
	public void insertJournal(Patient patient);
	
	public String insertNewRecord(Record record, Journal journal);

	public String insertFoodRecord(int recordEntryID, Patient patient, Record record, Food food);
	
	public String insertActivityRecord(int recordEntryID, Patient patient, Record record, Activity activity);

	public int countPatients();

	public int countFoodRecord();

	public int countActivityRecord();

	public LinkedList<Food> getFoodList(String searchQuery);

	public Food searchFood(String name);

	public LinkedList<Activity> getActivityList(String searchQuery);

	public Activity searchActivity(String name);
	
	public String updateRecordWeight(Journal journal, Record record);

	
}
