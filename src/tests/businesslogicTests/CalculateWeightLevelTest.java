package tests.businesslogicTests;

import junit.framework.TestCase;
import hns.businesslogic.CalculateWeightLevel;

public class CalculateWeightLevelTest extends TestCase{
	
	public void testNegativeWeightLevel1() throws Exception{
		System.out.println("\nStarting testNegativeWeightLevel1");
		
		try{
			CalculateWeightLevel.determineWeightLevel(-0.5);
		}
		catch (IllegalArgumentException e){
		
		}
		System.out.println("Finished testNegativeWeightLevel1");
	}
	
	public void testNegativeWeightLevel2() throws Exception{
		System.out.println("\nStarting testNegativeWeightLevel2");
		
		try{
			CalculateWeightLevel.determineWeightLevel(-5);
		}
		catch (IllegalArgumentException e){
		
		}
		System.out.println("Finished testNegativeWeightLevel2");
	}
	
	public void testNegativeWeightLevel3() throws Exception{
		System.out.println("\nStarting testNegativeWeightLevel3");
		
		try{
			CalculateWeightLevel.determineWeightLevel(-100);
		}
		catch (IllegalArgumentException e){
		
		}
		System.out.println("Finished testNegativeWeightLevel3");
	}
	
	public void testZeroWeightLevel() throws Exception{
		System.out.println("\nStarting testZeroWeightLevel");
		
		try{
			CalculateWeightLevel.determineWeightLevel(0);
		}
		catch (IllegalArgumentException e){
		
		}
		System.out.println("Finished testZeroWeightLevel");
	}
	
	public void testWithinLevelValues() throws Exception{
		System.out.println("\nStarting testWithinLevelValues");
		
		assertEquals("Severely underweight", CalculateWeightLevel.determineWeightLevel(10.0));
		assertEquals("Underweight", CalculateWeightLevel.determineWeightLevel(17.5));
		assertEquals("Normal", CalculateWeightLevel.determineWeightLevel(19.6));
		assertEquals("Overweight", CalculateWeightLevel.determineWeightLevel(27.4));
		assertEquals("Obese Class I", CalculateWeightLevel.determineWeightLevel(31.6));
		assertEquals("Obese Class II", CalculateWeightLevel.determineWeightLevel(37.7));
		assertEquals("Obese Class III", CalculateWeightLevel.determineWeightLevel(55.9));
		
		System.out.println("Finished testWithinLevelValues");
	}
	
	public void testIncorrectWithinLevelValues() throws Exception{
		System.out.println("\nStarting testIncorrectWithinLevelValues");
		
		assertFalse("Obese Class II".equals(CalculateWeightLevel.determineWeightLevel(10.0)));
		assertFalse("Overweight".equals(CalculateWeightLevel.determineWeightLevel(17.5)));
		assertFalse("Overweight".equals(CalculateWeightLevel.determineWeightLevel(19.6)));
		assertFalse("Normal".equals(CalculateWeightLevel.determineWeightLevel(27.4)));
		assertFalse("Underweight".equals(CalculateWeightLevel.determineWeightLevel(29.9)));
		assertFalse("Underweight".equals(CalculateWeightLevel.determineWeightLevel(31.6)));
		assertFalse("Obese Class III".equals(CalculateWeightLevel.determineWeightLevel(37.7)));
		assertFalse("Normal".equals(CalculateWeightLevel.determineWeightLevel(55.9)));

		System.out.println("Finished testIncorrectWithinLevelValues");
	}
	
	public void testBoundaryLevelValues() throws Exception{
		System.out.println("\nStarting testBoundaryLevelValues");
		assertEquals("Severely underweight", CalculateWeightLevel.determineWeightLevel(0.001));
		assertEquals("Severely underweight", CalculateWeightLevel.determineWeightLevel(0.01));
		assertEquals("Severely underweight", CalculateWeightLevel.determineWeightLevel(0.1));
		assertEquals("Severely underweight", CalculateWeightLevel.determineWeightLevel(15.9));
		assertEquals("Underweight", CalculateWeightLevel.determineWeightLevel(16.0));
		assertEquals("Underweight", CalculateWeightLevel.determineWeightLevel(18.4));
		assertEquals("Normal", CalculateWeightLevel.determineWeightLevel(18.5));
		assertEquals("Normal", CalculateWeightLevel.determineWeightLevel(24.9));
		assertEquals("Overweight", CalculateWeightLevel.determineWeightLevel(25.0));
		assertEquals("Overweight", CalculateWeightLevel.determineWeightLevel(29.9));
		assertEquals("Obese Class I", CalculateWeightLevel.determineWeightLevel(30.0));
		assertEquals("Obese Class I", CalculateWeightLevel.determineWeightLevel(34.9));
		assertEquals("Obese Class II", CalculateWeightLevel.determineWeightLevel(35.0));
		assertEquals("Obese Class II", CalculateWeightLevel.determineWeightLevel(39.9));
		assertEquals("Obese Class III", CalculateWeightLevel.determineWeightLevel(40.0));
		
		System.out.println("Finished testBoundaryLevelValues");
	}
	
	public void testIncorrectBoundaryLevelValues() throws Exception{
		System.out.println("\nStarting testIncorrectBoundaryLevelValues");
		
		assertFalse("Obese Class III".equals(CalculateWeightLevel.determineWeightLevel(0.1)));
		assertFalse("Overweight".equals(CalculateWeightLevel.determineWeightLevel(15.9)));
		assertFalse("Normal".equals(CalculateWeightLevel.determineWeightLevel(16.0)));
		assertFalse("Obese Class III".equals(CalculateWeightLevel.determineWeightLevel(18.4)));
		assertFalse("Obese Class I".equals(CalculateWeightLevel.determineWeightLevel(18.5)));
		assertFalse("Obese Class II".equals(CalculateWeightLevel.determineWeightLevel(24.9)));
		assertFalse("Underweight".equals(CalculateWeightLevel.determineWeightLevel(25.0)));
		assertFalse("Normal".equals(CalculateWeightLevel.determineWeightLevel(29.9)));
		assertFalse("Obese Class I".equals(CalculateWeightLevel.determineWeightLevel(29.9)));
		assertFalse("Normal".equals(CalculateWeightLevel.determineWeightLevel(30.0)));
		assertFalse("Obese Class II".equals(CalculateWeightLevel.determineWeightLevel(34.9)));
		assertFalse("Normal".equals(CalculateWeightLevel.determineWeightLevel(35.0)));
		assertFalse("Underweight".equals(CalculateWeightLevel.determineWeightLevel(39.9)));
		assertFalse("Underweight".equals(CalculateWeightLevel.determineWeightLevel(40.0)));
		
		System.out.println("Finished testIncorrectBoundaryLevelValues");
	}

	
	public void testExtremeLevelValues() throws Exception{
		System.out.println("\ntestExtremeLevelValues");
		assertEquals("Severely underweight", CalculateWeightLevel.determineWeightLevel(0.00001));
		assertEquals("Severely underweight", CalculateWeightLevel.determineWeightLevel(0.0001));
		assertEquals("Severely underweight", CalculateWeightLevel.determineWeightLevel(0.001));
		assertEquals("Obese Class III", CalculateWeightLevel.determineWeightLevel(999.0));
		assertEquals("Obese Class III", CalculateWeightLevel.determineWeightLevel(9999.0));
		assertEquals("Obese Class III", CalculateWeightLevel.determineWeightLevel(99999.0));
		System.out.println("Finished testExtremeLevelValues");
	}
	
	public void testIncorrectExtremeLevelValues() throws Exception{
		System.out.println("\nStarting testIncorrectExtremeLevelValues");
		
		assertFalse("Obese Class III".equals(CalculateWeightLevel.determineWeightLevel(0.00001)));
		assertFalse("Overweight".equals(CalculateWeightLevel.determineWeightLevel(0.00001)));
		assertFalse("Normal".equals(CalculateWeightLevel.determineWeightLevel(0.00001)));
		assertFalse("Obese Class III".equals(CalculateWeightLevel.determineWeightLevel(0.0001)));
		assertFalse("Obese Class I".equals(CalculateWeightLevel.determineWeightLevel(0.0001)));
		assertFalse("Obese Class II".equals(CalculateWeightLevel.determineWeightLevel(0.0001)));
		assertFalse("Underweight".equals(CalculateWeightLevel.determineWeightLevel(0.0001)));
		assertFalse("Normal".equals(CalculateWeightLevel.determineWeightLevel(0.0001)));
		assertFalse("Obese Class I".equals(CalculateWeightLevel.determineWeightLevel(99999.0)));
		assertFalse("Normal".equals(CalculateWeightLevel.determineWeightLevel(99999.0)));
		assertFalse("Obese Class II".equals(CalculateWeightLevel.determineWeightLevel(99999.0)));
		assertFalse("Normal".equals(CalculateWeightLevel.determineWeightLevel(9999.0)));
		assertFalse("Underweight".equals(CalculateWeightLevel.determineWeightLevel(99999.0)));
		assertFalse("Underweight".equals(CalculateWeightLevel.determineWeightLevel(9999.0)));
		
		System.out.println("Finished testIncorrectExtremeLevelValues");
	}

}

