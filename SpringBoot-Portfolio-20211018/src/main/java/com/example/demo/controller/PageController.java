package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {
	
	@RequestMapping("/home")
	public String getHomePage() {
		return "home";
	}
	
	@RequestMapping("/classify")
	public String getClassifyPage() {
		return "classify";
	}
	
	@RequestMapping("/investor")
    public String getInvestorPage() {
        return "investor";
    }
    
    @RequestMapping("/tstock")
    public String getTStockPage() {
        return "tstock";
    }
    
    @RequestMapping("/watch")
    public String getWatchPage() {
        return "watch";
    }
    
    @RequestMapping("/watchlist")
    public String getWatchListPage() {
        return "watchlist";
    }
    
    @RequestMapping("/asset")
    public String getAssetPage() {
        return "asset";
    }
	
}
