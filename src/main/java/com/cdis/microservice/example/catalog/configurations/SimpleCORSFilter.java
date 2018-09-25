package com.cdis.microservice.example.catalog.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SimpleCORSFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleCORSFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("Initializaong CORS filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest requestToUse = (HttpServletRequest) servletRequest;
        HttpServletResponse responseToUse = (HttpServletResponse) servletResponse;

        responseToUse.setHeader("Access-Control-Allow-Origin", requestToUse.getHeader("Origin"));

        filterChain.doFilter(requestToUse, responseToUse);
    }

    @Override
    public void destroy() {

    }
}
