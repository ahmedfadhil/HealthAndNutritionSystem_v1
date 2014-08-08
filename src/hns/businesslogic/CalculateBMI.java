package hns.businesslogic;

import hns.objects.Patient;

public class CalculateBMI{	
	
	public static double SMALLVALUE=0.0000001;
	
	public static double determineBMI(int weight, int height) throws Exception{
		
		
		double bmi=0;
		boolean validWeight = CheckValidInput.validInputNum(weight, Patient.WEIGHT_LOWER_BOUND, Patient.WEIGHT_HIGHER_BOUND );
		boolean validHeight = CheckValidInput.validInputNum(height, Patient.HEIGHT_LOWER_BOUND, Patient.HEIGHT_HIGHER_BOUND );
		
		try{
			if(validWeight && validHeight) {
				bmi = (double)weight / (double)Math.pow(height/100.0, 2);	
				if (bmi<SMALLVALUE)
					bmi=SMALLVALUE;
			}
			
		}catch(IllegalArgumentException e){
			
		} 
			
		return (int)Math.round(bmi*10)/10.0;

	}

}
