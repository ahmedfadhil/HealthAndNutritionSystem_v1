package tests.businesslogicTests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class BusinessTests{
	
	public static TestSuite suite;

    public static Test suite(){
    	
        suite = new TestSuite("Business tests");
        suite.addTestSuite(AccessActivitiesTest.class);
        suite.addTestSuite(AccessFoodTest.class);
        suite.addTestSuite(AccessPatientsTest.class);
        suite.addTestSuite(CalculateBMITest.class);
        suite.addTestSuite(CalculateRealCaloriesTest.class);
        suite.addTestSuite(CalculateRequiredCaloriesTest.class); 
        suite.addTestSuite(CalculateTargetWeightTest.class);
        suite.addTestSuite(CalculateTargetCaloriesTest.class);
        suite.addTestSuite(CheckValidInputTest.class);
        suite.addTestSuite(CalculateWeightLevelTest.class);
        return suite;
    }
}
