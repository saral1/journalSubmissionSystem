package journalSystem;

import java.util.Calendar;
import java.util.Date;

public class TodaysDate {
	private Date today;
	
	public TodaysDate() {
        Calendar c = Calendar.getInstance(); 
        c.clear();
        c.set(2019, Calendar.MARCH, 28); //Year, month and day of month
        this.today = c.getTime();
	}
	
	public Date getDate() {
		return this.today;
	}
	
	public void setMonth(int mon) {
		Calendar c = Calendar.getInstance();
		c.setTime(today);
		c.set(Calendar.MONTH, mon);
		this.today = c.getTime();
	}
	
	public void setDay(int day) {
		Calendar c = Calendar.getInstance();
		c.setTime(today);
		c.set(Calendar.DATE, day);
		this.today = c.getTime();
	}

}
