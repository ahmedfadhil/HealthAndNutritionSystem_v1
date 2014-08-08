package hns.objects;


public class Food {
	
	private String ndbNo;
	private String name;
	private int weightGram;
	private int calories;
	private int normalizedCalories;
	
	private final int FOOD_CALORIES_BASE = 100; // kcal per 100g food
	
	
	public Food(String name, int calories, int weightGram){
		
		this.name = name;
		this.calories = calories;
		this.weightGram = weightGram;
		normalizedCalories = normalizeCalories(weightGram);
	}
	
	private int normalizeCalories(int gram){
		return (int)(1.0*calories*gram/FOOD_CALORIES_BASE+0.5);
	}
	
	public int getNormalizedCalories(){
		return normalizedCalories;
	}
	
	public String getNdbNo(){
		return ndbNo;
	}
	
	public String getName(){
		
		return name;
	}
	
	public int getCalories(){
		
		return calories;
	}
	
	public int getWeightGram(){
		
		return weightGram;
	}
	
	public String toString(){
		String str = name + " - " + weightGram;
		if (weightGram > 1)
			str+= " grams.";
		else
			str+= " gram.";
		return str;
	}
	
	public boolean equals(Object item){
		if (item instanceof Food)
			if (this.name == ((Food)item).name)
				return true;
		return false;
	}
	
	public void setWeightGram(int weightGram){	
		this.weightGram = weightGram;
	}
}
