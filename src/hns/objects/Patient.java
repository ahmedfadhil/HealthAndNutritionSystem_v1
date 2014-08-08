package hns.objects;

import hns.objects.User;
import hns.enums.ActivityLevel;
import hns.enums.Gender;
import hns.businesslogic.CalculateBMI;
import hns.businesslogic.CalculateRequiredCalories;
import hns.businesslogic.CalculateWeightLevel;
import hns.businesslogic.CheckValidInput;

public class Patient extends User{
	private int age; // in year
	private int weight;//in kilograms
	private int height; //in centimeters
	private Gender gender;
	private ActivityLevel activityLevel;
	private double bmi;
	private String weightLevel;
	private int calories;
	private Journal journal;
	
	public final static int AGE_LOWER_BOUND = 4;
	public final static int AGE_HIGHER_BOUND = 150;// in year
	public final static int WEIGHT_LOWER_BOUND = 0;
	public final static int WEIGHT_HIGHER_BOUND = 500;//in kilograms
	public final static int HEIGHT_LOWER_BOUND = 0;
	public final static int HEIGHT_HIGHER_BOUND = 300;//in centimeters
	
	public Patient(int userID, int journalID, String lastName, String firstName, int age, int weight, int height, Gender gender, ActivityLevel activityLevel) throws Exception {
		super(userID, lastName, firstName);
		
		if(CheckValidInput.validInputNum(age, AGE_LOWER_BOUND, AGE_HIGHER_BOUND )) {
			this.age = age;
		}
		else{
			throw new IllegalArgumentException("Invalid input for age(5+)");
		}
		if(CheckValidInput.validInputNum(weight, WEIGHT_LOWER_BOUND, WEIGHT_HIGHER_BOUND )) {
			this.weight = weight;	
		}
		else{
			throw new IllegalArgumentException("Invalid input for weight (1+)");
		}
		
		if(CheckValidInput.validInputNum(height, HEIGHT_LOWER_BOUND, HEIGHT_HIGHER_BOUND )) {
			this.height = height;
		}
		else{
			throw new IllegalArgumentException("Invalid input for height(1+)");
		}
			
		if(gender != null){
			this.gender = gender;
		}
		else{
			throw new IllegalArgumentException("Invalid input for gender");
		}
		
		
		if(activityLevel != null){
			this.activityLevel = activityLevel;	
		}
		else{
			throw new IllegalArgumentException("Invalid input for activityLevel");
		}
		
		updateCalculations();
		journal = new Journal(journalID);
	}

	public void updateAge(int age) throws Exception{
		if(CheckValidInput.validInputNum(age, AGE_LOWER_BOUND, AGE_HIGHER_BOUND )) {
			this.age = age;
		}
		else{
			throw new IllegalArgumentException("Invalid input for age");
		}
		
		updateCalculations();
		
	}
	
	public void updateWeight(int weight) throws Exception{
		if(CheckValidInput.validInputNum(weight, WEIGHT_LOWER_BOUND, WEIGHT_HIGHER_BOUND )) {
			this.weight = weight;	
		}
		else{
			throw new IllegalArgumentException("Invalid input for weight");
		}
		updateCalculations();
	}
	
	public void updateHeight(int height) throws Exception{
		if(CheckValidInput.validInputNum(height, HEIGHT_LOWER_BOUND, HEIGHT_HIGHER_BOUND )) {
			this.height = height;
		}
		else{
			throw new IllegalArgumentException("Invalid input for height");
		}
		updateCalculations();
		
	}
	
	public void updateGender(Gender gender) throws Exception{
		if(gender != null){
			this.gender = gender;
		}
		else{
			throw new IllegalArgumentException("Invalid input for gender");
		}
		updateCalculations();
	}
	
	public void updateActivityLevel(ActivityLevel activityLevel) throws Exception{
		if(activityLevel != null){
			this.activityLevel = activityLevel;	
		}
		else{
			throw new IllegalArgumentException("Invalid input for activityLevel");
		}	
		updateCalculations();
	}
	
	private void updateCalculations() throws Exception{
		
		updateCalories();
		updateBMI();
		updateWeightLevel();
	}
	
	private void updateBMI() throws Exception{
		bmi = CalculateBMI.determineBMI(weight, height);
	}
	
	private void updateWeightLevel() throws Exception{
		weightLevel = CalculateWeightLevel.determineWeightLevel(bmi);
	}
	
	private void updateCalories() throws Exception{
		calories = CalculateRequiredCalories.determineCalories(weight, height, age, gender, activityLevel);
	}

	public int getAge(){
		return age;
	}
	
	public int getWeight(){
		return weight;
	}
	
	public int getHeight(){
		return height;
	}
	
	public Gender getGender(){
		return gender;
	}
	
	public ActivityLevel getActivityLevel(){
		return activityLevel;
	}
	
	public double getBMI(){
		return bmi;
	}
	
	public String getWeightLevel(){
		return weightLevel;
	}
	
	public int getCalories(){
		return calories;
	}
	
	public Journal getJournal(){
		return journal;
	}
	
	public int getJournalID(){
		return journal.getJournalID();
	}
	
	public String toString(){
		return getFirstName() + " " + getLastName();
	}
	

}
