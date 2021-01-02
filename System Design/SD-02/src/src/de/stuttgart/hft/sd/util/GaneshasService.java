package src.de.stuttgart.hft.sd.util;

import org.json.JSONObject;

public class GaneshasService implements HoroscopeService {

	private static final String BASE_URL = "http://horoscope-api.herokuapp.com/horoscope";
	private final ServiceAccess service = new ServiceAccess();

	@Override
	public String getHoroscope(Sign sign) {

		
		String request = BASE_URL + "/today/aries";
		JSONObject response = service.requestObject(request);

		request = BASE_URL + "/today/" + sign;
		response = service.requestObject(request);
		String horoscope = response.get("horoscope").toString();
		return horoscope;

	}
}