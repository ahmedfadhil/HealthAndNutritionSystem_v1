package hns.businesslogic;

import hns.objects.Patient;

public class CalculateTargetCalories {
	
	
	public static String determineTargetCalorieRange(Patient patient) throws Exception{
		if (patient!=null){
			return determineCalorieRange_LowerBound(patient) + " - " + determineCalorieRange_UpperBound(patient);			
		}else{
			throw new NullPointerException("Patient not found");
		}
	}
	
	public static int determineCalorieRange_UpperBound(Patient patient) throws Exception{
		return determineCalorieRangeAux(patient, "UPPER");
	}
	
	public static int determineCalorieRange_LowerBound(Patient patient) throws Exception{
		return determineCalorieRangeAux(patient, "LOWER");
	}
	
	private static int determineCalorieRangeAux(Patient patient, String mode) throws Exception{
		if (patient!=null) {
			int height = patient.getHeight();
			int bound;
			
			if (mode.equalsIgnoreCase("UPPER")){
				bound = CalculateTargetWeight.determineHigherBoundNormalWeight(height);
			}else{ // if (mode.equalsIgnoreCase("LOWER"))
				bound = CalculateTargetWeight.determineLowerBoundNormalWeight(height);
			}
			return CalculateRequiredCalories.determineCalories(bound, height, patient.getAge(), patient.getGender(), patient.getActivityLevel());
			
		}else{
			throw new NullPointerException("Patient not found");
		}
	}
}
