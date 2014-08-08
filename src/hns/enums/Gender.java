package hns.enums;

public enum Gender {
	
	FEMALE, MALE, UNSPECIFIED;
	
	public String toString() {
		
		  switch(this) {
		  
		    case FEMALE:   	return "Female";
		    case MALE:   	return "Male";
		    default:       	return "Unspecified";
		    
		    }
		  }
}

