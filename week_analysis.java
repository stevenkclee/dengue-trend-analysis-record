import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.text.ParseException;
import java.util.*;
import java.io.File;
import java.text.*;
import java.util.ArrayList;


public class week_analysis {
	public static void main(String [] argv) throws IOException {
		
		


		FileReader fr = new FileReader("/home/hdc/data_preprocessing/00_output/new.txt");

		FileWriter fw = new FileWriter("/home/hdc/data_preprocessing/00_output/train.txt");
		FileWriter fwt = new FileWriter("/home/hdc/data_preprocessing/00_output/test.txt");
		
		int tab = 0;
		int length = 0;
		int check = 0;
		int check2 = 0;
		int check3 = 0;
		
		
		String time,time2;
		
		int first_line = 0;
		int first_line_tab = 0;
		int first_line_length = 0;
		int first_line_check = 0;
		int time_check1 = 0;
		//int time_check2 = 0;
		//int time_check3 = 0;
		
		String[] line = new String[5538];
		String[] line2 = new String[5538];
		String[] line3 = new String[5538];
		//String[] array = new String[5538];
		
		BufferedReader br = new BufferedReader(fr);
		//String s2 = br.readLine();
		while (br.ready()) {
			//if(check == 1)
			//	break;

		
			
			if(check == 0){
				String s2 = br.readLine();
				//s1 = s2;
				
				line = s2.split(",");
				check++;
				continue;
			}
			else if(check2 == 0){
				String s3 = br.readLine();
				//s1 = s2;
				
				line2 = s3.split(",");
				check2++;
				continue;				
			}
			else if(check3 == 0){
				String s5 = br.readLine();
				//s1 = s2;
				
				line3 = s5.split(",");
				check3++;
				continue;				
			}
			else{
				time_check1++;
				String s4 = br.readLine();
				
				String[] line4 = s4.split(",");	
				
				for (int j = 0; j < line4.length; j++) {
						
					if( Integer.parseInt(line[j]) == Integer.parseInt(line2[j]) ){
						fw.write(time_check1 + "," + (j+1) + "1" + "," + "1" + "\n");
						line[j] = line2[j];
					}
					if( Integer.parseInt(line[j]) > Integer.parseInt(line2[j]) ){
												
						double Value1 = Double.parseDouble(line[j]);
						double Value2 = Double.parseDouble(line2[j]);
						
						double b2 = Value2/Value1;
						double b3 = Math.round(b2*100)/100.0;			
							
						fw.write(time_check1 + "," + (j+1) + "1" + "," + b3 + "\n");
						line[j] = line2[j];
					}
					if( Integer.parseInt(line[j]) < Integer.parseInt(line2[j]) ) {

						double Value2 = Double.parseDouble(line2[j]);
						double b3 = 0.00;
						if(Integer.parseInt(line[j]) == 0){
							double Value1 = 1.00;
							double b2 = (Value2+0.01)/Value1;
							b3 = Math.round(b2*100)/100.0;								
						}
						else{
							double Value1 = Double.parseDouble(line[j]);
							double b2 = Value2/Value1;
							b3 = Math.round(b2*100)/100.0;
						}
						
						fw.write(time_check1 + "," + (j+1) + "1" + "," + b3 + "\n");
						line[j] = line2[j];							
					}
					
					/* ------------------------------------------------------------- */
					
					if( Integer.parseInt(line2[j]) == Integer.parseInt(line3[j]) ){
						fw.write(time_check1 + "," + (j+1) + "2" + "," + "1" + "\n");
						line2[j] = line3[j];
					}
					if( Integer.parseInt(line2[j]) > Integer.parseInt(line3[j]) ){
												
						double Value1 = Double.parseDouble(line2[j]);
						double Value2 = Double.parseDouble(line3[j]);
						
						double b2 = Value2/Value1;
						double b3 = Math.round(b2*100)/100.0;			
							
						fw.write(time_check1 + "," + (j+1) + "2" + "," + b3 + "\n");
						line2[j] = line3[j];
					}
					if( Integer.parseInt(line2[j]) < Integer.parseInt(line3[j]) ) {

						double Value2 = Double.parseDouble(line3[j]);
						double b3 = 0.00;
						if(Integer.parseInt(line2[j]) == 0){
							double Value1 = 1.00;
							double b2 = (Value2+0.01)/Value1;
							b3 = Math.round(b2*100)/100.0;								
						}
						else{
							double Value1 = Double.parseDouble(line2[j]);
							double b2 = Value2/Value1;
							b3 = Math.round(b2*100)/100.0;
						}
						
						fw.write(time_check1 + "," + (j+1) + "2" + "," + b3 + "\n");
						line2[j] = line3[j];						
					}					
					
					/* ------------------------------------------------------------- */
					
					if( Integer.parseInt(line3[j]) == Integer.parseInt(line4[j]) ){
						fw.write(time_check1 + "," + (j+1) + "3" + "," + "1" + "\n");
						line3[j] = line4[j];
					}
					if( Integer.parseInt(line3[j]) > Integer.parseInt(line4[j]) ){
												
						double Value1 = Double.parseDouble(line3[j]);
						double Value2 = Double.parseDouble(line4[j]);
						
						double b2 = Value2/Value1;
						double b3 = Math.round(b2*100)/100.0;			
							
						fw.write(time_check1 + "," + (j+1) + "3" + "," + b3 + "\n");
						line3[j] = line4[j];
					}
					if( Integer.parseInt(line3[j]) < Integer.parseInt(line4[j]) ) {

						double Value2 = Double.parseDouble(line4[j]);
						double b3 = 0.00;
						if(Integer.parseInt(line3[j]) == 0){
							double Value1 = 1.00;
							double b2 = (Value2+0.01)/Value1;
							b3 = Math.round(b2*100)/100.0;								
						}
						else{
							double Value1 = Double.parseDouble(line3[j]);
							double b2 = Value2/Value1;
							b3 = Math.round(b2*100)/100.0;
						}
						
						fw.write(time_check1 + "," + (j+1) + "3" + "," + b3 + "\n");
						line3[j] = line4[j];							
					}
					
					
				}
				
				
			}

		}
		
		time_check1++;
		
		for (int j = 0; j < line.length; j++) {
			
			
			if( Integer.parseInt(line[j]) == Integer.parseInt(line2[j]) ){
				fw.write(time_check1 + "," + (j+1) + "1" + "," + "1" + "\n");
				line[j] = line2[j];
			}
			if( Integer.parseInt(line[j]) > Integer.parseInt(line2[j]) ){
										
				double Value1 = Double.parseDouble(line[j]);
				double Value2 = Double.parseDouble(line2[j]);
				
				double b2 = Value2/Value1;
				double b3 = Math.round(b2*100)/100.0;			
					
				fw.write(time_check1 + "," + (j+1) + "1" + "," + b3 + "\n");
				line[j] = line2[j];
			}
			if( Integer.parseInt(line[j]) < Integer.parseInt(line2[j]) ) {

				double Value2 = Double.parseDouble(line2[j]);
				double b3 = 0.00;
				if(Integer.parseInt(line[j]) == 0){
					double Value1 = 1.00;
					double b2 = (Value2+0.01)/Value1;
					b3 = Math.round(b2*100)/100.0;								
				}
				else{
					double Value1 = Double.parseDouble(line[j]);
					double b2 = Value2/Value1;
					b3 = Math.round(b2*100)/100.0;
				}
				
				fw.write(time_check1 + "," + (j+1) + "1" + "," + b3 + "\n");
				line[j] = line2[j];							
			}

			/* ------------------------------------------------------------- */
			
			if( Integer.parseInt(line2[j]) == Integer.parseInt(line3[j]) ){
				fw.write(time_check1 + "," + (j+1) + "2" + "," + "1" + "\n");
				line2[j] = line3[j];
			}
			if( Integer.parseInt(line2[j]) > Integer.parseInt(line3[j]) ){
										
				double Value1 = Double.parseDouble(line2[j]);
				double Value2 = Double.parseDouble(line3[j]);
				
				double b2 = Value2/Value1;
				double b3 = Math.round(b2*100)/100.0;			
					
				fw.write(time_check1 + "," + (j+1) + "2" + "," + b3 + "\n");
				line2[j] = line3[j];
			}
			if( Integer.parseInt(line2[j]) < Integer.parseInt(line3[j]) ) {

				double Value2 = Double.parseDouble(line3[j]);
				double b3 = 0.00;
				if(Integer.parseInt(line2[j]) == 0){
					double Value1 = 1.00;
					double b2 = (Value2+0.01)/Value1;
					b3 = Math.round(b2*100)/100.0;								
				}
				else{
					double Value1 = Double.parseDouble(line2[j]);
					double b2 = Value2/Value1;
					b3 = Math.round(b2*100)/100.0;
				}
				
				fw.write(time_check1 + "," + (j+1) + "2" + "," + b3 + "\n");
				line2[j] = line3[j];						
			}					
			
			/* ------------------------------------------------------------- */			
			
			fwt.write(time_check1 + "," + (j+1) + "3" + "\n");
			
		}

		//FileWriter fw = new FileWriter("test.txt");
        	//fw.write("test");
        	fw.flush();
        	fw.close();
        	fwt.flush();
        	fwt.close();
		fr.close();
	               
		


	}
}



