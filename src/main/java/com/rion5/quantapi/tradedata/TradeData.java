package com.rion5.quantapi.tradedata;

import java.sql.Date;

// import lombok.Data;

// @Data
// public class TradeData {

// 	private String symbol;
// 	private Date trading_date;
// 	private Integer open;
// 	private Integer high;
// 	private Integer low;
// 	private Integer close;
// 	private Integer volume;
// 	private Integer adjusted;
// 	private Date insert_date;

// }

public record TradeData(String symbol, Date trading_date, float open, float high, float low, float close, int volume, float adjusted, Date insert_date) {
}