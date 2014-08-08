package tests.businesslogicTests;

import hns.businesslogic.CheckValidInput;
import hns.objects.Patient;
import junit.framework.TestCase;

public class CheckValidInputTest extends TestCase {
	
	public void testInvalidInput() throws Exception{
		System.out.println("\nStarting testInvalidInput: test invalid number, user id, name and date input");
		
		try{
			assertFalse(CheckValidInput.validInputNum(150, Patient.AGE_LOWER_BOUND, Patient.AGE_HIGHER_BOUND));
			assertFalse(CheckValidInput.validInputNum(0, Patient.AGE_LOWER_BOUND, Patient.AGE_HIGHER_BOUND));
			assertFalse(CheckValidInput.validInputNum(-1111, Patient.AGE_LOWER_BOUND, Patient.AGE_HIGHER_BOUND));
			assertFalse(CheckValidInput.validInputNum(223343, Patient.AGE_LOWER_BOUND, Patient.AGE_HIGHER_BOUND));
			
			assertFalse(CheckValidInput.validInputNum(500, Patient.WEIGHT_LOWER_BOUND, Patient.WEIGHT_HIGHER_BOUND));
			assertFalse(CheckValidInput.validInputNum(0, Patient.WEIGHT_LOWER_BOUND, Patient.WEIGHT_HIGHER_BOUND));
			assertFalse(CheckValidInput.validInputNum(-3434343, Patient.WEIGHT_LOWER_BOUND, Patient.WEIGHT_HIGHER_BOUND));
			assertFalse(CheckValidInput.validInputNum(565645, Patient.WEIGHT_LOWER_BOUND, Patient.WEIGHT_HIGHER_BOUND));
			
			assertFalse(CheckValidInput.validInputNum(300, Patient.HEIGHT_LOWER_BOUND, Patient.HEIGHT_HIGHER_BOUND));
			assertFalse(CheckValidInput.validInputNum(0, Patient.HEIGHT_LOWER_BOUND, Patient.HEIGHT_HIGHER_BOUND));
			assertFalse(CheckValidInput.validInputNum(-4334343, Patient.HEIGHT_LOWER_BOUND, Patient.HEIGHT_HIGHER_BOUND));
			assertFalse(CheckValidInput.validInputNum(645466, Patient.HEIGHT_LOWER_BOUND, Patient.HEIGHT_HIGHER_BOUND));
			
			assertFalse(CheckValidInput.validInputName(null));		
			assertFalse(CheckValidInput.validInputName(""));	
			assertFalse(CheckValidInput.validInputName(" "));
			assertFalse(CheckValidInput.validInputName("  "));
			assertFalse(CheckValidInput.validInputName("   "));
			assertFalse(CheckValidInput.validInputName("                  "));
			assertFalse(CheckValidInput.validInputName(" BB"));
			assertFalse(CheckValidInput.validInputName(" bb "));
			assertFalse(CheckValidInput.validInputName("mm   "));
			
			assertFalse(CheckValidInput.validUserID(-1));
			assertFalse(CheckValidInput.validUserID(-5));
			assertFalse(CheckValidInput.validUserID(-4353));
			
			CheckValidInput.validateDateArguments(0, 31, 2011);
			CheckValidInput.validateDateArguments(3, 0, 2011);
			CheckValidInput.validateDateArguments(3, 31, 0);
			CheckValidInput.validateDateArguments(-1, 3, 2011);
			CheckValidInput.validateDateArguments(5, -26, 2011);
			CheckValidInput.validateDateArguments(3, 31, -2011);
			CheckValidInput.validateDateArguments(1, 32, 2011);
			CheckValidInput.validateDateArguments(14, 2, 2011);
			CheckValidInput.validateDateArguments(2, 29, 2011);
			CheckValidInput.validateDateArguments(2, 29, 2010);
			CheckValidInput.validateDateArguments(2, 30, 2012);
			CheckValidInput.validateDateArguments(2, 31, 2012);
			fail("should throw IllegalArgumentException");
			
		}catch(Exception e){
			
		}
		System.out.println("Finished testInvalidInput: test invalid number, user id, name and date input");
	}

	public void testValidInput() throws Exception{
		System.out.println("\nStarting testValidInput: test valid number, user id, name and date input");
		
		try{
			assertTrue(CheckValidInput.validInputNum(149, Patient.AGE_LOWER_BOUND, Patient.AGE_HIGHER_BOUND));
			assertTrue(CheckValidInput.validInputNum(5, Patient.AGE_LOWER_BOUND, Patient.AGE_HIGHER_BOUND));
			assertTrue(CheckValidInput.validInputNum(10, Patient.AGE_LOWER_BOUND, Patient.AGE_HIGHER_BOUND));
			assertTrue(CheckValidInput.validInputNum(90, Patient.AGE_LOWER_BOUND, Patient.AGE_HIGHER_BOUND));
			
			assertTrue(CheckValidInput.validInputNum(499, Patient.WEIGHT_LOWER_BOUND, Patient.WEIGHT_HIGHER_BOUND));
			assertTrue(CheckValidInput.validInputNum(1, Patient.WEIGHT_LOWER_BOUND, Patient.WEIGHT_HIGHER_BOUND));
			assertTrue(CheckValidInput.validInputNum(78, Patient.WEIGHT_LOWER_BOUND, Patient.WEIGHT_HIGHER_BOUND));
			assertTrue(CheckValidInput.validInputNum(200, Patient.WEIGHT_LOWER_BOUND, Patient.WEIGHT_HIGHER_BOUND));
			
			assertTrue(CheckValidInput.validInputNum(299, Patient.HEIGHT_LOWER_BOUND, Patient.HEIGHT_HIGHER_BOUND));
			assertTrue(CheckValidInput.validInputNum(1, Patient.HEIGHT_LOWER_BOUND, Patient.HEIGHT_HIGHER_BOUND));
			assertTrue(CheckValidInput.validInputNum(50, Patient.HEIGHT_LOWER_BOUND, Patient.HEIGHT_HIGHER_BOUND));
			assertTrue(CheckValidInput.validInputNum(164, Patient.HEIGHT_LOWER_BOUND, Patient.HEIGHT_HIGHER_BOUND));
			
			assertTrue(CheckValidInput.validInputName("a"));		
			assertTrue(CheckValidInput.validInputName("aa bb"));	
			assertTrue(CheckValidInput.validInputName("aa bb cc dd"));
			assertTrue(CheckValidInput.validInputName("Doe"));
			assertTrue(CheckValidInput.validInputName("John"));
			
			assertTrue(CheckValidInput.validUserID(0));
			assertTrue(CheckValidInput.validUserID(1));
			assertTrue(CheckValidInput.validUserID(004));
			assertTrue(CheckValidInput.validUserID(567788));
			
			
			CheckValidInput.validateDateArguments(1, 31, 2011);
			CheckValidInput.validateDateArguments(3, 1, 2011);
			CheckValidInput.validateDateArguments(4, 29, 1900);
			CheckValidInput.validateDateArguments(12, 5, 2013);
			CheckValidInput.validateDateArguments(2, 29, 2012);
			CheckValidInput.validateDateArguments(2, 28, 2011);
	
		}catch(Exception e){
			
		}
		System.out.println("Finished testValidInput: test valid number, user id, name and date input");
	}
	
	
	
}
