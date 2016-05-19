package com.tradeit.actions.accounts;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class authFilter
 */
@WebFilter("/*")
public class AuthFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		((HttpServletResponse)response).setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		if(((HttpServletRequest)request).isRequestedSessionIdValid()) {
			System.out.println("session is valid");
			chain.doFilter(request, response);
		}
		else if(((HttpServletRequest)request).getRequestURI().startsWith("/Tradeit/login") || ((HttpServletRequest)request).getRequestURI().startsWith("/Tradeit/register")) {
			System.out.println("login/register request");
			chain.doFilter(request, response);
		}
		else {
			System.out.println("session is not valid");
			((HttpServletResponse)response).sendRedirect("/Tradeit/login.html");
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
