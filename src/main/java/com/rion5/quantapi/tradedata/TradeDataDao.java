package com.rion5.quantapi.tradedata;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TradeDataDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

//	public void setDataSource(DataSource dataSource) {
//	    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
//	}

	public List<TradeData> getTradeDataList(String symbol, LocalDate start_date, LocalDate end_date) {
		String query = "SELECT symbol,trading_date, open,high,low,close,volume,adjusted,insert_date FROM stock WHERE symbol= :symbol "
				+ " AND trading_date between :start_date and :end_date order by trading_date ;";
//		System.out.println(query);
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("symbol", symbol);
		param.addValue("start_date", start_date);
		param.addValue("end_date", end_date);

		List<TradeData> tradeData = namedParameterJdbcTemplate.query(query, param, new TradeDataRowMapper());
		return tradeData;

	}
}
