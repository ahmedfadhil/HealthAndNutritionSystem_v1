package hns.businesslogic;

import java.util.LinkedList;

import hns.application.Main;
import hns.application.Services;
import hns.objects.Food;
import hns.persistence.DataAccess;


public class AccessFood{
	
	private DataAccess dataAccess;
	private Food food;
	
	public AccessFood(){
		
		dataAccess = (DataAccess)Services.getDataAccess(Main.dbName);
		food = null;
	}

	public Food getFood(String name){
		
		food = dataAccess.searchFood(name);
		return food;
	}
	
	public LinkedList<Food> getFoodList(String searchQuery){
		
		return dataAccess.getFoodList(searchQuery);
	}
	
	
}
