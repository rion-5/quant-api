// test url  https://localhost:8443/tradeData?symbol=TSLA

package com.rion5.quantapi.tradedata;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rion5.quantapi.dto.tradedatachart.Multiple;
import com.rion5.quantapi.jwt.JwtTokenProvider;

import jakarta.servlet.http.HttpServletRequest;

@RestController

public class TradeDataController {
	public static final String AUTHORIZATION_HEADER = "Authorization";
	@Autowired
	private TradeDataService tradeDataService;

	 @Autowired
	 private JwtTokenProvider jwtTokenProvider;

	@GetMapping(value = "tradeData")
	public ResponseEntity<List<TradeData>> getTradeDataList(@RequestParam(value = "symbol") String symbol,
			@RequestParam(value = "start_date") LocalDate start_date,
			@RequestParam(value = "end_date") LocalDate end_date, HttpServletRequest request) {
		String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")
				&& jwtTokenProvider.validateToken(bearerToken.substring(7))) {
			return ResponseEntity.ok(tradeDataService.getTradeDataList(symbol, start_date, end_date));

		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
	
//	@GetMapping(value = "tradeData")
//	public ResponseEntity<List<TradeData>> getTradeDataList(@RequestParam(value = "symbol") String symbol,
//			@RequestParam(value = "start_date") LocalDate start_date,
//			@RequestParam(value = "end_date") LocalDate end_date) {
//
//		return ResponseEntity.ok(tradeDataService.getTradeDataList(symbol, start_date, end_date));
//
//	}
	
	@GetMapping(value = "tradeDataForChart")
	public ResponseEntity<List<Multiple>> getTradeDataForChart(@RequestParam(value = "symbol") String symbol,
			@RequestParam(value = "start_date") LocalDate start_date,
			@RequestParam(value = "end_date") LocalDate end_date) {

		return ResponseEntity.ok(tradeDataService.getTradeDataForChart(symbol, start_date, end_date));

	}
	
}
