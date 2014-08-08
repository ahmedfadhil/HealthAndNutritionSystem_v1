package hns.application;
import hns.application.Services;
import hns.presentation.SelectUserWindow;
public class Main{
	
	public static final String dbName = "DB";

	public static void main(String[] args) throws Exception{

		startUp();
		new SelectUserWindow();
		shutDown();
		System.out.println("All done");
	}

	public static void startUp() throws Exception{
		
		Services.createDataAccess(dbName);
	}

	public static void shutDown(){
		
		Services.closeDataAccess();
	}
}
