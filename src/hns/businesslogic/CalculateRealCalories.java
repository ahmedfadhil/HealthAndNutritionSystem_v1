package hns.businesslogic;

import hns.objects.Activity;
import hns.objects.Food;
import hns.objects.Journal;
import hns.objects.Record;


public class CalculateRealCalories {
		
	public static long calculateDailyNetCalories(Record record) throws Exception{
		if (record!=null){
			long intakeCalories = calculateDailyTotalIntakenCalories(record.getArrayListOfFood());
			long burnedCalories = calculateDailyTotalBurnedCalories(record.getArrayListOfActivity());
			return intakeCalories - burnedCalories;
		}else{
			throw new NullPointerException("Record not found");
		}
		
	}
	
	public static long calculateAverageNetCalories(Journal journal) throws Exception{
		
		if (journal!=null){
			long averageIntakeCalories = calculateAverageTotalIntakenCalories(journal);
			long averageBurnedCalories = calculateAverageTotalBurnedCalories(journal);
			return averageIntakeCalories - averageBurnedCalories;
		}else{
			throw new NullPointerException("Journal not found");
		}
	}
	
	public static long calculateAverageTotalIntakenCalories(Journal journal) throws Exception{
		return calculateAverageTotalIntakenORBurnedAux(journal, "INTAKEN");
	}
	
	public static long calculateAverageTotalBurnedCalories(Journal journal) throws Exception{
		return calculateAverageTotalIntakenORBurnedAux(journal, "BURNED");
	}
	
	private static long calculateAverageTotalIntakenORBurnedAux(Journal journal, String mode) throws Exception{
		if (journal == null){
			throw new NullPointerException("Journal not found.");
		}else{
		
			long total = 0L;
			Object[] recordList = journal.getArrayListOfRecords();
			int numRecords = recordList.length;
			
			for(int i=0; i<numRecords;i++){
				if (mode.equalsIgnoreCase("INTAKEN"))
					total += calculateDailyTotalIntakenCalories(((Record)recordList[i]).getArrayListOfFood());
				else //if (mode.equalsIgnoreCase("BURNED")){
					total += calculateDailyTotalBurnedCalories(((Record)recordList[i]).getArrayListOfActivity());
			}	
			return total/numRecords;
		}
	}
	
	public static long calculateDailyTotalIntakenCalories(Object[] foodList) throws Exception{
		return calculateDailyTotalBurnedORIntakenCaloriesAux(foodList);
	}
	
	public static long calculateDailyTotalBurnedCalories(Object[] activityList) throws Exception{
		return calculateDailyTotalBurnedORIntakenCaloriesAux(activityList);
	}	
	
	private static long calculateDailyTotalBurnedORIntakenCaloriesAux(Object[] list) throws Exception{
		if (list == null)
			throw new NullPointerException ("Array List not found.");
		else{
			long total = 0L;
			for (int i=0; i<list.length;i++){
				if (list[i] instanceof Food)
					total += ((Food)list[i]).getNormalizedCalories();
				else if (list[i] instanceof Activity)
					total += ((Activity)list[i]).getNormalizedCalories();			
			}
			return total;
		}
	}
	

}
