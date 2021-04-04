package com.rohan.servlet.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SrvCtxServlet
 */
@WebServlet("/SrvCtxServlet")
public class SrvCtxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SrvCtxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	        ServletContext servletContext = getServletContext();
	    
	        String maxShoppingCartSize = servletContext.getInitParameter("max-shopping-cart-size");
	        String codingGuruName = servletContext.getInitParameter("coding-guru-name");
	    
	       response.setContentType("text/html");
	        
	        PrintWriter out = response.getWriter();
	        
	        out.println("<html> <body>");
	        out.println("Coding Guru: " + codingGuruName);
	        out.println("<br/>");
	        out.println("Max Shopping Cart Size: " + maxShoppingCartSize);
	        out.println("<body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
