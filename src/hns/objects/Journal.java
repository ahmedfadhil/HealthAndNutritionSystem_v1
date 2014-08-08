package hns.objects;
import java.util.LinkedList;


public class Journal {
	
	private LinkedList<Record> recordList;
	private int journalID;
	
	public Journal(int journalID) throws Exception{
			this.journalID = journalID;
			recordList = new LinkedList<Record>();
		
	}
	
	public boolean checkDuplicateDate(Record newRecord){
		
		boolean isDuplicate = false;
		for(Record record : recordList){
			
			if(record.getDate().equals(newRecord.getDate())){
				isDuplicate = true;
			}
		}
		return isDuplicate;
	}
	
	//ordered insertion. Earliest record will be the first in the list.
	public boolean insertNewRecord(Record newRecord){
		int index=0;
		Record curr;
		
		if (newRecord == null)
			return false;
		
		if (recordList.isEmpty()){
			return recordList.add(newRecord);
		}
		else if(checkDuplicateDate(newRecord)){
			return false;
		}
		else{
			curr = recordList.get(index);
			
			while ((curr.compareTo(newRecord) < 0) && (index < recordList.size())){
				index++;
				if (index < recordList.size()) curr = recordList.get(index);
			}		
			recordList.add(index, newRecord);
			return true;
		}
	}
	
	public int getJournalID(){
		return journalID;
	}
	
	public Object[] getArrayListOfRecords(){
		return recordList.toArray();
	}
	
	public String getDateOfFirstRecord(){
		return getDateOfFirstORLastRecordAux("FIRST") + "";
	}
	
	public String getDateOfLastRecord(){
		return getDateOfFirstORLastRecordAux("LAST") + "";
	}
	
	private Date getDateOfFirstORLastRecordAux(String mode){
		if (recordList.size()>0){
			if (mode.equalsIgnoreCase("FIRST"))
				return (recordList.getFirst()).getDate();
			else
				return (recordList.getLast()).getDate();
		}else
			return null;
	}
	
	public Record getLatestRecord(){
		if(recordList.size() > 0)
			return recordList.getLast();
		else
			return null;
	}
	
	
	//Returns null if there is no previous record or if the recordList is empty.
	public Record getPreviousRecord(Record record){
		int index = recordList.indexOf(record);
		
		if(index == -1){
			return null;
		}else{
			if (index == 0) 
				return null; 
			else
				return recordList.get((index-1));
		}
	}
	
	//Returns null if there is no next record or if the recordList is empty.
	public Record getNextRecord(Record record){
		int index = recordList.indexOf(record);
		
		if (index == -1){
			return null;
		}else{
			if (index == (recordList.size()-1))
				return null;
			else
				return recordList.get((index+1));
		}
	}
	
	//Returns null if the record dated with the passed date was not found.
	public Record getRecord(Date date){
		for (int i=0; i<recordList.size(); i++){
			if (recordList.get(i).equals(date))
				return recordList.get(i);
		}
		return null;
	}
	
	public String toString(){
		String string ="";
		for (int i=0; i<recordList.size(); i++){
			string += recordList.get(i).toString() + "\n";
		}
		return string;
	}
	public int size(){
		return recordList.size();
	}
	public Date[] getLastTenDates() {
		int max =10;
		if (recordList.size()<10){
			max=recordList.size();
		}
		Date[] dates = new Date[max];
		int min=recordList.size()-10;
		if (min<0)
			min=0;
		for(int i=0; i<max; i++, min++){
			dates[i]=recordList.get(min).getDate();
		}
		return dates;
	}

	public int[] getLastTenWeights() {
		int max =10;
		if (recordList.size()<10){
			max=recordList.size();
		}
		int min=recordList.size()-10;
		if (min<0)
			min=0;
		int[] weights = new int[max];
		for(int i=0; i<max; i++, min++){
			weights[i]=recordList.get(min).getWeight();
		}
		return weights;
	}
}
