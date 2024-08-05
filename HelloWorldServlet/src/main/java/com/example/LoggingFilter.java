package com.example;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@WebFilter("/hello") // This annotation specifies the URL patterns to be intercepted by this filter
public class LoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if needed
        System.out.println("LoggingFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Log the request
        System.out.println("Request received at " + new java.util.Date());

        // Continue the request-response cycle
        chain.doFilter(request, response);

        // Log the response
        System.out.println("Response sent at " + new java.util.Date());
    }

    @Override
    public void destroy() {
        // Cleanup code, if needed
        System.out.println("LoggingFilter destroyed");
    }
}
