package tests.objectTests;

import junit.framework.TestCase;
import hns.objects.Activity;

public class ActivityTest extends TestCase{
	
	public ActivityTest(String arg0){
		
		super(arg0);
	}
	
	public void testActivity1(){
		System.out.println("\nStarting testActivity");
		
		Activity activity = new Activity("Running", 150, 45);
		
		assertNotNull(activity);
		assertTrue(activity instanceof Activity);
		
		assertEquals(activity.getName(), "Running");
		assertEquals(activity.getCalories(), 150);
		assertEquals(activity.getDuration(), 45);
		assertEquals(activity.getNormalizedCalories(), 113);


		System.out.println("Finished testActivity");
	}

}
