import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;


public class ParseCVS {

	/**
	 * Turn the realdb.csv into a SQL script
	 */
	public static void main(String[] args) {

		//copy and paste results from realdb.script to DB.script
		String filename = "realdb.csv";
		String outputfile = "realdb.script";
		int count = 0;
		try {
			
			FileReader in = new FileReader(filename);
			BufferedReader br = new BufferedReader(in);
			
			FileWriter fw = new FileWriter(outputfile);
			BufferedWriter bw = new BufferedWriter(fw);
			
			String linein;
			String previous = null;
			
			while((linein = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(linein, ";");
				String name = st.nextToken();
				int calories = Integer.parseInt(st.nextToken());
				if(previous != null && previous.equals(name)){
					System.out.println("Duplicate: " + name);
				}
				else{
					String statement = "INSERT INTO FOOD VALUES(" + name + "," + calories + "," + 100 + ")"; 
					
					bw.write(statement);
					bw.newLine();
				}
				
				count ++;
				previous = name;
			}
	
			in.close();
			bw.close();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("count: " + count);
		}
	}

}
