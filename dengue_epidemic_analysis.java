import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.text.ParseException;
import java.util.*;
import java.io.File;
import java.text.*;
import java.util.ArrayList;


public class dengue_epidemic_analysis {
	public static void main(String [] argv) throws IOException {

		FileReader fr = new FileReader("/home/hdc/data_preprocessing/00_output/final.txt");

		FileWriter fw = new FileWriter("/home/hdc/data_preprocessing/00_output/double.txt");
		
		int tab = 0;
		int length = 0;
		int check = 0;
		String s1,s4,s11,tmp,time,tmp2,time2;
		
		int first_line = 0;
		int first_line_tab = 0;
		int first_line_length = 0;
		int first_line_check = 0;
		int time_check1 = 0;

		String[] line = new String[5538];
		//String[] array = new String[5538];
		
		BufferedReader br = new BufferedReader(fr);
		//String s2 = br.readLine();
		while (br.ready()) {
			//if(check == 1)
			//	break;

		    if(first_line == 0){
				String s10 = br.readLine();
				first_line_tab = s10.indexOf("	");
				first_line_length = s10.length();			
				s11 = s10.substring(first_line_tab+1, first_line_length);
					
				String[] array = s11.split(",");
			
				for(int k = 0; k < array.length; k++){
					if(k == (array.length-1)){
						fw.write(array[k] + "\n");
						
					}
					else if(first_line_check == 0){
						fw.write("YYYY/MM/DD" + "\t" + array[k] + "\t");
						first_line_check++;
					}
					else{
						fw.write(array[k] + "\t");
					}
						
				}
				first_line++;
				continue;
			}		
			
			if(check == 0){
				String s2 = br.readLine();
				//s1 = s2;
				tab = s2.indexOf("	");
				length = s2.length();
				tmp = s2;
				//System.out.println(tab + "\n");
				
				s1 = s2.substring(tab+1, length);
				
				time = tmp.substring(0, tab);
				
				line = s1.split(",");
				
				for (int j = 0; j < line.length; j++) {
					

					if(j == (line.length-1) ){
							fw.write("0" + "\n");
							continue;
					}
					else if(time_check1 == 0){
						fw.write(time + "\t" + "0" + "\t");
						time_check1++;
					}
					else{
						fw.write("0" + "\t");
					}
					
				}
				check++;
			}
			else{
			//if(bb == 1)
			//	break;				
				String s3 = br.readLine();
				//s1 = s2;
				tab = s3.indexOf("	");
				length = s3.length();
				tmp2 = s3;
				//System.out.println(tab + "\n");
				
				s4 = s3.substring(tab+1, length);
				
				time2 = tmp2.substring(0, tab);
				
				String[] line2 = s4.split(",");	
				//for (int j = 0; j < line2.length; j++) {
				//	System.out.println(line[j] + line2[j] + "\n"); 
				//}
				int time_check2 = 0;
				
				for (int j = 0; j < line2.length; j++) {
				//Double 資料顯示 0 就表示下一周病例變0, 如果出現1代表這周與上周的病例數沒變
				//如果為其他數字, 那就表示單純是這周為上周幾倍
					if(j == (line2.length-1) ){
						if( Integer.parseInt(line[j]) == Integer.parseInt(line2[j]) ){
							fw.write("1" + "\n");
							line[j] = line2[j];
						}
						if( Integer.parseInt(line[j]) > Integer.parseInt(line2[j]) ){
													
							double Value1 = Double.parseDouble(line[j]);
							double Value2 = Double.parseDouble(line2[j]);
							
							//if(Integer.parseInt(line2[j]) == 0){
							//	double Value2 = 1.00;
							//}
							//else{
							//	double Value2 = Double.parseDouble(line2[j]);
							//}
							
							//前面的數字 分之 (後面-前面)
							double b2 = Value2/Value1;
							double b3 = Math.round(b2*100)/100.0;			// 乘100後做四捨五入, 再除100.0
							
							//System.out.println(b3); 							
							fw.write(b3 + "\n");  //結果為增加或減少原本的百分點
							line[j] = line2[j];
						}
						if( Integer.parseInt(line[j]) < Integer.parseInt(line2[j]) ){
							//double Value1 = Double.parseDouble(line[j]);
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
							
							fw.write(b3 + "\n");	
							line[j] = line2[j];							
						}							
							
							//fw.write("0" + "\n");
							continue;
					}
					else{
						//if( line[j] == null || line2[j] == null ){
						//	System.out.println(j); 
						//	bb = 1; 
						//}
						//if(bb == 1)
						//	break;
						if(time_check2 == 0){
							if( Integer.parseInt(line[j]) == Integer.parseInt(line2[j]) ){
								fw.write(time2 + "\t" + "1" + "\t");
								line[j] = line2[j];
							}
							if( Integer.parseInt(line[j]) > Integer.parseInt(line2[j]) ){
														
								double Value1 = Double.parseDouble(line[j]);
								double Value2 = Double.parseDouble(line2[j]);
								
								//if(Integer.parseInt(line2[j]) == 0){
								//	double Value2 = 1.00;
								//}
								//else{
								//	double Value2 = Double.parseDouble(line2[j]);
								//}
								
								//前面的數字 分之 (後面-前面)
								double b2 = Value2/Value1;
								double b3 = Math.round(b2*100)/100.0;			// 乘100後做四捨五入, 再除100.0
								
								//System.out.println(b3); 							
								fw.write(time2 + "\t" + b3 + "\t");  //結果為增加或減少原本的百分點
								line[j] = line2[j];
							}
							if( Integer.parseInt(line[j]) < Integer.parseInt(line2[j]) ) {
								//double Value1 = Double.parseDouble(line[j]);
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
								
								fw.write(time2 + "\t" + b3 + "\t");	
								line[j] = line2[j];							
							}
							time_check2++;
						}
						else{
							if( Integer.parseInt(line[j]) == Integer.parseInt(line2[j]) ){
								fw.write("1" + "\t");
								line[j] = line2[j];
							}
							if( Integer.parseInt(line[j]) > Integer.parseInt(line2[j]) ){
														
								double Value1 = Double.parseDouble(line[j]);
								double Value2 = Double.parseDouble(line2[j]);
								
								//if(Integer.parseInt(line2[j]) == 0){
								//	double Value2 = 1.00;
								//}
								//else{
								//	double Value2 = Double.parseDouble(line2[j]);
								//}
								
								//前面的數字 分之 (後面-前面)
								double b2 = Value2/Value1;
								double b3 = Math.round(b2*100)/100.0;			// 乘100後做四捨五入, 再除100.0
								
								//System.out.println(b3); 							
								fw.write(b3 + "\t");  //結果為增加或減少原本的百分點
								line[j] = line2[j];
							}
							if( Integer.parseInt(line[j]) < Integer.parseInt(line2[j]) ) {
								//double Value1 = Double.parseDouble(line[j]);
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
								
								fw.write(b3 + "\t");	
								line[j] = line2[j];							
							}							
						}
			
						//fw.write("0" + ",");
					}
					//time_check2 = 0;
				}
				
				
			}

		}

		//FileWriter fw = new FileWriter("test.txt");
        	//fw.write("test");
        	fw.flush();
        	fw.close();
		fr.close();
	               
		


	}
}



