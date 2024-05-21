package com.rion5.quantapi.tradedata;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

public class TradeDataRowMapper implements RowMapper<TradeData> {

	@Override
	@NonNull
	public TradeData mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
		TradeData tradeData = new TradeData(
			rs.getString("SYMBOL"),rs.getDate("TRADING_DATE"),
			rs.getFloat("OPEN"),rs.getFloat("HIGH"),rs.getFloat("LOW"),
			rs.getFloat("CLOSE"),rs.getInt("VOLUME"),rs.getFloat("ADJUSTED"),rs.getDate("INSERT_DATE")
		);


		// TradeData tradeData = new TradeData();
		// tradeData.setSymbol(rs.getString("SYMBOL"));
		// tradeData.setTrading_date(rs.getDate("TRADING_DATE"));
		// tradeData.setOpen(rs.getInt("OPEN"));
		// tradeData.setHigh(rs.getInt("HIGH"));
		// tradeData.setLow(rs.getInt("LOW"));
		// tradeData.setClose(rs.getInt("CLOSE"));
		// tradeData.setVolume(rs.getInt("VOLUME"));
		// tradeData.setAdjusted(rs.getInt("ADJUSTED"));
		// tradeData.setInsert_date(rs.getDate("INSERT_DATE"));
		return tradeData;
	}

}
