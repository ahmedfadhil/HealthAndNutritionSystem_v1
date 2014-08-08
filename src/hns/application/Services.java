package hns.application;

import hns.persistence.DataAccess;
import hns.persistence.DataAccessObject;
import hns.persistence.DataAccessStub;

public class Services{

	private static DataAccess dataAccessService = null;

	public static DataAccess createDataAccess(DataAccess otherDataAccessService) throws Exception{
		
		if (dataAccessService == null){
			
			dataAccessService = otherDataAccessService;
			dataAccessService.open(Main.dbName);
		}
		return dataAccessService;
	}
	

	public static DataAccess createDataAccess(String dbName) throws Exception{
		
		if (dataAccessService == null){
			
			dataAccessService = new DataAccessObject(dbName);	//real db
			dataAccessService.open(Main.dbName);
		}
		return dataAccessService;
	}

	public static DataAccess getDataAccess(String dbName){
		
		if (dataAccessService == null){
			
			System.out.println("Connection to data access has not been established.");
			System.exit(1);
		}
		return dataAccessService;
	}

	public static void closeDataAccess(){
		
		if (dataAccessService != null){
			
			dataAccessService.close();
		}
		dataAccessService = null;
	}
}