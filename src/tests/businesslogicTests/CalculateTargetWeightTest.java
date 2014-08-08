package tests.businesslogicTests;

import hns.businesslogic.CalculateTargetWeight;
import junit.framework.TestCase;

public class CalculateTargetWeightTest extends TestCase {
	
	public void testCalculateTargetWeightInvalidField() throws Exception{
		System.out.println("\nStarting testCalculateTargetWeightInvalidField: illegal values");

		try{
			CalculateTargetWeight.determineLowerBoundNormalWeight(-1);
			CalculateTargetWeight.determineHigherBoundNormalWeight(-999999);
			CalculateTargetWeight.determineTargetRangeWeight(-111);
			
			CalculateTargetWeight.determineLowerBoundNormalWeight(301); //height upperbound = 300cm
			CalculateTargetWeight.determineHigherBoundNormalWeight(302);
			CalculateTargetWeight.determineTargetRangeWeight(303);
			

		}catch(IllegalArgumentException iae){}
		
		System.out.println("\nFinished testCalculateTargetWeightInvalidField: null and illegal values");
	}
	
	public void testCalculateTargetWeightValidField() throws Exception{
		System.out.println("\nStarting testCalculateTargetWeightValidField: legal values");
	
			assertEquals(CalculateTargetWeight.determineLowerBoundNormalWeight(175), 57);
			assertEquals(CalculateTargetWeight.determineHigherBoundNormalWeight(175), 77);
			assertEquals(CalculateTargetWeight.determineTargetRangeWeight(175), "57 - 77");
		
		
		System.out.println("\nFinished testCalculateTargetWeightValidField: legal values");
	}
	
}
