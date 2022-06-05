package com.tasha.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Shahrukh
 * @since June 03, 2022
 */
@WebServlet("/add_cart")
public class AddToCartServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		try(PrintWriter pw = response.getWriter();){
			//HttpSession
			HttpSession session = request.getSession();
			
			//get cart from session
			ArrayList<Integer> cart = (ArrayList<Integer>) session.getAttribute("cart");
			
			//get selected ids --String[]
			String[] ids = request.getParameterValues("bookTitle");
			
			//add ids to cart
			for(String id : ids)
				cart.add(Integer.parseInt(id));
			
			//redirect user to category page
			response.sendRedirect("category");
		} catch (Exception e) {
			throw new ServletException("Error in doGet() of " + getClass().getName(), e);
		}
	}
}
