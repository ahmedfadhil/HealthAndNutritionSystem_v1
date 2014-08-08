package hns.objects;

public class Activity {
	
	private String name;
	private int caloriesBurned;
	private int durationMinutes;
	private int normalizedCalories; 
	
	private final int ACTIVITY_CALORIES_BASE = 60; //kcal per 60 minutes.
	
	public Activity(String name, int caloriesBurned, int durationMinutes){
		
		this.name = name;
		this.caloriesBurned = caloriesBurned;
		this.durationMinutes = durationMinutes;
		normalizedCalories = normalizeCalories(durationMinutes);
	}
	
	
	private int normalizeCalories(int duration){
		return (int)(1.0*caloriesBurned*duration/ACTIVITY_CALORIES_BASE+0.5);
	}
	
	public int getNormalizedCalories(){
		return normalizedCalories;
	}
	
	public String getName(){
		
		return name;
	}
	
	public int getCalories(){
		
		return caloriesBurned;
	}
	
	public int getDuration(){
		
		return durationMinutes;
	}
	
	public void setDuration(int durationMinutes) {
		this.durationMinutes = durationMinutes;
	}
	
	public String toString(){
		String str = name + " - " + durationMinutes;
		if (durationMinutes > 1)
			str +=" minutes.";
		else
			str +=" minute.";
		return str;
	}
	
	public boolean equals(Object item){
		if(item instanceof Activity)
			if (this.name == ((Activity)item).name)
				return true;
		return false;
	}


}
