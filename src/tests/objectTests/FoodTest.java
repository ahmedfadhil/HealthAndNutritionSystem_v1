package tests.objectTests;

import junit.framework.TestCase;
import hns.objects.Food;

public class FoodTest extends TestCase{
	
	public FoodTest(String arg0){
		
		super(arg0);
	}
	
	public void testFood1(){
		System.out.println("\nStarting testFood");
		
		Food food = new Food("Pizza", 250, 2);
		
		assertNotNull(food);
		assertTrue(food instanceof Food);
		assertEquals(food.getName(), "Pizza");
		assertEquals(food.getCalories(), 250);
		assertEquals(food.getWeightGram(), 2);
		assertEquals(food.getNormalizedCalories(), 5);


		System.out.println("Finished testFood");
	}

	public void testFood2(){
		System.out.println("\nStarting testFood");
		
		Food food = new Food("Pizza", 150, 1);
		
		assertNotNull(food);
		assertTrue(food instanceof Food);
		assertEquals(food.getName(), "Pizza");
		assertEquals(food.getCalories(), 150);
		assertEquals(food.getWeightGram(), 1);
		assertEquals(food.getNormalizedCalories(), 2);


		System.out.println("Finished testFood");
	}
}
