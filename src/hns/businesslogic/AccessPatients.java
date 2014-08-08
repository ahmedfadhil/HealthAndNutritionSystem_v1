package hns.businesslogic;

import hns.application.Main;
import hns.application.Services;
import hns.objects.Patient;
import hns.persistence.DataAccess;

import java.util.LinkedList;


public class AccessPatients {
	
	private DataAccess dataAccess;
	
	public AccessPatients(){
		
		dataAccess = (DataAccess)Services.getDataAccess(Main.dbName);
	}
	
	public LinkedList<Patient> getPatientList(){
		
		return dataAccess.getPatientList();
	}

	public String insertPatient(Patient patient){
		
		//a journal gets created in a patient's constructor
		//so put journal in db here
		dataAccess.insertJournal(patient);	
		return dataAccess.insertPatient(patient);
	}
	
	public String updatePatient(Patient currentPatient){
		
		return dataAccess.updatePatient(currentPatient);
	}
	
	public int countPatients(){
		
		return dataAccess.countPatients();
	}
	
	



}
