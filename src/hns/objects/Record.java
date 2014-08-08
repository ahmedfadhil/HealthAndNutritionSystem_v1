package hns.objects;
import hns.businesslogic.CheckValidInput;
import java.util.LinkedList;


public class Record {

	private Date date;
	private LinkedList<Food> foodList;
	private LinkedList<Activity> activityList;
	private int weight;
	
	public Record(Date date, int weight)throws Exception{
		if (date != null){
			this.date = date;
		}
		else{
			throw new NullPointerException("Date argument passed into Record's constructor is null.");
		}
		
		if(CheckValidInput.validInputNum(weight, Patient.WEIGHT_LOWER_BOUND, Patient.WEIGHT_HIGHER_BOUND )) {
			this.weight = weight;	
		}
		else{
			throw new IllegalArgumentException("Invalid input for weight");
		}
		foodList = new LinkedList<Food>();
		activityList = new LinkedList<Activity>();
	}
	
	public boolean AddToList(Object item){
		
		if (item instanceof Food)
			return foodList.add((Food) item);
		else if (item instanceof Activity)
			return activityList.add((Activity) item);
		else
			return false;
	}
	
	public int compareTo(Record compare){
		return date.compareTo(compare.date);
	}
	
	public boolean equals(Object compare){
		
		if (compare instanceof Record)
			return (date.equals(((Record) compare).date));
		else if (compare instanceof Date)
			return (date.equals(compare));
		else
			return false;
	}

	public String[] getFoodList(){
		
		return getList(foodList.toArray()).split("/");
	}
	
	public String[] getActivityList(){
		
		return getList(activityList.toArray()).split("/");
	}
	
	//the following two methods will be used on the CalculateRealCalorie
	public Object[] getArrayListOfFood(){
		return foodList.toArray();
	}
	
	public Object[] getArrayListOfActivity(){
		return activityList.toArray();
	}
	
	private String getList(Object[] list){
		String result="";
		for(int i=0; i<list.length; i++){
			result += list[i].toString() + "/";
		}
		return result;
	}
	
	public Date getDate(){
		
		return date;
	}	
	
	public String getDateDelimited(){
		
		return date.getDateDelimited();
	}
	
	public void setWeight(int weight){
		this.weight = weight;
	}
	
	public int getWeight(){
		return weight;
	}
	
	public String toString(){
		return date.toString() + "\nFood List: \n" + getFoodList() + "\n\nActivity List: \n" + getActivityList();
	}
	

	
}
