package tests.integration;

import java.util.LinkedList;

import junit.framework.TestCase;
import hns.application.Main;
import hns.application.Services;
import hns.businesslogic.AccessPatients;
import hns.enums.ActivityLevel;
import hns.enums.Gender;
import hns.objects.Patient;
import hns.persistence.DataAccessObject;
import hns.persistence.DataAccessStub;

public class AccessPatientsTest extends TestCase{
	
	private static String dbName = Main.dbName;
	
	public AccessPatientsTest(String arg0){
		
		super(arg0);
	}
	
	public void testAccessPatients() throws Exception{
		System.out.println("\nStarting Integration test: testAccessPatients");
		
		Services.closeDataAccess();
		Services.createDataAccess(new DataAccessObject(dbName));
		
		AccessPatients access;
		LinkedList<Patient> patientList;
		
		access = new AccessPatients();
		assertNotNull(access);

		patientList = access.getPatientList();
		assertNotNull(patientList);
		assertNotNull(patientList.getFirst());
		assertEquals(patientList.getFirst().getUserID(), 0);
		assertEquals(patientList.getFirst().getFirstName(), "Matt");
		assertEquals(patientList.getFirst().getLastName(), "Damon");
		assertEquals(patientList.getFirst().getAge(), 41);
		assertEquals(patientList.getFirst().getWeight(), 72);
		assertEquals(patientList.getFirst().getHeight(), 178);
		assertEquals(patientList.getFirst().getGender(), Gender.MALE);
		assertEquals(patientList.getFirst().getActivityLevel(), ActivityLevel.LIGHT);

		assertNotNull(patientList.get(1));
		assertEquals(patientList.get(1).getUserID(), 1);
		assertEquals(patientList.get(1).getFirstName(), "Lara");
		assertEquals(patientList.get(1).getLastName(), "Croft");
		assertEquals(patientList.get(1).getAge(), 21);
		assertEquals(patientList.get(1).getWeight(), 60);
		assertEquals(patientList.get(1).getHeight(), 176);
		assertEquals(patientList.get(1).getGender(), Gender.FEMALE);
		assertEquals(patientList.get(1).getActivityLevel(), ActivityLevel.EXTRA);
		
		Patient patient = new Patient(200,  1, "Doe", "John", 20, 70, 175, Gender.MALE, ActivityLevel.LIGHT);
		access.insertPatient(patient);
		
		patient = access.getPatientList().getLast();
		assertNotNull(patient);
		assertEquals(200, patient.getUserID());
		assertEquals(1, patient.getJournalID());
		assertEquals("Doe", patient.getLastName());
		assertEquals("John", patient.getFirstName());
		assertEquals(20, patient.getAge());
		assertEquals(70, patient.getWeight());
		assertEquals(175, patient.getHeight());
		assertEquals(Gender.MALE, patient.getGender());
		assertEquals(ActivityLevel.LIGHT, patient.getActivityLevel());

		Services.closeDataAccess();
		System.out.println("Finished Integration test: testAccessPatients");
	}
	
	
	
}
