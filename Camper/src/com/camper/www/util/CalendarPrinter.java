package com.camper.www.util;

import java.util.Calendar;

public class CalendarPrinter {
	private Calendar calendar;
	private String[][] calDate = new String[6][7];
	private String[] title = {"일","월","화","수","목","금","토"};
	private int width = title.length; 
	private int startday; 
	private int lastday; 
	private int day=1; 
	public CalendarPrinter(int year, int month) {
		if(month<1 || month>12) {
			System.exit(1);
		}
		calendar = Calendar.getInstance();
		calendar.set(year, month-1, 1);
		startday = calendar.get(Calendar.DAY_OF_WEEK);
		lastday = calendar.getActualMaximum(Calendar.DATE);
		int row = 0;
		for(int i=1 ; day<=lastday ; i++) {
			if(i<startday) {
				calDate[row][i-1] = "";
			}else {
				calDate[row][(i-1)%width] = String.valueOf(day++);
				if(i%width==0) row++;
			}
		}
	}
	public CalendarPrinter() {
		calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		calendar.set(year, month, 1);
		startday = calendar.get(Calendar.DAY_OF_WEEK); 
		lastday = calendar.getActualMaximum(Calendar.DATE);
		int row = 0;
		for(int i=1 ; day<=lastday ; i++) {
			if(i<startday) {
				calDate[row][i-1] = "";
			}else {
				calDate[row][(i-1)%width] = String.valueOf(day++);
				if(i%width==0) row++;
			}
		}
	}
	public void printConsole() {
		for(String t :title) {
			System.out.print(t + "\t");
		}
		System.out.println();
		for(int i=0 ; i<calDate.length ; i++) {
			for(int j=0 ; j<calDate[i].length ; j++) {
				if(calDate[i][j]!=null) {
					System.out.print(calDate[i][j] + "\t");
				}
			}
			System.out.println();
		}
	}
	public String[][] getCalDate() {
		return calDate;
	}
	public String[] getTitle() {
		return title;
	}
}
