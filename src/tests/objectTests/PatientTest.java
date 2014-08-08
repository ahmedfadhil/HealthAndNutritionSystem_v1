package tests.objectTests;

import hns.enums.ActivityLevel;
import hns.enums.Gender;
import hns.objects.Patient;
import junit.framework.TestCase;

public class PatientTest extends TestCase {
	
	public PatientTest(String arg0){
		
		super(arg0);
	}
	
	public void testInvalid1() throws Exception{
		System.out.println("\nStarting testPatient: negative, zero and null input");
		try{
			
			new Patient(200, 1, "Doe", "John", -20, 70, 175, Gender.MALE, ActivityLevel.LIGHT);
			new Patient(200, 1, "Doe", "John", 20, -70, 175, Gender.MALE, ActivityLevel.LIGHT);
			new Patient(200, 1, "Doe", "John", 20, 70, -175, Gender.MALE, ActivityLevel.LIGHT);
			new Patient(200, 1, "Doe", "John", 0, 70, 175, Gender.MALE, ActivityLevel.LIGHT);
			new Patient(200, 1, "Doe", "John", 20, 0, 175, Gender.MALE, ActivityLevel.LIGHT);
			new Patient(200, 1, "Doe", "John", 20, 70, 0, Gender.MALE, ActivityLevel.LIGHT);
			new Patient(200, 1, "Doe", "John", 20, 70, 175, null, ActivityLevel.LIGHT);
			new Patient(200, 1, "Doe", "John", 20, 70, 175, Gender.MALE, null);
			
		}
		catch (IllegalArgumentException e){
			
		}
		System.out.println("Finished testPatient: negative, zero and null input");

	}
	

	public void testInvalid2() throws Exception{
		System.out.println("\nStarting testPatient: out of boundary input");
		try{
			
			new Patient(200, 1, "Doe", "John", 150, 70, 175, Gender.MALE, ActivityLevel.LIGHT);
			new Patient(200, 1, "Doe", "John", 15000, 70, 175, Gender.MALE, ActivityLevel.LIGHT);
			new Patient(200, 1, "Doe", "John", 111111111, 70, 175, Gender.MALE, ActivityLevel.LIGHT);
			new Patient(200, 1, "Doe", "John", 20, 500, 175, Gender.MALE, ActivityLevel.LIGHT);
			new Patient(200, 1, "Doe", "John", 20, 5000000, 175, Gender.MALE, ActivityLevel.LIGHT);
			new Patient(200, 1, "Doe", "John", 20, 33333333, 175, Gender.MALE, ActivityLevel.LIGHT);
			new Patient(200, 1, "Doe", "John", 20, 70, 300, Gender.MALE, ActivityLevel.LIGHT);
			new Patient(200, 1, "Doe", "John", 20, 70, 300000000, Gender.MALE, ActivityLevel.LIGHT);
			new Patient(200, 1, "Doe", "John", 20, 70, 1111111111, Gender.MALE, ActivityLevel.LIGHT);
		
		}
		catch (IllegalArgumentException e){
			
		}
		System.out.println("Finished testPatient: out of boundary input");

	}

	public void testInvalid3() throws Exception{
		System.out.println("\nStarting testPatient: negative, zero and null update");
		
		Patient p = new Patient(200, 1, "Doe", "John", 20, 70, 175, Gender.MALE, ActivityLevel.LIGHT);
		try{
			
			p.updateAge(-20);
			p.updateAge(0);
			p.updateWeight(-70);
			p.updateWeight(0);
			p.updateHeight(-175);
			p.updateHeight(0);
			p.updateGender(null);
			p.updateActivityLevel(null);		
		}
		catch (IllegalArgumentException e){
			
		}
		System.out.println("Finished testPatient: negative, zero and null update");

	}
	
	public void testInvalid4() throws Exception{
		System.out.println("\nStarting testPatient: out of boundary update");
		
		Patient p = new Patient(200, 1, "Doe", "John", 20, 70, 175, Gender.MALE, ActivityLevel.LIGHT);
		try{
			p.updateAge(150);
			p.updateAge(15000);
			p.updateAge(111111111);
			p.updateWeight(500);
			p.updateWeight(5000000);
			p.updateWeight(5000000);
			p.updateHeight(300);
			p.updateHeight(300000000);
			p.updateHeight(1111111111);
			
		}
		catch (IllegalArgumentException e){
			
		}
		System.out.println("Finished testPatient: out of boundary update");

	}	
	
	public void testWithinLevelValues() throws Exception {
		System.out.println("\nStarting testPatient: valid input and update");

		Patient patient;
		patient = new Patient(200,  1, "Doe", "John", 20, 70, 175, Gender.MALE, ActivityLevel.LIGHT);
		
		assertNotNull(patient);
		assertTrue(patient instanceof Patient);
		assertEquals(200, patient.getUserID());
		assertEquals(1, patient.getJournalID());
		assertEquals("Doe", patient.getLastName());
		assertEquals("John", patient.getFirstName());
		assertEquals(20, patient.getAge());
		assertEquals(70, patient.getWeight());
		assertEquals(175, patient.getHeight());
		assertEquals(Gender.MALE, patient.getGender());
		assertEquals(ActivityLevel.LIGHT, patient.getActivityLevel());
		assertEquals(22.9, patient.getBMI());
		assertEquals("Normal", patient.getWeightLevel());
		assertEquals(2433, patient.getCalories());
		
		patient.updateAge(21);
		assertEquals(21, patient.getAge());
		
		patient.updateWeight(68);
		assertEquals(68, patient.getWeight());
		
		patient.updateHeight(176);
		assertEquals(176, patient.getHeight());
				
		
		patient.updateGender(Gender.FEMALE);
		assertEquals(Gender.FEMALE, patient.getGender());		
		
		patient.updateActivityLevel(ActivityLevel.MODERATE);
		assertEquals(ActivityLevel.MODERATE, patient.getActivityLevel());
		
		assertEquals(22.0, patient.getBMI());
		assertEquals("Normal", patient.getWeightLevel());
		assertEquals(2376, patient.getCalories());

		System.out.println("Finished testPatient: valid input and update");
		
	}

}

