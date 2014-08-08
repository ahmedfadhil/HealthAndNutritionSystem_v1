package hns.businesslogic;

import hns.objects.Patient;

public class CalculateTargetWeight {
	
	public final static double NORMAL_BMI_LOWER_BOUND = 18.5;
	public final static double NORMAL_BMI_HIGHER_BOUND = 25.0;
	
	
	public static int determineLowerBoundNormalWeight(int height) throws Exception{
		if (CheckValidInput.validInputNum(height, Patient.HEIGHT_LOWER_BOUND, Patient.HEIGHT_HIGHER_BOUND))
			return (int)(Math.pow(height/100.0, 2)*NORMAL_BMI_LOWER_BOUND + 0.5);
		else
			throw new IllegalArgumentException("Invalid height");
	}
	
	public static int determineHigherBoundNormalWeight(int height) throws Exception{
		if(CheckValidInput.validInputNum(height, Patient.HEIGHT_LOWER_BOUND, Patient.HEIGHT_HIGHER_BOUND))
			return (int)(Math.pow(height/100.0, 2)*NORMAL_BMI_HIGHER_BOUND + 0.5);
		else
			throw new IllegalArgumentException("Invalid Height");
	}
	
	public static String determineTargetRangeWeight(int height) throws Exception{
		return determineLowerBoundNormalWeight(height) + " - " + determineHigherBoundNormalWeight(height);
	}
}
