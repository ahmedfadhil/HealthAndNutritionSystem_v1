package tests.businesslogicTests;

import junit.framework.TestCase;
import hns.businesslogic.CalculateBMI;

public class CalculateBMITest extends TestCase {
	
	public void testInvalidFieldsBMI1() throws Exception{
		System.out.println("\nStarting testInvalidFieldsBMI1: zero weight");
		
		try {
			CalculateBMI.determineBMI(0, 45);
		} catch (IllegalArgumentException e) {
			
		}
		
		System.out.println("Finished testInvalidFieldsBMI1: zero weight");
	}
	
	public void testInvalidFieldsBMI2() throws Exception{
		System.out.println("\nStarting testInvalidFieldsBMI2: zero height");
		
		try {
			CalculateBMI.determineBMI(45, 0);
		} catch (IllegalArgumentException e) {
			
		}
		
		System.out.println("Finished testInvalidFieldsBMI2: zero height");
	}
	
	public void testInvalidFieldsBMI3() throws Exception{
		System.out.println("\nStarting testInvalidFieldsBMI3: zero weight and height");

		try {
			CalculateBMI.determineBMI(0, 0);
		} catch (IllegalArgumentException e) {
			
		}
		
		System.out.println("Finished testInvalidFieldsBMI3: zero weight and height");	
	}
	
	public void testInvalidFieldsBMI4() throws Exception{
		System.out.println("\nStarting testInvalidFieldsBMI4: negative weight");
		
		try {
			CalculateBMI.determineBMI(-200, 50);
		} catch (IllegalArgumentException e) {
			
		}
		
		System.out.println("Finished testInvalidFieldsBMI4: negative weight");	
	}
	
	public void testInvalidFieldsBMI5() throws Exception{
		System.out.println("\nStarting testInvalidFieldsBMI5: negative height");
		
		try {
			CalculateBMI.determineBMI(50, -200);
		} catch (IllegalArgumentException e) {
			
		}
		
		System.out.println("Finished testInvalidFieldsBMI5: negative height");
	}
	public void testInvalidFieldsBMI6() throws Exception{
		System.out.println("\nStarting testInvalidFieldsBMI6: negative weight and height");
		
		try {
			CalculateBMI.determineBMI(-50, -200);
		} catch (IllegalArgumentException e) {
			
		}
		
		System.out.println("Finished testCalculateBMI6: negative weight and height");
	}
	
	public void testCalculateBMI7() throws Exception{
		System.out.println("\nStarting testCalculateBMI7: out of boundary weight");
		
		try {
			CalculateBMI.determineBMI(55555555, 200);
		} catch (IllegalArgumentException e) {
			
		}
		
		System.out.println("Finished testCalculateBMI7: out of boundary weight");
	}
	
	public void testCalculateBMI8() throws Exception{
		System.out.println("\nStarting testCalculateBMI8: out of boundary height");
		
		try {
			CalculateBMI.determineBMI(50, 200000000);
		} catch (IllegalArgumentException e) {
			
		}
		
		System.out.println("Finished testCalculateBMI8: out of boundary height");
	}
	
	public void testCalculateBMI9() throws Exception{
		System.out.println("\nStarting testCalculateBMI9: out of boundary weight and height");
		
		try {
			CalculateBMI.determineBMI(555555555, 200000000);
		} catch (IllegalArgumentException e) {
			
		}
		
		System.out.println("Finished testCalculateBMI9: out of boundary weight and height");
	}

	
	public void testBMIKnownResults() throws Exception{
		System.out.println("\nStarting testBMIKnownResults");
		
		//test arbitrarily because testing for every possible value is not conceivable
		assertEquals(10.0, CalculateBMI.determineBMI(42, 205));
		assertEquals(13.0, CalculateBMI.determineBMI(42, 180));
		assertEquals(15.6, CalculateBMI.determineBMI(45, 170));
		assertEquals(17.3, CalculateBMI.determineBMI(69, 200));
		assertEquals(19.7, CalculateBMI.determineBMI(57, 170));
		assertEquals(22.0, CalculateBMI.determineBMI(68, 176));
		assertEquals(27.0, CalculateBMI.determineBMI(69, 160));
		assertEquals(29.1, CalculateBMI.determineBMI(57, 140));
		assertEquals(30.0, CalculateBMI.determineBMI(126, 205));
		assertEquals(31.1, CalculateBMI.determineBMI(90, 170));
		assertEquals(36.2, CalculateBMI.determineBMI(87, 155));
		assertEquals(40,8, CalculateBMI.determineBMI(111, 165));
		assertEquals(41.3, CalculateBMI.determineBMI(93, 150));
		assertEquals(58.5, CalculateBMI.determineBMI(123, 145));
		assertEquals(55.1, CalculateBMI.determineBMI(108, 140));
		assertEquals(64.3, CalculateBMI.determineBMI(126, 140));

		System.out.println("Finished testBMIKnownResults");
	}
	
	public void testBMIIncorrectResults() throws Exception{
		System.out.println("\nStarting testBMIIncorrectResults");
		
		assertTrue(10.1 != CalculateBMI.determineBMI(42, 205));
		assertTrue(9.9 != CalculateBMI.determineBMI(42, 205));
		assertTrue(13.1 != CalculateBMI.determineBMI(42, 180));
		assertTrue(12.9 != CalculateBMI.determineBMI(42, 180));
		assertTrue(15.7 != CalculateBMI.determineBMI(45, 170));
		assertTrue(15.5 != CalculateBMI.determineBMI(45, 170));
		assertTrue(17.4 != CalculateBMI.determineBMI(69, 200));
		assertTrue(17.2 != CalculateBMI.determineBMI(69, 200));
		assertTrue(19.8 != CalculateBMI.determineBMI(57, 170));
		assertTrue(19.6 != CalculateBMI.determineBMI(57, 170));
		assertTrue(21.0 != CalculateBMI.determineBMI(68, 176));
		assertTrue(23.0 != CalculateBMI.determineBMI(68, 176));
		assertTrue(26.0 != CalculateBMI.determineBMI(69, 160));
		assertTrue(28.0 != CalculateBMI.determineBMI(69, 160));
		assertTrue(28.1 != CalculateBMI.determineBMI(57, 140));
		assertTrue(30.1 != CalculateBMI.determineBMI(57, 140));
		assertTrue(31.0 != CalculateBMI.determineBMI(126, 205));
		assertTrue(33.0 != CalculateBMI.determineBMI(126, 205));
		assertTrue(32.1 != CalculateBMI.determineBMI(90, 170));
		assertTrue(30.1 != CalculateBMI.determineBMI(90, 170));
		assertTrue(46.3 != CalculateBMI.determineBMI(87, 155));
		assertTrue(50.9 != CalculateBMI.determineBMI(111, 165));
		assertTrue(31.4 != CalculateBMI.determineBMI(93, 150));
		assertTrue(68.6 != CalculateBMI.determineBMI(123, 145));
		assertTrue(45.2 != CalculateBMI.determineBMI(108, 140));
		assertTrue(74.4 != CalculateBMI.determineBMI(126, 140));

		System.out.println("Finished testBMIIncorrectResults");
	}
	
	public void testBMIExtremes() throws Exception{
		System.out.println("\nStarting testBMIExtremes");
		
		assertTrue(CalculateBMI.SMALLVALUE <=CalculateBMI.determineBMI(42, 205));
		assertTrue(CalculateBMI.SMALLVALUE<=CalculateBMI.determineBMI(42, 180));
		assertTrue(CalculateBMI.SMALLVALUE<= CalculateBMI.determineBMI(69, 200));
		assertTrue(CalculateBMI.SMALLVALUE<= CalculateBMI.determineBMI(1, 200));		
		assertTrue(CalculateBMI.SMALLVALUE<= CalculateBMI.determineBMI(1, 299));
		assertTrue(CalculateBMI.SMALLVALUE<= CalculateBMI.determineBMI(140, 1));
		assertTrue(CalculateBMI.SMALLVALUE<= CalculateBMI.determineBMI(100, 2));

		System.out.println("Finished testBMIExtremes");
	}
	
}

