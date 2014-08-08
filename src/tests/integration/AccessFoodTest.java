package tests.integration;

import java.util.LinkedList;

import junit.framework.TestCase;
import hns.application.Main;
import hns.application.Services;
import hns.businesslogic.AccessFood;
import hns.objects.Food;
import hns.persistence.DataAccessObject;
import hns.persistence.DataAccessStub;

public class AccessFoodTest extends TestCase{
	
	private static String dbName = Main.dbName;
	
	public AccessFoodTest(String arg0){
		
		super(arg0);
	}
	
	public void testAccessFood() throws Exception{
		System.out.println("\nStarting Integration test: testAccessFood");
		
		Services.closeDataAccess();
		Services.createDataAccess(new DataAccessObject(dbName));
		
		AccessFood access = new AccessFood();
		LinkedList<Food> foodList = new LinkedList<Food>();
		Food food;
		
		foodList = access.getFoodList("ABALONE,MIXED SPECIES,RAW");
		assertNotNull(foodList);
		assertNotNull(foodList.getFirst());
		assertTrue(checkFoodList(foodList, foodList.getFirst()));
		assertFalse(checkFoodList(foodList, new Food("ADOBO FRESCO", 271, 100)));	
		assertFalse(checkFoodList(foodList, new Food("ACORN STEW (APACHE)", 95, 100)));

		foodList = access.getFoodList("ACORN FLOUR,FULL FAT");
		assertNotNull(foodList);
		assertNotNull(foodList.getFirst());
		assertTrue(checkFoodList(foodList, foodList.getFirst()));
		assertFalse(checkFoodList(foodList, new Food("ACORNS,RAW", 387, 100)));
		assertFalse(checkFoodList(foodList, new Food("ACEROLA,(WEST INDIAN CHERRY),RAW", 32, 100)));
		
		foodList = access.getFoodList(null);
		foodList.isEmpty();
		
		foodList = access.getFoodList("");
		foodList.isEmpty();
		
		foodList = access.getFoodList(" ");
		foodList.isEmpty();
		
		foodList = access.getFoodList("zzzzzzzz");
		foodList.isEmpty();
		
		foodList = access.getFoodList("90210");
		foodList.isEmpty();
		
		food = access.getFood("ABIYUCH,RAW");
		assertNotNull(food);
		assertEquals(food.getName(), "ABIYUCH,RAW");
		assertEquals(food.getCalories(), 69);
		assertEquals(food.getWeightGram(), 100);
		
		food = access.getFood("ACORNS,DRIED");
		assertNotNull(food);
		assertEquals(food.getName(), "ACORNS,DRIED");
		assertEquals(food.getCalories(), 509);
		assertEquals(food.getWeightGram(), 100);
		
		food = access.getFood(null);
		assertNull(food);
		
		food = access.getFood("");
		assertNull(food);
		
		food = access.getFood(" ");
		assertNull(food);
		
		food = access.getFood("DORITOS");
		assertNull(food);
		
		Services.closeDataAccess();
		System.out.println("Finished Integration test: testAccessFood");
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
	
	
	
}
