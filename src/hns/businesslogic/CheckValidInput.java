package hns.businesslogic;

public class CheckValidInput {
	
	public static boolean validInputNum(int input, int lowerBound, int higherBound){
		boolean validInput = false;
		if(input>lowerBound && input < higherBound){
			validInput = true;
		}
		return validInput;
	}

	public static boolean validUserID(int userID){
		boolean validUserID = false;
		if(userID>=0){
			validUserID = true;
		}
		return validUserID;
	}
	
	
	public static boolean validInputName(String input){
		if(input!=null && input.length()>0)
		{
			char[] name=input.toCharArray();
			if( !isConsecutiveSpace(name) && name[0]!=' ' && name[name.length-1]!=' ')
				return true;
			else 
				return false;
		}
		else 
			return false;
	}
	
	
	private static boolean isConsecutiveSpace(char[] string)
	{
		int count = 0;
		for (char c:string)
		{
			if (c==' ')
			{
				if (count ==0)
					count=1;
				else 
					return true;
			}	
			else 
				count=0;
		}
		return false;
	}
	
	public static void validateDateArguments(int month, int day, int year) throws Exception{
		int leapYear=year%4;
		
		if (year > 0){
			if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && (day > 0 && day <=31)) ;
			else if ((month == 2) && ( (day > 0 && day <= 29 && leapYear==0) || (day > 0 && day < 29 && leapYear!=0)));
			else if ((month == 4 || month == 6 || month == 9 || month == 11) && (day > 0 && day <=30));
			else{
				throw new IllegalArgumentException ("Invalid Date");
			}
		}
		else
			throw new IllegalArgumentException ("Invalid Year");
	}
	
	
	
}
