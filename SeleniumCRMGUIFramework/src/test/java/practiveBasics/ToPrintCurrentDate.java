package practiveBasics;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ToPrintCurrentDate
{
	public static void main(String[] args) {
		Date date = new Date();
		System.out.println(date);
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		String startdate = sdf.format(date);
		System.out.println(startdate);
		
		
		
		Calendar cal = sdf.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String reqDate = sdf.format(cal.getTime());
		System.out.println(reqDate);
	}

}
