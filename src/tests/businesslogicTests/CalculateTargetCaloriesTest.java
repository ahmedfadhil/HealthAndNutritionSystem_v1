package tests.businesslogicTests;

import hns.businesslogic.CalculateTargetCalories;
import hns.enums.ActivityLevel;
import hns.enums.Gender;
import hns.objects.Patient;
import junit.framework.TestCase;

public class CalculateTargetCaloriesTest extends TestCase {
	
	
	public void testCalculateTargetCaloriesInvalidField1() throws Exception{
		System.out.println("\nStarting testCalculateTargetCaloriesInvalidField1: null values");
		try{
			CalculateTargetCalories.determineCalorieRange_LowerBound(null);
			CalculateTargetCalories.determineCalorieRange_UpperBound(null);
			CalculateTargetCalories.determineTargetCalorieRange(null);

		}catch(NullPointerException npe){}

		
		System.out.println("\nFinished testCalculateTargetCaloiresInvalidField1: null values");
	}
	
	public void testCalculateTargetCaloriesInvalidField2() throws Exception{
		System.out.println("\nStarting testCalculateTargetCaloriesInvalidField2: illegal values");
		try{
			Patient p1 = new Patient(200, 1, "Doe", "John", 0, 0, 0, Gender.MALE, ActivityLevel.LIGHT);
			CalculateTargetCalories.determineCalorieRange_LowerBound(p1);
			CalculateTargetCalories.determineCalorieRange_UpperBound(p1);
			CalculateTargetCalories.determineTargetCalorieRange(p1);
			
			
			Patient p2 = new Patient(200, 1, "Doe", "John", 2067897, 7068789, 1111111111, Gender.MALE, ActivityLevel.LIGHT);
			CalculateTargetCalories.determineCalorieRange_LowerBound(p2);
			CalculateTargetCalories.determineCalorieRange_UpperBound(p2);
			CalculateTargetCalories.determineTargetCalorieRange(p2);
			
			Patient p3 = new Patient(200, 1, "Doe", "John", -20, -70, -175, Gender.MALE, ActivityLevel.LIGHT);
			CalculateTargetCalories.determineCalorieRange_LowerBound(p3);
			CalculateTargetCalories.determineCalorieRange_UpperBound(p3);
			CalculateTargetCalories.determineTargetCalorieRange(p3);
			

		}catch(IllegalArgumentException iae){}

		
		System.out.println("\nFinished testCalculateTargetCaloiresInvalidField2: illegal values");
	}
	
	public void testCalculateTargetCaloriesValidField() throws Exception{
		System.out.println("\nStarting testCalculateTargetCaloriesValidField: legal values");
	
			Patient patient1 = new Patient(200, 1, "Doe", "John", 20, 70, 175, Gender.MALE, ActivityLevel.LIGHT);
			assertEquals(CalculateTargetCalories.determineCalorieRange_LowerBound(patient1), 2187);
			assertEquals(CalculateTargetCalories.determineCalorieRange_UpperBound(patient1), 2566);
			assertEquals(CalculateTargetCalories.determineTargetCalorieRange(patient1), "2187 - 2566");
						
			Patient patient2 = new Patient(200, 1, "Doe", "John", 59, 98, 168, Gender.FEMALE, ActivityLevel.SEDENTARY);
			assertEquals(CalculateTargetCalories.determineCalorieRange_LowerBound(patient2), 1425);
			assertEquals(CalculateTargetCalories.determineCalorieRange_UpperBound(patient2), 1643);
			assertEquals(CalculateTargetCalories.determineTargetCalorieRange(patient2), "1425 - 1643");
		
		System.out.println("\nFinished testCalculateTargetCaloriesValidField: legal values");
	}
	
	
}
