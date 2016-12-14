import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.text.ParseException;
import java.util.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class datecsv_trans_to_array {
	public static void main(String [] argv) throws IOException, ParseException {

	String start = "2015/5/1";
	String end = "2015/12/31";
    int fin = 0;
	
	SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");  
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	
	Calendar c = Calendar.getInstance();  
	Calendar add = Calendar.getInstance(); 
	Calendar c_end = Calendar.getInstance(); 
	c_end.setTime(df.parse(end));

	 
	int first_line = 0; 		
	int first_line_check = 0;
	int tab = 0;
	String s10;
	
	while(fin == 0){
	
		File f = new File("00"); 
		ArrayList<String> fileList = new ArrayList<String>(); 

		if(f.isDirectory()){
	 
			System.out.println("filename : "+f.getName()); 
			String []s=f.list(); 
			System.out.println("size : "+s.length); 
				for(int i=0;i<s.length;i++){
					//System.out.println(s[i]);
					fileList.add(s[i]); 
				}
		}	
		
		
		c.setTime(df.parse(start));
		add.setTime(df.parse(start));
		add.add(Calendar.DAY_OF_WEEK, 7); 		
		
		int first = 0;
		
		for(int i=0;i<fileList.size();i++){

			FileReader fr = new FileReader("/home/hdc/data_preprocessing/00/" + fileList.get(i));

			FileWriter fw = new FileWriter("/home/hdc/data_preprocessing/00_output/new.txt", true);
			
		    if(first_line == 0){
				for(int k=0;k<fileList.size();k++){
					if(k == (fileList.size()-1)){
						tab = fileList.get(k).indexOf(".");
						s10 = fileList.get(k).substring(0, tab);
						fw.write(s10 + "\n");
						
					}
					else if(first_line_check == 0){
						tab = fileList.get(k).indexOf(".");
						s10 = fileList.get(k).substring(0, tab);
						fw.write("YYYY/MM/DD" + "\t" + s10 + ",");
						first_line_check++;
					}
					else{
						tab = fileList.get(k).indexOf(".");
						s10 = fileList.get(k).substring(0, tab);
						fw.write(s10 + ",");
					}
						
				}
				first_line++;
			}
			
			//int length;
			int check = 0;
			//String s1,s3;
		
			BufferedReader br = new BufferedReader(fr);
			//String s2 = br.readLine();
			while (br.ready()) {
				
				Calendar cal = Calendar.getInstance();
				
				String read = br.readLine();
				cal.setTime(sdf.parse(read));			
				
				//String s2 = br.readLine();
				//s1 = read;	
				
				//Calendar c = Calendar.getInstance();  
				//Calendar c_add = Calendar.getInstance(); 		
				//c.setTime(df.parse(start));
				//c_add = c.add(Calendar.DAY_OF_WEEK, 21); 
				//System.out.println(df.format(c.getTime())); 
				
				
				long difference_front = cal.getTimeInMillis() - c.getTimeInMillis();
				long difference_end = cal.getTimeInMillis() - add.getTimeInMillis();
				//String process2 = String.valueOf(difference);
				//System.out.println(process2);
				long day_front = difference_front/(3600*24*1000);
				long day_end = difference_end/(3600*24*1000);
						
				//String process = String.valueOf(day); 
				int d_front = (int) day_front;
				int d_end = (int) day_end;
				if (d_front >= 0 && d_end <= 0){
					check++;
				}
				
				
				//System.out.println(d); 
			
			}
			
			if(first == 0){
				fw.write(df.format(c.getTime()) + " ~ " + df.format(add.getTime()) + "\t" + check + ",");
				first++;
			} 
			else if(i == (fileList.size()-1)){
				fw.write(check + "\n");
			}
			else{
				fw.write(check + ",");
			}
				
			
				fw.flush();
				fw.close();
			fr.close();		
		
		}
		
		c.add(Calendar.DAY_OF_WEEK, 7); 	
		start = df.format(c.getTime());
		long difference_cf = c_end.getTimeInMillis() - c.getTimeInMillis();
		
		long cf = difference_cf/(3600*24*1000);
		int d_cf = (int) cf;
		if(d_cf < 0){
			fin = 1;
		}
			
	}
	
	
	
	}
}

