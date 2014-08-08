package tests.businesslogicTests;

import hns.businesslogic.CalculateRequiredCalories;
import hns.enums.ActivityLevel;
import hns.enums.Gender;
import junit.framework.TestCase;

public class CalculateRequiredCaloriesTest extends TestCase {
	
	public void testInvalidFieldsCalories1() throws Exception{
		System.out.println("\nStarting testInvalidFieldsCalories1: test zero and null invalid values");
		
		try{
			CalculateRequiredCalories.determineCalories(0, 0, 0, null, null);
		}catch(Exception e){
			
		}
		System.out.println("Finished testInvalidFieldsCalories1: test zero and null invalid values");
	}
	
	public void testInvalidFieldsCalories2() throws Exception{
		System.out.println("\nStarting testInvalidFieldsCalories2: test negative and null invalid values");

		try{
			CalculateRequiredCalories.determineCalories(-52, -160, -20, null, null);
		}catch(Exception e){
			
		}
		System.out.println("Finished testInvalidFieldsCalories2: test negative and null invalid values");
	}
	
	public void testInvalidFieldsCalories3() throws Exception{
		System.out.println("\nStarting testInvalidFieldsCalories3: test zero weight invalid value");

		
		try{
			CalculateRequiredCalories.determineCalories(0, 160, 20, Gender.FEMALE, ActivityLevel.EXTRA);
		}catch(Exception e){
			
		}
		System.out.println("Finished testInvalidFieldsCalories3: test zero weight invalid value");
	}
	
	public void testInvalidFieldsCalories4() throws Exception{
		System.out.println("\nStarting testInvalidFieldsCalories4: test zero height invalid value");

		try{
			CalculateRequiredCalories.determineCalories(52, 0, 20, Gender.MALE, ActivityLevel.LIGHT);
		}catch(Exception e){
			
		}
		System.out.println("Finished testInvalidFieldsCalories4: test zero height invalid value");
	}
	
	public void testInvalidFieldsCalories5() throws Exception{
		System.out.println("\nStarting testInvalidFieldsCalories5: test zero age invalid value");

		try{
			CalculateRequiredCalories.determineCalories(58, 160, 0, Gender.MALE, ActivityLevel.LIGHT);
		}catch(Exception e){
			
		}
		System.out.println("Finished testInvalidFieldsCalories5: test zero age invalid value");
	}
	
	public void testInvalidFieldsCalories6() throws Exception{
		System.out.println("\nStarting testInvalidFieldsCalories6: test null Gender invalid value");

		try{
			CalculateRequiredCalories.determineCalories(58, 160, 0, null, ActivityLevel.LIGHT);
		}catch(Exception e){
			
		}
		System.out.println("Finished testInvalidFieldsCalories6: test null Gender invalid value");
	}
	
	public void testInvalidFieldsCalories7() throws Exception{
		System.out.println("\nStarting testInvalidFieldsCalories7: test null ActivityLevel invalid value");

		try{
			CalculateRequiredCalories.determineCalories(58, 160, 0, Gender.MALE, null);
		}catch(Exception e){
			
		}
		System.out.println("Finished testInvalidFieldsCalories7: test null ActivityLevel invalid value");
	}
	
	public void testInvalid8() throws Exception{
		System.out.println("\nStarting testInvalidFieldsCalories8: test out of boundary weight invalid value");

		try{
			CalculateRequiredCalories.determineCalories(3333333, 160, 20, Gender.MALE, ActivityLevel.SEDENTARY);
		}catch(Exception e){
			
		}
		System.out.println("Finished testInvalidFieldsCalories8: test out of boundary weight invalid value");
	}
	
	public void testInvalid9() throws Exception{
		System.out.println("\nStarting testInvalidFieldsCalories9: test out of boundary height invalid value");

		try{
			CalculateRequiredCalories.determineCalories(58, 1111111111, 20, Gender.MALE, ActivityLevel.SEDENTARY);
		}catch(Exception e){
			
		}
		System.out.println("Finished testInvalidFieldsCalories9: test out of boundary height invalid value");
	}
	
	public void testInvalid10() throws Exception{
		System.out.println("\nStarting testInvalidFieldsCalories10: test out of boundary age invalid value");

		try{
			CalculateRequiredCalories.determineCalories(58, 160, 222222222, Gender.MALE, ActivityLevel.SEDENTARY);
		}catch(Exception e){
			
		}
		System.out.println("Finished testInvalidFieldsCalories10: test out of boundary age invalid value");
	}
	
	
	public void testCaloriesKnownResults() throws Exception{
		System.out.println("\nStarting testCaloriesKnownResults");

		assertEquals(CalculateRequiredCalories.determineCalories(52, 160, 20, Gender.FEMALE, ActivityLevel.SEDENTARY), 1626);
		assertEquals(CalculateRequiredCalories.determineCalories(70, 170, 24, Gender.FEMALE, ActivityLevel.LIGHT), 2099);
		assertEquals(CalculateRequiredCalories.determineCalories(49, 156, 19, Gender.FEMALE, ActivityLevel.MODERATE), 2051);
		assertEquals(CalculateRequiredCalories.determineCalories(65, 168, 30, Gender.FEMALE, ActivityLevel.VERY), 2496);
		assertEquals(CalculateRequiredCalories.determineCalories(58, 165, 28, Gender.FEMALE, ActivityLevel.EXTRA), 2630);
		
		assertEquals(CalculateRequiredCalories.determineCalories(70, 175, 20, Gender.MALE, ActivityLevel.SEDENTARY), 2123);
		assertEquals(CalculateRequiredCalories.determineCalories(82, 188, 26, Gender.MALE, ActivityLevel.LIGHT), 2694);
		assertEquals(CalculateRequiredCalories.determineCalories(68, 170, 22, Gender.MALE, ActivityLevel.MODERATE), 2640);
		assertEquals(CalculateRequiredCalories.determineCalories(100, 185, 19, Gender.MALE, ActivityLevel.VERY), 3862);
		assertEquals(CalculateRequiredCalories.determineCalories(78, 174, 30, Gender.MALE, ActivityLevel.EXTRA), 3433);
		
		System.out.println("Finished testCaloriesKnownResults");
	}
	
	public void testCaloriesIncorrectResults() throws Exception{
		System.out.println("\nStarting testCaloriesIncorrectResults");

		assertTrue(CalculateRequiredCalories.determineCalories(52, 160, 20, Gender.FEMALE, ActivityLevel.SEDENTARY) != 1627);
		assertTrue(CalculateRequiredCalories.determineCalories(70, 170, 24, Gender.FEMALE, ActivityLevel.LIGHT) != 2100);
		assertTrue(CalculateRequiredCalories.determineCalories(49, 156, 19, Gender.FEMALE, ActivityLevel.MODERATE) != 2052);
		assertTrue(CalculateRequiredCalories.determineCalories(65, 168, 30, Gender.FEMALE, ActivityLevel.VERY) != 2497);
		assertTrue(CalculateRequiredCalories.determineCalories(58, 165, 28, Gender.FEMALE, ActivityLevel.EXTRA) != 2631);
		
		assertTrue(CalculateRequiredCalories.determineCalories(70, 175, 20, Gender.MALE, ActivityLevel.SEDENTARY) != 2124);
		assertTrue(CalculateRequiredCalories.determineCalories(82, 188, 26, Gender.MALE, ActivityLevel.LIGHT) != 2684);
		assertTrue(CalculateRequiredCalories.determineCalories(68, 170, 22, Gender.MALE, ActivityLevel.MODERATE) != 2650);
		assertTrue(CalculateRequiredCalories.determineCalories(100, 185, 19, Gender.MALE, ActivityLevel.VERY) != 4862);
		assertTrue(CalculateRequiredCalories.determineCalories(78, 174, 30, Gender.MALE, ActivityLevel.EXTRA) != 3533);
		
		System.out.println("Finished testCaloriesIncorrectResults");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}

