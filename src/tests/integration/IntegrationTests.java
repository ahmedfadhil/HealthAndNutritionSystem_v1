package tests.integration;

import junit.framework.Test;
import junit.framework.TestSuite;
import tests.integration.AccessActivitiesTest;
import tests.integration.AccessFoodTest;
import tests.integration.AccessPatientsTest;

public class IntegrationTests {

	public static TestSuite suite;

    public static Test suite(){
    	
        suite = new TestSuite("Integration tests");
        suite.addTestSuite(AccessActivitiesTest.class);
        suite.addTestSuite(AccessFoodTest.class);
        suite.addTestSuite(AccessPatientsTest.class);

        return suite;
    }
}
