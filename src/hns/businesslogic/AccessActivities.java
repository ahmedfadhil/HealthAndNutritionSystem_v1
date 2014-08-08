package hns.businesslogic;

import java.util.LinkedList;

import hns.application.Main;
import hns.application.Services;
import hns.objects.Activity;
import hns.objects.Food;
import hns.persistence.DataAccess;


public class AccessActivities {
	
	private DataAccess dataAccess;
	private Activity activity;
	
	public AccessActivities(){
		
		dataAccess = (DataAccess)Services.getDataAccess(Main.dbName);
		activity = null;
	}
	
	public Activity getActivity(String name){
		
		activity = dataAccess.searchActivity(name);
		return activity;
	}
	
	public LinkedList<Activity> getActivityList(String searchQuery){
		
		return dataAccess.getActivityList(searchQuery);
	}
	
	
}
