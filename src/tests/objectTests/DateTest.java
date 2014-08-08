package tests.objectTests;
import hns.businesslogic.*;
import hns.objects.*;
import junit.framework.TestCase;

public class DateTest extends TestCase {
	public DateTest(String arg0){
		
		super(arg0);
	}
	
	public void testDate1() throws Exception{
		System.out.println("\nStarting testDate1: Valid arguments and comparing");
		
		Date yesterday = new Date(3,1,2012);
		Date today = new Date(3,2,2012);
		Date tomorrow = new Date(3,3,2012);
		
		assertNotNull(yesterday);
		assertTrue(yesterday instanceof Date);
		assertEquals(yesterday.toString(), "MAR 1, 2012");
		assertNotSame(yesterday.toString(), "MAR 2, 2012");

		assertNotNull(today);
		assertTrue(today instanceof Date);
		assertEquals(today.toString(), "MAR 2, 2012");
		assertNotSame(today.toString(), "MAR 3, 2012");

		
		assertNotNull(tomorrow);
		assertTrue(tomorrow instanceof Date);
		assertEquals(tomorrow.toString(), "MAR 3, 2012");
		assertNotSame(tomorrow.toString(), "MAR 4, 2012");
		
		
		assertTrue(tomorrow.compareTo(yesterday) > 0);
		assertTrue(tomorrow.compareTo(tomorrow) == 0);
		assertFalse(tomorrow.compareTo(yesterday) == 0);
		assertFalse(tomorrow.compareTo(yesterday) < 0);
		
		
		assertTrue(tomorrow.equals(tomorrow));
		assertFalse(tomorrow.equals(null));
		assertFalse(tomorrow.equals(yesterday));
	
		
		System.out.println("Finished testDate2:");
	}

	public void testDate3(){
		System.out.println("\nStarting testDate3: Testing the conversion of int to month");
		
		assertEquals(Date.convertToMonth(1), "JAN");
		assertEquals(Date.convertToMonth(2), "FEB");
		assertEquals(Date.convertToMonth(3), "MAR");
		assertEquals(Date.convertToMonth(4), "APR");
		assertEquals(Date.convertToMonth(5),"MAY");
		assertEquals(Date.convertToMonth(6),"JUN");
		assertEquals(Date.convertToMonth(7),"JUL");
		assertEquals(Date.convertToMonth(8),"AUG");
		assertEquals(Date.convertToMonth(9),"SEP");
		assertEquals(Date.convertToMonth(10),"OCT");
		assertEquals(Date.convertToMonth(11),"NOV");
		assertEquals(Date.convertToMonth(12),"DEC");

		//invalid
		assertEquals(Date.convertToMonth(-1),"UNKNOWN");
		assertEquals(Date.convertToMonth(0),"UNKNOWN");
		assertEquals(Date.convertToMonth(13),"UNKNOWN");
		assertEquals(Date.convertToMonth(99),"UNKNOWN");
		
		System.out.println("Finished testDate3:");
	}

	
	public void testDate4() throws Exception{
		System.out.println("\nStarting testDate4: Tests validation method...");
		
		//						month / date / year
		CheckValidInput.validateDateArguments(1, 1, 1111);
		CheckValidInput.validateDateArguments(1, 2, 2012);
		CheckValidInput.validateDateArguments(1, 31, 3012);
		CheckValidInput.validateDateArguments(12, 31, 9999);
		CheckValidInput.validateDateArguments(6, 30, 2011);
		
		try {
			//invalid day:
			CheckValidInput.validateDateArguments(1, 99 ,	 2012);//jan <=31
			CheckValidInput.validateDateArguments(2, 30, 	 2012);//feb <=29, leap year
			CheckValidInput.validateDateArguments(6, 31, 	 2012);//jun <=30
			CheckValidInput.validateDateArguments(3, -1, 	 2012);
			CheckValidInput.validateDateArguments(6, -9999, 2012);
			CheckValidInput.validateDateArguments(5, 654,   2012);
	
			//invalid year:
			CheckValidInput.validateDateArguments(1, 4, -1029);
			CheckValidInput.validateDateArguments(1, 4, -200);
	
			//invalid month
			CheckValidInput.validateDateArguments(0,     1, 2011);
			CheckValidInput.validateDateArguments(-294,  1, 2011);
			CheckValidInput.validateDateArguments(13,    1, 2011);
			
			//invalid month and day
			CheckValidInput.validateDateArguments(99, -1, 2012);
			CheckValidInput.validateDateArguments(0, 284, 2012);
			CheckValidInput.validateDateArguments(13, 0,  2012);
			
			//invalid month and year
			CheckValidInput.validateDateArguments(-1, 1, -1000);
			CheckValidInput.validateDateArguments(99, 1, 0);
			CheckValidInput.validateDateArguments(0,  1, -99);
	
			//invalid month, day and year
			CheckValidInput.validateDateArguments(99,99 ,-99 );
			CheckValidInput.validateDateArguments(99, 99, -2012);
			CheckValidInput.validateDateArguments(99,1 , 02012);


		}
		catch(IllegalArgumentException e) {
			
		}
		
		
		System.out.println("Finished testDate4:");
	}

	public void testDate5() throws Exception{
		System.out.println("\nStarting testDate5: Tests constructing with arguments and comparing");
		
		Date yesterday = new Date(2,27,2013);
		Date today = new Date(2,28,2013);
		Date tomorrow = new Date(3,1,2013);
		
		assertNotNull(yesterday);
		assertTrue(yesterday instanceof Date);
		assertEquals(yesterday.toString(), "FEB 27, 2013");
		assertNotSame(yesterday.toString(), "MAR 1, 2013");

		assertNotNull(today);
		assertTrue(today instanceof Date);
		assertEquals(today.toString(), "FEB 28, 2013");
		assertNotSame(today.toString(), "MAR 1, 2013");

		
		assertNotNull(tomorrow);
		assertTrue(tomorrow instanceof Date);
		assertEquals(tomorrow.toString(), "MAR 1, 2013");
		assertNotSame(tomorrow.toString(), "MAR 2, 2013");
		
		
		assertTrue(tomorrow.compareTo(yesterday) > 0);
		assertTrue(tomorrow.compareTo(tomorrow) == 0);
		assertFalse(tomorrow.compareTo(yesterday) == 0);
		assertFalse(tomorrow.compareTo(yesterday) < 0);
		
		
		assertTrue(tomorrow.equals(tomorrow));
		assertFalse(tomorrow.equals(null));
		assertFalse(tomorrow.equals(yesterday));
		
	
		
		System.out.println("Finished testDate5:");
	}
	
	public void testDate6() throws Exception{
		System.out.println("\nStarting testDate5: Tests constructing with arguments and comparing");
		
		Date yesterday = new Date(2,28,2012);
		Date today = new Date(2,29,2012);
		Date tomorrow = new Date(3,1,2012);
		
		assertNotNull(yesterday);
		assertTrue(yesterday instanceof Date);
		assertEquals(yesterday.toString(), "FEB 28, 2012");
		assertNotSame(yesterday.toString(), "FEB 29, 2012");

		assertNotNull(today);
		assertTrue(today instanceof Date);
		assertEquals(today.toString(), "FEB 29, 2012");
		assertNotSame(today.toString(), "MAR 1, 2012");

		
		assertNotNull(tomorrow);
		assertTrue(tomorrow instanceof Date);
		assertEquals(tomorrow.toString(), "MAR 1, 2012");
		assertNotSame(tomorrow.toString(), "MAR 2, 2012");
		
		
		assertTrue(tomorrow.compareTo(yesterday) > 0);
		assertTrue(tomorrow.compareTo(tomorrow) == 0);
		assertFalse(tomorrow.compareTo(yesterday) == 0);
		assertFalse(tomorrow.compareTo(yesterday) < 0);
		
		
		assertTrue(tomorrow.equals(tomorrow));
		assertFalse(tomorrow.equals(null));
		assertFalse(tomorrow.equals(yesterday));
		
	
		
		System.out.println("Finished testDate6:");
	}
	
	
}
