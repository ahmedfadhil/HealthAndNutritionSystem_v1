package hns.enums;

public enum ActivityLevel {
	
	  SEDENTARY, LIGHT, MODERATE, VERY, EXTRA, UNSPECIFIED;

	  public String toString() {
		  
	    switch(this) {
	    
	      case SEDENTARY:   return "Sedentary";
	      case LIGHT:   	return "Light";
	      case MODERATE: 	return "Moderate";
	      case VERY:   		return "Very";
	      case EXTRA: 		return "Extra";
	      default:       	return "Unspecified";
	    }
	  }

}

