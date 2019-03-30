package journalSystem;

import java.util.Date;

public class Paper {
	private Date dateSubmitted;
	private String title;
	private String status;
	private int submitNumber;
	
	public Paper() {
		
	}
	
	public Paper(Date d, String t, String s, int num) {
		this.dateSubmitted = d;
		this.title = t;
		this.status = s;
		this.submitNumber = num;
	}
	
	public Date getDate() {
		return this.dateSubmitted;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public int getNumber() {
		return this.submitNumber;
	}
}
