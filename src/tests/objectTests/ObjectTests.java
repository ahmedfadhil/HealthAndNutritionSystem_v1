package tests.objectTests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class ObjectTests{
	
	public static TestSuite suite;

    public static Test suite(){
    	
        suite = new TestSuite("Object tests");
        suite.addTestSuite(ActivityTest.class);
        suite.addTestSuite(DateTest.class);
        suite.addTestSuite(FoodTest.class);
        suite.addTestSuite(JournalTest.class);
        suite.addTestSuite(PatientTest.class);
        suite.addTestSuite(RecordTest.class);
        suite.addTestSuite(UserTest.class);
        return suite;
    }
}
