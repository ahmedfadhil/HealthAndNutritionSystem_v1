package hns.objects;

import hns.businesslogic.*;

public class Date {
	

	static String[] monthList = {"NULL","JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
	
	private int day;
	private int month;
	private int year;
	
	public Date(int month, int day, int year) throws Exception{
		CheckValidInput.validateDateArguments(month, day, year);
		this.month = month;
		this.day   = day;
		this.year  = year;
	}
	
	public static String convertToMonth(int month){
		if (month > 0 && month <= 12)
			return monthList[month];
		else
			return "UNKNOWN"; 
	}
	
	public String toString(){
		return convertToMonth(month) + " " + day + ", " + year;
	}
	
	//return format used for date in sql
	public String getDateDelimited(){
		String preMonth="";
		String preDay="";
		if (month<10) preMonth ="0";
		if (day<10) preDay ="0";
		return year + "-" + preMonth+month + "-" + preDay+day;
	}
	
	public int compareTo(Date compare){
		if (this.year < compare.year){
			return -1;
		}else if (this.year > compare.year){
			return 1;
		}else{
			if (this.month < compare.month){
				return -1;
			}else if (this.month > compare.month){
				return 1;
			}else{
				if(this.day < compare.day){
					return -1;
				}else if (this.day > compare.day){
					return 1;
				}else 
					return 0;
			}
		}
	}
	
	public boolean equals(Object compare) {
		if (compare instanceof Date){
			compare = (Date) compare;
			return (((Date) compare).month == this.month && ((Date)compare).day == this.day && ((Date)compare).year == this.year);
		}
		else 
			return false;
	}
}
