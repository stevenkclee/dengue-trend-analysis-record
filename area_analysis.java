import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.text.ParseException;
import java.util.*;
import java.io.File;
import java.util.ArrayList;
import java.math.BigDecimal;  

public class area_analysis {
	
	public static int comp(double val1, double val2) {  
	 
		BigDecimal data1 = new BigDecimal(val1);  
		BigDecimal data2 = new BigDecimal(val2); 

		return data1.compareTo(data2);  
	}	
	
	public static void main(String [] argv) throws IOException {
		
		int first_line = 0;
		int one_line = 0;
		int two_line = 0;
	
		int positive_positive = 0;
		int positive_negative = 0;
		int negative_negative = 0;
		int negative_positive = 0;
		int NaN = 0;
		int total_p = 0;
		int total_n = 0;
		int total_nan = 0;
		
		double b1 = 0.00;
		double b2 = 0.00;
		double b3 = 0.00;
		double b4 = 0.00;	
		double b5 = 0.00;
		double b6 = 0.00;
		double nan = 0.00;
		
		
		int loop2 = 0;
		int loop_check = 0;
		int index = 1;
		int week = 1;
	
		double up = 2.00;
		double down = 0.65;
		
		String[] location2 = new String[100];
		String[] line1 = new String[100];
		String[] line2 = new String[100];
		
		
		
		File f = new File("/home/hdc/data_preprocessing/table/");
		ArrayList<String> fileList = new ArrayList<String>(); 

		if(f.isDirectory()){
	 
			//System.out.println("filename : "+f.getName());
			String []s=f.list(); 
			//System.out.println("size : "+s.length);
				for(int i=0;i<s.length;i++){
					
					fileList.add(s[i]); 
				}
		}
		
		for(int i=0;i<fileList.size();i++){
					

			FileReader fr = new FileReader("/home/hdc/data_preprocessing/table/" + fileList.get(i));

			FileWriter fw = new FileWriter("/home/hdc/data_preprocessing/1_dimension/" + fileList.get(i));

			
			BufferedReader br = new BufferedReader(fr);
			while (br.ready()) {
				String[] location = br.readLine().split("\t");
				loop_check = location.length;
				break;
			}
			fr.close();
			
			if(loop_check == 1){
				fw.write("Major location doesn't have close location" + "\n");
				fw.flush();
				fw.close();
				System.out.println("File " + i + " complete! --- index 1 \n");
				continue;
			}			
			
			
			
			while ( loop2 != (loop_check-1) ) {
				loop2++;
				FileReader frr = new FileReader("/home/hdc/data_preprocessing/table/" + fileList.get(i));
				BufferedReader brr = new BufferedReader(frr);				
					
				while (brr.ready()) {
					
					if(first_line == 0){
						
						location2 = brr.readLine().split("\t");
						first_line++;
						continue;
						
					}				
				
					if(one_line == 0){
						
						line1 = brr.readLine().split("\t");
						one_line++;
						continue;
						
					}

					if(two_line == 0){
						
						line2 = brr.readLine().split("\t");
						two_line++;
						continue;
						
					}			
				
					String[] line3 = brr.readLine().split("\t");
					
					if( comp( Double.parseDouble(line1[0]), up )  >= 0 ){   //major up
					
						if( comp( Double.parseDouble(line2[index]), 1.00 )  > 0 &&  comp( Double.parseDouble(line3[index]), 1.00 )  > 0 ){ 
						//major up close up
							
							//fw.write(location2[0] + ", " + location2[index] + " in major location week (" + week  + ", " + week+1 + ")" + "\n");
							//fw.write(location2[0] + ", " + location2[index] + " in major location week (" + week  + ", " + week+2 + ")" + "\n");
							fw.write("Positive: " + location2[0] + ", " + location2[index] + " in major location week " + week + "\n");
							positive_positive++;
						}
						else if( comp( Double.parseDouble(line2[index]), 1.00 )  < 0 &&  comp( Double.parseDouble(line3[index]), 1.00 )  < 0 ){ // major up but close down
						 // major up but close down	
							fw.write("Negative: " + location2[0] + ", " + location2[index] + " in major location week " + week + "\n");
							positive_negative++;
						}
						else {
							NaN++;
						}
											
						
					}

					
					if( comp( Double.parseDouble(line1[0]), down )  <= 0 ){  //major down
						
						if( comp( Double.parseDouble(line2[index]), 1.00 )  < 0 &&  comp( Double.parseDouble(line3[index]), 1.00 )  < 0 ){ 
						//major down close down
							
							//fw.write(location2[0] + ", " + location2[index] + " in major location week (" + week  + ", " + week+1 + ")" + "\n");
							//fw.write(location2[0] + ", " + location2[index] + " in major location week (" + week  + ", " + week+2 + ")" + "\n");
							fw.write("Positive: " + location2[0] + ", " + location2[index] + " in major location week " + week + "\n");
							negative_negative++;
						}
						else if( comp( Double.parseDouble(line2[index]), 1.00 ) > 0 &&  comp( Double.parseDouble(line3[index]), 1.00 )  > 0 ){ // major up but close down
						 // major down but close up	
							fw.write("Negative: " + location2[0] + ", " + location2[index] + " in major location week " + week + "\n");
							negative_positive++;
						}
						else {
							NaN++;
						}

						
					}

					line1[0] = line2[0];
					line1[index] = line2[index];
					line2[0] = line3[0];
					line2[index] = line3[index];
					
					week++;
					
				} 
				
				//calculate each pair location with major and close_1...n
				fw.write("\n");
				total_p = total_p + positive_positive + negative_negative;
				total_n = total_n + positive_negative + negative_positive;
				total_nan = total_nan + NaN;
				
				b1 = Math.round( ((double)positive_positive) *100 )/100.0;	
				b2 = Math.round( ((double)positive_negative) *100 )/100.0;	
				b3 = Math.round( ((double)negative_negative) *100 )/100.0;	
				b4 = Math.round( ((double)negative_positive) *100 )/100.0;
				
				fw.write("Positive Rate: " + (b1+b3)/(b1+b2+b3+b4) + "\t" + "Negative Rate: " + (b2+b4)/(b1+b2+b3+b4) + "\n");
				positive_positive = 0;
				positive_negative = 0;
				negative_negative = 0;
				negative_positive = 0;
				NaN = 0;
				fw.write("-------------------------------------------------------" + "\n");
				
				index++;
				week = 1;
				first_line = 0;
				one_line = 0;
				two_line = 0;
				frr.close();
				
			} //end loop2 != (loop_check-1)
			
			fw.write("\n");
			fw.write("For analysis\n");
			//calculate major location "whole" rate (teacher want)
			b5 = Math.round( ((double)total_p) *100 )/100.0;
			b6 = Math.round( ((double)total_n) *100 )/100.0;
			nan = Math.round( ((double)total_nan) *100 )/100.0;
			fw.write("Whole Positive Rate: " + b5/(b5+b6+nan) + "\t" + "Whole Negative Rate: " + b6/(b5+b6+nan) + "\n");
			fw.write("**********************************************" + "\n");
			
			total_p = 0;
			total_n = 0;
			total_nan = 0;
        	fw.flush();
        	fw.close();
			
	        System.out.println("File " + i + " complete! \n");	
			loop2 = 0;	
			index = 1;
					
		}//end for loop


	}
}

