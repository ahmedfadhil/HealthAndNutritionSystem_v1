package hns.objects;

import hns.businesslogic.CheckValidInput;

public class User {
	
	private int userID;
	private String userLastName;
	private String userFirstName;
	
	public User(int userID, String userLastName, String userFirstName) throws Exception{
		
		
		try{
			if(CheckValidInput.validUserID(userID )) {
				this.userID = userID;
			}
			
			if(CheckValidInput.validInputName(userLastName )) {
				this.userLastName = userLastName;
			}
			
			if(CheckValidInput.validInputName(userFirstName )) {
				this.userFirstName = userFirstName;
			}
			
		}catch(IllegalArgumentException e){
			
		}
		
	}
	
	public void updateLastName(String userLastName) throws Exception{
		
		try{
		
			if(CheckValidInput.validInputName(userLastName )) {
				this.userLastName = userLastName;
			}
			
		}catch(IllegalArgumentException e){
			
		}

	}
	
	public void updateFirstName(String userFirstName) throws Exception{
		
		try{
			if(CheckValidInput.validInputName(userFirstName )) {
				this.userFirstName = userFirstName;
			}
			
		}catch(IllegalArgumentException e){
			
		}
		
	}
	
	public int getUserID(){
		return userID;
	}
	
	public String getLastName(){
		return userLastName;
	}
	
	public String getFirstName(){
		return userFirstName;
	}

	public String toString(){
		
		return userFirstName + " " + userLastName;
	}

}

