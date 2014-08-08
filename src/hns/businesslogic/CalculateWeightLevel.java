package hns.businesslogic;

public class CalculateWeightLevel {
	public static String determineWeightLevel(double bmi) throws Exception {
		
		final String[] WT_CATEGORY = {"Severely underweight","Underweight","Normal","Overweight","Obese Class I", "Obese Class II", "Obese Class III"};
		int index=0;
		
		try{
			if(bmi>=0 && bmi<16.0) index = 0;
			else if(bmi>=16.0 && bmi<18.5) index = 1;
			else if(bmi>=18.5 && bmi<25.0) index = 2;
			else if(bmi>=25.0 && bmi<30.0) index = 3;
			else if(bmi>=30.0 && bmi<35.0) index = 4;
			else if(bmi>=35.0 && bmi<40.0) index = 5;
			else if(bmi>=40.0) index = 6;
		}catch(IllegalArgumentException e){
			
		}

		return WT_CATEGORY[index];
	}

}

