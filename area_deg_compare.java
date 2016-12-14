import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.text.ParseException;
import java.util.*;
import java.io.File;
import java.util.ArrayList;
import java.math.BigDecimal;  

public class area_deg_compare {
	
	public static int comp(double val1, double val2) {  
	 
		BigDecimal data1 = new BigDecimal(val1);  
		BigDecimal data2 = new BigDecimal(val2); 

		return data1.compareTo(data2);  
	}	
	
	public static void main(String [] argv) throws IOException {
			
		
		double d1 = 0.000000;
		double d2 = 0.000000;
		
		double deg1 = 0.000000;
		double deg2 = 0.000000;
		
		double b1 = 0.00;
		double b2 = 0.00;
		double b3 = 0.00;
		double b4 = 0.00;
		
		int o = 0;
		int x = 0;
		int NaN = 0;
		int no_close = 0; 
		
		int check1 = 0;
		int check2 = 0;
		int deg1_c = 0;
		int deg2_c = 0;
		
		String s5,s6,s8,s9;
		
		File f = new File("/home/hdc/data_preprocessing/1_dimension/");
		ArrayList<String> fileList = new ArrayList<String>(); 

		if(f.isDirectory()){
	 
			//System.out.println("filename : "+f.getName());
			String []s=f.list(); 
			//System.out.println("size : "+s.length);
				for(int i=0;i<s.length;i++){
					
					fileList.add(s[i]); 
				}
		}
		
		FileWriter fw = new FileWriter("/home/hdc/data_preprocessing/" + "degree_one_two_compare.txt");
		
		for(int i=0;i<fileList.size();i++){
					

			FileReader fr = new FileReader("/home/hdc/data_preprocessing/1_dimension/" + fileList.get(i));
			FileReader frr = new FileReader("/home/hdc/data_preprocessing/2_dimension/" + fileList.get(i));
			BufferedReader br = new BufferedReader(fr);
			BufferedReader brr = new BufferedReader(frr);
			
			while (br.ready()) {
				check1++;
				String s3 = br.readLine();
				if( s3.equals("For analysis") ){
					String s4 = br.readLine();
					s5 = s4;
					s6 = s4;
					
					int index1 = s4.indexOf(":");
					int index2 = s5.indexOf("\t");
					//System.out.println(s6.substring(index1+1, index2));
					
					deg1 = Double.parseDouble( s6.substring(index1+1, index2) );
					break;
				}
				else{
					continue;
				}
				
			}
			
			if(check1 == 1){
				check1 = 0;
				deg1_c = 1;
			}
			
			//d1 = Math.round( deg1 *1000000 )/1000000.0;
			
			while (brr.ready()) {
				check2++;
				String s2 = brr.readLine();
				if( s2.equals("For analysis") ){
					String s7 = brr.readLine();
					s8 = s7;
					s9 = s7;
					
					int index3 = s7.indexOf(":");
					int index4 = s8.indexOf("\t");
					//System.out.println(s9.substring(index3+1, index4));
					deg2 = Double.parseDouble( s9.substring(index3+1, index4) );
					break;
					
				}
				else{
					continue;
				}
				
			}

			if(check2 == 1){
				check2 = 0;
				deg2_c = 1;
			}			
			//d2 = Math.round( deg2 *1000000 )/1000000.0;
			
			
			
			
			if( comp( deg1, deg2 ) > 0 ){
				o++;
				fw.write(fileList.get(i) + "\t" + "o" + "\n");
			}
			if( comp( deg1, deg2 ) < 0 ) {
				x++;
				fw.write(fileList.get(i) + "\t" + "x" + "\n");
			}
			if( comp( deg1, deg2 ) == 0 ) {
				NaN++;
				fw.write(fileList.get(i) + "\t" + "NaN" + "\n");
			}
			if( deg1_c == 1 || deg2_c == 1){
				deg1_c = 0;
				deg2_c = 0;
				no_close++;
				fw.write(fileList.get(i) + "\t" + "1-deg or 2-deg don't have close location" + "\n");
			}
			
			
			frr.close();
			fr.close();

			System.out.println("File " + i + " complete! \n");	
		}//end for loop
		
		fw.write("\n");
		fw.write("--------------------------------------------------" + "\n");
		//calculate
		fw.write("Each Total count " + "\n" + "o: " + o + "\n" + "x: " + x + "\n" + "NaN: " + NaN + "\n" + "No close: " + no_close + "\n\n\n");
		b1 = Math.round( ((double)o) *100 )/100.0;	
		b2 = Math.round( ((double)x) *100 )/100.0;	
		b3 = Math.round( ((double)NaN) *100 )/100.0;	
		b4 = Math.round( ((double)no_close) *100 )/100.0;		

		fw.write("Each Percentage " + "\n" + "o: " + b1/(b1+b2+b3+b4) + "\n" + "x: " + b2/(b1+b2+b3+b4) + "\n" + "NaN: " + b3/(b1+b2+b3+b4) + "\n" + "No close: " + b4/(b1+b2+b3+b4) + "\n");
		
        fw.flush();
        fw.close();
	}
}

