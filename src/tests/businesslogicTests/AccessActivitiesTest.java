package tests.businesslogicTests;

import java.util.LinkedList;

import junit.framework.TestCase;
import hns.application.Main;
import hns.application.Services;
import hns.businesslogic.AccessActivities;
import hns.objects.Activity;
import hns.persistence.DataAccessStub;

public class AccessActivitiesTest extends TestCase{
	
	private static String dbName = Main.dbName;
	
	public AccessActivitiesTest(String arg0){
		
		super(arg0);
	}
	
	public void testAccessActivities() throws Exception{
		System.out.println("\nStarting testAccessActivities");
		
		Services.closeDataAccess();
		Services.createDataAccess(new DataAccessStub(dbName));

		LinkedList <Activity> activityList = new LinkedList<Activity>();
		Activity activity;
		
		AccessActivities access = new AccessActivities();
		assertNotNull(access);
		
		activityList = access.getActivityList("AEROBICS, GENERAL");
		assertNotNull(activityList);
		assertNotNull(activityList.getFirst());
		assertTrue(checkActivity(activityList, activityList.getFirst()));
		assertFalse(checkActivity(activityList, new Activity("BACKPACKING, HIKING WITH PACK", 413, 60)));
		assertFalse(checkActivity(activityList, new Activity("BAGGING GRASS, LEAVES", 236, 60)));
		
		activityList = access.getActivityList("BADMINTON");
		assertNotNull(activityList);
		assertNotNull(activityList.getFirst());
		assertTrue(checkActivity(activityList, activityList.getFirst()));
		assertFalse(checkActivity(activityList, new Activity("AEROBICS, STEP AEROBICS", 502, 60)));
		assertFalse(checkActivity(activityList, new Activity("ARCHERY", 207, 60)));
		
		activityList = access.getActivityList(null);
		activityList.isEmpty();
		
		activityList = access.getActivityList("");
		activityList.isEmpty();
		
		activityList = access.getActivityList(" ");
		activityList.isEmpty();
		
		activityList = access.getActivityList("zzzzzzzz");
		activityList.isEmpty();
		
		activityList = access.getActivityList("90210");
		activityList.isEmpty();
		
		activity = access.getActivity("AEROBICS, GENERAL");
		assertNotNull(activity);
		assertEquals(activity.getName(), "AEROBICS, GENERAL");
		assertEquals(activity.getCalories(), 384);
		
		activity = access.getActivity("BADMINTON");
		assertNotNull(activity);
		assertEquals(activity.getName(), "BADMINTON");
		assertEquals(activity.getCalories(), 266);
		
		activity = access.getActivity("BALLROOM DANCING, FAST");
		assertNotNull(activity);
		assertEquals(activity.getName(), "BALLROOM DANCING, FAST");
		assertEquals(activity.getCalories(), 325);
		
		activity = access.getActivity("STUDYING");
		assertNull(activity);
		
		activity = access.getActivity("READING");
		assertNull(activity);
		
		activity = access.getActivity(null);
		assertNull(activity);
		
		activity = access.getActivity("");
		assertNull(activity);
		
		activity = access.getActivity(" ");
		assertNull(activity);
		
		Services.closeDataAccess();
		System.out.println("Finished testAccessActivities");
	}
	
	public boolean checkActivity(LinkedList<Activity> activityList, Activity other){
		boolean found = false;
		for(Activity activity : activityList){
			if(activity.equals(other)){
				found = true;
			}
		}
		return found;
		
	}

}
