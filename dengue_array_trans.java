import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.text.ParseException;
import java.util.*;
import java.io.File;
import java.util.ArrayList;


public class dengue_array_trans {
	public static void main(String [] argv) throws IOException {
		
		
		FileReader fr = new FileReader("/home/hdc/data_preprocessing/Dengue_test.csv");
		//FileWriter fw = new FileWriter("/home/hdc/data_preprocessing/Dengue/test.txt");

		int check = 0;
		String s1,s3;
		
		Set<String> intSet = new HashSet<String>();
		//String nonDuplicateArray[] = new String[intSet.size()];
		//Object[] tempArray = intSet.toArray();
		
		BufferedReader br = new BufferedReader(fr);
		//String s2 = br.readLine();
		while (br.ready()) {
			String s2 = br.readLine();
			s1 = s2;

			String[] line = s1.split(",");

			//Set<String> intSet = new HashSet<String>();
			for (String element : line) {
				if(check == 1){
					check = 0;
					break;
				}
				check++;
				intSet.add(element);
			}
		}
		
			String nonDuplicateArray[] = new String[intSet.size()];
			Object[] tempArray = intSet.toArray();
			for (int j = 0; j < tempArray.length; j++) {
			    nonDuplicateArray[j] = (String) tempArray[j];

			    //fw.write(nonDuplicateArray[j] + "\n");
			}
		 	
		fr.close();
	    
		int check2 = 0;
		
		for (int j = 0; j < nonDuplicateArray.length; j++) {
			
			FileWriter fw = new FileWriter("/home/hdc/data_preprocessing/Dengue/" + nonDuplicateArray[j] + ".txt");
			
			FileReader Fr = new FileReader("/home/hdc/data_preprocessing/Dengue_test.csv");
			
			BufferedReader Br = new BufferedReader(Fr);
			while (Br.ready()) {
				String s4 = Br.readLine();
				s3 = s4;
				String[] str = s3.split(",");

				for (String ele : str) {
					if(check2 == 1){
						check2 = 0;
						break;
					}
					if( nonDuplicateArray[j].equals(ele) ){
						//fw.write(s4 + "\n");
						fw.write(str[1] + "\n");
					}
					
				}
				
			}
				fw.flush();
				fw.close();
				Fr.close();
			
		}
		
		
		
	}
}



