package src.de.stuttgart.hft.sd.util;

import java.time.LocalDate;
import java.time.Month;

import org.json.JSONObject;

public class HoroscopeAccessSample {
	
	// See: https://github.com/tapasweni-pathak/Horoscope-API
	
	public static final String BASE_URL = "http://horoscope-api.herokuapp.com/horoscope";
	
	public static final String[] RANGE = {"today", "week", "month", "year"};
//	public static final String[] SIGNS = {"aries", "taurus", "gemini", "cancer", "leo", "virgo", "libra", "scorpio", "sagittarius", "capricorn", "aquarius", "pisces"};

	public static void main(String[] args) {
        ServiceAccess service = new ServiceAccess();
        
        {
		String request = BASE_URL + "/today/aries";
        JSONObject response = service.requestObject(request);
        System.out.println(response);
        }
        
        {
        LocalDate birthday = LocalDate.of(1990, Month.APRIL, 1);
        Sign sign = Sign.of(birthday);
        String request = BASE_URL + "/today/" + sign;
        JSONObject response = service.requestObject(request);
        System.out.println(response);        
        }
              
		System.out.println("=============================");
        for(String range : RANGE)
        	for(Sign sign : Sign.values()) {
        		String request = BASE_URL + "/" + range + "/" + sign;
        		JSONObject response = service.requestObject(request);
                if(response != null) {
	        		System.out.println(response.get((range.equals("today") ? "date" : range)));
	        		System.out.println(response.get("sunsign"));
	        		System.out.println(response.get("horoscope"));
                }      		
        		System.out.println("=============================");
        	}
    }
}
