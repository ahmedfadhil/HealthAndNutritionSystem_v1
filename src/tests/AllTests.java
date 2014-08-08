package tests;

import tests.businesslogicTests.BusinessTests;
import tests.integration.IntegrationTests;
import tests.objectTests.ObjectTests;
import tests.persistence.PersistenceTests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests{
	
	public static TestSuite suite;

    public static Test suite(){
    	
        suite = new TestSuite("All tests");
        suite.addTest(ObjectTests.suite());
        suite.addTest(BusinessTests.suite());
        suite.addTest(IntegrationTests.suite());
        suite.addTest(PersistenceTests.suite());
        return suite;
    }
}
