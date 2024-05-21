package com.rion5.quantapi.tradedata;

import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rion5.quantapi.dto.tradedatachart.Multiple;
import com.rion5.quantapi.dto.tradedatachart.Series;


@Service
public class TradeDataService {
	@Autowired
	TradeDataDao tradeDataDao;

	public List<TradeData> getTradeDataList(String symbol, LocalDate start_date, LocalDate end_date) {
		return tradeDataDao.getTradeDataList(symbol, start_date, end_date);
	}
	
	public List<Multiple> getTradeDataForChart(String symbol, LocalDate start_date, LocalDate end_date) {

// 구 코드 	
//		List<TradeData> tradeData = tradeDataDao.getTradeDataList(symbol, start_date, end_date);
//		ArrayList<Series> seriesList = new ArrayList<Series>();
//		for (int i= 0; i<tradeData.size();i++) {
//			Series tempSeries = new Series(tradeData.get(i).getTrading_date().toString(),tradeData.get(i).getClose());		
//			seriesList.add(tempSeries);
//		}		
//		Multiple m1 = new Multiple(symbol, seriesList);
//		ArrayList<Multiple> multiple1 = new ArrayList<Multiple>();
//		multiple1.add(m1);
		
//람다 표현식 			
		List<TradeData> tradeData = tradeDataDao.getTradeDataList(symbol, start_date, end_date);

		List<Series> seriesList = tradeData.stream()
		    .map(trade -> new Series(trade.trading_date().toString(), trade.close()))
		    .toList();

		Multiple m1 = new Multiple(symbol, seriesList);
		List<Multiple> multiple1 = List.of(m1);
		
		return multiple1;
	}

}
