package hns.businesslogic;

import hns.enums.ActivityLevel;
import hns.enums.Gender;
import hns.objects.Patient;

/* Calories = BMR * (1 + Activity factor)
 * Formula: Harris-Benedict Principle 
 */

public class CalculateRequiredCalories {
	public static int determineCalories(int weightKg, int heightCm, int age, Gender gender, ActivityLevel actLevel) throws Exception{

		final double POUNDS_PER_KG = 2.20462262;
		final double INCH_PER_CM = 0.393700787;
		final double BASE_F = 655.0955;
		final double WEIGHT_COEF_F = 4.3378853749;
		final double HEIGHT_COEF_F = 4.6979840048;
		final double AGE_COEF_F = 4.6756;
		final double BASE_M = 66.473;
		final double WEIGHT_COEF_M = 6.2376208405;
		final double HEIGHT_COEF_M = 12.708382013;
		final double AGE_COEF_M = 6.755;
		
		double bmr=0;
		int calories=0;
		double weightPounds;
		double hightInch;
		double actCaloriePercent;
		
		boolean validAge = CheckValidInput.validInputNum(age, Patient.AGE_LOWER_BOUND, Patient.AGE_HIGHER_BOUND );
		boolean validWeight = CheckValidInput.validInputNum(weightKg, Patient.WEIGHT_LOWER_BOUND, Patient.WEIGHT_HIGHER_BOUND );
		boolean validHeight = CheckValidInput.validInputNum(heightCm, Patient.HEIGHT_LOWER_BOUND, Patient.HEIGHT_HIGHER_BOUND );
		boolean validGender = (gender != null);
		boolean validActLevel = (actLevel != null);
		
		try{
			if(validAge && validWeight && validHeight && validGender && validActLevel){
				weightPounds = weightKg * POUNDS_PER_KG;
				hightInch = heightCm * INCH_PER_CM;
				actCaloriePercent=0;
				
				if(actLevel.equals(ActivityLevel.SEDENTARY)) actCaloriePercent = 0.2;
				else if(actLevel.equals(ActivityLevel.LIGHT)) actCaloriePercent = 0.375;
				else if(actLevel.equals(ActivityLevel.MODERATE)) actCaloriePercent = 0.55;
				else if(actLevel.equals(ActivityLevel.VERY)) actCaloriePercent = 0.725;
				else if(actLevel.equals(ActivityLevel.EXTRA)) actCaloriePercent = 0.9;
				
			
				if(gender.equals(Gender.FEMALE)){
					bmr = BASE_F + WEIGHT_COEF_F*weightPounds + HEIGHT_COEF_F*hightInch - AGE_COEF_F*age;
				}
				
				else if(gender.equals(Gender.MALE)){
					bmr = BASE_M + WEIGHT_COEF_M*weightPounds + HEIGHT_COEF_M*hightInch - AGE_COEF_M*age;
				}
				if(bmr<0){
					bmr=0;
				}
				calories = (int)Math.round(bmr * (1+actCaloriePercent));
			}
		} catch(IllegalArgumentException e){
			
		}
		
		return calories;
	}

}

