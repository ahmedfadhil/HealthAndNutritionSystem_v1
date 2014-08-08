package tests.objectTests;
import hns.objects.*;
import junit.framework.TestCase;

public class RecordTest extends TestCase {
	
	public RecordTest(String arg0){
		
		super(arg0);
	}
	
	public void testRecord1() throws Exception{
		System.out.println("\nStarting testRecord1: Testing Record(Date date, int weight) constructor ");
		
		Date date = new Date(1,1,2012);
		int weight = 50;
		Record rec = new Record(date, weight);
		
		assertNotNull(rec);
		assertTrue(rec instanceof Record);
		assertEquals(rec.getWeight(), weight);
		
		assertNotNull(rec.getActivityList());
		assertNotNull(rec.getFoodList());
		
		assertEquals(rec.getDate(), date); 

		assertTrue(rec.getDate().equals(date));
		assertFalse(rec.getDate().equals(new Date(1,1,2011)));
		
		
		assertEquals(rec.getWeight(), weight);
		
		rec.setWeight(60);
		assertNotSame(rec.getWeight(),weight);
		assertEquals(rec.getWeight(), 60);
		
		System.out.println("Finished testRecord1: Testing Record(Date date, int weight) constructor");
	}

	
	public void testRecord2() throws Exception{
		System.out.println("\nStarting testRecord2: Testing adding Objects to the FoodList and ActivityList ");
		
		Date today = new Date(1,1,2012);		
		Record rec = new Record(today, 10);
		assertTrue(rec instanceof Record);
		assertTrue(today instanceof Date);
		
		Food bread = new Food("bread", 20, 2);
		Food wine  = new Food("wine", 32,  1);
		
		assertTrue(bread instanceof Food);
		assertTrue(wine instanceof Food);

		Activity walking = new Activity("walking", 10, 60);
		Activity computing = new Activity("computing", 2, 90);
		
		assertTrue(walking instanceof Activity);
		assertTrue(computing instanceof Activity);
		
		assertTrue(rec.AddToList(bread));
		assertTrue(rec.AddToList(wine));
		
		assertTrue(rec.AddToList(walking));
		assertTrue(rec.AddToList(computing));
		
		//invalid inputs
		assertFalse(rec.AddToList(today));
		assertFalse(rec.AddToList(null));
		assertFalse(rec.AddToList(1));
		assertFalse(rec.AddToList("bread"));
		
		System.out.println("Finished testRecord2: Testing adding Objects to the FoodList and ActivityList ");
	}
	
	
	
	public void testRecord3() throws Exception{
		System.out.println("\nStarting testRecord3: Invalid constructor arguments");
	
		Date date = new Date(1,1,2012);
		assertTrue(date instanceof Date);
		
		try{
			new Record(null,10);
		}catch(NullPointerException e){
			
		}
		
		try{
			new Record(date, -1);
			new Record(date, 0);
			new Record(date, 9999999);
		}catch(IllegalArgumentException e){
			
		}
		System.out.println("Finished testRecord3: Invalid constructor arguments");
	}
	

}
