package hns.businesslogic;

import hns.application.Main;
import hns.application.Services;
import hns.objects.Activity;
import hns.objects.Food;
import hns.objects.Journal;
import hns.objects.Patient;
import hns.objects.Record;
import hns.persistence.DataAccess;

/*
 * This class is only used by the HSQL DB, when using the stub, is is never used because
 * it is not needed.
 * 
 */
public class AccessRecord {
	
	private DataAccess dataAccess;
	private static int foodEntryID;
	private static int activityEntryID = 1000;
	
	public AccessRecord(){
		
		dataAccess = (DataAccess)Services.getDataAccess(Main.dbName);
		foodEntryID = dataAccess.countFoodRecord();
		activityEntryID =dataAccess.countActivityRecord();
	}

	public String insertNewRecord(Record record, Journal journal) {
		
		return dataAccess.insertNewRecord(record, journal);
	}

	public String insertFoodRecord(Patient patient, Record record, Food food){
		
		foodEntryID ++;
		return dataAccess.insertFoodRecord(foodEntryID, patient, record, food);
	}
	
	public String insertActivityRecord(Patient patient, Record record, Activity activity) {
		
		activityEntryID ++;
		return dataAccess.insertActivityRecord(activityEntryID, patient, record, activity);
	}
	
	public String updateRecordWeight(Journal journal, Record record){
		
		return dataAccess.updateRecordWeight(journal, record);
	}


}
