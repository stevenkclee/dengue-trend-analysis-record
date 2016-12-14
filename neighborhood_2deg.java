import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.text.ParseException;
import java.util.*;
import java.io.File;
import java.util.ArrayList;


public class neighborhood_2deg {
	public static void main(String [] argv) throws IOException {
		
					

		
		FileReader frr = new FileReader("/home/hdc/data_preprocessing/00_output/neighborhood_2deg.txt");
		

		int first_line = 0;
		int write_first = 0;
		int location_check = 0;
		
	
		int major_check = 0;
		int find_check = 0;
		
		String s1,s11; 
		
		int debug = 0;
		
		BufferedReader brr = new BufferedReader(frr);
		
		
		String[] location = new String[5538];

		String[] neighbor = new String[2];
		
		while (brr.ready()){
						
			String s2 = brr.readLine();
			String[] major = s2.split("\t");
			String[] close = major[1].split(",");
				
			//System.out.println("location major: " + major[0].substring(0, 13) + "aaa \n");
			/*
			for (int a = 0; a < close.length; a++){
				
				System.out.println("location close: " + close[a] + "aaa \n");
			}		
			*/
			
			FileReader fr = new FileReader("/home/hdc/data_preprocessing/00_output/double_table.txt");		   
			BufferedReader br = new BufferedReader(fr);
			
			List<Integer> aList = new ArrayList<Integer>();
			
			
			while (br.ready()) {

				if(first_line == 0){
					String s10 = br.readLine();

					location = s10.split("\t");
							
					first_line++;
					
					for (int j = 0; j < location.length; j++) {

						if( major[0].substring(0, 13).equals(location[j]) ){
							major_check = j;
							find_check = 1;
							
							//System.out.println("location major: " + major[0] + " \n");
							break;
							
						} 
							
					}					
						
					if(find_check == 0){
						break;
					}						
					else{
						find_check = 0;
						continue;
					}					
						
				}		
				
				FileWriter fw = new FileWriter("/home/hdc/data_preprocessing/table_2deg/" + location[major_check] + ".txt", true);
			
				if(location_check == 0){
					
					for (int k = 0; k < close.length; k++){
						
						//System.out.println("location close: " + close[k] + " \n");
						
						for (int l = 0; l < location.length; l++) {
							
							if( close[k].equals(location[l]) ){
								aList.add(l);
							}							
						}
					
					}				
					
					if(write_first == 0){
						fw.write( location[major_check] + "\t" );	
						
						for(int m = 0; m < aList.size(); m++){
													
								fw.write( location[aList.get(m)] + "\t");
						}																		
						fw.write( "\n" );
					}				
					location_check++;
				}

			
				String[] line = br.readLine().split("\t");
				fw.write( line[major_check] + "\t");

				for(int n = 0; n < aList.size(); n++){
										
						fw.write( line[aList.get(n)] + "\t");
				}
				fw.write( "\n" );		
			
				fw.flush();
				fw.close();				
				
			}
			
			first_line = 0;
			location_check = 0;
			fr.close();
			
			
			
			System.out.println("location major " + debug + " complete! \n");
			debug++;
		}
		

		
		frr.close();
	             


	}
}


