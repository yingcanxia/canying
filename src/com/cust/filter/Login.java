package com.cust.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.cust.util.PageData;

/**
 * Servlet Filter implementation class Login
 */
public class Login implements Filter {

    /**
     * Default constructor. 
     */
    public Login() {
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
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		HttpServletRequest req = (HttpServletRequest)request;  
        HttpServletResponse res = (HttpServletResponse)response;  
        HttpSession session = req.getSession(false);
        String path = req.getRequestURI();
	    String page=path.substring(path.lastIndexOf("/"));
	    if(path.lastIndexOf(".")>=0){
		    String cssOrJs=path.substring(path.lastIndexOf("."));
		    if(cssOrJs.equals(".js")||cssOrJs.equals(".css")||cssOrJs.equals(".png")||cssOrJs.equals(".jpg")||cssOrJs.equals(".gif")){  // ��½ҳ���������
	        	chain.doFilter(request, response);
	        	return;
	        }
	    }
        if(session == null){
        	res.sendRedirect(req.getContextPath() + "/index.html");    
            return;
        }
        PageData pduser=(PageData) session.getAttribute("pduser");
        if(pduser==null)  
        {  
            res.sendRedirect(req.getContextPath() + "/index.html");    
            return;  
        }  
       /* if(cssOrJs.equals(".js")||cssOrJs.equals(".css")||cssOrJs.equals(".png")||cssOrJs.equals(".jpg")||cssOrJs.equals(".gif")){  // 登陆页面无需过滤
        	chain.doFilter(request, response);
        	return;
        }*/

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
