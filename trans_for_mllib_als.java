import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.text.ParseException;
import java.util.*;
import java.io.File;
import java.util.ArrayList;


public class trans_for_mllib_als {
	public static void main(String [] argv) throws IOException {

		FileReader fr = new FileReader("/home/hdc/data_preprocessing/00_output/double.txt");
		FileWriter fw = new FileWriter("/home/hdc/data_preprocessing/00_output/als.txt");

		int first_line = 0;
		int first_line_tab = 0;
		int first_line_length = 0;
		int first_line_check = 0;
		
		int data_first = 0;
		
		int tab = 0;
		int length = 0;
		
		int count = 0;
		int pre = 0;
		
		String s1,s11,tmp1,tmp2,time2; 
		String time1 = new String();
		
		BufferedReader br = new BufferedReader(fr);
		
		String[] location = new String[5538];
		int[] location_code = new int[5538];
		String[] percent1 = new String[5538];
		int[] time_arr = new int[35];
		
		while (br.ready()) {

		    if(first_line == 0){
				String s10 = br.readLine();
				first_line_tab = s10.indexOf("	");
				first_line_length = s10.length();			
				s11 = s10.substring(first_line_tab+1, first_line_length);
				
				location = s11.split("\t");
				
				for (int a = 0; a < location.length; a++) {
					location_code[a] = a+1;					
				}					
				first_line++;
				continue;
			}		
		
			if(data_first == 0){
				String s2 = br.readLine();
				
				tab = s2.indexOf("	");
				length = s2.length();
				tmp1 = s2;
				//System.out.println(tab + "\n");
				
				s1 = s2.substring(tab+1, length);
				
				time1 = tmp1.substring(0, tab);
				
				
				time_arr[0] = 0;
				
				percent1 = s1.split("\t");		
				data_first++;
				continue;
			}
			
			String s3 = br.readLine();
			
			tab = s3.indexOf("	");
			length = s3.length();
			tmp2 = s3;
			//System.out.println(tab + "\n");
			
			s1 = s3.substring(tab+1, length);
			
			time2 = tmp2.substring(0, tab);
			count++;
			pre = count - 1;
			time_arr[count] = count;
			
			String[] percent2 = s1.split("\t");
			
			for (int j = 0; j < percent2.length; j++) {
				
				fw.write(location_code[j] + "," + time_arr[pre] + "," + percent1[j] + "\n");
				fw.write(location_code[j] + "," + time_arr[count] + "," + percent2[j] + "\n");
				percent1[j] = percent2[j];
			}
			time1 = time2;
	
		}

        	fw.flush();
        	fw.close();
			fr.close();
		


	}
}


