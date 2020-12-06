package de.stuttgart.hft.stock.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Stock{

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public String symbol, name, currency = "USD";
	public double price, price_open, day_high, day_low;
	@JsonProperty("52_week_high")public double _52_week_high;
	@JsonProperty("52_week_low")public double _52_week_low;
	public double day_change, change_pct, close_yesterday;
	public long market_cap, volume, volume_avg, shares;
	public String stock_exchange_long = "New York Stock Exchange", stock_exchange_short ="NYSE", timezone = "EST", timezone_name = "America/New_York";
	public int gmt_offset;
	public LocalDateTime last_trade_time = LocalDateTime.now();
	public double pe;
	public double eps;
	
	public Stock() {}

	@JsonProperty("last_trade_time")
	public String getLast_trade_time() {
		return last_trade_time.format(FORMATTER);
	}
	
	public void setLast_trade_time(String last_trade_time) {
		this.last_trade_time = LocalDateTime.parse(last_trade_time, FORMATTER);
	}
	
	@JsonProperty("pe")
	public String getPe() {
		if(pe == Double.NaN)
			return "N/A";
		else
			return Double.toString(pe);
	}

	public void setPe(String pe) {
		try {
			this.pe = Double.parseDouble(pe);
		} catch (NumberFormatException e) {
			this.pe = Double.NaN;
		}
	}

	@JsonProperty("eps")
	public String getEps() {
		if(eps == Double.NaN)
			return "N/A";
		else
			return Double.toString(eps);
	}

	public void setEps(String eps) {
		try {
			this.eps = Double.parseDouble(eps);
		} catch (NumberFormatException e) {
			this.eps = Double.NaN;
		}
	}
	
	@Override
	public String toString() {
		return String.format(Locale.US, "%s : %f", symbol, price);
	}
	
}

