package com.example.demo.controller;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.TStock;
import com.example.demo.repository.TStockRepository;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

@RestController
@RequestMapping("/price")
public class PriceController {
	@Autowired
	private TStockRepository tStockRepository;
	
	@RequestMapping("/refresh")
	@Transactional
	public List<TStock> refresh() {
		List<TStock> list = tStockRepository.findAll();
		
		for(TStock st : list) {
			try {
				// 抓取報價
				Stock stock = YahooFinance.get(st.getSymbol());
				st.setChangePrice(stock.getQuote().getChange());
				st.setChangeInPercent(stock.getQuote().getChangeInPercent());
				st.setPreClosed(stock.getQuote().getPreviousClose());
				st.setPrice(stock.getQuote().getPrice());
				st.setTransactionDate(stock.getQuote().getLastTradeTime().getTime());
				st.setVolumn(stock.getQuote().getVolume());
				// 更新報價
				tStockRepository.updatePrice(
						st.getId(), 
						st.getChangePrice(), 
						st.getChangeInPercent(), 
						st.getPreClosed(), 
						st.getPrice(), 
						st.getTransactionDate(), 
						st.getVolumn());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return list;
	}
	
	@RequestMapping("/histquotes/{symbol:.+}")
	public List<HistoricalQuote> queryHistoricalQuotes(
			@PathVariable("symbol") String symbol ){
		List<HistoricalQuote> historicalQuotes =null ;
		try {
			Calendar from = Calendar.getInstance();
			Calendar to   = Calendar.getInstance();//現在日期
			from.add(Calendar.MONTH, -1); //上個月
			Stock stock = YahooFinance.get(symbol);
			historicalQuotes = stock.getHistory(from , to ,Interval.DAILY);
			//每月報價
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  historicalQuotes;
	}
	
	
}