package src.de.stuttgart.hft.sd.util;

import java.time.Month;
import static java.time.Month.*;

import java.time.LocalDate;

public enum Sign {
	
	AQUARIUS(JANUARY, 20), PISCES(FEBRUARY, 19), ARIES(MARCH, 21), 
	TAURUS(APRIL, 20), GEMINI(MAY, 21), CANCER(JUNE, 21), 
	LEO(JULY, 23), VIRGO(AUGUST, 23), LIBRA(SEPTEMBER, 23), 
	SCORPIO(OCTOBER, 23), SAGITTARIUS(NOVEMBER, 22), CAPRICORN(DECEMBER, 22);
	
	@SuppressWarnings("unused")
	private Month month;
	private int day;

	private Sign(Month month, int day) {
		this.month = month;
		this.day = day;
	}
	
	public static Sign of(LocalDate date) {
		Sign[] tmp = Sign.values();
		Sign[] signs = new Sign[tmp.length + 1];
		
		signs[0] = tmp[tmp.length - 1];
		for(int i = 0; i < tmp.length; i++)
			signs[i+1] = tmp[i];
		
		int pos = date.getMonth().getValue(); // 1 to 12
		Sign sign = signs[pos];
		if(sign.day <= date.getDayOfMonth())
			return sign;
		return signs[pos - 1];		
	}
	
}
