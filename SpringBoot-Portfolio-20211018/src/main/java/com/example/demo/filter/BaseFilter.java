package com.example.demo.filter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;

import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@Component
public class BaseFilter extends HttpFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, getFilterConfig().getServletContext());
	}
	
}
