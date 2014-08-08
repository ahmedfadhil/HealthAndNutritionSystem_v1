package hns.businesslogic;

import hns.objects.Journal;
import hns.objects.Patient;
import hns.objects.Record;

public class GiveRecommendations {

	/*
	Recommendations:
	Based on your current profile, your ideal weight range is *** - ***kg, 
	and your ideal daily net intake calories range is *** - *** kcal.
	*/
	public static String giveRecommendations(Patient patient, Journal journal) throws Exception{
		
		String recommendation;
		String targetRange =  CalculateTargetCalories.determineTargetCalorieRange(patient);
		String idealWeightRange = CalculateTargetWeight.determineTargetRangeWeight(patient.getHeight());
		
		recommendation = "Based on your current profile, your ideal weight range is " + idealWeightRange + " kg,";
		recommendation +="\nand your ideal daily net intake calories range is " + targetRange + " kcal.";

		return recommendation;
	}
	

	public static String giveDailyCalorieAnalysis(Patient patient, Record record) throws Exception{
		return giveAnalysisAux(patient, record);
	}
	
	public static String giveOverAllCalorieAnalysis(Patient patient, Journal journal) throws Exception{
		return giveAnalysisAux(patient, journal);
	}
	
	//source might be a Record Object (Daily Analysis) or a Journal Object (Overall Analysis)
	private static String giveAnalysisAux(Patient patient, Object source) throws Exception{
	
		String analysis;
		long totalCalorieIntaken=0L; 
		long totalCalorieBurned=0L;
		long net_Calories=0L;

		/*
		Dear ***(First name),
		Today your intake calories amount from food is *** kcal, your burned 
		calories amount from activities is *** kcal, and your net intake 
		calories amount is *** kcal.
		*/
		analysis =	"Dear " + patient.getFirstName() + ",\n";
		if (source instanceof Record){
			Record record = (Record) source;
			totalCalorieIntaken = CalculateRealCalories.calculateDailyTotalIntakenCalories(record.getArrayListOfFood());
			totalCalorieBurned = CalculateRealCalories.calculateDailyTotalBurnedCalories(record.getArrayListOfActivity());
			net_Calories = CalculateRealCalories.calculateDailyNetCalories(record);
			analysis += "Today your intake calories amount from food is " + totalCalorieIntaken + " kcal, ";
		}
		
		/*
		Dear ***(First name),
		From *** *** ****(date)  to  *** *** *** (date) average per day, your intake calories amount from food is *** kcal, 
		your burned calories amount from activities is *** kcal, and your net intake calories amount is *** kcal.
		*/
		else if (source instanceof Journal){
			Journal journal = (Journal) source;
			totalCalorieIntaken = CalculateRealCalories.calculateAverageTotalIntakenCalories(journal);
			totalCalorieBurned = CalculateRealCalories.calculateAverageTotalBurnedCalories(journal);
			net_Calories = CalculateRealCalories.calculateAverageNetCalories(journal);
			analysis += "From " + journal.getDateOfFirstRecord() + " to " + journal.getDateOfLastRecord() + " average per day, \nyour intake calories amount from food is " + totalCalorieIntaken + " kcal, ";
		}
		
		analysis += "\nyour burned calories amount from activities is " + totalCalorieBurned + " kcal, ";
		analysis +=	"\nand your net intake calories amount is " + net_Calories + " kcal.";
		
		return analysis;
		
	}
}
