package tests.objectTests;
import hns.objects.*;
import junit.framework.TestCase;

public class JournalTest extends TestCase {

	public JournalTest(String arg0){
		
		super(arg0);
	}

	
	public void testJournal1() throws Exception {		
		System.out.println("\nStarting testJournal1: in-ordered insertion");
		
		Record monday = new Record(new Date(3,1,2010),50);
		Record tuesday = new Record(new Date(3,2,2010),60);
		Record wednesday = new Record(new Date(3,3,2010),70);
		Record thursday = new Record(new Date(3,4,2010),80);
		
		Journal j = new Journal(1);
		
		//testing ordered insertion.
		j.insertNewRecord(tuesday);
		j.insertNewRecord(thursday);
		j.insertNewRecord(monday);		
		j.insertNewRecord(wednesday);
	
		
		assertNull(j.getPreviousRecord(monday)); //monday should be the 1st in the list.
		assertEquals(j.getNextRecord(monday), tuesday);
		
		assertEquals(j.getPreviousRecord(tuesday),monday);
		assertEquals(j.getNextRecord(tuesday),wednesday);
		
		assertEquals(j.getPreviousRecord(wednesday),tuesday);
		assertEquals(j.getNextRecord(wednesday),thursday);
		
		assertEquals(j.getPreviousRecord(thursday),wednesday);
		
		assertNull(j.getNextRecord(j.getLatestRecord())); //the latest should be the last.
		
		
		//invalid argument
		assertFalse(j.insertNewRecord(null));
		
		assertNull(j.getNextRecord(null));
		assertNull(j.getPreviousRecord(null));
		assertNull(j.getRecord(null));
		
		System.out.println("Finished testJournal1: in-ordered insertion");
	}
	
	
	public void testJournal2() throws Exception{
		System.out.println("\nStarting testJournal2: getting records of specific dates");
		
		Date mon = new Date(3,1,2013);
		Date tue = new Date(3,2,2013);
		Date wed = new Date(3,3,2013);
		Date thu = new Date(3,4,2013);
		Date fri = new Date(3,5,2013);
		
		Record monday = new Record(mon,50);
		Record tuesday = new Record(tue,60);
		Record wednesday = new Record(wed,70);

		
		Journal j = new Journal(1);
		
		j.insertNewRecord(monday);
		j.insertNewRecord(tuesday);
		j.insertNewRecord(wednesday);
		
		assertTrue(monday instanceof Record);
		assertTrue(tuesday instanceof Record);
		assertTrue(wednesday instanceof Record);
		
		assertNotNull(j.getRecord(mon));
		assertEquals(j.getRecord(mon), mon);
		
		assertNotNull(j.getRecord(tue));
		assertEquals(j.getRecord(tue),tue);
		
		assertNotNull(j.getRecord(wed));
		assertEquals(j.getRecord(wed),wed);
		
		//invalid
		assertNull(j.getRecord(thu));
		assertNull(j.getRecord(fri));
		assertNull(j.getRecord(null));		
		
		System.out.println("Finished testJournal2: getting records of specific dates");
	}
	
public void testJournal3() throws Exception{
		
	System.out.println("\nStarting testJournal3: getting journal last ten entries");
	Date mon = new Date(3,1,2013);
	Date tue = new Date(3,2,2013);
	Date wed = new Date(3,3,2013);
	Date thu = new Date(3,4,2013);
	Date fri = new Date(3,5,2013);
	Date mon2 = new Date(3,8,2013);
	Date tue2 = new Date(3,9,2013);
	Date wed2 = new Date(3,10,2013);
	Date thu2 = new Date(3,11,2013);
	Date fri2 = new Date(3,12,2013);
	Date mon3 = new Date(3,15,2013);
	Date tue3 = new Date(3,16,2013);
	Date wed3 = new Date(3,17,2013);
	Date thu3 = new Date(3,18,2013);
	Date fri3 = new Date(3,19,2013);
	
	Journal j = new Journal(1);
	j.insertNewRecord(new Record (mon, 50));
	j.insertNewRecord(new Record (tue, 60));
	j.insertNewRecord(new Record (wed, 70));
	
	assertEquals(j.size(),3);
	assertNotSame(j.size(),4);
	
	assertEquals(j.getLastTenDates().length,3);
	assertEquals(j.getLastTenWeights().length,3);
	
	Date array [] = j.getLastTenDates();
	assertTrue(array[0].equals(mon));
	assertTrue(array[1].equals(tue));
	assertTrue(array[2].equals(wed));
	assertFalse(array[2].equals(mon));
	
	int [] array2 =j.getLastTenWeights();
	assertEquals(array2[0],50);
	assertEquals(array2[1],60);
	assertEquals(array2[2],70);
	assertNotSame(array2[2],40);
	
	j.insertNewRecord(new Record (mon, 80));
	j.insertNewRecord(new Record (tue, 90));
	j.insertNewRecord(new Record (wed, 80));
	j.insertNewRecord(new Record (thu, 70));
	j.insertNewRecord(new Record (fri, 60));
	j.insertNewRecord(new Record (mon2, 50));
	j.insertNewRecord(new Record (tue2, 40));
	j.insertNewRecord(new Record (wed2, 30));
	j.insertNewRecord(new Record (thu2, 20));
	j.insertNewRecord(new Record (fri2, 10));
	j.insertNewRecord(new Record (mon3, 20));
	j.insertNewRecord(new Record (tue3, 30));
	j.insertNewRecord(new Record (wed3, 40));
	j.insertNewRecord(new Record (thu3, 50));
	j.insertNewRecord(new Record (fri3, 60));

	assertNotSame(j.size(),4);
	
	assertEquals(j.getLastTenDates().length,10);
	assertEquals(j.getLastTenWeights().length,10);
	array= j.getLastTenDates();
	assertEquals(array[0],mon2);
	assertEquals(array[1],tue2);
	assertEquals(array[2],wed2);
	assertEquals(array[3],thu2);
	assertEquals(array[4],fri2);
	assertEquals(array[5],mon3);
	assertEquals(array[6],tue3);
	assertEquals(array[7],wed3);
	assertEquals(array[8],thu3);
	assertEquals(array[9],fri3);
	
	array2 =j.getLastTenWeights();
	
	assertEquals(array2[0],50);
	assertEquals(array2[1],40);
	assertEquals(array2[2],30);
	assertEquals(array2[3],20);
	assertEquals(array2[4],10);
	assertEquals(array2[5],20);
	assertEquals(array2[6],30);
	assertEquals(array2[7],40);
	assertEquals(array2[8],50);
	assertEquals(array2[9],60);
	
	System.out.println("Finished testJournal3: getting journal last ten entries");
	
	}
		
	
}
