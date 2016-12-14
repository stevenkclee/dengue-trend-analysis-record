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

public class dengue_diff_date {
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
	
		File f = new File("00"); //讀取"00這個資料夾"，要記得將此資料夾放置同個java file裡面
		ArrayList<String> fileList = new ArrayList<String>(); //宣告一的動態陣列為String的型態，目的用來儲存檔名

		if(f.isDirectory()){
	 
			System.out.println("filename : "+f.getName()); //印出我們所讀到的資料夾
			String []s=f.list(); //宣告一個list
			System.out.println("size : "+s.length); //印出資料夾裡的檔案個數
				for(int i=0;i<s.length;i++){
					//System.out.println(s[i]);
					fileList.add(s[i]); //將檔名一一存到fileList動態陣列裡面
				}
		}	
		
		
		c.setTime(df.parse(start));
		add.setTime(df.parse(start));
		add.add(Calendar.DAY_OF_WEEK, 21); 		
		
		int first = 0;
		
		for(int i=0;i<fileList.size();i++){

			FileReader fr = new FileReader("/home/hdc/data_preprocessing/00/" + fileList.get(i));

			FileWriter fw = new FileWriter("/home/hdc/data_preprocessing/00_output/final.txt", true);
			
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


//DateFormat df=new SimpleDateFormat("yyyy/MM/dd");  
//System.out.println(df.format(new Date()));  
//Date date = new Date();  
//DateFormat shortDate=DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);  
//DateFormat mediumDate =DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);  
//DateFormat longDate =DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);  
//DateFormat fullDate =DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);  

//system.out.println(shortDate.format(date));  
//System.out.println(mediumDate.format(date));  
//System.out.println(longDate.format(date));  
//System.out.println(fullDate.format(date));  
 
//08-4-15 下午3:24  
//2008-4-15 15:24:31  
//2008年4月15日 下午03時24分31秒  
//2008年4月15日 星期二 下午03時24分31秒CST  